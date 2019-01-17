package com.DS.utils.excelutil;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * 测试导入Excel 97/2003
 */
public class TestImportExcel {

  @Test
  public void importXls() throws FileNotFoundException {
    File f=new File("src/resources/excel/test.xls");
    InputStream inputStream= new FileInputStream(f);
    
    ExcelLogs logs =new ExcelLogs();
    @SuppressWarnings("rawtypes")
	Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    
    for(@SuppressWarnings("rawtypes") Map m : importExcel){
      System.out.println(m);
    }
  }

  @Test
  public void importXlsx() throws FileNotFoundException {
    File f=new File("src/resources/excel/test.xlsx");
	  //File f=new File("F:/hello.xls");
    InputStream inputStream= new FileInputStream(f);
    ExcelLogs logs =new ExcelLogs();
    @SuppressWarnings("rawtypes")
	Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    for(@SuppressWarnings("rawtypes") Map m : importExcel){
      System.out.println(m);
    }
  }

}
