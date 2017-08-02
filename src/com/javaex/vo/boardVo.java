package com.javaex.vo;

public class boardVo {
	private int set_num;
	private int num;
	private String title;
	private String content;
	private int hit;
	private String reg_date;
	private int user_no;
	private String names;
	
	public boardVo() {
		super();
	}

	public boardVo(int num, String title, String content, int hit, String reg_date, int user_no, String names) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
		this.names = names;
	}

	
	public int getSet_num() {
		return set_num;
	}

	public void setSet_num(int set_num) {
		this.set_num = set_num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "boardVo [num=" + num + ", title=" + title + ", content=" + content + ", hit=" + hit + ", reg_date="
				+ reg_date + ", user_no=" + user_no + ", names=" + names + "]";
	}
	
	
	
	
	
	
	

	
}
