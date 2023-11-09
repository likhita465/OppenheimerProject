Feature: Working Class Hero System Login Page

@UI	
Scenario: Login page title
	Given user is on login page
	When user gets the header of the login page
	Then header of the login page should be "Working Class Hero System"
	
@UI	
Scenario: Login with correct credentials
	Given user is on login page
	When user enters username "clerk"
	And user enters password "clerk"
	And user click on Submit button
	#Then user gets the header of the clerk dashboard page
	#And header of the clerk dashboard page should be "Clerk Dashboard"	

@UI		
Scenario: Login with invalid credentials
	Given user is on login page
	When user enters username "ceew"
	And user enters password "deefe"
	And user click on Submit button
	Then user gets the error message in the page
	And error message should be "Unable to log in successfully!"
	
	