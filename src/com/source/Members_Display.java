package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;
import com.mysql.cj.xdevapi.Table;

import java.awt.Panel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Members_Display extends JFrame 
{

	public JPanel contentPane;
	public JTable table_model;
	public JScrollPane scrollPane;

	public static void members_display()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Members_Display frame = new Members_Display();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public Members_Display()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1106, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 10, 1082, 648);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 1072, 638);
		panel.add(scrollPane);
		
		table_model = new JTable();
		table_model.setCellSelectionEnabled(true);
		table_model.setColumnSelectionAllowed(true);
		table_model.setFont(new Font("Arial Black", Font.BOLD, 14));
		scrollPane.setViewportView(table_model);	
		
		try 
		{
			Connection con=Database.getpublicConnection();
			
			Statement st=con.createStatement();
			
			String sql="select * from members";
			
			ResultSet rs=st.executeQuery(sql);
			
			ResultSetMetaData rsmd=rs.getMetaData();
			
			DefaultTableModel model=(DefaultTableModel)table_model.getModel();
			
			int column=rsmd.getColumnCount();
			
			String [] column_name=new String[column];
			
			for (int i=0;i<column;i++) 
			{
				column_name[i]=rsmd.getColumnName(i+1);
			}
			
			model.setColumnIdentifiers(column_name);
		
			int code;
			
			String fnmae,mname,lname,type_new;
			
			while(rs.next())
			{
				code=rs.getInt(1);
				
				String code_new=String.valueOf(code);
				
				fnmae=rs.getString(2);
				
				mname=rs.getString(3);
				
				lname=rs.getString(4);
				
				type_new=rs.getString(5);
				
				String [] row= {code_new,fnmae,mname,lname,type_new};
				
				model.addRow(row);
				
			}
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
