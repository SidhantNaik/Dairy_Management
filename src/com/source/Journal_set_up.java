package com.source;

import java.awt.EventQueue;
import com.source.Milk_Collection.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;

import com.Database.Database;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Journal_set_up extends JFrame 
{

	private JPanel contentPane;
	public String datedata;
	public JLabel datedisplay;
	public String getdatevalue;

	public String time=null;
	public String time_data;
	
	public JLabel timing_display;
	public JComboBox comboBox;
	
	public static void Journal_set_up_method() //main(String args[])// //main(String args[])//////
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Journal_set_up frame = new Journal_set_up();
					frame.setLocation(570,100);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public Journal_set_up()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 471, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 567, 355);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel journalsetup = new JLabel("Journal Setup");
		journalsetup.setFont(new Font("Times New Roman", Font.BOLD, 28));
		journalsetup.setBounds(155, 10, 194, 58);
		panel.add(journalsetup);
		
		JLabel timing_set_up = new JLabel("Timing");
		timing_set_up.setFont(new Font("Arial Black", Font.BOLD, 17));
		timing_set_up.setBounds(37, 115, 95, 41);
		panel.add(timing_set_up);
		
		JLabel date_setup = new JLabel("Date");
		date_setup.setFont(new Font("Arial Black", Font.BOLD, 17));
		date_setup.setBounds(37, 213, 95, 41);
		panel.add(date_setup);
		
		
		datedisplay= new JLabel("");
		datedisplay.setFont(new Font("Arial Black", Font.BOLD, 15));
		
		Date date=new Date();
		
		SimpleDateFormat sdf_new=new SimpleDateFormat("dd-MM-yyyy");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		String str=sdf.format(date);
		
		String str1=sdf_new.format(date);
		
		datedisplay.setText(str1);
		
		datedata=datedisplay.getText();
		
		timing_display = new JLabel("");
		timing_display.setFont(new Font("Arial Black", Font.BOLD, 17));
		timing_display.setBounds(218, 160, 176, 26);
		panel.add(timing_display);
		
		
		JButton save_setup = new JButton("Save");
		save_setup.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					String selectItem=comboBox.getSelectedItem().toString();
					
					Milk_Collection m1=new Milk_Collection(selectItem);
					
					dispose();
					
					m1.Daily_Milk_Collection();
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,e2);
				}
			}
		});
		save_setup.setFont(new Font("Times New Roman", Font.BOLD, 19));
		save_setup.setBounds(156, 286, 107, 41);
		panel.add(save_setup);
		
		
		datedisplay.setBounds(186, 217, 163, 34);
		panel.add(datedisplay);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Morning", "Evening"}));
		comboBox.setToolTipText("Morning\r\nEvening");
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(186, 128, 163, 21);
		panel.add(comboBox);
		
		
	}
}
