package com.aiaa.claim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

	public static void main(String[] args) {
		String str="Record Claim (23259) Complete";
		Pattern p=Pattern.compile("\\(.*\\)");
		Matcher m = p.matcher(str);
		while(m.find()){
			String claimNumber = m.group();
			System.out.println(claimNumber.substring(1, claimNumber.length()-1));
		}
	}
}
