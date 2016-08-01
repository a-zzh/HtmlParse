package org.hyx.parse.model;
import java.util.Date;


public class Medal {
	private int id  ;
	private int candidateid  ;
	private int processStage	 ;
	private int flag  ;
	private float score ;
	private String getTime;//时间
	private String medalName;//奖项名称
	private String info;//
	private String medalInfo;//奖项信息
	private Date  createTime;
	private Date  updateTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCandidateid() {
		return candidateid;
	}
	public void setCandidateid(int candidateid) {
		this.candidateid = candidateid;
	}
	public int getProcessStage() {
		return processStage;
	}
	public void setProcessStage(int processStage) {
		this.processStage = processStage;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getMedalName() {
		return medalName;
	}
	public void setMedalName(String medalName) {
		this.medalName = medalName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMedalInfo() {
		return medalInfo;
	}
	public void setMedalInfo(String medalInfo) {
		this.medalInfo = medalInfo;
	}
	@Override
	public String toString() {
		return "Medal [id=" + id + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", flag=" + flag
				+ ", score=" + score + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", getTime=" + getTime
				+ ", medalName=" + medalName + ", info=" + info
				+ ", medalInfo=" + medalInfo + "]";
	}
	
}
