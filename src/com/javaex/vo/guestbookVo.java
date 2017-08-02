package com.javaex.vo;

public class guestbookVo {
	private int no;
	private String name;
	private String password;
	private String content;
	private String date;

	public guestbookVo(int no, String name, String password, String content, String date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.date = date;
	}
	
	public guestbookVo( String name, String password, String content) {
		super();
		
		this.name = name;
		this.password = password;
		this.content = content;
		
	}
	
	public guestbookVo(int no, String name, String content, String date) {
		super();
		this.no = no;
		this.name = name;
		
		this.content = content;
		this.date = date;
	}

	public guestbookVo( String no, String password) {
		super();
		
		
		this.no = Integer.valueOf(no);
		this.name=" ";
		this.content=" ";
		this.password = password;
		
		
	}
	
	public guestbookVo() {
		super();
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "guestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", date=" + date + "]";
	}


	
	

}
