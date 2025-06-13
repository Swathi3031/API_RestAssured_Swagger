Feature: User Deletion API by userFirstName - DELETE Request Scenarios

Scenario: Verify that the user deletion operation is successful with existing userFirstName
  Given The userFirstName exists already
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 200 OK status with success message

Scenario: Verify that the user deletion operation again after successful deletion with same userFirstName
  Given The userFirstName is already deleted
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with message

Scenario: Verify userFirstName in response is deleted with requested userFirstName
  Given The userFirstName is already deleted
  When User sends DELETE request to delete the respective userFirstName
  Then Response body contains the same userFirstName as requested

Scenario: Verify that the user deletion operation with invalid userFirstName (alphanumeric and special characters)
  Given User enters userFirstName with combination of number, alphabet and special characters
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify that the userFirstName deletion operation with only special characters
  Given User enters userFirstName with only special characters
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify that the userFirstName deletion operation with only digits
  Given User enters userFirstName with only 5 digits
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify that the userFirstName deletion operation with non-existent/invalid userFirstName
  Given User enters userFirstName with non-existent userFirstName
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify that the userFirstName deletion operation with zero value
  Given User enters userFirstName with zero value
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify that the userFirstName deletion operation with negative integer value
  Given User enters userFirstName with negative integer value
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify that the userFirstName deletion operation with out of range value
  Given User enters userFirstName with out of range value
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify the userFirstName deletion operation with invalid BaseURL
  Given User enters userFirstName with invalid BaseURL and valid Endpoint
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify the userFirstName deletion operation with invalid Endpoint
  Given User enters userFirstName with valid BaseURL and invalid Endpoint
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message

Scenario: Verify the userFirstName deletion operation with empty Endpoint
  Given User enters userFirstName with valid BaseURL and empty Endpoint
  When User sends DELETE request to delete the respective userFirstName
  Then User receives 404 Not Found error with userFirstName message