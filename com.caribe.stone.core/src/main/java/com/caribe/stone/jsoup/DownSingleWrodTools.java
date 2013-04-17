package com.caribe.stone.jsoup;

import java.io.File;

public class DownSingleWrodTools {
	public static void main(String[] args) {
		File rpFromICB = WordDemo.getGAFromICB("theater");
		System.out.println(rpFromICB);
	}
}
