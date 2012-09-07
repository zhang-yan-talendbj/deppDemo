Feature: Hello World
	
	Scenario Outline:Cucumber Hello World
	Given a new String <name>
	When I call hello method
	Then I get a String <greeting>
	
	Examples: this is a simple example
    | name | greeting | 
    | "bruce" | "Hello World, bruce" |
    | "test" | "Hello World, test" |

	
	