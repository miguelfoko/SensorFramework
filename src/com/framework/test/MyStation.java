package com.framework.test;

import java.util.Collection;

import com.framework.exception.NotEnoughPowerException;
import com.framework.exception.NotReachableEquipmentException;
import com.framework.exception.UnknownEquipmentException;
import com.framework.graphics.NetworkGraphics;
import com.framework.network.NetworkUtilities;
import com.framework.network.Sender;
import com.framework.sensor.Sensor;
import com.framework.sensor.Station;
import com.framework.util.ThreadWifiStation;

public class MyStation extends ThreadWifiStation {

	public volatile boolean recus=false ;
	private Frame frame;
	
	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	public MyStation(){
		this.setRadius(150);
	}
	
	public void diffuser(Object Message){
		recus=true;
		Collection<Sensor> sensors=NetworkUtilities.getReachableSensor(this);
		for(Sensor sensor : sensors){
			try {
				Sender.send(this, sensor, Message);
			} catch (NotReachableEquipmentException | UnknownEquipmentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				frame.getConsole().setText(frame.getConsole().getText()+"\n"+e.getMessage());
				
			}
		}
		
		Collection<Station> stations=NetworkUtilities.getReachableStation(this);
		for(Station station : stations){
			try {
				Sender.send(this, station, Message);
			} catch (NotReachableEquipmentException | UnknownEquipmentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				frame.getConsole().setText(frame.getConsole().getText()+"\n"+e.getMessage());
			}
		}
	}
	@Override
	public void receiveMessage(Sensor sender, Object Message) {
		// TODO Auto-generated method stub
	if(recus){
			
		}else{
			recus=true;
			System.out.println(this.getIdentificator()+": Message recu du capteur "+sender.getIdentificator()+": "+Message.toString());

			Collection<Sensor> sensors=NetworkUtilities.getReachableSensor(this);
			for(Sensor sensor : sensors){
				try {
					Sender.send(this, sensor, Message);
				} catch (NotReachableEquipmentException | UnknownEquipmentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.getConsole().setText(frame.getConsole().getText()+"\n"+e.getMessage());
					
				}
			}
			
			Collection<Station> stations=NetworkUtilities.getReachableStation(this);
			for(Station station : stations){
				try {
					Sender.send(this, station, Message);
				} catch (NotReachableEquipmentException | UnknownEquipmentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.getConsole().setText(frame.getConsole().getText()+"\n"+e.getMessage());
				}
			}
		}
	//NetworkGraphics.drawState(Frame.getNetwork(),Frame.getNetworkPanel());
	}

	@Override
	public void receiveMessage(Station station, Object Message) {
		// TODO Auto-generated method stub
	if(recus){
			
		}else{
			recus=true;
			System.out.println(this.getIdentificator()+": Message recu de la station "+station.getIdentificator()+": "+Message.toString());

			Collection<Sensor> sensors=NetworkUtilities.getReachableSensor(this);
			for(Sensor sensor : sensors){
				try {
					Sender.send(this, sensor, Message);
				} catch (NotReachableEquipmentException | UnknownEquipmentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.getConsole().setText(frame.getConsole().getText()+"\n"+e.getMessage());
				}
			}
			
			Collection<Station> stations=NetworkUtilities.getReachableStation(this);
			for(Station statione : stations){
				try {
					Sender.send(this, statione, Message);
				} catch (NotReachableEquipmentException | UnknownEquipmentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.getConsole().setText(frame.getConsole().getText()+"\n"+e.getMessage());
				}
			}
		}
	//NetworkGraphics.drawState(Frame.getNetwork(),Frame.getNetworkPanel());
	}
}
