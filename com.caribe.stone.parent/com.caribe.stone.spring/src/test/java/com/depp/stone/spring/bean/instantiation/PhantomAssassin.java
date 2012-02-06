package com.depp.stone.spring.bean.instantiation;

public class PhantomAssassin {

	@Override
	public String toString() {
		return "幻影刺客: [weapon=" + weapon + ",shoes =" + shoes + "]";
	}

	private Battlefury weapon;
	private FlyingShoes shoes;

	public FlyingShoes getShoes() {
		return shoes;
	}

	public void setShoes(FlyingShoes shoes) {
		this.shoes = shoes;
	}

	public Battlefury getWeapon() {
		return weapon;
	}

	public void setWeapon(Battlefury weapon) {
		this.weapon = weapon;
	}

	public PhantomAssassin(Battlefury weapon) {
		this.weapon = weapon;
	}

}
