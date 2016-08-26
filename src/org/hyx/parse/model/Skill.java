package org.hyx.parse.model;

import java.util.Date;
//skillName  skillInfo  time
public class Skill {
	private int id;
	private int candidateid;
	private int processStage;
	private int flag;
	private float score;
	private String skillName="";// 技能名称
	private String info;//
	private String skillInfo;// 技能熟练程度
	private String time;// 使用时间
	private Date createTime;
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

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

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSkillInfo() {
		return skillInfo;
	}

	public void setSkillInfo(String skillInfo) {
		this.skillInfo = skillInfo;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", flag=" + flag
				+ ", score=" + score + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", skillName=" + skillName
				+ ", info=" + info + ", skillInfo=" + skillInfo 
				+ ", time=" + time+ "]";
	}

}
