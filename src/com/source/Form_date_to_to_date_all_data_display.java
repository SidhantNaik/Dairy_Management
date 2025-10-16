package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.Database.Database;
import com.mysql.cj.exceptions.RSAException;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Form_date_to_to_date_all_data_display extends JFrame
{

	private JPanel contentPane;
	public static void all_data_display()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Form_date_to_to_date_all_data_display frame = new Form_date_to_to_date_all_data_display();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Form_date_to_to_date_all_data_display() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 442, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 428, 442);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("From Date\r\n");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 104, 163, 35);
		panel.add(lblNewLabel);
		
		JDateChooser date1 = new JDateChooser();
		date1.setBounds(232, 104, 179, 35);
		panel.add(date1);
		
		JLabel lblToDate = new JLabel("To Date\r\n");
		lblToDate.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblToDate.setBounds(10, 193, 163, 35);
		panel.add(lblToDate);
		
		JDateChooser to_date = new JDateChooser();
		to_date.setBounds(232, 193, 179, 35);
		panel.add(to_date);
		
		JLabel lblSelectCategory = new JLabel("Select Category");
		lblSelectCategory.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSelectCategory.setBounds(10, 279, 163, 35);
		panel.add(lblSelectCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "By Timing Wise Bill", "By Type Wise Bill"}));
		comboBox.setBounds(232, 284, 179, 30);
		panel.add(comboBox);
		
		JLabel lblDisplayAllData = new JLabel("Display All Data");
		lblDisplayAllData.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDisplayAllData.setBounds(97, 25, 229, 35);
		panel.add(lblDisplayAllData);
		
		JButton display_btn = new JButton("Display\r\n");
		display_btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
					String opt=comboBox.getSelectedItem().toString();
					
					SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					
					
					if(opt=="")
					{
						JOptionPane.showMessageDialog(null,"Select Any One Category");
					}
					String form_date1_new=inputDateFormat.format(date1.getDate());						
					String to_date1_new=inputDateFormat.format(to_date.getDate());
					
					
					if(opt.equals("By Timing Wise Bill"))
					{
						all_data_display_timing_wise a1=new all_data_display_timing_wise(form_date1_new, to_date1_new);
						a1.display_timing_wise();
					}
					if(opt.equals("By Type Wise Bill"))
					{
						All_data_display_type_wise al=new All_data_display_type_wise(form_date1_new, to_date1_new);
						al.type_wise();
					}
			}
		});
		display_btn.setFont(new Font("Times New Roman", Font.BOLD, 23));
		display_btn.setBounds(118, 355, 163, 35);
		panel.add(display_btn);
	}
}
