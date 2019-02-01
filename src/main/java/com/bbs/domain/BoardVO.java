package com.bbs.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private String img_file_name;
	private MultipartFile upload_f;

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getImg_file_name() {
		return img_file_name;
	}

	public void setImg_file_name(String img_file_name) {
		this.img_file_name = img_file_name;
	}

	public MultipartFile getUpload_f() {
		return upload_f;
	}

	public void setUpload_f(MultipartFile upload_f) {
		this.upload_f = upload_f;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", viewcnt=" + viewcnt + ", img_file_name=" + img_file_name + ", upload_f=" + upload_f
				+ "]";
	}

}
