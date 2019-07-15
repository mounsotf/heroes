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
import com.Automation.Pages.pageobjects.HeroesPage;
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
@DisplayName("HeroesPageTestCase")
 public class HeroesPageTestCase extends BaseTestCase {
	 
	String testCaseName;
	HashMap<String, BaseDTO> testcaseparams ;
	
    public HeroesPageTestCase() {
    	super();
		//testCaseName = new Object() {
	  //  }.getClass().getEnclosingClass().getSimpleName();
	  //  testcaseparams = getTestCaseParams(applicationName, testCaseName);
    	 
    }
    public
    void beforeAllTests() {
    	super.beforeAllTests();
    	
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
    private static Stream driversconfigurations() {
        return Stream.of(
                Arguments.of("linux", "chrome", ConfigurationProperties.INSTANCE.getSeleniumStandAloneServerURL(),"Mounir Youssef"),
                Arguments.of("linux", "firefox", ConfigurationProperties.INSTANCE.getSeleniumStandAloneServerURL(), "Mounir Youssef" )
        );
    }
    @DisplayName("Should add and delete a Hero")
    @ParameterizedTest(name = "{index} => os={0}, browser={1}, node={2}, hero= {3}")
    @MethodSource("driversconfigurations")
    public void Test_1_AddHeroeTestCase(String os, String browser,String nodeurl, String heroName) {
		driver = SeleniumSingleton.getInstance().getDriver(os,browser,nodeurl);
		DashBoardPage dashboardPage = new DashBoardPage(driver);
		HeroesPage heroesPage = dashboardPage.clickHeroesLink();
		dashboardPage.clickClearLink();
		heroesPage.AddHero(heroName);
		assertTrue(heroesPage.checkIfHeroExist(heroName),"add hero failed");
		 
		heroesPage.deleteHeroe(heroName);
		assertTrue(!heroesPage.checkIfHeroExist(heroName),"delete hero failed");
        
    }
       
}
