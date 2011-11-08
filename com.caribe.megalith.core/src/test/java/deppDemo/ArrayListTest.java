<<<<<<< HEAD
package deppDemo;

import deppDemo.com.depp.dsa.adt.impl.ArrayList;
import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-7-10
 * Time: 上午1:31
 * To change this template use File | Settings | File Templates.
 */
public class ArrayListTest extends TestCase{

    public void testAdd(){
        ArrayList list=new ArrayList();
        list.insert(0,"abc");
        assertEquals("abc",list.get(0));
    }
}
=======
package deppDemo;

import deppDemo.com.depp.dsa.adt.impl.ArrayList;
import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-7-10
 * Time: 上午1:31
 * To change this template use File | Settings | File Templates.
 */
public class ArrayListTest extends TestCase{

    public void testAdd(){
        ArrayList list=new ArrayList();
        list.insert(0,"abc");
        assertEquals("abc",list.get(0));
    }
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
