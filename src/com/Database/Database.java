package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Database
{
	public static Connection getpublicConnection()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/dairy_login","root","");
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null,"Please try again");
		}
		return null;
	}
}
