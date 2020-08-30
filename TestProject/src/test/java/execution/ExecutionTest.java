package execution;

import java.io.File;
import org.apache.commons.io.FileUtils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import excelFile.ExcelFile;

import pdfMyForm.PdfMyForm;

public class ExecutionTest {
	WebDriver driver;
	String workingDir = System.getProperty("user.dir");
	String url="https://www.pdfmyform.com/demo/";
	String xl = workingDir + "\\ExcelFiles\\FormName.xlsx";
	PdfMyForm pdf;	
  
  @BeforeTest
  @Parameters("browser")
  public void setup(String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			System.setProperty("webdriver.gecko.driver", "C:/Users/ABC/Downloads/geckodriver/geckodriver.exe");
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver=new FirefoxDriver(capabilities);
				
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}/*
		//Check if parameter passed as 'Edge'
				else if(browser.equalsIgnoreCase("Edge")){
					//set path to Edge.exe
					//System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
					//create Edge instance
					driver = new EdgeDriver();
				}
				*/
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.get(url);
		 driver.manage().window().maximize();
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
  @Test
  public void fillForm() throws Throwable {
	  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	  
	  pdf = new PdfMyForm(driver);
	  pdf.verifyTitleofPage();
	  /*
	  String Sheet = "FormName";
	     int rowCount = ExcelFile.getRowCount(xl, Sheet);
	     System.out.println("Row Number"+rowCount);
	     
	     for (int i=1;i<2;i++)

	     {
	    	 String UserName=ExcelFile.getCellValue(xl, Sheet, i, 0);
	    	 System.out.println("Value From Excel"+UserName);
	    	pdf.NameAgeTextBx("Urvashi","24");
	    	} */ //Code Commented is to read data from Excel File 
	    	 pdf.NameAgeTextBx("Urvashi","24");
		 
	     

	  
	  pdf.SelectRadioBox("Female");
	  pdf.SelectHearAboutDRPdwn("Webmaster Forum");
	  pdf.SelectMultipleDRPdwn("2");
	 // pdf.InputorSelectCheckbox("Input", "Hello");
	  pdf.InputorSelectCheckbox("Both","Hello", "Select option 2");
	  pdf.clickSubmit_Savebtn("Submit");
	 
	}
  @AfterMethod
  public void tearDown(ITestResult result,ITestContext context)
  {
   

  if(ITestResult.FAILURE==result.getStatus())
  {
  try 
  {

  TakesScreenshot ts=(TakesScreenshot)driver;
   

  File source=ts.getScreenshotAs(OutputType.FILE);
   

  FileUtils.copyFile(source, new File(System.getProperty("user.dir")+ "./Screenshots/"+result.getName()+".png"));
   
  
  } 
  catch (Exception e)
  {
   
  System.out.println("Exception while taking screenshot "+e.getMessage());

  }
   
   
  }
  
  }

@AfterTest
public void closeBrowser()
{
	driver.quit();
}
  
}
