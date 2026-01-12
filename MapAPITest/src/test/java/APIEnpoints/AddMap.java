package APIEnpoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import test.java.POJOS.Location;
import test.java.POJOS.AddPlace;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import test.java.POJOS.getPlaceDeatils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddMap {
    public static void main(String[] args) {
        //add map
        RequestSpecification specrequest=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123").build();
        AddPlace ap=new AddPlace();
        ap.setAccuracy(50);
        ap.setName("Pune");
        ap.setPhone_number("9999999999");
        ap.setAddress("123 main street");
        List<String> types=new ArrayList<String>();
        types.add("Mall");
        types.add("Mart");
        ap.setTypes(types);
        ap.setWebsite("www.abc.com");
        ap.setLanguage("Kannada");
        Location loc=new Location();
        loc.setLat(3.3333);
        loc.setLng(8.8);
        ap.setLocation(loc);


        RequestSpecification request=given().spec(specrequest)
                .contentType("application/json")
                .body(ap);
        getPlaceDeatils resonse= request.when().post("/maps/api/place/add/json").
                then().log().all().extract().response().as(getPlaceDeatils.class);
        String status=resonse.getStatus();
        String place_id=resonse.getPlace_id();
        String scope=resonse.getScope();
        String reference=resonse.getReference();
        String id=resonse.getId();
        System.out.println("status:"+status);
        System.out.println("place_id:"+place_id);
        System.out.println("scope:"+scope);
        System.out.println("reference:"+reference);
        System.out.println("id:"+id);
        //get Location details
        RequestSpecification getRequest=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick123")
                .addQueryParam("place_id",place_id).build();
        RequestSpecification placeResponse=given().spec(getRequest);
        String placedetails=placeResponse.when().get("/maps/api/place/get/json")
                .then().log().all().extract().response().asString();
        System.out.println("placedetails:"+placedetails);
        // Delete place
        RequestSpecification deletespec=new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick123").build();
        RequestSpecification deletereq=given().spec(deletespec).body(place_id);
        Response deleteresponse=deletereq.when().delete("maps/api/place/delete/json")
                .then().extract().response();
        String statuscode=resonse.getStatus();
        System.out.println("Status:"+statuscode);
    }
}
