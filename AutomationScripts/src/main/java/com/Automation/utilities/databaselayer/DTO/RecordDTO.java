package com.Automation.utilities.databaselayer.DTO;

public class RecordDTO implements Comparable<RecordDTO> {
	int id;
	int stepid;
	String name;
	int parentid;
	int recorder;

	
	public RecordDTO(){
		
	}

	public RecordDTO(int id, int stepid,String name, int parentid){
		this.id=id;
		this.stepid = stepid;
		this.name = name;
		this.parentid=parentid;
	
		
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getParentId(){
		return parentid;
	}
	public void setParentId(int parentid){
		this.parentid = parentid;
	}
	public int getStepId(){
		return stepid;
	}
	public void setStepId(int stepid){
		this.stepid = stepid;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getRecOrder(){
		return recorder;
	}
	public void setRecOrder(int recorder){
		this.recorder = recorder;
	}
	
	 @Override
	 public int compareTo(RecordDTO obj) {
	        return this.getRecOrder() - obj.getRecOrder();
	    }
	 @Override
     public boolean equals(final Object obj) {
       if (obj == null) {
          return false;
       }
       final RecordDTO std = (RecordDTO) obj;
       if (this == std) {
          return true;
       } else {
          return (this.getId() == std.getId());
       }
	 }
	
}
