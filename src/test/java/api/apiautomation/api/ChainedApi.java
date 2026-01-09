package api.apiautomation.api;

import api.apiautomation.base.BaseTest;
import api.apiautomation.utils.ApiLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ChainedApi extends BaseTest{
	 private static int userId;  
	//@Test
	 @Epic("API Automation")
		@Feature("User API")
		@Story("Create User")
		@Severity(SeverityLevel.CRITICAL)
		@Owner("Madhav")
		@Tag("Smoke")
		@Tag("UserAPI")
	@Test(description = "Create a new user using POST /users")

	public void createUser() {
		String endpoint = "/users";
		Allure.step("URL: " + endpoint);
		int expectedStatus = 201;
		
		String payload = """
				{
				"name" : "Madhav",
				"role" : "QA Trainee",
				"email" : "madhav@test.com"
				}
				""";
		Response response = given().header("Content-Type" , "application/json").body(payload).when().post(endpoint);
		
		ApiLogger.logResponse("POST", endpoint, response);
		ApiLogger.logResult("POST", endpoint, response, expectedStatus);
		ApiLogger.logOnFailure(response);
		ApiLogger.logResponseBody(response);
		
		response.then().statusCode(expectedStatus);
		
		userId = response.jsonPath().getInt("id");
		System.out.println("Created UserID: " + userId);
	}
	
	 @Epic("API Automation")
		@Feature("User API")
		@Story("Create User")
		@Severity(SeverityLevel.CRITICAL)
		@Owner("Madhav")
		@Tag("Smoke")
		@Tag("UserAPI")
	@Test(dependsOnMethods = "createUser", description = "Get a new user using GET /users")
	public void getUser() {

	    String endpoint = "/users/" + userId;
	    
	    int expectedStatus = 404 ;

	    Response response =
	            given()
	            .when()
	                .get(endpoint);

	    ApiLogger.logResponse("GET", endpoint, response);
	    ApiLogger.logResult("GET", endpoint, response, expectedStatus);
	    ApiLogger.logResponseBody(response);

	    response.then()
	            .statusCode(expectedStatus);
	}
	 @Epic("API Automation")
		@Feature("User API")
		@Story("Create User")
		@Severity(SeverityLevel.CRITICAL)
		@Owner("Madhav")
		@Tag("Smoke")
		@Tag("UserAPI")

	@Test(dependsOnMethods = "createUser", description = "Update a new user using PUT /users")
	public void putUser() {
		String endpoint = "/users/" + userId;
		int expectedStatus = 500;
		
		String payload = """
				{
				"name" : "Arjun",
				"role" : "QA Engineer",
				"email" : "arjun@test.com"
				}
				""";
		Response response = given().header("Content-Type", "application/json").body(payload).when().put(endpoint);
		ApiLogger.logResponse("PUT", endpoint, response);
		ApiLogger.logResult("PUT", endpoint, response, expectedStatus);
		ApiLogger.logOnFailure(response);
		ApiLogger.logResponseBody(response);
		
		response.then().statusCode(expectedStatus);
		
	}
}