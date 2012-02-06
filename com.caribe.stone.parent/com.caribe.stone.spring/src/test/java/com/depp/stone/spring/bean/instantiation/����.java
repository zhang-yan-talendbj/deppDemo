package com.depp.stone.spring.bean.instantiation;

public class 吕布 {

	@Override
	public String toString() {
		return "吕布 [weapon=" + weapon + ", horse=" + horse + "]";
	}

	private 方天画戟 weapon;
	private 赤免 horse;

	public 赤免 getHorse() {
		return horse;
	}

	public void setHorse(赤免 horse) {
		this.horse = horse;
	}

	public 方天画戟 getWeapon() {
		return weapon;
	}

	public void setWeapon(方天画戟 weapon) {
		this.weapon = weapon;
	}

	public 吕布(方天画戟 weapon) {
		this.weapon = weapon;
	}

}
