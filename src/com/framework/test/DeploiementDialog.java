package com.framework.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.framework.network.Dimension;

public class DeploiementDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nbrCapteurTextField;
	private JTextField nbrStationTextField;
	private JTextField widthTextField;
	private JTextField heightTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeploiementDialog dialog = new DeploiementDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeploiementDialog(Frame f) {
		super(f,true);
		setBounds(100, 100, 452, 335);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblNombreDeCapteurs = new JLabel("Nombre de capteurs");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNombreDeCapteurs, 46, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblNombreDeCapteurs, 42, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblNombreDeCapteurs);
		
		nbrCapteurTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, nbrCapteurTextField, 0, SpringLayout.NORTH, lblNombreDeCapteurs);
		sl_contentPanel.putConstraint(SpringLayout.EAST, nbrCapteurTextField, -65, SpringLayout.EAST, contentPanel);
		contentPanel.add(nbrCapteurTextField);
		nbrCapteurTextField.setColumns(10);
		
		JLabel lblNombreDeStations = new JLabel("Nombre de stations");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNombreDeStations, 36, SpringLayout.SOUTH, lblNombreDeCapteurs);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblNombreDeStations, 0, SpringLayout.WEST, lblNombreDeCapteurs);
		contentPanel.add(lblNombreDeStations);
		
		nbrStationTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, nbrStationTextField, 0, SpringLayout.NORTH, lblNombreDeStations);
		sl_contentPanel.putConstraint(SpringLayout.WEST, nbrStationTextField, 0, SpringLayout.WEST, nbrCapteurTextField);
		contentPanel.add(nbrStationTextField);
		nbrStationTextField.setColumns(10);
		
		JLabel lblLargeur = new JLabel("Largeur");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblLargeur, 48, SpringLayout.SOUTH, lblNombreDeStations);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblLargeur, 0, SpringLayout.WEST, lblNombreDeCapteurs);
		contentPanel.add(lblLargeur);
		
		widthTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, widthTextField, 0, SpringLayout.NORTH, lblLargeur);
		sl_contentPanel.putConstraint(SpringLayout.WEST, widthTextField, 0, SpringLayout.WEST, nbrCapteurTextField);
		contentPanel.add(widthTextField);
		widthTextField.setColumns(10);
		
		JLabel lblHauteur = new JLabel("Hauteur");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblHauteur, 0, SpringLayout.WEST, lblNombreDeCapteurs);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblHauteur, -29, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(lblHauteur);
		
		heightTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, heightTextField, 0, SpringLayout.SOUTH, lblHauteur);
		sl_contentPanel.putConstraint(SpringLayout.EAST, heightTextField, 0, SpringLayout.EAST, nbrCapteurTextField);
		contentPanel.add(heightTextField);
		heightTextField.setColumns(10);
		final DeploiementDialog dialog=this;
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						f.setNbreCapteur(Integer.valueOf(nbrCapteurTextField.getText()));
						f.setNbreStation(Integer.valueOf(nbrStationTextField.getText()));
						f.setDim(new Dimension(Integer.valueOf(widthTextField.getText()),Integer.valueOf(heightTextField.getText())));
						
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
