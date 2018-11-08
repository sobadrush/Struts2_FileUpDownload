package com.ctbc.struts2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "struts-default")
@Namespace(value = "/FileUploadController")
@Controller
@Results({
	@Result(name = "SUCCESS", location = "/WEB-INF/pages/default.jsp")
})
public class FileUploadController extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private File myAttachment; // 上傳的源檔File(假設變數取為 xxx)
	private String myAttachmentFileName; // 宣告 xxxFileName 即可取得檔名
	private String myAttachmentContentType;// 宣告 xxxContentType 即可取得ContentType
	
	private String dirtyWord;

	@Action(value = "fileUploadAction", 
		results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/pages/result.jsp")
		},
		interceptorRefs = {
			@InterceptorRef(value = "fileUpload" , 
				params = { 
					"allowedTypes","image/bmp,image/jpeg,image/jpg,image/png,image/gif" , 
					"maximumSize" , "2048000" /*2MB*/
				}
			),
			@InterceptorRef("defaultStack") // 要加這行才取得到 myFilename
		}
	)
	public String fileUploadAction() {
		System.out.println("================ fileUploadAction() ================");
		
		System.out.println(" dirtyWord >>> " + dirtyWord);
		
		File target = new File(String.format("%s%s", "D:/" , myAttachmentFileName));
		try (BufferedOutputStream buff_os = new BufferedOutputStream(new FileOutputStream(target));) {
			byte[] byteArray = IOUtils.toByteArray(new FileInputStream(myAttachment));
			IOUtils.write(byteArray, buff_os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}

	public File getMyAttachment() {
		return myAttachment;
	}

	public void setMyAttachment(File myAttachment) {
		this.myAttachment = myAttachment;
	}

	public String getMyAttachmentFileName() {
		return myAttachmentFileName;
	}

	public void setMyAttachmentFileName(String myAttachmentFileName) {
		this.myAttachmentFileName = myAttachmentFileName;
	}

	public String getMyAttachmentContentType() {
		return myAttachmentContentType;
	}

	public void setMyAttachmentContentType(String myAttachmentContentType) {
		this.myAttachmentContentType = myAttachmentContentType;
	}

	public String getDirtyWord() {
		return dirtyWord;
	}

	public void setDirtyWord(String dirtyWord) {
		this.dirtyWord = dirtyWord;
	}

}
