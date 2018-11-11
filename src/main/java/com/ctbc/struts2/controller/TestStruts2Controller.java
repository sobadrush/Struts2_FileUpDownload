package com.ctbc.struts2.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

// @ParentPackage(value = "struts-default")
@ParentPackage(value = "json-default")
@Namespace(value = "/TestStruts2Controller")
@Controller
@Results({
				@Result(name = "SUCCESS", location = "/WEB-INF/pages/default.jsp")
})
public class TestStruts2Controller extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DataSource ds;

	private String dirtyWord;

	private Map<String, Object> resultFuckMap;

	@Action(value = "myTest", results = {
					@Result(name = "success", type = "dispatcher", location = "/WEB-INF/pages/result.jsp")
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

	// URL : http://localhost:8080/Struts2_FileUpDownload/TestStruts2Controller/myTestJson.action
	@Action(value = "myTestJson", results = {
					@Result(name = "success", type = "json", params = { "root", "resultFuckMap" })
	})
	public String myTestJson() {
		System.out.println("================ myTestJson() ================");
		System.out.println(" dirtyWord = " + dirtyWord);

		List<String> listA = new ArrayList<>();
		listA.add("華農兄弟");
		listA.add("這樣不行唷");
		listA.add("烤竹鼠");

		resultFuckMap = new HashMap<>();
		resultFuckMap.put("listA", listA);
		return ActionSupport.SUCCESS;
	}

	// 測試 【includeProperties】
	// URL : http://localhost:8080/Struts2_FileUpDownload/TestStruts2Controller/myTestJson2.action
	// 【params屬性說明】http://cqjava.iteye.com/blog/465495
	@Action(value = "myTestJson2", results = {
					@Result(name = "success", type = "json", params = {
									"root", "resultFuckMap",
									"includeProperties", "AAA,BBB,listA", /*可用regex*/
									"ignoreHierarchy" , "true", // 是否要ignore輸出父類別的屬性
									"excludeProperties " , "B*" // 把B打頭的屬性排除
					})
	})
	public String myTestJson2() {
		System.out.println("================ myTestJson2() ================");
		System.out.println(" dirtyWord = " + dirtyWord);

		List<String> listA = new ArrayList<>();
		listA.add("華農兄弟");
		listA.add("這樣不行唷");
		listA.add("烤竹鼠");

		resultFuckMap = new HashMap<>();
		resultFuckMap.put("AAA", "aaa");
		resultFuckMap.put("BBB", "bbb");
		resultFuckMap.put("listA", new Gson().toJson(listA)); // 前端JSON.parse回來
		return ActionSupport.SUCCESS;
	}

	public Map<String, Object> getResultFuckMap() {
		return resultFuckMap;
	}

	public void setResultFuckMap(Map<String, Object> resultFuckMap) {
		this.resultFuckMap = resultFuckMap;
	}

	public String getDirtyWord() {
		return dirtyWord;
	}

	public void setDirtyWord(String dirtyWord) {
		this.dirtyWord = dirtyWord;
	}

}
