package hooks;

import io.cucumber.java.Before;
import StepDefinition.PostPositive_CreateUsers;
import Utilities.configReader.ConfigReader;
import io.cucumber.java.After;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Hooks {

    public static RequestSpecification requestSpec;

    @Before
    public void setup() {
        String baseURI = ConfigReader.get("baseURI");
        String contentType = ConfigReader.get("contentType");

        RestAssured.baseURI = baseURI;

        requestSpec = RestAssured.given()
                .header("Content-Type", contentType);
    }


    @After(order = 1)
    public void cleanUpCreatedUser() {
        int userId = PostPositive_CreateUsers.createdUserId;

        if (userId != 0) {
            Response deleteResponse = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("Numpy@gmail.com", "userapi@2025")
                .delete("/uap/user/" + userId);

            System.out.println("Cleanup - DELETE Response Code: " + deleteResponse.statusCode());

            PostPositive_CreateUsers.createdUserId = 0; // reset after cleanup
        }
    }

}