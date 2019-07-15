package com.Automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Helper enum singleton to handle configuration properties.
 * 
 * @author Mounir Youssef
 * 
 */
public enum ConfigurationProperties {

  /** The singleton instance. */
  INSTANCE;

  /** Path to the configuration properties file. */
  private final String FILE_PATH = "src" + File.separator + "main" + File.separator + 
                                    "resources"+ File.separator + 
                                    "automation.properties";

  /** Selenium properties. */

  /**
   * Path to the binaries of the browser If it's empty, selenium will execute the default command
   * that corresponds to selected browser.
   */
  private final String browserdriver;
  private final String linuxbrowserDriver;
  private String JunitReportName;
  private String log4jFileName;
  
  /* CrystallTrack server properties. */
  /** URL of CrystallTrack server. */
  private final String applicationBaseURL;
   /* Test properties. */

  private char videorecord;
  private String videorecordpath;
  
  /** Database properties */
  private String mysqlDriver;
  private String mysqlUrl;
  private String mysqlUserName;
  private String mysqlPassword;
  /** application modules */
  private String commonModule;
 
  private String screenshotrecordpath;
  private String oshost;
  private String seleniumStandAloneServerURL;
  private String driverType;
    
  /**
   * Enum constructor.
   */
  private ConfigurationProperties() {
    final Properties properties = new Properties();
    try {
      properties.load(new FileInputStream(FILE_PATH));
    } catch (FileNotFoundException exception) {
      try {
        // Looking up for CrystallTrackest.properties in the project
        System.out.println("Using alternate path method to reach CrystallTrackTest.properties");
        ClassLoader classLoader = getClass().getClassLoader();
        File fi = new File(classLoader.getResource("automation.properties").getFile());
        properties.load(new FileInputStream(fi));
      } catch (FileNotFoundException fnfe) {
        System.out.println("automation.properties has not been found");
        fnfe.printStackTrace();
      } catch (IOException ioe) {
        System.out.println("automation.properties has not been found");
        ioe.printStackTrace();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    commonModule = properties.getProperty("test.application.module.common");
    browserdriver = properties.getProperty("test.win.webdriver");
    linuxbrowserDriver =  properties.getProperty("test.linux.webdriver");
    applicationBaseURL = properties.getProperty("application.url");
    JunitReportName = properties.getProperty("test.junitReportFile");
    log4jFileName = properties.getProperty("test.log4jFileName");
    videorecordpath = properties.getProperty("test.videorecordpath");
    screenshotrecordpath = properties.getProperty("test.screenshotrecordpath");
    oshost= properties.getProperty("test.type.oshost");
    seleniumStandAloneServerURL = properties.getProperty("test.selenium.server.stadalone.url");
    driverType= properties.getProperty("test.driver.type");

    try {
    	mysqlDriver = properties.getProperty("jdbc.driver");
        mysqlUrl = properties.getProperty("jdbc.url");
        mysqlUserName = properties.getProperty("jdbc.username");
        mysqlPassword = properties.getProperty("jdbc.password");
       
    } catch (NullPointerException databaseNameNotSet) {
      // In case bbdd.rdbms property isn't properly set in OpenbravoERPTest.properties file
      System.out.println("Database proerty hasn't been propertly set");
      databaseNameNotSet.printStackTrace();
    }
    try {
      videorecord = properties.getProperty("test.videorecord").charAt(0);
    } catch (NullPointerException | StringIndexOutOfBoundsException videorecordSettingNotCreated) {
      // In case videorecord property is not created in OpenbravoERPTest.properties file
      System.out.println("videorecord setting has not been set");
      videorecord = 'f';
    }
    
  }
  
  /** 
   * Get the common module name
   * */
  public String getCommonModule() {
	    return commonModule;
  }
  /**
   * Get the browser web driver for linux.
   * 
   * @return the webdriver
   */
  public String getLinuxBrowserDriver() {
    return linuxbrowserDriver;
  }
  /**
   * Get the browser web driver for windows.
   * 
   * @return the webdriver
   */
  public String getWindowsBrowserDriver() {
    return browserdriver;
  }
   public String getJunitReportName() {
      
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss-mmmm");
      if (!JunitReportName.contains(".html")) {
          JunitReportName= JunitReportName+ dateFormat.format(new Date()) + ".html"; 
      }
      return JunitReportName;
  }
  public String getLog4jFileName() {
    return log4jFileName;
  }
  /**
   * Get the base URL of the CrystallTrack server.
   * 
   * @return the base URL of the CrystallTrack server.
   */
  public String getApplicationBaseURL() {
    return applicationBaseURL;
  }
  
  /**
   * Get the the operating system type for the host.
   * 
   * @return the the operating system type for the host.
   */
  public String getOsHost() {
    return oshost;
  }
  /**
   * Get the selenium server standalone url
   * 
   * @return the the selenium server standalone url
   */
  public String getSeleniumStandAloneServerURL() {
    return seleniumStandAloneServerURL;
  }
  /**
   * Get the database driver name
   * 
   * @return the database name
   */
  public String getMysqlDriver() {
    return mysqlDriver;
  }
  /**
   * Get the database name
   * 
   * @return the database name
   */

  public String getMysqlUrl() {
    return mysqlUrl;
  }
 
  /**
   * Get the database user name
   * 
   * @return the database name
   */
  public String getMysqlUserName() {
    return mysqlUserName;
  }
  /**
   * Get the database user password
   * 
   * @return the database name
   */
  public String getMysqlUserPassword() {
    return mysqlPassword;
  }
  /**
   * Get the video record setting.
   * 
   * @return the video record setting.
   */
  public char getVideoRecord() {
    return videorecord;
  }
  /**
   * Get the video record path.
   * 
   * @return the video record path.
   */
  public String getVideoRecordPath(){
	  return videorecordpath;
  }
  /**
   * Get the  screenshot record path.
   * 
   * @return the screenshot record path.
   */
  public String getScreenShotRecordPath(){
	  return  screenshotrecordpath;
  }

  /**
   * Get the  driver type.
   * 
   * @return remote or standalone.
   */
  public String getDriverType(){
	  return    driverType;
  }
}
