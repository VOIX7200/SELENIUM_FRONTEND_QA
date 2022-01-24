package com.demo.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RefreshPage {
	
	static WebDriver driver;

	public static void main(String[] args) {
    WebDriverManager.chromedriver().setup();    
    driver = new ChromeDriver();
    String reportPage ="file:///C:/Users/E%20Anya/eclipse-workspace/SELLENIUM_EXAMPLES/target/surefire-reports/emailable-report.html";
    driver.get(reportPage);

	}

}
