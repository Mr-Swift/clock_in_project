package com.njusc.npm.utils.util;

public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Test t=new Test();
					System.out.println(t.hashCode());
				}
			}).start();
		}
		Test t=new Test();
System.out.println(t.hashCode());
	}

}
