package org.example.dao;

public class Dao {
    protected int lastId;
    public Dao() {
        lastId = 0;
    }
    public int getLastID () {

        return lastId;
    }
    public int getNewId () {
        return lastId + 1;
    }
}
