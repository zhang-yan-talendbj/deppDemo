package com.caribe.stone.tapestry.pojo;

import java.io.Serializable;

public class Customer implements Serializable {
    private Integer id;
    private String fullName;
    private String memberLevel;
	private boolean display;

    public Customer(Integer id, String fullName, String memberLevel,boolean display) { 
        this.id = id;
        this.display=display;
        this.fullName = fullName;
        this.memberLevel = memberLevel; 
    }

    public boolean isDisplay() {
		return display;
	}

	public Integer getId() { return id; }
		
    public String getFullName() { return fullName; }

    public String getMemberLevel() { return memberLevel; }
}