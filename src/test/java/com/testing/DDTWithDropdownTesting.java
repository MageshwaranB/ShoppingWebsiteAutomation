package com.testing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utility.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDTWithDropdownTesting 
{
	ExcelReader excel;
	@Test(dataProvider = "providingDataSet")
	public void dataDriven(String firstname,String lastname, String company, String emailaddress,String phone,String Country,String streetaddress, String city, String state, String zipcode,String paymentoption) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//a[@class='woocommerce-LoopProduct-link']/img)[2]")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("wpmenucartli")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
		driver.findElement(By.id("billing_first_name")).sendKeys(firstname);
		driver.findElement(By.id("billing_last_name")).sendKeys(lastname);
		driver.findElement(By.id("billing_company")).sendKeys(company);
		driver.findElement(By.id("billing_email")).sendKeys(emailaddress);
		driver.findElement(By.id("billing_phone")).sendKeys(phone);
		driver.findElement(By.id("billing_address_1")).sendKeys(streetaddress);
		driver.findElement(By.id("s2id_billing_country")).click();
		WebElement searchBtn = driver.findElement(By.id("s2id_autogen1_search"));
		searchBtn.sendKeys(Country);
//		String preferredResult="United States (US)";
//		List<WebElement> allResults = driver.findElements(By.xpath("//ul[@class='select2-results']/li"));
//		for(WebElement options:allResults) {
//			String currentOption = options.getText();
//			if(currentOption.equals(preferredResult)) {
//				options.click();
//				System.out.println(currentOption);
//				break;
//			}
//		}
		searchBtn.sendKeys(Keys.ENTER);
		driver.findElement(By.id("billing_city")).sendKeys(city);
		driver.findElement(By.id("s2id_billing_state")).click();
		WebElement searchBox = driver.findElement(By.id("s2id_autogen2_search"));
		List<WebElement> stateResults = driver.findElements(By.xpath("//ul[@id='select2-results-2']/li"));
		searchBox.sendKeys(state);
		searchBox.sendKeys(Keys.ENTER);
		driver.findElement(By.id("billing_postcode")).sendKeys(zipcode);
		List<WebElement> paymentOptions = driver.findElements(By.xpath("//div[@id='payment']/ul/li"));
		List<WebElement> radioBTN = driver.findElements(By.xpath("//input[@type='radio']"));
		String preferredOption=paymentoption;
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
	
	@DataProvider
	public String[][] providingDataSet() {
		excel=new ExcelReader("./datafiles/BillingDetails.xlsx");
		String[][] data=excel.provideData("./datafiles/BillingDetails.xlsx", "Sheet1");
		return data;
	}
}
