package com.demo.Selenium;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WEBUY_TEST {
	static WebDriver driver;

	//@SuppressWarnings("unlikely-arg-type")
	//public static void main(String[] args) throws JsonSyntaxException, InterruptedException {
	@Test	
	public void weBuyTest() throws InterruptedException{
		// write json file here 

///NOTE:YOU NEED TO PARSE JSON STRINGS INTO JSON OBJECT 1ST & FORMOST
/// AFTER PARSING INTO JSON -- THE ALTIMATE GOAL IS TO PARSER THE DATA IN A JAVA FORMAT

		String userNames1 = "{\"username\":\"john\","
				+ "\"password\":\"password123\"}";
		System.out.println(userNames1);

		/*
		 * String books = "{\"id\": \"01\"," + "\"language\": \"Java\"," +
		 * "\"edition\": \"third\"," + "\"author\": \"Herbert Schildt\"}";
		 */

		String userName2 = "{\"userLogins\":[{\"username\":\"User1\",\"passwrd\":\"paswr1\",\"gender\":\"male\",\"age\":\"12\"},"
				+ "{\"username\":\"User2\",\"passwrd\":\"paswr2\",\"gender\":\"male\",\"age\":\"12\"},"
				+ "{\"username\":\"User3\",\"passwrd\":\"paswr3\", \"gender\":\"male\",\"age\":\"12\"}]}";


		// the parse json file into an object 
		//JsonParser jpasr = new JsonParser();
 
		JsonObject jobj = (JsonObject)JsonParser.parseString(userName2);
		System.out.println("top json size " + jobj.size());
		
		

		JsonArray jsArray = (JsonArray) jobj.get("userLogins");
		//JsonObject jsonP = (JsonObject) JsonParser.parseString(usernames);
		// Scroll Thru The Top Level Object
		Set<String> objSet = jobj.keySet();
		
		for(int i=0; i<jobj.keySet().size();i++) {
			// Note: THIS WILL ONLY LOOP ONCE CUZ THERE IS ONLY ONE JSON KEY & VALUE[WHICH IS AN ARRAY] IN THIS OBJECT]
		  System.out.println("====================================");	
		 
		  //Scroll thru each array in the top level object
		  Object[][] objArray = new Object[jsArray.size()][1];
		  for(int g=0; g<jsArray.size();g++) {
			  //extract each object[ELEMENT] in the array			  
		 JsonObject jobjOfArrays = (JsonObject)jsArray.get(g).getAsJsonObject();			  
			  //Extract the keyset from object
		 Set<String> objOfArrayKeySet =  jobjOfArrays.keySet();	  
			  //Scroll thru each element in the objects extract the elements & put into a hashTable 
		 Hashtable<String,String> jsobjHash = new Hashtable<String,String>();
			//int k=0;  
			for(String e : objOfArrayKeySet) {
				String key =  e;
				String value =  jobjOfArrays.get(e).toString();
				jsobjHash.put(key, value) ;
			}
			objArray[g][0]=jsobjHash;
			//k++;
			//System.out.println((String)((Hashtable)objArray[k][0]).get("username") + "  objectArray");
			  
		  }			
		  System.out.println((String)((Hashtable)objArray[2][0]).get("username") + "  objectArray"); 	
		}
		
		
		
		/**
		 * 
		 * 
		 * 
		 */
		
		
		

		
//========================================================================================
		/*
		 * List<String> usernamels = new ArrayList<String>(); Hashtable<String,String>
		 * passwordHash = new Hashtable<String,String>();
		 * 
		 * //then capture the array // the capture objects out of the array //Add to
		 * List and HashTable
		 * 
		 * //===========================================================================
		 * ============= for(int i=0;i<jsArray.size(); i++) {
		 * System.out.println("array size " + jsArray.size()); JsonObject logins =
		 * (JsonObject) jsArray.get(i).getAsJsonObject();
		 * 
		 * System.out.println(logins + "---" + logins.size() ); String username =
		 * (String) logins.get("username").toString(); String password = (String)
		 * logins.get("passwrd").toString(); usernamels.add(username);
		 * passwordHash.put(username, password);
		 * 
		 * //Print them out same time String uslis = usernamels.get(i);
		 * System.out.println(uslis); String pasrd = passwordHash.get(username);
		 * System.out.println(pasrd); }
		 * 
		 */


		//then capture the array 


		// the capture objects out of the array 


		//Add to List and HashTable


		String books = "{\"id\": \"01\","
				+ "\"language\": \"Java\","
				+ "\"edition\": \"third\","
				+ "\"author\": \"Herbert Schildt\"}";		
		
		String prdstNqty = "{\"4 GB PC12800 DDR3 1600MHz 240 Pin Memory\":2,"
				+ "\"4 GB PC10600 DDR3 1333MHz 240 Pin Memory\":2,"
				+ "\"8 GB PC25600 DDR4 3200MHz 288 Pin Memory\":2,"
				//+ "\"Seagate Barracuda 1TB 3.5 7200RPM SATA\":\"6\","
				+ "\"NVIDIA GeForce GTX 1050 Ti 4GB GDDR5\":2}";
		
		
		// Extract the string into a JsonObject
		List<String> bookInfoName = new ArrayList<String>();
		Hashtable<String,String> bookInfo = new Hashtable<String,String>();
		
		JsonObject booksJsonObject =  JsonParser.parseString(prdstNqty).getAsJsonObject();
		Set<String> booksKeySet = booksJsonObject.keySet();
           for(String e : booksKeySet ) {
        	   String bookInfoNameX = e;
        	   String bookInfoX = booksJsonObject.get(bookInfoNameX).toString();
        	   System.out.println("bookinfox & bookInfoName "+bookInfoNameX + "----" + bookInfoX);
        	   bookInfoName.add(bookInfoNameX);
        	   bookInfo.put(bookInfoNameX, bookInfoX);
           }

     System.out.println("print the hash table & list " + bookInfo.get("Computing/ DDR3 - Desktop 240 Pin"));
     System.out.println("print the list " + bookInfoName.get(2));
  // BRING IN SELENIUM WEBDRIVER      
   // WebDriver driver;
   WebDriverManager.chromedriver().setup();
 	//WebDriverManager.edgedriver().setup();
 	driver = new ChromeDriver();
 	//driver = new EdgeDriver();
 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
 	driver.manage().window().maximize();
 	WEBUY_TEST jsd = new WEBUY_TEST();
 //GO TO LOGIN URL AND LOGIN IN
 	jsd.getUrl("https://uk.webuy.com/");
 	
 //Click Sign In
 	WebElement clickSignIn = driver.findElement(By.cssSelector("#signIn"));
 	jsExecution(clickSignIn);
 //Enter UserName and PassWord & Click Submit
 	//userName  steveedm@mail.com pwd = Passgate123# 07916782809
 	driver.findElement(By.cssSelector("#user")).sendKeys("steveedm@mail.com");
 	driver.findElement(By.cssSelector("#pass")).sendKeys("Passgate123#");
 	Thread.sleep(5000);
 	//WebElement submitLogin = driver.findElement(By.cssSelector("#loginLink"));
 	//jsExecution(submitLogin);
 	//Thread.sleep(60000);
 //NAVIGATE TO GAMING (use below URL unless URL is DIff after Login
 	
 	//WebElement goToGamin = driver.findElement(By.xpath("//div[@id='topMenuContainer']//li[3]/a"));
 	//jsExecution(goToGamin);
  //GO TO THE URL 
 //	driver.get("https://uk.webuy.com/search?superCatId=3&superCatName=Computing");
 	
 jsd.getUrl("https://uk.webuy.com/search?superCatId=3&superCatName=Computing");	
 	
 	//EXTRACT ALLNAMES AND BUTTONS  //.savdiv > a
 	
 	//List<WebElement> allNames = driver.findElements(By.cssSelector(".superCatLink"));
 	List<WebElement> allNames = driver.findElements(By.cssSelector(".savdiv > a"));
 	for(int k =0; k<allNames.size(); k++) {
 		System.out.println("printing allName "+allNames.get(k).getText());
 		
 	}
 	
 	
 	List<WebElement> allButtons = driver.findElements(By.cssSelector(".buyNowButton"));
 	Thread.sleep(5000);
 	System.out.println("allname size " + allNames.size());
 	System.out.println("allbuttons size " + allButtons.size());
 	System.out.println("allbuttons size " + allButtons.get(0).getText());
 	
 	// driver.findElement(By.cssSelector(".buyNowButton")).click();
 	Thread.sleep(5000);
 	 WebElement webbutton =  driver.findElement(By.xpath("//a/div[@class='buyNowButton']"));
 	jsExecution(webbutton);
 	 
 	// Adding products to basket 
 	//If you want to count the number of products added then use prodCount (variable)
 	for(int i=0; i<allNames.size(); i++) {
 		//if(bookInfoName.contains(allNames.get(i).getText()) || bookInfoName.contains(allNames.get(i).getText().startsWith("Seagate Barracuda 1TB 3.5")) ) {
 		if(bookInfoName.contains(allNames.get(i).getText())) {
 			 	 
 			// if(((WebElement) allNames.get(i)).getText().equals(bookInfoName)) {	
 			System.out.println("------b4 click button");
 			
 			WebElement webbutton1=	allButtons.get(i);
 			jsExecution(webbutton1);
 		};
 		
 	}
 	// Click on View Basket
 	WebElement viewBasketButt=driver.findElement(By.xpath("//tr[@id='buyBasketRow']/td[2]/a"));
 	jsExecution(viewBasketButt);
 	
 	//Extract the First colume items  	
 	List<WebElement> firstCol = driver.findElements(By.xpath("//div[@class='clickAndCollect chktableout']//tbody//td/a"));
 	for(int fg=0; fg<firstCol.size(); fg++) {
 		
 		System.out.println(" display firstCol Data " + firstCol.get(fg).getText());
 		
 	}
 	
 	
 	//Extract the selects boxes [to enable qty update 
 	List<WebElement> select = driver.findElements(By.xpath(" //td[@class='txtcenter']/select"));
 	System.out.println("display select size " + select.size() );
 	for(int jp=0; jp<select.size();jp++) {
 		String key = firstCol.get(jp).getText();
 		WebElement dropdown = select.get(jp);
 		//if(jp==1) {
 		Select s = new Select(dropdown); 
 		System.out.println("display bookinfo.get key " + bookInfo.get(key) );
 		Thread.sleep(5000);
 			s.selectByVisibleText(bookInfo.get(key));
 		//}
 			select = driver.findElements(By.xpath(" //td[@class='txtcenter']/select"));
 			firstCol = driver.findElements(By.xpath("//div[@class='clickAndCollect chktableout']//tbody//td/a"));
 	}
 	
 	
 	//Calculating Total Price And Delivery Price
 	double sumItems =0.0;
 	double sumDel = 0.0;
 	List<WebElement> totalRows = driver.findElements(By.xpath("//div[@class='clickAndCollect chktableout']/table/tbody/tr"));
 	
 	List<WebElement> forthCol  = driver.findElements(By.xpath("//div[@class='clickAndCollect chktableout']/table/tbody/tr/td[4]"));	
 	List<WebElement> fifthCol  = driver.findElements(By.xpath("//div[@class='clickAndCollect chktableout']/table/tbody/tr/td[5]/label[1]/p/span"));
 	
 	System.out.println("display sum - 4th Col " + forthCol.size());
 	System.out.println("display sum - 5th Col " + fifthCol.size());
 	
 	//String delCharges = driver.findElement(By.xpath("//div[@class='basketPageBox']/form/table/tbody/tr[\"+(totalRows-1)+\"]/td[2]")).getText();
 	
 	for(int i=0; i<forthCol.size();i++ ) {
 	 String forthColVal = forthCol.get(i).getText().split("\\£")[1];
 	String fifthColVal;
 	System.out.println("Display fifthCol.get(i).getText() " + fifthCol.get(i).getText());
 	 if(fifthCol.get(i).getText().equals("FREE")) {
 		 System.out.println(" Control inside if fifthCol.get(i).getText() == FREE");
   	  fifthColVal="0.00";
	}else {
		fifthColVal = fifthCol.get(i).getText().split("\\£")[1];
	}
   
 	 //============================================================
 	 sumItems = sumItems + Double.parseDouble(forthColVal);
 	 System.out.println("display sumItems " + sumItems);
 	 
 	 //============================================================
 	 sumDel = sumDel + Double.parseDouble(fifthColVal);
 	 System.out.println("display sumDel " + sumDel);
 	
 	   }
 	double expectedCharges = sumItems + sumDel;
 	System.out.println(" Display expectedCharges " + expectedCharges );
 	
 	
 // Extract Actual Delivery Charges & Subtotal & Grand Total 
	
 	String actualDelCharge = driver.findElement(By.xpath("//div[@class='leftTextCls'][1]//span[1]")).getText();
 	double actualDelChargeTx = Double.parseDouble(actualDelCharge.split("\\£")[1]);
 	String actualSubTotal = driver.findElement(By.xpath("//div[@class='leftTextCls'][2]//span[1]")).getText();
 	double actualSubTotalTx = Double.parseDouble(actualSubTotal.split("\\£")[1]);
 	String actualGrandTotal = driver.findElement(By.xpath("//div[@class='leftTextCls'][3]//span[1]")).getText();
 	double actualGrandTotalTx = Double.parseDouble(actualGrandTotal.split("\\£")[1]);
 	
 	System.out.println("Display actualDelCharge + actualSubTotal + actualGrandTotal " + actualDelCharge+ "---" +actualSubTotal+ "--" + actualGrandTotal );
 	 
 	double actualCharges = actualDelChargeTx + actualSubTotalTx;
	double actualGrandTotalFinal = actualGrandTotalTx;
	
	 if(expectedCharges==actualCharges & expectedCharges == actualGrandTotalFinal ) {
		 System.out.println(" TEST IS PASS");
	 }else {
		 System.out.println("TEST IS FAILED");
	 }
	 
	 Thread.sleep(5000);
	 //Make Payment -- click on pay
	 driver.findElement(By.cssSelector(".cex_continueBar a:nth-child(2)")).click();
	// Thread.sleep(5000);
	 
	 driver.findElement(By.xpath("//input[@id='user']")).sendKeys("steveedm@mail.com");
	 driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Passgate123#");
	 Thread.sleep(5000);
	 WebElement login= driver.findElement(By.xpath("//div[@class='loginLinks']/a"));
	 jsExecution(login);
	 
	 Thread.sleep(5000);
	// WebElement gotoBasket = driver.findElement(By.xpath("//button [@class='button-primary']"));
	// jsExecution(gotoBasket);
	// Thread.sleep(20000);
	 String reportPage ="file:///C:/Users/E%20Anya/eclipse-workspace/SELENIUM_FRONTEND_QA/target/surefire-reports/emailable-report.html";
	 Thread.sleep(10000);
	 jsd.getUrl(reportPage);
	// driver.navigate().back(); 
	 // Thread.sleep(1000); 
	 // driver.navigate().forward();
	// driver.navigate().to(driver.getCurrentUrl());
	// driver.navigate().refresh();
	 
	}
	
	
	
	
	
	
	
public static void jsExecution(WebElement element){
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", element);
		 
		
	}
 	
	public void getUrl(String url) throws InterruptedException {
		
		driver.navigate().back(); 
		  Thread.sleep(1000);
		  driver.get(url);
		  driver.navigate().back();
		  driver.navigate().refresh();
		  driver.navigate().to(url);
		 // driver.navigate().back();
		 // driver.navigate().refresh();
		 // driver.navigate().forward();
	}

}
