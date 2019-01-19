package com.DS.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import com.DS.fileService.FileService;
import com.DS.fileService.impl.FileServiceImpl;
import com.DS.utils.excelutil.ExcelLogs;
import com.DS.utils.excelutil.ExcelUtil;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
public class FileController extends BaseController{
	@Inject(FileServiceImpl.class)
	private FileService fileService;
	 /****
	   * 文件下载
	   */
	  public void downfile() 
	   {		
		 //String path = getSession().getServletContext().getRealPath("/");
			File file = new File(PathKit.getRootClassPath()+"/resources/excel/test.xls"); 
			  if (file.exists()) { //如果文件存在 
				  renderFile(file); 
			  } else  { 
		  	   renderJson("文件不存在");
		  	 } 
	  } 
	  
	  public void uploadFile() 
	  {   
		  boolean exceptionFlag=false;
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  //String fileName = uploadFile.getOriginalFileName();//获取文件名 
		  File file = uploadFile.getFile();//获取文件对象 
		  Prop p =PropKit.use("config.properties");	
		  String filePath=p.get("uploadFile")+"/";  
		  String newfileName=UUID.randomUUID().toString() + file.getName().substring(file.getName().lastIndexOf("."));
		  File t = new File(filePath + newfileName);//设置本地上传文件对象（并重命名）	 
		  try { 
			   t.createNewFile(); 
			   } catch (IOException e) { 
				   exceptionFlag=true;
				   e.printStackTrace(); 
		   }  
		  	if(!exceptionFlag){
		  		fileService.fileChannelCopy(file, t);//将上传的文件的数据拷贝到本地新建的文件 
				file.delete(); 
				//====
				//importXls(filePath + newfileName);
				//===
				renderJson(ajaxDoneSuccess("文件上传成功"));
		  	}else{
		  		renderJson(ajaxDoneError("文件上传失败"));
		  	}
			   
	   } 	  
	  
	  public void uploadFileToProject(){		
		  UploadFile uf = getFile();
		  File f = uf.getFile();
		  String path = f.getPath();
		  System.out.println(path);
		  renderJson(ajaxDoneSuccess("文件上传成功"));
	  }
	  
	 //===========
	   //测试上传后立刻读取数据
	  public void importXls(String path) {
		  System.out.println(path);
		   File f=new File(path);
		    InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    
		    ExcelLogs logs =new ExcelLogs();
		    @SuppressWarnings("rawtypes")
			Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);	    
		    for(@SuppressWarnings("rawtypes") Map m : importExcel){
		      System.out.println(m);
		    }
		  }
	  //============
}
