package files;

import org.junit.Test;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJson {
	
	@Test
	public void addBook() {
		
		RestAssured.baseURI="http://216.10.245.166";
		String response =  given().log().all().header("Content-Type","application/json")
		.body(payload.Addbook("aap","noot"))
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200) //check ook altijd de server ivm eventuele hackers
		.extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJason(response);
		String id = js.get("ID");
		System.out.println(id);
		//tesst
	}
	
@DataProvider
public void getData() 
{
	
}
	
	
}
