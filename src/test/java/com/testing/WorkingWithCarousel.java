package com.testing;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingWithCarousel 
{
	static WebDriver driver;
	public WebDriver initializeBrowser(String websiteLink) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(websiteLink);
		driver.manage().window().maximize();
		return driver;
	}
	
	public void carousel(WebDriver driver) {
		WebElement carouselElement = driver.findElement(By.id("homeslider"));
		List<WebElement> totalLinks = carouselElement.findElements(By.tagName("li"));
		int count=0;
		for (int i = 0; i < totalLinks.size(); i++) {
			if (totalLinks.get(i).getAttribute("class").equals("homeslider-container"))
			{
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.findElement(By.className("bx-prev")).click();
				count++;
			}
		}
		System.out.println("Total Carousel Elements under the slider: "+count);
	}
	
	public void teardown() {
		driver.close();
	}
	
	public static void main(String[] args) {
		WorkingWithCarousel carousel=new WorkingWithCarousel();
		carousel.initializeBrowser("http://automationpractice.com/index.php");
		carousel.carousel(driver);
		carousel.teardown();
	}
}
