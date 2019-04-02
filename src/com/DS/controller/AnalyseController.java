package com.DS.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.DS.bean.FinanceExcelBean;
import com.DS.excel.service.ExcelService;
import com.DS.excel.service.impl.ExcelServiceImpl;
import com.DS.file.service.FileService;
import com.DS.file.service.impl.FileServiceImpl;
import com.DS.utils.common.PythonByRuntime;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.upload.UploadFile;

/*****
 * 
 * @author jeff
 * 案例页面的控制器
 */
public class AnalyseController extends BaseController {
	@Inject(FileServiceImpl.class)
	private FileService fileService;
	
	
	@Inject(ExcelServiceImpl.class)
	private ExcelService excelService;
	/***
	 *  跳转到数据分析页面
	 */
	 public void goAnalyse(){
   	  render("analyse.jsp");
     }
	 
	 /*****
	  * 跳转到csv信息读取展示页面
	  */
	 public void goCsvData(){
	   	  render("csvdata.jsp");
	    }
		 
	 
	 public void getDataFromExcel(){
		 //第一步文件上传
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  File file = uploadFile.getFile();
		  //第二步将文件信息拷贝
		  File myFile=fileService.uploadFile(file);
		  ArrayList<FinanceExcelBean> DBList=excelService.getInfoByFinanceExcel(myFile);
		  for(FinanceExcelBean bean:DBList){
			  System.out.println(bean.getMonenyValue());
		  }
	 }
	 
	 public void regression(){
		 List<String> result=PythonByRuntime.runPython3("house_data5.py");
		 renderJson(result);
	 }
}
