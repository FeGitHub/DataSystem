package com.DS.fileService;
import java.io.File;

public interface FileService {
   /****
    * 将文件信息拷贝到另一个文件
    * @param file 目标文件
    * @param copyfile 拷贝文件
    */
   public void fileChannelCopy(File file, File copyfile);
   
   public  boolean readExcelData(File file);
}
