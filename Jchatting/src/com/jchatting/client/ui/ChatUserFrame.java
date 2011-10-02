package com.jchatting.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import com.jchatting.client.ChatUserFramePool;
import com.jchatting.client.ClientMsgUtil;
import com.jchatting.client.ui.ex.FontAttribute;
import com.jchatting.db.DbHanddle;
import com.jchatting.db.bean.Friend;
import com.jchatting.db.bean.User;
import com.jchatting.db.bean.UserMessage;
import com.jchatting.pack.DataPackage;
import com.jchatting.util.StringUtil;

public class ChatUserFrame extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChatMainFrame mainFrame;
	private User user;
	private Friend friend;
	
	
	private FontAttribute fontAttribute;
	private StyledDocument document;
	
	private JSplitPane splitPane;
	private JScrollPane northScrollPane;
	private JScrollPane southScrollPane;
	private JTextPane receiveTextPane;
	
	private JToggleButton bTogButton;
	private JToggleButton iTogButton;
	private JComboBox fontComboBox;
	private JComboBox fontSizeComboBox;
	
	private JTextPane sendTextPane;
	private JButton sendButton;
	private JButton closeButton;
	private JButton foreColorButton;
	private JButton backColorButton;
	
	private Color foreColor;
	private Color backColor;
	
	/**
	 * Create the frame.
	 */
	public ChatUserFrame(ChatMainFrame mainFrame, Friend friend) {
		this.user = mainFrame.getUser();
		this.friend = friend;
		this.fontAttribute = new FontAttribute();
		
		setTitle("Chat With [" + this.friend.getAccount()+"]");
		setBounds(100, 100, 350, 400);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.7);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
				
		receiveTextPane = new JTextPane();
		receiveTextPane.setEditable(false);
		northScrollPane = new JScrollPane();
		northScrollPane.setViewportView(receiveTextPane);
		
		splitPane.setLeftComponent(northScrollPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		FlowLayout fl_northPanel = (FlowLayout) northPanel.getLayout();
		fl_northPanel.setVgap(2);
		fl_northPanel.setHgap(2);
		fl_northPanel.setAlignment(FlowLayout.LEFT);
		panel.add(northPanel, BorderLayout.NORTH);
		
		bTogButton = new JToggleButton("B");
		bTogButton.setToolTipText("Bold");
		northPanel.add(bTogButton);
		
		iTogButton = new JToggleButton("I");
		northPanel.add(iTogButton);
		
		fontComboBox = new JComboBox();
		fontComboBox.setModel(new DefaultComboBoxModel(new String[] {"宋体", "黑体", "Dialog", "Gulim"}));
		fontComboBox.setToolTipText("Font");
		northPanel.add(fontComboBox);
		
		fontSizeComboBox = new JComboBox();
		fontSizeComboBox.setModel(new DefaultComboBoxModel(new String[] {"8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"}));
		fontSizeComboBox.setSelectedIndex(3);
		fontSizeComboBox.setToolTipText("Font Size");
		northPanel.add(fontSizeComboBox);
		
		foreColorButton = new JButton(" ");
		foreColorButton.setToolTipText("Font Color");
		foreColorButton.setBackground(Color.BLACK);
		northPanel.add(foreColorButton);
		
		backColorButton = new JButton(" ");
		backColorButton.setToolTipText("Font Fore Color");
		backColorButton.setBackground(receiveTextPane.getBackground());
//		northPanel.add(backColorButton);
		
		this.foreColor = foreColorButton.getBackground();
		this.backColor = backColorButton.getBackground();
		
		sendTextPane = new JTextPane();
		southScrollPane = new JScrollPane();
		southScrollPane.setViewportView(sendTextPane);
		panel.add(southScrollPane, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		fl_southPanel.setVgap(2);
		fl_southPanel.setHgap(2);
		panel.add(southPanel, BorderLayout.SOUTH);
		
		sendButton = new JButton("Send[Ctrl+Enter]");
		southPanel.add(sendButton);
		
		closeButton = new JButton("Close");
		southPanel.add(closeButton);
		
		sendTextPane.requestFocus();
		
		bTogButton.addActionListener(this);
		iTogButton.addActionListener(this);
		sendButton.addActionListener(this);
		closeButton.addActionListener(this);
		foreColorButton.addActionListener(this);
		backColorButton.addActionListener(this);
		fontComboBox.addActionListener(this);
		fontSizeComboBox.addActionListener(this);
		sendTextPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getModifiers()==InputEvent.CTRL_MASK&&e.getKeyCode()==KeyEvent.VK_ENTER) {
					sendButtonAction();
				}
			}
			});
		
		document = receiveTextPane.getStyledDocument();
		
		
		this.fontAttribute.setName((String) fontComboBox.getSelectedItem());
		this.fontAttribute.setSize(Integer.parseInt((String) fontSizeComboBox.getSelectedItem()));
		this.fontAttribute.setBold(bTogButton.isSelected());
		this.fontAttribute.setItalic(iTogButton.isSelected());
		this.fontAttribute.setForeColor(foreColor);
		this.fontAttribute.setBackColor(backColor);
		
		changeSendTextPane();
		
		loadOfflineMsg();
		
		delOfflineMsg();
	}
	/**
	 * 以一定的格式向textpane中添加字符串
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-30 下午02:05:18
	 * @param attribute
	 * @param text
	 */
	private void insert(FontAttribute attribute, String text) {
		try { // 插入文本
			document.insertString(document.getLength(), text + "\n", attribute.getAttrSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
//		northScrollPane.getVerticalScrollBar().setValue(northScrollPane.getVerticalScrollBar().getMaximum());
		receiveTextPane.setCaretPosition(receiveTextPane.getDocument().getLength());
	}
	private void delOfflineMsg() {
		new DbHanddle().delMsgByAccount(friend.getAccount(), user.getAccount());
	}
	/**
	 * 将数据库找中保存的离线消息显示出来！
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-29 下午08:18:27
	 */
	private void loadOfflineMsg() {
		Vector<UserMessage> messages = new DbHanddle().getMsgByAccount(friend.getAccount(), user.getAccount());
		UserMessage message = null;
		DataPackage dataPackage = new DataPackage();
		for (int i = 0; i < messages.size(); i++) {
			message = messages.get(i);
			dataPackage.setSendId(message.getSendAccount());
			dataPackage.setReceiveId(message.getReceiveAccount());
			dataPackage.setContent(message.getContent());
			dataPackage.setType(DataPackage.USER);
			receiveMsg(dataPackage, message.getSendTime());
		}
	}
	/**
	 * 发送信息按钮的动作
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-26 下午01:10:44
	 */
	private void sendButtonAction() {
		String message = sendTextPane.getText();
		if (StringUtil.isEmpty(message)) {
			JOptionPane.showMessageDialog(this, "Can not send empty message!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else {
			sendTextPane.setText("");
			String user_time = user.getAccount() + "   [Me]    " + new Timestamp(System.currentTimeMillis());
			
			String content = "    " + message;
			insert(new FontAttribute(FontAttribute.SENDER_TIME_ATTRIBUTE), user_time);
			insert(this.fontAttribute, content);
			int type = DataPackage.USER;
			String sendId = user.getAccount();
			String receiveId = friend.getAccount();
			System.out.println("send content:" + message);
			try {
				if (ChatMainFrame.getSocket() == null) {
					JOptionPane.showMessageDialog(mainFrame, "您已经下线，请退出后重新登录！", "Eroor", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ClientMsgUtil.sendMsg(ChatMainFrame.getSocket(), new DataPackage(type, sendId, receiveId, message));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭按钮事件
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-26 下午01:11:03
	 */
	private void closeButtonAction() {
		if (JOptionPane.showConfirmDialog(this, "Sure to close chat frame?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			ChatUserFramePool.delChatFrame(friend.getAccount());
			dispose();
		}
	}
	
	public void receiveMsg(DataPackage dataPackage, Timestamp timestamp) {
		//改变文字在ta中的格式
		String user_time = dataPackage.getSendId() + " [" + friend.getAttach() +"] " + timestamp;
		
		String content = "    " + dataPackage.getContent();
		insert(new FontAttribute(FontAttribute.RECEIVER_TIME_ATTRIBUTE), user_time);
		insert(this.fontAttribute, content);
	}
	
	private void changeSendTextPane() {
		sendTextPane.setParagraphAttributes(this.fontAttribute.getAttrSet(), true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == bTogButton) {
			this.fontAttribute.setBold(bTogButton.isSelected());
			changeSendTextPane();
		}
		else if (source == iTogButton) {
			this.fontAttribute.setItalic(iTogButton.isSelected());
			changeSendTextPane();
		}
		else if (source == sendButton) {
			sendButtonAction();
		}
		else if (source == closeButton) {
			closeButtonAction();
		}
		else if (source == foreColorButton) {
			this.foreColor = JColorChooser.showDialog(this, "Choose The Font ForeColor", foreColorButton.getForeground());
			foreColorButton.setBackground(this.foreColor);
			this.fontAttribute.setForeColor(this.foreColor);
			changeSendTextPane();
		}
		else if (source == backColorButton) {
			this.backColor = JColorChooser.showDialog(this, "Choose The Font BackColor", backColorButton.getBackground());
			backColorButton.setBackground(this.backColor);
			this.fontAttribute.setBackColor(this.backColor);
			changeSendTextPane();
		}
		else if (source == fontSizeComboBox) {
			this.fontAttribute.setSize(Integer.parseInt((String) fontSizeComboBox.getSelectedItem()));
			changeSendTextPane();
		}
		else if (source == fontComboBox) {
			this.fontAttribute.setName((String) fontComboBox.getSelectedItem());
			changeSendTextPane();
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
		closeButtonAction();
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
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the friend
	 */
	public Friend getFriend() {
		return friend;
	}
	/**
	 * @param friend the friend to set
	 */
	public void setFriend(Friend friend) {
		this.friend = friend;
	}
	
}
