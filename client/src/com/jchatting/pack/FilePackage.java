/**
 * 
 */
package com.jchatting.pack;

import java.io.Serializable;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-4 下午01:26:03
 */
public class FilePackage implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String FILE_BUFFER = "fIlE";
	
	private String packType;
	private String sendId;
	private String receiveId;
	private byte[] fileBuffer;
	
	
	
	public FilePackage() {
		
	}
	public FilePackage(String packType, String sendId, String receiveId,
			byte[] fileBuffer) {
		this.packType = packType;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.fileBuffer = fileBuffer;
		
	}
	/**
	 * @return the packType
	 */
	public String getPackType() {
		return packType;
	}
	/**
	 * @param packType the packType to set
	 */
	public void setPackType(String packType) {
		this.packType = packType;
	}
	/**
	 * @return the sendId
	 */
	public String getSendId() {
		return sendId;
	}
	/**
	 * @param sendId the sendId to set
	 */
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	/**
	 * @return the receiveId
	 */
	public String getReceiveId() {
		return receiveId;
	}
	/**
	 * @param receiveId the receiveId to set
	 */
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	/**
	 * @return the fileBuffer
	 */
	public byte[] getFileBuffer() {
		return fileBuffer;
	}
	/**
	 * @param fileBuffer the fileBuffer to set
	 */
	public void setFileBuffer(byte[] fileBuffer) {
		this.fileBuffer = fileBuffer;
	}

}
