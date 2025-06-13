package StepDefinition;
import io.cucumber.java.en.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import Utilities.JsonReader;
import org.json.simple.JSONObject;
import java.util.Random;


import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostPositive_CreateUsers {
	
	private RequestSpecification requestSpec;
	private Response response;
	public static int createdUserId = 0; // Declare this at the class level
	private String expectedEmail;
	private long expectedContact;
	private String expectedFirstName;
	private String expectedLastName;
	
	 public String readJsonFile(String filePath) throws IOException {
	        return new String(Files.readAllBytes(Paths.get(filePath)));
	 }
	 
	@Given("User prepares the POST request with BaseURL and valid endpoint")
	public void preparePostRequest() {
		 requestSpec = Hooks.requestSpec;
	}

	@When("User sends the POST request with all required and optional fields")
	public void sendPostRequest() throws IOException {
		JSONObject jsonObject = JsonReader.getJsonObject("src/test/resources/user1.json");
		if (jsonObject == null) {
		    System.out.println("JSON object is null — check path or file contents!");
		} else {
		    // Generate unique email with timestamp
		    String uniqueEmail = "joy" + System.currentTimeMillis() + "@example.com";

		    // Generate unique 10-digit contact number starting with 9
		    Random rand = new Random();
		    long uniqueContactNumber = 9000000000L + (long)(rand.nextDouble() * 1000000000L);

		    // Update email and contact number in JSON
		    jsonObject.put("userEmailId", uniqueEmail);
		    jsonObject.put("userContactNumber", uniqueContactNumber);

		    System.out.println("JSON object loaded and updated: " + jsonObject.toJSONString());
		
		
		// Store expected values for validation
	    expectedEmail = uniqueEmail;
	    expectedContact = uniqueContactNumber;
	    expectedFirstName = jsonObject.get("userFirstName").toString();
	    expectedLastName = jsonObject.get("userLastName").toString();
		}
		
		String jsonBody = jsonObject.toJSONString(); // Convert JSONObject to JSON string

		response = requestSpec
		    .header("Content-Type", "application/json")
		    .auth()
		    .preemptive()
		    .basic("Numpy@gmail.com", "userapi@2025")
		    .body(jsonBody)
		    .post("/uap/createusers");

		int statusCode = response.statusCode();
		System.out.println("Actual Status Code: " + statusCode);
		System.out.println("Response Body: " + response.getBody().asString());
		
		createdUserId = response.jsonPath().getInt("userId");
		 Assert.assertTrue(createdUserId > 0, "User ID should be greater than 0");
		 //.get("/uap/getuser/" + PostPositive_CreateUsers.createdUserId); - to use in GET
	}
	
	@Then("User receives 201 Created status with matching fields")
	public void validateResponsefields() {
		int statusCode = response.getStatusCode();
	    System.out.println("Actual Status Code: " + statusCode);
	    System.out.println("Response Body: " + response.getBody().asString());
	    
	    Assert.assertEquals(statusCode, 201, "Expected status code 201 but got " + statusCode);
	    
	    String actualEmail = response.jsonPath().getString("userEmailId");
	    long actualContact = response.jsonPath().getLong("userContactNumber");
	    String actualFirstName = response.jsonPath().getString("userFirstName");
	    String actualLastName = response.jsonPath().getString("userLastName");

	    Assert.assertEquals(actualEmail, expectedEmail, "Email ID does not match");
	    Assert.assertEquals(actualContact, expectedContact, "Contact Number does not match");
	    Assert.assertEquals(actualFirstName, expectedFirstName, "First Name does not match");
	    Assert.assertEquals(actualLastName, expectedLastName, "Last Name does not match");
	
	}

	@Then("User receives 201 Created status")
	public void validateResponse() {
		int statusCode = response.getStatusCode();
	    System.out.println("Actual Status Code: " + statusCode);
	    System.out.println("Response Body: " + response.getBody().asString());
	    
	    Assert.assertEquals(statusCode, 201, "Expected status code 201 but got " + statusCode);
	}
	
	@When("User sends the POST request with only mandatory fields")
    public void user_sends_the_post_request_with_only_mandatory_fields() throws IOException {
        JSONObject jsonObject = JsonReader.getJsonObject("src/test/resources/user2.mandatory.json");

        if (jsonObject == null) {
            throw new RuntimeException("Mandatory fields JSON is null.");
        }

        String jsonBody = jsonObject.toJSONString();
        System.out.println("Request JSON (mandatory only): " + jsonBody);

        response = requestSpec
                .header("Content-Type", "application/json")
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .body(jsonBody)
                .post("/uap/createusers");

        logResponse();
    }


	// Validate user_id is present
	@Then("User sees autogenerated user_id in the response body")
	public void user_sees_autogenerated_user_id_in_the_response_body() {
	    int userId = response.jsonPath().getInt("userId");
	    Assert.assertTrue(userId > 0, "userId should be present and greater than 0");
	    System.out.println("userId = " + userId);
	}

	// Validate addressID is present
	@Then("User sees autogenerated addressID in the response body")
	public void user_sees_autogenerated_address_id_in_the_response_body() {
	    int addressId = response.jsonPath().getInt("userAddress.addressId");
	    Assert.assertTrue(addressId > 0, "addressId should be present and greater than 0");
	    System.out.println("addressId = " + addressId);
	}

	// Validate content-type header
	@Then("User receives Content-Type as application/json with status code 201")
	public void validateContentTypeAndStatus() {
	    int actualStatusCode = response.statusCode();
	    String contentType = response.header("Content-Type");

	    Assert.assertEquals(actualStatusCode, 201, "Expected status code 201");
	    Assert.assertTrue(contentType.contains("application/json"), "Expected Content-Type application/json");
	    System.out.println("Content-Type: " + contentType);
	}

	// Validate status code
	@Then("User receives status code 201")
	public void user_receives_status_code_201() {
	    Assert.assertEquals(response.getStatusCode(), 201);
	    System.out.println("Status code: " + response.getStatusCode());
	}

	// Validate status line contains "Created"
	@Then("User receives status text as Created")
	public void user_receives_status_text_created() {
	    String statusLine = response.getStatusLine();
	    Assert.assertTrue(statusLine.contains("Created"));
	    System.out.println("Status line: " + statusLine);
	}

	// Validate user_id is integer with status 201
	@Then("User sees user_id is of integer type with status 201")
	public void user_sees_user_id_is_integer_with_status_201() {
	    Object userId = response.jsonPath().get("userId");
	    Assert.assertTrue(userId instanceof Integer);
	    Assert.assertEquals(response.getStatusCode(), 201);
	    System.out.println("userId (int): " + userId);
	}

	// Validate addressID is integer with status 201
	@Then("User sees addressID is of integer type with status 201")
	public void user_sees_address_id_is_integer_with_status_201() {
	    Object addressId = response.jsonPath().get("userAddress.addressId");
	    Assert.assertTrue(addressId instanceof Integer);
	    Assert.assertEquals(response.getStatusCode(), 201);
	    System.out.println("addressId (int): " + addressId);
	}
	
//	@Then("User receives Content-Type as application/json with status code {int}")
//	public void user_receives_content_type_as_application_json_with_status_code(Integer statusCode) {
//	    Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
//	    String contentType = response.header("Content-Type");
//	    Assert.assertTrue(contentType.contains("application/json"));
//	}
	
	// Helper method to log response
    private void logResponse() {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}

  