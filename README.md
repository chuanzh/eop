# eop
Easy Open Platform Framework

EOP is a simple easyrom development framework, it realizes the data layer, based on Oracle, MySQL database, and realizes the separation of reading and writing; it also realizes the basic error model is defined, such as: access control, access restrictions, parametric test, convenient and fast positioning; it also includes the document annotation generation, interface testing and other functions, you only need to take care of your business.

## Frame Diagram
![image](https://raw.githubusercontent.com/chuanzh/eop/master/doc/eop.png) 

## Configuration and Development
The EOP framework has been released to the Maven library, you can download the following configuration
```xml
	<dependency>
		<groupId>com.github.chuanzh</groupId>
		<artifactId>eop</artifactId>
		<version>1.0.1</version>
	</dependency>
```
  + Achieve your ApiFilter class, configured to web.xml，as follows;  
    + getRunTimeLimit(): The maximum execution time of the service  
    + projectEncode(): The coding of your project, This code will be used in response.  
    + controlFolder(): The path of your service.  
    + startRequest(): you can do somethings in this start request, such as Record the log  
    + endRequest(): you can do somethings in this end request, such as Record the log  
    + beforeDoControl(): You can deal with some of the logic in the service method before the implementation, such as access control，parameter verification, controlObject parameter is the interface object,inputRequest parameter is request class  
```Java
public class ApiFilter extends EopFilter {
	
	private static Logger logger = Logger.getLogger(ApiFilter.class);

	@Override
	public int getRunTimeLimit() {
	    // The maximum execution time of the service
		return 10000;
	}


	@Override
	public String projectEncode() {
		// The coding of your project
		return "UTF-8";
	}

	@Override
	public String controlFolder() {
		// The path of your service
		return "xx.xx.service";
	}
	
	@Override
	public void startRequest(HttpServletRequest request){
		// Actions that need to be performed before the request is started, such as log printing
	}

	@Override
	public void endRequest(long pageRunTime, HttpServletRequest request, AbstractResponse responseBean) {
		// At the end of the request, you need to handle the operation, such as log printing, etc.
	}


	@Override
	public ErrorResponse beforeDoControl(Object controlObject, Object inputRequest, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// You can deal with some of the logic in the service method before the implementation, such as access control, access control has a separate module, you can use，You can return ErrorResponse when permission is not authenticated

		return null;
	}

}
```
web.xml configuration
```xml
  <filter>
    <filter-name>apiFilter</filter-name>
    <filter-class>cn.chuanz.util.ApiFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>apiFilter</filter-name>
    <url-pattern>/router</url-pattern>
  </filter-mapping>
```
Now you can develop your first interface, the interface consists of three parts, business processing xxxService, request object xxxRequest, response object xxxResponse
The business class uses the func method directly, passing in xxxRequest, returning xxxResponse.（@DescNote and other annotations are used later to generate annotations and injections for objects）,throw exception, so that EOP can capture
```Java
@DescNote("Get Dynamic Follow list")
public class GetFollowList {
		
	private static Logger logger = Logger.getLogger(GetFollowList.class); 
	
	public AbstractResponse func(GetFollowListRequest request) throws Exception {
		GetFollowListResponse response = new GetFollowListResponse();
		try {	
			FollowBean bean = new FollowBean();
			bean.setFlightno("CA1858");
			bean.setFlightdate("2017-01-01");
			bean.setFlightdepcode("SHA");
			bean.setFlightarrcode("PEK");
			bean.setFlightdep("ShangHai");
			bean.setFlightarr("BeiJing");
			List<FollowBean> datas = new ArrayList<FollowBean>();
			datas.add(bean);
			response.setDatas(datas);;
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			throw e;
		}
		
		return response;
	}
	
}
```
The request object needs to inherit the AbstractRequest class, and if the data needs to be paged, the PagerRequest
```Java
public class GetFollowListRequest extends AbstractRequest {

	@DescNote("phone ID")
	@DescNotNull
	private String phoneId;
	
	@DescNote("user ID")
	@DescNotNull
	private String userId;
	
	@DescNote("last operation time")
	private String latestOperTime;


}
```
The request object needs to implement AbstractResponse, and PagerResponse if the data needs to be paged
```Java
public class GetFollowListResponse extends AbstractResponse {

	private List<FollowBean> datas;

	// set and get method
	
}


public class FollowBean {
	
	@DescNote("flight no")
	private String flightno;
	@DescNote("flight date")
	private String flightdate;
	@DescNote("dep airport code")
	private String flightdepcode;
	@DescNote("arr airport code")
	private String flightarrcode;
	@DescNote("dep airport name")
	private String flightdep;
	@DescNote("arr airport name")
	private String flightarr;
	
	// set and get method
	
}
```

## interface access  
All interfaces are accessed by constructing the method parameter, and you can access the GetFollowList interface in the following way:  
Http://youhost/projectName/router?method=dynamic.getFollowList&phoneId=123&userId=12&latestOperTime=2017-01-01 00:00:00  

the result is json, such as:  
{"datas":[{"flightarr":"WuHan","flightarrcode":"PEK","flightdate":"2017-01-01","flightdep":"ShangHai","flightdepcode":"SHA","flightno":"CA1858"}],"errorCode":"1000"}

## create document comments
the document can be automatically create by use annotations, as follows：  
the first argument is document path, the second argument is service package path  
```Java
    CreateNote cn = new CreateNote();
    cn.create("/Users/zhangchuan/Desktop/note.html", "cn.chuanz.service");
```
![image](https://raw.githubusercontent.com/chuanzh/eop/master/doc/doc1.png)   
![image](https://raw.githubusercontent.com/chuanzh/eop/master/doc/doc2.png)   


## Error model  
  + The EOP has help you define some Error that you can use in interface, such as :    
    + 1001: Missing appKey  
    + 1002: Invalid appKey
    + 1003: IP forbidden  
    + 1004: No access to the service (you need configured access service first)    
    + 1005: appKey expired  
    + 1006: The number of requests is beyond limit  
    + 1007: Invalid interface method name (method parameter is Error)  
    + 1008: Invalid signature  
    + 1009: Missing method parameter  
    + 1010: Missing required parameters  
    + 1011: Business logic error  
    + 1012: Service timeout  

1001-1010 error can refer to the permission control module  

## Permission Control  
First, you need to import the permission table [permission.sql](https://github.com/chuanzh/eop/blob/master/doc/permission.sql), which you can configure and add in the eop-admin project.  
![image](https://raw.githubusercontent.com/chuanzh/eop/master/doc/eop-admin1.png)   
![image](https://raw.githubusercontent.com/chuanzh/eop/master/doc/eop-admin2.png) 

You can create a new app user, and then assign it appkey, appsecret, 
  + permission options:  
    + Bind Ip: only the specified IP can access  
    + Valid Date: the expiration that the service can be accessed  
    + Is Lock: when it is "yes", the service will not accessed  
    + Limit Type: there are tow type,(limit for all service| limit for some service), "limit for all service" that all service can be accessed, "limit for some service": you must configure some service that can be accessed  
    + Total Limit: the total number of times, when the value is "-1", it is not restricted  
    + Limit Rate: the unit is seconds, the maximum number of times per second can be accessed.  
    
After you configure the permissions list, you can use the permission class to control and use it  
```Java  
  EopPermission ep = new EopPermission(dbService);
  ep.setAppKeyCheck(true)
    .setIpCheck(true)
    .setMethodCheck(true);
  ErrorResponse err = ep.check(request);
```  
AppKeyCheck(true): Will verify whether the appKey is available, whether it is out of date, the signature is correct  
IpCheck(true): Will verify the IP is legal  
MethodCheck(true): Will check whether more than the total limit or the date limit.  
if verify fails,that will return ErrorResponse Object.

## Annotations use  
  + EOP cantains some annotation for injecting objects and create Note,such as: 
    + @DescNote: define parameter description
    + @DescNotNull: if you define this annotation no parameter,  when access service, the parameter must be not null, otherwise there will be errors
    + @IjDbService: database service object, when you use DbBasicService，you can use this annotation like this: 
  ```Java
      @IjDbService(DbDynamicConnect.class)
      private DbBasicService dbService;
  ```

## Performance Testing 
