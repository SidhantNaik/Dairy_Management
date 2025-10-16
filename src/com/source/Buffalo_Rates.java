package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Database;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Buffalo_Rates extends JFrame 
{
	private JPanel contentPane;
	private JTextField fat_txt;
	private JTextField rate_txt;
	private JTextField snf_txt;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Buffalo_Rates frame = new Buffalo_Rates();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Buffalo_Rates() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 536, 429);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buffalo Rate Management");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(139, 10, 307, 35);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Rate");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(25, 218, 134, 35);
		panel.add(lblNewLabel_1);
		
		fat_txt = new JTextField();
		fat_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		fat_txt.setBounds(201, 69, 173, 35);
		panel.add(fat_txt);
		fat_txt.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Fat\r\n");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(25, 69, 134, 35);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter SNF");
		lblNewLabel_1_2.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(25, 144, 134, 35);
		panel.add(lblNewLabel_1_2);
		
		rate_txt = new JTextField();
		rate_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		rate_txt.setColumns(10);
		rate_txt.setBounds(201, 218, 173, 35);
		panel.add(rate_txt);
		
		snf_txt = new JTextField();
		snf_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		snf_txt.setColumns(10);
		snf_txt.setBounds(201, 144, 173, 35);
		panel.add(snf_txt);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					String sql="insert into buffalo_rates values(?,?,?)";
					
					PreparedStatement ps=con.prepareStatement(sql);
					
					double fat_new1=Double.parseDouble(fat_txt.getText());
					
					double snf_new1=Double.parseDouble(snf_txt.getText());
					
					double rate_new1=Double.parseDouble(rate_txt.getText());
					
					ps.setDouble(1, fat_new1);
					ps.setDouble(2, snf_new1);
					ps.setDouble(3, rate_new1);
					
					
					int data=ps.executeUpdate();
					
					if(data>0)
					{
						JOptionPane.showMessageDialog(null,"Data was added");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid input");
					}
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Invalid input please try again");
				}
			}
		});
		save.setFont(new Font("Times New Roman", Font.BOLD, 20));
		save.setBounds(49, 309, 110, 35);
		panel.add(save);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fat_txt.setText("");
				snf_txt.setText("");
				rate_txt.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		clear.setBounds(264, 309, 110, 35);
		panel.add(clear);
	}
}
