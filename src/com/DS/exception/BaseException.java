package com.DS.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public abstract class BaseException extends RuntimeException {

   final Logger logger = LoggerFactory.getLogger(BaseException.class);
   private static final long serialVersionUID = -7126114767624436789L;
   private String className = "";
   private String functionName = "";
 

   public BaseException(String className, String functionName) {
      super("运行时异常");
      this.className = className;
      this.functionName = functionName;
      this.logger.error(this.className + "-->" + this.functionName);
   }

   public BaseException(String className, String functionName,String describe) {
	      super("运行时异常");
	      this.className = className;
	      this.functionName = functionName;
	      this.logger.error(this.className + "-->" + this.functionName+"():"+describe);
	   }
}