package stepdefs;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

import ApplicationPages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import listeners.ExtentReportListener;
import WebConnector.webconnector;

public class LoginSteps extends ExtentReportListener {
    private Login login;
	private String scenDesc;
	
	webconnector wc=new webconnector();
	
    public LoginSteps() {
        this.login = new Login();
    }
	
    @And("^User with valid credential logs in")
    public void userLogin() throws Exception {
		try {
			test = extent.createTest(Feature.class, "Perform Login DemoWebshop");                         
			test=test.createNode(Scenario.class, "Verify account login");                       
			logInfo=test.createNode(new GherkinKeyword("Given"), "User with valid credential logs in");
			login.performLogin();
			logInfo.pass("User with valid credential logs in");
			logInfo.addScreenCaptureFromPath(captureScreenShot(wc.driver));
			Thread.sleep(5000);
		}
		catch (AssertionError | Exception e) {
			System.out.println("Assertion Error");
			testStepHandle("FAIL",driver,logInfo,e);            
		}
    }
    
    @Then("^User account is logged in")
    public void verifyUserLogin() throws Exception {
    	
		try {                    
			logInfo=test.createNode(new GherkinKeyword("Then"), "User account is logged in");
			login.verifyLogin();
			logInfo.pass("User account is logged in");
			logInfo.addScreenCaptureFromPath(captureScreenShot(wc.driver));
			Thread.sleep(5000);
		}
		catch (AssertionError | Exception e) {
			System.out.println("Assertion Error");
			testStepHandle("FAIL",driver,logInfo,e);            
		}
    	
    }
}