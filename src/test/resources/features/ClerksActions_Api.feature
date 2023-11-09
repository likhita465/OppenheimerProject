Feature: Create Working Class Hero using API

  Clerk shall be able to add working hero's to the Database by using API

  Scenario: Creation of Single Working Class Hero

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with below details
    | natid			| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-120002	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code is 200
    
  Scenario: Validation fails for invalid natid where it contains aplhanumeric characters

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-abcd120002	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid natid is 400

  Scenario: Validation fails for invalid natid where it does not prefix with natid

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid			| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | abcd120002	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid natid is 400
    
  Scenario: Validation fails for invalid natid where the number is not inclusive

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-10000000	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid natid is 400

  Scenario: Validation fails for invalid name where the it includes numeric value

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-12345		| 1User		| FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid natid is 400
    And  Response body has "Invalid name"

	Scenario: Validation fails for invalid name where the it exceeds than allowed limit

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-12345		| XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX		| FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid field is 400
    And  Response body has "Name must be between 1 and 100 characters"

  	Scenario: Validation fails for invalid gender
  
  	Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-12345		| User		| M			|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid field is 400
    And  Response body has "Invalid gender"

  	Scenario: Validation fails for future deathDate
  
  	Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-12345		| User		| MALE		|	1993-04-05	|	2024-01-01	|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid field is 500

  	Scenario: Validation fails for invalid salary
  
  	Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-12345		| User		| MALE		|	1993-04-05	|				|	-9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid field is 400
    And  Response body has "Salary must be greater than or equals to zero"

  	Scenario: Validation fails for invalid taxpaid
  
  	Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with invalid details
    | natid				| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-12345		| User		| MALE		|	1993-04-05	|				|	9000	|	-900	|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code for invalid field is 400
    And  Response body has "must be greater than or equal to 0"

	Scenario: Creation of Duplicate Single Working Class Hero

    Given set headers to payload

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero with existing natid details
    | natid			| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-120002	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When Post Request Sent To "api/v1/hero"
    Then The status code is should be either of 400 and 500
    And  Response body has "Working Class Hero of natid: natid-120002 already exists!"
    
	Scenario: Verify Created Working Class Hero

    Given a database connection is established
    When I execute the query "SELECT * FROM working_class_heroes WHERE natid = natid-120002"
    Then I should receive a non empty resultset
	