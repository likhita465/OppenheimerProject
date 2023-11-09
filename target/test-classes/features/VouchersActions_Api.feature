Feature: Adding Vouchers to working class heroes using API

  System owner able to provide an API that creates a working class hero with vouchers.

  Scenario: Creation of Single Working Class Hero with Vouchers

    Given Add Headers to Vouchers Request

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero and vouchers with below details
    | natid			| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-120002	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When send vouchers request to "api/v1/hero/vouchers"
    Then validate vouchers request status 200

  Scenario: Creation of Single Working Class Hero with empty Vouchers

    Given Add Headers to Vouchers Request

      |KEY         |VALUE|
      |Content-Type| application/json |

    And Request body for Single working class hero and empty vouchers with below details
    | natid			| name      | gender	|	birthDate	|	deathDate	|	salary	|	taxPaid	|	browniePoints	|
    | natid-120002	| P Likhita | FEMALE	|	1993-04-05	|				|	9000	|	900		|	9				|

    When send vouchers request to "api/v1/hero/vouchers"
    Then validate vouchers request status 400
    And  validate response body for "vouchers cannot be null or empty"

   Scenario: Verify Created Vouchers in db

    Given a database connection is established
    When I execute the query "SELECT count(*) FROM vouchers"
    Then I should receive a non empty resultset

  Scenario: Get Number of vouchers each customer has for each voucher category

    Given Add Headers to Vouchers Request

      |KEY         |VALUE|
      |Content-Type| application/json |

    When send vouchers request by person and type to "api/v1/voucher/by-person-and-type"
    Then validate vouchers request status 200
    And  validate response body for vouchers