package com.DS.file.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.UUID;
import com.DS.file.service.FileService;
import com.DS.utils.common.CSVUtil;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Record;

public class FileServiceImpl implements FileService {
		/****
		 * 拷贝文件信息
		 */
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
	 * 上传文件并拷贝，删除原上传文件，留下拷贝信息
	 */
	@Override
	public File uploadFile(File file) {
		  Prop p =PropKit.use("config.properties");	
		  String filePath=p.get("uploadFile")+"/";  
		  String newfileName=UUID.randomUUID().toString() + file.getName().substring(file.getName().lastIndexOf("."));
		  File copyfile = new File(filePath + newfileName);//设置本地上传文件对象（并重命名）	 
		  try { 
			  copyfile.createNewFile(); 
			  fileChannelCopy(file, copyfile);			 
			   } catch (IOException e) { 
				   copyfile=null;
				   e.printStackTrace(); 
		    } finally{
		    	  file.delete();
		    }	 
		  return copyfile;
	}
 
	
	/****
	 * 读取csv文件信息
	 */
	@Override
	public List<Record> readCSV(File csv,boolean delete) {
		List<Record> data=null;
		try {
			 data=CSVUtil.ReadCSV(csv);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(delete){
		  csv.delete();//删除读取文件
		}		
		return data;
	  }

	@Override
	public File uploadFile(File file, String newFileName) {
		  Prop p =PropKit.use("config.properties");	
		  String filePath=p.get("uploadFile")+"/";  
		  File copyfile = new File(filePath + newFileName+file.getName().substring(file.getName().lastIndexOf(".")));//设置本地上传文件对象（并重命名）	 
		  try { 
			  copyfile.createNewFile(); 
			  fileChannelCopy(file, copyfile);			 
			   } catch (IOException e) { 
				   copyfile=null;
				   e.printStackTrace(); 
		    } finally{
		    	  file.delete();
		    }	 
		  return copyfile;
	
	}
	  
	}

