import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Demo4_GET_printAllHeaders {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "https://maps.googleapis.com";
	}

	@Test
	void googleMapTest() {
		RequestSpecification requestSpec = given();

		Response response = requestSpec.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyD4dwe97B22l95i5eabeBsAZP5FnHZGZQc");

		String contentType = response.header("Content-Encoding");
		Assert.assertEquals(contentType, "gzip");
		Headers headers = response.getHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + " " + header.getValue());
		}


	}
}
