import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Demo3_GET_request {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://maps.googleapis.com";
	}

	@Test
	void googleMapTest() {
		RequestSpecification requestSpec = given();

		Response response = requestSpec.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyD4dwe97B22l95i5eabeBsAZP5FnHZGZQc");

		System.out.println(response.getBody().asString());
		String contentType = response.header("Content-Encoding");
		Assert.assertEquals(contentType, "gzip");

	}
}
