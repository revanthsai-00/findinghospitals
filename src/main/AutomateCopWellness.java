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

//Capturing the warning message for invalid details
public class AutomateCopWellness extends BaseUI {
	int itemCount = 0;
	String searchPageId, itemPageId;

	// click on provider.
	public void provider() {

		clickElement("Provider_xpath");
	}

	// click on corporate wellness.
	public void copwell() {
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.visibilityOfElementLocated(By.partialLinkText(prop.getProperty("co_partialLinkText"))));
		clickElement("co_partialLinkText");
	}

	// navigating to next page
	public static AutomateCopWellness1 nextPage() {
		// sending driver to next page
		return PageFactory.initElements(driver, AutomateCopWellness1.class);
	}
}