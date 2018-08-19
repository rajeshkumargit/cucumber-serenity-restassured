Feature: Login

	I login to application I should be able to find my user credentials

	Scenario Outline: Unauthorized Login
    Given I provide login credentials "<username>" and "<password>"
    When I send request to login
    Then login failed
 
  Examples: Invalid
  			|username|password|
		  	|test-user|abc122|
		  	
	Scenario Outline: Successful Login
    Given I provide login credentials "<username>" and "<password>"
    When I send request to login
    Then login is successful
  Examples: Valid
  			|username|password|
		  	|test-user|abc123|
  	
    
    