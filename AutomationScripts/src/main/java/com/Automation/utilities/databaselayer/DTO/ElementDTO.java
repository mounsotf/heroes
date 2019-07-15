package com.Automation.utilities.databaselayer.DTO;

public class ElementDTO {
	int id;
	int pageid;
	String name;
	String location;

	public ElementDTO() {

	}

	public ElementDTO(int id, int pageid, String name, String location) {
		this.id = id;
		this.pageid = pageid;
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPageId() {
		return pageid;
	}

	public void setPageId(int pageid) {
		this.pageid = pageid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
