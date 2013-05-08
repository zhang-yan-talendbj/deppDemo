package com.caribe.stone.jsoup;

public class TestTools {

	public static void main(String[] args) {
		String string = "        assertEquals(=SMITH=, tokenArray[0]); assertEquals(=ROBERT=, tokenArray[1]); assertEquals(=12/04/1948=, tokenArray[2]); assertEquals(=FAMILY CARE PROTECTION PLAN=, tokenArray[3]); assertEquals(=0000MP9882=, tokenArray[4]); assertEquals(=Don't Care 1=, tokenArray[5]); assertEquals(=22487=, tokenArray[6]); assertEquals(=Payment: ( 15/5/2007 - 11/6/2007 )=, tokenArray[8]); assertEquals(Benefit.TYPE_INCOME_PROTECTION, tokenArray[12]); assertEquals(Payment.PAYABLE_TYPE_PAYMENT + =s=, tokenArray[13]); assertEquals(=30 Day(s)=, tokenArray[14]); assertEquals(=5 Years=, tokenArray[15]); assertEquals(=Removal Of Acoustic Neuroma=, tokenArray[16]); assertEquals(=Accountant=, tokenArray[17]); assertEquals(=$4,167.00=, tokenArray[18]); assertEquals(=$4,304.95=, tokenArray[19]); assertEquals(=75.00%=, tokenArray[20]); assertEquals(=$3,228.71=, tokenArray[21]); assertEquals(=12/02/2006=, tokenArray[22]); assertEquals(=16/02/2006=, tokenArray[23]); ";
		Boolean flag = false;
		for (int i = 0; i < string.length(); i++) {
			char charAt = string.charAt(i);
			if (charAt == '=') {
				if (flag) {
					flag = false;
				} else {
					flag = true;
				}
			}
			if (flag) {
				System.out.print(charAt);
			}
		}

	}
}
