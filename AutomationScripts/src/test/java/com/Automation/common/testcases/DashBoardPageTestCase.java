/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Automation.common.testcases;

import com.Automation.utilities.ConfigurationProperties;
import com.Automation.utilities.SeleniumSingleton;
import com.Automation.utilities.databaselayer.DTO.BaseDTO;
import com.Automation.Pages.pageobjects.DashBoardPage;
import com.Automation.general.BaseTestCase;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


/**
 *
 * @author Mounir.Youssef
 */
@DisplayName("DashBoardPageTestCase")
 public class DashBoardPageTestCase extends BaseTestCase {
	 
	String testCaseName;
	HashMap<String, BaseDTO> testcaseparams ;
	
    public DashBoardPageTestCase() {
    	super();
		//testCaseName = new Object() {
	  //  }.getClass().getEnclosingClass().getSimpleName();
	  //  testcaseparams = getTestCaseParams(applicationName, testCaseName);
    	 
    }
    @BeforeAll
    
	public
    void beforeAllTests() {
    	 
    	
    }

    @AfterAll
    public void afterAllTests() {
        // write code before super.afteralltests method call
        super.afterAllTests();
    }

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
    	super.beforeEachTest(testInfo);
        logger.info(() -> String.format("About to execute [%s]",
            testInfo.getDisplayName()));
    }

    @AfterEach
    public void afterEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("Finished executing [%s]",
            testInfo.getDisplayName()));
        super.afterEachTest(testInfo);
    }
    private static Stream parametersConfigurations() {
        return Stream.of(
                Arguments.of("linux", "chrome", ConfigurationProperties.INSTANCE.getSeleniumStandAloneServerURL(),"Magneta" ),
                Arguments.of("linux", "firefox", ConfigurationProperties.INSTANCE.getSeleniumStandAloneServerURL(),"Magneta" )
        );
    }
    @DisplayName("Should find the right Hero")
    @ParameterizedTest(name = "{index} => os={0}, browser={1}, node={2}, hero= {3}")
    @MethodSource("parametersConfigurations")
    public void Test_1_SearchHeroeTestCase(String os, String browser,String nodeurl, String heroName) {
    	driver = SeleniumSingleton.getInstance().getDriver(os,browser,nodeurl);
	    DashBoardPage mainPage = new DashBoardPage(driver);
		DashBoardPage dashboardPage = mainPage.clickDashboardLink();
        assertTrue(dashboardPage.searchHeroe(heroName),heroName + " Hero not found");
        
    }
    
}
