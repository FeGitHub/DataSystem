package com.DS.utils;

public class StringUtil {
	private static int indexMy=2;
	private static String addString="DS";
    public static String insertCharToString(int index,String addStr,String rawStr){
         String encryptStr=null;
    	 StringBuilder sb = new StringBuilder(rawStr);//构造一个StringBuilder对象
         sb.insert(index, addStr);
         encryptStr = sb.toString();
         System.out.println(encryptStr);
         return encryptStr;
    }
    public static String deleteCharToString(int index,String addStr,String EncryptStr){
	     String rawStr=null;
	     StringBuilder sb = new StringBuilder(EncryptStr);//构造一个StringBuilder对象
	   	 sb.delete(index, index+addStr.length());
	   	 rawStr= sb.toString();
	   	 System.out.println(rawStr);
	   	 return rawStr;
   }
    
    public static String insertCharToString(String rawString){
    	String encryptStr=null;
    	StringBuilder sb = new StringBuilder(rawString);//构造一个StringBuilder对象
        sb.insert(indexMy, addString);//在指定的位置1，插入指定的字符串
        encryptStr = sb.toString();
        System.out.println(encryptStr);
        return encryptStr;
   }
   
    public static String deleteCharToString(String EncryptStr){
    	 String rawStr=null;
    	 StringBuilder sb = new StringBuilder(EncryptStr);//构造一个StringBuilder对象
    	 sb.delete(indexMy, indexMy+addString.length());
    	 rawStr= sb.toString();
    	 System.out.println(rawStr);
    	 return rawStr;
    }
    
    public static void main(String[] args){
    	String test=insertCharToString("1234");
    	deleteCharToString(test);
    }
}
