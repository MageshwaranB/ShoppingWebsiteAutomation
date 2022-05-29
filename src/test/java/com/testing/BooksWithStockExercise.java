package com.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BooksWithStockExercise {
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
		int modifyInteger = integer + 1;
		List<String> list = new ArrayList<String>();
		WebElement quantityTxt = driver.findElement(By.className("cartcontents"));
		String[] value = quantityTxt.getText().split(" ");
		for (String s : value) {
			list.add(s);
		}
		String newValue = list.get(0);
		Integer newInteger = Integer.parseInt(newValue);
		for (int i = integer; i <= modifyInteger; i++) {
			WebElement basketButton;
			if (i < modifyInteger) {
				
				try {
					WebElement quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
					quantity.clear();
					quantity.sendKeys(integer.toString());
					basketButton=driver.findElement(By.xpath("//button[@type='submit']"));
					basketButton.click();
				} catch (StaleElementReferenceException e) {
					WebElement quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
					basketButton=driver.findElement(By.xpath("//button[@type='submit']"));
//					quantity.clear();
//					quantity.sendKeys(integer.toString());
//					
//					basketButton.click();
				}
			}
			else {
				WebElement quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
				quantity.clear();
				Integer value2=1;
				quantity.sendKeys(value2.toString());
				basketButton=driver.findElement(By.xpath("//button[@type='submit']"));
				basketButton.click();
				String firstHalf=" You cannot add that amount to the cart — we have ";
				WebElement error=driver.findElement(By.xpath("(//*[contains(text(),'You cannot add')])"));
				System.out.println(error.getText());
				if(error.getText().contains(arr[0]))
					driver.close();
				
			}
		}
		
	}
}

//"//li[text()=' You cannot add that amount to the cart — we have 1969 in stock and you already have 1969 in your cart.']"