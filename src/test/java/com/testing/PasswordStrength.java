package com.testing;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PasswordStrength {
	static WebDriver driver;

	public void initializeDrivers() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("menu-item-50")).click();
	}
	
	//Scanner scan =new Scanner(System.in);
	
	public String sendingValues() throws Exception{
		//System.out.println("Enter the username: ");
		//String email=scan.nextLine();
		driver.findElement(By.id("reg_email")).sendKeys("muttaipuffs@peacock.com");
		//System.out.println("Enter the password: ");
		WebElement pwordTxtBox = driver.findElement(By.id("reg_password"));
		String pword = "MYnameISjAmesBond#1356";

		pwordTxtBox.sendKeys(pword);
		//pwordTxtBox.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pwordTxtBox.sendKeys(Keys.ENTER);
		pwordTxtBox.sendKeys(Keys.ENTER);
		WebElement status = driver.findElement(By.xpath("//div[@aria-live='polite']"));
		int strengthStatus = validatingPasswordStrength(pword);
		if (strengthStatus > 0) {
			System.out.println("Current status of the password: "+status.getText());
			return status.getText();
		} else {
			System.out.println("Current status of the password: "+status.getText());
			return status.getText();
		}
	}

	public int validatingPasswordStrength(String pword) {
		int count = 0;
		System.out.println("Password length: "+pword.length());
		int minimumCharacters=7;
		int passwordLength=pword.length();
		if (passwordLength > minimumCharacters) {
			String specialCharacters = " ! \" ? $ % ^ & ).";
			boolean numbersFlag = false;
			boolean uppercaseFlag = false;
			boolean lowercaseFlag = false;
			boolean specialCharactersFlag = false;
			for (int i = 0; i < pword.length(); i++) {
				Character ch = pword.charAt(i);
				if (Character.isDigit(ch)) {
					numbersFlag = true;
				} else if (Character.isLowerCase(ch)) {
					lowercaseFlag = true;
				} else if (Character.isUpperCase(ch)) {
					uppercaseFlag = true;
				} else if (specialCharacters.contains(Character.toString(ch))) {
					specialCharactersFlag = true;
				}
				if (uppercaseFlag && lowercaseFlag && numbersFlag && specialCharactersFlag) {
					count++;
				}
			}
		} else {
			System.out.println("Password should be minimum of 7 characters ");
		}
		return count;
	}

	public int checkingPasswordStatus(String status) {
		if (status.contains("Medium") || status.contains("Strong")) {
			return 1;
		} else {
			return 0;
		}
	}

	@BeforeMethod
	public void setup() {
		initializeDrivers();
	}

	@Test
	public void testExecution() {
		
		try {
			String status = sendingValues();
			int strengthValue = checkingPasswordStatus(status);
			if (strengthValue > 0) {
				driver.findElement(By.name("register")).click();
			} else {
				System.out.print(driver.findElement(By.className("woocommerce-password-hint")).getText());
			}
		} catch (Exception e) {

		}
	}

	@AfterMethod
	public void termination() {
		driver.quit();
	}
}
