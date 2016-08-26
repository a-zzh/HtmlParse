package org.hyx.parse.model;

public class StudentsPractice {
// practiceTime   practiceName
	private String practiceTime;//时间
	private String practiceName;//职位
	private String practiceInfo;//信息

	


	public String getPracticeTime() {
		return practiceTime;
	}


	public void setPracticeTime(String practiceTime) {
		this.practiceTime = practiceTime;
	}


	public String getPracticeName() {
		return practiceName;
	}


	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}


	public String getPracticeInfo() {
		return practiceInfo;
	}


	public void setPracticeInfo(String practiceInfo) {
		this.practiceInfo = practiceInfo;
	}


	@Override
	public String toString() {
		return "StudentsPractice [practiceTime=" + practiceTime + ", practiceName=" + practiceName + ", practiceInfo=" + practiceInfo
         + "]";
	}
}
