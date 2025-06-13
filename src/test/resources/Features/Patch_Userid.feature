Feature: Update User Details API - PATCH Request Scenarios


Scenario: Verify that user is able to update FirstName
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_firstname.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update LastName
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_lastname.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update contact_number
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_contactnumber.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update email_id
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_emailid.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update plotNumber
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_plotnumber.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update Street
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_street.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update state
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_state.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update Country
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_country.json"
  Then User receives 200 status code

Scenario: Verify that user is able to update zipCode
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_zipcode.json"
  Then User receives 200 status code

Scenario: Verify that user is not able to update contact_number with Characters
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_invalid_contactnumber.json"
  Then User receives 400 status code

Scenario: Verify that user is not able to update Invalid email_id
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_invalid_email.json"
  Then User receives 400 status code

Scenario: Verify that user is not able to update Invalid state with Numeric Data
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_invalid_state.json"
  Then User receives 400 status code

Scenario: Verify that user is not able to update Invalid Country with Numeric Data
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_invalid_country.json"
  Then User receives 400 status code

Scenario: Verify that user is not able to update Invalid zipCode with Characters
  Given Patch request with new data, BaseURL, valid Endpoint
  When User creates Patch request with file "patch_invalid_zipcode.json"
  Then User receives 400 status code

Scenario: Verify that user is not able to update FirstName with invalid BaseURL
  Given Patch request with new data, invalid BaseURL, valid Endpoint
  When User creates Patch request with file "patch_firstname.json"
  Then User receives 404 status code

Scenario: Verify that user is not able to update FirstName with valid BaseURL, Invalid EndPoint
  Given Patch request with new data, valid BaseURL, invalid Endpoint
  When User creates Patch request with file "patch_firstname.json"
  Then User receives 400 status code

Scenario: Verify that user is not able to update FirstName with valid BaseURL, empty EndPoint
  Given Patch request with new data, valid BaseURL, empty Endpoint
  When User creates Patch request with file "patch_firstname.json"
  Then User receives 404 status code

Scenario: Verify that user is not able to update FirstName with BaseURL, valid Endpoint and No Auth
  Given Patch request with new data, valid BaseURL, valid Endpoint and No Auth
  When User creates Patch request with file "patch_firstname.json"
  Then User receives 401 status code