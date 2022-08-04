package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import WebConnector.webconnector;
import java.io.IOException;



public class Homepage<InputTxt> extends webconnector 
{
	webconnector wc=new webconnector();
		
	//List of all the Web Elements of this page 
	@FindBy(xpath = "//input[@title='Search for:']")
	public WebElement InputTxt;
	
	@FindBy(xpath = "//input[@value='Go']")
    public WebElement GoBtn;	
	
	@FindBy(xpath = "//h2/a[text()='Hello world!']")
	public WebElement HelloTitle;

	public WebDriver driver;	
	
	
	public Homepage(WebDriver driver) {
		//this.driver = driver;
		//PageFactory.initElements(wc.driver, this);
		}
	
	public Homepage() 
    {
		PageFactory.initElements(wc.driver, this);
		System.out.println("Inside Homepage Constructor");	
	}
	
	public void searchText() throws InvalidFormatException, IOException
    {
    	try	{
	        System.out.println("Now, Get Page Title:"+wc.driver.getTitle());
	        Thread.sleep(2000);
	        System.out.println("Input Text is:"+InputTxt.getAttribute("title"));
	        InputTxt.click();
	        InputTxt.sendKeys(wc.testData.get("InputTxt"));
	        System.out.println("Clicking Hello World");
	        Thread.sleep(2000);                  
	        GoBtn.click();
    	}
    	catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("Inside Catch goToHomePage");
	    	e.printStackTrace();
    	}
    }

	public void verifyResult() {
		HelloTitle.isDisplayed();
	}
   
}