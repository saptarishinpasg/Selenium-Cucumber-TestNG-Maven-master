package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebConnector.webconnector;
import static WebConnector.webconnector.driver;
import java.io.IOException;

public class Login {
	webconnector wc=new webconnector();
    
	@FindBy(xpath = "//a[text()='Log in']")
	public WebElement LoginLnk;
    
	@FindBy(xpath = "//input[@id='Email']")
	public WebElement EmailTxt;
    
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement PasswordTxt;
    
	@FindBy(xpath = "//input[@value='Log in']")
	public WebElement LoginBtn;
    
	@FindBy(xpath = "//a[@href='/customer/info' and contains(text(), '@gmail.com')]")
	public WebElement CustomerLnk;
	
	public Login() {
		PageFactory.initElements(wc.driver, this);
		System.out.println("Inside Homepage Constructor");	
	}
	
    public void performLogin() throws Exception {
    	LoginLnk.click();
    	EmailTxt.sendKeys(wc.testData.get("Email"));
    	PasswordTxt.sendKeys(wc.testData.get("Password"));
    	LoginBtn.click();
    }

    public void verifyLogin() throws Exception {
    	CustomerLnk.isDisplayed();
    }
}
