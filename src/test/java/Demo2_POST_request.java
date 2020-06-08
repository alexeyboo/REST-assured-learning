import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Demo2_POST_request {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
	}

	@Test
	void registrationSuccessful() {
		RequestSpecification requestSpec = given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Alex123123");
		requestParams.put("LastName", "Bond");
		requestParams.put("UserName", "Alexxx123123123123");
		requestParams.put("Password", "123");
		requestParams.put("Email", "sdsd1231223123a@gmail.com");
		requestSpec.header("Content-Type", "application/json");
		requestSpec.body(requestParams.toJSONString());
		Response response = requestSpec.request(Method.POST, "/register");

		System.out.println(response.getBody().asString());

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
	}
}
