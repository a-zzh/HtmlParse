package org.hyx.parse.model;
import java.util.Date;


public class Job {
	private int id;
	private int candidateid;
	private int processStage;
	private int flag;
	private float score;
	private String startTime;//起始时间
	private String endTime;//结束时间
	private String company;//所在公司
	private String department;//所在部门
	private String position;//职位
	private String report;//汇报人
	private String salary;//薪资
	private String member;//管辖成员
	private String duty;//职责
	private String achieve;//成果
	private String reason;//离职原因
	private String city;//所在城市
	private String info;
	private String jobInfo;//工作描述信息（其它关于工作描述的信息）
	private String jobIndustry;//所属行业
	private String jobReferences;//证明人
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
