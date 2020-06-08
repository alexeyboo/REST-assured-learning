import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Demo6_GET_requestAuthorization {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
	}

	@Test
	void authorization() {
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		RestAssured.authentication = authScheme;
		RequestSpecification requestSpec = given();

		Response response = requestSpec.request(Method.GET, "/");

		JsonPath jsonPath = response.jsonPath();
		System.out.println(response.getBody().asString());

		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);


	}
}
