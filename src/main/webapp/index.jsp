<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%> 
 
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
      <h1 style="color:red">index.jsp</h1>
      
     
      <a href='<c:url value="/TestControllerServlet"/>'>Click_Servlet</a>
      
      <p/>
      <s:url action="myTest" namespace="/TestStruts2Controller"/>
      <a href='<s:url action="myTest" namespace="/TestStruts2Controller"/>'>Click_Struts2</a>
		      
  </body>
</html>
