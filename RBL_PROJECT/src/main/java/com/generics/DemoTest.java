package com.generics;

import java.text.DecimalFormat;

public class DemoTest {

	
	
	
	public static void main(String[] args) {
		
		String s=getRandomStringNO(21);
		System.out.println(s);
		System.out.println(s.length());

		
	
	}
	
	
	
	public static String getRandomStringNO(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}
	
}
