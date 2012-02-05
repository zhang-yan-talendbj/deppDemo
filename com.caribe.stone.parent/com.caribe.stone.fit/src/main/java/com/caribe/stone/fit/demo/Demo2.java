package com.caribe.stone.fit.demo;

import com.tddinaction.fit.fixtures.Calculator;

import fit.ColumnFixture;

public class Demo2 extends ColumnFixture {

	// the "setters" are public fields
	public double num1;

	public double num2;

	public char operator;

	public Demo2() {
	}

	// the "getters" are no-argument methods that return something
	public double result() {
		Calculator calculator = Calculator.getInstance();
		switch (operator) {
		case '+':
			return calculator.add(num1, num2);
		case '-':
			return calculator.subtract(num1, num2);
		case '*':
			return calculator.multiply(num1, num2);
		case '/':
			return calculator.divide(num1, num2);
		default:
			throw new IllegalArgumentException("Unknown operator: " + operator);
		}
	}

}
