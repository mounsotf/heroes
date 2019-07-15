package com.Automation.utilities.databaselayer.DTO;

public class TestCaseParamsDTO extends BaseDTO {
	
	int testcaseid;
	
	public TestCaseParamsDTO(){
		
	}

	public TestCaseParamsDTO(int id,int testcaseid,String name, String value){
		this.id=id;
		this.testcaseid = testcaseid;
		this.name = name;
		this.value = value;
		
	}
	
	public int getTestCaseId(){
		return testcaseid;
	}
	public void setTestCaseId(int testcaseid){
		this.testcaseid = testcaseid;
	}
	
	
	
	
}
