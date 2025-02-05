package rs_Cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
				features="src/test/java/rs_features",
				plugin ="json:target/jsonReports/cucumber-report.json",
				glue= {"rs_stepDefinitions"}
				)

public class TestRunner {
//tags= {"@DeletePlace"}  compile test verify
}

