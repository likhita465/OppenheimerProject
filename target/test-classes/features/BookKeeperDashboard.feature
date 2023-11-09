Feature: Tax Relief Egress File Generation

Background:
	Given user has already logged in to application as bookkeeper
	|username|password|
	|bk|bk|

@UI	
Scenario: BookKeeper Dashboard page header
	Given user is on bookkeeper dashboard page
	Then user gets the header of the bookkeeper dashboard page
	And header of the bookkeeper dashboard page should be "Book Keeper Dashboard"

@UI		
Scenario: Generate Tax Relief Egress File Button visibility
	Given user is on bookkeeper dashboard page
	Then verify Generate Tax Relief Egress File button should be displayed

@UI		
Scenario: Generate Tax Relief Egress File Successfully
	Given user is on bookkeeper dashboard page
	When user click on the Generate Tax Relief Egress File button
	Then verify the "taxrelief.txt" file is generated