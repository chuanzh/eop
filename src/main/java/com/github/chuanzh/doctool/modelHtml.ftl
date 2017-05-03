<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<html>
<head>
<title>model.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}
a:link{color:black  }
a:visited{color:purple}
a:hover{color:red}
#aa{ 
background: #CAE1FF;
} 
#bb{ 
background: #FFFFFF;
} 
.cc{
background: #FFFFFF; 
}
     .css1 {background-color: #ffffff;}
     .css2 {background-color: #1E90FF;}
</style>
<script language="javascript">
   function mouseOver(obj){
      if(obj.className="css1")
         obj.className="css2";
   }
   function mouseOut(obj){
      if(obj.className="css2")
         obj.className="css1";
   }
</script> 
</head>

<body>
　　　　 <center><h1>接口文档注释</h1></center>
	<a name="top"></a>
	<table  width="90%" height="100%"  border="1" cellpadding="0" cellspacing="0" style="margin-top: 20px; margin-left: 20px;" id="alternatecolor" class="altrowstable" >
		<tr>
			<td align="center" width="70">序号</td>
			<td align="center" width="30%">接口名称</td>
			<td align="center" >接口作用</td>
		</tr>
		<#list interfaces as inter>
			<td align="center" width="70">${inter["index"] !}</td>
			<td align="center" width="30%">${inter["name"] !}</td>
			<td align="center" >${inter["desc"] !}</td>
		</#list>
	</table>
	
	<#list services as service>
		<span id="request"></span>
		<h2>${service["index"]!}. 方法名称:${service["title"]!}</h2> 
		请求参数列表:
		<br/>
		<table  width="90%" height="100%"  border="1" cellpadding="0" cellspacing="0" style="margin-top: 20px; margin-left: 20px;" id="alternatecolor" class="altrowstable" >
			<tr class="cc">
				<td align="center" width="70">序号</td>
				<td align="center" width="70">上级参数</td>
				<td align="center" width="70">参数名</td>
				<td align="center" width="70">类型</td>
				<td align="center" width="70">是否可为空</td>
				<td align="center" >说明</td>
			</tr>
			<#list service["request"] as request>
				<tr onMouseOver='mouseOver(this)'  onMouseOut='mouseOut(this)'>
					<td align="center">${request.index!}</td>
					<td align="center">${request.parent!}</td>
					<td align="center">${request.name!}</td>
					<td align="center">${request.type!}</td>
					<td align="center">
						<#if request.isNull == 0>
							否
						<#else>
							是
						</#if>
					</td>
					<td align="center">${request.desc!}</td>
				</tr>
			</#list>
		</table>
		
		<br/>
			<span id="response"></span>
			响应参数列表:
			<table  width="90%" height="100%"  border="1" cellpadding="0" cellspacing="0" style="margin-top: 20px; margin-left: 20px;" id="alternatecolor" class="altrowstable" >
				<tr class="cc">
					<td align="center" width="70">序号</td>
					<td align="center" width="70">上级参数</td>		
					<td align="center" width="70">参数名</td>
					<td align="center" width="70">类型</td>
					<td align="center" >说明</td>
				</tr>
				<#list service["response"] as response>
				<tr onMouseOver='mouseOver(this)'  onMouseOut='mouseOut(this)'>
					<td align="center">${response.index!}</td>
					<td align="center">${response.parent!}</td>
					<td align="center">${response.name!}</td>
					<td align="center">${response.type!}</td>
					<td align="center">${response.desc!}</td>
				</tr>
				</#list>
			</table>
		<br/>
		<a href="#top">回到顶部</a>
		<br/>
		<hr/>
	</#list>
				
	
　　<div style="font-weight:bold;">错误编码定义</div><br/>
	<table align=center  style="border-collapse:collapse;width:600pt; margin-left:-1.2500pt; mso-table-layout-alt:fixed; padding:0.0000pt 0.0000pt 0.0000pt 0.0000pt ; " >
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">错误编码</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">错误说明</span></td>
		</tr>
		<tr style="height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1001</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">缺少AppKey</span></td>
		</tr>
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1002</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">无效AppKey</span></td>
		</tr>
		<tr style="height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1003</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">IP受限</span></td>
		</tr>
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1004</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">无权访问指定接口</span></td>
		</tr>
		<tr style="height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1005</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">appKey已过期</span></td>
		</tr>
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1006</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">接口请求次数超限制</span></td>
		</tr>
		<tr style="height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1007</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">无效接口方法名</span></td>
		</tr>
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1008</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">无效签名</span></td>
		</tr>
		<tr style="height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1009</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">缺少接口方法名参数</span></td>
		</tr>
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1010</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">缺少必选参数</span></td>
		</tr>
		<tr style="height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1011</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">业务逻辑错误</span></td>
		</tr>
		<tr style="background:rgb(237,242,250); height:30px;">
			<td width="65" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">1012</span></td>
			<td width="103" valign="center"><span style="font-size:10.0000pt; font-family:'Arial';">服务超时</span></td>
		</tr>
	</table>
		
</body>
</html>
