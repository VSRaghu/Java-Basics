package com.vivek.myexperiments.thread;

import java.util.ArrayList;

public class FinalExperiment {
	public static void main(String[] args) {
		final String a= "Test";
		final ArrayList<String> list = new ArrayList<String>();
		list.add("Test1");
		list.add("Test2");
		System.out.print(list.toString());
		
	} 
}
