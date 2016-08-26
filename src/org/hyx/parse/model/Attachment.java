package org.hyx.parse.model;

//附件 —无忧      //attName attUrl  Attachment
public class Attachment {
	private String attName; //附件名
	private String attUrl;//附件URL
	
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getAttUrl() {
		return attUrl;
	}
	public void setAttUrl(String attUrl) {
		this.attUrl = attUrl;
	}
	public String toString(){
		
		return "[Attachment attName="+attName+", attUrl="+attUrl+"]";
	}

}
