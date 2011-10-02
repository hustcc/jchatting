package com.jchatting.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.jchatting.client.ChatFramePool;
import com.jchatting.client.ClientMsgUtil;
import com.jchatting.client.ClientThread;
import com.jchatting.client.config.ClientConfig;
import com.jchatting.db.DbHanddle;
import com.jchatting.db.bean.Friend;
import com.jchatting.db.bean.Group;
import com.jchatting.db.bean.User;
import com.jchatting.pack.DataPackage;

public class ChatMainFrame extends JFrame implements ActionListener, MouseListener, WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbHanddle handdle = new DbHanddle();
	private User user;
	private static Socket socket;

//	private Vector<Friend> friendVector;
	private Map<String, Friend> friendMap;
	private Vector<Group> groupvecVector;
	
	private JTree userTree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode online;
	private DefaultMutableTreeNode offline;
	
	private JTree groupTree;
	
	private JPopupMenu popupMenu;
	private JPopupMenu addPopupMenu;
	
	private JMenuItem chatItem;
	private JMenuItem alterItem;
	private JMenuItem delItem;
	private JMenuItem addItem;
	
	/**
	 * Create the frame.
	 */
	public ChatMainFrame(User user) {
		this.user = user;
		
		initUserAndGroup();
		
		setTitle("JChat--" + user.getAccount());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JScrollPane userScrollPane = new JScrollPane();
		tabbedPane.addTab("JChat好友", null, userScrollPane, null);
		tabbedPane.setEnabledAt(0, true);
		
		constractTree();
		
		userTree = new JTree(treeModel);
		userTree.addMouseListener(this);
		userScrollPane.setViewportView(userTree);
		
		JScrollPane groupScrollPane = new JScrollPane();
		tabbedPane.addTab("JChat群组", null, groupScrollPane, null);
		
		groupTree = new JTree();
		groupTree.addMouseListener(this);
		groupScrollPane.setViewportView(groupTree);
		
		setSize(300,500);
		
		popupMenu = new JPopupMenu();
		chatItem = new JMenuItem("Chat");
		chatItem.addActionListener(this);
		delItem = new JMenuItem("Del Friend");
		delItem.addActionListener(this);
		alterItem = new JMenuItem("Edit Friend");
		alterItem.addActionListener(this);
		addItem = new JMenuItem("Add Friend");
		addItem.addActionListener(this);
		popupMenu.add(chatItem);
		popupMenu.addSeparator();
		popupMenu.add(alterItem);
		popupMenu.add(delItem);
		popupMenu.add(addItem);
		
		addPopupMenu = new JPopupMenu();
		addPopupMenu.add(addItem);
		
		connectServerBySocket();
		
	}
	
	private void connectServerBySocket() {
		//连接服务器，获得socket
		try {
			ChatMainFrame.socket = new Socket(ClientConfig.instance().getIp(), ClientConfig.instance().getPort());
			System.out.println("连接成功");
			ClientMsgUtil.sendMsg(ChatMainFrame.getSocket(), DataPackage.CLIENT_ON, user.getAccount(), "SYSTEM", "");
			
			new ClientThread(this).start();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Connect to server Error, Try again!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(ABORT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Connect to server Error, Try again!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(ABORT);
		}
	}
	
	private void initUserAndGroup() {
//		friendVector = handdle.getFriendList(this.user.getAccount());
		friendMap = handdle.getFriendMap(this.user.getAccount());
		//TODO
		groupvecVector = null;
	}
	public void reConstractTree(Map<String, Friend> friendMap) {
		setFriendMap(friendMap);
//		online.removeAllChildren();
//		offline.removeAllChildren();
		constractTree();
		userTree.setModel(treeModel);
	}
	/**
	 * 根据数据库数据，构造树
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-30 下午07:44:27
	 */
	private void constractTree() {
		root = new DefaultMutableTreeNode("JChatting");
		online = new DefaultMutableTreeNode("Online Friends");
		offline = new DefaultMutableTreeNode("Offline Friends");
		treeModel = new DefaultTreeModel(root);
		treeModel.insertNodeInto(online, root, root.getChildCount());
		treeModel.insertNodeInto(offline, root, root.getChildCount());
		
		int onlineCount = 0;
		int offlineCount = 0;
		Iterator<Friend> friIterator = friendMap.values().iterator();
		Friend friend = null;
		while (friIterator.hasNext()) {
			friend = (Friend) friIterator.next();
			if (friend.isOnline()) {
				DefaultMutableTreeNode online = new DefaultMutableTreeNode(friend);
				treeModel.insertNodeInto(online, this.online, this.online.getChildCount());
				onlineCount ++;
			}
			else {
				DefaultMutableTreeNode offline = new DefaultMutableTreeNode(friend);
				treeModel.insertNodeInto(offline, this.offline, this.offline.getChildCount());
				offlineCount ++;
			}
		}
		online.setUserObject("Online Friends  [" + onlineCount + "]");
		offline.setUserObject("Offline Friends  [" + offlineCount + "]");
	}
	/**
	 * 通过好友的account得到好友的Friend所有信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-29 上午09:43:05
	 * @param friendAccount
	 * @return
	 */
	public Friend getFriendByAccount(String friendAccount) {
		return friendMap.get(friendAccount);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == delItem) {
			System.out.println("del item");
		}
		else if (source == alterItem) {
			System.out.println("alter item");
		}
		else if (source == addItem) {
			System.out.println("add item");
		}
		else if (source == chatItem) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
			if (node.isLeaf() && node.getUserObject() instanceof Friend) {
				Friend friend = (Friend)node.getUserObject();
				System.out.println(friend.getAccount());
				ChatUserFrame userFrame = new ChatUserFrame(this, friend);
				userFrame.setVisible(true);
				ChatFramePool.addChatFrame(userFrame);
			}
		}
		else {
			System.out.println("other item");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//双击打开聊天窗口
		if (e.getClickCount() == 2) {
			if (e.getSource() == userTree) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
				if (node.isLeaf() && node.getUserObject() instanceof Friend) {
					Friend friend = (Friend)node.getUserObject();
					System.out.println(friend.getAccount());
					ChatUserFrame userFrame = new ChatUserFrame(this, friend);
					userFrame.setVisible(true);
					ChatFramePool.addChatFrame(userFrame);
				}
				//TODO
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int X = e.getX();
		int Y = e.getY();
		Object source = e.getSource();
		if (e.isPopupTrigger()) {
			if (source == userTree) {
//				int selRow = userTree.getRowForLocation(e.getX(), e.getY());//返回节点所在的行，-1表示鼠标定位不在显示的单元边界内 
                TreePath selPath = userTree.getPathForLocation(e.getX(), e.getY());//返回指定节点的树路径 
				if (selPath != null) {
					userTree.setSelectionPath(selPath);
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
					if (node.isLeaf() && node.getUserObject() instanceof Friend) {
						popupMenu.show(e.getComponent(), X, Y);
					}
					else {
						addPopupMenu.show(e.getComponent(), X, Y);
					}
				}
			}
			else if (source == groupTree) {
				popupMenu.show(e.getComponent(), X, Y);
			}
			else {
				
			}
		}
		else {
			
		}
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
	public synchronized void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the socket
	 */
	public synchronized static Socket getSocket() {
		return ChatMainFrame.socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public synchronized static void setSocket(Socket socket) {
		ChatMainFrame.socket = socket;
	}

	
	/**
	 * @return the friendMap
	 */
	public Map<String, Friend> getFriendMap() {
		return friendMap;
	}

	/**
	 * @param friendMap the friendMap to set
	 */
	public void setFriendMap(Map<String, Friend> friendMap) {
		this.friendMap = friendMap;
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
		if (JOptionPane.showConfirmDialog(this, "Sure to quit?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			this.dispose();
			new DbHanddle().setUserOnline(user.getAccount(), false);
			System.exit(ABORT);
		}
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
