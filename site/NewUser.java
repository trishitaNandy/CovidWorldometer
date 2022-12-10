package com.tut2.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con;
	PreparedStatement pst;
	/**
	 * Create the frame.
	 */
	public NewUser() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(33, 43, 125, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(32, 96, 125, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(33, 148, 138, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("User Type");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(33, 203, 125, 32);
		contentPane.add(lblNewLabel_3);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.setBounds(178, 43, 258, 37);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPass.setBounds(178, 94, 258, 38);
		contentPane.add(txtPass);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtConfirm.setBounds(181, 145, 255, 38);
		contentPane.add(txtConfirm);
		
		final JComboBox txtUtype = new JComboBox();
		txtUtype.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtUtype.setToolTipText("User");
		txtUtype.setMaximumRowCount(3);
		txtUtype.setBounds(181, 203, 255, 37);
		txtUtype.addItem("User");
		txtUtype.addItem("Admin");
		contentPane.add(txtUtype);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUser.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Please type user name");
				}
				else if(txtPass.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null,"Please type password");
				}
				else if(txtPass.getText().equals(txtConfirm.getText())==false)
				{
					JOptionPane.showMessageDialog(null,"Password not matched");
				}
				else
				{
					try {
						String username=txtUser.getText();
						String confirmpass=txtConfirm.getText();
						String usertype= txtUtype.getSelectedItem().toString();
						
						
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/vaccinemanagement","root","");
						//con=DriverManager.getConnection("jdbc:mysql://localhost/VaccineManagement","root","Nandy@123");
						pst=con.prepareStatement("insert into user(username, password, utype)values(?, ?, ?)");
						
						pst.setString(1, username);
						pst.setString(2, confirmpass);
						pst.setString(3, usertype);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null,"User Created...");
						txtUser.setText("");
						txtConfirm.setText("");
						txtUtype.setSelectedIndex(-1);
						txtUser.requestFocus();
						
					}catch (ClassNotFoundException ex) {
						// TODO: handle exception
						java.util.logging.Logger.getLogger(NewUser.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
					}
					catch(SQLException ex) {
						java.util.logging.Logger.getLogger(NewUser.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
					}
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(93, 283, 125, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(300, 283, 112, 32);
		contentPane.add(btnNewButton_1);
	}
}
