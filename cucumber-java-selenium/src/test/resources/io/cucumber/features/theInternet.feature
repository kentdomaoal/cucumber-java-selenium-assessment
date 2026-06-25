Feature: The Internet
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'

  Background:
    Given the page under test is 'https://the-internet.herokuapp.com'

  @TEST_TI_0001
  Scenario: Homepage has a list of links to Expected examples
    When the 'Available Examples' header is displayed
    Then the expected list of example links should be displayed correctly

  @TEST_TI_0002
  Scenario: Basic Auth allows validated access
    When the 'Basic Auth' example is opened
    And valid credentials are supplied
    Then 'Congratulations' should be displayed

  @TEST_TI_0003
  Scenario: Sortable Data Tables - Example 1 displays the expected 4 results
    When the 'Sortable Data Tables' example is opened
    And the 'Example 1' table header is displayed
    Then the 'Example 1' table should display the following records:
      | Smith     | John       | jsmith@gmail.com      | $50.00  | http://www.jsmith.com    |
      | Bach      | Frank      | fbach@yahoo.com       | $51.00  | http://www.frank.com     |
      | Doe       | Jason      | jdoe@hotmail.com      | $100.00 | http://www.jdoe.com      |
      | Conway    | Tim        | tconway@earthlink.net | $50.00  | http://www.timconway.com |
