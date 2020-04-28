package com.vivek.myexperiments.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsInSequenceExperiment {

	public static int MAX = 30;
	Object monitor = new Object();
	AtomicInteger number = new AtomicInteger(1);

	public static void main(String[] args) {
		ThreadsInSequenceExperiment tnp = new ThreadsInSequenceExperiment();
		Thread t1 = new Thread(tnp.new NumberPrinter(0, 3));
		Thread t2 = new Thread(tnp.new NumberPrinter(1, 3));
		Thread t3 = new Thread(tnp.new NumberPrinter(2, 3));

		t3.start();
		t1.start();
		t2.start();
	}

	class NumberPrinter implements Runnable {
		int threadId;
		int numOfThreads;

		public NumberPrinter(int id, int nubOfThreads) {
			threadId = id;
			this.numOfThreads = nubOfThreads;
		}

		public void run() {
			print();
		}

		private void print() {
			try {
				while (true && number.get() <= MAX) {
					Thread.sleep(1000l);
					synchronized (monitor) {
						if (number.get() % numOfThreads != threadId) {
							monitor.wait();
						} else {
							System.out.println("ThreadId [" + threadId + "] printing -->" + number.getAndIncrement());
							monitor.notifyAll();
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}