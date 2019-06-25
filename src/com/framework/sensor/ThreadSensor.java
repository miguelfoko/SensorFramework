package com.framework.sensor;

import java.util.ArrayDeque;
import java.util.Deque;

import com.framework.exception.NotEnoughPowerException;

public abstract class ThreadSensor extends Thread implements Sensor{

	private volatile boolean on=false;
	private final ArrayDeque<Message> messageQueue =new ArrayDeque<Message>() ;
	
	
	public final void run(){
		on=true;
		while(on){
			try {
				readingQueue();
			} catch (NotEnoughPowerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private synchronized final void readingQueue() throws NotEnoughPowerException{
		
		while(!messageQueue.isEmpty()){
			Message mes=messageQueue.removeFirst();
			double consume=this.consumeWhileReceive(mes.message);
			if((this.getPower()-consume)>=0){
				this.substractPower(consume);
				if(mes.sensorSender){
					Sensor sender=(Sensor)mes.sender;
					this.receiveMessage(sender, mes.message);
							
				}else{
					
					Station sender=(Station)mes.sender;
					this.receiveMessage(sender, mes.message);
				}
			}else{
				on=false;
				throw new NotEnoughPowerException();
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
