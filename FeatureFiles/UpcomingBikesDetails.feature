Feature: Bike Details
	
	@sanity  @regression
  Scenario: Get Bike Details
    Given the user is on application
    When the user clicked on NewBikes
    And the user select the Honda from dropdown
    And the user clicked on the view more Bikes
    And get all Bike details for selected manufacturer 
