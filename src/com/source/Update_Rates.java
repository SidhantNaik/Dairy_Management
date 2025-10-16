package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
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

public class Update_Rates extends JFrame
{

	private JPanel contentPane;
	private JTextField txt_fat;
	private JTextField txt_snf;
	private JTextField txt_old_rate;
	private JTextField txt_new_rate;

	public static void update_rates()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Update_Rates frame = new Update_Rates();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public Update_Rates() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 413, 446);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Rates");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(117, 10, 163, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Type");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(24, 68, 142, 28);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "COW", "BUFFALO"}));
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setBounds(201, 68, 163, 28);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Fat");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(24, 141, 142, 28);
		panel.add(lblNewLabel_1_1);
		
		txt_fat = new JTextField();
		txt_fat.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_fat.setBounds(201, 141, 142, 28);
		panel.add(txt_fat);
		txt_fat.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Enter SNF");
		lblNewLabel_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(24, 219, 142, 28);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Old Rate is");
		lblNewLabel_1_1_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(24, 288, 142, 28);
		panel.add(lblNewLabel_1_1_2);
		
		txt_snf = new JTextField();
		txt_snf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try 
				{
					
					if(txt_fat.getText()==" " &&txt_snf.getText()==" "&&txt_old_rate.getText()=="")
					{
						JOptionPane.showMessageDialog(null,"Please fill out all fields");
					}
					else
					{
						Connection con=Database.getpublicConnection();
						
						String str=comboBox.getSelectedItem().toString();
						
						
						if(str.equalsIgnoreCase("COW"))
						{
							String sql="select rate from cow_rates where fat=? and snf=?";
							
							PreparedStatement ps=con.prepareStatement(sql);
							
							ps.setDouble(1, Double.parseDouble(txt_fat.getText()));
							ps.setDouble(2, Double.parseDouble(txt_snf.getText()));
							
							ResultSet rs=ps.executeQuery();
							
							while(rs.next())
							{
								double r1=rs.getDouble(1);
								
								String r11=String.valueOf(r1);
								
								txt_old_rate.setText(r11);
								
							}
						}
						if(str.equalsIgnoreCase("BUFFALO"))
						{
							String sql="select rate from buffalo_rates where fat=? and snf=?";
							
							PreparedStatement ps=con.prepareStatement(sql);
							
							ps.setDouble(1, Double.parseDouble(txt_fat.getText()));
							ps.setDouble(2, Double.parseDouble(txt_snf.getText()));
							
							ResultSet rs=ps.executeQuery();
							
							while(rs.next())
							{
								double r1=rs.getDouble(1);
								
								String r11=String.valueOf(r1);
								
								txt_old_rate.setText(r11);
								
							}
						}
					}
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Please Enter Valid Fat and SNF");
				}
			}
		});
		txt_snf.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_snf.setColumns(10);
		txt_snf.setBounds(201, 219, 142, 28);
		panel.add(txt_snf);
		
		txt_old_rate = new JTextField();
		txt_old_rate.setEditable(false);
		txt_old_rate.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_old_rate.setColumns(10);
		txt_old_rate.setBounds(201, 289, 142, 28);
		panel.add(txt_old_rate);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					double d1=Double.parseDouble(txt_fat.getText());
					double d2=Double.parseDouble(txt_snf.getText());
					double d3=Double.parseDouble(txt_new_rate.getText());
					
					String string=comboBox.getSelectedItem().toString();
								
					Statement st=con.createStatement();
					
					if(string.equalsIgnoreCase("COW"))
					{
						String sql="update cow_rates set rate="+d3+"where fat="+d1+"and snf="+d2;
						int data=st.executeUpdate(sql);
						
						int display=JOptionPane.showConfirmDialog(null,"Are you sure...!","Update a Rate",JOptionPane.YES_NO_OPTION);
						
						
						if(data>0)
						{
							if(display==JOptionPane.YES_OPTION)
							{
								JOptionPane.showMessageDialog(null,"Rate Updated successfully");
							}
							if(display==JOptionPane.NO_OPTION)
							{
								System.exit(0);
							}
						}
						else 
						{
							JOptionPane.showMessageDialog(null,"Sorry Please try again");
						}
					}
					if(string.equalsIgnoreCase("BUFFALO"))
					{
						String sql="update buffalo_rates set rate="+d3+"where fat="+d1+"and snf="+d2;

						int data=st.executeUpdate(sql);
						
						int display=JOptionPane.showConfirmDialog(null,"Are you sure...!","Update a Rate",JOptionPane.YES_NO_OPTION);
						
						
						if(data>0)
						{
							if(display==JOptionPane.YES_OPTION)
							{
								JOptionPane.showMessageDialog(null,"Rate Updated successfully");
							}
							if(display==JOptionPane.NO_OPTION)
							{
								System.exit(0);
							}
						}
						else 
						{
							JOptionPane.showMessageDialog(null,"Sorry Please try again");
						}
					}
					
					
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,e2);
				}
			}
		});
		btn_update.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_update.setBounds(47, 399, 134, 37);
		panel.add(btn_update);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Enter New Rate");
		lblNewLabel_1_1_2_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1_1_2_1.setBounds(24, 354, 142, 28);
		panel.add(lblNewLabel_1_1_2_1);
		
		txt_new_rate = new JTextField();
		txt_new_rate.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_new_rate.setColumns(10);
		txt_new_rate.setBounds(201, 354, 142, 28);
		panel.add(txt_new_rate);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txt_fat.setText("");
				txt_snf.setText("");
				txt_old_rate.setText("");
				txt_new_rate.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		clear.setBounds(265, 399, 123, 37);
		panel.add(clear);
	}
}
