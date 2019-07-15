package com.Automation.utilities.databaselayer.DTO;

public class RecordParamsDTO {
	int id;
	String name;
	String value;
	int idrecord;
	
	public RecordParamsDTO(){
		
	}

	public RecordParamsDTO(int id,int idrecord,String name, String value){
		this.id=id;
		this.idrecord = idrecord;
		this.name = name;
		this.value = value;
		
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getRecordId(){
		return idrecord;
	}
	public void setRecordId(int idrecord){
		this.idrecord = idrecord;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	
}
