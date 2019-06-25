package com.framework.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.framework.graphics.NetworkGraphics;
import com.framework.sensor.Position;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StationCreateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCoordonneeX;
	private JLabel lblCoordonneeY;
	private JTextField PosxTextField;
	private JTextField rayontextField;
	private JLabel lblRayon;
	private JTextField PosytextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StationCreateDialog dialog = new StationCreateDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StationCreateDialog(Frame fen) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblCoordonneeX = new JLabel("Coordonnee X");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCoordonneeX, 50, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblCoordonneeX, 39, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblCoordonneeX);
		}
		{
			lblCoordonneeY = new JLabel("Coordonnee Y");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCoordonneeY, 46, SpringLayout.SOUTH, lblCoordonneeX);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblCoordonneeY, 0, SpringLayout.WEST, lblCoordonneeX);
			contentPanel.add(lblCoordonneeY);
		}
		{
			lblRayon = new JLabel("Rayon");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblRayon, 35, SpringLayout.SOUTH, lblCoordonneeY);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblRayon, 0, SpringLayout.WEST, lblCoordonneeX);
			contentPanel.add(lblRayon);
		}
		
		PosxTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, PosxTextField, 0, SpringLayout.NORTH, lblCoordonneeX);
		sl_contentPanel.putConstraint(SpringLayout.WEST, PosxTextField, 56, SpringLayout.EAST, lblCoordonneeX);
		contentPanel.add(PosxTextField);
		PosxTextField.setColumns(10);
		
		PosytextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, PosytextField, 0, SpringLayout.NORTH, lblCoordonneeY);
		sl_contentPanel.putConstraint(SpringLayout.WEST, PosytextField, 0, SpringLayout.WEST, PosxTextField);
		contentPanel.add(PosytextField);
		PosytextField.setColumns(10);
		
		rayontextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, rayontextField, 0, SpringLayout.NORTH, lblRayon);
		sl_contentPanel.putConstraint(SpringLayout.EAST, rayontextField, 0, SpringLayout.EAST, PosxTextField);
		contentPanel.add(rayontextField);
		rayontextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				final StationCreateDialog dialog=this;
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						
						MyStation station=new MyStation();
						
						station.setPosition(new Position(Integer.valueOf(dialog.PosxTextField.getText()),Integer.valueOf(dialog.PosytextField.getText())));
						station.setRadius(Integer.valueOf(dialog.rayontextField.getText()));
						station.setFrame(fen);
						Frame.getNetwork().addStation(station);
						NetworkGraphics.drawState(Frame.getNetwork(),fen.getNetworkPanel());
						station.on();
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				final StationCreateDialog dialog = this;
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
			}
		}
	}
}
