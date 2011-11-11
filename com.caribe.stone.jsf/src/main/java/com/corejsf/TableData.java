package com.corejsf;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.corba.se.spi.orbutil.fsm.Action;

public class TableData {

    private final ArrayList<Name> names;

    public TableData() {
        names = new ArrayList<Name>();
        names.add(new Name(1, "001", "aaa"));
        names.add(new Name(2, "002", "bbb"));
        names.add(new Name(3, "003", "ccc"));
        names.add(new Name(4, "004", "ddd"));
        names.add(new Name(5, "005", "eee"));

    }

    public ArrayList<Name> getNames() {
        return names;
    }

    public String deleteNames() {
        for (Iterator iterator = names.iterator(); iterator.hasNext();) {
            Name name = (Name) iterator.next();
            if (name.isMarkedForDeletion()) {
                iterator.remove();
            }
        }
        return null;
    }

    public int getNumberOfNamesMarkedForDeletion() {
        int cnt = 0;
        for (Name name : names) {
            if (name.isMarkedForDeletion()) {
                ++cnt;
            }
        }
        return cnt;

    }

}
