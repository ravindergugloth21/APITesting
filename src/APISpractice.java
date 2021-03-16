import io.restassured.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.Assert;

public class APISpractice {
	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in";
		given().header("Content-Type","application/json;charset=UTF-8")
		.when().get("/api/users")
		.then().log().all().assertThat().statusCode(200);
}
}