package com.github.chuanzh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
 
public class FuncStatic {
	private static Logger logger = Logger.getLogger(FuncStatic.class.getName());
 
	public static JsonConfig JSON_NONULL_CONFIG = new JsonConfig();
	static {
		JSON_NONULL_CONFIG.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				return value == null;
			}
		});
	}
	
	public static boolean isWindowsOS(){
	    boolean isWindowsOS = false;
	    String osName = System.getProperty("os.name");
	    if(osName.toLowerCase().indexOf("windows")>-1){
	      isWindowsOS = true;
	    }
	    return isWindowsOS;
	}

	public static String sizeToText(Long size) {
		if (size == null)
			return "";
		long sz = size;
		if (sz < 1024)
			return size.toString();
		if (sz < 1024 * 1024)
			return String.valueOf((size / 1024)) + "KB";
		if (sz < 1024 * 1024 * 1024)
			return String.valueOf(size / 1024 / 1024) + "MB";
		if (sz < 1024 * 1024 * 1024 * 1024)
			return String.valueOf(size / 1024 / 1024) + "GB";
		return "";
	}

	public static String numToTextNum(int i) {
		char[] nums = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
		String[] dw = { "", "十", "百", "千", "万" };
		String t = String.valueOf(i);
		StringBuilder sb = new StringBuilder();
		char lastInsert = 0;
		for (int x = t.length() - 1, ws = 0; x >= 0; x--, ws++) {
			if (ws == 0 && t.charAt(x) - 48 == 0)
				continue;
			if (t.charAt(x) - 48 == 0) {
				// 过滤重复的零
				if (lastInsert != '零') {
					sb.insert(0, '零');
					lastInsert = '零';
				}
				continue;
			}
			if (ws == 1 && t.charAt(x) - 48 == 1 && t.length() == 2) {
				// “一十” 简写为“十”
				sb.insert(0, dw[ws]);
				lastInsert = dw[ws].charAt(0);
				continue;
			}
			sb.insert(0, dw[ws]);
			sb.insert(0, nums[t.charAt(x) - 48]);
			lastInsert = nums[t.charAt(x) - 48];
		}

		return sb.toString();
	}
	/**
	 * 匹配字符串中的占位符 如:{0},{1}
	 * @param content 字符串
	 * @param arguments 占位符
	 * @return 结果
	 */
	public static String format(String content, Object... arguments) {
		if (content == null)
			return "";
		String to = null;
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] == null)
				to = "";
			else
				to = arguments[i].toString();

			content = StringUtils.replace(content, "{" + i + "}", to);
		}

		return content;
	}

	public static int sum(Object... arguments) {
		int sum = 0;
		for (int i = 0; i < arguments.length; i++) {
			if (!FuncStatic.checkIsEmpty(arguments[i])) {
				try {
					sum += Integer.parseInt(arguments[i].toString());
				} catch (Exception e) {

				}
			}
		}
		return sum;
	}

	public static int sum(List arguments) {
		int sum = 0;
		for (int i = 0; i < arguments.size(); i++) {
			if (!FuncStatic.checkIsEmpty(arguments.get(i))) {
				try {
					sum += Integer.parseInt(arguments.get(i).toString());
				} catch (Exception e) {
				}
			}
		}
		return sum;
	}

	public static Long sumLong(Object... arguments) {
		long sum = 0;
		for (int i = 0; i < arguments.length; i++) {
			if (!FuncStatic.checkIsEmpty(arguments[i])) {
				try {
					sum += Long.parseLong(arguments[i].toString());
				} catch (Exception e) {

				}
			}
		}
		return sum;
	}

	public static Long sumLong(List arguments) {
		long sum = 0;
		for (int i = 0; i < arguments.size(); i++) {
			if (!FuncStatic.checkIsEmpty(arguments.get(i))) {
				try {
					sum += Long.parseLong(arguments.get(i).toString());
				} catch (Exception e) {

				}
			}
		}
		return sum;
	}

	public static Double sumDouble(Object... arguments) {
		double sum = 0;
		for (int i = 0; i < arguments.length; i++) {
			if (!FuncStatic.checkIsEmpty(arguments[i])) {
				try {
					sum += Double.parseDouble(arguments[i].toString());
				} catch (Exception e) {

				}
			}
		}
		return sum;
	}

	public static Long sumDouble(List arguments) {
		long sum = 0;
		for (int i = 0; i < arguments.size(); i++) {
			if (!FuncStatic.checkIsEmpty(arguments.get(i))) {
				try {
					sum += Double.parseDouble(arguments.get(i).toString());
				} catch (Exception e) {

				}
			}
		}
		return sum;
	}

	/**
	 * 检查String 是NULL, 或者“”
	 * 
	 * @param var 字符串
	 * @return 是否为空
	 */
	public static boolean checkStringIsEmpty(String var) {
		if (var == null)
			return true;
		if (var.equals(""))
			return true;
		return false;
	}

	public static boolean checkIsEmpty(Object var) {
		if (var == null)
			return true;
		if (var.toString().equals(""))
			return true;
		return false;
	}

	public static String checkZero(Object var) {
		if (var == null)
			return "0";
		if (var.toString().equals(""))
			return "0";
		return var.toString();
	}

	/**
	 * 检查List 是NULL, 或者“”
	 * 
	 * @param var list集合
	 * @return 是否为空
	 */
	public static boolean checkListIsEmpty(List var) {
		if (var == null)
			return true;
		return var.isEmpty();
	}

	/**
	 * 将HTML中input的值进行转义 
	 * @param str 输入字符串
	 * @return 结果
	 */
	public static String textToInputHtml(String str) {
		if (str == null)
			return "";
		int strlen;
		StringBuilder restring = new StringBuilder();
		String destr = "";
		strlen = str.length();
		for (int i = 0; i < strlen; i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '<':
				destr = "&lt;";
				break;
			case '>':
				destr = "&gt;";
				break;
			case '\"':
				destr = "&quot;";
				break;
			default:
				destr = "" + ch;
				break;
			}
			restring.append(destr);
		}
		return restring.toString();
	}

	/**
	 * 将text进行HTML转义
	 * @param str 输入字符串
	 * @return 结果
	 */
	public static String textToHtml(String str) {
		if (str == null)
			return "";
		int strlen;
		StringBuilder restring = new StringBuilder();
		String destr = "";
		strlen = str.length();
		for (int i = 0; i < strlen; i++) {
			char ch = str.charAt(i);
			switch (ch) {

			case '<':
				destr = "&lt;";
				break;
			case '>':
				destr = "&gt;";
				break;
			case '\"':
				destr = "&quot;";
				break;
			case '&':
				destr = "&amp;";
				break;
			case 10:
				destr = "<br/>";
				break;
			case 32:
				destr = "&nbsp;";
				break;
			case '$':
				destr = "&#36;";
				break;
			case '\\':
				destr = "&#92;";
				break;
			default:
				destr = "" + ch;
				break;
			}
			restring.append(destr);
		}
		return restring.toString();
	}

	/**
	 * 去掉一个字符串两端的某个字符串
	 * @param str 原字符串
	 * @param c 需要去掉的字符串
	 * @return 结果
	 */
	public static String trim(String str, String c) {
		while (str.startsWith(c))
			str = str.substring(c.length(), str.length());
		while (str.endsWith(c))
			str = str.substring(0, str.length() - c.length());
		return str;
	}

	
	/**
	 * 去掉一个字符串两端的两个字符串
	 * @param str 原字符串
	 * @param start 开头需要去掉的字符串
	 * @param end 结尾需要去掉的字符串
	 * @return 结果
	 */
	public static String trim(String str, String start, String end) {
		if (str == null)
			return null;
		if (str.startsWith(start))
			str = str.substring(start.length(), str.length());
		if (str.endsWith(end))
			str = str.substring(0, str.length() - end.length());
		return str;
	}

	public static String htmlToText(String str) {
		if (str == null)
			return "";
		str = str.replace("&lt;", "<");
		str = str.replace("&gt;", ">");
		str = str.replace("&quot;", "\"");
		str = str.replace("&amp;", "&");
		str = str.replace("&nbsp;", "  ");
		return str;
	}

	 
	/**
	 * 截取字符串
	 * @param origin 原始字符串
	 * @param len 截取长度(一个汉字长度按2算的)
	 * @return 结果
	 */
	public static String substring(String origin, int len) {
		if (origin == null || "".equals(origin))
			return null;
		StringBuilder sb = new StringBuilder(len);
		int count = 0;
		for (int index = 0; index < origin.length(); index++) {
			char c = origin.charAt(index);
			if (c < 250)
				count = count + 1;
			else
				count = count + 2;

			if (count > len)
				break;
			else
				sb.append(c);
		}
		return sb.toString();
	}

 
	public static HashMap<String, Object> urlParamsParse(String str) {
		HashMap<String, Object> map = new HashMap();
		if (str == null)
			return map;
		String[] ss = str.split("&");
		for (String s : ss) {
			if ("".equals(s))
				continue;
			int c = s.indexOf("=");
			if (c == -1)
				continue;
			map.put(s.substring(0, c), s.substring(c + 1, s.length()));
		}
		return map;
	}

	/**
	 * 计算字符串长度。每个中文占两个长度单位
	 * 
	 * @param o 对象
	 * @return 长度
	 */
	public static int computeStringLength(Object o) {
		int i = 0;
		if (o == null || "".equals(o.toString()))
			return i;
		String s = o.toString();
		for (int x = 0; x < s.toString().length(); x++) {
			if (s.charAt(x) < 250) {
				i++;
			} else {
				i++;
				i++;
			}
		}
		return i;
	}

	/**
	 * 返回指定长度的字符串。字符串长度不够时用空格补齐。每个中文占两个长度单位
	 * 
	 * @param s 字符串
	 * @param length 长度
	 * @return 结果
	 */
	public static String formatStringLength(Object s, int length) {
		int count = FuncStatic.computeStringLength(s);
		if (s == null)
			s = "";
		if (count < length) {
			s = s + StringUtils.repeat(" ", length - count);
		}
		return s.toString();
	}

	/**
	 * 返回指定长度的字符串。字符串长度不够时用指定字符串补齐。每个中文占两个长度单位
	 * @param s 字符串
	 * @param length 长度
	 * @param s1 填补的字符串
	 * @param left 左右标识
	 * @return 结果
	 */
	public static String formatStringLength(Object s, int length, String s1,
			boolean left) {
		int count = FuncStatic.computeStringLength(s);
		if (s == null)
			s = "";
		if (count < length) {
			if (left)
				s = StringUtils.repeat(s1, length - count) + s;
			else
				s = s + StringUtils.repeat(s1, length - count);
		}
		return s.toString();
	}

	/**
	 * 将大写字符串转换为驼峰形式，下划线后面和第一个字母是大写(USER_NAME 转换 UserName)
	 * 
	 * @param s 要转的字符串
	 * @return 结果
	 */
	public static String convertUpperCaseToHump(String s) {
		if (FuncStatic.checkIsEmpty(s))
			return s;
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(s.charAt(0)));
		boolean up = false;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '_') {
				up = true;
			} else {
				if (up) {
					sb.append(Character.toUpperCase(s.charAt(i)));
					up = false;
				} else
					sb.append(Character.toLowerCase(s.charAt(i)));
			}
		}
		return sb.toString();
	}

	/**
	 * 将驼峰形式转换为大写字符串，大写字母前面是下划线后面,第一个字母除外 UserName 转换 USER_NAME
	 * 
	 * @param s 要转的字符串
	 * @return 结果
	 */
	public static String convertHumpToUpperCase(String s) {
		if (FuncStatic.checkIsEmpty(s))
			return s;
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(s.charAt(0)));

		for (int i = 1; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				sb.append("_");
				sb.append(s.charAt(i));
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}  
	
	/**
	 * 将字符串的首字母进行大小写转换
	 * @param str 转换字符串
	 * @param upper 标识
	 * @return 结果
	 */
	public static String convertWordHead(String str, boolean upper) {
		if (FuncStatic.checkIsEmpty(str))
			return str;
		if (upper)
			return StringUtils.capitalize(str);
		else
			return StringUtils.uncapitalize(str);
	}

	/**
	 * 正则表达式搜索字符串
	 * 
	 * @param str 搜索字符串
	 * @param reg 正则
	 * @return 结果
	 */
	public static String searchString(String str, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}

	/**
	 * 正则表达式搜索字符串
	 * @param str 搜索字符串
	 * @param reg 正则
	 * @param count 搜索个数
	 * @return 结果
	 */
	public static List<String> searchString(String str, String reg, int count) {
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		if (m.find()) {
			for (int i = 0; i < count; i++) {
				list.add(m.group(i + 1));
			}
		}
		return list;
	}

	public static boolean checkIsNumber(char c) {
		switch (c) {
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '0':
			return true;
		default:
			return false;
		}
	}

	public static String getRootFilePath() {
		String path = FuncStatic.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		int x=0;
		if(path.indexOf(".jar") > -1){
			x = 1;
		}else if(path.indexOf("tomcat") > -1){
			x = 6;
		}
		else{
			x = 2;
		}
		String[] patharray = path.split("/");
		String str = "";
		for(int i=0; i<patharray.length-x; i++){
			str += patharray[i]+"/";
		}
		return str;
	}

	/**
	 * 求百分比
	 * @param p1 分子
	 * @param p2 分母
	 * @param digits 小数点后面位数
	 * @return 结果
	 */
	public static String percent(double p1, double p2, int digits) {
		
		String str = "";
		try{
			double p3 = p1 / p2;

			NumberFormat nf = NumberFormat.getPercentInstance();

			nf.setMinimumFractionDigits(digits);

			str = nf.format(p3);
		}
		catch (Exception e) {
			str = "";
		}
		return str;

	}
	
	/**
	 * 去掉所有HTML标签元素
	 * @param input 字符串
	 * @return 结果
	 */
	public static String splitAndFilterTag(String input) {  
        if (input == null || input.trim().equals("")) {  
            return "";  
        }  
        // 去掉所有html元素,  
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
                "<[^>]*>", "");  
        return str.replaceAll("[(/>)<]", "");   
    }  
	
	public static String errorTrace(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);  
		return sw.toString();
 	}
	
	public static void killWindowsTask(String taskName){
		String pid = null;  
        String cmd = "tasklist /nh /FI \"IMAGENAME eq "+taskName+"\"";  
        try {  
        	Process process = Runtime.getRuntime().exec(cmd);  
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
            String line = null;  
            while((line=br.readLine()) != null){  
                if(line.indexOf(taskName) != -1){  
                	line = line.replace(taskName, "").trim();
                	pid = line.substring(0,line.indexOf(" ")).trim();
                }  
            }  
            if(pid == null){
            	logger.info("not find process id '"+taskName+"'");
            }else{
            	try {  
             	    Runtime.getRuntime().exec("taskkill /F /PID "+pid);  
             	   logger.info("process "+taskName + " is killed");
            	} catch (Exception e1) {  
            	    logger.error(FuncStatic.errorTrace(e1)); 
            	} 
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	 
	/**
	 * 运行shell 
	 * @param taskName 需要执行的shell
	 * @return list集合
	 * @throws Exception 异常
	 */
    public static List<String> runLinuxTask(String taskName) throws Exception {  
        List<String> strList = new ArrayList<String>();  
        Process process;  
        process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",taskName},null,null);  
        InputStreamReader ir = new InputStreamReader(process  
                .getInputStream());  
        LineNumberReader input = new LineNumberReader(ir);  
        String line;  
        while ((line = input.readLine()) != null){  
            strList.add(line);  
        }  
        return strList;  
    }
    /**
	 *  MD5加密函数
	 * @param sourceString 字符串
	 * @return 加密值
	 */
	public static String md5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}
	public static String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}
	 
	/**
	 * 获取某个类及其父类的Field
	 * @param clazz 类
	 * @return 所有字段
	 */
	public static ArrayList<Field> findClassAllField (Class clazz){
		ArrayList<Field> list = new ArrayList<Field>();
		try {
			List<Class>  clazzList = new ArrayList();
 
			while(!"java.lang.Object".equals(clazz.getName())){
				clazzList.add(clazz);
				clazz = clazz.getSuperclass();
			}
			Collections.reverse(clazzList);
			for(int i=0;i<clazzList.size();i++){
				Field[] fs =  clazzList.get(i).getDeclaredFields();
				for(Field f : fs){
					list.add(f);
				}				
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
		return list;
	}
	/**
	 * 包装json工具类
	 * @param args key,value形式
	 * @return JSONObject
	 */
	public static JSONObject createJson(String... args) {
		JSONObject jo = new JSONObject();
		for (int i = 0; i < args.length; i = i + 2) {
			jo.put(args[i], args[i + 1]);
		}
		return jo;
	}
	
	public static String toStr (Object o) {
		if (o != null) {
			return o.toString();
		}
		return "";
	}
}
