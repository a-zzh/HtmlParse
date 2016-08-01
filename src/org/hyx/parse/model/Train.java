package org.hyx.parse.model;

public class Train {

	private String trainInstitutions;// 培训机构
	private String trainClass;// 培训内容
	private String trainInfo;// 培训信息
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	
	public String getTrainInstitutions() {
		return trainInstitutions;
	}
	public void setTrainInstitutions(String trainInstitutions) {
		this.trainInstitutions = trainInstitutions;
	}
	public String getTrainClass() {
		return trainClass;
	}
	public void setTrainClass(String trainClass) {
		this.trainClass = trainClass;
	}
	public String getTrainInfo() {
		return trainInfo;
	}
	public void setTrainInfo(String trainInfo) {
		this.trainInfo = trainInfo;
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
	
	@Override
	public String toString() {
		return "Skill [trainInstitutions=" + trainInstitutions + ", trainClass=" + trainClass
				+ ", trainInfo=" + trainInfo + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}
