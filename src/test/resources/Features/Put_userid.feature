Feature: Update User Details API - PUT Request Scenarios

Scenario: Update user details with valid BaseURL and valid Endpoints
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "update_valid_all_fields.json"
  Then User should receive status code 200

Scenario: UPDATE user details with no request body
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request without request body
  Then User should receive status code 400

Scenario: UPDATE user details with duplicate contact number
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "update_duplicate_contact.json"
  Then User should receive status code 409

Scenario: Update user details with less than ten digits contact number
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_short_contact.json"
  Then User should receive status code 400

Scenario: UPDATE user details with duplicate email ID
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "duplicate_email.json"
  Then User should receive status code 409

Scenario: UPDATE user detail with invalid emailID
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_email.json"
  Then User should receive status code 400

Scenario: UPDATE user details with invalid plot number
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_plotnumber.json"
  Then User should receive status code 400

Scenario: UPDATE user details with valid new plot number
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "valid_plotnumber.json"
  Then User should receive status code 200

Scenario: UPDATE user details with invalid new street name
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_street.json"
  Then User should receive status code 400

Scenario: UPDATE user details with valid new street name
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "valid_street.json"
  Then User should receive status code 200

Scenario: UPDATE user details with invalid country name
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_country.json"
  Then User should receive status code 400

Scenario: UPDATE user details with valid new country name
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "valid_country.json"
  Then User should receive status code 200

Scenario: UPDATE user details with invalid zipcode
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_zipcode.json"
  Then User should receive status code 400

Scenario: UPDATE user details with valid new zipcode
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "valid_zipcode.json"
  Then User should receive status code 200

Scenario: UPDATE user details with invalid firstname
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_firstname.json"
  Then User should receive status code 400

Scenario: UPDATE user details with valid firstname
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "valid_firstname.json"
  Then User should receive status code 200

Scenario: UPDATE user details with invalid lastname
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "invalid_lastname.json"
  Then User should receive status code 400

Scenario: UPDATE user details with valid lastname
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "valid_lastname.json"
  Then User should receive status code 200

Scenario: UPDATE user details with empty contact number
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_contact.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty emailID
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_email.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty firstname
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_firstname.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty lastname
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_lastname.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty plotnumber
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_plotnumber.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty street
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_street.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty country
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_country.json"
  Then User should receive status code 400

Scenario: UPDATE user details with empty zipcode
  Given User prepares PUT request with authorization and valid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "empty_zipcode.json"
  Then User should receive status code 400

Scenario: UPDATE user details with invalid BaseURL
  Given User prepares PUT request with authorization and invalid BaseURL and valid Endpoint
  When User sends PUT request with JSON file "update_valid_all_fields.json"
  Then User should receive status code 404

Scenario: UPDATE user details with invalid endpoint
  Given User prepares PUT request with authorization and valid BaseURL and invalid Endpoint
  When User sends PUT request with JSON file "update_valid_all_fields.json"
  Then User should receive status code 404

Scenario: UPDATE user details with empty endpoint
  Given User prepares PUT request with authorization and valid BaseURL and empty Endpoint
  When User sends PUT request with JSON file "update_valid_all_fields.json"
  Then User should receive status code 404

Scenario: Add a New User with all fields without Authorization
  Given User prepares PUT request with valid BaseURL and valid Endpoint without Auth
  When User sends PUT request with JSON file "update_valid_all_fields.json"
  Then User should receive status code 401