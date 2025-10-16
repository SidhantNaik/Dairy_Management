package com.source;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import java.awt.Color;

public class Members_Dash extends JInternalFrame 
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Members_Dash  frame1 = new Members_Dash();
					frame1.setVisible(true);
					frame1.setLocation(553,80);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Members_Dash() throws PropertyVetoException
	{
		setBackground(Color.RED);
		setResizable(true);
		setMaximizable(true);
		setMaximum(true);
		setBounds(100, 100, 450, 300);

	}

}
