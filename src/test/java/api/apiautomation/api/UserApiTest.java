package api.apiautomation.api;

import api.apiautomation.base.BaseTest;
import api.apiautomation.utils.ApiLogger;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.testng.AllureTestNg;
import io.qameta.allure.testng.Tag;

import org.testng.annotations.Listeners;
import io.qameta.allure.*;


@Listeners(AllureTestNg.class)


public class UserApiTest extends BaseTest {

	@Epic("API Automation")
	@Feature("User API")
	@Story("Create User")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Madhav")
	@Tag("Smoke")
	@Tag("UserAPI")
	//@Test(priority = 1)
	@Test(description = "Get a new user using GET /users")

    
    public void getUser() {

        String endpoint = "/users/1";
        int expectedStatus = 200;
        

        Response response =
                given()
                .when()
                    .get(endpoint);

        // 🔹 Logs
        ApiLogger.logResponse("GET", endpoint, response);
        ApiLogger.logResult("GET", endpoint, response, expectedStatus);
        ApiLogger.logResponseBody(response);
        ApiLogger.logOnFailure(response);
        

        // 🔹 Assertions
        response.then()
                .statusCode(expectedStatus)
                .body("id", equalTo(1))
                .body("email", containsString("@"));
    }
    //@Test (priority = 2)
	@Epic("API Automation")
	@Feature("User API")
	@Story("Create User")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Madhav")
	@Tag("Smoke")
	@Tag("UserAPI")
	@Test(description = "Create a new user using POST /users")

    public void postUser() {
    	String endpoint = "/users";
    	int expectedStatus = 201;
    	
    	String payload = """
    			{
    			"name" : "Maddy",
    			"username" : "maddyqa",
    			"email" : "maddy@test.com"
    			}
    			""";
    	
    	Response response = given().header("content-Type", "application/json").body(payload).when().post(endpoint);
    	
    	ApiLogger.logResponse("POST", endpoint, response);
    	ApiLogger.logResult("POST",endpoint, response, expectedStatus);
    	ApiLogger.logOnFailure(response);
    	ApiLogger.logResponseBody(response);
    	
    	response.then().statusCode(expectedStatus).body("name", equalTo("Maddy")).body("email", equalTo("maddy@test.com"));
    	
    }
	@Epic("API Automation")
	@Feature("User API")
	@Story("Create User")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Madhav")
	@Tag("Smoke")
	@Tag("UserAPI")
    //@Test (priority =3)
	@Test(description = "Update a new user using PUT /users")

    public void putUser() {
    	String endpoint = "/users/1";
    	int expectedStatus = 200;
    	
    	String payload = """
    			{
    			"name" : "Arjun",
    			"username": "arjunqa",
    			"email": "arjun@test.com"
    			}
    			""";
    	Response response = given().header("Content-Type", "application/json").body(payload).when().put(endpoint);
    	
    	ApiLogger.logResponse("PUT", endpoint, response);
    	ApiLogger.logResult("PUT", endpoint, response, expectedStatus);
    	ApiLogger.logOnFailure(response);
    	ApiLogger.logResponseBody(response);
    	
    	response.then().statusCode(expectedStatus).body("name", equalTo ("Arjun")).body("email",  equalTo("arjun@test.com"));
    }
    //@Test (priority = 4)
	@Epic("API Automation")
	@Feature("User API")
	@Story("Create User")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Madhav")
	@Tag("Smoke")
	@Tag("UserAPI")
	@Test(description = "Create a new user using DELETE /users")

    public void deleteUser() {
    	String endpoint = "/users/1";
    	int expectedStatus = 200;
    	
    	Response response = given().when().delete(endpoint);
    	
    	ApiLogger.logResponse("DELETE", endpoint, response);
    	ApiLogger.logResult("DELETE", endpoint, response, expectedStatus);
    	ApiLogger.logOnFailure(response);
    	ApiLogger.logResponseBody(response);
    	
    	response.then().statusCode(expectedStatus);
    }
}
