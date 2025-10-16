package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class COW_Rates extends JFrame 
{

	private JPanel contentPane;
	private JTextField fattxt;
	private JTextField snftxt;
	private JTextField ratetxt;

	
	public static void main(String[] args)//add_new_rates()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					COW_Rates frame = new COW_Rates();
					frame.setLocation(835,80);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public COW_Rates() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 536, 429);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel enterfat = new JLabel("Enter Fat\r\n");
		enterfat.setFont(new Font("Arial Black", Font.BOLD, 16));
		enterfat.setBounds(20, 76, 130, 35);
		panel.add(enterfat);
		
		JLabel snfrate = new JLabel("Enter SNF");
		snfrate.setFont(new Font("Arial Black", Font.BOLD, 16));
		snfrate.setBounds(20, 160, 130, 35);
		panel.add(snfrate);
		
		JLabel enterrate = new JLabel("Enter Rate");
		enterrate.setFont(new Font("Arial Black", Font.BOLD, 16));
		enterrate.setBounds(20, 247, 130, 35);
		panel.add(enterrate);
		
		fattxt = new JTextField();
		fattxt.setFont(new Font("Arial Black", Font.BOLD, 16));
		fattxt.setBounds(200, 76, 235, 35);
		panel.add(fattxt);
		fattxt.setColumns(10);
		
		snftxt = new JTextField();
		snftxt.setFont(new Font("Arial Black", Font.BOLD, 16));
		snftxt.setColumns(10);
		snftxt.setBounds(200, 160, 235, 35);
		panel.add(snftxt);
		
		ratetxt = new JTextField();
		ratetxt.setFont(new Font("Arial Black", Font.BOLD, 16));
		ratetxt.setColumns(10);
		ratetxt.setBounds(200, 247, 235, 35);
		panel.add(ratetxt);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					String sql="insert into cow_rates values(?,?,?)";
					
					PreparedStatement ps=con.prepareStatement(sql);
					
					double fat_new1=Double.parseDouble(fattxt.getText());
					
					double snf_new1=Double.parseDouble(snftxt.getText());
					
					double rate_new1=Double.parseDouble(ratetxt.getText());
					
					if(fat_new1>=2.9 && fat_new1>=5.1)
					{
						ps.setDouble(1, fat_new1);
						ps.setDouble(2, snf_new1);
						ps.setDouble(3, rate_new1);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Please enter valid fat");
					}
					
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
		save.setBounds(61, 335, 123, 35);
		panel.add(save);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fattxt.setText("");
				snftxt.setText("");
				ratetxt.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 21));
		clear.setBounds(289, 334, 123, 35);
		panel.add(clear);
		
		JLabel lblNewLabel = new JLabel("COW Rate Management\r\n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(80, 10, 355, 28);
		panel.add(lblNewLabel);
	}
}
