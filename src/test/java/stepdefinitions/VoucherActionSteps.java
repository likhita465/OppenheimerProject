package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VoucherActionSteps {

	public static Response response;
	public static RequestSpecification request;
	private JSONObject jsonBody;

	private Random random = new Random();

	private int min = 10000;
	private int max = 9999999;
	private int increment = 10;
	private int numberOfPossibleValues = ((max - min) / increment) + 1;
	
	private static final String BASE_URL = "http://localhost:9997/";

	@Given("Add Headers to Vouchers Request")
	public void i_set_Headers_for_request(io.cucumber.datatable.DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			request = given().headers(list.get(i).get("KEY"), list.get(i).get("VALUE"));
		}
	}

	@SuppressWarnings("unchecked")
	@Given("Request body for Single working class hero and vouchers with below details")
	public void request_body_for_Single_working_class_hero_and_vouchers_below_details(
			io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {

			jsonBody = new JSONObject();
			int randomIndex = random.nextInt(numberOfPossibleValues);
			int randomNumber = min + (randomIndex * increment);

			jsonBody.put("natid", "natid-" + randomNumber);
			jsonBody.put("name", list.get(i).get("name"));
			jsonBody.put("gender", list.get(i).get("gender"));
			jsonBody.put("birthDate", list.get(i).get("birthDate"));
			jsonBody.put("deathDate", list.get(i).get("deathDate"));
			jsonBody.put("salary", list.get(i).get("salary"));
			jsonBody.put("taxPaid", list.get(i).get("taxPaid"));
			jsonBody.put("browniePoints", list.get(i).get("browniePoints"));

			JSONObject voucher = new JSONObject();
			voucher.put("voucherName", "VOUCHER 1");
			voucher.put("voucherType", "TRAVEL");

			JSONArray vouchers = new JSONArray();
			vouchers.add(voucher);

			jsonBody.put("vouchers", vouchers);

			System.out.println("JSON object is : " + jsonBody.toJSONString());
		}
		request.body(jsonBody);
	}

	@When("send vouchers request to {string}")
	public void i_send_POST_request_to(String string) {
		response = request.when().post(BASE_URL + string);
		System.out.println("Response :  " + response.asString());
	}

	@When("send vouchers request by person and type to {string}")
	public void i_send_POST_request_by_person_and_type_to(String string) {
		response = request.when().get(BASE_URL + string);
		System.out.println("Response :  " + response.asString());
	}

	@Then("validate vouchers request status {int}")
	public void the_status_code_is(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@SuppressWarnings("unchecked")
	@Given("Request body for Single working class hero and empty vouchers with below details")
	public void request_body_for_Single_working_class_hero_and_empty_vouchers_below_details(
			io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {

			jsonBody = new JSONObject();
			int randomIndex = random.nextInt(numberOfPossibleValues);
			int randomNumber = min + (randomIndex * increment);

			jsonBody.put("natid", "natid-" + randomNumber);
			jsonBody.put("name", list.get(i).get("name"));
			jsonBody.put("gender", list.get(i).get("gender"));
			jsonBody.put("birthDate", list.get(i).get("birthDate"));
			jsonBody.put("deathDate", list.get(i).get("deathDate"));
			jsonBody.put("salary", list.get(i).get("salary"));
			jsonBody.put("taxPaid", list.get(i).get("taxPaid"));
			jsonBody.put("browniePoints", list.get(i).get("browniePoints"));

			JSONArray vouchers = new JSONArray();

			jsonBody.put("vouchers", vouchers);

			System.out.println("JSON object is : " + jsonBody.toJSONString());
		}
		request.body(jsonBody);
	}

	@Then("validate response body for {string}")
	public void validateResponseBody(String errorMsg) {
		response.then().assertThat().log().body().toString().contains(errorMsg);
	}

	@Then("validate response body for vouchers")
	public void validateResponseBodyForVouchers() throws JsonMappingException, JsonProcessingException {
		String json = response.getBody().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("data");
		assertTrue(data.isArray() && data.size() > 0);
	}

}
