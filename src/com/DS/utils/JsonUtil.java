package com.DS.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/****
 * 
 * @author jeff
 * json相关的工具类
 */
public class JsonUtil {
	/****
	 * 将离散的树形结构数据重组成树形数据集合体
	 * （一般用于从数据库取得数据后重组成树形结构集合体）
	 * @param arr JSONArray类型的待转换数据
	 * @param id  自身节点
	 * @param pid 父节点
	 * @param child 子孩子集合名
	 * @return
	 */
	public static JSONArray listToTree(JSONArray arr,String id,String pid,String child){
		   JSONArray r = new JSONArray();//存放重组的的数据
		   JSONObject hash = new JSONObject();
		   for(int i=0;i<arr.size();i++){//将数组转换成键值对，自身节点为key,自身为value
		      JSONObject json = (JSONObject) arr.get(i);
		      hash.put(json.getString(id), json);
		   }
		   //遍历结果集
		   for(int j=0;j<arr.size();j++){
		      JSONObject aVal = (JSONObject) arr.get(j);//从中取一条数据
		      JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());//取出这条记录的父节点联系，利用此联系找到其父节点
		      if(hashVP!=null){//存在父节点
		         if(hashVP.get(child)!=null){//有子节点的标志
		        	//有子节点标志，直接加
		            JSONArray ch = (JSONArray) hashVP.get(child);
		            ch.add(aVal);
		            hashVP.put(child, ch);
		            hashVP.put("open", true);//默认展开
		         }else{//无子节点的标志,创建子节点再添加
		            JSONArray ch = new JSONArray();
		            ch.add(aVal);
		            hashVP.put(child, ch);
		            hashVP.put("open", true);//默认展开
		         }
		      }else{//不存在父节点，为最高节点
		         r.add(aVal);//只添加顶级节点
		      }
		   }	
		   return r;
		}
		
	/***
	 * 树形结构映射
	 * 所有父节点映射的所有叶子
	 */
	public static Map<String, String> teeMap(JSONArray arr,String id,String pid){	
			JSONObject hashList = new JSONObject();//存放键值对
			Map<String, String> treemap=new HashMap<String, String>();//存放映射集
			String tempValue="";
			String pidMap="";
			String idValue="";
			//转成键值对，key为自身节点，value为本身
		   for(int i=0;i<arr.size();i++){
		      JSONObject jsonTemp = (JSONObject) arr.get(i);
		      hashList.put(jsonTemp.getString(id), jsonTemp);
		   }
		   //将treemap的映射转为一级映射
		   for(int i=0;i<arr.size();i++){//遍历键值对			 
			   JSONObject aVal = (JSONObject) arr.get(i);
			   idValue=aVal.get(id).toString();//目前节点
			   pidMap=aVal.get(pid).toString();//目前节点的父节点			 
			   JSONObject hashVP = (JSONObject) hashList.get(pidMap);		   
			   if(hashVP!=null){//父节点存在
				   if(treemap.get(pidMap)==null){
					   treemap.put(pidMap, idValue);
				   }else{
					   tempValue=treemap.get(pidMap).toString()+","+idValue;
					   treemap.put(pidMap, tempValue);
				   }
			   }
		   }		
		   Set<String> key = treemap.keySet();
		   for (Iterator<String> it = key.iterator(); it.hasNext();) {
			   String s = it.next();
			   String[] temp=treemap.get(s).split(",");
			   for(int i=0;i<temp.length;i++){
				   temp[i]=getTreeLeaf(treemap,temp[i]);
			   }
			   //把数组中的叶子再次拼接成字符串
			   for(int i=0;i<temp.length;i++){
				   if(i==0){
					   tempValue=temp[i];
				   }else{
					   tempValue+=","+temp[i]; 
				   }
			   }
			   treemap.put(s, tempValue);
		  }
		   //==============打印查看	   
		  /* Set<String> key1 = treemap.keySet();
		   for (Iterator<String> it = key1.iterator(); it.hasNext();) {
			   String s = it.next();
			   System.out.println(s+":"+treemap.get(s));
		   }*/
		   return treemap;
	}
	
	/***
	 * treemap:key为节点，value为此节点的一级映射节点
	 * 也就是说treemap只映射下一级，下一级可能是节点或是叶子
	 * 这个方法就是节点再次映射下一级
	 * 得到一个节点对应的所有叶子
	 * return 叶子拼接而成的字符串
	 */
	public static String getTreeLeaf(Map<String, String> treemap,String node){
			String tempSum="";
			if(treemap.get(node)!=null){//此节点不是叶子
				   String[] temp=((String) treemap.get(node)).split(",");
				   for(int i=0;i<temp.length;i++){
					   temp[i]=getTreeLeaf(treemap,temp[i]);
					    if(i==0){
					    	tempSum=temp[i];
					    }else{
					    	tempSum+=","+temp[i];
					    }
				   }					
			}else{//是叶子
				 tempSum=node;
			}	
			return tempSum;
	}
}
