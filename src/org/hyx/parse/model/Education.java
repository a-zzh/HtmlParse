package org.hyx.parse.model;

import java.util.Date;

public class Education {

	private int candidateid;
	private int processStage;

	private int flag;//
	private float score;//
	private String degree;// 学位
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String school;// 学校
	private String major;// 专业
	private String info;//
	private String educationInfo;// 其他信息
	private String courses;// 所学课程
	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEducationInfo() {
		return educationInfo;
	}

	public void setEducationInfo(String educationInfo) {
		this.educationInfo = educationInfo;
	}

	@Override
	public String toString() {
		return "Education [createTime=" + createTime + ", updateTime="
				+ updateTime + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", degree=" + degree
				+ ", flag=" + flag + ", score=" + score + ", startTime="
				+ startTime + ", endTime=" + endTime + ", school=" + school
				+ ", major=" + major + ", info=" + info+ ", courses=" + courses
				+ ", educationInfo="+ educationInfo + "]";
		
	}

}
