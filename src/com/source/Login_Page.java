package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

public class Login_Page extends JFrame 
{

	private JPanel contentPane;
	private static JTextField usernametext;
	private static JPasswordField passwordField;
	
	public static void login()//main(String[] args) //// 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Login_Page frame = new Login_Page();
					frame.setResizable(false);
					frame.setLocation(550,220);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public Login_Page() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginarea = new JLabel("LOGIN AREA");
		loginarea.setBounds(135, 10, 217, 26);
		loginarea.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(loginarea);
		
		JLabel username = new JLabel("UserName");
		username.setFont(new Font("Arial Black", Font.BOLD, 19));
		username.setBounds(10, 71, 127, 34);
		contentPane.add(username);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Arial Black", Font.BOLD, 19));
		password.setBounds(10, 130, 115, 34);
		contentPane.add(password);
		
		usernametext = new JTextField();
		usernametext.setFont(new Font("Arial Black", Font.BOLD, 14));
		usernametext.setBounds(187, 74, 217, 34);
		contentPane.add(usernametext);
		usernametext.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try 
					{
						Connection con=Database.getpublicConnection();
						
						boolean flag=false;
						
						String sql="select * from login";
						
						PreparedStatement ps=con.prepareStatement(sql);
						
						String str=usernametext.getText();
						
						String pass1=passwordField.getText();
						
						ResultSet rs=ps.executeQuery();
						
						while(rs.next())
						{
							String uname=rs.getString(1);
							
							String pass=rs.getString(2);
							
							if(str.equalsIgnoreCase(uname) && pass1.equals(pass))
							{
								flag=true;
								break;
							}
						}
						if(flag==true) 
						{
							dispose();
							DashBoard1.dashboard_main();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Username and Password Incorrect");
						}
						
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null,"Please connect to database");
					}
				}
			}
		});
		passwordField.setFont(new Font("Arial Black", Font.BOLD, 14));
		passwordField.setBounds(187, 133, 217, 34);
		contentPane.add(passwordField);
		
		JButton login = new JButton("LOGIN");
		login.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try 
					{
						Connection con=Database.getpublicConnection();
						
						boolean flag=false;
						
						String sql="select * from login";
						
						PreparedStatement ps=con.prepareStatement(sql);
						
						String str=usernametext.getText();
						
						String pass1=passwordField.getText();
						
						ResultSet rs=ps.executeQuery();
						
						while(rs.next())
						{
							String uname=rs.getString(1);
							
							String pass=rs.getString(2);
							
							if(str.equalsIgnoreCase(uname) && pass1.equals(pass))
							{
								flag=true;
								break;
							}
						}
						if(flag==true) 
						{
							dispose();
							DashBoard1.dashboard_main();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Username and Password Incorrect");
						}
						
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null,"Please connect to database");
					}
				}
			}
		});
		login.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					boolean flag=false;
					
					String sql="select * from login";
					
					PreparedStatement ps=con.prepareStatement(sql);
					
					String str=usernametext.getText();
					
					String pass1=passwordField.getText();
					
					ResultSet rs=ps.executeQuery();
					
					while(rs.next())
					{
						String uname=rs.getString(1);
						
						String pass=rs.getString(2);
						
						if(str.equalsIgnoreCase(uname) && pass1.equals(pass))
						{
							flag=true;
							break;
						}
					}
					if(flag==true) 
					{
						dispose();
						DashBoard1.dashboard_main();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Username and Password Incorrect");
					}
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Please connect to database");
				}
			}
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 18));
		login.setBounds(68, 235, 108, 33);
		contentPane.add(login);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				usernametext.setText("");
				passwordField.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		clear.setBounds(269, 235, 108, 33);
		contentPane.add(clear);
		
		JCheckBox show = new JCheckBox("Show");
		show.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(show.isSelected())
				{
					passwordField.setEchoChar((char)0);
				}
				else
				{
					passwordField.setEchoChar('●');
				}
			}
		});
		show.setFont(new Font("Arial Black", Font.BOLD, 12));
		show.setBounds(410, 141, 65, 23);
		contentPane.add(show);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\Dairy_Management\\src\\com\\source\\log_in.jpg"));
		background.setBounds(0, 0, 497, 278);
		contentPane.add(background);
	}
}
