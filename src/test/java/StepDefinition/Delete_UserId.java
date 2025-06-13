package StepDefinition;


	import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import org.testng.Assert;
	import hooks.Hooks;

	public class Delete_UserId {

	    private RequestSpecification requestSpec;
	    private Response response;
	    private String basePath = "/uap/deleteuser/"; // You can modify this if  - 24305

	    private String userId; // Store dynamic userID for deletion

	    @Given("The userID exists already")
	    public void the_user_id_exists_already() {
	        requestSpec = Hooks.requestSpec;
	        userId = "28167"; //28167, 21319 replace with an existing valid userID
	    }

	    @When("User sends DELETE request to delete the respective userID")
	    public void user_sends_delete_request_with_existing_user_id() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .delete(basePath + userId);
	        System.out.println("Response: " + response.asPrettyString());
	    }

	    @Then("User receives 200 OK status with the success message")
	    public void user_receives_200_ok_status() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertTrue(response.getBody().asString().contains("deleted") || response.getBody().asString().contains("success"));
	    }

	    @Given("The userID is already deleted")
	    public void user_id_is_already_deleted() {
	        requestSpec = Hooks.requestSpec;
	        userId = "28167"; // use previously deleted userID
	    }

	    @Then("User receives 404 Not Found error with the message")
	    public void user_receives_404_not_found() {
	    	String responseBody = response.getBody().asString().toLowerCase();
	    	Assert.assertTrue(
	    	    responseBody.contains("not found") ||
	    	    responseBody.contains("error") ||
	    	    responseBody.contains("page not found") ||
	    	    responseBody.contains("no such app"),
	    	    "Expected response body to contain a 404 error message, but got: " + responseBody
	    	);
	    }
	    
	    @Then("User receives 400 Bad Request with the message")
	    public void user_receives_400_bad_request_with_the_message() {
	        Assert.assertEquals(response.getStatusCode(), 400);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("bad request"));
	    }
	    

	    @Then("Response body contains the same user_id as requested")
	    public void response_body_contains_requested_user_id() {
	    	String userId = "28167"; // Replace this with dynamic value if needed
	    	String message = response.jsonPath().getString("message");

	    	Assert.assertTrue(message.contains(userId),
	    	    "Expected userId to be mentioned in message, but got: " + message);
	    }

	    @Given("User enters userID with combination of number, alphabet and special characters")
	    public void user_enters_userid_with_alphanumeric_special_chars() {
	        requestSpec = Hooks.requestSpec;
	        userId = "123abc$%";
	    }

	    @Given("User enters userID with only special characters")
	    public void user_enters_userid_with_special_chars_only() {
	        requestSpec = Hooks.requestSpec;
	        userId = "!@#";
	    }

	    @Given("User enters userID with 5 invalid digits")
	    public void user_enters_userid_with_invalid_digits() {
	        requestSpec = Hooks.requestSpec;
	        userId = "99999"; // non-existent ID
	    }

	    @Given("User enters userID with nothing \\(blank)")
	    public void user_enters_userid_with_blank() {
	        requestSpec = Hooks.requestSpec;
	        userId = "";
	    }

	    @Given("User enters userID with zero value")
	    public void user_enters_userid_with_zero() {
	        requestSpec = Hooks.requestSpec;
	        userId = "0";
	    }

	    @Given("User enters userID with negative value")
	    public void user_enters_userid_with_negative_value() {
	        requestSpec = Hooks.requestSpec;
	        userId = "-123";
	    }

	    @Given("User enters userID with out of range value")
	    public void user_enters_userid_with_out_of_range() {
	        requestSpec = Hooks.requestSpec;
	        userId = "999999999999999999999999999999"; // adjust depending on your API limits
	    }

	    @Given("User enters userID with invalid BaseURL and valid Endpoint")
	    public void user_enters_invalid_baseurl() {
	    	requestSpec = RestAssured.given().baseUri("https://userserviceapp-f5a5482841b.herokuapp.com"); 
	        userId = "123";
	    }

	    @When("User sends DELETE request with the respective userID")
	    public void user_sends_delete_with_invalid_baseurl_or_endpoint() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .delete(basePath + userId);
	        System.out.println("Response: " + response.asPrettyString());
	    }

	    @Given("User enters userID with valid BaseURL and invalid Endpoint")
	    public void user_enters_valid_baseurl_invalid_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        basePath = "/uap/deleteusers/"; // Invalid endpoint
	        userId = "123";
	    }

	    @Given("User enters userID with valid BaseURL and empty Endpoint")
	    public void user_enters_valid_baseurl_empty_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        basePath = ""; // Empty endpoint
	        userId = "123";
	    }

	    @Then("User gets no response")
	    public void user_gets_no_response() {
	        Assert.assertTrue(response == null || response.getBody().asString().isEmpty());
	    }
	
}
