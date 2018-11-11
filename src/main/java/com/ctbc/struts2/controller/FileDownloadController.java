package com.ctbc.struts2.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "struts-default")
//@ParentPackage(value = "json-default")
@Namespace(value = "/FileDownloadController")
//@Controller
@Results({
				@Result(name = "SUCCESS", location = "/WEB-INF/pages/default.jsp")
})
public class FileDownloadController extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private InputStream myInputStream;
	private String myFilename;

	private String dirtyWord;

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	@Action(value = "fileDownloadAction", results = {
			@Result(name = "success", type = "stream", params = {
							"contentType", "application/octet-stream",
							"inputName", "myInputStream",
							"contentDisposition", "attachment;filename=${myFilename}",
							"bufferSize", "2048",
			})
	}, interceptorRefs = {
			@InterceptorRef("defaultStack") // 可去 struts-default.xml 中查看要用哪個 stack
	})
	public String fileDownloadAction() {
		System.out.println("================ fileDownloadAction() ================");

//		System.out.println(" dirtyWord >>> " + dirtyWord);

		String from = "E:/CTBC_workspace_phantom/Struts2_FileUpDownload/images/lena.jpg";

		try {
			this.myInputStream = IOUtils.toBufferedInputStream(new FileInputStream(new File(from)));
			this.myFilename = StringUtils.substringAfterLast(from, "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}

	@JSON(serialize = false) // 指定此屬性不被序列化，也就是Struts2不會將此屬性吐回前端
	public String getDirtyWord() {
		return dirtyWord;
	}

	public void setDirtyWord(String dirtyWord) {
		this.dirtyWord = dirtyWord;
	}

	public InputStream getMyInputStream() {
		return myInputStream;
	}

	public void setMyInputStream(InputStream myInputStream) {
		this.myInputStream = myInputStream;
	}

	public String getMyFilename() {
		return myFilename;
	}

	public void setMyFilename(String myFilename) {
		this.myFilename = myFilename;
	}

	//	@org.apache.struts2.json.annotations.JSON(name = "myJsonData" /*可指定轉成JSON後的key要叫啥，不指定的話就會用resultMap*/)
	@org.apache.struts2.json.annotations.JSON(serialize = true)
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public static void main(String[] args) {

		String from = "E:/CTBC_workspace_phantom/Struts2_FileUpDownload/images/lena.jpg";
		String target = "D:/lenaABC.jpg";

		System.out.println("檔名 = " + StringUtils.substringAfterLast(from, "/"));
		
		try (BufferedInputStream buffFis = new BufferedInputStream(new FileInputStream(new File(from)));
				BufferedOutputStream buffFos = new BufferedOutputStream(new FileOutputStream(new File(target)));) {
			byte[] fileInBytes = IOUtils.toByteArray(buffFis, buffFis.available());
			IOUtils.write(fileInBytes, buffFos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
