package pdfMyForm;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class PdfMyForm {
	 WebDriver driver;
	
	//Identifying all the webelements on the web Page
	@FindBy(xpath="//input[@name='textinput']")
	public static WebElement Name1;
	
	@FindBy(id= "Age")
	public static WebElement Age;
	
	@FindBy(xpath= "//input[@value='Male']")
	public static WebElement MaleRadioBox;
	
	
	@FindBy(xpath= "//input[@value='Female']")
	public static WebElement FemaleRadioBox;
	
	@FindBy(id= "selectbasic")
	public static WebElement Hearaboutdrpdwn;
		
	@FindBy(id= "selectmultiple")
	public static WebElement FeatureDrpdwn;
	
	@FindBy(xpath= "//input[@value='Input field']")
	public static WebElement inputfieldchkbx;
	
	
	@FindBy(xpath= "//input[@value='Input field']/following::input[@id='textinput']")
	public static WebElement GoodChbxoiceTxtbx;
	
	
	@FindBy(xpath= "//input[@value='Select field']")
	public static WebElement selectfieldchkbx;
	
	@FindBy(xpath= "//option[@value='It’s easy-to-use']")
	public static WebElement VALUE1;
	
	@FindBy(xpath= "//option[@value='It has a lot of options']")
	public static WebElement VALUE2;
	
	
	
	@FindBy(xpath= "//a[contains(text(),'Submit')]")
	public static WebElement Submitbtn;
	
	@FindBy(xpath= "//a[contains(text(),'Save as PDF!')]")
	public static WebElement PDFbtn;
	
	 public PdfMyForm(WebDriver driver)
	 {
		 

		 		 this.driver = driver; 
		 		PageFactory.initElements(driver, this);

	        //This initElements method will create all WebElements


	  }

	  public void verifyTitleofPage() throws Exception
	 {
		  System.out.println("Page Title"+driver.getTitle());
		 Assert.assertEquals("HTML form to PDF demo",driver.getTitle());
	 }
	 public static void NameAgeTextBx(String name,String age) throws Exception
	 {
		 
		 Name1.sendKeys(name);//eo Enter text in name textbox
		 Age.sendKeys(age);//To Enter text in Age textbox
	 }
	public void SelectRadioBox(String Value) //Method to click radio button according to user requirement
	{
		if(Value.equalsIgnoreCase("Male"))
		{
			if(MaleRadioBox.isSelected())//To check whether Male RadioBx is selected or not
			{
				System.out.println("Already Selected");//Male Radiobox already selected
			}
			else
			{
				MaleRadioBox.click();//To Select MaleRadiobox
			}
		}
		if(Value.equalsIgnoreCase("Female"))
		{
			if(FemaleRadioBox.isSelected())//To check whether FeMale RadioBx is selected or not
			{
				System.out.println("Already Selected");//FeMale Radiobox already selected
			}
			else
			{
				FemaleRadioBox.click();//To Select FeMaleRadiobox
			}
		}
		
	}
	
	public void SelectHearAboutDRPdwn(String Value) throws Exception
	 {
		Select se=new Select(Hearaboutdrpdwn);// To select value from dropdown
		se.selectByVisibleText(Value);
		
	 }
	
	public void SelectMultipleDRPdwn(String Value) throws Exception
	 {
		Select se=new Select(FeatureDrpdwn);// To select value from dropdown
		if(Value.equalsIgnoreCase("1"))
		{
		se.selectByVisibleText("It’s easy-to-use");
		}
		if(Value.equalsIgnoreCase("2"))
		{
			se.selectByVisibleText("It’s easy-to-use");
			se.selectByVisibleText("It has a lot of options");
		}
	 }
	
	public void InputorSelectCheckbox(String Value,String Text,String Value2) throws Exception
	 {
		if(Value.equalsIgnoreCase("Input")) // To select input checkboxonly
		{
			inputfieldchkbx.click();
			GoodChbxoiceTxtbx.sendKeys(Text);
		}
		
		else if(Value.equalsIgnoreCase("Select"))// To select Select checkboxonly
		{
			selectfieldchkbx.click();
			Select sel= new Select(driver.findElement(By.xpath("//input[@value='Select field']/following::div//select")));
			sel.selectByVisibleText(Value2);
		}
		
		else  if(Value.equalsIgnoreCase("Both"))// To select both checkboxonly
		{
			inputfieldchkbx.click();
			GoodChbxoiceTxtbx.sendKeys(Text);
			selectfieldchkbx.click();
			Select sel= new Select(driver.findElement(By.xpath("//input[@value='Select field']/following::div//select")));
			sel.selectByVisibleText(Value2);

		}
		
		else                       // Did not select any checkbox on form
		{
			System.out.println("No Action");
		}
	 }
	
	public void clickSubmit_Savebtn(String Value) //Click on Submit or Save button on Form
	{
		if(Value.equalsIgnoreCase("Submit"))
		{
			Submitbtn.click();
		}
		else
		{
			PDFbtn.click();
		}
	}
	
	
	
	
	 
	 
	 
}