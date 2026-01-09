package api.apiautomation.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import api.apiautomation.utils.ConfigReader;

public class BaseTest {
	
	@BeforeSuite
	public void setupAllureEnvironment() throws IOException {
	    Path allureResults = Paths.get("target/allure-results");
	    Files.createDirectories(allureResults);

	    Files.writeString(allureResults.resolve("environment.properties"),
	        "Environment=QA\nBaseURL=https://jsonplaceholder.typicode.com\nTester=Madhav");

	    Files.writeString(allureResults.resolve("executor.json"),
	            """
	            {
	              "name": "Local Maven Execution",
	              "type": "maven",
	              "buildName": "API Automation Build",
	              "buildOrder": 1,
	              "reportName": "Rest-Assured API Test Report"
	            }
	            """
	        );	}

	@BeforeMethod
	public void setup() {

	    Allure.step("Initialize API test setup", () -> {

	        String baseUri = ConfigReader.get("baseURI");
	        RestAssured.baseURI = baseUri;

	        Allure.step("Base URI configured: " + baseUri);

	        Allure.step("Default headers configured");
	        Allure.step("Test execution environment ready");
	    });
	}

}
