package com.tut2.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	ResultSet rs;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jButton1 = new JButton("Covid Details");
		jButton1.setBackground(Color.ORANGE);
		jButton1.setFont(new Font("Tahoma", Font.BOLD, 18));
		jButton1.setBounds(43, 76, 165, 58);
		contentPane.add(jButton1);
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					CovidDetails frame1 = new CovidDetails();
					frame1.setVisible(true);
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
				
			
		});
		
		JButton jButton3 = new JButton("Registration");
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Registration frame2 = new Registration();
					frame2.setVisible(true);
				} catch (Exception err) {
					err.printStackTrace();
				}
				
			}
		});
		jButton3.setBackground(Color.GREEN);
		jButton3.setFont(new Font("Tahoma", Font.BOLD, 18));
		jButton3.setBounds(255, 79, 149, 53);
		contentPane.add(jButton3);
		
//		try {
//			if(rs.next())
//			{
//				CovidDetails n=new CovidDetails();
//				this.hide();
//				n.setVisible(true);
//			}
//		}//catch(ClassNotFoundException ex) {
//			//Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,ex);
//		//}
//		catch(SQLException ex) {
//			Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE,null,ex);
//		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
