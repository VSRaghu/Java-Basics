package com.vivek.myexperiments.thread;

import java.util.LinkedList;

public class ProducerConsumer {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		PC pc = new PC(list);
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				pc.produce();
			}
		});
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				pc.consume();
			}
		});
		producer.start();
		consumer.start();
	}
}

class PC {
	int number = 1;
	LinkedList<Integer> list;
	int capacity = 3;
	static int MAX = 5;

	public PC(LinkedList<Integer> list) {
		this.list = list;
	}

	public void produce() {
		try {
			while (true && number <= MAX) {
				synchronized (this) {
					if (list.size() == capacity) {
						wait();
					} else {
						System.out.println("Producer producing - " + number);
						list.add(number);
						number++;
						Thread.sleep(1000);
						notifyAll();
					}
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public void consume() {
		try {
			while (true && number<=MAX) {
				synchronized (this) {
					if (list.isEmpty()) {
						wait();
					} else {
						System.out.println("Consumer consuming - " + list.getFirst());
						list.removeFirst();
						Thread.sleep(1000);
						number--;
						notifyAll();
					}
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
