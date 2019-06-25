package com.framework.graphics;

import java.awt.BorderLayout;
import java.awt.Color;

import java.util.Hashtable;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.framework.network.Network;
import com.framework.network.NetworkUtilities;
import com.framework.sensor.Sensor;
import com.framework.sensor.Station;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;



public class NetworkGraphics {

public static void drawStateInHierarchicalGraphic(Network network, JPanel panel){
		
		panel.removeAll();
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		mxGraphComponent graphComponent;
		graphComponent=new mxGraphComponent(graph);
		panel.setLayout(new BorderLayout(100,100));
		panel.add(graphComponent,BorderLayout.CENTER);
		
		
		graph.getModel().beginUpdate();
		
		Hashtable<Object, Object> hash=new Hashtable<Object,Object>();
		Object v1;
		String styleColor = mxConstants.STYLE_FILLCOLOR + "=rgb(255,255,255)";
		String styleColor1= mxConstants.STYLE_FILLCOLOR + "=rgb(0,255,0)";
		String styleColor2= mxConstants.STYLE_FILLCOLOR + "=rgb(255,0,0)";
		int stationCount=0;
		for(Station station:network.getAllStation()){
			stationCount++;
			v1 = graph.insertVertex(parent, null,(station.getIdentificator()!=null)?station.getIdentificator():"",45, 70, 40,40 ,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_DOUBLE_RECTANGLE+";"+styleColor);
			hash.put(station, v1);
		}
		int sensorCount=0;
		
		for(Sensor sensor:network.getAllSensor()){
			sensorCount++;
			if(sensor.getPower()==0){
				v1 = graph.insertVertex(parent, null,((sensor.getIdentificator()!=null)?sensor.getIdentificator():"")+" "+sensor.getPower()+"J",45, 70, 30,30 ,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_ELLIPSE+";"+styleColor2);
				
			}else{
				v1 = graph.insertVertex(parent, null,((sensor.getIdentificator()!=null)?sensor.getIdentificator():"")+" "+sensor.getPower()+"J",45, 70, 30,30 ,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_ELLIPSE+";"+styleColor1);
				
			}
			hash.put(sensor, v1);
		}
		
		for(Station station:network.getAllStation()){
			
			for(Station otherStation:NetworkUtilities.getReachableStation(station)){
				Object vd=hash.get(station);
				Object vf=hash.get(otherStation);
				
				graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
			}
			
			for(Sensor sensor:NetworkUtilities.getReachableSensor(station)){
				Object vd=hash.get(station);
				Object vf=hash.get(sensor);
				
				graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
			}
		}
		
		for(Sensor sensor:network.getAllSensor()){
			
			for(Station station:NetworkUtilities.getReachableStation(sensor)){
				Object vd=hash.get(sensor);
				Object vf=hash.get(station);
				
				graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
			}
			
			for(Sensor otherSensor:NetworkUtilities.getReachableSensor(sensor)){
				Object vd=hash.get(sensor);
				Object vf=hash.get(otherSensor);
				//System.out.println("et sensor");
				graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
			}
		}
		
		 mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		 //mxFastOrganicLayout layout= new mxFastOrganicLayout(graph);
		 //mxCompactTreeLayout layout= new mxCompactTreeLayout(graph);
		//mxStackLayout layout= new mxStackLayout(graph);
		//mxOrganicLayout layout=new mxOrganicLayout(graph);
		//mxEdgeLabelLayout layout=new mxEdgeLabelLayout(graph);
		 //HumanOrgansGraph hog = new HumanOrgansGraph(graph);
	           layout.setOrientation(SwingConstants.WEST);
	           
	            layout.execute(parent);
	           // laymeout.execute(parent);
	            graph.getModel().endUpdate();
	            
	            graphComponent.setBackground(Color.white);
		panel.validate();
		panel.repaint();
	}

public static void drawState(Network network, JPanel panel){
	
	panel.removeAll();
	mxGraph graph = new mxGraph();
	Object parent = graph.getDefaultParent();
	mxGraphComponent graphComponent;
	graphComponent=new mxGraphComponent(graph);
	panel.setLayout(new BorderLayout(100,100));
	panel.add(graphComponent,BorderLayout.CENTER);
	
	
	
	Hashtable<Object, Object> hash=new Hashtable<Object,Object>();
	Object v1;
	String styleColor = mxConstants.STYLE_FILLCOLOR + "=rgb(255,255,255)";
	String styleColor1= mxConstants.STYLE_FILLCOLOR + "=rgb(0,255,0)";
	String styleColor2= mxConstants.STYLE_FILLCOLOR + "=rgb(255,0,0)";
	int stationCount=0;
	for(Station station:network.getAllStation()){
		stationCount++;
		v1 = graph.insertVertex(parent, null,(station.getIdentificator()!=null)?station.getIdentificator():"",station.getPosition().getX(),station.getPosition().getY(), 40,40 ,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_DOUBLE_RECTANGLE+";"+styleColor);
		hash.put(station, v1);
	}
	int sensorCount=0;
	
	for(Sensor sensor:network.getAllSensor()){
		sensorCount++;
		if(sensor.getPower()==0){
			v1 = graph.insertVertex(parent, null,((sensor.getIdentificator()!=null)?sensor.getIdentificator():"")+" "+sensor.getPower()+"J",sensor.getPosition().getX(),sensor.getPosition().getY(), 30,30 ,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_ELLIPSE+";"+styleColor2);
			
		}else{
			v1 = graph.insertVertex(parent, null,((sensor.getIdentificator()!=null)?sensor.getIdentificator():"")+" "+sensor.getPower()+"J",sensor.getPosition().getX(),sensor.getPosition().getY(), 30,30 ,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_ELLIPSE+";"+styleColor1);
			
		}
		hash.put(sensor, v1);
	}
	
	for(Station station:network.getAllStation()){
		
		for(Station otherStation:NetworkUtilities.getReachableStation(station)){
			Object vd=hash.get(station);
			Object vf=hash.get(otherStation);
			
			graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
		}
		
		for(Sensor sensor:NetworkUtilities.getReachableSensor(station)){
			Object vd=hash.get(station);
			Object vf=hash.get(sensor);
			
			graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
		}
	}
	
	for(Sensor sensor:network.getAllSensor()){
		
		for(Station station:NetworkUtilities.getReachableStation(sensor)){
			Object vd=hash.get(sensor);
			Object vf=hash.get(station);
			
			graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
		}
		
		for(Sensor otherSensor:NetworkUtilities.getReachableSensor(sensor)){
			Object vd=hash.get(sensor);
			Object vf=hash.get(otherSensor);
			//System.out.println("et sensor");
			graph.insertEdge(parent, null," ", vd, vf,mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_CLASSIC);
		}
	}
	
            
    graphComponent.setBackground(Color.white);
	panel.validate();
	panel.repaint();
}
/*
private String StringAroundValue(double p){
	String val =""+p;
	String[] part=val.split(".");
	
	if(part.length>2){
		String decimalPart=part[1];
		if(decimalPart.length()>=6){
			//if(Integer.valueOf(decimalPart.charAt(5)
		}
	}
	 return val ;
	}
*/
}

