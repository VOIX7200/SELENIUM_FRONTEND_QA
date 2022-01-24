package com.demo.Selenium;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDTestDemo2 extends DDTestDemo {
	
	//If I told im done and im ready ..i will be wasting our time and effort...

	// Put the jason String here 

	String userLogins = "{\"userlogins\":[{\"username\":\"steveedm@mail.com\",\"password\":\"Passgate123#\"},"
			+ "{\"username\":\"steveedm@mail.com\",\"password\":\"Passgate123#\"},"
			+ "{\"username\":\"steveedm@mail.com\",\"password\":\"Passgate123#\"}]}";	



	//WebDriver driver;
	/*
	 * @BeforeTest public void setUp() { WebDriverManager.chromedriver().setup();
	 * driver = new ChromeDriver();
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	 * 
	 * }
	 */

	@Test(dataProvider="dp")
	public void loginTest(String data) throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		Thread.sleep(100);
		System.out.println(data.split(",")[0]);
		String usrname = data.split(",")[0].replace("\"","");
		System.out.println(data.split(",")[1]);
		String pwd = data.split(",")[1].replace("\"","");

		//Perform Login 
		driver.findElement(By.xpath("//input[@class='email']")).sendKeys(usrname);
		driver.findElement(By.xpath("//input[@class='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();

		//Verify Loggin Failure
		//String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\r\n"
		//+ "No customer account found";

		String expectedMessage = "Login was unsuccessful.";
		String actualMessage = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']")).getText();

		//Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage), "PASS");


	}

	@Test
	public void testParallel() {
		System.out.println("Testting Paralel Testing");
	}

	@Test
	public void testParallel2() {
		System.out.println("Testting Paralel Testing222");
	}


	@DataProvider(name="dp")	
	public String[] readJsonData() throws FileNotFoundException { 
		// First parser json object 
		FileReader fReader = new FileReader(".//JsonData/JSON_DATA1.json");
		// JsonObject obj =  JsonParser.parseString(userLogins).getAsJsonObject();
		JsonObject obj =  JsonParser.parseReader(fReader).getAsJsonObject();
		JsonArray jsonUsernamesArray = (JsonArray) obj.get("userlogins"); 
		// Need to add the  json Array to a Java Array 
		String [] usernamesArray = new String[jsonUsernamesArray.size()];

		for(int i=0; i<jsonUsernamesArray.size();i++) 
		{ 
			// extract each element from the array as  each element is an object 
			JsonObject userNamejsonObject = (JsonObject)
					jsonUsernamesArray.get(i); 
			String username =  userNamejsonObject.get("username").toString(); 
			String password =  userNamejsonObject.get("password").toString(); 
			usernamesArray[i] = username + "," + password;

		}

		return usernamesArray;



	}






	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();


	}

}
