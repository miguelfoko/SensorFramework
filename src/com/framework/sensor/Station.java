package com.framework.sensor;

import com.framework.network.Network;

public interface Station {

	
	public boolean isCommunicationPossible(Sensor sensor);
	
	public boolean isCommunicationPossible(Station station);
	
	public void receiveMessage(Sensor sender , Object Message);
	
	public void receiveMessage(Station station , Object Message);
	
	public Network getNetwork();
	
	public void setNetwork(Network network);
	
	public Position getPosition();
	
	public void setPosition(Position pos);
	
	public String getIdentificator();

}
