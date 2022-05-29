package com.testing;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utility.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownWithText 
{
	@Test
	public void dropdownTest() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//a[@class='woocommerce-LoopProduct-link']/img)[2]")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("wpmenucartli")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
		driver.findElement(By.id("s2id_billing_country")).click();
		String actualOption="Belgium";
		WebElement autoSearch = driver.findElement(By.id("s2id_autogen1_search"));
		autoSearch.sendKeys("Bel");
		List <WebElement> results=driver.findElements(By.xpath("//ul[@class='select2-results']/li"));
		String clickedOption = null;
		for(WebElement options:results) {
			String optionsText = options.getText();
//			if(optionsText.contains(actualOption))
//			{
//				options.click();
//				System.out.println(optionsText+" clicked");
//				break;
//			}
			System.out.println(optionsText);
			if(optionsText.contentEquals(actualOption)) {
				options.click();
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clickedOption=optionsText;
				break;
			}
		}
		System.out.println(clickedOption+ " is clicked ");
		List<WebElement> paymentOptions = driver.findElements(By.xpath("//div[@id='payment']/ul/li"));
		List<WebElement> radioBTN = driver.findElements(By.xpath("//input[@type='radio']"));
		String preferredOption="Direct Bank Transfer";
		String radioOption=null;
		for(WebElement options:paymentOptions) {
			String selectedOption=options.findElement(By.tagName("label")).getText();
			System.out.println(selectedOption);
			
			if(selectedOption.contains(preferredOption))
			{
				options.findElement(By.tagName("input")).click();
				String optionMessage = options.findElement(By.tagName("div")).getText();
				System.out.println(optionMessage);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				radioOption=selectedOption;
				System.out.println(radioOption+" is clicked");
				break;
			}
		}
		driver.close();
	}
	
	
}

