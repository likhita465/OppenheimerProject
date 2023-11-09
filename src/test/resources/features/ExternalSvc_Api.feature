Feature: External Service Api calls

  Scenario: Calling owe-money api with valid natid
	
	Given Add Headers to ExternalSvc Request

      |KEY         |VALUE|
      |Content-Type| application/json |

    When send GET request to ExternalSvc "api/v1/hero/owe-money?natid=75699"
    Then ExternalSvc Resp status code is 200
    And  Response body has "natid-75699" and "OWE"
	
	Scenario: Calling owe-money api with invalid natid
	
	Given Add Headers to ExternalSvc Request

      |KEY         |VALUE|
      |Content-Type| application/json |

    When send GET request to ExternalSvc "api/v1/hero/owe-money?natid=abcd"
    Then ExternalSvc Resp status code is 500
    And  Response body contains "Internal Server Error"
	
	Scenario: Calling owe-money api with valid natid and validating its response schema
	
	Given Add Headers to ExternalSvc Request

      |KEY         |VALUE|
      |Content-Type| application/json |

    When send GET request to ExternalSvc "api/v1/hero/owe-money?natid=75699"
    Then ExternalSvc Resp status code is 200
    And  Validate Response body "natid-75699" and "OWE"
	