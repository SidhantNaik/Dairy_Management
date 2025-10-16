package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.CSS;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import com.Database.Database;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Bill_Form_date_to_date extends JFrame 
{
	private JPanel contentPane;
	public JDateChooser to_date_1;
	public JDateChooser form_date_1;

	public static void bill_form_date_to_date() //main(String args[])// 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Bill_Form_date_to_date frame = new Bill_Form_date_to_date();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Bill_Form_date_to_date() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 687, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Display Milk Reports");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(206, 10, 239, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Form Date");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(37, 69, 145, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("to date");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(37, 145, 134, 31);
		contentPane.add(lblNewLabel_2);
		
		 to_date_1= new JDateChooser();
		to_date_1.setBounds(268, 145, 177, 31);
		
		contentPane.add(to_date_1);
		
		form_date_1 = new JDateChooser();
		form_date_1.setBounds(268, 69, 177, 31);
		contentPane.add(form_date_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Select Category");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(37, 224, 169, 31);
		contentPane.add(lblNewLabel_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "All Members", "By One Separate Member"}));
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 12));
		comboBox.setBounds(268, 228, 177, 31);
		contentPane.add(comboBox);
		
		JButton generate_bill =   new JButton("Generate Bill");
		generate_bill.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String option=comboBox.getSelectedItem().toString();
				
					
					if(option.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please select one category");
					}
					
					if(option.equals("All Members"))
					{		
						SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String form_date1_new=inputDateFormat.format(form_date_1.getDate());						
						String to_date1_new=inputDateFormat.format(to_date_1.getDate());
						by_datewise_bill_display d1=new by_datewise_bill_display(form_date1_new, to_date1_new);
						d1.datewise_bill_display();
					}
					
					if(option.equalsIgnoreCase("By One Separate Member"))
					{
						SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String form_date1_new=inputDateFormat.format(form_date_1.getDate());						
						String to_date1_new=inputDateFormat.format(to_date_1.getDate());
						
						By_One_Separte_Member b1=new By_One_Separte_Member(form_date1_new, to_date1_new);
						b1.by_one_member();
						
					}
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,e2);
				}
			}
		});
		generate_bill.setFont(new Font("Times New Roman", Font.BOLD, 20));
		generate_bill.setBounds(168, 322, 186, 37);
		contentPane.add(generate_bill);
		
		
	}
}
