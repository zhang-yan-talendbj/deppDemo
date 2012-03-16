package com.aiaa.claim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

	public static void main(String[] args) {
		String str="Release Payments Complete - 5 released, 10 remain unreleased, 0 encountered errors (See System Logs)";
		
		Pattern pp = Pattern.compile("Release Payments Complete .* \\(See System Logs\\)");
		System.out.println(pp.matcher(str).matches());
		
		Pattern p=Pattern.compile("\\(.*\\)");
		Matcher m = p.matcher(str);
		while(m.find()){
			String claimNumber = m.group();
			System.out.println(claimNumber.substring(1, claimNumber.length()-1));
		}
	}
}
