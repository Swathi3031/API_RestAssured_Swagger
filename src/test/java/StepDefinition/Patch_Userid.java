package StepDefinition;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.*;
import org.testng.Assert;

import hooks.Hooks;

import java.io.File;

public class Patch_Userid {
	
	private RequestSpecification requestSpec;
    private Response response;
    private String basePath = "uap/updateuserfields/22305";  // You can change user ID as needed

    @Given("Patch request with new data, BaseURL, valid Endpoint")
    public void patchRequestWithValidURLValidEndpoint() {
        requestSpec = Hooks.requestSpec
            .basePath("uap/updateuserfields/22305")
            .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
            .header("Content-Type", "application/json");
    }

    @Given("Patch request with new data, invalid BaseURL, valid Endpoint")
    public void patchRequestWithInvalidBaseURL() {
        requestSpec = Hooks.requestSpec.baseUri("https://userserviceapp-f5a5482841b.herokuapp.com")
            .basePath("uap/updateuserfields/22305")
            .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
            .header("Content-Type", "application/json");
    }

    @Given("Patch request with new data, valid BaseURL, invalid Endpoint")
    public void patchRequestWithInvalidEndpoint() {
        requestSpec = Hooks.requestSpec
            .basePath("uap/updateuserfields/22305m")
            .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
            .header("Content-Type", "application/json");
    }

    @Given("Patch request with new data, valid BaseURL, empty Endpoint")
    public void patchRequestWithEmptyEndpoint() {
        requestSpec = Hooks.requestSpec
            .basePath("")
            .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
            .header("Content-Type", "application/json");
    }

    @Given("Patch request with new data, valid BaseURL, valid Endpoint and No Auth")
    public void patchRequestWithNoAuth() {
        requestSpec = Hooks.requestSpec
            .basePath("uap/updateuserfields/22305")
            .header("Content-Type", "application/json"); // No Auth
    }

   

    @When("User creates Patch request with file {string}")
    public void sendPatchRequestWithFile(String fileName) {
        File jsonFile = new File("src/test/resources/PatchJson/" + fileName);
        System.out.println("Sending PATCH request using file: " + fileName);

        response = requestSpec.body(jsonFile).when().patch();
        
        System.out.println("Response:\n" + response.asPrettyString());
    }

    

    @Then("User receives {int} status code")
    public void validateResponseStatusCode(int expectedStatusCode) {
    	 Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
	                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
	    }
}

//    @Then("User receives {int} status code")
//    public void userReceivesStatusCode(int expectedStatusCode) {
//        if (response != null) {
//            int actualStatusCode = response.getStatusCode();
//            if (actualStatusCode != expectedStatusCode) {
//                System.out.println("Expected Status Code: " + expectedStatusCode + ", but got: " + actualStatusCode);
//            }
//            assert actualStatusCode == expectedStatusCode;
//        } else {
//            throw new AssertionError("Response is null, request might have failed.");
//        }
//    }
//
//}
