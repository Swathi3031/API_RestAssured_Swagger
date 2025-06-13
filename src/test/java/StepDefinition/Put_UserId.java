package StepDefinition;

import hooks.Hooks;


	import io.cucumber.java.en.*;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

	import static org.testng.Assert.assertEquals;
	import static io.restassured.RestAssured.*;
	import org.testng.Assert;

	import java.io.File;

	public class Put_UserId {

	    private RequestSpecification requestSpec;
	    private Response response;
	    private String basePath = "uap/updateuser/21910";
	   

	    @Given("User prepares PUT request with authorization and valid BaseURL and valid Endpoint")
	    public void preparePutRequestWithAuthValidURL() {
	        requestSpec = Hooks.requestSpec
	                .basePath("uap/updateuser/21910")
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025") 
	                .header("Content-Type", "application/json");
	    }

	    @Given("User prepares PUT request with authorization and invalid BaseURL and valid Endpoint")
	    public void preparePutRequestWithInvalidBaseURL() {
	    	requestSpec = Hooks.requestSpec.baseUri("https://userserviceapp-f5a5482841b.herokuapp.com")
	                .basePath("uap/updateuser/21910")
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025") 
	                .header("Content-Type", "application/json");
	    }

	    @Given("User prepares PUT request with authorization and valid BaseURL and invalid Endpoint")
	    public void preparePutRequestWithInvalidEndpoint() {
	    	 requestSpec = Hooks.requestSpec
		                .basePath("uap/updateuser/2191m")
		                .auth()
		                .preemptive()
		                .basic("Numpy@gmail.com", "userapi@2025") 
		                .header("Content-Type", "application/json");
		    }

	    @Given("User prepares PUT request with authorization and valid BaseURL and empty Endpoint")
	    public void preparePutRequestWithEmptyEndpoint() {
	    	requestSpec = Hooks.requestSpec
	                .basePath("")
	                .auth()
	                .preemptive()
	                .basic("Numpy@gmail.com", "userapi@2025") 
	                .header("Content-Type", "application/json");
	    }

	    @Given("User prepares PUT request with valid BaseURL and valid Endpoint without Auth")
	    public void preparePutRequestWithoutAuthorization() {
	        requestSpec = Hooks.requestSpec
	                .basePath("uap/updateuser/21910")
	                .header("Content-Type", "application/json"); // No authorization
	    }

	    @When("User sends PUT request with JSON file {string}")
	    public void sendPutRequestWithJsonFile(String fileName) {
	        File jsonFile = new File("src/test/resources/Putjson/" + fileName);
	        response = requestSpec
	                .body(jsonFile)
	                .when()
	                .put();
	        System.out.println("Response:\n" + response.asPrettyString());
	    }

	    @When("User sends PUT request without request body")
	    public void sendPutRequestWithoutBody() {
	        response = requestSpec
	                .when()
	                .put();
	        System.out.println("Response:\n" + response.asPrettyString());
	    }

	    @Then("User should receive status code {int}")
	    public void validateStatusCode(int expectedStatusCode) {
	        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
	                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
	    }
		}