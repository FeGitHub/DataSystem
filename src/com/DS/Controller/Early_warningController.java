package com.DS.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.Bean.MaterialBean;
import com.DS.utils.JsonUtil;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 *	用于预警信息的控制器
 */
public class Early_warningController extends BaseController {	
	/***
	 * 订单预警判定的测试入口，
	 * 测试数据的准备
	 */
	public void test(){
		  List<Record> ruleList=new ArrayList<Record>();//存放预警规则
		  ruleList=getAllWarnRules();
		   int  orderID=233;//订单ID
		  /* Record r1=new Record();
		   r1.set("id", 345);
		   r1.set("early_warning_name", "规则1：数量预警");
		   r1.set("early_warning_type", "数量预警");//预警类型
		   r1.set("min", 30);//最小值
		   r1.set("max", 60);//最大值
		   r1.set("material_type","11");//影响物料的类型
		   
		   Record r2=new Record();
		   r2.set("id", 3455);
		   r2.set("early_warning_name", "规则2：总价预警");
		   r2.set("early_warning_type", "总价预警");//预警类型
		   r2.set("min", 300);//最小值
		   r2.set("max", 600);//最大值
		   r2.set("material_type","11");//影响物料的类型
		   
		   Record r3=new Record();
		   r3.set("id", 9455);
		   r3.set("early_warning_name", "规则3：单价预警");
		   r3.set("early_warning_type", "单价预警");//预警类型
		   r3.set("min", 30);//最小值
		   r3.set("max", 60);//最大值
		   r3.set("material_type","1");//影响物料的类型
		   	 
		   ruleList.add(r1);
		   ruleList.add(r2);
		   ruleList.add(r3);
		   */
		   
		   
		   MaterialBean bean1=new MaterialBean();//测试物料1
		   bean1.setAmount(30);
		   bean1.setMaterial_name("铅笔");
		   bean1.setNode("111");
		   bean1.setPrice(20);
		   
		   MaterialBean bean2=new MaterialBean();//测试物料2
		   bean2.setAmount(30);
		   bean2.setMaterial_name("圆珠笔");
		   bean2.setNode("112");
		   bean2.setPrice(20);
		   
		   MaterialBean bean3=new MaterialBean();//测试物料3
		   bean3.setAmount(50);
		   bean3.setMaterial_name("A5");
		   bean3.setNode("133");
		   bean3.setPrice(20);
		   
		   
		   Map<String,MaterialBean> materialMap=new HashMap<String, MaterialBean>();
		   materialMap.put(bean1.getNode(), bean1);
		   materialMap.put(bean2.getNode(), bean2);
		   materialMap.put(bean3.getNode(), bean3);
		   
		  
		   //========开始测试=======
		    String warnStr="";//预警项集合
		    boolean flag=true;//true表示没有触发预警
		    for(int i=0;i<ruleList.size();i++){//遍历预警规则
		    	if(!judgeOrder(materialMap,ruleList.get(i),orderID)){
		    		flag=false;
		    		if(warnStr.equals("")){
		    			warnStr=ruleList.get(i).getStr("id");
		    		}else{
		    			warnStr+=","+ruleList.get(i).getStr("id");
		    		}
		    	}
		    }
		    if(!flag){//预警菜单的处理
		    	setWarnOrder(orderID,warnStr);
		    }else{
		    	System.out.println("订单判定正常");
		    }
		   //==================
		   //judgeOrder(materialMap,r1,orderID);
	}
	
	
	/***
	 * 判定订单是否触发预警规则
	 * materialMap:订单中要判定的物料，key为节点，value为信息
	 */
	public  boolean judgeOrder(Map<String,MaterialBean> materialMap,Record r,int orderID){
		//=========
			String strtemp="";
		//========
		System.out.println("信息提示------>"+r.getStr("early_warning_name")+"正在被判定");
		boolean flag=true;
		String early_warning_type=r.getStr("early_warning_type");//预警类型
		int max=r.getInt("max");
		int min=r.getInt("min");
		String RuleNodeData=r.getStr("material_type");//预警类型	
		List<String> OrderNodeList = new ArrayList<String>();
		  for(String key : materialMap.keySet()){
		     OrderNodeList.add(key);//订单的物料的节点集（包含要判定的不要判定的）
		     strtemp+=key+",";
		  }	
		  //============
		  System.out.println("信息提示------>订单的叶子们："+strtemp);
		  //================
		  List<String> judgeList= getJudgeMaterial(OrderNodeList,RuleNodeData);//筛选后的，要判定的物料的节点
		  if(judgeList.size()==0){
			  System.out.println("信息提示------>没有要判定的物料，订单正常");
			  return true;
		  }
		  if(early_warning_type.equals("数量预警")){
			  flag=AmountWarn(judgeList,materialMap,min,max);
		  }else if(early_warning_type.equals("单价预警")){
			  flag=PriceWarn(judgeList,materialMap,min,max);
		  }else{//总价预警
			  flag=SumPriceWarn(judgeList,materialMap,min,max);
		  }
		  if(flag){
			  //System.out.println("信息提示------>订单判定正常");
			  return true;
		  }else{
			 // System.out.println("信息提示------>应该发出预警");
			  return false;
		  }
		  //============
	}
	
	
	/****
	 * 获取预警规则信息
	 */
	public void getWarnRuleDetails(){	
		String sql="SELECT * FROM  early_warning_rule";
		List<Record> WarnRuleDetails=Db.find(sql);
		Map<String, List<Record>> map = new HashMap<String, List<Record>>();
		map.put("data", WarnRuleDetails);
        renderJson(map);
	}
	
	
	
