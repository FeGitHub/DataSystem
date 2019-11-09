 package com.DS.pams.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import com.DS.bean.FinanceExcelBean;
import com.DS.pams.service.ExcelService;
import com.DS.utils.excelutil.ExcelLogs;
import com.DS.utils.excelutil.ExcelUtil;
public class ExcelServiceImpl implements ExcelService {
    /*****
     * 从财务excel中读取数据
     * @param file 本地的财务excel文件资源
     * @return 财务信息
     */
	@Override
	public ArrayList<FinanceExcelBean> getInfoByFinanceExcel(File file) {
		ArrayList<FinanceExcelBean> DBList=new ArrayList<FinanceExcelBean>();
		 InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	    
		    ExcelLogs logs =new ExcelLogs();
			Collection<FinanceExcelBean> importExcel = ExcelUtil.importExcel(FinanceExcelBean.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);	    
			  for(FinanceExcelBean m : importExcel){
				  DBList.add(m);
			    }				
		     return DBList ;
	}

}
