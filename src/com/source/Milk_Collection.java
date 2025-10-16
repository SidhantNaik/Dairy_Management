package com.source;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Database.Database;

public class Milk_Collection extends JFrame 
{

	private JPanel contentPane;
	private JTextField code_txt;
	private JTextField name_txt;
	private JTextField type_txt;
	private JTextField litre_txt;
	private JTextField fat_txt;
	private JTextField snf_txt;
	private JTextField rate_txt;
	private JTextField amount_txt;
	private JTable table_data;
	public String selectItem;
	public JLabel timing_display_milk_collection_new = new JLabel();
	
	
	public void Daily_Milk_Collection()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					
					Milk_Collection frame = new Milk_Collection(selectItem);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Milk_Collection(String selectItem) 
	{
		this.selectItem=selectItem;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1159, 736);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1145, 699);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel dailycollection = new JLabel("Daily Milk Collection");
		dailycollection.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dailycollection.setBounds(388, 14, 293, 38);
		panel.add(dailycollection);
		
		JLabel date = new JLabel("Date:- ");
		date.setFont(new Font("Times New Roman", Font.BOLD, 25));
		date.setBounds(10, 17, 80, 25);
		panel.add(date);
		
		JLabel date_display_Milk_Collection = new JLabel("");
		date_display_Milk_Collection.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
		Journal_set_up j1=new Journal_set_up();
		date_display_Milk_Collection.setText(j1.datedisplay.getText());
		
		date_display_Milk_Collection.setBounds(100, 14, 165, 31);
		panel.add(date_display_Milk_Collection);
		
		JLabel timing = new JLabel("Timing:- ");
		timing.setFont(new Font("Times New Roman", Font.BOLD, 25));
		timing.setBounds(766, 23, 120, 25);
		panel.add(timing);

		JLabel member_code = new JLabel("Member Code");
		member_code.setFont(new Font("Arial Black", Font.BOLD, 16));
		member_code.setBounds(23, 88, 130, 38);
		panel.add(member_code);
		
		code_txt = new JTextField();
		code_txt.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE  || e==null || code_txt.getText()==null)
					{
						name_txt.setText("");
						type_txt.setText("");
					}
					else
					{
						
						int id=Integer.parseInt(code_txt.getText());
						
						Statement st=con.createStatement();
						
						ResultSet rs=st.executeQuery("select * from members where member_code="+id);
						
						while(rs.next())
						{
							String fname=rs.getString(2);
							String mname=rs.getString(3);
							String lname=rs.getString(4);
							
							name_txt.setText(fname+" "+mname+" "+lname);
							
							String type1=rs.getString(5);
							type_txt.setText(type1);
							
						}
					}
					
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Please enter valid member code");
				}
			}
		
			
		});
		code_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		code_txt.setBounds(40, 152, 92, 38);
		panel.add(code_txt);
		code_txt.setColumns(10);
		
		JLabel member_name = new JLabel("Member Name");
		member_name.setFont(new Font("Arial Black", Font.BOLD, 16));
		member_name.setBounds(413, 88, 165, 38);
		panel.add(member_name);
		
		name_txt = new JTextField();
		name_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		name_txt.setEditable(false);
		name_txt.setColumns(10);
		name_txt.setBounds(250, 152, 549, 38);
		panel.add(name_txt);
		
		JLabel type = new JLabel("Type");
		type.setFont(new Font("Arial Black", Font.BOLD, 16));
		type.setBounds(931, 88, 85, 38);
		panel.add(type);
		
		type_txt = new JTextField();
		type_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		type_txt.setEditable(false);
		type_txt.setColumns(10);
		type_txt.setBounds(909, 152, 112, 38);
		panel.add(type_txt);
		
		JLabel litre = new JLabel("Litre");
		litre.setFont(new Font("Arial Black", Font.BOLD, 16));
		litre.setBounds(76, 220, 67, 38);
		panel.add(litre);
		
		litre_txt = new JTextField();
		litre_txt.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE || litre_txt.getText()==null)
				{
					litre_txt.setText("");
					fat_txt.setText("");
					snf_txt.setText("");
					rate_txt.setText("");
					amount_txt.setText("");
				}
			}
		});
		litre_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		litre_txt.setColumns(10);
		litre_txt.setBounds(61, 268, 92, 38);
		panel.add(litre_txt);
		
		JLabel fat = new JLabel("Fat");
		fat.setFont(new Font("Arial Black", Font.BOLD, 16));
		fat.setBounds(294, 220, 61, 38);
		panel.add(fat);
		
		JLabel snf = new JLabel("SNF");
		snf.setFont(new Font("Arial Black", Font.BOLD, 16));
		snf.setBounds(511, 220, 67, 38);
		panel.add(snf);
		
		JLabel rate = new JLabel("Rate");
		rate.setFont(new Font("Arial Black", Font.BOLD, 16));
		rate.setBounds(718, 220, 67, 38);
		panel.add(rate);
		
		JLabel amount = new JLabel("Amount\r\n");
		amount.setFont(new Font("Arial Black", Font.BOLD, 16));
		amount.setBounds(929, 220, 97, 38);
		panel.add(amount);
		
		fat_txt = new JTextField();
		fat_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		fat_txt.setColumns(10);
		fat_txt.setBounds(274, 268, 92, 38);
		panel.add(fat_txt);
		
		snf_txt = new JTextField();
		snf_txt.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				try 
				{
						double fat1=Double.parseDouble(fat_txt.getText());
						double snf1=Double.parseDouble(snf_txt.getText());
					
						String option=type_txt.getText();
						
						if(option.equalsIgnoreCase("cow"))
						{
							Connection con=Database.getpublicConnection();
							
							try 
							{
								String str="select * from cow_rates where fat="+fat1+"and snf="+snf1;
								
								Statement st=con.createStatement();
								
								ResultSet rs=st.executeQuery(str);
								
								while(rs.next())
								{
									double rate1=rs.getDouble(3);
									
									String str1=String.valueOf(rate1);
									
									double litre1=Double.parseDouble(litre_txt.getText());
				
									rate_txt.setText(str1);							

									double cal=rate1*litre1;
									
									double cal_new=Math.round(cal*100)/100.0;
									
									String calculation=String.valueOf(cal_new);
									
									amount_txt.setText(calculation);
								}
							} 
							catch (Exception e2) 
							{
								JOptionPane.showMessageDialog(null,"Sorry no rate found");
							}
						}
						if(option.equalsIgnoreCase("buffalo"))
						{
							try 
							{
								Connection con1=Database.getpublicConnection();
								
								String str="select * from buffalo_rates where fat="+fat1+"and snf="+snf1;
								
								Statement st=con1.createStatement();
								
								ResultSet rs=st.executeQuery(str);
								
								while(rs.next())
								{
									double rate1=rs.getDouble(3);
									
									String str1=String.valueOf(rate1);
									
									double litre1=Double.parseDouble(litre_txt.getText());
				
									rate_txt.setText(str1);							

									double cal=rate1*litre1;
									
									double cal_new=Math.round(cal*100)/100.0;
									
									String calculation=String.valueOf(cal_new);
									
									amount_txt.setText(calculation);
								}
							} 
							catch (Exception e2) 
							{
								JOptionPane.showMessageDialog(null,"sorry no rate found");
							}
						}
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null,"Invalid");
				}
			}
		});
		snf_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		snf_txt.setColumns(10);
		snf_txt.setBounds(496, 268, 92, 38);
		panel.add(snf_txt);
		
		rate_txt = new JTextField();
		rate_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		rate_txt.setEditable(false);
		rate_txt.setColumns(10);
		rate_txt.setBounds(707, 268, 92, 38);
		panel.add(rate_txt);
		
		amount_txt = new JTextField();
		amount_txt.setFont(new Font("Arial Black", Font.BOLD, 16));
		amount_txt.setEditable(false);
		amount_txt.setColumns(10);
		amount_txt.setBounds(929, 268, 92, 38);
		panel.add(amount_txt);
		
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Connection con=Database.getpublicConnection();
					
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					
					String str=date_display_Milk_Collection.getText();
					
					Date d=sdf.parse(str);
					
					SimpleDateFormat format_date=new SimpleDateFormat("yyyy-MM-dd");
					
					String date_display=format_date.format(d).toString();
					
					java.sql.Date sqlDate=java.sql.Date.valueOf(date_display);
					
					String time=timing_display_milk_collection_new.getText();
					
					int code=Integer.parseInt(code_txt.getText());
					
					String name=name_txt.getText();
					
					String opt=type_txt.getText();
					
					double litre=Double.parseDouble(litre_txt.getText());
					
					double fat1=Double.parseDouble(fat_txt.getText());
					
					double snf=Double.parseDouble(snf_txt.getText());
					
					double rate=Double.parseDouble(rate_txt.getText());
					
					double amount=Double.parseDouble(amount_txt.getText());
					
					String sql="insert into milk_collection values(?,?,?,?,?,?,?,?,?,?)";
					
					PreparedStatement ps=con.prepareStatement(sql);
					
					ps.setDate(1,sqlDate);
					ps.setString(2,time);
					ps.setInt(3, code);
					ps.setString(4,name);
					ps.setString(5,opt);
					ps.setDouble(6,litre);
					ps.setDouble(7,fat1);
					ps.setDouble(8,snf);
					ps.setDouble(9,rate);
					ps.setDouble(10, amount);
					
					int row=ps.executeUpdate();
					if(row>0)
					{
						
						JOptionPane.showMessageDialog(null,"Record Added");
					
						try 
						{
							Connection con_new=Database.getpublicConnection();
							
							Statement st_new=con_new.createStatement();
							
							String sql_2="select * from milk_collection where collection_date='"+sqlDate+"' and collection_timing='"+time+"'";
							
							ResultSet rs=st_new.executeQuery(sql_2);
							
							ResultSetMetaData rsmd=rs.getMetaData();
							
							DefaultTableModel model=(DefaultTableModel)table_data.getModel();
							
							int column=rsmd.getColumnCount();
							
							String [] column_name=new String[column];
							
							for (int i=0;i<column;i++) 
							{
								column_name[i]=rsmd.getColumnName(i+1);
							}
							
							model.setColumnIdentifiers(column_name);
							

							
							while(rs.next())
							{
								String date_data=rs.getString(1);
								
								SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
								SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
								
								Date date_new=inputDateFormat.parse(date_data);
								
								String format=outputDateFormat.format(date_new);
								
								String time_data=rs.getString(2);
								
								int code_data=rs.getInt(3);
								
								String code_display=String.valueOf(code_data);
								
								String name_display=rs.getString(4);
								
								String type_new=rs.getString(5);
								
								double litre_new=rs.getDouble(6);
								double fat_new=rs.getDouble(7);
								double snf_new=rs.getDouble(8);
								double rate_new=rs.getDouble(9);
								Double amount_new=rs.getDouble(10);
								
								String litreString=String.valueOf(litre_new);
								String fatString=String.valueOf(fat_new);
								String snfString=String.valueOf(snf_new);
								String rateString=String.valueOf(rate_new);
								String amountString=String.valueOf(amount_new);
								
								String [] row2= {format,time_data,code_display,name_display,type_new,litreString,fatString,snfString,rateString,amountString};
								
								model.addRow(row2);
							}
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null,"Please Enter valid details");
						}
					
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Sorry Please try again later");
					}
				}
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		
		save.setFont(new Font("Times New Roman", Font.BOLD, 20));
		save.setBounds(61, 335, 112, 31);
		panel.add(save);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 376, 1123, 311);
		panel.add(scrollPane_1);
		
		table_data = new JTable();
		scrollPane_1.setViewportView(table_data);
		table_data.setFont(new Font("Arial Black", Font.BOLD, 12));
		table_data.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Timing", "Member_code", "Member_name", "Type", "Litre", "Fat", " SNF", "Rate", "Amount"
			}
		));
		table_data.getColumnModel().getColumn(0).setPreferredWidth(79);
		table_data.getColumnModel().getColumn(1).setPreferredWidth(82);
		table_data.getColumnModel().getColumn(2).setPreferredWidth(187);
		table_data.getColumnModel().getColumn(3).setPreferredWidth(57);
		table_data.getColumnModel().getColumn(4).setPreferredWidth(51);
		table_data.getColumnModel().getColumn(6).setPreferredWidth(55);
		
		timing_display_milk_collection_new.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
		timing_display_milk_collection_new.setText(selectItem);
		
		timing_display_milk_collection_new.setBounds(878, 23, 165, 25);
		panel.add(timing_display_milk_collection_new);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				code_txt.setText("");
				name_txt.setText("");
				type_txt.setText("");
				litre_txt.setText("");
				fat_txt.setText("");
				snf_txt.setText("");
				rate_txt.setText("");
				amount_txt.setText("");
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		clear.setBounds(448, 335, 112, 31);
		panel.add(clear);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				Journal_set_up.Journal_set_up_method();
			}
		});
		
		JButton receipt_btn = new JButton("Receipt");
		receipt_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String id1=code_txt.getText();
				String name1=name_txt.getText();
				String type1=type_txt.getText();
				String timing1=timing_display_milk_collection_new.getText();
				String litre1=litre_txt.getText();
				String fat1=fat_txt.getText();
				String snf1=snf_txt.getText();
				String rate1=rate_txt.getText();
				String amount1=amount_txt.getText();
				
				Print_Page page=new Print_Page(id1, name1, type1,timing1, litre1, fat1, snf1, rate1, amount1);
				page.page_printing();
			}
		});
		receipt_btn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		receipt_btn.setBounds(254, 335, 112, 31);
		panel.add(receipt_btn);
		
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBack.setBounds(623, 335, 112, 31);
		panel.add(btnBack);
	}
}
