/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Automation.utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Mounir.Youssef
 */
public class SeleniumSingleton  {
	private static  SeleniumSingleton _instance = new SeleniumSingleton();
	private  WebDriver driver = null;
	static protected Logger logger = LogManager.getLogger(SeleniumSingleton.class.getName());
   
   public static synchronized SeleniumSingleton getInstance()  {
       if ((_instance==null) ) {
           _instance = new SeleniumSingleton();
       }
       return _instance;
   }
   
   private SeleniumSingleton() {
	       
   }
   private void setDriver(String os, String browser,String nodeurl) {
	   if (driver !=null) return;
	   String driverType =  ConfigurationProperties.INSTANCE.getDriverType();
	   if (driverType.equalsIgnoreCase("remote")) {
		 
		try{
	       //ChromeOptions options = new ChromeOptions();
	        // set some options
	        //options.addArguments("--headless");
	        //options.addArguments("--privileged");
	        //options.addArguments("--no-sandbox");
	        //DesiredCapabilities dc = DesiredCapabilities.chrome();
          // dc.setCapability(ChromeOptions.CAPABILITY, options);
          SetupTestSeleniumDriver setupdriver = new SetupTestSeleniumDriver(os, browser, nodeurl);
          driver = setupdriver.getDriver();
          // driver = new RemoteWebDriver(new URL(ConfigurationProperties.INSTANCE.getSeleniumStandAloneServerURL()),options );
	           
	     }
	     catch(Exception  e){
	     }
	   }
	   else {
		// Exists only to defeat instantiation.
           String hostType =  ConfigurationProperties.INSTANCE.getOsHost();
           if (hostType.equalsIgnoreCase("windows")){
                System.setProperty ("webdriver.chrome.driver", ConfigurationProperties.INSTANCE.getWindowsBrowserDriver());
           } else {
                System.setProperty ("webdriver.chrome.driver", ConfigurationProperties.INSTANCE.getLinuxBrowserDriver());
           }
           driver = new ChromeDriver(); 
		   
	   }
	   // Maximize Window
       // driver.manage().window().maximize();
       //Wait for page to load
       driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	   
   }
   public WebDriver getDriver(String os, String browser,String nodeurl) {
	   setDriver(os, browser,nodeurl);
	   logger.info("driver is up");
	   return getInstance().driver;
   }
    public void stopDriver() {
      if (_instance !=null) {
    	  if( _instance.driver != null) {
    		  //_instance.driver.close();
    		  _instance.driver.quit(); 
    		  _instance.driver = null;
    	  
    	  }
    	  _instance.driver = null;
    	  _instance=null;
    	  logger.info("driver is down");
      }
     
   }
}
