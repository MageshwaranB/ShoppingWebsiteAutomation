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

public class BooksWithMaxStocks {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//a[@class='woocommerce-LoopProduct-link']/img)[2]")).click();
		String stockText = driver.findElement(By.xpath("//p[@class='stock in-stock']")).getText();
		String[] arr = stockText.split(" ");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		WebDriverWait wait = new WebDriverWait(driver, 5);
		Integer integer = Integer.parseInt(arr[0]);
		for (int i = 0; i < integer; i++) {
			WebElement addToBasket;
			try {
				addToBasket= driver.findElement(By.xpath("//button[@type='submit']"));
				addToBasket.click();
			} catch (StaleElementReferenceException e) {
				addToBasket = driver.findElement(By.xpath("//button[@type='submit']"));
				addToBasket.click();
			}
			//wait.until(ExpectedConditions.visibilityOf(addToBasket));
		}
		driver.close();
	}
}
