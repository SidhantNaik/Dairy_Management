package com.source;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Display_rate_table_buffalo extends JFrame 
{

	private JPanel contentPane;
	private JTable table;

	public static void display_buffalo_rate_table()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Display_rate_table_buffalo frame = new Display_rate_table_buffalo();
					frame.setLocation(700,50);
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Display_rate_table_buffalo() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 699, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 665, 524);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial Black", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		
		
		try 
		{
			Connection con=Database.getpublicConnection();
			
			Statement st=con.createStatement();
			
			String str="select * from buffalo_rates";
			
			ResultSet rs=st.executeQuery(str);
			
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
				double fat_new=rs.getDouble(1);
				double snf_new=rs.getDouble(2);
				double rate_new=rs.getDouble(3);
				
				String fatString=String.valueOf(fat_new);
				String snfString=String.valueOf(snf_new);
				String rateString=String.valueOf(rate_new);
				
				
				String [] row= {fatString,snfString,rateString};
				model.addRow(row);
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
