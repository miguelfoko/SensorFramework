package com.framework.sensor;

import com.framework.network.Network;

public interface Sensor {

	public void substractPower(double value);
	
	public double getPower();
	
	public double consumeWhileSend(Object message);
	
	public double consumeWhileReceive(Object message);
	
	public double consumeWhileIddle();
	
	public boolean isCommunicationPossible(Sensor sensor);
	
	public boolean isCommunicationPossible(Station station);
	
	public void  receiveMessage(Sensor sender , Object Message);
	
	public void receiveMessage(Station station , Object Message);
	
	public Network getNetwork();

	public void setNetwork(Network network);
	
	public Position getPosition();
	
	public void setPosition(Position pos);
	
	public String getIdentificator();
	
}
