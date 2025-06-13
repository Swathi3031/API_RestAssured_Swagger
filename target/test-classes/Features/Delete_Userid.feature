Feature: User Deletion API - DELETE Request Scenarios

Scenario: Verify that the user deletion operation is successful with existing userID
  Given The userID exists already
  When User sends DELETE request to delete the respective userID
  Then User receives 200 OK status with the success message

Scenario: Verify that the user deletion operation again after successful deletion with same userID
  Given The userID is already deleted
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify userID in response is deleted with requested userID
  Given The userID is already deleted
  When User sends DELETE request to delete the respective userID
  Then Response body contains the same user_id as requested

Scenario: Verify that the user deletion operation with invalid userID (alphanumeric and special characters)
  Given User enters userID with combination of number, alphabet and special characters
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify that the user deletion operation with invalid userID (only special characters)
  Given User enters userID with only special characters
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify that the user deletion operation with non-existent userID
  Given User enters userID with 5 invalid digits
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify that the user deletion operation with blank userID
  Given User enters userID with nothing (blank)
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify that the user deletion operation with zero value userID
  Given User enters userID with zero value
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify that the user deletion operation with negative integer userID
  Given User enters userID with negative value
  When User sends DELETE request to delete the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify that the user deletion operation with out of range userID
  Given User enters userID with out of range value
  When User sends DELETE request to delete the respective userID
  Then User receives 400 Bad Request with the message

Scenario: Verify the user deletion operation with invalid BaseURL
  Given User enters userID with invalid BaseURL and valid Endpoint
  When User sends DELETE request with the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify the user deletion operation with invalid Endpoint
  Given User enters userID with valid BaseURL and invalid Endpoint
  When User sends DELETE request with the respective userID
  Then User receives 404 Not Found error with the message

Scenario: Verify the user deletion operation with empty Endpoint
  Given User enters userID with valid BaseURL and empty Endpoint
  When User sends DELETE request with the respective userID
  Then User receives 404 Not Found error with the message