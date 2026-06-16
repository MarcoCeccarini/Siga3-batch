package it.finanze.siga.bean;

import java.io.Serializable;

public class FileBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8473622300841693937L;
	
	private String name;
	
	 private byte[] data; 

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
