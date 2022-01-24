package com.demo.sketches;

import org.testng.annotations.Test;

public class B_Class extends A_Class{
	
	@Test(priority=1)
	public void b_Class_Demo() {
		System.out.println("executing b_class_demo");
	}
	
	@Test(priority=2)
	public void b_Class_Demo1() {
		System.out.println("executing b_class_demo2");
	}
	
	@Test(priority=3)
	public void b_Class_Demo3() {
		System.out.println("executing b_class_demo3");
	}

}
