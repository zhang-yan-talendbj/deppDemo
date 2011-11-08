package deppDemo
scenario "",{
	when "test Map", {
		map = [name:"Gromit", likes:"cheese", id:1234]
	}

	then "", {
		assert map.get("name") == "Gromit"
		assert map.get("id") == 1234
		assert map["name"] == "Gromit"
		assert map['id'] == 1234
		assert map instanceof java.util.Map
	}
	when "",{ emptyMap = [:] }
	then "",{
		assert emptyMap.size() == 0
		emptyMap.put("foo", 5)
		assert emptyMap.size() == 1
		assert emptyMap.get("foo") == 5
		list=[]
		map=[:]//null
		map.isNull
	}
	then "Getting efficient with the star-dot '*.' operator\\\
	You can perform operations on all the members of a collection using the '*.' operator",{
				assert [1, 3, 5]== ['a', 'few', 'words']*.size()
			}
	then "In addition to providing the literal syntax for collections, Groovy adds some additional methods to make working with collections more convenient. \\\
	As an example, you can find big words from a list as follows:",{
				def words = [ 'ant', 'buffalo', 'cat', 'dinosaur']
				assert words.findAll{ w -> w.size() > 4 } == ['buffalo', 'dinosaur']
				 words = ['ant', 'buffalo', 'cat', 'dinosaur']
				assert words.collect{ it[0] } == ['a', 'b', 'c', 'd']
				
			}
}