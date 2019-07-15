package com.Automation.Pages.pageobjects;

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

/**
 *
 * @author Mounir Youssef
 */
public class HeroesPage extends BasePage {

	// HashMap<String, String> hmHomePage = new HashMap<String, String>();
    @CacheLookup
    private WebElement add;
    @CacheLookup
    private WebElement title;
    @CacheLookup
    private WebElement heroName; 

    public HeroesPage(WebDriver driver) {
	    super(driver);
	     //  hmHomePage =  getElementByTagName(
			//		ConfigurationProperties.INSTANCE.getCommonModule(), new Object(){}.getClass().getEnclosingClass().getSimpleName()
			//		);   
	    // waitForPageToLoad();
         title =this.getWebElementWhenVisible(By.xpath("/html/body/app-root/app-heroes/h2")); 
         add = this.getWebElementWhenVisible(By.cssSelector("app-root app-heroes div button"));
         heroName = this.getWebElementWhenVisible(By.cssSelector("body > app-root > app-heroes > div > label > input"));
		       
    } 
    public void AddHero(String mheroName) {
    	heroName.sendKeys(mheroName);
    	add.click();
    }
    public boolean checkIfHeroExist(String heroname) {
    	return this.findWebElement(By.xpath("//*[text()[contains(.," + "'" + heroname+ "')]]"));
    			
    }
    public boolean deleteHeroe(String heroname) {
    	logger.info("find the hero " + heroname);
    	WebElement element=  this.getWebElementWhenVisible(By.xpath("//*[text()[contains(.," + "'" + heroname+ "')]]/following-sibling::button"));
   
       	element.click();
       	logger.info(" hero " + heroname + " should be disappeared");
    	return true;
    	
    }
}
