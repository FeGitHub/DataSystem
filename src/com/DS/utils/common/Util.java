package com.DS.utils.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/****
 * 
 * @author jeff 对象工具
 */
public class Util {

	static final Map<Type, Object> defaultVal;

	static {

		defaultVal = new HashMap();

	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	public static Map<String, Object> convertBeanToMap(Object bean) {
		@SuppressWarnings("rawtypes")
		Class type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(type);
		} catch (IntrospectionException e1) {// 分析类属性失败
			e1.printStackTrace();
		}
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = null;
				try {
					result = readMethod.invoke(bean, new Object[0]);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// IllegalAccessException 如果实例化 JavaBean 失败
					// InvocationTargetException 如果调用属性的 setter 方法失败
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
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || ("".equals(str.trim())));
	}

	/****
	 * bean转成map
	 * 
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> beanToMap(Object bean)
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String classProp = "class";
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		Map mapBean = new HashMap();
		for (PropertyDescriptor prop : props) {
			if ("class".equals(prop.getName())) {
				continue;
			}

			if (prop.getReadMethod() == null)
				continue;
			Object value = prop.getReadMethod().invoke(bean, new Object[0]);
			mapBean.put(prop.getName().toUpperCase(), value);
		}

		return mapBean;
	}

	/*****
	 * map转换成bean
	 * 
	 * @param map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Object mapToBean(Map<String, Object> map, Object bean) throws Exception {
		String classProp = "class";
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

		PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();

		for (PropertyDescriptor prop : props) {
			if ("class".equals(prop.getName())) {
				continue;
			}
			if (prop.getWriteMethod() == null)
				continue;
			Object value = map.get(prop.getName().toUpperCase());
			Class clazz = prop.getPropertyType();
			if (value == null) {
				if (clazz.isPrimitive())
					prop.getWriteMethod().invoke(bean, new Object[] { defaultVal.get(clazz) });
				else {
					prop.getWriteMethod().invoke(bean, new Object[] { null });
				}

			} else if (clazz.isAssignableFrom(value.getClass())) {
				prop.getWriteMethod().invoke(bean, new Object[] { value });
			} else if ((((Double.TYPE == clazz) || (Double.class == clazz)))
					&& (Number.class.isAssignableFrom(value.getClass()))) {
				prop.getWriteMethod().invoke(bean, new Object[] { Double.valueOf(((Number) value).doubleValue()) });
			} else if ((((Integer.TYPE == clazz) || (Integer.class == clazz)))
					&& (Number.class.isAssignableFrom(value.getClass()))) {
				prop.getWriteMethod().invoke(bean, new Object[] { Integer.valueOf(((Number) value).intValue()) });
			} else if ((((Long.TYPE == clazz) || (Long.class == clazz)))
					&& (Number.class.isAssignableFrom(value.getClass()))) {
				prop.getWriteMethod().invoke(bean, new Object[] { Long.valueOf(((Number) value).longValue()) });
			} else if ((((Byte.TYPE == clazz) || (Byte.class == clazz)))
					&& (Number.class.isAssignableFrom(value.getClass()))) {
				prop.getWriteMethod().invoke(bean, new Object[] { Byte.valueOf(((Number) value).byteValue()) });
			} else if ((((Short.TYPE == clazz) || (Short.class == clazz)))
					&& (Number.class.isAssignableFrom(value.getClass()))) {
				prop.getWriteMethod().invoke(bean, new Object[] { Short.valueOf(((Number) value).shortValue()) });
			} else if ((((Float.TYPE == clazz) || (Float.class == clazz)))
					&& (Number.class.isAssignableFrom(value.getClass()))) {
				prop.getWriteMethod().invoke(bean, new Object[] { Float.valueOf(((Number) value).floatValue()) });
			} else {
				prop.getWriteMethod().invoke(bean, new Object[] { value });
			}

		}
		return bean;
	}

}