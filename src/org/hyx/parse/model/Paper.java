package org.hyx.parse.model;
import java.util.Date;


public class Paper {

	private int id  ;
	private int candidateid  ;
	private int processStage	 ;
	private int flag  ;
	private float score ;
	private String time;//时间
	private String title;//标题
	private String author;//作者
	private String magazine;//期刊/会议
	private String info;//
	private String paperInfo;//其他信息
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMagazine() {
		return magazine;
	}
	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPaperInfo() {
		return paperInfo;
	}
	public void setPaperInfo(String paperInfo) {
		this.paperInfo = paperInfo;
	}
	@Override
	public String toString() {
		return "Paper [id=" + id + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", flag=" + flag
				+ ", score=" + score + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", time=" + time + ", title="
				+ title + ", author=" + author + ", magazine=" + magazine
				+ ", info=" + info + ", paperInfo=" + paperInfo + "]";
	}
	
}
