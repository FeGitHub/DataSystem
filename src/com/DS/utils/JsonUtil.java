package com.DS.utils;
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
}
