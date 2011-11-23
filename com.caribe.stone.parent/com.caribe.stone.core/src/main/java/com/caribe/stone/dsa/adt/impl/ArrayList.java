package com.caribe.stone.dsa.adt.impl;

import com.caribe.stone.dsa.adt.exception.OutOfBoundaryException;

import deppDemo.com.depp.dsa.adt.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-7-10
 * Time: 上午12:04
 * To change this template use File | Settings | File Templates.
 */
public class ArrayList implements List {
    private Object[] data;
    private int size;//线性表中元素数据个数

    public int getSize() {
        return size;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList() {

        this(10);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("error");
        }
        data = new Object[initialCapacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size > 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean contains(Object e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int indexOf(Object e) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void insert(int i, Object e) throws OutOfBoundaryException {
        if (i < 0 || i > size) {
            throw new OutOfBoundaryException("error,指定的插入位置越界:" + i);
        }

        for (int j = size; j < i; j--) {
            data[j] = data[j - 1];
        }
        data[i] = e;
        size++;
    }

    public boolean insertBefore(Object obj, Object e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean insertAfter(Object obj, Object e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object remove(int i) throws OutOfBoundaryException {
        if (i < 0 || i > size) {
            throw new OutOfBoundaryException("error:要删除的元素项越界:" + i);
        }
        Object obj = data[i];
        for (int j = i; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        data[--size] = null;
        return obj;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean remove(Object e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object replace(int i, Object e) throws OutOfBoundaryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object get(int i) throws OutOfBoundaryException {
        if(i<0 || i>size){
            throw new OutOfBoundaryException("error");
        }
        return data[i];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
