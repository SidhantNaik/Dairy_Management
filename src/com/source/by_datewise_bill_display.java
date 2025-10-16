package com.source;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class by_datewise_bill_display extends JFrame
{

	private JPanel contentPane;
	public String form_date_data,to_date_data;
	private JPanel panel;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField total_milk;
	private JLabel lblTotalAmount;
	private JTextField total_amount;
	
	public void datewise_bill_display() //main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					by_datewise_bill_display frame = new by_datewise_bill_display(form_date_data,to_date_data);
					
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public by_datewise_bill_display(String form_date_data,String to_date_data) 
	{
		this.form_date_data=form_date_data;
		this.to_date_data=to_date_data;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1132, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 1063, 660);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1026, 502);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial Black", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collection_Date", "Collection_time", "Member_code", "Member_name", "Type", "Litre", "Fat", "SNF", "Rate", "Amount"
			}
		));
		table.getColumnModel().getColumn(6).setPreferredWidth(15);
		table.getColumnModel().getColumn(7).setPreferredWidth(15);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("Total Milk");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel.setBounds(211, 543, 81, 23);
		panel.add(lblNewLabel);
		
		total_milk = new JTextField();
		total_milk.setText("                      Litre");
		total_milk.setEditable(false);
		total_milk.setFont(new Font("Arial Black", Font.BOLD, 13));
		total_milk.setBounds(302, 544, 136, 21);
		panel.add(total_milk);
		total_milk.setColumns(10);
		
		lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTotalAmount.setBounds(797, 543, 115, 23);
		panel.add(lblTotalAmount);
		
		total_amount = new JTextField();
		total_amount.setFont(new Font("Arial Black", Font.BOLD, 13));
		total_amount.setEditable(false);
		total_amount.setColumns(10);
		total_amount.setBounds(927, 544, 110, 21);
		panel.add(total_amount);
		
		try 
		{
			Connection con=Database.getpublicConnection();
		
			
			String sql="select * from milk_collection where collection_date between '"+form_date_data+"' and '"+to_date_data+"'";
			
			
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery(sql);
			
			ResultSetMetaData rsmd=rs.getMetaData();
			
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			
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
				
				SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				Date date_new=inputDateFormat.parse(date_data);
				
				String format=outputDateFormat.format(date_new);
				
				String time_data=rs.getString(2);
				
				int code_data=rs.getInt(3);
				
				String code_display=String.valueOf(code_data);
				
				String name_display=rs.getString(4);
				
				String type_new=rs.getString(5);
				
				double litre_new=rs.getDouble(6);
				double fat_new=rs.getDouble(7);
				double snf_new=rs.getDouble(8);
				double rate_new=rs.getDouble(9);
				Double amount_new=rs.getDouble(10);
				
				String litreString=String.valueOf(litre_new);
				String fatString=String.valueOf(fat_new);
				String snfString=String.valueOf(snf_new);
				String rateString=String.valueOf(rate_new);
				String amountString=String.valueOf(amount_new);
				
				String [] row2= {format,time_data,code_display,name_display,type_new,litreString,fatString,snfString,rateString,amountString};
				
				model.addRow(row2);
			}
			
			
			try 
			{
				Connection con1=Database.getpublicConnection();
				
				String str="select sum(litre) from milk_collection where collection_date between '"+form_date_data+"' and '"+to_date_data+"'";
				
				Statement st1=con1.createStatement();
				
				ResultSet rs1=st1.executeQuery(str);
				
				while(rs1.next())
				{
					double cal=rs1.getDouble(1);
				
					double cal_new=Math.round(cal*100)/100.0;
					
					String calculate=String.valueOf(cal_new);
					
					total_milk.setText(calculate);
					
				}
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, e);
			}
			
			try 
			{
				Connection con1=Database.getpublicConnection();
				
				String str="select sum(amount) from milk_collection where collection_date between '"+form_date_data+"' and '"+to_date_data+"'";
				
				Statement st1=con1.createStatement();
				
				ResultSet rs1=st1.executeQuery(str);
				
				while(rs1.next())
				{
					double cal=rs1.getDouble(1);
				
					double cal_new=Math.round(cal*100)/100.0;
					
					String calculate=String.valueOf(cal_new);
					
					total_amount.setText(calculate);
					
				}
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, e);
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
