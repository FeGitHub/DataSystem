package com.DS.pams.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.DS.bean.CsvInfo;
import com.DS.pams.service.FileService;
import com.DS.utils.common.CSVUtil;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

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

	@Override
	public CsvInfo getCsvInfo(File file) throws IOException{
		CsvInfo info=new CsvInfo();
		List<String> heads=new ArrayList<String>();
		List<Record> resultList=new ArrayList<Record>();
        InputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(settings);
        //读取数据到列表
        List<String[]> allRows = parser.parseAll(reader);
       //处理读取到的数据
        String[] rowdata=null;
        for(int i=0;i<allRows.size();i++){
        	rowdata=allRows.get(i);
        	Record record=new Record();
        	if(i>=30){
        		info.setMsg("只显示部分数据");
        		break;
        	}
        	for(int j=0;j<rowdata.length;j++){
        		if(i==0){
        			heads.add(rowdata[j]);
        		}
        		record.set(j+"_column", rowdata[j]);
        	}
        	resultList.add(record);
       
        }
        info.setView(resultList);
        info.setRows(allRows.size());
        info.setHeads(heads);
        info.setCols(heads.size());
        return info;
}
	  
	}

