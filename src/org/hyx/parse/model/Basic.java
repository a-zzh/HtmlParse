package org.hyx.parse.model;

import java.util.Date;

public class Basic {
	private int id;
	private int candidateId;
	private int processStage;
	private int gender;//性别
	private int cardType;//证件类型
	private int marriage;//婚姻状况
	
	
	private int tradeId;//行业
	private int functionId;//职能
	private int mpc;//是否是最合适的候选人
	private int photoId;//
	private int flag;//
	private String  expectnaturework;// 期望工作性质
	private String  expectindustry;// 期望从事行业
	private String  currentSalary;//目前薪资
	private String  expectedCity;//期望城市
	private String  expectedJob;//期望职位
	private String  jobStatus;//工作状态
	private String  expectedSalary;//期望薪资
	private String  name ;//姓名
	private String  usedName;//曾用名
	private String  englishName;//英文名
	private String  idCard;//身份证号
	private String  company;//公司
	private String  title;//职位
	private String  city;//城市
	private String  birthPlace;//出生地
	private String  account;//户口
	private String  phone;//固定电话
	private String  mobile;//手机
	private String  email;//电子邮件
	private String  age;//年龄
	private String  birthday;//生日
	private String  major;//专业
	private String  degree;//学位
	private String  school;//学校
	private String  jobTime;//工作时间
	private String  height;//身高
	private String  weight;//体重
	private String  bwh;//三围
	private String  nationality;//国籍
	private String  country;//国家
	private String  health;//健康状况
	private String  politics;//政治面貌
	private String  weixin;//微信
	private String  qq;//QQ
	private String  linkedIn;//领英
	private String  dajie;//大街
	private String  weibo;//微博
	private String  address;//地址
	private String  comment;//自我评价
	private String  preference;//
	private String  basicInfo;//其它基本信息
	private int  fileid;
	private int  ownerid;
	private int  company_id;
	private Date  createTime;
	private Date  updateTime;
	private Long job_time;
		
		public Long getJob_time() {
			return job_time;
		}
		public void setJob_time(Long job_time) {
			this.job_time = job_time;
		}
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
		
