package com.Automation.utilities.databaselayer.DTO;

public class ApplicationDTO {
	int id;
	int appid;
	String code;
	String name;
	String description;
	public ApplicationDTO(){
	}
	public ApplicationDTO(int id,String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	public ApplicationDTO(String code, String name){
		this.code = code;
		this.name = name;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
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
