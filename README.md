GovTech QE Challenge

THE OPPENHEIMER PROJECT
-----------------------

This is a software system that has to support 3 features:
	- Enable Clerks to populate a list of working class heroes to the system
	- Enable Bookkeepers to retrieve the payable taxation relief for each working class

Below is the maven command to run:
	> mvn clean test

Below are the test scenarios covered.

ClerksActions_Api.feature

	Feature: Working Class Hero System Login Page
	
		Scenario: Login page title
		Scenario: Login with correct credentials
		Scenario: Login with invalid credentials
	
	Feature: Create Working Class Hero using API

	Clerk shall be able to add working hero's to the Database by using API
		
		Scenario: Creation of Single Working Class Hero
		Scenario: Validation fails for invalid natid where it contains aplhanumeric characters
		Scenario: Validation fails for invalid natid where it does not prefix with natid
		Scenario: Validation fails for invalid natid where the number is not inclusive
		Scenario: Validation fails for invalid name where the it includes numeric value
		Scenario: Validation fails for invalid name where the it exceeds than allowed limit
		Scenario: Validation fails for invalid gender
		Scenario: Validation fails for future deathDate
		Scenario: Validation fails for invalid salary
		Scenario: Validation fails for invalid taxpaid
		Scenario: Creation of Duplicate Single Working Class Hero
		Scenario: Verify Created Working Class Hero
	
	Feature: Upload a CSV file to populate the database from UI
	
		Scenario: Clerk Dashboard page header
		Scenario: Add Hero Button visibility
		Scenario: Upload a working record CSV File
		Scenario: Upload a erroneous record CSV File
		Scenario: Upload a empty record CSV File
		
	Feature: Tax Relief Egress File Generation
	
		Scenario: BookKeeper Dashboard page header
		Scenario: Generate Tax Relief Egress File Button visibility
		Scenario: Generate Tax Relief Egress File Successfully
		
	Feature: Adding Vouchers to working class heroes
	
		System owner able to provide an API that creates a working class hero with vouchers.
		
		Scenario: Creation of Single Working Class Hero with Vouchers
		Scenario: Creation of Single Working Class Hero with empty Vouchers
		Scenario: Verify Created Vouchers in db
		Scenario: Get Number of vouchers each customer has for each voucher category
		
	Feature: External Service Api calls
	
		Scenario: Calling owe-money api with valid natid
		Scenario: Calling owe-money api with invalid natid
		Scenario: Calling owe-money api with valid natid and validating its response schema	
		
Test Result from IDE:		

