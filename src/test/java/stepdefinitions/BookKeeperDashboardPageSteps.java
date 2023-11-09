package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.govtech.sg.factory.DriverFactory;
import com.govtech.sg.pages.BookKeeperDashboardPage;
import com.govtech.sg.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookKeeperDashboardPageSteps {
	
	private String bookkeeperHeaderMsg;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private BookKeeperDashboardPage bookkeeperPage;
	
	@Given("user has already logged in to application as bookkeeper")
	public void user_has_already_logged_in_to_application(DataTable dataTable) {
		List<Map<String,String>> credList = dataTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");
		
		DriverFactory.getDriver().get("http://localhost:9997/login");
		bookkeeperPage = loginPage.doBookkeeperLogin(userName, password);
	}
	
	@Given("user is on bookkeeper dashboard page")
	public void user_is_on_bookkeeper_dashboard_page() {
		System.out.println("user is on bookkeeper dashboard page");
	}


	@Then("user gets the header of the bookkeeper dashboard page")
	public void user_gets_the_header_of_the_bookkeeper_dashboard_page() {
		bookkeeperHeaderMsg = bookkeeperPage.getBookKeeperHeaderMsg();
		System.out.println("clerk dashboard page header is:" + bookkeeperHeaderMsg);
	}
	
	
	@Then("header of the bookkeeper dashboard page should be {string}")
	public void header_of_the_bookkeeper_dashboard_page_should_be(String expectedHeaderMsg) {
		Assert.assertTrue(bookkeeperHeaderMsg.equalsIgnoreCase(expectedHeaderMsg));
	}


	@Then("verify Generate Tax Relief Egress File button should be displayed")
	public void verify_generate_tax_relief_egress_file_button_should_be_displayed() {
		if(bookkeeperPage.taxReliefFileButtonVisibility()) {
			Assert.assertTrue("genarate tax releif button is enabled",bookkeeperPage.taxReliefFileButtonVisibility());
			System.out.println("genarate tax releif button is enabled");
		}
		
		else {
			Assert.assertFalse("genarate tax releif button is not enabled",bookkeeperPage.taxReliefFileButtonVisibility());
			System.out.println("genarate tax releif button is not enabled");
		}
		
	}


	@When("user click on the Generate Tax Relief Egress File button")
	public void user_click_on_the_generate_tax_relief_egress_file_button() {
		bookkeeperPage.taxReliefFileButtonState();
	}
	
	
	@Then("verify the {string} file is generated")
	public void verify_the_file_is_generated(String string) {
			Assert.assertTrue("tax reflief file is not generated",bookkeeperPage.taxReliefFileButtonVisibility());
	}

}
