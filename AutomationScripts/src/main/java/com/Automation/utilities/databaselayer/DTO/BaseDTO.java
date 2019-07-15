package com.Automation.utilities.databaselayer.DTO;

public class BaseDTO {
	int id;
	String value;
	String name;
	public BaseDTO(){
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public String getValue(){
		return value;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
}
