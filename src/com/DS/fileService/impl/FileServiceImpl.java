package com.DS.fileService.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.DS.fileService.FileService;
import com.DS.utils.excelutil.ExcelLogs;
import com.DS.utils.excelutil.ExcelUtil;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;

public class FileServiceImpl implements FileService {

	@Override
		public void fileChannelCopy(File file, File copyfile) {
			FileInputStream fi = null;//文件输入流 
			FileOutputStream fo = null;//文件输出流 
			FileChannel in = null;
			 FileChannel out = null; 
			 try { 
			 fi = new FileInputStream(file);
			 fo = new FileOutputStream(copyfile); 
			 in = fi.getChannel();//获取读入的文件通道
			  out = fo.getChannel();//获取写出的文件通道 
			  in.transferTo(0, in.size(), out);// 连接两个通道，从文件输入流读取数据到文件输出流 
			} catch (IOException e) {
				e.printStackTrace(); 
			} finally {
					try { 
						fi.close(); 
						in.close(); 
						fo.close();
						out.close() ;
					} catch (IOException e) {
						e.printStackTrace(); 
					} 
			   }
			 }
		
	 /****
	  * @param file 上传的excel文件资源
	  * 步骤如下：
	  * 1.上传excel文件资源到本地
	  * 2.将文件信息拷贝到临时excel
	  * 3.读取excel信息
	  * 4.删除不必要的excel文件
	  */
	   @Override
		public  boolean readExcelData(File file) {
		  boolean exceptionFlag=false;
		  Prop p =PropKit.use("config.properties");	
		  String filePath=p.get("uploadFile")+"/";  
		  String newfileName="tempExcel" + file.getName().substring(file.getName().lastIndexOf("."));
		  File copyfile = new File(filePath + newfileName);//设置本地上传文件对象（并重命名）	 
		  try { 
			  copyfile.createNewFile(); 
			   } catch (IOException e) { 
				   exceptionFlag=true;
				   e.printStackTrace(); 
		   }  
		  	if(!exceptionFlag){
		  		fileChannelCopy(file, copyfile);//将上传的文件的数据拷贝到本地新建的文件 
				file.delete(); 			
				importXls(filePath + newfileName);		
				copyfile.delete();
			    return true;
		  	}else{
		  		return false;
		  	}
			
		} 
		
	/****
	 * 读取excel文件信息
	 * @param path  excel文件位置
	 */
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
	}

