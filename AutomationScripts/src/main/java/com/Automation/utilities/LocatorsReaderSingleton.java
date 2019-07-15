package com.Automation.utilities;

import com.Automation.utilities.databaselayer.DAO.ApplicationDAO;
import com.Automation.utilities.databaselayer.DAO.ElementDAO;
import com.Automation.utilities.databaselayer.DAO.PageDAO;
import com.Automation.utilities.databaselayer.DTO.ApplicationDTO;
import com.Automation.utilities.databaselayer.DTO.ElementDTO;
import com.Automation.utilities.databaselayer.DTO.PageDTO;

import java.util.HashMap;

public class LocatorsReaderSingleton {

 // private static  Document doc;
 protected LocatorsReaderSingleton() {
     // Exists only to defeat instantiation.
  }
  
  public static HashMap<String,String> getElementByTagName(String appName, String pageName) {

	  HashMap<String, String> hm=new HashMap<String,String>(); 
	  ApplicationDAO appdao = new ApplicationDAO();
	  try {
		ApplicationDTO appdto = appdao.getApplicationByName(appName);
		PageDAO pagedao = new PageDAO();
		PageDTO pagedto = pagedao.getPageByName(appdto.getId(), pageName);
		
		ElementDAO elementdao = new ElementDAO();
		
		 hm = elementdao.getElementsLocationByPageId(pagedto.getId());
		return hm;
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	  }
	  return hm;
	  
	  
  }
  public static HashMap<String,ElementDTO> getElementByPageName(String appName, String pageName) {
	  HashMap<String, ElementDTO> hm=new HashMap<String,ElementDTO>(); 
	  ApplicationDAO appdao = new ApplicationDAO();
	  try {
		ApplicationDTO appdto = appdao.getApplicationByName(appName);
		PageDAO pagedao = new PageDAO();
		PageDTO pagedto = pagedao.getPageByName(appdto.getId(), pageName);
		
		ElementDAO elementdao = new ElementDAO();
		
		 hm = elementdao.getElementsByPageId(pagedto.getId());
		return hm;
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	  }
	  return hm;
  }
  

	
  }
