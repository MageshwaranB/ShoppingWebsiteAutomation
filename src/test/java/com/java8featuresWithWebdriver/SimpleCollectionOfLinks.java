package com.java8featuresWithWebdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCollectionOfLinks {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
         WebDriver driver=new ChromeDriver();
         driver.get("http://practice.automationtesting.in/");
         driver.manage().window().maximize();
         List<WebElement> navBarLinks=driver.findElements(By.id("main-nav")).stream().collect(Collectors.toList());
         navBarLinks.forEach(links-> System.out.println(links.getText()));
         driver.quit();
    }
}
