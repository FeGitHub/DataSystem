package com.DS.controller;
import java.io.File;
import com.DS.fileService.FileService;
import com.DS.fileService.impl.FileServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
/****
 * 用于处理文件操作的控制器
 * @author jeff
 *
 */
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
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  File file = uploadFile.getFile();//获取文件对象 
		  File myFile=fileService.uploadFile(file);
		  if(myFile!=null){
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
		 /* String path = f.getPath();
		  System.out.println(path);*/
		  renderJson(ajaxDoneSuccess("文件上传成功"));
	  }
	  		  
	 
}
