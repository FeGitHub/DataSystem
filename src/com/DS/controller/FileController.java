package com.DS.controller;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import com.DS.fileService.FileService;
import com.DS.fileService.impl.FileServiceImpl;
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
	  
	  /****
	   * 上传文件资源到服务器（服务器任意位置）
	   */
	  public void uploadFile() 
	  {   
		  boolean exceptionFlag=false;
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  //String fileName = uploadFile.getOriginalFileName();//获取文件名 
		  File file = uploadFile.getFile();//获取文件对象 
		  Prop p =PropKit.use("config.properties");	
		  String filePath=p.get("uploadFile")+"/";  
		  String newfileName=UUID.randomUUID().toString() + file.getName().substring(file.getName().lastIndexOf("."));
		  File copyfile = new File(filePath + newfileName);//设置本地上传文件对象（并重命名）	 
		  try { 
			  copyfile.createNewFile(); 
			   } catch (IOException e) { 
				   exceptionFlag=true;
				   e.printStackTrace(); 
		   }  
		  	if(!exceptionFlag){
		  		fileService.fileChannelCopy(file, copyfile);//将上传的文件的数据拷贝到本地新建的文件 
				file.delete(); 
				renderJson(ajaxDoneSuccess("文件上传成功"));
		  	}else{
		  		renderJson(ajaxDoneError("文件上传失败"));
		  	}
			   
	   } 	  
	  
	  /***
	   * 上传文件资源到服务器（项目中）
	   */
	  public void uploadFileToProject(){		
		  UploadFile uf = getFile();
		  File f = uf.getFile();
		  String path = f.getPath();
		  System.out.println(path);
		  renderJson(ajaxDoneSuccess("文件上传成功"));
	  }
	  
	  /****
	   * 读取excel文件信息
	   */
	  public void getInfoFromExcel(){
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  File file = uploadFile.getFile();
		   if(fileService.readExcelData(file)){
			   renderJson(ajaxDoneSuccess("excel数据读取成功"));
		   }else{
			   renderJson(ajaxDoneError("excel数据读取失败"));
		   }
	  }
}
