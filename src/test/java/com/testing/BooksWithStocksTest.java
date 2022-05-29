package com.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BooksWithStocksTest 
{	
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
		List<String> list=new ArrayList<String>();
		for(String s:arr) {
			list.add(s);
		}
		String value=list.get(0);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		//Integer integer = Integer.parseInt(arr[0]);
		Integer integer=Integer.parseInt(value);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("wpmenucartli")).click();
		WebElement quantityTxt = driver.findElement(By.xpath("//div[@class='quantity']/input"));
		quantityTxt.clear();
		quantityTxt.sendKeys(integer.toString());
		System.out.println(quantityTxt.getAttribute("value"));
		driver.findElement(By.xpath("//input[@value='Update Basket']")).click();
		quantityTxt.clear();
		System.out.println("Before updating "+value);
		integer=integer+1;
		System.out.println("After updating "+ value);
		quantityTxt.sendKeys(integer.toString());
		//arr[0]=arr.
		//System.err.println(length);
		//quantityTxt.sendKeys(arr);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}

}
