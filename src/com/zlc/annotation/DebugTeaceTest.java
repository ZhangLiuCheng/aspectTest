package com.zlc.annotation;

public class DebugTeaceTest {
	
	@DebugTrace
	public DebugTeaceTest() {
		super();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	@DebugTrace
	public void sayHello() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("Hello Aspectj!  ");
	}

	public static void main(String[] args) {
		DebugTeaceTest t = new DebugTeaceTest();
		t.sayHello();
	}
}
