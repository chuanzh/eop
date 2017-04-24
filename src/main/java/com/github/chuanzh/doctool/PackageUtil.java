package com.github.chuanzh.doctool;


import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.chuanzh.eop.annotation.DescNote;

public class PackageUtil {
	
	public static List<Map<String,String>> handle(String packageName){
		List<String> classNames = getClassName(packageName);
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		for (String className : classNames) {
			resultList.add(getMethodInfobyFunc(className));
		}	
		return resultList;
	}
	
	private static List<String> getClassName(String packageName) {
		List<String> fileNames = null;
		String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "/");
		fileNames = getClassName(filePath, null);
		return fileNames;
	}

	private static List<String> getClassName(String filePath, List<String> className) {
		filePath = filePath.replace("\\", "/");
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				myClassName.addAll(getClassName(childFile.getPath(), myClassName));
			} else {
				String childFilePath = childFile.getPath();
				if (!childFilePath.startsWith("/")) {
					childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
				} else {
					childFilePath = childFilePath.substring(childFilePath.indexOf("/classes") + 9, childFilePath.lastIndexOf("."));
				}
				childFilePath = childFilePath.replace("\\", ".").replace("/", ".");
				String lastString = childFilePath.substring(childFilePath.length()-1, childFilePath.length());
				if(childFilePath.indexOf(".svn")<0&&!".".equals(lastString)){
					myClassName.add(childFilePath);
				}
			}
		}

		return myClassName;
	}
	
	/**
	 * 获取中func方法参数名
	 * @param pkgName 包名
	 * @return Map集合
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String,String> getMethodInfobyFunc(String pkgName) {
		try {
			Class clazz = Class.forName(pkgName);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if("func".equals(methodName)){
					String parameterName = "";
					Class<?>[] parameterTypes = method.getParameterTypes();
					for (Class<?> clas : parameterTypes) {
						parameterName = clas.getName();
					}
					String classAnnotation = "";
					if(clazz.getAnnotation(DescNote.class)!=null){
						classAnnotation = ((DescNote) clazz.getAnnotation(DescNote.class)).value();
					}					
					Map<String,String> map = new HashMap<String,String>();
					map.put("request", parameterName);
					map.put("serviceEffect", classAnnotation);
					map.put("interfaceName", clazz.getName());
					String response = map.get("interfaceName").toString()+"Response";
					map.put("response", response.replace("service", "bean"));
					return map;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
}