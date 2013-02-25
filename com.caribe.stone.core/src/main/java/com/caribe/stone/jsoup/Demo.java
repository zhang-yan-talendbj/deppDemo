package com.caribe.stone.jsoup;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class Demo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "Opt up offer - Lifeapp - Individual Transfer - Default Cover - Life/age event  cover - White collar/professional cover - Full underwriting - Commercial agreement - Takeover terms - Reinstatement of cover";
		String[] arr = str.split(" - ");
		int i=0;
		for (String s : arr) {
//		    DELETE FROM CLAIMS.BENEFIT_TYPE_LKUP;
//			INSERT INTO CLAIMS.BENEFIT_TYPE_LKUP (ID, VALUE, ORDER) VALUES(1, 'Death Benefit', 1);
			System.out.println("INSERT INTO CLAIMS.BENEFIT_TYPE_LKUP (ID, VALUE, ORDER) VALUES(" +
					++i +
					", " +
					"'" +
					s +
					"'" +
					", " +
					i +
					");");
		}
	}
	public static void TEST() throws UnsupportedEncodingException {
		String x = "http://localhost:8091/wiki/%E9%A1%B9%E7%9B%AE%E7%AE%A1%E7%90%86";
		String str = java.net.URLDecoder.decode(x,"utf-8");
		System.out.println(str);
	}
	private String content;
	public void name() {
		Calendar.getInstance();
		
	}
}
