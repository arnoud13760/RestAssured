import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;


public class test01_GET {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// validate if Add Place API is working as excpected
		
		// given, when, then
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		
		//JsonPath js = new JsonPath(response); // For parsing Json
		JsonPath js = ReUsableMethods.rawToJason(response);
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		
		// Update Place
		
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReUsableMethods.rawToJason1(getPlaceResponse);
		String actualAddress = js1.getString("address");
		
		//Junit,  Testng
		Assert.assertEquals(actualAddress, newAddress);
		
		//jsoneditoronline.org
		
	}

}
