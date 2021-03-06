Feature: ADT ArrayList
	
	Scenario: add and remove an emelent at index X then get it at X
	Given a new list initialize following element:
      | element  | index |
      | milk  | 1     |
      | bread | 2     |
      | soap  | 3     |
    When add a element "rice" at index 2
	Then get element from list index 1 is "milk"
	Then get element from list index 2 is "rice"
	Then get element from list index 3 is "bread"
	Then get element from list index 4 is "soap"
	Then the list has 4 element
	
    Scenario: remove an emelent at index X then get it at X
	Given a new list initialize following element:
      | element  | index |
      | milk  | 1     |
      | bread | 2     |
      | soap  | 3     |
    When remove a element at index 2
    Then we get remove element "bread"
	Then get element from list index 1 is "milk"
	Then get element from list index 2 is "soap"
	Then index of element "soap" is 2
	Then the list has 2 element