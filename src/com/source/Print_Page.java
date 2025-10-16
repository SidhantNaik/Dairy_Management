package com.source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Print_Page extends JFrame 
{

	private JPanel contentPane;
	
	String id,name,type,timing,litre1,fat1,snf1,rate1,amount1;
	
	
	public void page_printing()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					Print_Page frame = new Print_Page(id,name,type,timing,litre1,fat1,snf1,rate1,amount1);
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public Print_Page(String id,String name,String type,String timing,String litre1,String fat1,String snf1,String rate1,String amount1) 
	{
		this.id=id;
		this.name=name;
		this.type=type;
		this.timing=timing;
		this.litre1=litre1;
		this.fat1=fat1;
		this.snf1=snf1;
		this.rate1=rate1;
		this.amount1=amount1;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 317, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 309, 297);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel txt_name = new JLabel("");
		txt_name.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_name.setBounds(115, 46, 186, 18);
		panel.add(txt_name);
		
		JLabel txt_timing = new JLabel("\r\n");
		txt_timing.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_timing.setBounds(115, 96, 128, 18);
		panel.add(txt_timing);
		
		JLabel txt_litre = new JLabel("");
		txt_litre.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_litre.setBounds(115, 121, 186, 18);
		panel.add(txt_litre);
		
		JLabel txt_fat = new JLabel("");
		txt_fat.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_fat.setBounds(115, 143, 154, 18);
		panel.add(txt_fat);
		
		JLabel txt_snf = new JLabel("");
		txt_snf.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_snf.setBounds(115, 171, 192, 18);
		panel.add(txt_snf);
		
		JLabel txt_rate = new JLabel("");
		txt_rate.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_rate.setBounds(115, 199, 186, 18);
		panel.add(txt_rate);
		
		JLabel txt_amount = new JLabel("");
		txt_amount.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_amount.setBounds(114, 227, 166, 18);
		panel.add(txt_amount);
		
		JButton print = new JButton("Print");
		print.setFont(new Font("Times New Roman", Font.BOLD, 14));
		print.setBounds(66, 265, 104, 25);
		panel.add(print);
		
		JLabel txt_id = new JLabel("<dynamic>");
		txt_id.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_id.setBounds(115, 18, 117, 18);
		panel.add(txt_id);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 20, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name\r\n");
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblName.setBounds(10, 49, 45, 13);
		panel.add(lblName);
		
		JLabel lblLitre = new JLabel("Timing");
		lblLitre.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblLitre.setBounds(10, 99, 45, 13);
		panel.add(lblLitre);
		
		JLabel lblFat = new JLabel("Litre");
		lblFat.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblFat.setBounds(10, 124, 45, 13);
		panel.add(lblFat);
		
		JLabel lblSnf = new JLabel("Fat");
		lblSnf.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblSnf.setBounds(10, 146, 45, 13);
		panel.add(lblSnf);
		
		JLabel lblSnf_2 = new JLabel("SNF");
		lblSnf_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblSnf_2.setBounds(10, 174, 45, 13);
		panel.add(lblSnf_2);
		
		JLabel lblSnf_2_1 = new JLabel("Rate");
		lblSnf_2_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblSnf_2_1.setBounds(10, 202, 45, 13);
		panel.add(lblSnf_2_1);
		
		JLabel lblSnf_2_1_1 = new JLabel("Amount");
		lblSnf_2_1_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblSnf_2_1_1.setBounds(10, 230, 66, 13);
		panel.add(lblSnf_2_1_1);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblType.setBounds(10, 73, 45, 13);
		panel.add(lblType);
		
		JLabel txt_type = new JLabel("<dynamic>");
		txt_type.setFont(new Font("Arial Black", Font.BOLD, 12));
		txt_type.setBounds(115, 74, 186, 18);
		panel.add(txt_type);
		

		txt_id.setText(id);
		txt_name.setText(name);
		txt_type.setText(type);
		txt_timing.setText(timing);
		txt_litre.setText(litre1);
		txt_fat.setText(fat1);
		txt_snf.setText(snf1);
		txt_rate.setText(rate1);
		txt_amount.setText(amount1);
		
				
	}
}
