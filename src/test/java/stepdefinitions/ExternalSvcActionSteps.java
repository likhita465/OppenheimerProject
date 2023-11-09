package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExternalSvcActionSteps {

	public static Response response;
	public static RequestSpecification request;

	private static final String BASE_URL = "http://localhost:9997/";

	@Given("Add Headers to ExternalSvc Request")
	public void i_set_Headers_for_request(io.cucumber.datatable.DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			request = given().headers(list.get(i).get("KEY"), list.get(i).get("VALUE"));
		}
	}

	@When("send GET request to ExternalSvc {string}")
	public void sendGetReqToExternalSvc(String string) {
		response = request.when().get(BASE_URL + string);
		System.out.println("Response :  " + response.asString());
	}

	@Then("ExternalSvc Resp status code is {int}")
	public void externalSvcRespStatusCode(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("Response body has {string} and {string}")
	public void response_body_has(String natid, String status) {
		String respStr = response.getBody().asString();
		assertTrue(respStr.contains(natid) && respStr.contains(status));
	}

	@Then("Response body contains {string}")
	public void response_body_contains(String message) {
		response.then().assertThat().log().body().toString().contains(message);
	}

	@Then("Validate Response body {string} and {string}")
	public void validate_response_body(String natid, String status)
			throws JsonMappingException, JsonProcessingException {
		String json = response.getBody().asString();

		JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
		JsonObject messageObj = jsonObj.getAsJsonObject("message");
		String data = messageObj.get("data").getAsString();
		String owsSts = messageObj.get("status").getAsString();
		assertTrue(natid.equalsIgnoreCase(data) && status.equalsIgnoreCase(owsSts));
	}

}
