package RunTest;


import java.io.*;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import main.AutomateCopWellness;
import main.AutomateCopWellness1;
import main.AutomateFindHospitals;
import main.Automatetopcities;
import main.BaseUI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.beust.jcommander.Parameter;

public class TestClass  {
	
	ExtentReports report=ExtentReportManager.getReportInstance();
  
	  
	  
	  public void open(String browser) throws Exception 
		{
		  	BaseUI ba=new BaseUI();
			ba.openBrowserAndNavigateToPracto(browser);
		
		}
	  
	  
	
	  
	  @Parameters("browser")
	  @Test(priority=1,groups = {"smoke"})
	  public void getHospitalNames(String browser) throws Exception
	  {	
		  //FileIO.readExcelData()
		  open(browser);
		  ExtentTest logger=report.createTest("getHospitalNames");
		  AutomateFindHospitals ah=new AutomateFindHospitals();
		  logger.log(Status.INFO, "Searching for hospitals");
		  ah.SearchHospitals();
		  logger.log(Status.INFO, "Click on 24x7 CheckBox");
		  ah.Hospitalsopen();
		  logger.log(Status.INFO, "Apply has parking filter");
		  ah.applyfilter();
		  logger.log(Status.INFO, "Get List of hospitals");
		  ah.ListHospitals();
		  logger.log(Status.PASS, "Test Executed Successfully");
		 
	  }
	  
	  @AfterTest
	  public void endReport()
	  {
		  report.flush();
	  }
	  
	  @Test(priority = 2)// , dependsOnMethods = {"getHospitalNames"}, groups = {"smoke"})
	  public void Diagnosticpage()
	  {
		  Automatetopcities ct= AutomateFindHospitals.nextPage();
		  ExtentTest logger=report.createTest("Diagnostic Page");
		  logger.log(Status.INFO, "Click diagnostics button");
		  ct.diagnostic();
		  logger.log(Status.INFO, "Get List of Cities");
		  ct.ListCities();
		  logger.log(Status.INFO, "Navigating to the previous page");
		  ct.back();
		  logger.log(Status.PASS, "Test Executed Successfully");
	  }
  
	  @Test(priority = 3)// , dependsOnMethods = {"Diagnosticpage"}, groups = {"regression"})
	  public void CopWelleness() throws IOException, InterruptedException
	  {
		  ExtentTest logger=report.createTest("CopWellness");
		  AutomateCopWellness cw= Automatetopcities.nextPage();
		  logger.log(Status.INFO, "Click provider button");
		  cw.provider();
		  logger.log(Status.INFO, "Click Corporate Providers button");
		  cw.copwell();
		  
		  AutomateCopWellness1 cw1= AutomateCopWellness.nextPage();
		  cw1.newwindow();
		  logger.log(Status.INFO, "Entering provided details");
		  cw1.enterdetails();
		  logger.log(Status.INFO, "Captured alert message and accept the alert");
		  cw1.alertBox();
		  logger.log(Status.INFO, "Captured the screeshot");
		  cw1.screenshoot();
		  logger.log(Status.PASS, "Test Executed Successfully");
		  close();
	  }
	
	  
	  public void close()
	  {
			BaseUI ba=new BaseUI();
			ba.closeBrowser();
	  }
}
