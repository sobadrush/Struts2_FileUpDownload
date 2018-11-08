package com.ctbc.struts2.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "struts-default")
@Namespace(value = "/TestStruts2Controller")
@Controller
@Results({
	@Result(name = "SUCCESS", location = "/WEB-INF/pages/default.jsp")
})
public class TestStruts2Controller extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DataSource ds;
	
	@Action(value = "myTest", results = { 
		@Result(name = "success" , type = "dispatcher" , location = "/WEB-INF/pages/result.jsp" ) 
	})
	public String myTest() {
		System.out.println("================ myTest() ================");
		
		try {
			System.out.println("DatabaseProductName >>> " + ds.getConnection().getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}

}
