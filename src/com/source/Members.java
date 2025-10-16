package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Database;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Members extends JFrame 
{
	private JPanel contentPane;
	
	public static void members_dashboard()//main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Members frame = new Members();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setLocation(553,80);
					frame.setResizable(false);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}

	public Members() 
	{
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 261, 271);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton addmember = new JButton("Add New Member");
		addmember.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				members_add.members_add_setup();
				
				dispose();
			}
		});
		addmember.setFont(new Font("Times New Roman", Font.BOLD, 22));
		addmember.setBounds(10, 96, 220, 34);
		panel.add(addmember);
		
		JButton editmember = new JButton("Edit A Existing Member");
		editmember.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Members_edit.members_edit();
			}
		});
		editmember.setFont(new Font("Times New Roman", Font.BOLD, 20));
		editmember.setBounds(10, 162, 251, 34);
		panel.add(editmember);
		
		JButton removemember = new JButton("Remove A Member");
		removemember.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Remove_a_member.remove_member();
			}
		});
		removemember.setFont(new Font("Times New Roman", Font.BOLD, 22));
		removemember.setBounds(10, 227, 220, 34);
		panel.add(removemember);
		
		JButton allmembers = new JButton("All Members List");
		allmembers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Members_Display.members_display();
			}
		});
		allmembers.setFont(new Font("Times New Roman", Font.BOLD, 22));
		allmembers.setBounds(10, 33, 220, 34);
		panel.add(allmembers);
		
		JLabel bg = new JLabel("");
		bg.setBackground(Color.BLACK);
		bg.setForeground(Color.BLACK);
		bg.setBounds(0, 0, 277, 271);
		panel.add(bg);
	}
}
