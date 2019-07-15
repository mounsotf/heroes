package com.Automation.Pages.pageobjects;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import com.Automation.Pages.general.BasePage;
import com.Automation.utilities.ConfigurationProperties;

/**
 *
 * @author Mounir Youssef
 */
public class DashBoardPage extends BasePage {

	// HashMap<String, String> hmHomePage = new HashMap<String, String>();
	@CacheLookup
    private WebElement dashboard;
	@CacheLookup
    private WebElement heroes;
	@CacheLookup
    private WebElement clear;
	@CacheLookup
    private WebElement search;
  
    public DashBoardPage(WebDriver driver) {
       super(driver);
     //  hmHomePage =  getElementByTagName(
		//		ConfigurationProperties.INSTANCE.getCommonModule(), new Object(){}.getClass().getEnclosingClass().getSimpleName()
		//		);   
       String baseUrl = ConfigurationProperties.INSTANCE.getApplicationBaseURL();
       logger.info(String.format("Open the web page %s.", baseUrl));
       driver.get(baseUrl);
       //waitForPageToLoad();
       
       dashboard = this.getWebElementWhenVisible(By.cssSelector("a[href='/heroes/dashboard']"));
       heroes = this.getWebElementWhenVisible(By.cssSelector("a[href='/heroes/heroes']"));
       clear =  this.getWebElementWhenVisible(By.cssSelector("button.clear"));
       search = this.getWebElementWhenVisible(By.xpath("//*[@id=\"search-box\"]"));
    }
   
    public DashBoardPage clickDashboardLink () {
    	logger.info("click on dashboard link");
        dashboard.click();
        logger.info("make sure that search edit box does exist");
        search = this.getWebElementWhenVisible(By.xpath("//*[@id=\"search-box\"]"));
        return this;
    }
    public HeroesPage clickHeroesLink () {
    	logger.info("click on heroes link");
    	heroes.click();
        
        return new HeroesPage(driver);
    }
    public void clickClearLink () {
    	logger.info("click on clear link");
        clear.click();
        logger.info("messages should be cleared now");
     
    }
    public boolean clearbuttonIsVisible() {
    	logger.info("ckeck if clear button still visible");
    	return this.findWebElement(By.cssSelector("button.clear"));
    }
    public boolean searchHeroe(String heroname) {
    	logger.info("click on clear link");
    	clear.click();
    	logger.info("find and fill the search box by:" + heroname);
    	search.sendKeys(heroname);
    	return this.findWebElement(By.xpath("//*[text()[contains(.," + "'" + heroname+ "')]]"));
    	
    }
   
}
