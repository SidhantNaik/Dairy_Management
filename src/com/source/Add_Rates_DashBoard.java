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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class Add_Rates_DashBoard extends JFrame 
{

	private JPanel contentPane;
	private JTextField fat;
	private JTextField snf;
	private JTextField rate;
	
	public static void add_rates_dash() //main(String[] args) ////
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Add_Rates_DashBoard frame = new Add_Rates_DashBoard();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public Add_Rates_DashBoard() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 523, 534);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel ratemanagement = new JLabel("Rate Management Department");
		ratemanagement.setFont(new Font("Times New Roman", Font.BOLD, 30));
		ratemanagement.setBounds(55, 24, 420, 32);
		panel.add(ratemanagement);
		
		JLabel fat_label = new JLabel("Fat\r\n");
		fat_label.setFont(new Font("Arial Black", Font.BOLD, 20));
		fat_label.setBounds(76, 221, 71, 25);
		panel.add(fat_label);
		
		fat = new JTextField();
		fat.setFont(new Font("Arial Black", Font.BOLD, 15));
		fat.setBounds(253, 222, 132, 26);
		panel.add(fat);
		fat.setColumns(10);
		
		JLabel snf_label = new JLabel("SNF");
		snf_label.setFont(new Font("Arial Black", Font.BOLD, 20));
		snf_label.setBounds(76, 295, 71, 25);
		panel.add(snf_label);
		
		JLabel rate_label = new JLabel("Rate");
		rate_label.setFont(new Font("Arial Black", Font.BOLD, 20));
		rate_label.setBounds(76, 381, 71, 25);
		panel.add(rate_label);
		
		snf = new JTextField();
		snf.setFont(new Font("Arial Black", Font.BOLD, 15));
		snf.setColumns(10);
		snf.setBounds(253, 294, 132, 26);
		panel.add(snf);
		
		rate = new JTextField();
		rate.setFont(new Font("Arial Black", Font.BOLD, 15));
		rate.setColumns(10);
		rate.setBounds(253, 380, 132, 26);
		panel.add(rate);
		
		JComboBox select_type = new JComboBox();
		select_type.setModel(new DefaultComboBoxModel(new String[] {"None", "Cow ", "Buffalo"}));
		select_type.setFont(new Font("Arial Black", Font.BOLD, 15));
		select_type.setBounds(239, 126, 173, 32);
		panel.add(select_type);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					String str1=select_type.getSelectedItem().toString();
					
					if(str1.equalsIgnoreCase("None"))
					{
						JOptionPane.showMessageDialog(null,"Please choice any one option");
					}
					if(str1.equalsIgnoreCase("BUFFALO"))
					{
						String sql="insert into buffalo_rates values(?,?,?)";
						
						PreparedStatement ps=con.prepareStatement(sql);
						
						double fat_new=Double.parseDouble(fat.getText());
						
						double snf_new=Double.parseDouble(snf.getText());
						
						double rate_new=Double.parseDouble(rate.getText());
						
						ps.setDouble(1, fat_new);
						ps.setDouble(2, snf_new);
						ps.setDouble(3, rate_new);
						
						int data=ps.executeUpdate();
						
						if(data>0)
						{
							int display=JOptionPane.showConfirmDialog(null,"Are you sure...!","",JOptionPane.YES_NO_OPTION);
							
							if(display==JOptionPane.YES_OPTION)
							{
								JOptionPane.showMessageDialog(null,"Records added successfully");
							}
							if(display==JOptionPane.NO_OPTION)
							{
								System.exit(0);
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Sorry Please try again");
						}
						con.close();
					}
					if(str1.equalsIgnoreCase("COW"))
					{
						try 
						{
							String sql="insert into cow_rates values(?,?,?)";
							
							PreparedStatement ps=con.prepareStatement(sql);
							
							double fat_new=Double.parseDouble(fat.getText());
							
							double snf_new=Double.parseDouble(snf.getText());
							
							double rate_new=Double.parseDouble(rate.getText());
							
							ps.setDouble(1, fat_new);
							ps.setDouble(2, snf_new);
							ps.setDouble(3, rate_new);
							
							int data=ps.executeUpdate();
							
							if(data>0)
							{
								int display=JOptionPane.showConfirmDialog(null,"Are you sure...!","",JOptionPane.YES_NO_OPTION);
								
								if(display==JOptionPane.YES_OPTION)
								{
									JOptionPane.showMessageDialog(null,"Records added successfully");
								}
								if(display==JOptionPane.NO_OPTION)
								{
									System.exit(0);
								}
								
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Sorry Please try again");
							}
							
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null,"Enter valid inforamtion");
						}
					}
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Enter valid Inforamtion");
				}
			}
		});
		submit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		submit.setBounds(55, 462, 117, 32);
		panel.add(submit);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fat.setText("");
				snf.setText("");
				rate.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 25));
		clear.setBounds(255, 462, 117, 32);
		panel.add(clear);
		
		JLabel fat_label_1 = new JLabel("Select type");
		fat_label_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		fat_label_1.setBounds(48, 119, 140, 38);
		panel.add(fat_label_1);
		
	
	}
}
