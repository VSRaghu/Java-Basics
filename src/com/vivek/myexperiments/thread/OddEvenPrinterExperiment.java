package com.vivek.myexperiments.thread;

public class OddEvenPrinterExperiment {
	
	public static boolean printOdd = true;
	//Object monitor = new Object();

	public static void main(String[] args) {
		OddEvenPrinter oddEvenPrinter = new OddEvenPrinter();
		Thread tOdd = new Thread(new Runnable() {
			@Override
			public void run() {
				oddEvenPrinter.printOdd();
			}
		});
		Thread tEven = new Thread(new Runnable() {
			@Override
			public void run() {
				oddEvenPrinter.printEven();
			}
		});
		tOdd.start();
		tEven.start();
	}
}

class OddEvenPrinter {
	boolean printOdd = true;
	int MAX = 20;
	int number = 1;
	public void printOdd() {
		while(number < MAX) {
			try {
				synchronized (this) {
					if(printOdd) {
						System.out.println("Thread Odd printing - "+number);
						number++;
						printOdd = false;
						notifyAll();
					}else {
						wait();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printEven() {
		while(number < MAX) {
			try {
				synchronized (this) {
					if(!printOdd) {
						System.out.println("Thread Even printing - "+number);
						number++;
						printOdd = true;
						notifyAll();
					}else {
						wait();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

