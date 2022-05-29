package com.testing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigateBackAndForth extends ScrollingExercises {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> allImages = driver.findElements(By.xpath("(//a[@class='woocommerce-LoopProduct-link']/img)"));
		List<WebElement> addToBasket = driver.findElements(By.xpath("//a[text()='Add to basket']"));
		WebElement newArrivalsBanner = driver.findElement(By.xpath("//h2[text()='new arrivals']"));
//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String parentURL=driver.getCurrentUrl();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		Actions action = new Actions(driver);
		action.moveToElement(newArrivalsBanner).perform();
		for (int i = 0; i < allImages.size(); i++) {
			// int j=0;
			if (allImages.get(i) != null) {
				
				System.out.println(allImages.get(i).getAttribute("alt").toString());
				allImages.get(i).click();
				WebElement basket = driver.findElement(By.xpath("//button[text()='Add to basket']"));
				wait.until(ExpectedConditions.visibilityOf(basket));
				basket.click();
				
				driver.navigate().to(parentURL);
			}
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(newArrivalsBanner)));
		}
		driver.close();
	}

}
