package test.fileTran;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class ClientTest {
	private ClientSocket cs = null;

	private String ip = "192.168.0.106"; // 设置成服务器IP

	private int port = 8821;

	private String sendMessage = "Windows";

	public ClientTest() {
		try {
			if (createConnection()) {
				sendMessage();
				getMessage();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private boolean createConnection() {
		cs = new ClientSocket(ip, port);
		try {
			cs.CreateConnection();
			System.out.print("连接服务器成功!" + "");
			return true;
		} catch (Exception e) {
			System.out.print("连接服务器失败!" + "");
			return false;
		}

	}

	private void sendMessage() {
		if (cs == null)
			return;
		try {
			cs.sendMessage(sendMessage);
		} catch (Exception e) {
			System.out.print("发送消息失败!" + "");
		}
	}

	private void getMessage() {
		if (cs == null)
			return;
		DataInputStream inputStream = null;
		try {
			inputStream = cs.getMessageStream();
		} catch (Exception e) {
			System.out.print("接收消息缓存错误");
			return;
		}

		try {
			// 本地保存路径，文件名会自动从服务器端继承而来。
			String savePath = "E:\\";
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			int passedlen = 0;
			long len = 0;
			long lastPer = 0;
			long thisPer = 0;
			String fileName = "";
			fileName = inputStream.readUTF();
			savePath += fileName;
			DataOutputStream fileOut = new DataOutputStream(
					new BufferedOutputStream(new BufferedOutputStream(
							new FileOutputStream(savePath))));
			len = inputStream.readLong();
			
			System.out.println("文件名:" + fileName + "\t文件的长度为:" + len/1024/1024 + "M");
			System.out.println("开始接收文件..." + "");
			System.out.print("<");

			while (true) {
				int read = 0;
				if (inputStream != null) {
					read = inputStream.read(buf);
				}
				passedlen += read;
				if (read == -1) {
					break;
				}
				// 下面进度条本为图形界面的prograssBar做的，这里如果是打文件，
				// 可能会重复打印出一些相同的百分比
				thisPer = passedlen * 100 / len;
				if(thisPer > lastPer)
				{
					lastPer = thisPer;
					System.out.print("=" + thisPer);
				}
				
				fileOut.write(buf, 0, read);
			}
			System.out.println(">");
			System.out.println("接收完成，文件存为" + savePath + "");
			

			fileOut.close();
		} catch (Exception e) {
			System.out.println("接收消息错误" + "");
			return;
		}
	}

	public static void main(String arg[]) {
		new ClientTest();
	}
}