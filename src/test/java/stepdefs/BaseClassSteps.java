package stepdefs;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.opencsv.exceptions.CsvException;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import listeners.ExtentReportListener;

public class BaseClassSteps extends ExtentReportListener{
	public static String featureName;
	
	  @Before
		public void before(Scenario scenario) throws MalformedURLException {
		  String featureName= getFeatureFileNameFromScenarioId(scenario);
		  System.out.println("feature name is:"+featureName);
			setUp();  
			setUpDriver();
		}
	    
	     private String getFeatureFileNameFromScenarioId(Scenario scenario) {
		    featureName = "Feature ";
		    String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
		    featureName = StringUtils.substringAfterLast(rawFeatureName, "/").split(".feature")[0];
		    return featureName;
		}
	  
	    @After
	    public void after(Scenario scenario){
	    	clearTestData();
	    	closeDriver(scenario);
	    	extent.flush();
	    }
		
	    @When("^User fetches test data \"(.*)\" from \"(.*)\"$")
		public void userTestData(String testDataName, String testFileName) throws InvalidFormatException, IOException, CsvException {
			fetchTestData(testDataName, testFileName);
		}
	    
	    @Given("^User navigates to \"(.*)\" site \"(.*)\" environment$")
	    public void aUserNavigatesToHomePage(String site, String env) throws InvalidFormatException, IOException {
	        goToSite(site.toLowerCase(), env.toLowerCase());
	    }
}