	/***
	 * 获取应该被判定的预警物料
	 * OrderNodeList:订单中物料的节点结合
	 * RuleNodeData：预警节点
	 */
	public List<String> getJudgeMaterial(List<String> OrderNodeList,String RuleNodeData){
		List<String> RuleNodeList = new ArrayList<String>();
	    JSONArray tempArray=getBasicTreeData();
		Map treeMap=JsonUtil.teeMap(tempArray, "id", "pid");//获取父节点与旗下的所有的叶子的映射关系
		String str;//存放该预警节点的所有映射叶子组成的字符串
		if(treeMap.get(RuleNodeData)!=null){
			str=treeMap.get(RuleNodeData).toString();
		}else{//该节点是叶子
			str=RuleNodeData;
		}
		//===========
		System.out.println("信息提示------>"+"预警节点对应的所有叶子："+str);
		//==========
		String[] treemapStr=str.split(",");//把映射叶子切分放入数组
		for(int i=0;i<treemapStr.length;i++){//把叶子放入list
			RuleNodeList.add(treemapStr[i]);
		}
		RuleNodeList.retainAll(OrderNodeList);//订单中的物料和预警规则的物料的交集，即涉及判定的物料		
		//=========remove1=====
		String strtemp="";
		for(int i=0;i<RuleNodeList.size();i++){
			strtemp+=RuleNodeList.get(i)+",";
		}
		System.out.println("信息提示------>"+"要判定的叶子："+strtemp);
		//===========remove1=====
		return RuleNodeList;
	}
	
	/***
	 * 获取物料类型的树形结构
	 */
	public void getTree(){
		 JSONArray tempArray=getBasicTreeData();		
		 JSONArray result = JsonUtil.listToTree(tempArray,"id","pid","children");
		//System.out.println(JSON.toJSONString(result));
		 renderJson(result);
	}
	
	/***
	 * 从数据库中得到树形结构的基础数据，并把它转换成JSONArray形式
	 */
	public JSONArray getBasicTreeData(){
		String sql="SELECT own_node,parent_node,material_name FROM  materialTree";
		List<Record> treeDetails=Db.find(sql);	
		List<Map<String,Object>> data = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		for(Record c:treeDetails){
			 map = new HashMap<>();
			 map.put("id",c.get("own_node"));
			 map.put("pid",c.get("parent_node"));
			 map.put("name",c.get("material_name"));
			 data.add(map);
		}	
		return JSONArray.parseArray(JSON.toJSONString(data));
	}
	
