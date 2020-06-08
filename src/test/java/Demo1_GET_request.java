import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Demo1_GET_request {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://reqres.in/api/users";;
	}

	@Test
	void getWeatherDetails() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification requestSpec = given();

		Response response = requestSpec.request(Method.GET, "/Ufa");

		System.out.println(response.getBody().asString());

		int statusCode = response.getStatusCode();


		String statusLine = response.getStatusLine();

		System.out.println(statusLine);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");


	}
}
