package com.jqueryExercise;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Slider 
{
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void colorPickerSlider() throws InterruptedException {
		driver.get("https://jqueryui.com/slider/#colorpicker");
		WebElement iFrame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iFrame);
		String iFrameText=driver.findElement(By.xpath("//body[@class='ui-widget-content']/p")).getText();
		System.out.println(iFrameText);
		WebElement redSlider = driver.findElement(By.id("red"));
		Point initialLocationOfSlider = redSlider.getLocation();
		int initialSliderXCoordinate = initialLocationOfSlider.getX();
		int initialSliderYCoordinate = initialLocationOfSlider.getY();
		System.out.println(initialSliderXCoordinate+" "+initialSliderYCoordinate);
		Actions actions=new Actions(driver);
		actions.dragAndDropBy(redSlider, initialSliderXCoordinate, initialSliderYCoordinate).build().perform();
		actions.dragAndDropBy(redSlider, 40, 60).build().perform();
		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void cleanup() {
		driver.quit();
	}
	
	@Test
	public void priceSlider() {
		driver.get("https://jqueryui.com/slider/#range");
		WebElement iFrame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iFrame);
		WebElement amount = driver.findElement(By.id("amount"));
		System.out.println(amount.getText());
		String price=driver.findElement(By.xpath("//label[text()='Price range:']")).getText();
		String[] priceSplit=price.split(" ");
		List.of(priceSplit).forEach(s->System.out.println(s));
		String[] amountText=amount.getText().split(" ");
		List.of(amount).forEach(System.out::println);
		//getTheAmount(amount.getText())
;		
	}

	private void getTheAmount(String amount) {
		String[] splitValue=amount.split(" ");
		List.of(splitValue).forEach(s->System.out.println(s));
		
	}
}