	/***
	 * 总价预警判定
	 * @return
	 */
	public boolean SumPriceWarn(List<String> judgeList,Map<String,MaterialBean> materialMap,int min,int max){
		System.out.println("***************数量预警信息***********");
		System.out.println("信息提示------>总价合法范围："+min+"到"+max);
		MaterialBean temp;
		 double allCount=0;
		 for(int i=0;i<judgeList.size();i++){
			 temp=materialMap.get(judgeList.get(i));//要判定的物料
			 System.out.println(temp.getMaterial_name()+":总价为"+temp.getPrice()*temp.getAmount());
			 allCount+=temp.getPrice()*temp.getAmount();
		 }
		 System.out.println("信息提示------>总价为"+allCount);
		 System.out.println("*************************");
		 System.out.println("");
		 if(allCount<=min||allCount>max){			
				return false;
			}else{				
				return true;
			}
	}
	/***
	 * 数量预警判定
	 * @return
	 */
	public boolean AmountWarn(List<String> judgeList,Map<String,MaterialBean> materialMap,int min,int max){
		System.out.println("**************数量预警信息**************");
		System.out.println("信息提示------>数量合法范围："+min+"到"+max);
		 MaterialBean temp;
		 int amount=0;
		 for(int i=0;i<judgeList.size();i++){
			 temp=materialMap.get(judgeList.get(i));//要判定的物料
			 System.out.println(temp.getMaterial_name()+":数量为"+temp.getAmount());
			 amount+=temp.getAmount();
		 }
		 System.out.println("信息提示------>总数量为"+amount);
		 System.out.println("****************************");
		 System.out.println("");
		if(amount<=min||amount>max){		
			return false;
		}else{			
			return true;
			
		}
	}
	
	/***
	 * 单价预警判定
	 * @return
	 */
	public boolean PriceWarn(List<String> judgeList,Map<String,MaterialBean> materialMap,int min,int max){
		boolean flag=true;
		System.out.println("**************单价预警信息********");
		System.out.println("信息提示------>单价合法范围："+min+"到"+max);
		 MaterialBean temp;
		 for(int i=0;i<judgeList.size();i++){
			 temp=materialMap.get(judgeList.get(i));//要判定的物料
			if(temp.getPrice()<min||temp.getPrice()>max){
				flag=false;
				 System.out.println(temp.getMaterial_name()+":单价为"+temp.getPrice()+",不在合法范围内");
			}else{
				 System.out.println(temp.getMaterial_name()+":单价为"+temp.getPrice()+",在合法范围内");
			 }
		 }		
		 System.out.println("***********************");
		 System.out.println("");
		 return flag;
	}
	
	/***
	 * 获取全部预警规则
	 */
	public List<Record> getAllWarnRules(){
		String sql="SELECT * FROM  early_warning_rule";
		List<Record> WarnRuleDetails=Db.find(sql);
		return WarnRuleDetails;
	}
	
	/***
	 * 当订单被判定为预警订单时，订单表要做出的处理
	 * OrderID:订单ID
	 * warnRuleID:触发的预警的ID
	 */
	public void setWarnOrder(int OrderID,String warnRuleIDS){
		System.out.println("信息提示------------->订单触发的预警规则："+warnRuleIDS);
	}
	/***
	 * 跳转预警规则编辑页面
	 */
	public void goWarnRule(){
		render("warnRule.jsp");
	}
	/***
	 * 跳转预警规则编辑页面
	 */
	public void goWarnRuleAdd(){
		render("warnRuleAdd.jsp");
	}
	
	/***
	 * 跳转到订单预警判定界面
	 */
	public void goOrderCheck(){
		render("orderCheck.jsp");
	}
	
	/***
	 * 跳转到TH的订单预警判定界面
	 */
	public void goTh_OrderCheck(){
		render("th_orderCheck.jsp");
	}
}
