package org.hyx.parse.model;

import java.util.Date;

public class Project {
	private int id;
	private int candidateid;
	private int processStage;
	private int flag;
	private float score;
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String projectName;// 项目名称
	private String company;// 所在公司
	private String content;// 项目内容
	private String duty;// 项目职责
	private String achieve;// 项目成果
	private String city;// 所在城市
	private String info;//
	private String projectInfo;// 其他信息
	private String proSofEn;// 软件环境
	private String proHardEn;// 硬件环境
	private String proDevelopment;// 开发工具

	public String getProDevelopment() {
		return proDevelopment;
	}

	public void setProDevelopment(String proDevelopment) {
		this.proDevelopment = proDevelopment;
	}

	public String getProSofEn() {
		return proSofEn;
	}

	public void setProSofEn(String proSofEn) {
		this.proSofEn = proSofEn;
	}

	public String getProHardEn() {
		return proHardEn;
	}

	public void setProHardEn(String proHardEn) {
		this.proHardEn = proHardEn;
	}

	private Date createTime;
	private Date updateTime;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAchieve() {
		return achieve;
	}

	public void setAchieve(String achieve) {
		this.achieve = achieve;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", flag=" + flag
				+ ", score=" + score + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", projectName=" + projectName
				+ ", company=" + company + ", content=" + content + ", duty="
				+ duty + ", achieve=" + achieve + ", city=" + city + ", info="
				+ info + ", projectInfo=" + projectInfo + ", proSofEn="
				+ proSofEn + ", proHardEn=" + proHardEn + ", proDevelopment="
				+ proDevelopment + "]";

	}

}
