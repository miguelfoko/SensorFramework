package com.framework.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.framework.graphics.NetworkGraphics;
import com.framework.network.Dimension;
import com.framework.network.Network;
import com.framework.sensor.Position;
import com.framework.sensor.Station;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextArea console;
    private static Network network= new Network();
    private static JPanel networkPanel;
    private int nbreCapteur;
    private int nbreStation;
    private Dimension dim;
    
	/**
	 * Launch the application.
	 */
	
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					Frame frame = new Frame();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int getNbreCapteur() {
		return nbreCapteur;
	}

	public void setNbreCapteur(int nbreCapteur) {
		this.nbreCapteur = nbreCapteur;
	}

	public int getNbreStation() {
		return nbreStation;
	}

	public void setNbreStation(int nbreStation) {
		this.nbreStation = nbreStation;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 558);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		this.setOtherLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnFichier.add(mntmQuitter);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblReseau = new JLabel("Reseau");
		sl_panel.putConstraint(SpringLayout.WEST, lblReseau, 205, SpringLayout.WEST, panel);
		panel.add(lblReseau);
		
		JButton btnAjouterUneStation = new JButton("Ajouter une station");
		sl_panel.putConstraint(SpringLayout.NORTH, btnAjouterUneStation, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnAjouterUneStation, 0, SpringLayout.WEST, panel);
		panel.add(btnAjouterUneStation);
		
		final Frame frame=this;
		btnAjouterUneStation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				(new StationCreateDialog(frame)).setVisible(true);
			}
		});
		
		JButton btnAjouterUnCapteur = new JButton("Ajouter un capteur");
		sl_panel.putConstraint(SpringLayout.NORTH, btnAjouterUnCapteur, 0, SpringLayout.NORTH, btnAjouterUneStation);
		sl_panel.putConstraint(SpringLayout.WEST, btnAjouterUnCapteur, 6, SpringLayout.EAST, btnAjouterUneStation);
		panel.add(btnAjouterUnCapteur);
		
		btnAjouterUnCapteur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				(new SensorCreateDialog(frame)).setVisible(true);
			}
		});
		
		networkPanel = Frame.getNetwork().getGraphicPanel();
		sl_panel.putConstraint(SpringLayout.SOUTH, lblReseau, -13, SpringLayout.NORTH, networkPanel);
		sl_panel.putConstraint(SpringLayout.WEST, networkPanel, 42, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, networkPanel, 120, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, networkPanel, -229, SpringLayout.SOUTH, panel);
		panel.add(networkPanel);
		
		JLabel lblConsole = new JLabel("Console");
		sl_panel.putConstraint(SpringLayout.NORTH, lblConsole, 30, SpringLayout.SOUTH, networkPanel);
		sl_panel.putConstraint(SpringLayout.WEST, lblConsole, 215, SpringLayout.WEST, panel);
		panel.add(lblConsole);
		
		JPanel panel_2 = new JPanel();
		sl_panel.putConstraint(SpringLayout.EAST, networkPanel, 0, SpringLayout.EAST, panel_2);
		sl_panel.putConstraint(SpringLayout.NORTH, panel_2, 12, SpringLayout.SOUTH, lblConsole);
		sl_panel.putConstraint(SpringLayout.WEST, panel_2, 42, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_2, -44, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, panel_2, -49, SpringLayout.EAST, panel);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		console = new JTextArea();
		scrollPane.setViewportView(console);
		
		JButton btnTesterUneDiffusion = new JButton("Tester une diffusion");
		sl_panel.putConstraint(SpringLayout.NORTH, btnTesterUneDiffusion, 6, SpringLayout.SOUTH, networkPanel);
		sl_panel.putConstraint(SpringLayout.WEST, btnTesterUneDiffusion, 42, SpringLayout.WEST, panel);
		panel.add(btnTesterUneDiffusion);
		
		JButton btnDeploiementAleatoire = new JButton("Deploiement aleatoire");
		btnDeploiementAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeploiementDialog dialog = new DeploiementDialog(frame);
				dialog.setVisible(true);
				network.deploy(nbreCapteur, nbreStation, dim, MySensor.class, MyStation.class);
				NetworkGraphics.drawState(network, frame.getNetworkPanel());
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, btnDeploiementAleatoire, 6, SpringLayout.EAST, btnAjouterUnCapteur);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnDeploiementAleatoire, 0, SpringLayout.SOUTH, btnAjouterUneStation);
		panel.add(btnDeploiementAleatoire);
		
		btnTesterUneDiffusion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//System.out.println("diffusion");
				if(Frame.getNetwork().getAllStation().size()>0){
					//System.out.println("diffusion en cours");
					MyStation station=(MyStation) Frame.getNetwork().getAllStation().get(0);
					station.diffuser(new Object());
				};
			}
		});
	}

	public static JPanel getNetworkPanel() {
		return networkPanel;
	}

	public static void setNetworkPanel(JPanel networkPanel) {
		Frame.networkPanel = networkPanel;
	}

	public JTextArea getConsole() {
		return console;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}

	public static Network getNetwork() {
		return network;
	}

	public static void setNetwork(Network network) {
		Frame.network = network;
	}
	
	public void setOtherLookAndFeel(String look){
		 try {
			UIManager.setLookAndFeel(look);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
