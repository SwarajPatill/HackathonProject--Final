Feature: UsedCars Details
	
	@sanity
  Scenario: Get Popular Models
    Given the user is on application
    When the user hover to UsedCars
    And the user click on location chennai
    And get the popular models
