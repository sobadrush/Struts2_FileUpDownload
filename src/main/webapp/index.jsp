<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%> 
 
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <title>測試Struts2 檔案上傳/下載</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
  </head>
  <body style="padding:0.5cm">
      <h1 style="color:red">index.jsp</h1>
      
      <h2 style="color:purple">URL : http://localhost:8080/Struts2_FileUpDownload/index.jsp</h2>
     
      <a href='<c:url value="/TestControllerServlet"/>'>Click_Servlet</a>
      
      <p/>
      <s:url action="myTest" namespace="/TestStruts2Controller"/>
      <a href='<s:url action="myTest" namespace="/TestStruts2Controller"/>'>Click_Struts2</a>
		      
      <hr>		      
		  
      <form action='<s:url action="fileUploadAction" namespace="/FileUploadController"/>' method="POST" enctype="multipart/form-data" style="border: solid 1px black;">
     	   <label for="myFile">使用一般html Tag</label>
     	   <input type="hidden" name="dirtyWord" value="fuckYou!">
     	   <input type="file" name="myAttachment" onchange="console.log( '%c %s' , 'color:yellow' , this.value)">
     	   <input type="submit" value="上傳囉"/>
      </form>      		  

	  <p/>
	
	  <s:form action="fileUploadAction" namespace="/FileUploadController" method="POST" enctype="multipart/form-data" style="border: solid 1px black;">
		  <s:file name="myAttachment" label="請選擇檔案(使用Struts2 Tag)" size="1"/>
		  <s:hidden name="dirtyWord" value="幹你娘"/>
		  <s:submit value="上傳囉!!!" align="center" />
	  </s:form>
      
	  <p/>      

	  <form id="myForm" action='javascript:void(0)' method="POST" enctype="multipart/form-data" style="border: solid 1px black;">
     	   <label for="myFile">使用ajaxSubmit插件：</label>
     	   <input type="hidden" name="dirtyWord" value="幹你娘">
     	   <input type="file" name="myAttachment" onchange="console.log( '%c %s' , 'color:yellow' , this.value)"><br>
     	   <!-- <input type="submit" value="上傳囉"/> -->
     	   <button id="myBtn" type="button" class="btn btn-primary">-- 上傳囉 --</button>
      </form>    
	  
	  <script>
	  	$(function(){
	  		$("#myBtn").click(function(event){
	  			event.preventDefault();
	  			$('#myForm').ajaxSubmit({
	  				url : '<s:url action="fileUploadJsonAction" namespace="/FileUploadController"/>',
	  	            type : 'post',
	  	            dataType : 'json', // 預期服務器返回的資料類型，若指定json，則無需再解析json字符串
	  	            data : {},
	  	            success : function(data) {
	  	            	console.log( ' data >>> ' , data );
	  	                alert(data.resultMap.rtnMsg);
	  	            }
	  			});
	  		});
	  	});
	  </script>
  </body>
</html>



