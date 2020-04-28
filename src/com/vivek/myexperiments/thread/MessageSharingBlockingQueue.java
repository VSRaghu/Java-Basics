package com.vivek.myexperiments.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MessageSharingBlockingQueue {
	public static void main(String[] args) {
		int messages = 100;
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(messages);
		BlockingQueue<String> queueA = new ArrayBlockingQueue<>(messages/2);
		BlockingQueue<String> queueB = new ArrayBlockingQueue<>(messages/2);
		Thread producer = new Thread(new ProducerThread(queue, messages));
		Thread dispatcher = new Thread(new DispatcherThread(queue, queueA, queueB, messages));
		Thread consumerA = new Thread(new ConsumerA(queueA, messages/2));
		Thread consumerB = new Thread(new ConsumerB(queueB, messages/2));
		producer.start();
		dispatcher.start();
		consumerA.start();
		consumerB.start();
	}
}

class ProducerThread implements Runnable {
	int number = 1;
	BlockingQueue<String> queue;
	int maxNumber;

	ProducerThread(BlockingQueue<String> queue, int maxNumber) {
		this.queue = queue;
		this.maxNumber = maxNumber;
	}

	public void run() {
		produce();
	}

	public void produce() {
		try {
			boolean aMessage = true;
			while (number <= maxNumber) {
				if (aMessage) {
					queue.put("A_Message_" + number);
					aMessage = false;
				}else if(!aMessage){
					queue.put("B_Message_" + number);
					aMessage = true;
				}
				number++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class DispatcherThread implements Runnable{
	BlockingQueue<String> queue;
	BlockingQueue<String> queueA;
	BlockingQueue<String> queueB;
	int maxNumber;
	public DispatcherThread(BlockingQueue<String> queue,BlockingQueue<String> queueA, BlockingQueue<String> queueB, int maxNumber) {
		this.queue = queue;
		this.queueA = queueA;
		this.queueB = queueB;
		this.maxNumber = maxNumber;
	}
	public void run() {
		dispacthMessages();
	}
	public void dispacthMessages() {
		try {
			int i=1;
			while(i<=maxNumber) {
					String message = queue.take();
					if(message.startsWith("A_")) {
						queueA.add(message);
					}else{
						queueB.add(message);
					}
					i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class ConsumerA implements Runnable{
	BlockingQueue<String> queueA;
	int maxNumber;
	public ConsumerA(BlockingQueue<String> queueA, int maxNumber) {
		this.queueA = queueA;
		this.maxNumber = maxNumber;
	}
	public void run() {
		consume();
	}
	public void consume() {
		try {
			int i = 0;
			while(i<maxNumber) {
					String message = queueA.take();
					System.out.println("Message processed by  consumerA - "+ message+"_Proessed");
					i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class ConsumerB implements Runnable{
	BlockingQueue<String> queueB;
	int maxNumber;
	public ConsumerB(BlockingQueue<String> queueB, int maxNumber) {
		this.queueB = queueB;
		this.maxNumber = maxNumber;
	}
	public void run() {
		consume();
	}
	public void consume() {
		try {
			int i =0;
			while(i<maxNumber) {
					String message = queueB.take();
					System.out.println("Message processed by  consumerB - "+ message+"_Proessed");
					i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}