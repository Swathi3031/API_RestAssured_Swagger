package StepDefinition;


	import hooks.Hooks;
	import io.cucumber.java.en.*;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

import java.util.List;

import org.testng.Assert;

	public class Get_UserName {
		
	    private RequestSpecification requestSpec;
	    private Response response;
	    private String basePath = "/uap/users/username/";
	    private String userFirstName;

	    @Given("User prepares GET request with BaseURL, valid endpoint, and existing user FirstName")
	    public void prepare_get_request_with_valid_firstname() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "Numpy"; // Replace with existing first name in your system
	        //
	    }

	    @When("User sends GET request with existing user FirstName")
	    public void send_get_request_with_valid_firstname() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .header("Accept", "application/json") 
	                .when()
	                .get(basePath + userFirstName);
	       // .get("/uap/getuser/" + PostPositive_CreateUsers.createdUserId);
	        System.out.println("Status Code: " + response.getStatusCode());
	        System.out.println("Response: \n" + response.asPrettyString());
	    }

	    @Then("User receives 200 OK status with user details in response")
	    public void validate_200_status_with_user_details() {
	        Assert.assertEquals(response.statusCode(), 200);
	        Assert.assertTrue(response.getBody().asString().contains(userFirstName));
	    }

	    @Then("Response body contains same UserfirstName as requested")
	    public void response_body_contains_requested_firstname() {
//	        String actualFirstName = response.jsonPath().getString("userFirstName");
//	        Assert.assertEquals(actualFirstName, userFirstName);
	    	List<String> firstNames = response.jsonPath().getList("userFirstName");
	    	Assert.assertNotNull(firstNames, "FirstName list should not be null");
	    	Assert.assertTrue(firstNames.stream().allMatch(name -> name.equals("Numpy")), "All first names in response should be 'Numpy'");
	    }

	    @Then("Response Content-Type is JSON with status code 200 OK")
	    public void response_content_type_is_json() {
	        Assert.assertEquals(response.statusCode(), 200);
	        Assert.assertTrue(response.getContentType().contains("application/json"));
	    }

	    @Then("Response contains user details: user_id, UserfirstName, UserlastName, userContactNumber, userEmailId, creationTime, lastModTime, userAddress, addressId, plotNumber, street, state, country, zipCode")
	    public void response_contains_required_fields() {
	        Assert.assertNotNull(response.jsonPath().get("userId"));
	        Assert.assertNotNull(response.jsonPath().get("userFirstName"));
	        Assert.assertNotNull(response.jsonPath().get("userLastName"));
	        Assert.assertNotNull(response.jsonPath().get("userContactNumber"));
	        Assert.assertNotNull(response.jsonPath().get("userEmailId"));
	        Assert.assertNotNull(response.jsonPath().get("creationTime"));
	        Assert.assertNotNull(response.jsonPath().get("lastModTime"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.addressId"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.plotNumber"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.street"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.state"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.country"));
	        Assert.assertNotNull(response.jsonPath().get("userAddress.zipCode"));
	    }

	    @Then("UserfirstName in response is of string type")
	    public void userfirstname_is_string_type() {
//	        Object firstName = response.jsonPath().get("userFirstName");
//	        Assert.assertTrue(firstName instanceof String);
	        List<Object> firstNames = response.jsonPath().getList("userFirstName");
	        Assert.assertNotNull(firstNames, "First name list should not be null");
	        Assert.assertTrue(firstNames.stream().allMatch(name -> name instanceof String), "Each userFirstName should be of type String");
	    }

	    @Then("address_id is of integer type")
	    public void address_id_is_integer_type() {
	    	 List<Object> addressIds = response.jsonPath().getList("userAddress.addressId");

	    	    Assert.assertNotNull(addressIds, "addressId list should not be null");
	    	    Assert.assertFalse(addressIds.isEmpty(), "addressId list should not be empty");

	    	    for (Object id : addressIds) {
	    	        Assert.assertTrue(id instanceof Number, "Each addressId should be a number");
	    	        int value = ((Number) id).intValue();
	    	        Assert.assertTrue(value >= 0, "Each addressId should be a non-negative integer");
	    	    }
	    }

	    @Given("User prepares GET request with BaseURL, valid endpoint, and non-existing FirstName")
	    public void prepare_get_request_with_non_existing_firstname() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "NonExistentUser";
	    }

	    @When("User sends GET request with non-existent FirstName")
	    public void send_get_with_non_existent_firstname() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .header("Accept", "application/json") 
	                .when()
	                .get(basePath + userFirstName);
	        System.out.println("Response: " + response.asPrettyString());
	    }

	    @Then("User receives 404 Not Found status with the error message")
	    public void validate_404_response() {
	        Assert.assertEquals(response.statusCode(), 404);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("not found"));
	    }

	    @Given("User prepares GET request with BaseURL, valid endpoint, and invalid FirstName as 123")
	    public void prepare_get_request_with_integer_firstname() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "123";
	    }

	    @When("User sends GET request with invalid FirstName 123")
	    public void send_get_with_integer_firstname() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .header("Accept", "application/json") 
	                .when()
	                .get(basePath + userFirstName);
	    }

	    @Given("User prepares GET request with BaseURL and invalid endpoint path")
	    public void prepare_get_with_invalid_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        basePath = "/uap/users/usernames/";
	        userFirstName = "Numpy";
	    }

	    @When("User sends GET request to wrong path")
	    public void send_get_to_invalid_path() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .header("Accept", "application/json") 
	                .when()
	                .get(basePath + userFirstName);
	    }

	    @Given("User prepares GET request with BaseURL, valid endpoint, and existing user FirstName but without Authorization header")
	    public void prepare_get_request_without_auth() {
	        requestSpec = Hooks.requestSpec;
	        userFirstName = "Numpy";
	    }

	    @When("User sends GET request with existing user FirstName without Auth")
	    public void send_get_without_auth() {
	        response = requestSpec
	                .when()
	                .get(basePath + userFirstName);
	    }

	    @Then("User received 401 Unauthorized status")
	    public void validate_401_response() {
	        Assert.assertEquals(response.statusCode(), 401);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("unauthorized"));
	    }
	
}
