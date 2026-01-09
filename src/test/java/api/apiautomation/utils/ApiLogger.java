	package api.apiautomation.utils;
	
	import io.qameta.allure.Allure;
import io.restassured.response.Response;
	
	import java.util.Map;
	
	public class ApiLogger {
	
	    private static final Map<Integer, String> STATUS_TEXT = Map.of(
	            200, "OK",
	            201, "CREATED",
	            204, "NO CONTENT",
	            400, "BAD REQUEST",
	            401, "UNAUTHORIZED",
	            403, "FORBIDDEN",
	            404, "NOT FOUND",
	            500, "INTERNAL SERVER ERROR"
	    );
	
	    public static void logResponse(String method, String url, Response response) {
	
	        int statusCode = response.getStatusCode();
	        String statusText = STATUS_TEXT.getOrDefault(statusCode, "UNKNOWN");
	
	        System.out.println("\n================ API RESPONSE ================");
	        System.out.println("Method        : " + method);
	        System.out.println("URL           : " + url);
	        System.out.println("Status Code   : " + statusCode + " " + statusText);
	        System.out.println("Response Time : " + response.getTime() + " ms");
	        System.out.println("==============================================\n");
	        
	        // Allure steps
	        Allure.step("API Call Details", () -> {
	            Allure.step("HTTP Method: " + method);
	            Allure.step("Endpoint: " + url);
	            Allure.step("Status Code: " + statusCode + " " + statusText);
	            Allure.step("Response Time: " + response.getTime() + " ms");
	        });

	        // Attach response body
	        Allure.addAttachment(
	            "Response Body",
	            "application/json",
	            response.asPrettyString()
	        );
	    }
	    public static void logResult(String method,
                String url,
                Response response,
                int expectedStatus) {

	    	int actualStatus = response.getStatusCode();
	    	String statusText = STATUS_TEXT.getOrDefault(actualStatus, "UNKNOWN");
	    	String result = (actualStatus == expectedStatus) ? "PASS ✅" : "FAIL ❌";

	    	System.out.println("Expected Code : " + expectedStatus);
	    	System.out.println("Actual Code   : " + actualStatus + " " + statusText);
	    	System.out.println("Result        : " + result);
	    	
	    	// Allure validation block
	        Allure.step("Validation", () -> {
	            Allure.step("Expected Status Code: " + expectedStatus);
	            Allure.step("Actual Status Code: " + actualStatus + " " + statusText);
	            Allure.step("Result: " + result);
	        });
}
	    public static void logResponseBody(Response response) {
	        System.out.println("Response Body:");
	        response.getBody().prettyPrint();
	    }


	    public static void logOnFailure(Response response) {
	        if (response.getStatusCode() >= 400) {
	            System.out.println("Response Body:");
	            response.getBody().prettyPrint();
	            Allure.addAttachment(
	                    "Error Response Body",
	                    "application/json",
	                    response.asPrettyString()
	                );
	        }
	    }
	
	}
