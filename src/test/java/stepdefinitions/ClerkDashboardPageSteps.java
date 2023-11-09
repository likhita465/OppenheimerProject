package stepdefinitions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.govtech.sg.factory.DriverFactory;
import com.govtech.sg.pages.ClerkDashboardPage;
import com.govtech.sg.pages.LoginPage;
import com.govtech.sg.util.DBUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClerkDashboardPageSteps {

	private String clerkHeaderMsg;
	private Integer beforeCsvUploadRecCount;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private ClerkDashboardPage clerkPage;

	@Given("user has already logged in to application as clerk")
	public void user_has_already_logged_in_to_application(DataTable dataTable) {
		List<Map<String, String>> credList = dataTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");

		DriverFactory.getDriver().get("http://localhost:9997/login");
		clerkPage = loginPage.doClerkLogin(userName, password);
	}

	@Then("verify Add Hero button should be displayed")
	public void verify_add_hero_button_should_be_displayed() {
		Assert.assertTrue(clerkPage.isAddHeroButtonExists());
	}

	@When("user select upload a csv file from Add Hero dropdown")
	public void user_select_upload_a_csv_file_from_add_hero_dropdown() {
		clerkPage.clickAddHeroButton();
		clerkPage.clickUploadCsvFileFromDropdown();
	}

	@When("user upload working record csv file")
	public void user_upload_working_record_csv_file() {
		clerkPage.uploadWorkingCsvFile();

	}

	@When("user click create button")
	public void user_click_create_button() throws SQLException {
		Statement statement = DBUtils.createStatement();
		ResultSet rs = statement.executeQuery("select count(*) from working_class_heroes");
		while (rs.next()) {
			beforeCsvUploadRecCount = rs.getInt(1);
			System.out.println("Data count before importing empty csv file:" + beforeCsvUploadRecCount);
		}
		clerkPage.clickCreateButton();

		DBUtils.closeStatement();
	}

	@Then("{string} message should be displayed")
	public void message_should_be_displayed(String expectedMsg) {
		String actualMsg = clerkPage.getSuccessNotificationMsg();
		Assert.assertTrue(actualMsg.equalsIgnoreCase(expectedMsg));
	}

	@Then("verify the count of {string} in database table")
	public void verify_the_count_of_in_database_table(String string) throws SQLException {
		Statement statement = DBUtils.createStatement();
		ResultSet rs = statement.executeQuery("select count(*) from working_class_heroes");
		while (rs.next()) {
			Integer afterCsvUploadRecCount = rs.getInt(1);
			System.out.println("Data count after importing empty csv file:" + afterCsvUploadRecCount);
			Assert.assertTrue((afterCsvUploadRecCount == afterCsvUploadRecCount));
		}
		DBUtils.closeStatement();

	}

	@When("user upload erroneous record csv file")
	public void user_upload_erroneous_record_csv_file() {
		clerkPage.uploadErroneousCsvFile();
	}

	@When("user upload empty record csv file")
	public void user_select_empty_record_csv_file() {
		clerkPage.uploadEmptyCsvFile();
	}

	@Then("user gets the header of the clerk dashboard page")
	public void user_gets_the_header_of_the_clerk_dashboard_page() {
		clerkHeaderMsg = clerkPage.getClerkHeaderMessage();
		System.out.println("clerk dashboard page header is:" + clerkHeaderMsg);
	}

	@Then("header of the clerk dashboard page should be {string}")
	public void header_of_the_clerk_dashboard_page_should_be(String expectClerkHeader) {
		Assert.assertTrue(clerkHeaderMsg.equalsIgnoreCase(expectClerkHeader));
	}

	@Given("user is on Clerk dashboard page")
	public void user_is_on_clerk_dashboard_page() {
		System.out.println("user is on clerk dashboard page");
	}

}
