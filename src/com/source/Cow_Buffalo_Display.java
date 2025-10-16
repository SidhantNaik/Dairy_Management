package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Database;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Cow_Buffalo_Display extends JFrame 
{

	private JPanel contentPane;

	public static void cow_buffalo_combo()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Cow_Buffalo_Display frame = new Cow_Buffalo_Display();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocation(400,170);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Cow_Buffalo_Display() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String str=comboBox.getSelectedItem().toString();
				
				if(str.equals("Cow Rate"))
				{
					Display_rate_table_cow.display_cow_rate_table();
				}
				if(str.equals("Buffalo Rate"))
				{
					Display_rate_table_buffalo.display_buffalo_rate_table();
				}
			}
		});
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cow Rate", "Buffalo Rate"}));
		comboBox.setBounds(48, 83, 152, 25);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Please Select Below");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(39, 21, 204, 39);
		contentPane.add(lblNewLabel);
	}
}
