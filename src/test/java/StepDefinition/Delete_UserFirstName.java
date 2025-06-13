package StepDefinition;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import hooks.Hooks;

public class Delete_UserFirstName {

	    private RequestSpecification requestSpec;
	    private Response response;
	    private String userFirstName;

	    @Given("The userFirstName exists already")
	    public void the_user_first_name_exists_already() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "alice"; 
	    }

	    @When("User sends DELETE request to delete the respective userFirstName")
	    public void send_delete_request_by_user_first_name() {
	        response = requestSpec
	                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
	                .delete("/uap/deleteuser/username/" + userFirstName);

	        System.out.println("DELETE Response:\n" + response.asPrettyString());
	    }

	    @Then("User receives 200 OK status with success message")
	    public void user_receives_200_ok_status() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        System.out.println("Status: 200 OK");
	    }

	    @Given("The userFirstName is already deleted")
	    public void the_user_first_name_is_already_deleted() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "alice"; // Same userFirstName that was deleted earlier
	    }

	    @Then("User receives 404 Not Found error with message")
	    public void user_receives_404_not_found_error() {
	        Assert.assertEquals(response.getStatusCode(), 404);
	        System.out.println("Status: 404 Not Found");
	    }

	    @Then("Response body contains the same userFirstName as requested")
	    public void validate_deleted_user_first_name_in_response() {
	    	String expectedName = "alice"; // replace with dynamically passed value if needed
	        String message = response.jsonPath().getString("message");
	        Assert.assertTrue(message.contains(expectedName),
	            "Expected userFirstName to be mentioned in message, but got: " + message);
	    }

	    @Given("User enters userFirstName with combination of number, alphabet and special characters")
	    public void user_enters_invalid_user_firstname_combo() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "Jo@n123";
	    }

	    @Then("User receives 404 Not Found error with userFirstName message")
	    public void validate_404_for_invalid_userFirstName() {
	    	 int statusCode = response.getStatusCode();
	    	    String body = response.getBody().asString();
	    	    System.out.println("DELETE Response: \n" + body);

	    	    Assert.assertEquals(statusCode, 404, "Expected status code 404");

	    	    // For JSON response
	    	    if (response.getContentType().contains("application/json")) {
	    	        String message = response.jsonPath().getString("message");
	    	        Assert.assertTrue(message != null && message.toLowerCase().contains("user does not exist"),
	    	                "Expected message to contain 'user does not exist' but got: " + message);
	    	    }
	    	    // For HTML response (invalid BaseURL)
	    	    else if (response.getContentType().contains("text/html")) {
	    	        Assert.assertTrue(body.toLowerCase().contains("no such app") || body.toLowerCase().contains("error"),
	    	                "Expected HTML error page, but got: " + body);
	    	    }
	    	}

	    @Given("User enters userFirstName with only special characters")
	    public void user_enters_only_special_characters() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "@#$";
	    }

	    @Then("User receives 500 Internal Server Error with userFirstName message")
	    public void validate_500_for_special_chars() {
	    	System.out.println("Actual status: " + response.getStatusCode());
	        Assert.assertEquals(response.getStatusCode(), 500);
	        Assert.assertTrue(response.getBody().asString().contains("userFirstName"));
	    }

	    @Given("User enters userFirstName with only 5 digits")
	    public void user_enters_digits_only() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "12345";
	    }

	    @Given("User enters userFirstName with non-existent userFirstName")
	    public void user_enters_non_existent_userFirstName() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "DoesNotExist123";
	    }

	    @Given("User enters userFirstName with zero value")
	    public void user_enters_zero_value() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "0";
	    }

	    @Given("User enters userFirstName with negative integer value")
	    public void user_enters_negative_value() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "-1";
	    }

	    @Given("User enters userFirstName with out of range value")
	    public void user_enters_out_of_range_value() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "ANameThatIsExcessivelyAndUnrealisticallyLongToBeAFirstNameInNormalUsage";
	    }

	    @Given("User enters userFirstName with invalid BaseURL and valid Endpoint")
	    public void user_enters_invalid_baseurl() {
	        requestSpec = RestAssured.given().baseUri("https://userserviceapp-f5a5482841b.herokuapp.com"); // override baseURL
	        userFirstName = "alice";
	        
	    }

	    @Given("User enters userFirstName with valid BaseURL and invalid Endpoint")
	    public void user_enters_invalid_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "John";
	        response = requestSpec
	                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
	                .delete("/uap/deleteuser/username/" + userFirstName);
	    }

	    @Given("User enters userFirstName with valid BaseURL and empty Endpoint")
	    public void user_enters_empty_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "John";
	        response = requestSpec
	                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
	                .delete("/"); // Empty endpoint
	    }
	
}
