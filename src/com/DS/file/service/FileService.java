package com.DS.file.service;
import java.io.File;

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
}
