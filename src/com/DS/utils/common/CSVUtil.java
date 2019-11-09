 package com.DS.utils.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.DS.common.model.User;
import com.DS.pams.service.impl.TaskServiceImpl;
import com.jfinal.plugin.activerecord.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

/****
 * 
 * @author jeff
 * csv文件操作工具
 */
public class CSVUtil {
	
	/****
	 * 创建csv文件并写入数据
	 * @param heads
	 * @param rows
	 * @param outPutPath
	 * @param filename
	 */
	public static boolean createCSVFile(String[] heads, List<Object[]> rows, String outPutPath, String filename)
    { 
		
		boolean result=true;
        File csvFile = new File(outPutPath + filename + ".csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists())
        {
            parent.mkdirs();
        }
        try
        {
            csvFile.createNewFile();
            Writer fileWriter = new FileWriter(csvFile);
            CsvWriterSettings settings = new CsvWriterSettings();
            CsvFormat format = settings.getFormat();
            format.setDelimiter(',');
            CsvWriter writer = new CsvWriter(fileWriter, settings);
            writer.writeHeaders(heads);
            writer.writeRowsAndClose(rows);
        } catch (Exception e)
        {
            e.printStackTrace(); 
            result=false;
        }
        return result;
    }
	
	/****
	 * 根据路径读取CSV数据
	 * @param filePath
	 * @throws IOException
	 */
	public static void ReadCSV(String filePath) throws IOException {
        File file = new File(filePath);
        InputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(settings);
        //读取数据到列表
        List<String[]> allRows = parser.parseAll(reader);
       //处理读取到的数据
        String[] temp=null;
        for(int i=0;i<allRows.size();i++){
        	temp=allRows.get(i);
        	for(int j=0;j<temp.length;j++){
        		System.out.println(temp[j]+" ");
        	}
        }
}
	
	/****
	 * csv文件信息读取
	 * @param file
	 * @throws IOException
	 */
	public static List<Record> ReadCSV(File file) throws IOException {
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
        	for(int j=0;j<rowdata.length;j++){
        		record.set(j+"_column", rowdata[j]);
        		//System.out.print(rowdata[j]+" ");
        	}
        	resultList.add(record);
        	//System.out.println("");
        }
        return resultList;
}
	
	
	
	@Test
	public void testReadCSV(){
		try {
			ReadCSV("C://testCSV.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public static void testCreateCSVFile(){
		String[] heads={"plantime","leve","tasknum","taskNumInWeek"};
		TaskServiceImpl taskServiceImpl=new TaskServiceImpl();
		User user=new User();
		user.setId(2);
		List<Record> list=taskServiceImpl.getAnalyseDataByUser(user);
		String outPutPath="E://";
		String filename="ToBeCSV";
		//转换
		List<Object[]> rows=new ArrayList<Object[]>();
		for(int i=0;i<list.size();i++){
			String str[] = new String[4]; 
			Record record=list.get(i);
			str[0]=record.getStr("plantime");
			str[1]=record.getStr("leve");
			str[2]=record.getStr("tasknum");
			str[3]=record.getStr("taskNumInWeek");
			rows.add(str);
		}
		createCSVFile(heads,rows,outPutPath,filename);		
	}
	
	public static void main(String[] args) {
		testCreateCSVFile();
	}
}
