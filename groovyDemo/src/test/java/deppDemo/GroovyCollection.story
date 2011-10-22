package deppDemo
scenario "",{

	then "You can index into Strings", {
		def text = "nice cheese gromit!"
		def x = text[2]

		assert x == "c"
		assert x.class == String

		def sub = text[5..10]
		assert sub == 'cheese'

		def map = [name:"Gromit", likes:"cheese", id:1234]

		assert map["name"] == "Gromit"
		assert map.name == "Gromit"

		def list = [10, 11, 12]
		def answer = list[2]
		assert answer == 12
	}
	then "You can index into List",{
		def list = 100..200
		def sub = list[1, 3, 20..25, 33]
		assert sub == [
			101,
			103,
			120,
			121,
			122,
			123,
			124,
			125,
			133
		]
	}
	then "You can update items using the subscript operator too",{
		def list = ["a", "b", "c"]
		list[2] = "d"
		list[0] = list[1]
		list[3] = 5
		assert list == ["b", "b", "d", 5]
	}
	then "You can use negative indices to count from the end of the List, array, String etc.",{
		def text = "nice cheese gromit!"
		def x = text[-1]
		assert x == "!"

		def name = text[-7..-2]
		assert name == "gromit"
	}
	then "Also if you use a backwards range (the starting index is greater than the end index) then the answer is reversed.",{
		def text = "nice cheese gromit!"
		def name = text[3..1]
		assert name == "eci"
	}
	then "",{
		def player = new Expando()
		player.name = "Dierk"
		player.greeting = { "Hello, my name is $name" }

		println player.greeting()
		player.name = "Jochen"
		println player.greeting()
	}
}