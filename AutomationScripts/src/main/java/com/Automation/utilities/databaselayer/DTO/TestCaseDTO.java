package com.Automation.utilities.databaselayer.DTO;

public class TestCaseDTO {
	int id;
	int appid;
	String code;
	String name;
	
	public TestCaseDTO(){
		
	}
	public TestCaseDTO(int id, int appid,String code, String name){
		this.id = id;
		this.appid = appid;
		this.code = code;
		this.name = name;
		
	}
	public TestCaseDTO(String code, int appid,String name){
		this.appid = appid;
		this.code = code;
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
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
}
