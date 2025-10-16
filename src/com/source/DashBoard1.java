package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class DashBoard1 extends JFrame
{
	private JPanel contentPane;

	public static void dashboard_main()//main(String[] args)//// // //
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					DashBoard1 frame = new DashBoard1();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public DashBoard1() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1173, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.RED);
		menuBar.setBackground(Color.CYAN);
		menuBar.setBounds(70, 0, 717, 50);
		contentPane.add(menuBar);
		
		JMenu member = new JMenu("Member");
		member.setFont(new Font("Times New Roman", Font.BOLD, 22));
		menuBar.add(member);
		
		JMenuItem new1 = new JMenuItem("Add a Member\r\n");
		new1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				members_add.members_add_setup();
			}
		});
		new1.setFont(new Font("Arial Black", Font.BOLD, 15));
		member.add(new1);
		JMenuItem update1 = new JMenuItem("Update A Member");
		update1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Members_edit.members_edit();
			}
		});
		update1.setFont(new Font("Arial Black", Font.BOLD, 15));
		member.add(update1);
		
		JMenuItem remove1 = new JMenuItem("Remove Any Member");
		remove1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Remove_a_member.remove_member();
			}
		});
		remove1.setFont(new Font("Arial Black", Font.BOLD, 15));
		member.add(remove1);
		
		JMenuItem display1 = new JMenuItem("Display All Members\r\n");
		display1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Members_Display.members_display();
			}
		});
		display1.setFont(new Font("Arial Black", Font.BOLD, 15));
		member.add(display1);
		
		JMenu milkcollection = new JMenu("Milk Collection");
		milkcollection.setFont(new Font("Times New Roman", Font.BOLD, 22));
		menuBar.add(milkcollection);
		
		JMenuItem dailycollection = new JMenuItem("Daily Collection\r\n");
		dailycollection.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Journal_set_up.Journal_set_up_method();
			}
		});
		dailycollection.setFont(new Font("Arial Black", Font.BOLD, 15));
		milkcollection.add(dailycollection);
		
		JMenuItem collectedmilk = new JMenuItem("Collected Milk");
		collectedmilk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Form_date_to_to_date_all_data_display.all_data_display();
			}
		});
		collectedmilk.setFont(new Font("Arial Black", Font.BOLD, 15));
		milkcollection.add(collectedmilk);
		
		JMenu ratemanagement = new JMenu("Rate management");
		ratemanagement.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(ratemanagement);
		
		JMenuItem displaychart = new JMenuItem("Display Chart");
		displaychart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Cow_Buffalo_Display.cow_buffalo_combo();
			}
		});
		displaychart.setFont(new Font("Arial Black", Font.BOLD, 15));
		ratemanagement.add(displaychart);
		
		JMenuItem addnewrate = new JMenuItem("Add New Fat,SNF,Rate");
		addnewrate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Add_Rates_DashBoard.add_rates_dash();
			}
		});
		addnewrate.setFont(new Font("Arial Black", Font.BOLD, 15));
		ratemanagement.add(addnewrate);
		
		JMenuItem updaterate = new JMenuItem("Update Rates");
		updaterate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Update_Rates.update_rates();
			}
		});
		updaterate.setFont(new Font("Arial Black", Font.BOLD, 15));
		ratemanagement.add(updaterate);
		
		JMenu billingdepartment = new JMenu("Billing Department");
		billingdepartment.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(billingdepartment);
		
		JMenuItem milk_reports = new JMenuItem("Milk Reports\r\n");
		milk_reports.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Bill_Form_date_to_date.bill_form_date_to_date();
			}
		});
		milk_reports.setFont(new Font("Arial Black", Font.BOLD, 15));
		billingdepartment.add(milk_reports);
		
		JLabel bg = new JLabel("");
		bg.setIcon(new ImageIcon(DashBoard1.class.getResource("/com/source/istockphoto-1297005217-170667a.jpg")));
		bg.setBounds(10, 0, 1149, 624);
		contentPane.add(bg);
	}
}
