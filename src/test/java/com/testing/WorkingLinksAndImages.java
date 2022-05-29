package com.testing;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingLinksAndImages {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException,IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://saucelabs.com/");
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println(allLinks.size());
		allLinks.addAll(driver.findElements(By.tagName("img")));
		List<WebElement> activeLinks = new ArrayList<WebElement>();
		for (int i = 0; i < allLinks.size(); i++) {
			System.out.println(allLinks.get(i).getAttribute("href"));
			if (allLinks.get(i).getAttribute("href") != null) {
				activeLinks.add(allLinks.get(i));
			}
		}
		System.out.println(activeLinks.size());
		ArrayList<String> links=new ArrayList<String>();
		for (int i = 0; i < activeLinks.size(); i++) {
			HttpURLConnection connection=(HttpURLConnection) new URL(activeLinks.get(i).getAttribute("href")).openConnection();
			connection.connect();
			
			String response= connection.getResponseMessage();
			int code = connection.getResponseCode();
			if(code==200)
			{
				links.add(activeLinks.get(i).getAttribute("href"));
			}
			
			connection.disconnect();
			System.out.println(activeLinks.get(i).getAttribute("href")+"--->"+response);
			
		}
		System.out.println("OK LINKS SIZE:"+links.size());
		driver.close();
	}

}
