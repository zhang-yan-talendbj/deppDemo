package foo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.DynamicSubscript;

public class Root {  
    public static final String      SIZE_STRING = "size";  

    private int[]  array = { 1, 2, 3, 4 };  

    private Map  map = new HashMap(23);  

    private List list = Arrays.asList(new Object[] { null, this, array });  

	public Root(){  

         map.put( "test", this );  

         map.put( "array", array );  

         map.put( "list", list );  

         map.put( "size", new Integer(5000) );  

         map.put( DynamicSubscript.first, new Integer(99) );  

    }

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}  
}  