package org.hyx.parse.model;

// startTime  endTime duty  content

//与正规工作经历不同的社会经验  ,社会实践

public class SocialExperience {
	private String startTime; // 开始时间
	private String endTime; // 结束时间
	private String socialtitle; // 标题 
	
	private String socialcontent; // 职责描述

 
	public String getSocialtitle() {
		return socialtitle;
	}

	public void setSocialtitle(String socialtitle) {
		this.socialtitle = socialtitle;
	}

	public String getSocialcontent() {
		return socialcontent;
	}

	public void setSocialcontent(String socialcontent) {
		this.socialcontent = socialcontent;
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

	public String toString(){
		
		return "SocialExperience  [startTime="+startTime+", endTime="+endTime+", socialtitle="+socialtitle+", socialcontent="+socialcontent+"]";
		
		
	}
}
