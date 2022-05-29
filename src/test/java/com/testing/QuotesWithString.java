package com.testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QuotesWithString 
{
	public static void main(String[] args) {
		System.out.println("\""+"magesh"+"\"");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement newArrival=driver.findElement(By.xpath("//h2[text()='new arrivals']"));
		WebElement element = driver.findElement(By.xpath("//img[@alt='Selenium Ruby']"));
		WebDriverWait wait=new WebDriverWait(driver, 7);
//		String text=element.getAttribute("alt");
//		String validation="\"" +text+"\""+" has been added to your basket.";
//		System.out.println(validation);
		element.click();
		WebElement validation;
		try {
		driver.findElement(By.xpath("//button[text()='Add to basket']")).click();
		validation=driver.findElement(By.xpath("//*[contains(text(),' has been added')]"));
		System.out.println(validation.getText());
//		while(!(newArrival.isDisplayed()))
//		{
//			driver.navigate().back();
//		}
//		if(driver.getPageSource().contains(validation.getText()))
//		{
//			System.out.print("invaded");
//			driver.navigate().back();
//		}
//		System.out.println(driver.getPageSource().toString());
		}
		catch (StaleElementReferenceException e) {
//			 validation=driver.findElement(By.xpath("//*[contains(text(),' has been added')]"));
//			 driver.navigate().back();
			 // TODO: handle exception
			newArrival=driver.findElement(By.xpath("//h2[text()='new arrivals']"));
//			while((!newArrival.isDisplayed()))
//			{
//				driver.navigate().back();
//			}
		}
//		if(driver.getPageSource().contains(validation)) {
			driver.close();
//		}
		
		
		
	}
}
