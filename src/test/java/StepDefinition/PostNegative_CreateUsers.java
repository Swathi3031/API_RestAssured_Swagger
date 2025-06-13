package StepDefinition;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import Utilities.JsonReader;
import hooks.Hooks;

import org.json.simple.JSONObject;
import java.io.IOException;

public class PostNegative_CreateUsers {

    private RequestSpecification requestSpec;
    private Response response;

    @Given("Users prepare the POST request with BaseURL and valid endpoint")
    public void prepareValidPostRequest() {
        requestSpec = Hooks.requestSpec.basePath("/uap/createusers");
    }

    @Given("User prepares the POST request with invalid BaseURL and valid endpoint")
    public void prepareRequestWithInvalidBaseURL() {
        requestSpec = RestAssured.given().baseUri("https://userserviceapp-f5a5482841b.herokuapp.com").basePath("/uap/createusers");
    }

    @Given("User prepares the POST request with BaseURL and invalid endpoint")
    public void prepareRequestWithInvalidEndpoint() {
        requestSpec = Hooks.requestSpec.basePath("/uap/createuser");
    }

    @Given("User prepares the POST request with BaseURL and empty endpoint")
    public void prepareRequestWithEmptyEndpoint() {
        requestSpec = Hooks.requestSpec.basePath("");
    }

    @When("Users sends the POST request with all mandatory fields but without Authorization")
    public void sendPostWithoutAuth() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_without_auth.json");
        response = requestSpec.header("Content-Type", "application/json").body(json.toJSONString()).post();
        logResponse();
    }

    @When("User sends the POST request with all required user details")
    public void user_sends_post_request_with_required_details() {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/User_All.json");
        response = requestSpec.header("Content-Type", "application/json")
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .body(json.toJSONString()).post();
        logResponse();
    }

    @When("User sends the POST request with an empty request body")
    public void sendEmptyRequestBody() {
        response = requestSpec.header("Content-Type", "application/json")
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .body("{}").post();
        logResponse();
    }

    @When("Users sends the POST request without only mandatory fields")
    public void sendOnlyOptionalFields() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_only_optional_fields.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("Users sends the POST request with existing email and contact number")
    public void sendDuplicateEmailAndContact() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_existing_email_contact.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

      @When("User sends the POST request with invalid first name")
    public void sendInvalidFirstName() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_invalid_first_name.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with invalid last name")
    public void sendInvalidLastName() throws IOException {
    	 JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_invalid_last_name.json");
    	 System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
    	 System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with special characters in contact number")
    public void sendSpecialCharInContact() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_special_chars_contact.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with less than 10 digits in contact number")
    public void sendShortContact() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_short_contact.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with more than 10 digits in contact number")
    public void sendLongContact() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_long_contact.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with numeric plot number")
    public void sendNumericPlot() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_numeric_plot.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with special characters in street name")
    public void sendSpecialStreet() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_special_chars_street.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with numeric value in state")
    public void sendNumericState() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_numeric_state.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with numeric value in country")
    public void sendNumericCountry() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_numeric_country.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @When("User sends the POST request with special characters in zip code")
    public void sendSpecialZip() throws IOException {
        JSONObject json = JsonReader.getJsonObject("src/test/resources/PostNegativeJson/user_special_chars_zip.json");
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        System.out.println("JSON content: " + (json != null ? json.toJSONString() : "NULL"));
        response = sendRequest(json);
        logResponse();
    }

    @Then("User receives 409 Conflict status for existing entry")
    public void validate409ConflictResponse() {
        Assert.assertEquals(response.getStatusCode(), 409, "Expected 409 Conflict status");
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.toLowerCase().contains("already exist"),
                "Conflict message does not match: " + message);
        logResponse();
    }

    @Then("Users receives 401 Unauthorized status")
    public void users_receives_401_unauthorized_status() {
        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("unauthorized"));
        logResponse();
    }

    @Then("User receives 404 Not Found status with appropriate message")
    public void user_receives_404_not_found_status_with_appropriate_message() {
        Assert.assertEquals(response.getStatusCode(), 404);
//        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("not found"));
        logResponse();
    }

    @Then("User receives 400 Bad Request status with appropriate message")
    public void user_receives_400_bad_request_status_with_appropriate_message() {
    	Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertTrue(response.getBody().asString().toLowerCase().contains("bad_request"));
    	       	    logResponse();
    }

    private Response sendRequest(JSONObject json) {
    	 if (json == null) {
    	        throw new RuntimeException("JSON object is null. Check the file path or contents.");
    	    }

    	    return requestSpec.header("Content-Type", "application/json")
    	            .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
    	            .body(json.toJSONString()).post();
    	
    }

    private void assertStatusCodeAndLine(int expectedStatus, String statusText) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
        Assert.assertTrue(response.getStatusLine().contains(statusText),
                "Expected status line to contain: " + statusText);
        System.out.println("Response: " + response.getStatusLine());
    }

    private void logResponse() {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
    }
}

 