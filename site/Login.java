package com.tut2.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Login extends JFrame {
	private JTextField txtUser;
	private JPasswordField txtPass;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public Login() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 578, 84);
		panel.setBackground(SystemColor.textHighlight);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Login");
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 26));
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(71, 94, 110, 29);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(71, 144, 110, 29);
		getContentPane().add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setBounds(224, 94, 206, 34);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(224, 146, 206, 34);
		getContentPane().add(txtPass);
		
		JButton jButton3 = new JButton("New User");
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					NewUser frame = new NewUser();
					frame.setVisible(true);
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
		});
		jButton3.setForeground(SystemColor.text);
		jButton3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton3.setBounds(71, 291, 141, 43);
		getContentPane().add(jButton3);
		jButton3.setBackground(SystemColor.textHighlight);
		
		JButton jButton1 = new JButton("Login");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
//					Class.forName("com.mysql.jdbc.Driver");
//					con=DriverManager.getConnection("jdbc:mysql://localhost/VaccineManagement","root","");
					if(txtUser.getText().isEmpty() ||txtPass.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Username or Password blank");
					}
					else
					{
						String username=txtUser.getText();
						String password=txtPass.getText();
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/vaccinemanagement","root","");
						pst=con.prepareStatement("Select * from user where username= ? and password= ?");
						pst.setString(1, username);
						pst.setString(2, password);
						rs=pst.executeQuery();
						
						if(rs.next())
						{
							String utype = rs.getString(4);
							int uid = rs.getInt(1);
							System.out.println(utype);
							if(utype.equals("Admin")) {
								System.out.println("enter here");
								MainAdmin m=new MainAdmin();
								//this.hide();
								m.setVisible(true);
							}else {
								Main m=new Main();
								//this.hide();
								m.setVisible(true);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Username or Password do not match");
							txtUser.setText("");
							txtPass.setText("");
							txtUser.requestFocus();
						}
					}
				}catch(ClassNotFoundException ex) {
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,ex);
				}
				catch(SQLException ex) {
					Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE,null,ex);
				}
				
			}
		});
		jButton1.setBackground(SystemColor.textHighlight);
		jButton1.setForeground(SystemColor.text);
		jButton1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jButton1.setBounds(250, 292, 110, 43);
		getContentPane().add(jButton1);
		jButton1.setBackground(Color.RED);
		
		JButton jButton2 = new JButton("Cancel");
		jButton2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		jButton2.setBounds(395, 292, 117, 43);
		getContentPane().add(jButton2);
		
		JLabel lblNewLabel_3 = new JLabel("User Type");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(71, 204, 117, 29);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox txtUtype = new JComboBox();
		txtUtype.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUtype.setBounds(224, 212, 217, 29);
		txtUtype.addItem("User");
		txtUtype.addItem("Admin");
		getContentPane().add(txtUtype);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame1 = new Login();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
