package com.DS.exception;

public class BusinessException extends BaseException {
   private static final long serialVersionUID = 7537976633438034302L;

   public BusinessException(String className, String functionName) {
      super(className, functionName);
   }

   public BusinessException(String className, String functionName,String describe){
	   super(className, functionName,describe);
   }

}