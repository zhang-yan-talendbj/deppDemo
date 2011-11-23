package deppDemo

import java.awt.List;
import java.util.Collections.EmptyList;

scenario "test groovy list",{
	when "emptyList=[]", { emptyList=[]}

	then "This list is empty", { emptyList.size().shouldBe 0 }

	when "array [5, 6, 7, 8]",{
		list = [5, 6, 7, 8]
		assert list.get(2) == 7
		assert list[2] == 7
		assert list instanceof java.util.List
	}
	then "Array subscript from 1 start",{
		list.get(2).shouldBe 7
		list[2].shouldBe 7

		list.shouldBeA java.util.List
	}
	when "range 5..8",{ range = 5..8 }
	then "",{
		assert range.size() == 4
		assert range.get(2) == 7
		assert range[2] == 7
		assert range instanceof java.util.List
		assert range.contains(5)
		assert range.contains(8)
	}
	when "range = 5..<8",{
		// lets use an exclusive range
		range = 5..<8
	}
	then "",{
		assert range.size() == 3
		assert range.get(2) == 7
		assert range[2] == 7
		assert range instanceof java.util.List
		assert range.contains(5)
		assert ! range.contains(8)
		assert range.from==5
		assert range.to==7
	}
	when "range = 1..10",{
		//get the end points of the range without using indexes
		range = 1..10
		assert range.from == 1
		assert range.to == 10
	}
	when "range = 'a'..'d'",{ range = 'a'..'d' }
	then "",{
		assert range.size() == 4
		assert range.get(2) == 'c'
		assert range[2] == 'c'
		assert range instanceof java.util.List
		assert range.contains('a')
		assert range.contains('d')
		assert ! range.contains('e')
	}
	then "Ranges can be used to iterate using the for statement.",{
		j=1
		for (i in 1..10) { i.shouldBe j++ }
		k=1
		(1..10).each { i -> i.shouldBe k++ }
	}
}