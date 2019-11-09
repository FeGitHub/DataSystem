package com.DS.pams.web.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.CsvInfo;
import com.DS.bean.FinanceExcelBean;
import com.DS.common.model.User;
import com.DS.pams.service.AnalyseService;
import com.DS.pams.service.ExcelService;
import com.DS.pams.service.FileService;
import com.DS.pams.service.impl.AnalyseServiceImpl;
import com.DS.pams.service.impl.ExcelServiceImpl;
import com.DS.pams.service.impl.FileServiceImpl;
import com.DS.pams.web.base.BaseController;
import com.DS.utils.common.PythonByRuntime;
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
	
	@Inject(AnalyseServiceImpl.class)
	private AnalyseService analyseService;
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
	 
	 /***
	  * 通用版逻辑回归算法模型分析请求
	  */
	 public void customAnalyse(){
		 User nowUser = (User)getSession().getAttribute("user");
		 String customfile=nowUser.getId()+nowUser.getAccount();
		 CsvInfo csvinfo=getSessionAttr(customfile);
		 if(csvinfo!=null){
			 List<String> headlist=csvinfo.getHeads();
			 String heads="";
			 for(int i=0;i<headlist.size();i++){
				 if(i==0){
					 heads=headlist.get(i);
				 }else{
					 heads+=","+headlist.get(i);
				 }
			 }
			 List<String> rows=analyseService.customAnalyse(heads, customfile,nowUser);
			 Map<String,Object> result=new HashMap<String,Object>();
			 result.put("rows", rows);
			 renderJson(ajaxDoneSuccess(result));
		 }else{
			 renderJson(ajaxDoneError("数据已失效"));
		 }
	 }
	 
}
