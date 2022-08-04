package stepdefs;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ApplicationPages.Homepage;
import WebConnector.webconnector;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import listeners.ExtentReportListener;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class HomePageSteps extends ExtentReportListener 
{
	public Homepage homePage;  
	
	webconnector wc=new webconnector();
	public WebDriver driver;
	
	public HomePageSteps() 
	{
		this.homePage = new Homepage();
	}
	public HomePageSteps(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
    @BeforeStep
	public void beforeStep() throws InterruptedException 
	{
	Thread.sleep(2000);
	}
	
	@Given("^User searches for a text$")
	public void searchTextInput() throws InvalidFormatException, IOException, ClassNotFoundException {
		try {
			test = extent.createTest(Feature.class, "Home Page Verifications");                         
			test=test.createNode(Scenario.class, "Check Blog Link displayed");                       
			logInfo=test.createNode(new GherkinKeyword("Given"), "User searches for a text");
			homePage.searchText();
			logInfo.pass("User searches for a text");
			logInfo.addScreenCaptureFromPath(captureScreenShot(wc.driver));
			Thread.sleep(5000);
		}
		catch (AssertionError | Exception e) {
			System.out.println("Assertion Error");
			testStepHandle("FAIL",driver,logInfo,e);            
		}
	}

	@Then("^Verify search result$")
	public void verifyTextResult() {
		try {                     
			logInfo=test.createNode(new GherkinKeyword("Then"), "Verify search result");
			homePage.verifyResult();
			logInfo.pass("Verify search result");
			logInfo.addScreenCaptureFromPath(captureScreenShot(wc.driver));
			Thread.sleep(5000);
		}
		catch (AssertionError | Exception e) {
			System.out.println("Assertion Error");
			testStepHandle("FAIL",driver,logInfo,e);            
		}
	}
    
//    @AfterClass
//    public void after(Scenario scenario)
//    {
//    closeDriver(scenario);
//    }
}
