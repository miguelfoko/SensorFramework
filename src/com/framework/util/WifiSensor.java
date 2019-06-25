package com.framework.util;

import com.framework.network.Network;
import com.framework.sensor.Position;
import com.framework.sensor.Sensor;
import com.framework.sensor.Station;

public class WifiSensor implements Sensor{
	
	private Position position;
	private Network network ;
	private double power;
	private float radius;
	
	private static int count=0;
	private String identificator;
	
	public WifiSensor(){
		count++;
		identificator="S"+count;
	}
	
	public String getIdentificator() {
		return identificator;
	}

	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}
	
	@Override
	public void substractPower(double value) {
		// TODO Auto-generated method stub
		power=power-value;
	}
	@Override
	public double getPower() {
		// TODO Auto-generated method stub
		return power;
	}
	@Override
	public double consumeWhileSend(Object message) {
		// TODO Auto-generated method stub
		
		return 0.1 ;
	}
	@Override
	public boolean isCommunicationPossible(Sensor sensor) {
		// TODO Auto-generated method stub
		
		WifiSensor sens=(WifiSensor)sensor;
		double distance= Math.sqrt(Math.pow(sens.getPosition().getX()-this.getPosition().getX(), 2) + Math.pow(sens.getPosition().getY()-this.getPosition().getY(), 2)) ;
		//System.out.println("distance:"+distance+" radius:"+radius);
		if(distance<=radius) return true;
		return false;
	}
	@Override
	public boolean isCommunicationPossible(Station station) {
		// TODO Auto-generated method stub
		WifiStation statione=(WifiStation)station;
		double distance= Math.sqrt(Math.pow(statione.getPosition().getX()-this.getPosition().getX(), 2) + Math.pow(statione.getPosition().getY()-this.getPosition().getY(), 2)) ;
		if(distance<=radius) return true;
		return false;
	}
	@Override
	public  synchronized void receiveMessage(Sensor sender, Object Message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public synchronized void receiveMessage(Station station, Object Message) {
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
		
		this.network=network ;
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
	public void setPower(double power) {
		this.power = power;
	}
	@Override
	public double consumeWhileReceive(Object message) {
		// TODO Auto-generated method stub
		return 0.01;
	}
	@Override
	public double consumeWhileIddle() {
		// TODO Auto-generated method stub
		return 0.001;
	}
	
	

}
