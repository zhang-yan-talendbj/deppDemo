package com.caribe.stone.DesignPattern.simpleFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
	private static Logger LOG = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String resoult;
		Integer intA = null;
		Integer intB = null;
		String operation = null;
		String commander = "";
		while (commander != null) {
			if (intA == null) {
				LOG.info("Please input a number A:");
				commander = br.readLine();
				try {
					intA = Integer.parseInt(commander);
				} catch (NumberFormatException e1) {
					LOG.info(commander + " is not a number,plz input a number.");
				}
			} else if (intB == null) {
				LOG.info("Please input a number b:");
				commander = br.readLine();
				try {
					intB = Integer.parseInt(commander);
				} catch (NumberFormatException e1) {
					LOG.info(commander + " is not a number,plz input a number.");
				}
			} else if (operation == null) {
				LOG.info("Please input a operation:");
				commander = br.readLine();
				if (commander.length() == 1 && "+-*/".indexOf(commander) >= 0) {
					operation = commander;
				} else {
					LOG.info(commander
							+ " is not a operation,plz input a operation.");
				}
			}else{
				resoult = calculate(intA,intB,operation);
				System.out.println("Result is :"+resoult);
			}
			
			if ("exit".equals(commander)) {
				break;
			}
		}
	}

	private static String calculate(Integer intA, Integer intB, String operation) {
		if (operation == "+") {

		} else if (operation == "-") {

		} else if (operation == "*") {

		} else if (operation == "/") {

		}
		return null;
	}
}
