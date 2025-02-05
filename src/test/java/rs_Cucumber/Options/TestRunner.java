package rs_Cucumber.Options;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import rs_Resources.GenerateReport;
@RunWith(Cucumber.class)
@CucumberOptions(
				features="src/test/java/rs_features",
				plugin ="json:target/jsonReports/cucumber-report.json",
				glue= {"rs_stepDefinitions"},
				tags = "@PlaceValidations"
				)

public class TestRunner {
//tags= {"@DeletePlace"}  compile test verify
// mvn test -Dcucumber.options="--tags @AddPlace"1
// mvn test -Dcucumber.filter.tags="@AddPlace"
//mvn test verify to generate above cucumber report
	
	  @AfterClass
	    public static void generateReport() {
	        GenerateReport.generateCucumberReport();
	    }
}

