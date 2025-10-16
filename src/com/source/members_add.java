package com.source;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.Database.Database;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class members_add extends JFrame 
{
	private JPanel contentPane;
	private JTextField firstnametxt;
	private JLabel lastname;
	private JTextField middlenametxt;
	private JTextField lastnametxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public static void members_add_setup()//main(String args[])//
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					members_add frame = new members_add();
					frame.setLocation(400,200);
					frame.setResizable(false);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public members_add() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 476, 427);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel newmember = new JLabel("New Member Registration");
		newmember.setFont(new Font("Times New Roman", Font.BOLD, 28));
		newmember.setBounds(70, 10, 330, 41);
		panel.add(newmember);
		
		
		JLabel firstname = new JLabel("First Name\r\n");
		firstname.setFont(new Font("Arial Black", Font.BOLD, 15));
		firstname.setBounds(24, 81, 173, 34);
		panel.add(firstname);
		
		firstnametxt = new JTextField();
		firstnametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		firstnametxt.setColumns(10);
		firstnametxt.setBounds(162, 81, 302, 34);
		panel.add(firstnametxt);
		
		JLabel Type = new JLabel("Type");
		Type.setFont(new Font("Arial Black", Font.BOLD, 17));
		Type.setBounds(36, 293, 91, 34);
		panel.add(Type);
		
		JRadioButton cow = new JRadioButton("COW");
		buttonGroup.add(cow);
		cow.setFont(new Font("Arial Black", Font.BOLD, 15));
		cow.setBounds(175, 301, 103, 21);
		panel.add(cow);
		
		JRadioButton buffalo = new JRadioButton("BUFFALO");
		buttonGroup.add(buffalo);
		buffalo.setFont(new Font("Arial Black", Font.BOLD, 15));
		buffalo.setBounds(298, 301, 135, 21);
		panel.add(buffalo);
		
		JButton save = new JButton("SAVE\r\n");
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				String str=firstnametxt.getText();
				String str1=middlenametxt.getText();
				String str2=lastnametxt.getText();
				

				String opt=null;
				
				
				if(cow.isSelected())
				{
					opt="COW";
				}
				if(buffalo.isSelected())
				{
					opt="BUFFALO";
				}
				
				
				if(str.length()==0 && str1.length()==0 && str2.length()==0)
				{
					JOptionPane.showMessageDialog(null,"Please Enter Details");
				}
				
				else
				{
					try 
					{
						Connection con=Database.getpublicConnection();
						
						String fname=firstnametxt.getText();
						String mname=middlenametxt.getText();
						String lname=lastnametxt.getText();
						
						String sql="insert into members (first_name,middle_name,last_name,type)VALUES('"+fname+"','"+mname+"','"+lname+"','"+opt+"')";
						
						Statement st=con.createStatement();
											
						int data_new=st.executeUpdate(sql);
							
						if(data_new>0)
						{
							JOptionPane.showMessageDialog(null,"Member Added Successfully");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Please try again");
						}
						firstnametxt.setText("");
						middlenametxt.setText("");
						lastnametxt.setText("");
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null,"Please Enter Valid information");
					}
				}
				
			}
		});
		save.setFont(new Font("Times New Roman", Font.BOLD, 17));
		save.setBounds(162, 361, 116, 41);
		panel.add(save);
		
		JLabel middlename = new JLabel("Middle Name");
		middlename.setFont(new Font("Arial Black", Font.BOLD, 15));
		middlename.setBounds(24, 156, 158, 34);
		panel.add(middlename);
		
		lastname = new JLabel("Last Name\r\n");
		lastname.setFont(new Font("Arial Black", Font.BOLD, 15));
		lastname.setBounds(24, 229, 151, 34);
		panel.add(lastname);
		
		middlenametxt = new JTextField();
		middlenametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		middlenametxt.setColumns(10);
		middlenametxt.setBounds(162, 156, 302, 34);
		panel.add(middlenametxt);
		
		lastnametxt = new JTextField();
		lastnametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		lastnametxt.setColumns(10);
		lastnametxt.setBounds(162, 233, 302, 34);
		panel.add(lastnametxt);
	}
}
