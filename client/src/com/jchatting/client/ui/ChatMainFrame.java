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
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

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

import com.jchatting.client.config.ClientConfig;
import com.jchatting.client.thread.ClientThread;
import com.jchatting.client.util.ChatGroupFramePool;
import com.jchatting.client.util.ChatUserFramePool;
import com.jchatting.client.util.ClientMsgUtil;
import com.jchatting.db.DbHanddle;
import com.jchatting.db.bean.Friend;
import com.jchatting.db.bean.Group;
import com.jchatting.db.bean.User;
import com.jchatting.db.bean.UserInGroup;
import com.jchatting.db.dao.impl.UserImpl;
import com.jchatting.db.dao.impl.UserInGroupImpl;
import com.jchatting.db.dao.impl.UserUserImpl;
import com.jchatting.pack.DataPackage;

public class ChatMainFrame extends JFrame implements ActionListener, MouseListener, WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbHanddle handdle = new DbHanddle();
	private User user;
	private static Socket socket;

	private Map<String, Friend> friendMap;
	private Map<String, UserInGroup> groupMap;
	
	private JTree userTree;
	private DefaultTreeModel userTreeModel;
	private DefaultMutableTreeNode userRoot;
	private DefaultMutableTreeNode online;
	private DefaultMutableTreeNode offline;
	
	private JTree groupTree;
	private DefaultTreeModel groupTreeModel;
	private DefaultMutableTreeNode groupRoot;
	
	private JPopupMenu userPopupMenu;
	private JMenuItem chatItem;
//	private JMenuItem alterItem;
	private JMenuItem delItem;
	private JMenuItem addItem;
	private JMenuItem userInfoItem;
	private JMenuItem friendInfoItem;
	private JMenuItem refreshUserItem;
	
	private JPopupMenu userPopupMenu_1;
	private JMenuItem addItem_1;
	private JMenuItem userInfoItem_1;
	private JMenuItem refreshUserItem_1;
	
	private JPopupMenu grouPopupMenu;
	private JMenuItem chatGroupItem;
	private JMenuItem createGroupItem;
	private JMenuItem enterGroupItem;
	private JMenuItem quitGroupItem;
	private JMenuItem groupInfoItem;
	private JMenuItem refreshGroupItem;
	
	private JPopupMenu grouPopupMenu_1;
	private JMenuItem createGroupItem_1;
	private JMenuItem enterGroupItem_1;
	private JMenuItem refreshGroupItem_1;
	
	
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
		
		constructFriendTree();
		
		userTree = new JTree(userTreeModel);
		userTree.addMouseListener(this);
		userScrollPane.setViewportView(userTree);
		
		JScrollPane groupScrollPane = new JScrollPane();
		tabbedPane.addTab("JChat群组", null, groupScrollPane, null);
		
		
		constructGroupTree();
		
		groupTree = new JTree(groupTreeModel);
		groupTree.addMouseListener(this);
		groupScrollPane.setViewportView(groupTree);
		
		
		setSize(300,500);
		
		userPopupMenu = new JPopupMenu();
		
		chatItem = new JMenuItem("Chat");
		delItem = new JMenuItem("Del Friend");
//		alterItem = new JMenuItem("Edit Friend");
		addItem = new JMenuItem("Add Friend");
		userInfoItem = new JMenuItem("My Info");
		friendInfoItem = new JMenuItem("Friend Info");
		refreshUserItem = new JMenuItem("Refresh Friend");
		
		chatItem.addActionListener(this);
		delItem.addActionListener(this);
//		alterItem.addActionListener(this);
		addItem.addActionListener(this);
		userInfoItem.addActionListener(this);
		friendInfoItem.addActionListener(this);
		refreshUserItem.addActionListener(this);
		
		userPopupMenu.add(chatItem);
		userPopupMenu.addSeparator();
		userPopupMenu.add(addItem);
