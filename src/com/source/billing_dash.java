package com.source;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class billing_dash extends JFrame 
{

	private JPanel contentPane;

	
	public static void Billing_dashboard()//main(String[] args) ////
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					billing_dash frame = new billing_dash();
					frame.setLocation(553,80);
					frame.setResizable(false);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public billing_dash() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 292, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 270, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton displaytablechart = new JButton("Display Table Chart");
		displaytablechart.setFont(new Font("Times New Roman", Font.BOLD, 25));
		displaytablechart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Cow_Buffalo_Display.cow_buffalo_combo();
			}
		});
		displaytablechart.setBounds(10, 22, 250, 35);
		panel.add(displaytablechart);
		
		JButton btnAddNewRates = new JButton("Add New Rates");
		btnAddNewRates.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
//				COW_Rates.add_new_rates();
			}
		});
		btnAddNewRates.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnAddNewRates.setBounds(10, 93, 250, 35);
		panel.add(btnAddNewRates);
		
		JButton editinchart = new JButton("Edit In Chart");
		editinchart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		editinchart.setFont(new Font("Times New Roman", Font.BOLD, 25));
		editinchart.setBounds(10, 170, 250, 35);
		panel.add(editinchart);
	}

}
