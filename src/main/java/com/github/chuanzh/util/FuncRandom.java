package com.github.chuanzh.util;

import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

public class FuncRandom {


	/**
	 * 随机生成一个源文件ID
	 * 
	 * @return 一个20位的随机ID
	 */
	public static String randomId() {
		StringBuffer sb = new StringBuffer(); 
		for (int i = 0; i < 20; i++) {
			sb.append(randomChar());
		}

		return sb.toString();
	}

	public static int randomNumber() {
		LinkedList ls = new LinkedList();
		Random random = new Random();
		int ch = 0;

		// 0-9
		for (int i = 0; i < 10; i++) {
			ls.add(String.valueOf(48 + i));
		}

		int index = random.nextInt(ls.size());
		if (index > (ls.size() - 1)) {
			index = ls.size() - 1;
		}
		ch =  Integer.parseInt(String.valueOf(ls.get(index)));

		return ch;
	}

	public static char randomChar() {
		LinkedList ls = new LinkedList();
		Random random = new Random();
		char ch = '0';

		// 0-9
		for (int i = 0; i < 10; i++) {
			ls.add(String.valueOf(48 + i));
		}

		// A-Z
		for (int i = 0; i < 26; i++) {
			ls.add(String.valueOf(65 + i));
		}

		// a-z
		for (int i = 0; i < 26; i++) {
			ls.add(String.valueOf(97 + i));
		}

		int index = random.nextInt(ls.size());
		if (index > (ls.size() - 1)) {
			index = ls.size() - 1;
		}
		ch = (char) Integer.parseInt(String.valueOf(ls.get(index)));

		return ch;
	}

	public static String randomNumber(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(randomNumber());
		}
		return sb.toString().substring(0,length);
	}
	 

	public static String randomChar(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(randomChar());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(FuncRandom.createSequence());
	}
	public static String createSequence(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
