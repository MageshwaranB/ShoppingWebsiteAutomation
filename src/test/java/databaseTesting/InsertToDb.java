package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertToDb 
{
	static Connection con=null;
	Scanner scan=new Scanner(System.in);
	static WebDriver driver;
	public static void main(String[] args) throws SQLException {
		try {
		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		System.out.println("1. Perform insertion of records\n"
				+ "2. Show the existing the records\n"
				+ "3. Validation of existing data");
		InsertToDb db=new InsertToDb();
		int choice=Integer.parseInt(db.scan.nextLine());
		switch(choice) {
		case 1:
			db.insertingData();
			//System.out.println("Insertion successfull");
			break;
		case 2:
			db.selectionData("hr.registerdetails","email","password");
			
			break;
		case 3:
			driver=db.insertingThroughSelenium();
			driver.close();
			break;
		default :
			break;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
////			con.close();
//			driver.close();
//			}
	}
	
	public void insertingData() throws SQLException {
		String query="insert into hr.testingtable(username,password) values(?,?)";
		PreparedStatement prepareStmt = con.prepareStatement(query);
		System.out.println("Enter the username: \n");
		//String username=scan.nextLine();
		prepareStmt.setString(1, scan.nextLine());
		System.out.println("Enter the password");
		//String pword=scan.nextLine();
		prepareStmt.setString(2, scan.nextLine());
		int rows = prepareStmt.executeUpdate();
		if(rows>0) {
			System.out.println("Insertion is done");
		}
	}
	
	public void selectionData(String tableName,String ...columnName) throws SQLException {
		String query="select * from " + tableName;
		Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet result=statement.executeQuery(query);
		result.last();
		int rowCount=result.getRow();
		ResultSetMetaData metaData = result.getMetaData();
		int colCount=metaData.getColumnCount();
		result.beforeFirst();
//		String uname = null;
//		String pword = null;
		while(result.next()) {
			String uname = null;
			String pword = null;
			for(int j=0;j<colCount;j++) {
				 //uname=result.getString("username");
				uname=result.getString(columnName[0]); 
				pword=result.getString(columnName[1]);		
			}
			System.out.println("|"+uname+"| "+"|"+pword+"|");
		}
	}
	
	public void initializeDrivers() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}
	
	public WebDriver insertingThroughSelenium() throws SQLException {
		String query="insert into hr.testingtable(username,password) values(?,?)";
		PreparedStatement prepareStmt = con.prepareStatement(query);
		initializeDrivers();
		driver.findElement(By.id("menu-item-50")).click();
		WebElement usernameTxtBox = driver.findElement(By.id("reg_email"));
		usernameTxtBox.sendKeys("puthinachutney@cuisine.com");
		String currentUserName=usernameTxtBox.getAttribute("value");
		WebElement pwordTxtBox = driver.findElement(By.id("reg_password"));
		pwordTxtBox.sendKeys("butterdelite");
		String currentPword=pwordTxtBox.getAttribute("value");
		prepareStmt.setString(1, currentUserName);
		prepareStmt.setString(2, currentPword);
		WebElement registerBtn = driver.findElement(By.name("register"));
		int count=validatingExistenceOfCredentials(currentUserName, currentPword);
		try {
		if(count>0) {
			prepareStmt.executeUpdate();
			registerBtn.click();
			System.out.println("Successfully added");
		}
		}
		catch(Exception e) {
			System.out.println("Data is already present and you're violating the uniqueness");
		}
		return driver;
		
	}
	
	public int validatingExistenceOfCredentials(String currentUname, String currentPword) throws SQLException {
		String query="select * from hr.testingtable";
		Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet result=statement.executeQuery(query);
		result.last();
		int rowCount=result.getRow();
		ResultSetMetaData metaData = result.getMetaData();
		int colCount=metaData.getColumnCount();
		String[][] data=new String [rowCount][colCount];
		result.beforeFirst();
		String uname = null;
		String pword = null;
		int count=0;
		int index=0;
		while(result.next()) {
			for(int j=0;j<colCount;j++) {
				 data[index][j]=result.getString(j+1);
			}
			index++;
		}
		for(int i=0;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				uname=data[i][0];
				pword=data[i][1];
				//System.out.print(uname+ pword);
			}
			if(!(uname.equals(currentUname)&&pword.equals(currentPword))) {
				 count++;
			}
		}
		if(count>0) {
			return count;
		}
		else
			return 0;
	}
	public void tearDown() {
		driver.close();
	}
	
}
