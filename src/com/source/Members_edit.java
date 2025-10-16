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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Members_edit extends JFrame 
{

	private JPanel contentPane;
	private JTextField membercodetxt;
	private JTextField fnametxt;
	private JTextField mnametxt;
	private JTextField lnametxt;

	
	public static void members_edit()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Members_edit frame = new Members_edit();
					frame.setResizable(false);
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

	public Members_edit() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 558, 591);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel editamember = new JLabel("Update an existing member");
		editamember.setFont(new Font("Times New Roman", Font.BOLD, 24));
		editamember.setBounds(128, 0, 296, 60);
		panel.add(editamember);
		
		JLabel membercode = new JLabel("Enter Member Code");
		membercode.setFont(new Font("Arial Black", Font.BOLD, 15));
		membercode.setBounds(10, 62, 203, 49);
		panel.add(membercode);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"COW", "BUFFALO"}));
		comboBox.setBounds(229, 359, 177, 32);
		panel.add(comboBox);
		
		membercodetxt = new JTextField();
		membercodetxt.addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE  || e==null || membercodetxt.getText()==null)
					{
						fnametxt.setText("");
						mnametxt.setText("");
						lnametxt.setText("");
					}
					else 
					{
						Connection con=Database.getpublicConnection();
						try 
						{
							
							String sql="select * from members where member_code=?";
							
							PreparedStatement ps=con.prepareStatement(sql);
							
							ps.setString(1,membercodetxt.getText());
							
							ResultSet rs=ps.executeQuery();
							
							while(rs.next())
							{
								String first=rs.getString(2);
								String second=rs.getString(3);
								String third=rs.getString(4);
								String type_data=rs.getString(5);
								fnametxt.setText(first);
								mnametxt.setText(second);
								lnametxt.setText(third);
								comboBox.setSelectedItem(type_data);
							}
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null, e2);
						}
					} 
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		
		membercodetxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		membercodetxt.setBounds(239, 70, 103, 32);
		panel.add(membercodetxt);
		membercodetxt.setColumns(10);
		
		JLabel nameedit = new JLabel("First Name");
		nameedit.setFont(new Font("Arial Black", Font.BOLD, 15));
		nameedit.setBounds(10, 121, 128, 40);
		panel.add(nameedit);
		
		fnametxt = new JTextField();
		fnametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		fnametxt.setColumns(10);
		fnametxt.setBounds(197, 129, 303, 32);
		panel.add(fnametxt);
		

		
		
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					int id=Integer.parseInt(membercodetxt.getText());
					
					String fname=fnametxt.getText();
					
					String mname=mnametxt.getText();
					
					String lname=lnametxt.getText();
					
					String type=comboBox.getSelectedItem().toString();
					
					String sql="update members set first_name=?,middle_name=?,last_name=?,type=? where member_code=?";
					
					PreparedStatement ps=con.prepareStatement(sql);
					
					ps.setString(1,fname);
					ps.setString(2,mname);
					ps.setString(3,lname);
					ps.setString(4,type);
					ps.setInt(5, id);
					
					int data=ps.executeUpdate();
					
					int display=JOptionPane.showConfirmDialog(null,"Are you sure...!","Update a member",JOptionPane.YES_NO_OPTION);
					
					
					if(data>0)
					{
						if(display==JOptionPane.YES_OPTION)
						{
							JOptionPane.showMessageDialog(null,"Member Updated successfully");
						}
						if(display==JOptionPane.NO_OPTION)
						{
							System.exit(0);
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(null,"Invalid Member code");
					}
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Please Enter Information");
				}
			}
		});
		update.setFont(new Font("Times New Roman", Font.BOLD, 21));
		update.setBounds(61, 436, 116, 39);
		panel.add(update);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				membercodetxt.setText("");
				fnametxt.setText("");
				mnametxt.setText("");
				lnametxt.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 21));
		clear.setBounds(290, 436, 116, 39);
		panel.add(clear);
		
		JLabel lblType = new JLabel("Type\r\n");
		lblType.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblType.setBounds(10, 359, 63, 39);
		panel.add(lblType);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblMiddleName.setBounds(10, 199, 128, 40);
		panel.add(lblMiddleName);
		
		mnametxt = new JTextField();
		mnametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		mnametxt.setColumns(10);
		mnametxt.setBounds(197, 203, 303, 32);
		panel.add(mnametxt);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblLastName.setBounds(10, 273, 128, 40);
		panel.add(lblLastName);
		
		lnametxt = new JTextField();
		lnametxt.setFont(new Font("Arial Black", Font.BOLD, 15));
		lnametxt.setColumns(10);
		lnametxt.setBounds(197, 277, 303, 32);
		panel.add(lnametxt);
		
	}
}
