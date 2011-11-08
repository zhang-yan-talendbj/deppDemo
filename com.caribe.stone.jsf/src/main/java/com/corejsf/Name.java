package com.corejsf;

public class Name {
    private int id;
    private String first;

    private String last;

    private boolean markedForDeletion = false;

    public Name(int id, String first, String last) {

        this.id = id;
        this.first = first;

        this.last = last;

    }

    public void setFirst(String newValue) {
        first = newValue;
    }

    public String getFirst() {
        return first;
    }

    public void setLast(String newValue) {
        last = newValue;
    }

    public String getLast() {
        return last;
    }

    public boolean isMarkedForDeletion() {
        return markedForDeletion;
    }

    public void setMarkedForDeletion(boolean newValue) {

        markedForDeletion = newValue;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
