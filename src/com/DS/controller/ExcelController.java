package com.DS.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.FinanceExcelBean;
import com.DS.excel.service.ExcelService;
import com.DS.excel.service.impl.ExcelServiceImpl;
import com.DS.fileService.FileService;
import com.DS.fileService.impl.FileServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.upload.UploadFile;
/****
 * 
 * @author jeff
 * 用于处理excel操作的控制器
 */
public class ExcelController extends BaseController{
	@Inject(FileServiceImpl.class)
	private FileService fileService;
	
	@Inject(ExcelServiceImpl.class)
	private ExcelService excelService;
	/****
	 *  将excel中的财务信息导入到数据库中
	 */
	 public void importFinanceToDB(){
		  //第一步文件上传
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  File file = uploadFile.getFile();
		  //第二步将文件信息拷贝
		  File myFile=fileService.uploadFile(file);
		  ArrayList<FinanceExcelBean> DBList=excelService.getInfoByFinanceExcel(myFile);
		  //第三步读取信息并插入数据库
		  Map<String, List<FinanceExcelBean>> map=new HashMap<String, List<FinanceExcelBean>>();
		  map.put("cond", DBList);
		  SqlPara updateSql=Db.getSqlPara("excel.insertDataBatch", map);
		  System.out.println(updateSql);
		  int result=Db.update(updateSql);	
		  if(result>0){
			  renderJson(ajaxDoneSuccess("数据导入成功"));
		  }else{
			  renderJson(ajaxDoneError("数据导入失败"));
		  }
		 
	  }
}
