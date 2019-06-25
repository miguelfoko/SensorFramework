package com.framework.sensor;

import java.util.ArrayDeque;
import java.util.Deque;

import com.framework.exception.NotEnoughPowerException;

public abstract class ThreadStation extends Thread implements Station{

	private volatile boolean on=false;
	private final ArrayDeque<Message> messageQueue =new ArrayDeque<Message>() ;
	public final void run(){
		on=true;
		while(on){
	
				readingQueue();
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private synchronized final void readingQueue() {
		
		while(!messageQueue.isEmpty()){
			Message mes=messageQueue.removeFirst();
			
				if(mes.sensorSender){
					Sensor sender=(Sensor)mes.sender;
					this.receiveMessage(sender, mes.message);
							
				}else{
					
					Station sender=(Station)mes.sender;
					this.receiveMessage(sender, mes.message);
				}
			
		}
		
	}
	public final void on(){
		if(!on){
		on=true;
		start();
		}
	}
	
	public final void off(){
		if(on){
		on=false;
		stop();
		}
	}
	
	public final void addSensorMessageToQueue(Sensor sender,Object message){
		
		if(on){
		Message mes=new Message();
		mes.sensorSender=true;
		mes.sender=sender;
		mes.message=message;
		messageQueue.addLast(mes);
		}
	}
	
	public final void addStationMessageToQueue(Station sender,Object message){
		if(on){
		Message mes=new Message();
		mes.sensorSender=false;
		mes.sender=sender;
		mes.message=message;
		messageQueue.addLast(mes);
		}
	}
	
	private final static class Message {
		
		public boolean sensorSender ;
		
		public Object sender ;
		
		public Object message ;
	}
	
}
