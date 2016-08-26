package org.hyx.parse.model;
import java.util.Date;

/*
 * 51
 * startTime  endTime   company  duration  
 *  jobIndustry  
 *  department
 *  
 *  jobInfo
 * */
public class Job {
	private int id=0;
	private int candidateid=0;
	private int processStage=0;
	private int flag=0;
	private float score=0;
	private String startTime=null;//起始时间
	private String endTime=null;//结束时间
	private String duration=null;  //任职时长_zzh 	 
	private String company=null;//所在公司
	private String department=null;//所在部门
	private String position=null;//职位
	private String report=null;//汇报人
	private String salary=null;//薪资
	private String member=null;//管辖成员,下属人数
	private String duty=null;//职责
	private String achieve=null;//成果
	private String reason=null;//离职原因
	private String city=null;//所在城市
	private String info=null;//其他信息
	private String jobInfo=null;//工作描述信息（其它关于工作描述的信息）
	private String jobIndustry=null;//所属行业
	private String jobReferences=null;//证明人
	 
	
	
	
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getJobReferences() {
		return jobReferences;
	}
	public void setJobReferences(String jobReferences) {
		this.jobReferences = jobReferences;
	}
	public String getJobIndustry() {
		return jobIndustry;
	}
	public void setJobIndustry(String jobIndustry) {
		this.jobIndustry = jobIndustry;
	}
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getJobInfo() {
		return jobInfo;
	}
	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", candidateid=" + candidateid
				+ ", processStage=" + processStage + ", flag=" + flag
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", score=" + score + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", company=" + company
				+ ", department=" + department + ", position=" + position
				+ ", report=" + report + ", salary=" + salary + ", member="
				+ member + ", duty=" + duty + ", achieve=" + achieve
				+ ", reason=" + reason + ", city=" + city + ", info=" + info
				+ ", jobInfo=" + jobInfo+", jobIndustry=" + jobIndustry 
				+", jobReferences=" + jobReferences
				+ "]";
		
		
	}
	
}
