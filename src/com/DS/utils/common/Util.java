package com.DS.utils.common;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/****
 * 
 * @author jeff
 *  对象工具
 */
public class Util {    
    /** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     */ 
    public static Map<String,Object> convertBeanToMap(Object bean) { 
        @SuppressWarnings("rawtypes")
		Class type = bean.getClass(); 
        Map<String,Object> returnMap = new HashMap<String,Object>(); 
        BeanInfo beanInfo=null;
		try {
			beanInfo = Introspector.getBeanInfo(type);
		 } catch (IntrospectionException e1) {//分析类属性失败	
			e1.printStackTrace();
		} 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result=null;
				try {
					result = readMethod.invoke(bean, new Object[0]);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {	
					//IllegalAccessException 如果实例化 JavaBean 失败 		
					//InvocationTargetException 如果调用属性的 setter 方法失败 
					e.printStackTrace();
				} 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                } else { 
                    returnMap.put(propertyName, ""); 
                } 
            } 
        } 
        return returnMap; 
    }      
    
    /***
     * 判断字符串是否为空
     * @param str
     * @return
     */
     public static boolean isEmpty(String str)
   	 {
   		 return ((str == null) || ("".equals(str.trim())));
   		 }
}