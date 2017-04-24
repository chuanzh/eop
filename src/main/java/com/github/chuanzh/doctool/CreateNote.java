package com.github.chuanzh.doctool;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.chuanzh.eop.annotation.DescNotNull;
import com.github.chuanzh.eop.annotation.DescNote;
import com.github.chuanzh.util.FuncStatic;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 创建文档注释
 *
 */
public class CreateNote {
	
	private Logger logger = Logger.getLogger(CreateNote.class);
	
	private static Configuration cfg = new Configuration();
	private Template temp = null;
	private static String encode = "UTF-8";
	
	static {
		cfg.setClassForTemplateLoading(CreateNote.class,"./");
		cfg.setDefaultEncoding(encode);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setNumberFormat("#.######");
	}
	
	public void create(String notePath, String packageName) throws IOException, ClassNotFoundException {
		File file = new File(notePath);
		if(!file.exists()) {   
	        file.createNewFile();  
	        logger.info("mkdir "+notePath);
		}
		
		List<Map<String,String>> services = PackageUtil.handle(packageName);
		int number = 1;
		List<HashMap<String, String>> interfaces = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, Object>> serviceList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> serviceMap = null;
		HashMap<String , String> interMap = null;
		for (Map<String,String> service : services) {
			Class requestClass = null;
			try {
				requestClass = Class.forName(service.get("request").toString());
			} catch (Exception e) {
				logger.error("初始化Request对象失败: "+service.get("request"));
				continue;
			}
			
			Class responseClass = null;
			try {
				responseClass = Class.forName(service.get("response").toString());
			} catch (Exception e) {
				logger.error("初始化Response对象失败: "+service.get("response"));
				continue;
			}
			
			String interfaceName = service.get("interfaceName").toString();
			interfaceName = interfaceName.substring(interfaceName.indexOf("service.")+"service.".length());
			interMap = new HashMap<String,String>();
			interMap.put("index", number+"");
			interMap.put("name", interfaceName);
			interMap.put("desc", service.get("serviceEffect").toString());
			interfaces.add(interMap);
			
			
			/* 请求对象 */
			List<Field> requestFields = FuncStatic.findClassAllField(requestClass);
			int index = 1;
			List<Request> requestList = new ArrayList<Request>();
			Request request = null;
			String parentParm = "无";
			for (Field field : requestFields) {
				String parmName = field.getName();
				String parmType = field.getType().toString();
				parmType = parmType.substring(parmType.lastIndexOf(".")+1);
				String parmDesc = "";
				int isNotNull = 0;
				if(field.getAnnotation(DescNote.class)!= null){
					parmDesc = field.getAnnotation(DescNote.class).value();			
				}
				
				if(field.getAnnotation(DescNotNull.class)!= null){
					isNotNull = 1;			
				}
				
				request = new Request();
				request.setIndex(index);
				request.setName(parmName);
				request.setType(parmType);
				request.setParent(parentParm);
				request.setIsNull(isNotNull);
				request.setDesc(parmDesc);
				requestList.add(request);
				index ++;
			}
			serviceMap = new HashMap<String, Object>();
			serviceMap.put("request", requestList);
			
			/* 相应对象 */
			List<Response> responseList = new ArrayList<Response>();
			index = 1;
			List<Field> reponseFields = FuncStatic.findClassAllField(responseClass);
			try {
				responseList = createResponse(responseList, reponseFields, parentParm, index);
			} catch (Exception e) {
				logger.error("生成响应注释出错： "+FuncStatic.errorTrace(e));
			}
			serviceMap.put("response", responseList);
			
			serviceMap.put("index", number);
			serviceMap.put("title", interfaceName);
			serviceList.add(serviceMap);
			
			number ++;
		}
		
		this.temp = cfg.getTemplate("modelHtml.ftl");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("interfaces", interfaces);
		map.put("services", serviceList);
		StringWriter sw = new StringWriter();
		try {
			this.temp.process(map, sw);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
		
		FileWriter fw = null;
		fw = new FileWriter(notePath);//生成的文件路径
		fw.write(sw.toString());
		fw.close();

		logger.info("生成文档完成,生成的路径为:"+notePath);
	}

	private List<Response> createResponse(List<Response> responseList, List<Field> fields, String parentName, int index) throws Exception {
		for (Field field : fields) {
			String parmName = field.getName();
			String parmType = field.getType().toString();
			parmType = parmType.substring(parmType.lastIndexOf(".")+1);
			String parmDesc = "";
			if(field.getAnnotation(DescNote.class)!=null){
				parmDesc = field.getAnnotation(DescNote.class).value();			
			}
			
			Response response = new Response();
			response.setIndex(index);
			response.setParent(parentName);
			response.setName(parmName);
			response.setType(parmType);
			response.setDesc(parmDesc);
			responseList.add(response);
			index++;
			
			if (parmType.equals("String") || parmType.equals("int")){
				
			} else {
				String genericType = field.getGenericType().toString();//返回一个 Type 对象，它表示此 Field 对象所表示字段的声明类型。
				String fieldClassName = genericType.substring(genericType.indexOf(" ")+1);
				if (genericType.indexOf("<") != -1 && genericType.indexOf(">") != -1) {
					fieldClassName = genericType.substring(genericType.indexOf("<")+1, genericType.indexOf(">"));
				}
				Class subResponseClass = Class.forName(fieldClassName);
				List subResponseFields = FuncStatic.findClassAllField(subResponseClass);
				createResponse(responseList, subResponseFields, parmName, index);
			}
		}
		
		return responseList;
	}
   
}
