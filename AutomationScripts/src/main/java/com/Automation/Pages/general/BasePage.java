package com.Automation.Pages.general;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Automation.utilities.databaselayer.DAO.ElementDAO;
import com.Automation.utilities.databaselayer.DAO.PageDAO;
import com.Automation.utilities.databaselayer.DTO.PageDTO;
import com.Automation.utilities.ConfigurationProperties;
import com.Automation.utilities.databaselayer.DAO.ApplicationDAO;
import com.Automation.utilities.databaselayer.DTO.ApplicationDTO;

public class BasePage {
	protected static final String FILE_PATH_PROPERTIES = ConfigurationProperties.INSTANCE.getLog4jFileName();

	public WebDriver driver;
	protected static Logger logger = LogManager.getLogger("com.crystalTrack.PageOgjects");
	protected Actions actions;
	protected Actions builder;

	protected static int timeOut = 30;

	// Constructor
	public BasePage(WebDriver mdriver) {
    	driver = mdriver;
       // actions = new Actions(driver);
       // builder = new Actions(driver);
  
        
    }
   
    public String getBrowserTitle(){
    	return driver.getTitle();
    }
    public WebElement getWebElementWhenVisible(By elementLocation){
    	  return (new WebDriverWait(driver, timeOut))
               .until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }
    public boolean findWebElement(By elementLocation){
  	  try {
  		  (new WebDriverWait(driver, timeOut))
  	       .until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
  	  }
  	  catch (Exception e) {
    	return false;
  	  }
  	  return true;
    }
    //Click Method
    public void click (By elementLocation) {
    	getWebElementWhenVisible(elementLocation).click();
     
    }
  //Read Text
    public String readText (By elementLocation) {
        return getWebElementWhenVisible(elementLocation).getText();
    }
    //Write Text
    public void writeText (By elementLocation, String text) {
    	getWebElementWhenVisible(elementLocation).sendKeys(text);
        
    }
    
	public  void waitForPageToLoad() {
		 String pageLoadStatus="";
	    do {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	         pageLoadStatus = (String)js.executeScript("return document.readyState");
	         
	        System.out.print(".");
	      } while ( !pageLoadStatus.equals("complete") );
	       // System.out.println();
	        logger.info("Page Loaded.");
	      }
	
    
    public HashMap<String,String> getElementByTagName(String appName, String pageName) {
  	 
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
  		
  		e.printStackTrace();
  		 logger.error(e.getMessage());
  	  }
  	  return hm;
  	}
    public void screenShot(String screenname)
    {
    	try {
	        File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        File dest= new File(ConfigurationProperties.INSTANCE.getScreenShotRecordPath()+"\\"+ screenname+"-"+new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date())+".png");
	        FileUtils.copyFile(scr, dest);
		} catch (IOException e) {
			
			logger.error(e.getMessage());
		}
    }

    

}