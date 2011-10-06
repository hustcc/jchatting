package com.jchatting.client.ui.ex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.jchatting.client.config.FileSocketConfig;
import com.jchatting.client.thread.FileReceiveThread;
import com.jchatting.client.thread.FileSendThread;
import com.jchatting.client.ui.ChatUserFrame;
import com.jchatting.util.FileUtils;
import com.jchatting.util.StringUtil;

public class SendRecvDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static int CANCEL_SEDNRECV = 8;
	public final static int REFUSE_RECV = 9;
	
	public final static int SEND_FILE = 1;
	public final static int RECEIVE_FILE = 2;
	
	private int type;
	private ChatUserFrame userFrame;
	private FileSocketConfig config;
	private String friendId;
	
	private String fileName;
	private long fileSize;
	
	
	private JProgressBar progressBar;
	private JLabel rateLabel;
	private JLabel speedLabel;
	private JButton cancelButton;
	
	private FileSendThread sendThread;
	private FileReceiveThread receiveThread;
	
	private Timer timer;
	

	/**
	 * Create the frame.
	 */
	public SendRecvDialog(int type, ChatUserFrame userFrame, FileSocketConfig config, String friendId) {
		this.type = type;
		this.userFrame = userFrame;
		this.config = config;
		this.friendId = friendId;
		
		this.fileName = this.config.getFileName();
		this.fileSize = this.config.getFileLength();
		
		timer = new Timer();
		
		setTitle(type == SEND_FILE ? "Send File Progress" : "Receive File Progress");
		
		setBounds(100, 100, 400, 165);
		setSize(400, 160);
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		speedLabel = new JLabel(" ");
		panel.add(speedLabel);
		
		cancelButton = new JButton("Cancel");
		panel.add(cancelButton);
		
		progressBar = new JProgressBar();
		getContentPane().add(progressBar, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 20));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(5);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2, BorderLayout.CENTER);
		
		JLabel fileNameLabel = new JLabel(StringUtil.replaceLengthChars(this.fileName, 10, "..."));
		panel_2.add(fileNameLabel);
		
		JLabel sendByLabel = new JLabel();
		if (this.type == SEND_FILE) {
			sendByLabel.setText("Send To");
		}
		else if (this.type == RECEIVE_FILE) {
			sendByLabel.setText("Send By");
		}
		panel_2.add(sendByLabel);
		
		JLabel friendIdLabel = new JLabel("[ " + StringUtil.replaceLengthChars(this.friendId, 10, "...") + " ]");
		panel_2.add(friendIdLabel);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setVgap(20);
		flowLayout_2.setHgap(10);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3, BorderLayout.EAST);
		
		JLabel fileSizeLabel = new JLabel(this.fileSize + "Bytes");
		panel_3.add(fileSizeLabel);
		
		rateLabel = new JLabel();
		panel_3.add(rateLabel);
		
		setAlwaysOnTop(true);
		
		cancelButton.addActionListener(this);

	}

//	public void startSendRecv() {
//		timer.schedule(new TimerTask() {
//			long lastSecSize = 0;
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				long sendRecvSize = ((long)progressBar.getValue())/(long)100.0f * fileSize;
//				long speed = sendRecvSize - lastSecSize;
//				
//				speedLabel.setText(String.valueOf(speed));
//				
//				lastSecSize = sendRecvSize;
//			}
//		}, 1000, 1000);
//	}
	
	/**
	 * 收发文件完成
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-5 下午03:34:32
	 */
	public void finishFileTrans() {
		progressBar.setValue(100);	
		userFrame.finishFileTrans(this, this.fileName);
	}
	/**
	 * 通过收发的大小，更新界面
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-5 下午03:34:12
	 * @param sendOrReceiSize
	 */
	public void update(long sendOrReceiSize) {
//		System.out.println("send :" + sendOrReceiSize);
		if (sendOrReceiSize <= this.fileSize && this.fileSize != 0) {
			rateLabel.setText(String.valueOf(sendOrReceiSize / this.fileSize));
			progressBar.setValue((int)(((float)sendOrReceiSize/(float)this.fileSize) * 100));
			rateLabel.setText(progressBar.getValue() + " %");
		}
	}
	
	public void cancel(int type) {
		System.out.println("cancel");
		userFrame.cancelRefuseSendRecv(fileName, rateLabel.getText(), type);
		if (this.type == SEND_FILE && sendThread != null) {
			cancelButton.setEnabled(false);
			sendThread.cancel();
		}
		else if (this.type == RECEIVE_FILE && receiveThread != null) {
			cancelButton.setEnabled(false);
			timer.cancel();
			timer = null;
			receiveThread.cancel();
		}
		
		FileUtils.delete(new File("res/temp/"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == cancelButton) {
			cancel(CANCEL_SEDNRECV);
		}
	}

	/**
	 * @param sendThread the sendThread to set
	 */
	public void setSendThread(FileSendThread sendThread) {
		this.sendThread = sendThread;
	}

	/**
	 * @param receiveThread the receiveThread to set
	 */
	public void setReceiveThread(FileReceiveThread receiveThread) {
		this.receiveThread = receiveThread;
	}
	
}
