/**
 * 
 */
package com.Automation.general;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import com.Automation.utilities.databaselayer.DAO.RecordDAO;
import com.Automation.utilities.databaselayer.DAO.RecordParamsDAO;
import com.Automation.utilities.databaselayer.DAO.StepDAO;
import com.Automation.utilities.databaselayer.DAO.StepParamsDAO;
import com.Automation.utilities.databaselayer.DAO.TestCaseDAO;
import com.Automation.utilities.databaselayer.DAO.TestCaseParamsDAO;
import com.Automation.utilities.databaselayer.DTO.BaseDTO;
import com.Automation.utilities.databaselayer.DTO.RecordDTO;
import com.Automation.utilities.databaselayer.DTO.RecordParamsDTO;
import com.Automation.utilities.databaselayer.DTO.StepDTO;
import com.Automation.utilities.databaselayer.DTO.TestCaseDTO;
import com.Automation.utilities.ConfigurationProperties;
import com.Automation.utilities.SeleniumSingleton;
import com.Automation.utilities.databaselayer.DAO.ApplicationDAO;
import com.Automation.utilities.databaselayer.DTO.ApplicationDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseTestCase {
	protected WebDriver driver = null;
	static protected Logger logger = LogManager.getLogger(BaseTestCase.class.getName());
	//protected String applicationName = ConfigurationProperties.INSTANCE.getCommonModule();
   
   // static HashMap<String, BaseDTO> testcaseparams = getTestCaseParams(applicationName, testCaseName);;
    
    @BeforeAll
    protected void beforeAllTests() {
        logger.info("Setup the browser...");
       
       
        //ConfigurationProperties.INSTANCE.getSeleniumStandAloneServerURL()    
    }
    
    @AfterAll
    protected void afterAllTests() {
        
    }

    @BeforeEach
    protected void beforeEachTest(TestInfo testInfo) {
    	logger.info("before Each Test in super class");
    }

    @AfterEach
    protected void afterEachTest(TestInfo testInfo) {
    	logger.info("after Each Test in super class");
    	logger.info("Close the browser");
    	SeleniumSingleton.getInstance().stopDriver();
       
    }
    
    protected HashMap<String,BaseDTO> getTestCaseParams(String applicationName,String testcasename)  {
 	   ApplicationDAO applicationDataObject = new ApplicationDAO();
 	   ApplicationDTO app = null;
    
 		try {
 			app = applicationDataObject.getApplicationByName(applicationName);
 			TestCaseDAO testcaseDataObject = new TestCaseDAO();
 			HashMap<String,TestCaseDTO> testCases  = testcaseDataObject.getTestCasesByApplicationId(app.getId());
 			TestCaseDTO testcase = testCases.get(testcasename);
 		   
 		   // get testcase params list by testcase id
 		   TestCaseParamsDAO testcaseparamdao = new  TestCaseParamsDAO();
 		   HashMap<String,BaseDTO> params = testcaseparamdao.getTestCaseParamsByTestCaseId(testcase.getId());
 		   
 		      	   
 				return params;
 			
 			} 
 			catch (SQLException e) {
 				e.printStackTrace();
 			}
 			
 	      return null;  
 		}
    protected HashMap<String,BaseDTO> getStepParams(String applicationName,String testCaseName, String stepName)  {
 	   ApplicationDAO applicationDataObject = new ApplicationDAO();
 	   ApplicationDTO app = null;
    
 		try {
 			app = applicationDataObject.getApplicationByName(applicationName);
 			TestCaseDAO testcaseDataObject = new TestCaseDAO();
 			HashMap<String,TestCaseDTO> testCases  = testcaseDataObject.getTestCasesByApplicationId(app.getId());
 			TestCaseDTO testcase = testCases.get(testCaseName);
 			//  get the steps
 	        StepDAO stepDataObject = new StepDAO();
 	    	HashMap<String,StepDTO> steps = stepDataObject.getStepsByTestCaseId(testcase.getId());
 	    	// get the given step
 	    	StepDTO step = steps.get(stepName);
 	    	 StepParamsDAO stepparamsDataObject = new StepParamsDAO();
 	    	HashMap<String, BaseDTO> steparams = stepparamsDataObject.getStepParamsByStepId(step.getId());
 		    return steparams;
 			
 			} 
 			catch (SQLException e) {
 				e.printStackTrace();
 			}
 			
 	      return null;  
 		}
    protected HashMap<String,RecordParamsDTO> getRecordParams(RecordDTO record)  {
    try {	
 	    	// get all record params
 	    	RecordParamsDAO recordDataObject = new RecordParamsDAO();
 	    	HashMap<String,RecordParamsDTO> params = recordDataObject.getRecordParamsByRecordId(record.getId());
 			    	   
 			return params;
 		
 		} 
 		catch (SQLException e) {
 			e.printStackTrace();
 		}
 		
       return null;  
 	}
    protected HashMap<String,RecordDTO> getRecords(String applicationName, String testCaseName, String stepName) throws InterruptedException {
 	       
 		    ApplicationDAO applicationDataObject = new ApplicationDAO();
 	    	ApplicationDTO app = null;
 	    
 			try {
 				app = applicationDataObject.getApplicationByName(applicationName);
 				TestCaseDAO testcaseDataObject = new TestCaseDAO();
 				HashMap<String,TestCaseDTO> testCases  = testcaseDataObject.getTestCasesByApplicationId(app.getId());
 				TestCaseDTO testcase = testCases.get(testCaseName);
 				//  get the steps
 		        StepDAO stepDataObject = new StepDAO();
 		    	HashMap<String,StepDTO> steps = stepDataObject.getStepsByTestCaseId(testcase.getId());
 		    	// get the given step
 		    	StepDTO step = steps.get(stepName);
 		    	// get step's records
 		    	RecordDAO recordDataObject = new RecordDAO();
 		    	HashMap<String,RecordDTO> records = recordDataObject.getRecordsByStepId(step.getId());
 				    	   
 				return records;
 			
 			} 
         	catch (SQLException e) {
 				e.printStackTrace();
 			}
 			
 	      return null;  
 	    }

}


