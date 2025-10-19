package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Listeners;

import config.ConfigManager;

/**
 * BaseAPI is a base class for all API test classes.
 * It sets up the default RestAssured configuration and request specification.
 */
@Listeners(reports.TestListener.class)
public abstract class BaseAPI {

    protected RequestSpecification request;

    /**
     * Constructor sets the base URI and prepares a default request specification.
     */
    public BaseAPI() {
        RestAssured.baseURI = ConfigManager.getApiBaseUrl();

        request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .log().all(); // Logs the full request
    }
}
