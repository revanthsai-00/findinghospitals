package main;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

//Finding Top cities in Diagnostic Page.

public class Automatetopcities extends BaseUI {

	// clicking on the diagnostic page.
	public void diagnostic() {
		clickElement("diagnostic_xpath");
	}

	@Test// listing the topcities on diagnostic page.
	public void ListCities() {
		List<WebElement> topCity = (List<WebElement>) getElements("city_xpath");
		System.out.println("*************************List Of Top Cities**********************");
		System.out.println("****************************************************************");
		for (WebElement city : topCity) {

			System.out.println(city.getText());

		}
	}

	// navigating back
	public void back() {
		driver.navigate().back();
	}

	public static AutomateCopWellness nextPage() {
		// sending driver to next page
		return PageFactory.initElements(driver, AutomateCopWellness.class);
	}

}
