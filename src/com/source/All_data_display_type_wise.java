package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;

public class All_data_display_type_wise extends JFrame 
{

	private JPanel contentPane;
	private JTable cow_wise;
	private JTable buffalo_wise;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel;
	private JLabel lblEveningWise;
	
	String time1,time2;
	
	public void type_wise()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					All_data_display_type_wise frame = new All_data_display_type_wise(time1,time2);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public All_data_display_type_wise(String time1,String time2) 
	{
		this.time1=time1;
		this.time2=time2;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1128, 752);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1094, 695);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 80, 530, 605);
		panel.add(scrollPane);
		
		cow_wise = new JTable();
		cow_wise.setFont(new Font("Arial Black", Font.BOLD, 10));
		cow_wise.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collection_Date", "Member_Id", "Litre", "Fat", "SNF", "Rate", "Amount"
			}
		));
		cow_wise.getColumnModel().getColumn(0).setPreferredWidth(81);
		cow_wise.getColumnModel().getColumn(1).setPreferredWidth(66);
		scrollPane.setViewportView(cow_wise);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(540, 80, 544, 605);
		panel.add(scrollPane_1);
		
		buffalo_wise = new JTable();
		buffalo_wise.setFont(new Font("Arial Black", Font.BOLD, 10));
		buffalo_wise.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collection_Date", "Member_Id", "Litre", "Fat", "SNF", "Rate", "Amount"
			}
		));
		buffalo_wise.getColumnModel().getColumn(0).setPreferredWidth(85);
		scrollPane_1.setViewportView(buffalo_wise);
		
		lblNewLabel = new JLabel("COW \r\n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(115, 26, 231, 37);
		panel.add(lblNewLabel);
		
		lblEveningWise = new JLabel("BUFFALO");
		lblEveningWise.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEveningWise.setBounds(728, 26, 116, 37);
		panel.add(lblEveningWise);
		
		
		String str1 ="COW";
		String str2="BUFFALO";
		
		if(str1=="COW")
		{
			try
			{
					Connection con=Database.getpublicConnection();
					
					String str="select collection_date,member_code,litre,fat,snf,rate,amount FROM milk_collection where (collection_date BETWEEN '"+time1+"' AND '"+time2+"') and type ='COW'";
					
					Statement statement=con.createStatement();
					
					ResultSet rs=statement.executeQuery(str);
					
					ResultSetMetaData rsmd=rs.getMetaData();
					
					DefaultTableModel model=(DefaultTableModel)cow_wise.getModel();
					
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
		if(str2=="BUFFALO")
		{
			try
			{
					Connection con=Database.getpublicConnection();
					
					String str="select collection_date,member_code,litre,fat,snf,rate,amount FROM milk_collection where (collection_date BETWEEN '"+time1+"' AND '"+time2+"') and type='BUFFALO'";
					
					Statement statement=con.createStatement();
					
					ResultSet rs=statement.executeQuery(str);
					
					ResultSetMetaData rsmd=rs.getMetaData();
					
					DefaultTableModel model=(DefaultTableModel)buffalo_wise.getModel();
					
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
