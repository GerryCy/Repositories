package cn.shop.utils.mail;

import java.io.File;

public class AttachBean {
	private String cid;
	private File file;
	private String filename;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public AttachBean() {}
	
	public AttachBean(File file, String filename) {
		super();
		this.file = file;
		this.filename = filename;
	}
	
	
}
