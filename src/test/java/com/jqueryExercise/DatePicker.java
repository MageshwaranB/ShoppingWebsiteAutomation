package com.jqueryExercise;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker 
{
	static WebDriver driver;
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
	}
	
	@Test
	public void datePickerType1() {
		String month="November 2022";
		String day="12";
		
		driver.get("https://www.phptravels.net/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("checkin")).click();
		while(true) {
			String currentMonth = driver.findElement(By.xpath("//th[@class='switch'][1]")).getText();
			if(currentMonth.equalsIgnoreCase(month)) {
				break;
			}
			else {
				driver.findElement(By.className("next")).click();
			}
		}
		/*
		 * In the below xpath, we have hardcoded the value we wanted, to pass it dynamically, just
		 * add two plus before and after the value and mention it in double quotes
		 */
		//driver.findElement(By.xpath("(//tbody[1]/tr/td[contains(text(),'12')])[1]")).click();
		driver.findElement(By.xpath("(//tbody[1]/tr/td[contains(text(),"+day+")])[1]")).click();
		
		
	}
	
	@Test
	public void datePickerTyp2() {
		driver.get("https://www.expedia.ca/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[text()='Flights']")).click();
		driver.findElement(By.id("d1-btn")).click();
		String expMonth="November 2022";
		String expDate="12";
		while(true) {
			String currentMonth = driver.findElement(By.xpath("(//div/div/h2)[2]")).getText();
			if(currentMonth.equalsIgnoreCase(expMonth)) {
				break;
			}
			else {
				driver.findElement(By.xpath("//div[@class='uitk-calendar']/ descendant::button[2]")).click();
			}
		}
		driver.findElement(By.xpath("(//tbody/tr/td/button[contains(@data-day,"+expDate+")])[1]")).click();
		
	}
	
//	@AfterClass
//	public void cleanup() {
//		driver.quit();
//	}
	
	}

