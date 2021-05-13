package main;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseUI {

	public static Properties prop;
	static WebDriver driver = null;
	public static String data[];
	final String baseUrl = "https://www.practo.com/";

	// Open the Practo website using specified browser
	public void openBrowserAndNavigateToPracto(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		/*	ChromeOptions co=new ChromeOptions();
		   // co.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});
		   // co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		    co.setAcceptInsecureCerts(false);
		    co.addArguments("--disable-blink-features=AutomationControlled");
		    co.addArguments("--disable-browser-side-navigation" );
		    co.setPageLoadStrategy(PageLoadStrategy.NONE);
		    co.addArguments("--disable-features=VizDisplayCompositor");
	       	    driver=new ChromeDriver(co);*/
			driver = new ChromeDriver();
			if (prop == null) {
				prop = new Properties();
				try {
					FileInputStream file = new FileInputStream("C://Users//REVANTH//workspace//findinghospital//src//ObjectRepository//projectConfig");
					prop.load(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			driver.manage().window().maximize();
			driver.get(baseUrl);
			// Verify if correct page is loaded
			String pageTitle = driver.getTitle();
			Assert.assertEquals(pageTitle,
					"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		}
		// Automate using firefox browser
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			if (prop == null) {
				prop = new Properties();
				try {
					FileInputStream file = new FileInputStream("C://Users//REVANTH//workspace//findinghospital//src//ObjectRepository//projectConfig");
					prop.load(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			driver.manage().window().maximize();
			driver.get(baseUrl);
			// Verify if correct page is loaded
			String pageTitle = driver.getTitle();
			Assert.assertEquals(pageTitle,
					"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		}
		else if(browser.equalsIgnoreCase("chromeGrid")){
			DesiredCapabilities dr=null;
	        dr=DesiredCapabilities.chrome();
	        dr.setBrowserName("chrome");
	        dr.setPlatform(Platform.WINDOWS);
	             
	        driver=new RemoteWebDriver(new URL("http://localhost:9999/wd/hub"), dr);
		}
	}

	// Enter text into a field
	public void enterText(String nameKey, String text) {
		getElement(nameKey).sendKeys(text);
	}

	// Click a web element
	public void clickElement(String locatorKey) {

		getElement(locatorKey).click();

	}

	// Identify and return webelement on a page

	public WebElement getElement(String locatorKey) {
		WebElement element = null;

		if (locatorKey.endsWith("_xpath")) {
			element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_partialLinkText")) {
			element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_name")) {
			element = driver.findElement(By.name(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_id")) {
			element = driver.findElement(By.id(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_CssSelector")) {
			element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
		}
		return element;

	}

	public List<WebElement> getElements(String locatorKey) {
		List<WebElement> elements = null;
		if (locatorKey.endsWith("_xpath")) {
			elements = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		}
		return elements;
	}

	// Close all instances of the browser
	public void closeBrowser() {
		driver.quit();
	}

}
