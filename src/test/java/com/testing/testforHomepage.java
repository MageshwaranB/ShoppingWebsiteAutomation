package com.testing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testforHomepage 
{
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://practice.automationtesting.in/");
		WebElement ele= driver.findElement(By.xpath("//div[@class='n2-ss-slider-3']"));
		List<WebElement> allImages= ele.findElements(By.xpath("//div[@class='n2-ss-slider-3']/div"));
		WebElement arrowelement=driver.findElement(By.id("n2-ss-6-arrow-next"));
		int count=0;
		for (int i = 0; i < allImages.size(); i++) {
			allImages.get(i).isDisplayed();
			arrowelement.click();
			count++;
		}
		if(count==allImages.size())
		{
			System.out.println("Images are displayed"+count);
		}
		else
			System.out.println("Images aren't displayed");
		driver.close();
	}
}
