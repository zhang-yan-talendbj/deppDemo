Feature: Test mult paramters

	Scenario Outline: input 2 numbers
	Given a new List
	When add <a>, <b>
	Then this list has <c> elements
	
	Examples: Single digits
    | a | b | c  |
    | 1 | 1 | 2  |
    | 2 | 2 | 2 |
