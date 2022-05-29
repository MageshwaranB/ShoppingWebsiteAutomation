package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleJDBC 
{
	/*
	 * 1. create the connection
	 * 2. Create the query
	 * 3. Execute the query using the method from statement class
	 * Apart from select command everything else is unilateral in the sense that we can pass the data but not see the result
	 * for select command, we can store the results in a resultset 
	 * 4. Close the connection
	 */
	
	public static void main(String[] args) throws SQLException{
		Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		Statement statement=con.createStatement();
		String query="select * from hr.saucelabs";
		ResultSet results=statement.executeQuery(query);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		while(results.next()) {
			String uname=results.getString("username");
			String pword=results.getString("password");
			System.out.println(uname+" "+pword);
			driver.findElement(By.id("user-name")).sendKeys(uname);
			driver.findElement(By.id("password")).sendKeys(pword);
			driver.findElement(By.id("login-button")).click();
			if(uname.contains("locked_out_user")) {
				WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
				System.out.println("error message is "+error.getText());
			}
			else {
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.id("logout_sidebar_link")).click();
				}
			}
		con.close();
		driver.close();
		System.out.println("done successfully");
	}
}
