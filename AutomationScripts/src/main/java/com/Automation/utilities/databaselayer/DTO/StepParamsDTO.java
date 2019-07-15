package com.Automation.utilities.databaselayer.DTO;

public class StepParamsDTO extends BaseDTO {
	
	int stepid;
	
	public StepParamsDTO(){
		
	}

	public StepParamsDTO(int id,int stepid,String name, String value){
		this.id=id;
		this.stepid = stepid;
		this.name = name;
		
		
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getstepid(){
		return stepid;
	}
	public void setTestCaseId(int stepid){
		this.stepid = stepid;
	}
	

	
}
