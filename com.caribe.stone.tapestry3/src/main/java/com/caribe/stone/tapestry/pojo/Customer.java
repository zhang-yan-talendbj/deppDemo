package com.caribe.stone.tapestry.pojo;

import java.io.Serializable;

public class Customer implements Serializable {
    private Integer id;
    private String fullName;
    private String memberLevel;

    public Customer(Integer id, String fullName, String memberLevel) { 
        this.id = id;
        this.fullName = fullName;
        this.memberLevel = memberLevel; 
    }

    public Integer getId() { return id; }
		
    public String getFullName() { return fullName; }

    public String getMemberLevel() { return memberLevel; }
}