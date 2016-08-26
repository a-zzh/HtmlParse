package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.cert.CertPathValidatorException.Reason;
import java.util.LinkedHashMap;
import java.util.Map;

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

public class UModel7Parse {

	public static String keyword;// 简历关键字

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
	public static String jobInfo;// 工作描述信息（其它关于工作描述的信息）
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

	public UModel7Parse() {

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
			File input = new File(path);
			doc = Jsoup.parse(input, "gbk");

			String str = null, str2 = null;
			for (Element ele : doc.select("div[class=ResumeView]>div")) {
				// System.out.println(ele);
				str = ele.select("div.itemName").text();
				name = ele.select("div[class=flt_l]").text();

				if (!name.equals("")) {
					basicModel2.setName(name);
				} else if (!ele.select("div[class=Resumekey]").text().equals("")) {
					basicInfo(ele);

				} else if (str.indexOf("基本资料") != -1) {

					basicInfo2(ele);
				} else if (str.indexOf("评价") != -1) {
					selfassessment = ele.select("div.ContentBox").text();
					basicModel2.setSelfassessment(selfassessment);

				} else if (str.indexOf("职业技能") != -1) {
					proSkill(ele);

				} else if (str.indexOf("求职") != -1) {
					jobIntension(ele);

				} else if (str.indexOf("工作") != -1) {
					jobExperience(ele);
				} else if (str.indexOf("项目") != -1) {
					projectExperience(ele);
				} else if (str.indexOf("教育背景") != -1) {
					educationContent(ele);
				} else if (str.indexOf("语言") != -1) {
					language = ele.text();
					basicModel2.setLanguage(language);
				} else if (str.indexOf("培训") != -1) {
					//
				} else if (str.indexOf("IT技能") != -1) {
					skillInfo(ele);
				} else if (str.indexOf("证书") != -1) {

					credentialInfo(ele);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void skillInfo(Element ele) {
		String str = null;
		int i = 0;
		for (Element el : ele.select("dl>dd")) {
			try {
				skillModel = new Skill();
				str = el.text();

				skillName = str.split("\\[")[0];
				skillInfo = str.split("\\[")[1].split("，")[0];
				skilltime = str.split("，")[1].split("\\]")[0];
				skillModel.setSkillName(skillName);
				skillModel.setSkillInfo(skillInfo);
				skillModel.setTime(skilltime);
				skillMap.put((i++) + "", skillModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void basicInfo2(Element ele) {
		try {
			int i = 0, j = 0;
			String str, str2;
			for (Element el : ele.select("dl>dt")) {
				// System.out.println(el);
				j = 0;
				for (Element el2 : ele.select("dl>dd")) {

					if (i == j) {
						str = el.text();
						str2 = el2.text();
						//System.out.println(str + " +++++++" + str2);
						if (str.indexOf("手机") != -1) {
							mobile = str2;
							basicModel2.setMobile(mobile);
						} else if (str.indexOf("邮箱") != -1) {

							email = str2;
							basicModel2.setEmail(email);

						} else if (str.indexOf("城市") != -1) {
							city = str2;
							basicModel2.setCity(city);

						} else if (str.indexOf("地址") != -1) {
							latteraddress = str2;
							basicModel2.setLatteraddress(latteraddress);

						} else if (str.indexOf("状态") != -1) {

							jobStatus = str2;
							basicModel2.setJobStatus(jobStatus);
						} else if (str.indexOf("出生日期") != -1) {
							birthday = str2;
							basicModel2.setBirth(birthday);
						} else if (str.indexOf("户口所在地") != -1) {
							account = str2;
							basicModel2.setAccount(account);

						} else if (str.indexOf("政治面貌") != -1) {
							politics = str2;
							basicModel2.setPolitics(politics);

						} else if (str.indexOf("国籍") != -1) {
							// 暂无

						} else if (str.indexOf("婚姻状况") != -1) {

							maritalstatus = str2;
							basicModel2.setMaritalstatus(maritalstatus);

						} else if (str.indexOf("民族") != -1) {

							// 暂无
						} else if (str.indexOf("毕业时间") != -1) {
							//

						} else if (str.indexOf("个人主页") != -1) {
							//

						}

					}
					j++;
				}
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 证书map certificateTime certificate CredentialLevel certificateMap
	 * certificateModel Credential
	 */
	public static void credentialInfo(Element ele) {

		int i = 0;
		for (Element el : ele.select("div.itemTit")) {
			try {
				certificateModel = new Credential();
				certificateTime = el.select("div.titc1").text();
				certificate = el.select("div.titc2").text();
				certificateModel.setCertificateTime(certificateTime);
				certificateModel.setCertificate(certificate);
				certificateMap.put((i++) + "", certificateModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/*
	 * 获取基本信息
	 * 
	 */
	public static void basicInfo(Element eles) {
		try {
			// 基本信息
			int i = 0;
			String str = null;
			for (Element ele : eles.select("ul>li")) {
				if (i == 0) {
					int j = 0;
					for (Element el : ele.select("div>span")) {
						str = el.text();
						if (j == 0) {
							gender = str;
						} else if (j == 1) {
							age = str;
						} else if (j == 2) {
							city = str;
						} else if (j == 3) {
							workingyear = str;
						} else if (j == 4) {
							// 供职过5家单位
						} else if (j == 5) {
							// 英语、中文普通话
						}
						j++;
					}

				} else if (i == 1) {
					int j = 0;
					for (Element el : ele.select("div>span")) {
						str = el.text();
						if (j == 0) {
							title = str;
						} else if (j == 1) {
							company = str;
						} else if (j == 2) {
							jobIndustry = str;
						}
						j++;
					}

				} else if (i == 2) {
					int j = 0;
					for (Element el : ele.select("div>span")) {
						str = el.text();
						if (j == 0) {
							degree = str;
						} else if (j == 1) {
							major = str;
						} else if (j == 2) {
							school = str;
						}
						j++;
					}

				} else if (i == 3) {

					try {
						expectedSalary = ele.select("div>span").text().split("：")[1];
					} catch (Exception e) { 
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				i++;
			//	System.out.println(ele.text());
			}
			basicModel2.setGender(gender);
			basicModel2.setAge(age);
			basicModel2.setCity(city);
			basicModel2.setTitle(title);

			basicModel2.setCompany(company);
			basicModel2.setSchool(school);
			basicModel2.setDegree(degree);
			basicModel2.setMajor(major);
			basicModel2.setExpectedSalary(expectedSalary);

			// 基本资料
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 求职意向
	 * 
	 * @param ele
	 */
	public static void jobIntension(Element ele) {

		int i = 0, j = 0;
		String str, str2;
		for (Element el : ele.select("dl>dt")) {
			try {
				j = 0;
				for (Element el2 : ele.select("dl>dd")) {

					if (i == j) {
						str = el.text();
						str2 = el2.text();
						//System.out.println(str + " " + str2);
						if (str.indexOf("工作性质") != -1) {
							expectnaturework = str2;
							basicModel2.setExpectnaturework(expectnaturework);
						} else if (str.indexOf("期望行业") != -1) {

							expectindustry = str2;
							basicModel2.setExpectindustry(expectindustry);

						} else if (str.indexOf("期望职业") != -1) {
							expectedJob = str2;
							basicModel2.setExpectedJob(expectedJob);

						} else if (str.indexOf("目标城市") != -1) {
							expectedCity = str2;
							basicModel2.setExpectedCity(expectedCity);

						} else if (str.indexOf("期望薪水") != -1) {

							expectedSalary = str2;
							basicModel2.setExpectedSalary(expectedSalary);
						} else if (str.indexOf("到岗时间") != -1) {
							dutytime = str2;
							basicModel2.setDutytime(dutytime);

						}

					}
					j++;
				}
				i++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		int i = 0, j = 0, k = 0;
		String str, str2;
		for (Element el0 : ele.select("div.itemTit")) {
			Job job = new Job();
			try {
				startTime = el0.select("div.titc1").text().split("-")[0];
				endTime = el0.select("div.titc1").text().split("-")[1];
				company = el0.select("div.titc2").text();
				title = el0.select("div.titc3").text();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			i = k * 6;
			for (Element el : ele.select("dl>dt")) {

				j = 0;
				str = el.text();
				for (Element el2 : ele.select("dl>dd")) {
					str2 = el2.text();
					if (i == j) {
						if (str.indexOf("所属行业") != -1) {
							jobIndustry = str2;
						} else if (str.indexOf("工作地点") != -1) {
							city = str2;
						} else if (str.indexOf("工作性质") != -1) {
							jobInfo = str2;
						} else if (str.indexOf("职位类别") != -1) {
							jobInfo += "  " + str2;
						} else if (str.indexOf("职位级别") != -1) {
							jobInfo += "  " + str2;
						} else if (str.indexOf("职责和业绩") != -1) {
							achieve = str2;
							job.setStartTime(startTime);
							job.setEndTime(endTime);
							job.setCompany(company);
							job.setPosition(title);
							job.setJobIndustry(jobIndustry);
							job.setCity(city);
							job.setAchieve(achieve);
							job.setJobInfo(jobInfo);
							jobMap.put(k + "", job);
							jobInfo = "";

						}

					}

					j++;
				}
				i++;
			}

			k++;
		}

		// System.exit(0);

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
		// System.exit(0);
		int i = 0, j = 0, k = 0;
		String str, str2;
		for (Element el0 : ele.select("div.itemTit")) {
			// System.out.println(el0);
			Education edu = new Education();
			try {
				startTime = el0.select("div.titc1").text().split("-")[0];
				endTime = el0.select("div.titc1").text().split("-")[1];
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			school = el0.select("div.titc2").text();
			degree = el0.select("div.titc3").text();
			i = k * 2;
			for (Element el : ele.select("dl>dt")) {
				j = 0;
				str = el.text();
				for (Element el2 : ele.select("dl>dd")) {

					if (i == j) {
						str2 = el2.text();
						if (str.indexOf("专业：") != -1) {
							major = str2;
							if (el0.text().indexOf("专业描述与主修课程") == -1) {
								edu.setStartTime(startTime);
								edu.setEndTime(endTime);
								edu.setSchool(school);
								edu.setDegree(degree);
								edu.setMajor(major);
								educationMap.put(k + "", edu);
							}

						} else if (str.indexOf("专业描述与主修课程") != -1) {
							courses = str2;

							edu.setStartTime(startTime);
							edu.setEndTime(endTime);
							edu.setSchool(school);
							edu.setDegree(degree);
							edu.setMajor(major);
							edu.setCourses(courses);
							educationMap.put(k + "", edu);

						}

					}

					j++;
				}
				i++;
			}

			k++;
		}

		// System.exit(0);

	}

	/**
	 * 项目经历 startTime endTime company proHardEn/proSofEn proDevelopment content
	 * duty
	 */

	public static void projectExperience(Element ele) {
		// System.exit(0);
		int i = 0, j = 0, k = 0;
		String str, str2;
		for (Element el0 : ele.select("div.itemTit")) {
			Project project = new Project();
			try {
				startTime = el0.select("div.titc1").text().split("-")[0];
				endTime = el0.select("div.titc1").text().split("-")[1];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			projectName = el0.select("div.titc2").text();

			i = k * 2;
			for (Element el : ele.select("dl>dt")) {

				j = 0;
				str = el.text();
				for (Element el2 : ele.select("dl>dd")) {
					str2 = el2.text();
					if (i == j) {
						if (str.indexOf("项目描述") != -1) {
							content = str2;
						} else if (str.indexOf("项目职责") != -1) {
							duty = str2;
							project.setStartTime(startTime);
							project.setEndTime(endTime);
							project.setProjectName(projectName);
							project.setContent(content);
							project.setDuty(duty);
							projectmap.put(k + "", project);

						}

					}

					j++;
				}
				i++;
			}

			k++;
		}

		// System.exit(0);

	}

	/**
	 * 专业技能 //skillName skillInfo skilltime skillMap skillModel
	 * 
	 * @param ele
	 */
	public static void proSkill(Element ele) {

		try {
			ele.select("div.ContentBox>br").wrap("<li>;<li>");
			String[] str = ele.text().split(";");

			for (int i = 0; i < str.length; i++) {
				skillModel = new Skill();
				skillName = str[i];
				skillModel.setSkillName(skillName);
				skillMap.put(i + "", skillModel);
			}
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// String path =H:/猎头需要软件/Html/前程无忧/0/51job_archibald(8248536).html
		// C:/Users/gongcaichun/Desktop/HTML/未分类/Aries Liu 1.html 中英文简历
		// C:/Users/gongcaichun/Desktop/模板/WModel4Parse/a 1.html
		String path = "C:/Users/gongcaichun/Desktop/html额外/未分类/898/6000000005975758 雍娜(13527403673).htm";
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
