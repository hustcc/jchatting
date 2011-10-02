package com.jchatting.client.ui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.jchatting.client.LoginResult;
import com.jchatting.db.DbHanddle;

public class LoginFrame extends JFrame implements ActionListener, MouseListener, Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField accountTextField;
	private JPasswordField passwordField;
	private JLabel regLabel;
	
	private JButton loginButton;
	private JButton cancelButton;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("登录Jchatting");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("账号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(10, 21, 54, 24);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(10, 55, 54, 24);
		getContentPane().add(label_1);
		
		accountTextField = new JTextField();
		accountTextField.setBounds(74, 21, 213, 24);
		getContentPane().add(accountTextField);
		accountTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(74, 55, 213, 24);
		getContentPane().add(passwordField);
		
		loginButton = new JButton("登录");
		loginButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		loginButton.setBounds(133, 89, 72, 24);
		/**
		 * 给button加enter快捷键
		 */
		loginButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,   0),   "CLICK ");
		loginButton.getActionMap().put("CLICK ",   this);
		getContentPane().add(loginButton);
		
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(215, 89, 72, 24);
		getContentPane().add(cancelButton);
		
		regLabel = new JLabel("<html><font color = \"red\"><u><i>注册帐号</i></u></font></html>");
		regLabel.setHorizontalAlignment(SwingConstants.CENTER);
		regLabel.setBounds(43, 89, 72, 21);
		getContentPane().add(regLabel);
		
		setSize(305, 158);
		
		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);
		regLabel.addMouseListener(this);
		
		accountTextField.setText("wzwahl36");
		passwordField.setText("wzwahl36");
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void loginSuccess(LoginResult result) {
		new DbHanddle().setUserOnline(result.getUser().getAccount(), true);
		new ChatMainFrame(result.getUser()).setVisible(true);
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == loginButton) {
			System.out.println("d"+accountTextField.getText());
			LoginResult result = DbHanddle.login(accountTextField.getText(), String.valueOf(passwordField.getPassword()));
			System.out.println(result);
			switch (result.getReturnValue()) {
				case DbHanddle.USER_FORBIDDEN :
					JOptionPane.showMessageDialog(this, "用户账号被禁用！");
					break;
				case DbHanddle.USER_LOGIN_ERROR :
					JOptionPane.showMessageDialog(this, "用户账号登录出错！");				
					break;
				case DbHanddle.USER_LOGIN_FAIL :
					JOptionPane.showMessageDialog(this, "用户账号密码不配备！");
					break;
				case DbHanddle.USER_LOGIN_SUCCESS :
					loginSuccess(result);
					break;
				case DbHanddle.USER_NOT_VALIDATED :
					JOptionPane.showMessageDialog(this, "用户账号没有通过审核！");
					break;
				case DbHanddle.USER_ONLINE :
					JOptionPane.showMessageDialog(this, "登录成功，但帐号在其他地方登录！");
					loginSuccess(result);
					break;
				default :
					break;
			}
		}
		else if (source == cancelButton) {
			System.exit(ABORT);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == regLabel) {
			this.setVisible(false);
			new RegisteDialog(this).setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if (source == regLabel) {
			regLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source == regLabel) {
			regLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}
}
