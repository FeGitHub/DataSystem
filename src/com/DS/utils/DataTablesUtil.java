package com.DS.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/*****
 * 
 * @author jeff
 * 配合dataTables插件的分页工具类
 */
public class DataTablesUtil {
   private static List<Record> showPageData;
   private static Record rec;
   private static Map<String,Object> PageData=new HashMap<String,Object>();
	
   /****
    * 
    * @param sqlShow  获取此页面应该展示的具体数据
    * @param sqlTotal 获取数据总条数(有查询条件或无查询条件的总数据)的sql
    * @return
    */
   public static Map<String,Object> getPageData(SqlPara sqlShow,SqlPara sqlTotal){
    	rec=Db.findFirst(sqlTotal);
    	showPageData=Db.find(sqlShow);
    	PageData.put("recordsTotal", rec.getLong("total"));//总记录数据
    	PageData.put("recordsFiltered", rec.getLong("total"));//被过滤后的数据
    	PageData.put("data", showPageData);//分页展示数据
    	return PageData;
    	
    }
}
