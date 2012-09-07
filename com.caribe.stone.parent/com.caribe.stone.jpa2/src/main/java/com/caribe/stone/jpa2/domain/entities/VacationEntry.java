package com.caribe.stone.jpa2.domain.entities;

import javax.persistence.Embeddable;

@Embeddable
public class VacationEntry {

	private int daysTaken;

	public int getDaysTaken() {
		return daysTaken;
	}

	public void setDaysTaken(int daysTaken) {
		this.daysTaken = daysTaken;
	}
}
