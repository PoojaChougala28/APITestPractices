package StepDefination;

import Pojo.*;
import Resourse.Testdata_addPlace;
import Resourse.Utils;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefination extends Utils {
    RequestSpecification request;

    Response response;
    Testdata_addPlace data=new Testdata_addPlace();
    @Given("AddPlace Payload")
    public void add_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        request=given().spec(requestSpecification())
                .contentType("application/json")
                .body(data.addPlacePayload());
    }
    @When("user calls {string} with post http request")
    public void user_calls_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
         response= request.when().post("/maps/api/place/add/json").
                then().log().all().extract().response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        // Write code here that turns the phrase above into concrete actions
        String res=response.asString();
        JsonPath js=new JsonPath(res);
        assertEquals(js.get(key).toString(),value);
        }
}
