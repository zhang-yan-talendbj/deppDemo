package com.aiaa.claim;

import java.io.IOException;

import freemarker.template.TemplateException;

public class CopyOfDemo {

	public static void main(String[] args) throws IOException, TemplateException {
		System.out.println(isOngoing() && !isDeath() && !isTPD() && isClaimEscalation());
		System.out.println(123);
	}

	private static boolean isTPD() {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean isDeath() {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean isOngoing() {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean isClaimEscalation() {
		// TODO Auto-generated method stub
		return true;
	}
}
