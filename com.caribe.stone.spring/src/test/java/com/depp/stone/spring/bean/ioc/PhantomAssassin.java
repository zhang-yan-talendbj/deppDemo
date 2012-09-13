package com.depp.stone.spring.bean.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pa")// pa is bean name
public class PhantomAssassin {

	@Override
	public String toString() {
		return "幻影刺客: [weapon=" + weapon + ",shoes =" + shoes + "]";
	}

	@Autowired()
	private Battlefury weapon;
	@Autowired()
	private FlyingShoes shoes;

	public PhantomAssassin() {
	}

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
