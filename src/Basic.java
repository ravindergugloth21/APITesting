import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.Assert;

import Files.PayLoad;
import Files.ReusableMethod;


public class Basic {
	

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//POST
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json;charset=UTF-8").body(PayLoad.addPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP") )
		.header("Content-Type","application/json;charset=UTF-8")
		.extract().response().asString();
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
				
		//Update the record PUT Method
		 String newAddress="70 Summer walk, USA";
				given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json; charset=UTF-8").body("{\r\n" +
				"\"place_id\":\""+placeid+"\",\r\n" +
				"\"address\":\""+newAddress+"\",\r\n" +
				"\"key\":\"qaclick123\"\r\n" +
				"}\r\n" +
				"")
				.when().put("/maps/api/place/update/json")
				.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

		
		//GET
		String getResponse=given().log().all().queryParam("Key","qaclick123").queryParam("place_id",placeid)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("This is address check "+newAddress);
		System.out.println("This is place id "+placeid);
	
		JsonPath js1=ReusableMethod.rawToJson(getResponse);
		 String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}

}
