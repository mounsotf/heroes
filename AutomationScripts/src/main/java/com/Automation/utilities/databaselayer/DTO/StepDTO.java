package com.Automation.utilities.databaselayer.DTO;

public class StepDTO {
	int id;
	int testcaseid;
	String code;
	String name;

	public StepDTO() {

	}

	public StepDTO(int id, int testcaseid, String code, String name) {
		this.id = id;
		this.testcaseid = testcaseid;
		this.code = code;
		this.name = name;
	}

	public StepDTO(String code, int testcaseid, String name) {
		this.testcaseid = testcaseid;
		this.code = code;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestCaseId() {
		return testcaseid;
	}

	public void setTestCaseId(int testcaseid) {
		this.testcaseid = testcaseid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
