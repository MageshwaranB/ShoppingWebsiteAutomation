package com.testing;

class Maths{
	int num1;
	public Maths(int num1) {
		this.num1=num1;
	}
	public Maths performAdd(int num2) {
		num1=num1+num2;
		return this;
	}
	public Maths performMultiplication(int num2) {
		num1=num1*num2;
		return this;
	}
	
	public void toDisplay() {
		System.out.println(num1);
	}
	public void clear() {
		num1=0;
	}
}

public class MethodChaining {
	public static void main(String[] args) {
		Maths math=new Maths(7);
		math.performAdd(7).performAdd(7).performAdd(21).toDisplay();
		math.clear();
		math.performAdd(5).performMultiplication(6).toDisplay();
	}
}
