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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSet;
import com.Database.Database;


import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class Display_rate_table_cow extends JFrame 
{

	private JPanel contentPane;
	private JTable table1;

	
	public static void display_cow_rate_table()//main(String[] args) //
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Display_rate_table_cow frame = new Display_rate_table_cow();
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

	public Display_rate_table_cow()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 734, 569);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 637, 414);
		panel.add(scrollPane);
		
		table1 = new JTable();
		table1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table1.setFont(new Font("Arial Black", Font.BOLD, 13));
		scrollPane.setViewportView(table1);
		
		try 
		{
			Connection con=Database.getpublicConnection();
			
			Statement st=con.createStatement();
			
			String str="select * from cow_rates";
			
			ResultSet rs=st.executeQuery(str);
			
			ResultSetMetaData rsmd=rs.getMetaData();
			
			DefaultTableModel model=(DefaultTableModel)table1.getModel();
			
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
