package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;

import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class By_One_Separte_Member extends JFrame 
{

	private JPanel contentPane;
	public String form_date_data,to_date_data;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txt_member_code;
	private JTextField txt_name;
	private JTextField txt_type;
	public boolean flag=false;
	private JLabel lblTotalMilkIn;
	private JTextField txt_total_litre;
	private JLabel lblTotalAmount;
	private JTextField txt_amount;
	
	public void by_one_member()//main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					By_One_Separte_Member frame = new By_One_Separte_Member(form_date_data,to_date_data);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public By_One_Separte_Member(String form_date_data,String to_date_data)
	{
		this.form_date_data=form_date_data;
		this.to_date_data=to_date_data;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1033, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 1017, 713);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial Black", Font.BOLD, 10));
		scrollPane.setBounds(10, 91, 1001, 574);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial Black", Font.BOLD, 10));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collection_Date", "Collection_Timing", "Litre", "Fat", "SNF", "Rate", "Amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(107);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		
		JLabel lblNewLabel = new JLabel("Enter Member Code:-");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 17, 187, 25);
		panel.add(lblNewLabel);
		
		txt_member_code = new JTextField();
		txt_member_code.setFont(new Font("Arial Black", Font.BOLD, 15));
		txt_member_code.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try 
					{
						Connection con=Database.getpublicConnection();

							
							int id=Integer.parseInt(txt_member_code.getText());
							
							Statement st=con.createStatement();
							
							ResultSet rs=st.executeQuery("select * from members where member_code="+id);
							
							while(rs.next())
							{
								String fname=rs.getString(2);
								String mname=rs.getString(3);
								String lname=rs.getString(4);
								
								txt_name.setText(fname+" "+mname+" "+lname);
								
								String type1=rs.getString(5);
								txt_type.setText(type1);
								
		
							}
						
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null,"Please enter valid member code");
					}
				}
				
			}
		});
		txt_member_code.setBounds(207, 15, 92, 28);
		panel.add(txt_member_code);
		txt_member_code.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.setEditable(false);
		txt_name.setFont(new Font("Arial Black", Font.BOLD, 15));
		txt_name.setColumns(10);
		txt_name.setBounds(328, 14, 432, 28);
		panel.add(txt_name);
		
		txt_type = new JTextField();
		txt_type.setEditable(false);
		txt_type.setFont(new Font("Arial Black", Font.BOLD, 15));
		txt_type.setColumns(10);
		txt_type.setBounds(836, 17, 92, 28);
		panel.add(txt_type);
		
		JButton btn_genrate = new JButton("Genenrate Bill");
		btn_genrate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				
						try
						{
								Connection con1=Database.getpublicConnection();
								
								int id=Integer.parseInt(txt_member_code.getText());
								
								String str="select collection_date,collection_timing,litre,fat,snf,rate,amount FROM milk_collection where (collection_date BETWEEN '"+form_date_data+"' AND '"+to_date_data+"') and collection_timing='Morning' or collection_timing='Evening' and member_code="+id;
								
								Statement statement=con1.createStatement();
								
								ResultSet rs1=statement.executeQuery(str);
								
								ResultSetMetaData rsmd=rs1.getMetaData();
								
								DefaultTableModel model=(DefaultTableModel)table.getModel();
								
								int column=rsmd.getColumnCount();
								
								String [] column_name=new String[column];
								
								for (int i=0;i<column;i++) 
								{
									column_name[i]=rsmd.getColumnName(i+1);
								}
								
								model.setColumnIdentifiers(column_name);
								
								
								while(rs1.next())
								{
									String date_data=rs1.getString(1);
									
									SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
									SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
									
									Date date_new=inputDateFormat.parse(date_data);
									
									String format=outputDateFormat.format(date_new);
									
									String timing_data=rs1.getString(2);
									
									double litre_new=rs1.getDouble(3);
									double fat_new=rs1.getDouble(4);
									double snf_new=rs1.getDouble(5);
									double rate_new=rs1.getDouble(6);
									Double amount_new=rs1.getDouble(7);
									
									String litreString=String.valueOf(litre_new);
									String fatString=String.valueOf(fat_new);
									String snfString=String.valueOf(snf_new);
									String rateString=String.valueOf(rate_new);
									String amountString=String.valueOf(amount_new);
									
									String [] row2= {format,timing_data,litreString,fatString,snfString,rateString,amountString};
									
									model.addRow(row2);
								}
							}
							catch (Exception e2) 
							{
								JOptionPane.showMessageDialog(null,e2);
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
								
								txt_total_litre.setText(calculate);
								
							}
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null, e2);
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
								
								txt_amount.setText(calculate);
								
							}
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null, e2);
						}
					
			}
		});
		btn_genrate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_genrate.setBounds(284, 53, 174, 25);
		panel.add(btn_genrate);
		
		lblTotalMilkIn = new JLabel("Total Litre");
		lblTotalMilkIn.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblTotalMilkIn.setBounds(95, 675, 102, 25);
		panel.add(lblTotalMilkIn);
		
		txt_total_litre = new JTextField();
		txt_total_litre.setFont(new Font("Arial Black", Font.BOLD, 15));
		txt_total_litre.setColumns(10);
		txt_total_litre.setBounds(207, 675, 92, 28);
		panel.add(txt_total_litre);
		
		lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblTotalAmount.setBounds(673, 675, 153, 25);
		panel.add(lblTotalAmount);
		
		txt_amount = new JTextField();
		txt_amount.setFont(new Font("Arial Black", Font.BOLD, 15));
		txt_amount.setColumns(10);
		txt_amount.setBounds(836, 675, 92, 28);
		panel.add(txt_amount);
		
		
	}
}
