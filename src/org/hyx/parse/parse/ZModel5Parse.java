package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertPathValidatorException.Reason;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hyx.parse.model.Attachment;
import org.hyx.parse.model.Basic2;
import org.hyx.parse.model.Credential;
import org.hyx.parse.model.Education;
import org.hyx.parse.model.Honor;
import org.hyx.parse.model.Job;
import org.hyx.parse.model.Medal;
import org.hyx.parse.model.OtherInfor;
import org.hyx.parse.model.Project;
import org.hyx.parse.model.Skill;
import org.hyx.parse.model.SocialExperience;
import org.hyx.parse.model.StudentsPractice;
import org.hyx.parse.model.Train;
import org.hyx.parse.util.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZModel5Parse {

	public static String keyword;// 简历关键字

	public static String idcard;// 身份证号
	public static String name;// 姓名
	public static String gender;// 性别
	public static String title;// 职位
	public static String city;// 城市 居住地
	public static String workingyear;// 工作年限
	public static String education;// 学历
	public static String latteraddress;// 暂住地址
	public static String account;// 户口
	public static String phone;// 固定电话
	public static String mobile;// 手机
	public static String email;// 电子邮件
	public static String age;// 年龄
	public static String birthday;// 生日
	public static String maritalstatus;// 婚姻状况
	public static String height;// 身高

	public static String degree;// 学位
	public static String school;// 学校
	public static String major;// 专业
	public static String language;// 语言能力
	public static String courses;// 所学课程
	public static String zipCode;// 邮编
	public static String educationInfo;// 教育信息

	public static String dutytime;// 到岗时间_zzh
	public static String expectnaturework;// 期望工作性质
	public static String expectindustry;// 期望从事行业
	public static String currentSalary;// 目前薪资
	// public String currentJobYear; // 现从事职业年限_zzh

	public static String expectedCity;// 期望城市
	public static String expectedJob;// 期望职位
	public static String jobStatus;// 工作状态
	public static String expectedSalary;// 期望薪资
	public static String bonus;// 奖金

	public static String selfassessment;// 自我评价
	public static String politics;// 政治面貌

	public static String startTime;// 起始时间
	public static String endTime;// 结束时间
	public static String position;// 职位
	public static String salary;// 薪资
	public static String jobInfo = "";// 工作描述信息（其它关于工作描述的信息）
	public static String company;// 所在公司
	public static String department;// 所在部门
	public static String report;// 汇报人
	public static String member;// 管辖成员
	public static String duty;// 职责
	public static String jobIndustry;// 所属行业
	public static String jobReferences;// 证明人
	public static String achieve;// 工作业绩
	public static String duration; // 任职时长_zzh
	public static String reason;// 职责;// 职责

	public static String certificateTime;// 证书获得时间
	public static String certificate;// 证书名称
	public static String CredentialLevel;// 证书水准

	public static String projectName;// 项目名称
	public static String content;// 项目内容
	public static String proSofEn;// 软件环境
	public static String proHardEn;// 硬件环境
	public static String projectInfo;// 其他信息
	public static String proDevelopment;// 开发工具

	public static String medalName;// 奖项名称
	public static String medalInfo;// 奖项信息

	public static String skillName;// 技能名称
	public static String skillInfo;// 技能熟练程度
	public static String skilltime;// 使用时间

	public static String getTime;// 奖项时间
	public static String honorName;// 奖项名称
	public static String honorInfo;// 奖项信息
	public static String medalLevel;// 奖项级别（几等奖）

	public static String socialtitle;// 奖项级别（几等奖）
	public static String socialcontent;//

	public static String trainInstitutions;// 培训机构
	public static String trainClass;// 培训内容
	public static String trainInfo;// 培训信息

	public static String practiceTime;// 事件
	public static String practiceName;// 职务
	public static String practiceInfo;// 信息

	// attName attUrl attchment attMap
	public static String attName;// 信息
	public static String attUrl;// 信息

	public static String otherInfor;// 附加信息
	// practiceTime practiceName StudentsJob StudentsJobMap
	public static Map<String, Job> jobMap; // 工作经验
	public static Map<String, Credential> certificateMap; // 证书
	public static Map<String, Education> educationMap;// 教育经历
	public static Map<String, Project> projectmap; // 项目经理
	public static Map<String, Medal> medalMap; // 所获奖项
	public static Map<String, SocialExperience> socialMap; // 社会经验
	public static Map<String, Skill> skillMap; // 技能
	public static Map<String, Train> trainMap; // 培训经历
	public static Map<String, StudentsPractice> StudentsJobMap; // 校内职务
	public static Map<String, Attachment> attMap; // 培训经历

	public static Attachment attchment; // 附件
	public static StudentsPractice StudentsJob;// 校内职务
	public static Education educationModel;
	public static Project projectModel;
	public static Basic2 basicModel2;
	public static Credential certificateModel;// 证书
	public static Medal medalModel;// 学校获奖情况
	public static Skill skillModel;// 技能情况
	public static Train trainModel;// 培训经历
	public static SocialExperience socialEx;// 社会经历
	public static OtherInfor otherInfoModel;// 其他信息
	public static util utilClass;
	public static Elements eles;
	public static String eletext;
	public static Document doc;

	public ZModel5Parse() {

	}

	public static void parse(String path) {
		basicModel2 = new Basic2();
		jobMap = new LinkedHashMap<String, Job>(); // 工作经验
		certificateMap = new LinkedHashMap<String, Credential>(); // 证书
		educationMap = new LinkedHashMap<String, Education>();// 教育经历
		projectmap = new LinkedHashMap<String, Project>(); // 项目经理
		medalMap = new LinkedHashMap<String, Medal>(); // 所获奖项
		socialMap = new LinkedHashMap<String, SocialExperience>(); // 社会经验
		skillMap = new LinkedHashMap<String, Skill>(); // 技能
		trainMap = new LinkedHashMap<String, Train>(); // 培训经历
		StudentsJobMap = new LinkedHashMap<String, StudentsPractice>(); // 培训经历
		attMap = new LinkedHashMap<String, Attachment>(); // 培训经历
		otherInfoModel = new OtherInfor(); // 其他信息
		try {
			// System.out.println(path);
			File input = new File(path);// utf-8
			doc = Jsoup.parse(input, "utf-8");

			eles = doc.select("body>div[class=zpResumeS]");
			// System.out.println(eles.size());
			// System.exit(0);
			// begin 基本信息
			basicInfo(doc);

			String itemTitle = null;
			String itemCon = null;

			for (Element ele : doc.select("div[style=display:]")) {

				try {
					itemTitle = ele.select("div[class=itemTitle]").text();
					itemCon = ele.select("div[class=itemCon]").text();
					// System.out.println(itemTitle + "sssssssssssss");
					if (itemTitle.indexOf("求职意向") != -1) {
						jobIntension(ele);// ok

					} else if (itemTitle.indexOf("自我评价") != -1) {
						selfassessment = itemCon;
						basicModel2.setSelfassessment(selfassessment);

					} else if (itemTitle.indexOf("工作经历") != -1) {
						jobExperience(ele);// ok
					} else if (itemTitle.indexOf("教育经历") != -1) {
						educationContent(ele.select("div[class=itemCon]").get(0)); // ok

					} else if (itemTitle.indexOf("培训经历") != -1) {
						// trainExperience(ele);

					} else if (itemTitle.indexOf("证        书") != -1) {
						// System.out.println("证 书");
						credentialInfo(ele);

					} else if (itemTitle.indexOf("语言能力") != -1) {
						language = itemCon;
						basicModel2.setLanguage(language);

					} else if (itemTitle.indexOf("专业技能") != -1) {
						proSkill(ele);

					} else if (itemTitle.indexOf("兴趣爱好") != -1) {
						// proSkill(ele);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * title company
	 * 
	 * 最高学历
	 */
	public static void education(Element ele) {
		company = null;
		title = null;
		education = null;
		school = null;
		try {

			String str = null;
			for (Element el : ele.select("td[width=45%]>table>tbody>tr")) {
				str = el.text();
				if (str.contains("公　司")) {
					company = str.split("：")[1];
				} else if (str.contains("行　业")) {
					// company = str.split("：")[1];
				} else if (str.contains("职　位")) {
					title = str.split("：")[1];
				}
			}
			for (Element el : ele.select("td[width=49%]>table>tbody>tr")) {
				str = el.text();
				if (str.contains("学　历")) {
					education = str.split("：")[1];
				} else if (str.contains("专　业")) {
					// company = str.split("：")[1];
				} else if (str.contains("学　校")) {
					school = str.split("：")[1];
				}
			}

			basicModel2.setEducation(education);
			basicModel2.setCompany(company);
			basicModel2.setTitle(title);

		} catch (ArrayIndexOutOfBoundsException e) {

		} finally {
			basicModel2.setEducation(education);
			basicModel2.setCompany(company);
			basicModel2.setTitle(title);

		}

	}

	/*
	 * 证书map certificateTime certificate CredentialLevel certificateMap
	 * certificateModel Credential
	 */
	public static void credentialInfo(Element ele) {
		// TODO Auto-generated method stub
		int i = 0;

		for (Element el : ele.select("tbody>tr")) {
			try {

				certificateModel = new Credential();
				if (el.text().indexOf("/") != -1) {
					String str = el.select("td[width=1%]").text().trim();
					certificateTime = str.substring(0, str.indexOf("/") + 3);
					certificate = el.select("td[style=word-break:break-all;word-wrap:break-word]").text();

					certificateModel.setCertificateTime(certificateTime);
					certificateModel.setCertificate(certificate);
					certificateMap.put((i++) + "", certificateModel);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/*
	 * 获取奖励信息
	 * 
	 */
	/**
	 * medalMap medalModel getTime medalName medalLevel
	 */
	public static void medalInfo(Element ele) {
		String temp = null;
		int j = 0;
		for (Element el : ele.select("tbody>tr")) {
			if (!el.text().equals("")) {

				medalModel = new Medal();
				int i = 0;
				for (Element el2 : el.select("td[width=33%]")) {
					temp = el2.text();
					if (i == 0)
						getTime = temp;
					if (i == 1)
						medalName = temp;
					if (i == 2)
						medalLevel = temp;
					i++;
				}

				medalModel.setGetTime(getTime);
				medalModel.setMedalName(medalName);
				medalModel.setMedalLevel(medalLevel);
				medalMap.put((j++) + "", medalModel);

			}
		}
		// System.exit(0);
	}

	/*
	 * 获取基本信息
	 * 
	 */
	public static void basicInfo(Document doc) {
		// div[class=zpResumeS]>div[class=box]
		name = doc.select("div[class=name]").text();
		email = doc.select("div[class=baseinfo]>a").text();
		String basicInfo = doc.select("div[class=baseinfo]").text();

		Pattern p = Pattern.compile("\\d{15,18}");
		Matcher m = p.matcher(basicInfo);
		if (m.find())
			idcard = m.group();

		String[] str = basicInfo.split("\\|");

		// 男 | 已婚 | 1965 年5月生 | 户口：江苏南京 | 现居住于江苏南京 10年以上工作经验 | 身份证：
		// 320106650524043 江苏省南京市玄武区 210042 13770750888(手机)
		// E-mail:leejiqing@yahoo.com.cn

		String s = null;
		for (int i = 0; i < str.length; i++) {
			s = str[i].trim();
			if (s.indexOf("男") != -1 || str[i].indexOf("女") != -1) {
				gender = s.trim();
			} else if (s.indexOf("户口") != -1) {
				account = s.split("：")[1];

			} else if (s.indexOf("居住于") != -1) {

				if (s.indexOf("工作经验") != -1) {

					workingyear = s = s.substring(s.indexOf(" "), s.indexOf("工作经验") + 4);
				}
			} else if (s.indexOf("手机") != -1) {
				mobile = s.substring(s.indexOf("(") - 11, s.indexOf("("));
			} else if (s.indexOf("年") != -1 && s.indexOf("月") != -1) {
				birthday = s;
			} else if (s.indexOf("婚") != -1) {
				maritalstatus = s;
			}

		}
		basicModel2.setName(name);
		basicModel2.setAccount(account);
		basicModel2.setGender(gender);
		basicModel2.setLatteraddress(latteraddress);
		basicModel2.setWorkingyear(workingyear);
		basicModel2.setMaritalstatus(maritalstatus);
		basicModel2.setIdcard(idcard);
		basicModel2.setMobile(mobile);

	}

	/**
	 * 语言能力
	 * 
	 * @param ele
	 */
	public static void language(Element ele) {
		language = ele.text();
		basicModel2.setLanguage(language);
		// System.out.println(language);
		// System.exit(0);
	}

	/**
	 * 求职意向
	 * 
	 * @param ele
	 */
	public static void jobIntension(Element ele) {
		// 求职意向信息 class="table_set"
		String str = null;
		String str2 = null;
		for (Element el : ele.select("table>tbody>tr")) {
			if (el.text().trim().equals(""))
				continue;
			else
				try {
					// System.out.println(el.text());
					str = el.text().split("：")[0];
					str2 = el.text().split("：")[1];

					if (str.contains("工作性质")) {
						expectnaturework = str2;
						basicModel2.setExpectnaturework(expectnaturework);
					} else if (str.indexOf("从事职业") != -1) {
						expectedJob = str2;
						basicModel2.setExpectedJob(expectedJob);
					} else if (str.indexOf("从事行业") != -1) {
						expectindustry = str2;
						basicModel2.setExpectindustry(expectindustry);
					} else if (str.indexOf("工作地区") != -1) {
						expectedCity = str2;
						basicModel2.setExpectedCity(expectedCity);
					} else if (str.indexOf("期望月薪") != -1) {
						expectedSalary = str2;
						basicModel2.setExpectedSalary(expectedSalary);
					} else if (str.indexOf("目前状况") != -1) {
						jobStatus = str2;
						basicModel2.setJobStatus(jobStatus);
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
		}

	}

	/**
	 * 工作经历
	 * 
	 * @param ele
	 */

	/*
	 * jobMap 51 startTime endTime company duration jobIndustry department
	 * position
	 * 
	 * jobInfo
	 **/
	public static void jobExperience(Element ele) {
		String from, to, str = null;
		startTime = null;
		endTime = null;
		company = null;
		duration = null;
		int i = 0, j = 0;

		for (Element el : ele.select("tbody>tr")) {

			try {
				str = el.select("td[width=1%]").text();
				if (!str.trim().equals("")) {
					startTime = str.split("-")[0];
					endTime = str.split("-")[2].split("：")[0];
				}

				// 两种处理方法

				el.select("td>br").wrap("<li>;</li>");

				Job job = new Job();
				str = el.select("td[class=line150]").text();
				if (str.trim().equals("")) {
					continue;
				} else {
					String[] str2 = str.split(";");

					String[] str30 = str2[0].split("\\|");
					for (int ii = 0; ii < str30.length; ii++) {
						if (ii == 0) {
							company = str30[0];
						} else if (ii == 1) {
							department = str30[1];
						} else if (ii == 2) {
							position = str30[2];
						}

					}

					String[] str31 = str2[1].split("\\|");
					jobIndustry = str31[0];
					for (String ss : str31) {
						if (ss.indexOf("/月") != -1)
							salary = ss;
					}

					for (int k = 2; k < str2.length; k++) {
						jobInfo += str2[k];
					}

				}
				job.setStartTime(startTime);
				job.setEndTime(endTime);
				job.setCompany(company);
				job.setDepartment(department);
				job.setPosition(position);
				job.setJobIndustry(jobIndustry);
				job.setSalary(salary);
				job.setJobInfo(jobInfo);
				jobMap.put((j++) + "", job);
				jobInfo = "";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 教育信息
	 */
	/**
	 * educationMap
	 * 
	 * educationModel
	 * 
	 * startTime endTime school major degree educationInfo
	 * 
	 */

	public static void educationContent(Element ele) {

		int i = 0;
		startTime = "";
		endTime = "";
		String[] str1, str2;

		ele.select("div[class=itemCon]>br").wrap("<li>;</li>");

		for (String ss : ele.text().split(";")) {
			educationModel = new Education();
			try {
				if (ss.trim().equals(""))
					continue;
				else {
					str1 = ss.split("：")[0].split("-");
					str2 = ss.split("：")[1].split("\\|");

					for (int ii = 0; ii < str1.length; ii++) {
						if (ii == 0) {
							startTime = str1[0];
						} else if (ii == 1) {
							endTime = str1[2];
						}
					}
					for (int ii = 0; ii < str2.length; ii++) {
						if (ii == 0) {
							school = str2[0];
						} else if (ii == 1) {
							major = str2[1];
						} else if (ii == 2) {
							degree = str2[2];
						}
					}

					if (i == 0) {
						basicModel2.setDegree(degree);
						basicModel2.setMajor(major);
						basicModel2.setSchool(school);

					}

					// System.out.println(startTime);
					educationModel.setStartTime(startTime);
					educationModel.setEndTime(endTime);
					educationModel.setSchool(school);
					educationModel.setDegree(degree);
					educationModel.setMajor(major);

					educationMap.put((i++) + "", educationModel);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 项目经历 startTime endTime company proHardEn/proSofEn proDevelopment content
	 * duty
	 */

	public static void projectExperience(Element ele) {
		startTime = null;
		endTime = null;
		company = null;
		projectName = null;
		proHardEn = null;
		proSofEn = null;
		proDevelopment = null;
		content = null;
		duty = null;

		int i = 0;
		for (Element el : ele.select("tbody>tr")) {
			try {
				projectModel = new Project();
				String str = el.text();
				if (str.trim().equals("")) {
					continue;
				} else {
					if (str.contains("--") && str.length() < 100) { //
						startTime = str.split("-")[0];
						endTime = str.split("-")[2].split("：")[0];
						projectName = str.split("：")[1];
					} else if (str.contains("硬件环境")) {
						proHardEn = str.split("：")[1];
					} else if (str.contains("软件环境")) {
						proSofEn = str.split("：")[1];
					} else if (str.contains("开发工具")) {
						proDevelopment = str.split("：")[1];
					} else if (str.contains("项目描述")) {
						content = str.split("：")[1];
					} else if (str.contains("责任描述：")) {
						duty = str.split("：")[1];

						projectModel.setStartTime(startTime);
						projectModel.setEndTime(endTime);
						// projectModel.setCompany(company);
						projectModel.setProjectName(projectName);
						projectModel.setProHardEn(proHardEn);
						projectModel.setProHardEn(proHardEn);
						projectModel.setProDevelopment(proDevelopment);
						projectModel.setContent(content);
						projectModel.setDuty(duty);

						projectmap.put((i++) + "", projectModel);

					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}

	}

	/**
	 * 专业技能 //skillName skillInfo skilltime skillMap skillModel
	 * 
	 * @param ele
	 */
	public static void proSkill(Element ele) {
		int i = 0, j;
		for (Element el : ele.select("div[class=itemCon line150]")) {
			try {
				skillModel = new Skill();
				String[] str = el.text().split("\\|");
				skillName = str[0];
				skillInfo = str[1];
				skilltime = str[2];

				skillModel.setSkillName(skillName);
				skillModel.setSkillInfo(skillInfo);
				skillModel.setTime(skilltime);
				skillMap.put(i + "", skillModel);
				i++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 培训经历 // trainInstitutions trainClass trainInfo startTime endTime trainMap
	 * trainModel
	 * 
	 * @param ele
	 */
	public static void trainExperience(Element ele) {
		startTime = null;
		endTime = null;

		int j = 0;
		for (Element el : ele.select("tbody>tr")) {
			trainModel = new Train();
			String str = el.text();
			if (!str.equals("")) {
				if (str.contains("--") && str.length() < 200) {
					try {
						startTime = el.select("td[width=20%]").text().split("-")[0];
						endTime = el.select("td[width=20%]").text().split("-")[2].replace("：", "");
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					int i = 0;
					for (Element el2 : el.select("td[width=29%]")) {
						if (i == 0) {
							trainInstitutions = el2.text();
						} else
							trainInfo += el2.text() + " ";

						i++;
					}

					if (!ele.select("td").toString().contains("<td colspan=4 class=text_left>")) {
						trainModel.setStartTime(startTime);
						trainModel.setEndTime(endTime);
						trainModel.setTrainInstitutions(trainInstitutions);
						trainModel.setTrainInfo(trainInfo);
						trainMap.put((j++) + "", trainModel);
						trainInfo = "";
					}

				} else {
					str = el.select("td[colspan=4]").text();
					if (!str.equals("")) {
						trainClass = str;
						trainModel.setStartTime(startTime);
						trainModel.setEndTime(endTime);
						trainModel.setTrainClass(trainClass);
						trainModel.setTrainInstitutions(trainInstitutions);
						trainModel.setTrainInfo(trainInfo);
						trainMap.put((j++) + "", trainModel);
						trainInfo = "";
					}

				}

			}

		}

	}

	/**
	 * 附加信息
	 * 
	 * @param ele
	 */
	public static void addInformation(Element ele) {
		otherInfor = ele.text();
		otherInfoModel.setOtherInfor(otherInfor);
	}

	public static void main(String[] args) throws IOException {

		String path = "C:/Users/gongcaichun/Desktop/html额外/未分类/898/郑坤 139438233_cn.htm";
		parse(path);
		// System.out.println("-----------------------------------------------------------------------------");

		System.out.println("工作经验  " + jobMap.values());
		System.out.println("证书   " + certificateMap.values());
		System.out.println("教育经历  " + educationMap.values());
		System.out.println("项目经验  " + projectmap.values());
		System.out.println("所获奖励  " + medalMap.values());
		System.out.println("社会经验  " + socialMap.values());
		System.out.println("技能  " + skillMap.values());
		System.out.println("培训信息  " + trainMap.values());
		System.out.println("校内职务  " + StudentsJobMap.values());
		System.out.println("附件  " + attMap.values());
		System.out.println("基本信息  " + basicModel2.toString());
		for (Job job : jobMap.values()) {
			System.out.println(job.toString());
		}

	}
}
