package com.vivek.myexperiments.thread;

import java.util.List;

public final class MyImmutable {//can't extend this.Otherwise someone might modify getter  methods.
	private final String name; // Should not be accessible outside class.
	private final int age;
	private final List foodLikings; // You will still be able to add elements in the list.Soclone the object.
	public MyImmutable(String name,int age, List foods) {
		this.name = name;
		this.age = age;
		this.foodLikings = foods;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public List getFoodLikings() {
		return foodLikings;
	}
}
