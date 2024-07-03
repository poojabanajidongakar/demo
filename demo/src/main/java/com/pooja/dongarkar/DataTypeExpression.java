package com.pooja.dongarkar;

public class DataTypeExpression {

	public static void main(String[] args) {

		float a = 6.54f + 6;

		System.out.println(a);

		/////////// Increment and dcrement operators

		int i = 56;

		int b = i++;

		System.out.println(i++);
		System.out.println(i);
		System.out.println(++i);
		System.out.println(i);

		int y = 7;
		int x = ++y + 8;
		int Z = x;
		System.out.println("Z " + Z);

		char ch = 'a';
		System.out.println("A  " + ++ch);

	}

}
