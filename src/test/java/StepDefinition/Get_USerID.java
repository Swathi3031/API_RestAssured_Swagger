package StepDefinition;


	import hooks.Hooks;
	import io.cucumber.java.en.*;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import org.testng.Assert;

	public class Get_USerID {

	    private RequestSpecification requestSpec;
	    private Response response;
	    private String basePath = "/uap/user/"; ///21742
	    private String userId;

	    @Given("User prepares GET request with BaseURL, valid endpoint, and valid user ID")
	    public void prepare_get_request_with_valid_id() {
	        requestSpec = Hooks.requestSpec;
	        userId = "1"; // Replace with actual valid user ID in your test system
	    }

	    @When("User sends GET request with existing user ID")
	    public void send_get_request_with_valid_id() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .get(basePath + userId);
	        System.out.println("Response:\n" + response.asPrettyString());
	    }

	    @Then("User receives 200 OK status with the user details in response")
	    public void validate_status_200() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertTrue(response.getBody().asString().contains(userId));
	    }

	    @Then("Response body contains same user_id as requested")
	    public void validate_response_user_id_matches() {
	        int actualUserId = response.jsonPath().getInt("userId");
	        Assert.assertEquals(String.valueOf(actualUserId), userId);
	    }

	    @Then("the Response Content-Type is JSON with status code 200 OK")
	    public void validate_content_type_json() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertTrue(response.getContentType().contains("application/json"));
	    }

	    @Then("Response contains mandatory fields: user_id, UserfirstName, UserlastName, userContactNumber, userEmailId, creationTime, lastModTime, userAddress, addressId, plotNumber, street, state, country, zipCode")
	    public void validate_mandatory_fields() {
	        Assert.assertNotNull(response.jsonPath().get("userId"));
	        Assert.assertNotNull(response.jsonPath().get("userFirstName"));
	        Assert.assertNotNull(response.jsonPath().get("userLastName"));
	        Assert.assertNotNull(response.jsonPath().get("userContactNumber"));
	        Assert.assertNotNull(response.jsonPath().get("userEmailId"));
	        Assert.assertNotNull(response.jsonPath().get("creationTime"));
	        Assert.assertNotNull(response.jsonPath().get("lastModTime"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.addressId"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.plotNumber"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.street"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.state"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.country"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.zipCode"));
	    }

	    @Then("the user_id in response is of integer type")
	    public void user_id_is_integer_type() {
	        Object id = response.jsonPath().get("userId");
	        Assert.assertTrue(id instanceof Number);
	    }

	    @Then("the address_id is of integer type")
	    public void address_id_is_integer_type() {
	        Object addressId = response.jsonPath().get("userAddress.addressId");
	        Assert.assertTrue(addressId instanceof Number, "addressId should be a number");

	        // Then check if it is an integer specifically
	        int value = ((Number) addressId).intValue();

	        // Optionally log or validate integer range
	        Assert.assertTrue(value >= 0, "addressId should be a non-negative integer");
	    }

	    @Given("User prepares GET request with BaseURL, valid endpoint, and non-existing user ID")
	    public void prepare_get_request_with_non_existing_id() {
	        requestSpec = Hooks.requestSpec;
	        userId = "99999"; // Replace with an ID that doesn't exist in the system
	    }

	    @When("User sends GET request with non-existent ID")
	    public void send_get_with_non_existent_id() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .get(basePath + userId);
	    }

	    @Then("User receives 404 Not Found status with an error message")
	    public void validate_404_status() {
	        Assert.assertEquals(response.getStatusCode(), 404);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("not found"));
	    }

	    @Given("User prepares GET request with BaseURL, valid endpoint, and invalid user ID 'abc'")
	    public void prepare_get_request_with_invalid_id_string() {
	        requestSpec = Hooks.requestSpec;
	        userId = "abc";
	    }

	    @When("User sends GET request with invalid ID 'abc'")
	    public void send_get_request_with_invalid_id_string() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .get(basePath + userId);
	    }

	    @Then("User receives 400 Bad Request status with an error message")
	    public void validate_400_status() {
	        Assert.assertEquals(response.getStatusCode(), 400);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("bad request"));
	    }

	    @Given("User prepares GET request with the BaseURL and invalid endpoint path")
	    public void prepare_get_with_invalid_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        basePath = "/uap/users/";
	        userId = "1";
	    }

	    @When("User sends GET request to the wrong path")
	    public void send_get_to_wrong_endpoint() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .get(basePath + userId);
	    }

	    @Given("User prepares GET request with valid BaseURL, valid endpoint, and existing user ID but without Authorization header")
	    public void prepare_get_without_auth() {
	        requestSpec = Hooks.requestSpec;
	        userId = "1";
	    }

	    @When("User sends GET request with existing user ID without Authorization")
	    public void send_get_without_auth() {
	        response = requestSpec
	                .when()
	                .get(basePath + userId);
	    }

	    @Then("User receives the 401 Unauthorized status")
	    public void validate_401_status() {
	        Assert.assertEquals(response.getStatusCode(), 401);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("unauthorized"));
	    }
	}

