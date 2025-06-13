package StepDefinition;


	
	import hooks.Hooks;
	import io.cucumber.java.en.*;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import org.testng.Assert;

	import java.util.List;
	import java.util.Map;

	public class Get_Users {

	    private RequestSpecification requestSpec;
	    private Response response;
	    private String basePath = "/uap/users";

	    @Given("User prepares the GET request with valid BaseURL and valid endpoint")
	    public void prepare_get_all_users_valid_request() {
	        requestSpec = Hooks.requestSpec;
	    }

	    @When("User sends GET request to fetch users")
	    public void send_get_all_users_request() {
	        response = requestSpec
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025")
	                .when()
	                .get(basePath);
	        System.out.println("Response:\n" + response.asPrettyString());
	    }
	    
	    @When("User sends GET request to fetch users without Auth")
	    public void send_get_users_without_auth() {
	    	response = requestSpec
	                .when()
	                .get(basePath);
	        System.out.println("Response:\n" + response.asPrettyString());
	    }

	    @Then("User receives 200 OK status with list of users")
	    public void validate_200_status_and_users_list() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        List<?> userList = response.jsonPath().getList("$");
	        Assert.assertNotNull(userList);
	        Assert.assertTrue(userList.size() > 0, "User list should not be empty");
	    }

	    @Then("Response Content-Type is JSON with the status code 200 OK")
	    public void validate_content_type_json() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertTrue(response.getContentType().contains("application/json"));
	    }

	    @Then("Response contains user fields: user_id, UserfirstName, UserlastName, userContactNumber, userEmailId, creationTime, lastModTime, userAddress, addressId, plotNumber, street, state, country, zipCode")
	    public void validate_user_fields_in_response() {
	        List<Map<String, Object>> users = response.jsonPath().getList("$");
	        Assert.assertFalse(users.isEmpty());

	        Map<String, Object> user = users.get(0); // check only first user
	        Assert.assertNotNull(user.get("userId"));
	        Assert.assertNotNull(user.get("userFirstName"));
	        Assert.assertNotNull(user.get("userLastName"));
	        Assert.assertNotNull(user.get("userContactNumber"));
	        Assert.assertNotNull(user.get("userEmailId"));
	        Assert.assertNotNull(user.get("creationTime"));
	        Assert.assertNotNull(user.get("lastModTime"));

	        Map<String, Object> address = (Map<String, Object>) user.get("userAddress");
	        Assert.assertNotNull(address);
	        Assert.assertNotNull(address.get("addressId"));
	        Assert.assertNotNull(address.get("plotNumber"));
	        Assert.assertNotNull(address.get("street"));
	        Assert.assertNotNull(address.get("state"));
	        Assert.assertNotNull(address.get("country"));
	        Assert.assertNotNull(address.get("zipCode"));
	    }

	    @Then("user_id is of integer type with the status code 200 OK")
	    public void validate_user_id_type_integer() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Object id = response.jsonPath().get("userId");
	        Assert.assertTrue(id instanceof Number);
	    }

	    @Then("address_id is of integer type with the status code 200 OK")
	    public void validate_address_id_type_integer() {
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Object addressId = response.jsonPath().get("userAddress.addressId");
	        Assert.assertTrue(addressId instanceof Number, "addressId should be a number");

	        // Then check if it is an integer specifically
	        int value = ((Number) addressId).intValue();

	        // Optionally log or validate integer range
	        Assert.assertTrue(value >= 0, "addressId should be a non-negative integer");
	    }

	    @Given("User prepares the GET request with valid BaseURL and invalid endpoint")
	    public void prepare_request_with_invalid_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        basePath = "/uap/invalidendpoint";
	    }

	    @Then("User receives 404 Not Found status")
	    public void validate_404_status() {
	    	Assert.assertEquals(response.getStatusCode(), 404);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("not found"));
	        System.out.println("Actual Status Code: " + response.getStatusCode());
	        System.out.println("Actual Response Body: " + response.asPrettyString());
	    }

	    @Given("User prepares the GET request with valid BaseURL and empty endpoint")
	    public void prepare_request_with_empty_endpoint() {
	        requestSpec = Hooks.requestSpec;
	        basePath = ""; // Empty path
	    }

	    @Given("User prepares the GET request without Authorization header")
	    public void prepare_request_without_auth() {
	        requestSpec = Hooks.requestSpec;
	    }

	    @Then("User receives 401 Unauthorized status")
	    public void validate_401_status() {
	        Assert.assertEquals(response.getStatusCode(), 401);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("unauthorized"));
	    }

	    @Given("User prepares the GET request with invalid BaseURL and valid endpoint")
	    public void prepare_request_with_invalid_baseurl() {
	        requestSpec = Hooks.requestSpec.baseUri("https://userserviceapp-f5a5482841b.herokuapp.com");
	        basePath = "/uap/users";
	    }

	    @Then("User receives 404 Not Found status with error message")
	    public void validate_404_with_invalid_baseurl() {
	        Assert.assertEquals(response.getStatusCode(), 404);
	        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("not found"));
	    }
	}


