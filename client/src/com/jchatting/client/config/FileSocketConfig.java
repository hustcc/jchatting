/**
 * 
 */
package com.jchatting.client.config;

/**
 * 发送文件和接受文件时创建的socket和serversocket配置信息
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-4 下午02:04:54
 */
public class FileSocketConfig {
	public final static String SPLIT_STRING = "!@#%&";
	private String ip;
	private String port;
	private String fileName;
	private long fileLength;
	/**
	 * 
	 */
	private FileSocketConfig() {
		// TODO Auto-generated constructor stub
	}
	public FileSocketConfig(String ip, String port, String fileName, long fileLenght) {
		this.ip = ip;
		this.port = port;
		this.fileName = fileName;
		this.fileLength = fileLenght;
	}
	
	/**
	 * 将对象转化成string，便于socket发送
	 */
	public String toString() {
		return new StringBuffer().append(ip).append(SPLIT_STRING)
								 .append(port).append(SPLIT_STRING)
								 .append(fileName).append(SPLIT_STRING)
								 .append(fileLength).toString();
	}
	/**
	 * 从字符串解析出config
	 * 用于从收到的数据中得到配置信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-4 下午02:12:11
	 * @param config
	 * @return
	 */
	public static FileSocketConfig valueOf(String config) {
		String [] params = config.split(SPLIT_STRING);
		for (int i = 0; i < params.length; i++) {
			String string = params[i];
			System.out.println(i + ":" + string);
		}
		if (params.length == 4) {
			try {
				return new FileSocketConfig(params[0], params[1], params[2], Integer.valueOf(params[3]));
			} catch (NumberFormatException e) {
				return new FileSocketConfig(params[0], params[1], params[2], 0);
			}
		}
		return null;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileLength
	 */
	public long getFileLength() {
		return fileLength;
	}
	/**
	 * @param fileLength the fileLength to set
	 */
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	
	
}
