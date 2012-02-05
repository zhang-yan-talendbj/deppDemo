package com.arcmind.jsfquickstart.model;

/**
 * Calculator. Simple POJO.
 *
 * @author Rick Hightower
 */
public class Calculator {
 
    /** First number used in operation. */
    private int firstNumber = 0;

    /** Result of operation on first number and second number. */
    private int result = 0;

    /** Second number used in operation. */
    private int secondNumber = 0;
    
    /** Add the two numbers. */
    public void add() {
		result = firstNumber + secondNumber;
	}

    /** Multiply the two numbers. */
	public void multiply() {
		result = firstNumber * secondNumber;
	}

    /** Divide the two numbers. */
	public void divide() {
		this.result = this.firstNumber / this.secondNumber;
	}
	
	/** Clear the results. */
	public void clear () {
		this.firstNumber = 0;
		this.secondNumber = 0;
		result = 0;
	}
    
	
	/* ---------- properties ------------- */
	
	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

    
}
