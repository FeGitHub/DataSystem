package com.DS.utils.common;
import java.io.File;
import java.util.ArrayList;
/***
 * @author jeff
 * 文件处理工具类
 */
public class FileUtil {
   /* public static void main(String[] args) {
        String path="F:/MysqlBackup";//注意斜杆方向
        getFilePaths(path);       
        path="F:/MysqlBackup/mysql常用命令.txt";
        deleteFile(path);
    }   */
    /***
     * 
     * @param path 文件路径
     * 取得一个文件夹下的所有文件的文件路径
     */
    public static ArrayList<String> getFilePaths(String path) {
        File file = new File(path);    
        ArrayList<String> FileNameList = new ArrayList<String>();//存放读取路径的所有文件名
        String tempFilePath="";
        if (!file.exists()){
        	System.out.println("文件路径不存在！"+path);
        	return null;
        }
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            if(files.length==0){    
            	return FileNameList;
            }
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 
                if (files[i].isDirectory()) {                	             
                	getFilePaths(files[i].getPath());
                } else {   
                	tempFilePath=files[i].getPath();
                	tempFilePath=tempFilePath.replaceAll("\\\\", "/");//文件路径转为java可读的路径
                	FileNameList.add(tempFilePath);              
                }
            }
        } else { 
        	tempFilePath=file.getPath();
        	tempFilePath=tempFilePath.replaceAll("\\\\", "/");
        	FileNameList.add(tempFilePath);            
        }
        return FileNameList;       
    }
    
    /***
     * 删除指定路径文件
     */
    public static boolean deleteFile(String fileName){
    	 File file = new File(fileName);
         if (!file.exists()) {       
             System.out.println("删除文件失败:" + fileName + "不存在！");
             return false;
         }
         return file.delete();  
        
    }  
    
    /***
     * 判断盘符是否存在
     * @return
     */
    public static boolean isDiskExist(String diskPath){
    	File f = new File(diskPath);
    	if(f.exists()){
    		return true;
    		}else{
    		return false;
    		}   	 
      }
}
