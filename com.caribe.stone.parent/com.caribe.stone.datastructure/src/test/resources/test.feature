Feature: ADT ArrayList
	
	Scenario: add an emelent at index X then get it at X
	Given a new list initialize following element:
      | element  | index |
      | milk  | 1     |
      | bread | 2     |
      | soap  | 3     |
	Then get element from list index 1 is "milk"
	Then get element from list index 2 is "bread"
	Then get element from list index 3 is "soap"