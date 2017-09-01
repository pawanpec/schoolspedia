<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  <style type="text/css">
.footer{ clear:both}
.left_coloum{width:100%}
#cse-search-results{}
#cse-search-results iframe{width:100% !important; height:1500px !important}
.gs-webResult{ width:100% !important}
.gsc-control-cse div{ width:100% !important}
@media (max-width: 650px) {
}
</style>
  
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchContent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div class="left_coloum">
  <%@include file="adsenseSearchResult.jsp"%>
  </div>
  	<div class="right_coloum">
  	<%@include file="adsenseSearchPopularQuery.jsp"%>
  	</div>
  </body>
</html>
