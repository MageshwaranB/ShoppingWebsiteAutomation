package com.testing;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PasswordWithoutSendKeys 
{
	static WebDriver driver;
	public static void main(String[] args) throws AWTException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.id("menu-item-50")).click();
		driver.findElement(By.id("reg_email")).sendKeys("uselessfellow@gym.com");
		WebElement passwordTxtBox = driver.findElement(By.id("reg_password"));
		//sendingThoughJS(passwordTxtBox,driver);
		sendingThroughActions(passwordTxtBox, driver);
		//passwordTxtBox.sendKeys(Keys.SHIFT);
		//sendingThroughRobot();
		Robot robot;
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyPress(KeyEvent.VK_N);
		//Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_N);
		//driver.manage().window().maximize();
		//Thread.sleep(3000);
		WebElement Status=driver.findElement(By.xpath("//div[@aria-live='polite']"));
		System.out.println(Status.getText());
		WebElement hintText = driver.findElement(By.className("woocommerce-password-hint"));
		System.out.println(hintText.getText());
		passwordTxtBox.click();
		
		driver.quit();
	}
	
	public static void sendingThoughJS(WebElement pwordTxtBox,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='admin';", pwordTxtBox);
	}
	public static void sendingThroughActions(WebElement pwordTxtBox,WebDriver driver) {
		Actions actions=new Actions(driver);
		actions.sendKeys(pwordTxtBox, "JurassICpARK").build().perform();
	}
	public static void sendingThroughRobot() {
		try {
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_SHIFT);
		}
		catch(Exception e) {
			
		}
	}
}
