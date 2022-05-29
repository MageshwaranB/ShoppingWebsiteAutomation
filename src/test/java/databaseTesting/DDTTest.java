package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDTTest 
{
	@DataProvider
	public String[][] getDbData() throws SQLException {
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet results=statement.executeQuery("select * from hr.saucelabs");
		results.last();
		int rows=results.getRow();
		System.out.println("Total no of rows: "+ rows);
		ResultSetMetaData meta = results.getMetaData();
		int cols=meta.getColumnCount();
		System.out.println("Total no of cols: "+cols);
		int index=0;
		String data[][]=new String[rows][cols];
		results.beforeFirst();
		while(results.next()) {
			for(int i=0;i<cols;i++) {
				data[index][i]=results.getString(i+1);
				System.out.println(data[index][i]);
			}
			index++;
		}
		return data;
	}
	
	@Test(dataProvider = "getDbData")
	public void sauceLabsTest(String uname, String pword) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
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
		driver.close();
	}
}
