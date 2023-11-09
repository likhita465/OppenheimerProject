Feature: Upload a CSV file to populate the database from UI

Background:
	Given user has already logged in to application as clerk
	|username|password|
	|clerk|clerk|

@UI	
Scenario: Clerk Dashboard page header
	Given user is on Clerk dashboard page
	Then user gets the header of the clerk dashboard page
	And header of the clerk dashboard page should be "Clerk Dashboard"

@UI		
Scenario: Add Hero Button visibility
	Given user is on Clerk dashboard page
	Then verify Add Hero button should be displayed

@UI		
Scenario: Upload a working record CSV File
	Given user is on Clerk dashboard page
	When user select upload a csv file from Add Hero dropdown
	And user upload working record csv file
	And user click create button
	Then "Created Successfully!" message should be displayed
	And verify the count of "WORKING_CLASS_HEROES" in database table

@UI		
Scenario: Upload a erroneous record CSV File
	Given user is on Clerk dashboard page
	When user select upload a csv file from Add Hero dropdown
	And user upload erroneous record csv file
	And user click create button
	Then "Unable to create hero!" message should be displayed
	And verify the count of "WORKING_CLASS_HEROES" in database table	

@UI	
Scenario: Upload a empty record CSV File
	Given user is on Clerk dashboard page
	When user select upload a csv file from Add Hero dropdown
	And user upload empty record csv file
	And user click create button
	Then "Created Successfully!" message should be displayed
	And verify the count of "WORKING_CLASS_HEROES" in database table

		