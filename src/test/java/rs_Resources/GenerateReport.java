package rs_Resources;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerateReport {
    public static void generateCucumberReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
       // jsonFiles.add("target/cucumber-reports/CucumberTestReport.json");
        jsonFiles.add("target/jsonReports/cucumber-report.json");
        Configuration configuration = new Configuration(reportOutputDirectory, "Rest Assured API Tests");
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Test Type", "API Testing");
        configuration.addClassifications("Environment", "QA");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
