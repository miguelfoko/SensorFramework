package com.framework.util;

import com.framework.network.Network;
import com.framework.sensor.Position;
import com.framework.sensor.Sensor;
import com.framework.sensor.Station;
import com.framework.sensor.ThreadStation;

public class ThreadWifiStation extends ThreadStation{

	private Position position;
	private Network network ;


	private float radius;
	private static int count=0;
	private String identificator;
	
	public ThreadWifiStation(){
		count++;
		identificator="Station"+count;
	}
	
	public String getIdentificator() {
		return identificator;
	}

	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}

	@Override
	public boolean isCommunicationPossible(Sensor sensor) {
		// TODO Auto-generated method stub
		ThreadWifiSensor sens=(ThreadWifiSensor)sensor;
		double distance= Math.sqrt(Math.pow(sens.getPosition().getX()-this.getPosition().getX(), 2) + Math.pow(sens.getPosition().getY()-this.getPosition().getY(), 2));
		if(distance<=radius) return true;
		return false;
	}

	@Override
	public boolean isCommunicationPossible(Station station) {
		// TODO Auto-generated method stub
		ThreadWifiStation statione=(ThreadWifiStation)station;
		double distance= Math.sqrt(Math.pow(statione.getPosition().getX()-this.getPosition().getX(), 2) + Math.pow(statione.getPosition().getY()-this.getPosition().getY(), 2)) ;
		if(distance<=radius) return true;
		return false;
	}

	@Override
	public void receiveMessage(Sensor sender, Object Message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveMessage(Station station, Object Message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Network getNetwork() {
		// TODO Auto-generated method stub
		return network;
	}

	@Override
	public void setNetwork(Network network) {
		// TODO Auto-generated method stub
		this.network=network;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
}
