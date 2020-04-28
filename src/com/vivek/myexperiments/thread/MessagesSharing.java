package com.vivek.myexperiments.thread;

import java.util.ArrayList;
import java.util.List;

public class MessagesSharing {
	public static void main(String[] args) {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("A_Message_1");
		messages.add("A_Message_2");
		messages.add("B_Message_1");
		messages.add("B_Message_3");
		messages.add("C_Message_1");
		messages.add("C_Message_4");
		messages.add("C_Message_5");
		messages.add("D_Message_1");
		messages.add("D_Message_2");
		MessageProcessing msgPrc = new MessageProcessing(messages);
		msgPrc.processMessages();
	}
}

class MessageProcessing{
	List<String> messages;
	List<String> messagesAB = new ArrayList<String>();
	List<String> messagesCD = new ArrayList<String>();
	public MessageProcessing(List<String> messages) {
		this.messages = messages;
	}
	public void processMessages(){
		for(String message: messages) {
			if(message.startsWith("A_") || message.startsWith("B_")) {
				messagesAB.add(message);
			}else if(message.startsWith("C_") || message.startsWith("D_")) {
				messagesCD.add(message);
			}
		}
	
	ProcessAB pab = new ProcessAB(messagesAB);
	ProcessCD pcd = new ProcessCD(messagesCD);
	
	Thread abProcessor = new Thread(new Runnable() {
		@Override
		public void run() {
			pab.processAB();
		}
	});
	
	Thread cdProcessor = new Thread(new Runnable() {
		@Override
		public void run() {
			pcd.processCD();
		}
	});
	
	abProcessor.start();
	cdProcessor.start();
	}
}

class ProcessAB implements Runnable{
	List<String> messages;
	public ProcessAB(List<String> messages) {
		this.messages = messages;
	}
	public void run(){
		processAB();
	}
	public void processAB() {
		for(String message: messages) {
			System.out.println("Processeing done by - AB thread -"+ message+"_Processed");
		}
	}
}

class ProcessCD implements Runnable{
	List<String> messages;
	public ProcessCD(List<String> messages) {
		this.messages = messages;
	}
	public void run(){
		processCD();
	}
	public void processCD() {
		for(String message: messages) {
			System.out.println("Processeing done by - CD thread -"+ message+"_Processed");
		}
	}
}

