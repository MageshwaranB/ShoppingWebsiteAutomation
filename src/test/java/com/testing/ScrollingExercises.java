package com.testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollingExercises
{
	static JavascriptExecutor js;
	static WebDriver driver;
	static Actions action;
	static WebDriverWait wait;
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://saucelabs.com/");
		js=(JavascriptExecutor) driver;
		action=new Actions(driver);
		wait=new WebDriverWait(driver,10);
		ScrollingExercises scroll=new ScrollingExercises();
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		boolean actualRes = scroll.withJSExecutor(driver, "//i[@class='svg svg-facebook']");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Presence of Element is "+actualRes);
		String actualText=scroll.withActionClass(driver, "//div[@class='link-list display-horizontal-on-mobile']/ul/li[2]/a");
		System.out.println("The text present in the element :" + actualText);
		driver.quit();
	}
	
	public String withActionClass(WebDriver driver, String xpathValue) 
	{
		WebElement elementToBeScrolledUp = driver.findElement(By.xpath(xpathValue));
		action.moveToElement(elementToBeScrolledUp).perform();
		return elementToBeScrolledUp.getText();
		
	}
	public boolean withJSExecutor(WebDriver driver,String xpathValue) {
		WebElement elementToBeScrolledDown = driver.findElement(By.xpath(xpathValue));
		
		js.executeScript("arguments[0].scrollIntoView(true);", elementToBeScrolledDown);
		wait.until(ExpectedConditions.visibilityOf(elementToBeScrolledDown));
		return elementToBeScrolledDown.isDisplayed();
	}
}