//		popupMenu.add(alterItem);
		userPopupMenu.add(delItem);
		userPopupMenu.addSeparator();
		userPopupMenu.add(userInfoItem);
		userPopupMenu.add(friendInfoItem);
		userPopupMenu.addSeparator();
		userPopupMenu.add(refreshUserItem);
		
		
		userPopupMenu_1 = new JPopupMenu();
		addItem_1 = new JMenuItem("Add Friend");
		userInfoItem_1 = new JMenuItem("My Info");
		refreshUserItem_1 = new JMenuItem("Refresh Friend");
		addItem_1.addActionListener(this);
		userInfoItem_1.addActionListener(this);
		refreshUserItem_1.addActionListener(this);
		userPopupMenu_1.add(addItem_1);
		userPopupMenu_1.addSeparator();
		userPopupMenu_1.add(userInfoItem_1);
		userPopupMenu_1.addSeparator();
		userPopupMenu_1.add(refreshUserItem_1);
		
		
		grouPopupMenu = new JPopupMenu();
		chatGroupItem = new JMenuItem("Chat In Group");
		createGroupItem = new JMenuItem("Create Group");
		enterGroupItem = new JMenuItem("Enter Group");
		quitGroupItem = new JMenuItem("Quit The Group");
		groupInfoItem = new JMenuItem("Group Info");
		refreshGroupItem = new JMenuItem("Refresh Group");
		chatGroupItem.addActionListener(this);
		createGroupItem.addActionListener(this);
		enterGroupItem.addActionListener(this);
		quitGroupItem.addActionListener(this);
		groupInfoItem.addActionListener(this);
		refreshGroupItem.addActionListener(this);
		grouPopupMenu.add(chatGroupItem);
		grouPopupMenu.addSeparator();
		grouPopupMenu.add(createGroupItem);
		grouPopupMenu.add(enterGroupItem);
		grouPopupMenu.add(quitGroupItem);
		grouPopupMenu.addSeparator();
		grouPopupMenu.add(groupInfoItem);
		grouPopupMenu.addSeparator();
		grouPopupMenu.add(refreshGroupItem);
		
		grouPopupMenu_1 = new JPopupMenu();
		createGroupItem_1 = new JMenuItem("Create Group");
		enterGroupItem_1 = new JMenuItem("Enter Group");
		refreshGroupItem_1 = new JMenuItem("Refresh Group");
		enterGroupItem_1.addActionListener(this);
		createGroupItem_1.addActionListener(this);
		refreshGroupItem_1.addActionListener(this);
		grouPopupMenu_1.add(createGroupItem_1);
		grouPopupMenu_1.add(enterGroupItem_1);
		grouPopupMenu_1.add(refreshGroupItem_1);
		
		
		
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
		initUser();
		initGroup();
	}
	private void initUser() {
		friendMap = handdle.getFriendMap(this.user.getAccount());
	}
	private void initGroup() {
		groupMap = handdle.getAllGroupOfUser(this.user);
	}
	public void reConstractUserTree(Map<String, Friend> friendMap) {
		setFriendMap(friendMap);
		constructFriendTree();
		userTree.setModel(userTreeModel);
	}
	public void reConstractGroupTree(Map<String, UserInGroup> groupMap) {
		setGroupMap(groupMap);
		constructGroupTree();
		groupTree.setModel(groupTreeModel);
	}
	/**
	 * 根据数据库数据，构造树
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-30 下午07:44:27
	 */
	private void constructFriendTree() {
		userRoot = new DefaultMutableTreeNode("JChatting Friend List");
		online = new DefaultMutableTreeNode("Online Friends");
		offline = new DefaultMutableTreeNode("Offline Friends");
		userTreeModel = new DefaultTreeModel(userRoot);
		userTreeModel.insertNodeInto(online, userRoot,userRoot.getChildCount());
		userTreeModel.insertNodeInto(offline, userRoot, userRoot.getChildCount());
		
		int onlineCount = 0;
		int offlineCount = 0;
		Iterator<Friend> friIterator = friendMap.values().iterator();
		Friend friend = null;
		while (friIterator.hasNext()) {
			friend = (Friend) friIterator.next();
			if (friend.isOnline()) {
				DefaultMutableTreeNode online = new DefaultMutableTreeNode(friend);
				userTreeModel.insertNodeInto(online, this.online, this.online.getChildCount());
				onlineCount ++;
			}
			else {
				DefaultMutableTreeNode offline = new DefaultMutableTreeNode(friend);
				userTreeModel.insertNodeInto(offline, this.offline, this.offline.getChildCount());
				offlineCount ++;
			}
		}
		online.setUserObject("Online Friends  [" + onlineCount + "]");
		offline.setUserObject("Offline Friends  [" + offlineCount + "]");
	}
	
	private void constructGroupTree() {
		//todo
		
		groupRoot = new DefaultMutableTreeNode("JChatting Group List  [" + groupMap.size() + "]");
		groupTreeModel = new DefaultTreeModel(groupRoot);
		Iterator<UserInGroup> groupIterator = groupMap.values().iterator();
		UserInGroup group = null;
		while (groupIterator.hasNext()) {
			group = groupIterator.next();
			DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);
			groupTreeModel.insertNodeInto(groupNode, this.groupRoot, this.groupRoot.getChildCount());
			
		}
	}
	
	private void chatWithUser() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
		if (node.isLeaf() && node.getUserObject() instanceof Friend) {
			Friend friend = (Friend)node.getUserObject();
			System.out.println(friend.getAccount());
			ChatUserFrame userFrame = ChatUserFramePool.getChatFrame(friend.getAccount());
			if (userFrame == null) {
				userFrame = new ChatUserFrame(this, friend);
			}
				
			userFrame.setVisible(true);
			ChatUserFramePool.addChatFrame(userFrame);
		}
	}
	
	private void chatInGroup() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)groupTree.getLastSelectedPathComponent();
		if (node.isLeaf() && node.getUserObject() instanceof UserInGroup) {
			UserInGroup userInGroup = (UserInGroup)node.getUserObject();
			System.out.println(userInGroup.getAccount());
			ChatGroupFrame groupFrame = ChatGroupFramePool.getChatFrame(userInGroup.getId());
			if (groupFrame == null) {
				groupFrame = new ChatGroupFrame(this, userInGroup);
			}
			groupFrame.setVisible(true);
			ChatGroupFramePool.addChatFrame(groupFrame);
		}
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
	public UserInGroup getUserInGroupByGroupId(String groupId) {
		return this.groupMap.get(groupId);
	}

	private void enterIntoGroup(User user, String groupId) {
		int result = new DbHanddle().addUserIntoGroup(user, groupId);
		if (result == UserInGroupImpl.GROUP_NOT_EXIST) {
			JOptionPane.showMessageDialog(this, "Group is not exist, Check the group is error!");
			return;
		}
		else if (result == UserInGroupImpl.USER_IN_GROUP) {
			JOptionPane.showMessageDialog(this, "You are in this group!");
			return;
		}
		else if (result == UserInGroupImpl.ADD_USER_TO_GROUP_ERROR) {
			JOptionPane.showMessageDialog(this, "Error in enter group, try later!");
			return;
		}
		Group group = new DbHanddle().getGroupById(groupId);
		groupMap.put(groupId, new UserInGroup(group, user.getAccount(), ""));
		reConstractGroupTree(groupMap);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == delItem) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
			if (node.isLeaf() && node.getUserObject() instanceof Friend) {
				Friend friend = (Friend)node.getUserObject();
				System.out.println(friend.getAccount());
				if (new DbHanddle().delFriend(user.getAccount(), friend.getAccount())) {
//					JOptionPane.showMessageDialog(this, "Delete friend " + friend.getAccount() + " success!");
					this.friendMap.remove(friend.getAccount());
					reConstractUserTree(this.friendMap);
				}
				else {
					JOptionPane.showMessageDialog(this, "Fail to delete friend [" + friend.getAccount() + "]");
				}
			}
		}
		else if (source == addItem || source == addItem_1) {
			String friendAccount = JOptionPane.showInputDialog(this, "Input friend's account:");
			if (friendAccount != null) {
				int result = new DbHanddle().addFriend(user.getAccount(), friendAccount);
				System.out.println("add result:" + result);
				if (result == UserUserImpl.INSERT_USERUSER_SUCCESS) {
					JOptionPane.showMessageDialog(this, "Add friend [" + friendAccount + "] success!");
					User friendUser = new DbHanddle().getUserByAccount(friendAccount);
					Friend friend = new Friend(friendUser, "", "", "");
					this.friendMap.put(friendAccount, friend);
					reConstractUserTree(this.friendMap);
				}
				else if (result == UserImpl.USER_NOT_EXIST) {
					JOptionPane.showMessageDialog(this, "Friend [" + friendAccount + "] not exist!");
				}
				else if (result == UserUserImpl.USERUSER_EXIST) {
					JOptionPane.showMessageDialog(this, "Friend [" + friendAccount + "] is exist in your friend list!");
				}
				else {
					JOptionPane.showMessageDialog(this, "Add friend [" + friendAccount + "] error!");
				}
			}
		}
		else if (source == chatItem) {
			chatWithUser();
		}
		else if (source == userInfoItem || source == userInfoItem_1) {
			//todo
			System.out.println("userInfo item");
			JOptionPane.showMessageDialog(this, "not supply!");
		}
		else if (source == friendInfoItem) {
			//todo
			System.out.println("friendInfo item");
			JOptionPane.showMessageDialog(this, "not supply!");
		}
		else if (source == chatGroupItem) {
			chatInGroup();
		}
		else if (source == createGroupItem || source == createGroupItem_1) {
			//todo
			String groupName = JOptionPane.showInputDialog(this, "Input the chat group's name!");
			if (groupName != null) {
				int groupId = new DbHanddle().createGroup(new Group(-1, groupName, new Timestamp(System.currentTimeMillis()), "", ""));
				if (groupId > 0) {
					enterIntoGroup(user, String.valueOf(groupId));
				}
			}
		}
		else if (source == enterGroupItem || source == enterGroupItem_1) {
			//todo
			String groupId = JOptionPane.showInputDialog(this, "Input the chat group's id!");
			if (groupId != null) {
				enterIntoGroup(user, groupId);
			}
		}
		else if (source == quitGroupItem) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)groupTree.getLastSelectedPathComponent();
			if (node.isLeaf() && node.getUserObject() instanceof UserInGroup) {
				UserInGroup userInGroup = (UserInGroup)node.getUserObject();
				if (new DbHanddle().quitOutGroup(user, userInGroup)) {
					groupMap.remove(userInGroup.getId());
					reConstractGroupTree(groupMap);
				}
			}
		}
		else if (source == groupInfoItem) {
			//todo
			System.out.println("groupInfoItem");
			JOptionPane.showMessageDialog(this, "not supply!");
		}
		else if (source == refreshGroupItem || source == refreshGroupItem_1) {
			initGroup();
			reConstractGroupTree(getGroupMap());
		}
		else if (source == refreshUserItem || source == refreshUserItem_1) {
			initUser();
			reConstractUserTree(getFriendMap());
		}
		else {
			System.out.println("other item");
			JOptionPane.showMessageDialog(this, "not supply!");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//双击打开聊天窗口
		if (e.getClickCount() == 2) {
			if (e.getSource() == userTree) {
				chatWithUser();
				
			}
			else if (e.getSource() == groupTree) {
				chatInGroup();
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
						userPopupMenu.show(e.getComponent(), X, Y);
					}
					else {
						userPopupMenu_1.show(e.getComponent(), X, Y);
					}
				}
			}
			else if (source == groupTree) {
//				int selRow = groupTree.getRowForLocation(e.getX(), e.getY());//返回节点所在的行，-1表示鼠标定位不在显示的单元边界内 
                TreePath selPath = groupTree.getPathForLocation(e.getX(), e.getY());//返回指定节点的树路径 
				if (selPath != null) {
					groupTree.setSelectionPath(selPath);
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)groupTree.getLastSelectedPathComponent();
					if (node.isLeaf() && node.getUserObject() instanceof UserInGroup) {
						grouPopupMenu.show(e.getComponent(), X, Y);
					}
					else {
						grouPopupMenu_1.show(e.getComponent(), X, Y);
					}
				}
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
	 * @return the groupMap
	 */
	public Map<String, UserInGroup> getGroupMap() {
		return groupMap;
	}

	/**
	 * @param groupMap the groupMap to set
	 */
	public void setGroupMap(Map<String, UserInGroup> groupMap) {
		this.groupMap = groupMap;
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
