package com.framework.network;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;

import com.framework.graphics.NetworkGraphics;
import com.framework.sensor.Position;
import com.framework.sensor.Sensor;
import com.framework.sensor.Station;
import com.framework.sensor.ThreadSensor;
import com.framework.sensor.ThreadStation;

public class Network {

	private ArrayList<Sensor> allSensor=new ArrayList<Sensor>();
	
	private ArrayList<Station> allStation= new ArrayList<Station>();
	
	private Hashtable<Sensor,Long> lastSubstraction = new Hashtable<Sensor,Long>() ;
	
	private JPanel graphicPanel; 
	
	private ArrayList<Position> allPositions = new ArrayList<>();
	
	public static boolean isBelongTo(Sensor sensor,Network network){
		
		return network.getAllSensor().contains(sensor);
	}
	
	public Network(){
		graphicPanel = new JPanel();
		(new Substractor(this)).start();
	}
	
	public static boolean isBelongTo(Station station,Network network){
		
		return network.getAllStation().contains(station);
	}
	
	public JPanel getGraphicPanel() {
		return graphicPanel;
	}

	public void setGraphicPanel(JPanel graphicPanel) {
		this.graphicPanel = graphicPanel;
	}

	public boolean addStation(Station station){
		if(allStation.add(station)){
			station.setNetwork(this);
			return true;
		}
		return false; 
	}
	
	public boolean removeStation(Station station){
		if(allStation.remove(station)){
			station.setNetwork(null);
			return true;
		}
		return false; 
	}
	
	public boolean addSensor(Sensor sensor){
		if(allSensor.add(sensor)){
			
			sensor.setNetwork(this);
			lastSubstraction.put(sensor, System.currentTimeMillis());
			return true;
		}
		return false; 
	}
	
	public boolean removeSensor(Sensor sensor){
		if(allSensor.remove(sensor)){
			sensor.setNetwork(null);
			lastSubstraction.remove(sensor);
			return true;
		}
		return false; 
	}

	public ArrayList<Sensor> getAllSensor() {
		return allSensor;
	}

	public void setAllSensor(ArrayList<Sensor> allSensor) {
		this.allSensor = allSensor;
	}

	public ArrayList<Station> getAllStation() {
		return allStation;
	}

	public void setAllStation(ArrayList<Station> allStation) {
		this.allStation = allStation;
	}
	
	public void deploy(int numberOfSenors, int numberOfStations, Dimension dim, Class<? extends Sensor>  sensorClass, Class<? extends Station>  stationClass){
		int x,y;
		Position pos;
		
		allStation=new ArrayList<Station>();
		allSensor=new ArrayList<Sensor>();
		lastSubstraction=new Hashtable<Sensor,Long>();
		allPositions=new ArrayList<Position>();
		for(int n=0;n<numberOfSenors;n++){
			x = (int)(Math.random()*dim.getWidth());
			y = (int)(Math.random()*dim.getHeight());
			pos = new Position(x,y);
			
			try {
				Sensor sensor = sensorClass.newInstance();
				while(allPositions.contains(pos)){
					x = (int)(Math.random()*dim.getWidth());
					y = (int)(Math.random()*dim.getHeight());
					pos = new Position(x,y);
				}
				sensor.setPosition(pos);
				allPositions.add(pos);
				addSensor(sensor);
				if(sensor instanceof ThreadSensor) ((ThreadSensor)sensor).start();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int n=0;n<numberOfStations;n++){
			x = (int)(Math.random()*dim.getWidth());
			y = (int)(Math.random()*dim.getHeight());
			pos = new Position(x,y);
			
			try {
				Station station = stationClass.newInstance();
				while(allPositions.contains(pos)){
					x = (int)(Math.random()*dim.getWidth());
					y = (int)(Math.random()*dim.getHeight());
					pos = new Position(x,y);
				}
				station.setPosition(pos);
				allPositions.add(pos);
				addStation(station);
				if(station instanceof ThreadStation) ((ThreadStation)station).start();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private  class Substractor extends Thread{
		Network net;
		public Substractor(Network net){
			this.net = net;
		}
		public void run(){
			
			while(true){
				for(Sensor sensor: allSensor  ){
				
					long lastSubstract= lastSubstraction.get(sensor);
					long currentTime= System.currentTimeMillis() ;
					long difference= currentTime-lastSubstract;
					double differencePerSecond= (double)difference/1000;
				
					sensor.substractPower(differencePerSecond*sensor.consumeWhileIddle());
					lastSubstraction.put(sensor, currentTime);
				}
				NetworkGraphics.drawState(net, graphicPanel);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
