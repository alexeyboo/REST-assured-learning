import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Demo5_GET_validatingNodesInJSON {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://reqres.in/api/users";;
	}

	@Test
	void getWeatherDetails() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification requestSpec = given();

		Response response = requestSpec.request(Method.GET, "/Ufa");

		JsonPath jsonPath = response.jsonPath();

		Object city = jsonPath.get("City");
		System.out.println(city);


	}
}
