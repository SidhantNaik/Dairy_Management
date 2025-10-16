package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class all_data_display_timing_wise extends JFrame
{

	private JPanel contentPane;
	String time1,time2;
	private JPanel panel;
	private JTable morning_table;
	private JScrollPane scrollPane;
	private JTable Evening_table;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel;
	private JLabel lblEveningWise;

	public void display_timing_wise()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					all_data_display_timing_wise frame = new all_data_display_timing_wise(time1,time2);
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	public all_data_display_timing_wise(String time1,String time2) 
	{
		this.time1=time1;
		this.time2=time2;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1129, 769);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 10, 1094, 712);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 530, 632);
		panel.add(scrollPane);
		
		morning_table = new JTable();
		morning_table.setFont(new Font("Arial Black", Font.BOLD, 10));
		morning_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collection_Date", "Memeber_Id", "Litre", "Fat", "SNF", "Rate", "Amount"
			}
		));
		scrollPane.setViewportView(morning_table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(549, 55, 535, 631);
		panel.add(scrollPane_1);
		
		Evening_table = new JTable();
		Evening_table.setFont(new Font("Arial Black", Font.BOLD, 10));
		Evening_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collection_Date", "Member_Id", "Litre", "Fat", "SNF", "Rate", "Amount"
			}
		));
		scrollPane_1.setViewportView(Evening_table);
		
		lblNewLabel = new JLabel("Morning Wise\r\n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(163, 10, 242, 42);
		panel.add(lblNewLabel);
		
		lblEveningWise = new JLabel("Evening Wise\r\n");
		lblEveningWise.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEveningWise.setBounds(687, 10, 242, 42);
		panel.add(lblEveningWise);
		
		
		String str1 ="Morning";
		String str2="Evening";
		
		if(str1=="Morning")
		{
			try
			{
					Connection con=Database.getpublicConnection();
					
					String str="select collection_date,member_code,litre,fat,snf,rate,amount FROM milk_collection where (collection_date BETWEEN '"+time1+"' AND '"+time2+"') and collection_timing ='Morning'";
					
					Statement statement=con.createStatement();
					
					ResultSet rs=statement.executeQuery(str);
					
					ResultSetMetaData rsmd=rs.getMetaData();
					
					DefaultTableModel model=(DefaultTableModel)morning_table.getModel();
					
					int column=rsmd.getColumnCount();
					
					String [] column_name=new String[column];
					
					for (int i=0;i<column;i++) 
					{
						column_name[i]=rsmd.getColumnName(i+1);
					}
					
					model.setColumnIdentifiers(column_name);
					
					
					while(rs.next())
					{
						String date_data=rs.getString(1);
						
						SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

						
						Date date_new=inputDateFormat.parse(date_data);
						
						String format=outputDateFormat.format(date_new);
						
						int code_data=rs.getInt(2);
						
						String code_display=String.valueOf(code_data);
						
						double litre_new=rs.getDouble(3);
						double fat_new=rs.getDouble(4);
						double snf_new=rs.getDouble(5);
						double rate_new=rs.getDouble(6);
						Double amount_new=rs.getDouble(7);
						
						String litreString=String.valueOf(litre_new);
						String fatString=String.valueOf(fat_new);
						String snfString=String.valueOf(snf_new);
						String rateString=String.valueOf(rate_new);
						String amountString=String.valueOf(amount_new);
						
						String [] row2= {format,code_display,litreString,fatString,snfString,rateString,amountString};
						
						model.addRow(row2);
					}
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2);
				}
		}
		if(str2=="Evening")
		{
			try
			{
					Connection con=Database.getpublicConnection();
					
					String str="select collection_date,member_code,litre,fat,snf,rate,amount FROM milk_collection where (collection_date BETWEEN '"+time1+"' AND '"+time2+"') and collection_timing ='Evening'";
					
					Statement statement=con.createStatement();
					
					ResultSet rs=statement.executeQuery(str);
					
					ResultSetMetaData rsmd=rs.getMetaData();
					
					DefaultTableModel model=(DefaultTableModel)Evening_table.getModel();
					
					int column=rsmd.getColumnCount();
					
					String [] column_name=new String[column];
					
					for (int i=0;i<column;i++) 
					{
						column_name[i]=rsmd.getColumnName(i+1);
					}
					
					model.setColumnIdentifiers(column_name);
					
					
					while(rs.next())
					{
						String date_data=rs.getString(1);
						
						SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

						
						Date date_new=inputDateFormat.parse(date_data);
						
						String format=outputDateFormat.format(date_new);
						
						int code_data=rs.getInt(2);
						
						String code_display=String.valueOf(code_data);
						
						double litre_new=rs.getDouble(3);
						double fat_new=rs.getDouble(4);
						double snf_new=rs.getDouble(5);
						double rate_new=rs.getDouble(6);
						Double amount_new=rs.getDouble(7);
						
						String litreString=String.valueOf(litre_new);
						String fatString=String.valueOf(fat_new);
						String snfString=String.valueOf(snf_new);
						String rateString=String.valueOf(rate_new);
						String amountString=String.valueOf(amount_new);
						
						String [] row2= {format,code_display,litreString,fatString,snfString,rateString,amountString};
						
						model.addRow(row2);
					}
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2);
				}
		
		
		}
	}
}
