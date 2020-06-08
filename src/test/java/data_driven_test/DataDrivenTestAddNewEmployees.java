package data_driven_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DataDrivenTestAddNewEmployees {
	@BeforeClass
	void setup() {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";;
	}
	@DataProvider(name = "employees_data")
	private Object[][] getEmpData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/data_driven_test/employees_data.xls";
		int rowNum = XLSUtils.getRowCount(path, "Sheet1");
		int columnNum = XLSUtils.getCellCount(path, "Sheet1", 1);
		String[][] empData = new String[rowNum - 1][columnNum];
		for (int i = 1; i < rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				empData[i - 1][j] = XLSUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return empData;
	}
	@Test(dataProvider = "employees_data")
	void postNewEmployees(String name, String salary, String age){
		RequestSpecification requestSpec = given();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("salary", salary);
		jsonObject.put("age", age);

		requestSpec.header("Content-Type", "application/json");
		requestSpec.body(jsonObject.toJSONString());
		Response response = requestSpec.request(Method.POST, "/create");
		String s = response.getBody().asString();
		Assert.assertTrue(s.contains(name));
		Assert.assertTrue(s.contains(salary));
		Assert.assertTrue(s.contains(age));

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
}
