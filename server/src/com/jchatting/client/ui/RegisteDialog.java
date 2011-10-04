package com.jchatting.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jchatting.db.DbHanddle;
import com.jchatting.db.bean.User;
import com.jchatting.db.dao.impl.UserImpl;
import com.jchatting.util.StringUtil;

public class RegisteDialog extends JDialog implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	private LoginFrame loginFrame;
	
	private JTextField userNameTF;
	private JPasswordField passwordTF;
	private JPasswordField rePasswordTF;
	
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisteDialog dialog = new RegisteDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegisteDialog(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 332, 173);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName:");
		lblNewLabel.setBounds(10, 10, 79, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(10, 40, 79, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblRepassword = new JLabel("RePassword:");
		lblRepassword.setBounds(10, 70, 79, 15);
		contentPanel.add(lblRepassword);
		
		userNameTF = new JTextField();
		userNameTF.setBounds(99, 7, 217, 21);
		userNameTF.setToolTipText("Username length must between 1 to 20, not contains '/' or '&' and so on!");
		contentPanel.add(userNameTF);
		userNameTF.setColumns(10);
		
		passwordTF = new JPasswordField();
		passwordTF.setBounds(99, 37, 217, 21);
		passwordTF.setToolTipText("Password length must between 1 to 20, not contains '/' or '&' and so on!");
		contentPanel.add(passwordTF);
		passwordTF.setColumns(10);
		
		rePasswordTF = new JPasswordField();
		rePasswordTF.setBounds(99, 67, 217, 21);
		contentPanel.add(rePasswordTF);
		rePasswordTF.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	private void validateInput() {
		if (StringUtil.isEmpty(userNameTF.getText()) || userNameTF.getText().length() > 20) {
			JOptionPane.showMessageDialog(this, "UserName can not be null or longer than 20 chars!");
		}
		else {
			if (StringUtil.isEmpty(String.valueOf(passwordTF.getPassword())) || String.valueOf(passwordTF.getPassword()).length() > 20) {
				JOptionPane.showMessageDialog(this, "Password can not be null or longer than 20 chars!");
			}
			else {
				if (StringUtil.isEmpty(String.valueOf(rePasswordTF.getPassword()))) {
					JOptionPane.showMessageDialog(this, "RePassword can not be null!");
				}
				else {
					if (String.valueOf(passwordTF.getPassword()).equals(String.valueOf(rePasswordTF.getPassword()))) {
						if (String.valueOf(passwordTF.getPassword()).contains("/") || String.valueOf(passwordTF.getPassword()).contains("&")) {
							JOptionPane.showMessageDialog(this, "Password can not contains '/' or '&'!");
						}
					}
				}
			}
		}
		
	}
	
	private void closeRegisteDialog() {
		this.setVisible(false);
		this.dispose();
		if (this.loginFrame != null) {
			this.loginFrame.setVisible(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == okButton) {
			validateInput();
			User user = new User();
			user.setAccount(userNameTF.getText());
			user.setPassword(String.valueOf(passwordTF.getPassword()));
			user.setOnline(false);
			user.setValidated(true);
			user.setRegtime(new Timestamp(System.currentTimeMillis()));
			user.setForbidden(false);
			int result = new DbHanddle().registeUser(user);
			if (result == 1) {
				closeRegisteDialog();
				JOptionPane.showMessageDialog(this, "Regesit success");
			}
			else if (result == UserImpl.USER_EXIST) {
				JOptionPane.showMessageDialog(this, "User name is exist, change another one!");
				this.setVisible(true);
			}
			
		}
		else if (e.getSource() == cancelButton) {
			closeRegisteDialog();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		closeRegisteDialog();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
