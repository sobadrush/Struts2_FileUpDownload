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
		      
      <hr>		      
		  
      <form action='<s:url action="fileUploadAction" namespace="/FileUploadController"/>' method="POST" enctype="multipart/form-data">
     	   <label for="myFile">使用一般html Tag</label>
     	   <input type="hidden" name="dirtyWord" value="fuckYou!">
     	   <input type="file" name="myAttachment" onchange="console.log( '%c %s' , 'color:yellow' , this.value)">
     	   <input type="submit" value="上傳囉"/>
      </form>      		  

	  <p/>
	
	  <s:form action="fileUploadAction" namespace="/FileUploadController" method="POST" enctype="multipart/form-data">
		  <s:file name="myAttachment" label="請選擇檔案(使用Struts2 Tag)" size="1"/>
		  <s:hidden name="dirtyWord" value="幹你娘"/>
		  <s:submit value="上傳囉!!!" align="center" />
	  </s:form>
		      
  </body>
</html>
