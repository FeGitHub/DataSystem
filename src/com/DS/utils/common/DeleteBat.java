package com.DS.utils.common;
import java.io.File;
/***
 * 删除指定文件
 *
 */
public class DeleteBat {
	public static int sum=0;
	
	
	
	public static void main(String[] args) {
		String root="C:\\Users\\24980\\Desktop";
		File folder = new File(root);
		delete(folder);
		System.out.println("删除数据条数:"+sum);
	}


	public static int delete(File folder){		
		File[] files = folder.listFiles();
		for(File file:files){
			 if(file.isDirectory()){
				 delete(file);
			 }						
		if(shouldDelete(file.getName())){
		     file.delete();
		     sum++;
		   }	
		}
		return sum;
	}
	
	

public static boolean shouldDelete(String filename){
	String[] list={
			"a.txt",
			"b.txt",
			"pkg_sequence_b.sql"
			};
	for(int i=0;i<list.length;i++){
		if(list[i].equals(filename)){
			System.out.println("删除:"+filename);
			return true;
		}
		
	}
	return false;
}
    
}
