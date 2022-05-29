package com.jqueryExercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SliderPracticeWebsite 
{
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	
	@Test
	public void sliderExampleWithDemoWebsite() {
		driver.get("http://practice.automationtesting.in/shop/");
		WebElement initialSliderPoint = driver.findElement(By.xpath("//div[@class='price_slider_wrapper']/div/span[1]"));
		WebElement finalSliderPoint = driver.findElement(By.xpath("//div[@class='price_slider_wrapper']/div/span[2]"));
		Actions actions=new Actions(driver);
		//Filtering the price from 200-400
		
		actions.dragAndDropBy(initialSliderPoint, 32,0).perform();
		
		actions.dragAndDropBy(finalSliderPoint, -60, 1).perform();
		
		System.out.println("Gonna click the button");
		driver.findElement(By.xpath("//button[text()='Filter']")).click();
	}
}
