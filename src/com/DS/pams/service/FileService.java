package com.DS.pams.service;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;

import com.DS.bean.CsvInfo;
import com.jfinal.plugin.activerecord.Record;

/****
 * 文件操作服务层
 * @author jeff
 *
 */
public interface FileService {
   /****
    * 将文件信息拷贝到另一个文件
    * @param file 目标文件
    * @param copyfile 拷贝文件
    */
   public void fileChannelCopy(File file, File copyfile);
 
   /***
    * 上传文件并拷贝，删除原上传文件，留下拷贝信息
    * @param file
    * @return
    */
   public File uploadFile(File file);
   
   /***
    * 
    * @param csv  读取的csv文件
    * @param delete  读取后是否删除 
    * @return
    */
   public List<Record> readCSV(File csv,boolean delete);
   
   
   public File uploadFile(File file,String newFileName);
   
   public CsvInfo getCsvInfo(File file) throws IOException;
}