		public int getFileid() {
			return fileid;
		}
		public void setFileid(int fileid) {
			this.fileid = fileid;
		}
		public int getOwnerid() {
			return ownerid;
		}
		public void setOwnerid(int ownerid) {
			this.ownerid = ownerid;
		}
		public int getCompany_id() {
			return company_id;
		}
		public void setCompany_id(int company_id) {
			this.company_id = company_id;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCandidateId() {
			return candidateId;
		}
		public void setCandidateId(int candidateId) {
			this.candidateId = candidateId;
		}
		public int getProcessStage() {
			return processStage;
		}
		public void setProcessStage(int processStage) {
			this.processStage = processStage;
		}
		public int getGender() {
			return gender;
		}
		public void setGender(int gender) {
			this.gender = gender;
		}
		public int getCardType() {
			return cardType;
		}
		public void setCardType(int cardType) {
			this.cardType = cardType;
		}
		public int getMarriage() {
			return marriage;
		}
		public void setMarriage(int marriage) {
			this.marriage = marriage;
		}
		
		public String getExpectnaturework() {
			return expectnaturework;
		}
		public void setExpectnaturework(String expectnaturework) {
			this.expectnaturework = expectnaturework;
		}
		
		public String getExpectoccupation() {
			return jobStatus;
		}
		public String getExpectindustry() {
			return expectindustry;
		}
		public void setExpectindustry(String expectindustry) {
			this.expectindustry = expectindustry;
		}
		public String getJobStatus() {
			return jobStatus;
		}
		public void setJobStatus(String jobStatus) {
			this.jobStatus = jobStatus;
		}
		public String getExpectedSalary() {
			return expectedSalary;
		}
		public void setExpectedSalary(String expectsalary) {
			this.expectedSalary = expectsalary;
		}
		public int getTradeId() {
			return tradeId;
		}
		public void setTradeId(int tradeId) {
			this.tradeId = tradeId;
		}
		public int getFunctionId() {
			return functionId;
		}
		public void setFunctionId(int functionId) {
			this.functionId = functionId;
		}
		public int getMpc() {
			return mpc;
		}
		public void setMpc(int mpc) {
			this.mpc = mpc;
		}
		public int getPhotoId() {
			return photoId;
		}
		public void setPhotoId(int photoId) {
			this.photoId = photoId;
		}
		public int getFlag() {
			return flag;
		}
		public void setFlag(int flag) {
			this.flag = flag;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUsedName() {
			return usedName;
		}
		public void setUsedName(String usedName) {
			this.usedName = usedName;
		}
		public String getEnglishName() {
			return englishName;
		}
		public void setEnglishName(String englishName) {
			this.englishName = englishName;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getBirthPlace() {
			return birthPlace;
		}
		public void setBirthPlace(String birthPlace) {
			this.birthPlace = birthPlace;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getMajor() {
			return major;
		}
		public void setMajor(String major) {
			this.major = major;
		}
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getSchool() {
			return school;
		}
		public void setSchool(String school) {
			this.school = school;
		}
		public String getJobTime() {
			return jobTime;
		}
		public void setJobTime(String jobTime) {
			this.jobTime = jobTime;
		}
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		public String getWeight() {
			return weight;
		}
		public void setWeight(String weight) {
			this.weight = weight;
		}
		public String getBwh() {
			return bwh;
		}
		public void setBwh(String bwh) {
			this.bwh = bwh;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getHealth() {
			return health;
		}
		public void setHealth(String health) {
			this.health = health;
		}
		public String getPolitics() {
			return politics;
		}
		public void setPolitics(String politics) {
			this.politics = politics;
		}
		public String getCurrentSalary() {
			return currentSalary;
		}
		public void setCurrentSalary(String currentSalary) {
			this.currentSalary = currentSalary;
		}
		public String getExpectedCity() {
			return expectedCity;
		}
		public void setExpectedCity(String expectedCity) {
			this.expectedCity = expectedCity;
		}
		public String getExpectedJob() {
			return expectedJob;
		}
		public void setExpectedJob(String expectedJob) {
			this.expectedJob = expectedJob;
		}
		public String getWeixin() {
			return weixin;
		}
		public void setWeixin(String weixin) {
			this.weixin = weixin;
		}
		public String getQq() {
			return qq;
		}
		public void setQq(String qq) {
			this.qq = qq;
		}
		public String getLinkedIn() {
			return linkedIn;
		}
		public void setLinkedIn(String linkedIn) {
			this.linkedIn = linkedIn;
		}
		public String getDajie() {
			return dajie;
		}
		public void setDajie(String dajie) {
			this.dajie = dajie;
		}
		public String getWeibo() {
			return weibo;
		}
		public void setWeibo(String weibo) {
			this.weibo = weibo;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getPreference() {
			return preference;
		}
		public void setPreference(String preference) {
			this.preference = preference;
		}
		public String getBasicInfo() {
			return basicInfo;
		}
		public void setBasicInfo(String basicInfo) {
			this.basicInfo = basicInfo;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
	
		@Override
		public String toString() {
			return "Basic [id=" + id + ", candidateId=" + candidateId
					+ ", processStage=" + processStage + ", gender=" + gender
					+ ", cardType=" + cardType + ", marriage=" + marriage
					+ ", jobStatus=" + jobStatus + ", expectedSalary="
					+ expectedSalary + ", tradeId=" + tradeId + ", functionId="
					+ functionId + ", mpc=" + mpc + ", photoId=" + photoId
					+ ", flag=" + flag + ", name=" + name + ", usedName="
					+ usedName + ", englishName=" + englishName + ", idCard="
					+ idCard + ", company=" + company + ", title=" + title
					+ ", city=" + city + ", birthPlace=" + birthPlace+",account"+account
					+ ", phone=" + phone + ", mobile=" + mobile + ", email="
					+ email + ", age=" + age + ", birthday=" + birthday
					+ ", major=" + major + ", degree=" + degree + ", school="
					+ school + ", jobTime=" + jobTime + ", height=" + height
					+ ", weight=" + weight + ", bwh=" + bwh + ", nationality="
					+ nationality + ", country=" + country + ", health="
					+ health + ", politics=" + politics + ", currentSalary="
					+ currentSalary + ", expectedCity=" + expectedCity
					+ ", expectedJob=" + expectedJob + ", weixin=" + weixin
					+ ", qq=" + qq + ", linkedIn=" + linkedIn + ", dajie="
					+ dajie + ", weibo=" + weibo + ", address=" + address
					+ ", comment=" + comment + ", preference=" + preference
					+ ", basicInfo=" + basicInfo + ", createTime=" + createTime
					+ ", updateTime=" + updateTime + "]";
		}
		
		
}
