package com.framework.network;

import java.util.ArrayList;
import java.util.Collection;

import com.framework.sensor.Sensor;
import com.framework.sensor.Station;

public class NetworkUtilities {

	
	public static Collection<Station> getReachableStation(Sensor sensor){
		
		ArrayList<Station> array =new ArrayList<Station>();
		for(Station station: sensor.getNetwork().getAllStation() ){
			
			if(sensor.isCommunicationPossible(station)){
				array.add(station);
			}
		}
		return array ;
	}
	public static Collection<Station> getReachableStation(Station station){
		
		ArrayList<Station> array =new ArrayList<Station>();
		for(Station statione: station.getNetwork().getAllStation() ){
			if(!statione.equals(station)){
			if(station.isCommunicationPossible(statione)){
				array.add(statione);
			}
			}
		}
		return array ;
	}
	
	public static Collection<Sensor> getReachableSensor(Sensor sensor){
		
		ArrayList<Sensor> array =new ArrayList<Sensor>();
		for(Sensor sen: sensor.getNetwork().getAllSensor() ){
			if(!sen.equals(sensor)){
			if(sensor.isCommunicationPossible(sen)){
				array.add(sen);
			}
			}
		}
		return array ;
	}
	public static Collection<Sensor> getReachableSensor(Station station){
		
		ArrayList<Sensor> array =new ArrayList<Sensor>();
		for(Sensor sensor: station.getNetwork().getAllSensor() ){
			
			if(station.isCommunicationPossible(sensor)){
				array.add(sensor);
			}
		}
		return array ;
	}
	
	
	public static void deploy(int numberOfSensor , int numberOfStation , long area , Class<? extends Sensor>  sensorClass, Class<? extends Station>  stationClass){
		
		
	}
	
}
