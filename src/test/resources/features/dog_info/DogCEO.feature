@SMOKE
Feature: Testing different requests on the dog ceo api application
  Description: As a user, I want to be able to perform the following API GET request:

  - Get list of all dog breeds from the application
  - Using code, verify “retriever” breed is within the list
  - Perform an API request to produce a list of sub-breeds for “retriever
  - Perform an API request to produce a random image / link for the sub-breed “golden”

  Scenario: Get list of all dog breeds from the application
    Given User sends a GET request to get list of all dog breeds

  Scenario Outline: Verify Retriever is within list of breeds
    Given User sends a GET request to verify retriever is within list <value>
    Examples:
      | value     |
      | retriever |

  Scenario: Get list of sub-breeds for retriever
    Given User sends a GET request to get list of sub-breeds for retriever

  Scenario: Get random image link for the sub-breed golden
    Given User sends a GET request to random image link for the golden sub-breed



