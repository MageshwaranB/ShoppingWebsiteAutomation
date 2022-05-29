package com.testing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SplitString 
{
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://practice.automationtesting.in/");
		driver.findElement(By.xpath("(//a[@class='woocommerce-LoopProduct-link']/img)[1]")).click();
		try {
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			driver.findElement(By.id("wpmenucartli")).click();
			String[] subTotalArr=driver.findElement(By.xpath("//td[@data-title='Subtotal']")).getText().split("\u20B9");
			double subTotalPrice=removingRupeeSymbol(subTotalArr);
			String[] taxRateArr=driver.findElement(By.xpath("//td[@data-title='Tax']")).getText().split("\u20B9");
			double taxRate=removingRupeeSymbol(taxRateArr);
			String[] totalArr=driver.findElement(By.xpath("//tr[@class='order-total']//descendant::td")).getText().split("\u20B9");
			double totalPrice=removingRupeeSymbol(totalArr);
			if(subTotalPrice+taxRate==totalPrice) {
				WebDriverWait wait=new WebDriverWait(driver, 7);
				try {
				driver.findElement(By.xpath("//input[@id='coupon_code']")).sendKeys("krishnasakinala");
				driver.findElement(By.xpath("//input[@name='apply_coupon']")).click();
				
				if(totalPrice>=450)
				{
					WebElement couponMessage = driver.findElement(By.className("woocommerce-message"));
					System.out.println(couponMessage.getText()+" is displayed "+couponMessage.isDisplayed());
					WebElement couponRow = driver.findElement(By.xpath("//th[contains(text(),'Coupon')]"));
					System.out.println("Existence of coupon: "+couponRow.isDisplayed());
					String[] couponArr=driver.findElement(By.xpath("//td[@data-title='Coupon: krishnasakinala']/span")).getText().split("\u20B9");
					double couponAmount=removingRupeeSymbol(couponArr);
					if(subTotalPrice+taxRate-couponAmount!=totalPrice)
					{
						driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
						if(driver.getPageSource().contains("Billing Details"))
						{
							System.out.println("Success");
						}
					}
					else
						System.out.println("Failure");
					

				}
				else {
					WebElement errorMessage = driver.findElement(By.xpath("//ul[@class='woocommerce-error']/descendant::li"));
					wait.until(ExpectedConditions.visibilityOf(errorMessage));
					System.out.println(errorMessage.getText()+" is displayed "+ errorMessage.isDisplayed());
									}
			}
				catch(StaleElementReferenceException e) {
					
				}
			}
			

			driver.close();
		}
		catch (StaleElementReferenceException e) {
			//driver.findElement(By.xpath("//button[@type='submit']")).click();
			driver.findElement(By.id("wpmenucartli")).click();
		}
	}
	public static Double removingRupeeSymbol(String arr[]) {
		ArrayList<String> list=new ArrayList<String>();
		for(String s:arr)
		{
			list.add(s);
		}
		list.remove(0);
		String valueToConvert=list.get(0);
		Double value=Double.parseDouble(valueToConvert);
		return value;
	}
}
