package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class Edit_Fat_SNF_Rate_Dashboard extends JFrame 
{

	private JPanel contentPane;

	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Edit_Fat_SNF_Rate_Dashboard frame = new Edit_Fat_SNF_Rate_Dashboard();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Edit_Fat_SNF_Rate_Dashboard() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 310, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 296, 250);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton editfat = new JButton("Edit Fat\r\n");
		editfat.setFont(new Font("Times New Roman", Font.BOLD, 25));
		editfat.setBounds(43, 10, 189, 43);
		panel.add(editfat);
		
		JButton editsnf = new JButton("Edit SNF");
		editsnf.setFont(new Font("Times New Roman", Font.BOLD, 25));
		editsnf.setBounds(43, 85, 189, 43);
		panel.add(editsnf);
		
		JButton editrate = new JButton("Edit Rate\r\n\r\n");
		editrate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		editrate.setBounds(43, 168, 189, 43);
		panel.add(editrate);
	}
}
