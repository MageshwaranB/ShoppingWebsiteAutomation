package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataBaseBilling 
{
	@DataProvider
	public String[][] provideDbData() throws SQLException{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		Statement state=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String query="select * from hr.billingdata";
		ResultSet result=state.executeQuery(query);
		result.last();
		int rowCount=result.getRow();
		ResultSetMetaData meta = result.getMetaData();
		int columnCount=meta.getColumnCount();
		int index=0;
		String data[][]=new String[rowCount][columnCount];
		result.beforeFirst();
		while(result.next()) {
			for(int j=0;j<columnCount;j++) {
				try {
					data[index][j]=result.getString(j+1);
				}catch(NullPointerException e) {
					data[index][j]="";
				}
				
				}
			index++;
		}
		return data;
	}
	
	
	@Test(dataProvider = "provideDbData")
	public void dataDrivenTest(String firstName,String lastName,String companyName,String email,String phone,String country, String address, String city, String state, String zipcode, String paymentOption) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='Shop']")).click();
		driver.findElement(By.xpath("//img[@alt='Android Quick Start Guide']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("wpmenucartli")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
		driver.findElement(By.id("billing_first_name")).sendKeys(firstName);
		driver.findElement(By.id("billing_last_name")).sendKeys(lastName);
		driver.findElement(By.id("billing_company")).sendKeys(companyName);
		driver.findElement(By.id("billing_email")).sendKeys(email);
		driver.findElement(By.id("billing_phone")).sendKeys(phone);
		driver.findElement(By.id("s2id_billing_country")).click();
		
		WebElement searchTxt = driver.findElement(By.id("s2id_autogen1_search"));
		searchTxt.sendKeys(country);
		List<WebElement> results = driver.findElements(By.xpath("//ul[@id='select2-results-1']/li"));
		for(WebElement option:results) {
			String currentOption=option.getText();
			if(currentOption.equals(country)) {
				option.click();
				break;
			}
		}
		driver.findElement(By.id("billing_address_1")).sendKeys(address);
		driver.findElement(By.id("billing_city")).sendKeys(city);
		driver.findElement(By.id("s2id_billing_state")).click();
		
		WebElement stateTxtBox = driver.findElement(By.id("s2id_autogen2_search"));
		stateTxtBox.sendKeys(state);
		List<WebElement> stateRes = driver.findElements(By.xpath("//ul[@id='select2-results-2']/li"));
		for(WebElement currentState:stateRes) {
			String currentStateTxt=currentState.getText();
			if(currentStateTxt.contains(state));
			{
				currentState.click();
				break;
			}
		}
		driver.findElement(By.id("billing_postcode")).sendKeys(zipcode);
		List<WebElement> paymentWays = driver.findElements(By.xpath("//div[@id='payment']/ul/li"));
		//List<WebElement> radioBtns = driver.findElements(By.xpath("//input[@type='radio']"));
		for(WebElement payment:paymentWays) {
			String currentPayment = payment.findElement(By.tagName("label")).getText();
			if(currentPayment.equals(paymentOption))
			{
				payment.findElement(By.tagName("input")).click();
				System.out.println(payment.findElement(By.tagName("div")).getText());
				break;
			}
		}
		driver.findElement(By.id("place_order")).click();
		while(!driver.getPageSource().contains("new arrivals")) {
			//driver.navigate().back();
			if(!driver.getPageSource().contains("Basket Totals")) {
				driver.navigate().back();
				continue;
			}
			else {
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[@class='remove']")).click();
				Thread.sleep(3000);
				System.out.println("item is removed");
				driver.navigate().back();
			}
			//driver.navigate().back();
		}
		driver.close();
	}
}
