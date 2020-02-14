package com.dell.password;

import java.io.Serializable;

public class Webdetails implements Serializable {
	
	Userdetails ud=new Userdetails();

	private static final long serialVersionUID = 1L;
	private String website_name;
	private String web_user_name;
	private String web_pwd;
	
	
	private String uname =(String) ud.getUser_name();
		
	public String getWebsite_name() {
		return website_name;
	}
	public void setWebsite_name(String website_name) {
		this.website_name = website_name;
	}
	
	public String getWeb_user_name() {
		return web_user_name;
	}
	public void setWeb_user_name(String web_user_name) {
		this.web_user_name = web_user_name;
	}
	public String getWeb_pwd() {
		return web_pwd;
	}
	public void setWeb_pwd(String web_pwd) {
		this.web_pwd = web_pwd;
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Webdetails(){}
	public Webdetails( String uname,String website_name,
			String web_user_name, String web_pwd) {
		super();
		this.uname = uname;
		this.website_name = website_name;
		this.web_user_name = web_user_name;
		this.web_pwd = web_pwd;
	}
	@Override
	public String toString() {
		return " \n User Name = "+ uname+  "\n website_name="
				+ website_name + "\n web_user_name=" + web_user_name
				+ "\n web_pwd=" + web_pwd + "";
	}
	
	   
	
	
	
	
}
