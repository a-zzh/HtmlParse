package org.hyx.parse.model;
import java.util.Date;


public class Patent {
	
	private int id  ;
	private int candidateid  ;
	private int processStage	 ;
	private int flag  ;
	private float score ;
	private String time;//时间
	private String patentName	;//专利名称
	//private String name	;
	private String inventor;//发明人
	private String patentId;//专利号            
	private String info;//
	private String patentInfo;//其他信息
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

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getInventor() {
		return inventor;
	}
	public void setInventor(String inventor) {
		this.inventor = inventor;
	}
	public String getPatentId() {
		return patentId;
	}
	public void setPatentId(String patentId) {
		this.patentId = patentId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPatentInfo() {
		return patentInfo;
	}
	public void setPatentInfo(String patentInfo) {
		this.patentInfo = patentInfo;
	}
	@Override
	public String toString() {
		return "Patent [id=" + id + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", flag=" + flag
				+ ", score=" + score + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", time=" + time
				+ ", patentName=" + patentName + ", inventor=" + inventor
				+ ", patentId=" + patentId + ", info=" + info + ", patentInfo="
				+ patentInfo + "]";
	}
	
}
