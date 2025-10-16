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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Remove_a_member extends JFrame 
{

	private JPanel contentPane;
	private JTextField removemember;

	public static void remove_member()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Remove_a_member frame = new Remove_a_member();
					frame.setLocation(400,200);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public Remove_a_member() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 452, 387);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel removeamember = new JLabel("Remove A Member");
		removeamember.setFont(new Font("Times New Roman", Font.BOLD, 25));
		removeamember.setBounds(78, 25, 282, 33);
		panel.add(removeamember);
		
		JLabel entermembercodetoremoveamemeber = new JLabel("Enter Member code to Remove a Member");
		entermembercodetoremoveamemeber.setFont(new Font("Arial Black", Font.BOLD, 15));
		entermembercodetoremoveamemeber.setBounds(30, 91, 487, 47);
		panel.add(entermembercodetoremoveamemeber);
		
		JLabel nametxt = new JLabel("");
		nametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		nametxt.setBounds(92, 216, 350, 38);
		panel.add(nametxt);
		
		
		removemember = new JTextField();
		removemember.setFont(new Font("Arial Black", Font.BOLD, 14));
		removemember.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				String str=removemember.getText();
				
				if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE || e==null)
				{
					nametxt.setText("");
				}
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
						try 
						{
							Connection con=Database.getpublicConnection();
							
							String sql="select * from members where member_code=?";
							
							PreparedStatement ps=con.prepareStatement(sql);
							
							ps.setString(1,removemember.getText());
							
							ResultSet rs=ps.executeQuery();
							
							while(rs.next())
							{
								String first=rs.getString(2);
								String second=rs.getString(3);
								String third=rs.getString(4);
								nametxt.setText(first+" "+second+" "+third);
							}
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null, e2);
						}
			
				 }
			}
		});
		removemember.setBounds(147, 162, 83, 33);
		panel.add(removemember);
		removemember.setColumns(10);
		
		
		
		JButton remove = new JButton("Remove\r\n");
		remove.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					Statement statement=con.createStatement();
					
					int i=Integer.parseInt(removemember.getText());
					
					int data=statement.executeUpdate("delete from members where member_code="+i);
					
					if(data>0)
					{

						int display=JOptionPane.showConfirmDialog(null,"Are you sure...!","Remove a member",JOptionPane.YES_NO_OPTION);
						
						if(display==JOptionPane.YES_OPTION)
						{
							JOptionPane.showMessageDialog(null,"Member removed successfully");
						}
						if(display==JOptionPane.NO_OPTION)
						{
							System.exit(0);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid Member Code");
					}
					removemember.setText("");
					nametxt.setText("");
					con.close();
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Please Enter Code To Remove");
				}
			}
		});
		remove.setFont(new Font("Times New Roman", Font.BOLD, 19));
		remove.setBounds(119, 296, 139, 38);
		panel.add(remove);
	}
}
