package org.hyx.parse.model;

public class Credential {  //certificateTime   certificate   CredentialLevel

	private  String certificateTime;//证书获得时间
	private  String certificate;//证书名称
	private  String CredentialLevel;
	
	
	 
	public String getCredentialLevel() {
		return CredentialLevel;
	}
	public void setCredentialLevel(String credentialLevel) {
		CredentialLevel = credentialLevel;
	}
	public String getCertificateTime() {
		return certificateTime;
	}
	public void setCertificateTime(String certificateTime) {
		this.certificateTime = certificateTime;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	public String toString() {
		return "Credential ["+"certificateTime=" + certificateTime + ", certificate=" + certificate+", CredentialLevel=" + CredentialLevel+"]";
	}
}
