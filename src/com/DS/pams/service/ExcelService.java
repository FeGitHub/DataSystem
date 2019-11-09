package com.DS.pams.service;
import java.io.File;
import java.util.ArrayList;
import com.DS.bean.FinanceExcelBean;

/****
 * 
 * @author jeff
 *  excel操作的服务层接口
 */
public interface ExcelService {
	 /***
	  * 读取财务excel的文件信息
	  * @param file 财务excel
	  * @return
	  */
     public ArrayList<FinanceExcelBean> getInfoByFinanceExcel(File file);
}
