package com.vivek.myexperiments.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
	public static void main(String[] args) {
	BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
	PCBlockingQueue pc = new PCBlockingQueue(queue);
	
	Thread producer = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				pc.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	Thread consumer = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				pc.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	producer.start();
	consumer.start();
	}
}

class PCBlockingQueue{
	int limit = 10;
	int number = 1;
	BlockingQueue<Integer> queue;
	
	PCBlockingQueue(BlockingQueue<Integer> queue){
		this.queue = queue;
	}
	
	public void produce() throws InterruptedException {
		while(number <= limit) {
			System.out.println("Produced - "+ number);
			queue.put(number);
			number++;
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			Integer  num = queue.take();
			System.out.println("consumed - "+ num);
		}
	}
}
