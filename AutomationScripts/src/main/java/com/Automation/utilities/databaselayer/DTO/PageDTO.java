package com.Automation.utilities.databaselayer.DTO;

public class PageDTO {
	int id;
	int appid;
	String code;
	String name;
	
	public PageDTO(){
		
	}
	public PageDTO(int id, int appid, String name){
		this.id = id;
		this.appid = appid;
		this.name = name;
		
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getAppId(){
		return appid;
	}
	public void setAppId(int appid){
		this.appid = appid;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
}
