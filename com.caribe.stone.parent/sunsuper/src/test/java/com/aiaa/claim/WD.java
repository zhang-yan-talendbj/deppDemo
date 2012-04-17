package com.aiaa.claim;

import java.io.UnsupportedEncodingException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class WD {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str="WASX7209I: Ê¹ÓÃ SOAP Á¬½ÓÆ÷Á¬½Óµ½½Úµã cibwkdp000105Node04 ÉÏµÄ½ø³Ì¡°server1¡±£»½ø³ÌµÄÀàÐÍÎª£ºUnManagedProcess";
		System.out.println(new String(str.getBytes("gbk"),""));
	}
}
