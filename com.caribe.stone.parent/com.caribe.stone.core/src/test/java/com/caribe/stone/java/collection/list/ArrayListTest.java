package com.caribe.stone.java.collection.list;

import java.util.ArrayList;

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
        list.add(0,"abc");
        assertEquals("abc",list.get(0));
    }
}
