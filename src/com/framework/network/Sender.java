package com.framework.network;

import java.util.concurrent.locks.ReentrantLock;

import com.framework.exception.NotEnoughPowerException;
import com.framework.exception.NotReachableEquipmentException;
import com.framework.exception.UnknownEquipmentException;
import com.framework.sensor.Sensor;
import com.framework.sensor.Station;
import com.framework.sensor.ThreadSensor;
import com.framework.sensor.ThreadStation;

public class Sender {

	public static void send(Sensor sender , Sensor receiver , Object message) throws NotEnoughPowerException ,NotReachableEquipmentException,UnknownEquipmentException{
		
		double consume=sender.consumeWhileSend(message);
		if((sender.getPower()-consume)<=0){
			throw new NotEnoughPowerException();
		}else{
			if(!sender.isCommunicationPossible(receiver)){
				throw new UnknownEquipmentException();
			}else{
				sender.substractPower(consume);
				if(receiver instanceof ThreadSensor){
					
					ThreadSensor threadSensor= (ThreadSensor)receiver ;
					threadSensor.addSensorMessageToQueue(sender, message);
					
				}else{
					
					double cons=receiver.consumeWhileReceive(message);
					if((receiver.getPower()-cons)<=0){
						receiver.substractPower(receiver.getPower());
						throw new NotReachableEquipmentException();
					}else{
						synchronized (receiver) {
							receiver.substractPower(cons);
							receiver.receiveMessage(sender, message);
							
						}
					}	
					
				}
				
			}
		}
	}
	
	public static void send(Sensor sender , Station receiver , Object message) throws NotEnoughPowerException ,NotReachableEquipmentException ,UnknownEquipmentException{
		double consume=sender.consumeWhileSend(message);
		if((sender.getPower()-consume)<=0){
			throw new NotEnoughPowerException();
		}else{
			if(!sender.isCommunicationPossible(receiver)){
				throw new UnknownEquipmentException();
			}else{
				sender.substractPower(consume);
				
				if(receiver instanceof ThreadStation){
					
					ThreadStation threadStation= (ThreadStation)receiver ;
					threadStation.addSensorMessageToQueue(sender, message);
					
				}else{
				receiver.receiveMessage(sender, message);
				}
			}
		}
	}
	
	public static void send(Station sender , Station receiver , Object message) throws NotReachableEquipmentException,UnknownEquipmentException{
		
	
			if(!sender.isCommunicationPossible(receiver)){
				throw new UnknownEquipmentException();
			}else{
				if(receiver instanceof ThreadStation){
					
					ThreadStation threadStation= (ThreadStation)receiver ;
					threadStation.addStationMessageToQueue(sender, message);
					
				}else{
					receiver.receiveMessage(sender, message);
					
				}
				
			}
			
	}
	
	public static void send(Station sender , Sensor receiver , Object message) throws NotReachableEquipmentException,UnknownEquipmentException{
		
		
			if(!sender.isCommunicationPossible(receiver)){
				throw new UnknownEquipmentException();
			}else{
				
				if(receiver instanceof ThreadSensor){
					
					ThreadSensor threadSensor= (ThreadSensor)receiver ;
					threadSensor.addStationMessageToQueue(sender, message);
					
				}else{
					double cons=receiver.consumeWhileReceive(message);
					if((receiver.getPower()-cons)<=0){
						receiver.substractPower(receiver.getPower());
						throw new NotReachableEquipmentException();
					}else{
						
						receiver.substractPower(cons);
						receiver.receiveMessage(sender, message);
						
					}
				}
			}
		
	}
}
