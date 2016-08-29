package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.cert.CertPathValidatorException.Reason;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.security.sasl.SaslServerFactory;

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

public class brModel8Parse {

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
	public static String info;// 其他信息

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

	public brModel8Parse() {

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

			File input = new File(path);
			doc = Jsoup.parse(input, "utf-8");
			// eles = doc.select("html[lang=en]>head");
			//
			// System.out.println(eles.text().equals("简历"));
			// System.exit(0);

			String str, docStr;
			docStr = doc.text();

			jobExperience(docStr); // ok
			projectExperience(docStr); // ok
			language(docStr);// ok
			proSkill(docStr);// ok

			doc.select("body>br").wrap("<li>;<li>");
			docStr = doc.text();
			basicInfo(docStr);// ok
			educationContent(docStr);// ok

			// System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获取基本信息
	 * 
	 */
	public static void basicInfo(String docStr) {

		// System.out.print(docStr);
		// System.exit(0);
		String str;
		String[] str2 = docStr.split(";");
		for (int i = 0; i < str2.length; i++) {
			try {
				str = str2[i];

				if (str.indexOf("姓名:") != -1) {
					name = str.split(":")[1];
					basicModel2.setName(name);
				} else if (str.indexOf("性别:") != -1) {

					gender = str.split(":")[1].split(" ")[0];
					age = str.split(":")[2].trim();
					basicModel2.setGender(gender);
					basicModel2.setAge(age);
				} else if (str.indexOf("出生日期:") != -1) {
					birthday = str.split(":")[1].trim();
					basicModel2.setBirth(birthday);
				} else if (str.indexOf("婚否：") != -1) {
					maritalstatus = str.split("：")[1].split(" ")[0];
					basicModel2.setMaritalstatus(maritalstatus);
				} else if (str.indexOf("学历:") != -1) {
					degree = str.split(":")[1].trim();
					basicModel2.setDegree(degree);
				} else if (str.indexOf("参加工作时间:") != -1) {
					//
				} else if (str.indexOf("工作经验:") != -1) {
					workingyear = str.split(":")[1];
					basicModel2.setWorkingyear(workingyear);
				} else if (str.indexOf("户口所在地:") != -1) {
					account = str.split(":")[1];
					basicModel2.setAccount(account);
				} else if (str.indexOf("现居住城市:") != -1) {
					city = str.split(":")[1];
					basicModel2.setCity(city);
				} else if (str.indexOf("手机号码:") != -1) {
					mobile = str.split(":")[1];
					basicModel2.setMobile(mobile);
				} else if (str.indexOf("地址:") != -1) {
					latteraddress = str.split(":")[1];
					basicModel2.setLatteraddress(latteraddress);
				} else if (str.indexOf("Email:") != -1) {
					email = str.split(":")[1];
					basicModel2.setEmail(email);
				} else if (str.indexOf("QQ") != -1) {
					// qq = str.split(":")[1];
				} else if (str.indexOf("国籍") != -1) {
					//
				} else if (str.indexOf("民族") != -1) {
					//
				} else if (str.indexOf("证件类型") != -1) {
					//
				} else if (str.indexOf("邮编:") != -1) {
					zipCode = str.split(":")[1];
					basicModel2.setZipCode(zipCode);
				} else if (str.indexOf("政治面貌:") != -1) {
					politics = str.split(":")[1];
					basicModel2.setPolitics(politics);
				} else if (str.indexOf("期望工作地点") != -1) {
					expectedCity = str.split(":")[1];
					basicModel2.setExpectedCity(expectedCity);
				} else if (str.indexOf("期望从事职业") != -1) {
					expectedJob = str.split(":")[1];
					basicModel2.setExpectedJob(expectedJob);
				} else if (str.indexOf("期望从事行业") != -1) {
					expectindustry = str.split(":")[1];
					basicModel2.setExpectindustry(expectindustry);
				} else if (str.indexOf("期望月薪") != -1) {
					expectedSalary = str.split(":")[1];
					basicModel2.setExpectedSalary(expectedSalary);
				} else if (str.indexOf("当前年薪") != -1) {
					currentSalary = str.split(":")[1];
					basicModel2.setCurrentSalary(currentSalary);
				} else if (str.indexOf("简介:") != -1) {
					selfassessment = str.substring(str.indexOf(":") + 1, str.length());
					basicModel2.setSelfassessment(selfassessment);
				} else if (str.indexOf("照片") != -1) {
					// photoPath = str.split(":")[1];
				} else if (str.indexOf("主页") != -1) {
					i = str2.length; // 退出
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 语言能力
	 * 
	 * @param ele
	 */
	public static void language(String docStr) {
		try {
			int i = 0, j = 0;
			String str = null;
			i = docStr.indexOf("语言水平：");
			j = docStr.indexOf("技能:");
			if (i != -1) {
				if (j != -1)
					str = docStr.substring(i + 5, j);
				else
					str = docStr.substring(i + 5, docStr.length());
				language = str.replace("&nbsp", " ");
				basicModel2.setLanguage(language);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static void jobExperience(String docStr) {
		try {
			String str2 = docStr.substring(docStr.indexOf("工作经历："), docStr.length()), tem = "";
			String[] s;
			int j = 0, k = 0, n = 0;

			while (str2.indexOf("离职理由:") != -1) {
				Job job = new Job();

				j = str2.indexOf("工作经历：");
				k = str2.indexOf("工作内容:");
				if (j != -1)
					tem = str2.substring(j + 5, k);
				else {
					if (k != -1)
						tem = str2.substring(0, k);
				}

				s = tem.split("\\|");
				info = "";
				for (int i = 0; i < s.length; i++) {
					try {
						if (!s[i].trim().equals(""))
							if (0 == i) {
								startTime = s[0];
								job.setStartTime(startTime);
							} else if (1 == i) {
								endTime = s[1];
								job.setEndTime(endTime);

							} else if (2 == i) {
								//
							} else if (3 == i) {
								company = s[3];
								job.setCompany(company);
							} else if (4 == i) {// 民营
								info += s[4] + " ";
							} else if (5 == i) {// 人数规模
								info += s[5] + " ";
								job.setInfo(info);
							} else if (6 == i) {
								jobIndustry = s[6];
								job.setJobIndustry(jobIndustry);
							} else if (7 == i) {
								salary = s[7];
								job.setSalary(salary);
							} else if (8 == i) {// 工作时间

								duration = s[8].substring(0, s[8].length() - 2);
								job.setDuration(duration);

							}

						// System.out.println(s[i]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				jobInfo = str2.substring(k + 5, str2.indexOf("离职理由"));
				job.setJobInfo(jobInfo);

				jobMap.put((n++) + "", job);
				str2 = str2.substring(str2.indexOf("离职理由") + 5, str2.length());

				// System.out.println(jobInfo);
				// System.out.println("--------------------------");
				// System.exit(0);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void educationContent(String docStr) {
		try {
			String tem;
			String[] str, str1;
			int n = 0;
			if (docStr.indexOf("教育经历:") != -1) {
				if (docStr.indexOf("语言水平") != -1) {
					tem = docStr.substring(docStr.indexOf("教育经历:") + 5, docStr.indexOf("语言水平") - 1);
					str = tem.split(";");

					for (int i = 0; i < str.length; i++) {
						try {
							if (!str[i].trim().equals("")) {
								educationModel = new Education();
								String str2 = str[i];
								str2 = str2.replaceAll("&nbsp", ";");
								str1 = str2.split(";");
								// System.out.println(str[i]);
								for (int j = 0; j < str1.length; j++) {

									// System.out.println(str1[j]);
									if (0 == j) {
										startTime = str1[j].split("-")[0];
										educationModel.setStartTime(startTime);
										endTime = str1[j].split("-")[1];
										educationModel.setEndTime(endTime);
									} else if (1 == j) {

										school = str1[j];
										educationModel.setSchool(school);
									} else if (2 == j) {
										major = str1[j];
										educationModel.setMajor(major);
									} else if (3 == j) {
										degree = str1[j];
										educationModel.setDegree(degree);
									}

								}

								educationMap.put((n++) + "", educationModel);

							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					// System.exit(0);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 项目经历 startTime endTime company proHardEn/proSofEn proDevelopment content
	 * duty
	 */

	public static void projectExperience(String docStr) {
		try {
			String str2 = docStr.substring(docStr.indexOf("项目经验:"), docStr.length()), tem = "";
			String[] s;
			int j = 0, k = 0, n = 0;

			while (str2.indexOf("责任描述:") != -1) {

				projectModel = new Project();

				j = str2.indexOf("项目经验:");
				k = str2.indexOf("项目介绍:");
				if (j != -1)
					tem = str2.substring(j + 5, k);
				else {
					if (k != -1)
						// tem = str2.substring(0, k);
						tem = str2.substring(str2.indexOf("|") - 7, k);
				}

				s = tem.split("\\|");
				projectInfo = "";
				for (int i = 0; i < s.length; i++) {

					if (0 == i) {
						startTime = s[0];
						projectModel.setStartTime(startTime);
					} else if (1 == i) {
						endTime = s[1];
						projectModel.setEndTime(endTime);
					} else if (2 == i) {
						projectName = s[2];
						projectModel.setProjectName(projectName);
					} else if (3 == i) {
						projectInfo += s[3] + "	";
					} else if (4 == i) {// 民营
						projectInfo += s[4] + "	";
						projectModel.setProjectInfo(projectInfo);
					}

					// System.out.println(s[i]);
					// System.exit(0);
				}

				content = str2.substring(k + 5, str2.indexOf("责任描述:")); // 项目描述
				projectModel.setContent(content);
				// System.out.println(content);
				str2 = str2.substring(str2.indexOf("责任描述:"), str2.length());
				if (str2.indexOf("项目介绍:") != -1) {
					duty = str2.substring(str2.indexOf("责任描述:") + 5, str2.indexOf("|") - 7);

				} else {
					duty = str2.substring(str2.indexOf("责任描述:") + 5, str2.indexOf("教育经历:"));
				}
				projectModel.setDuty(duty);

				projectmap.put((n++) + "", projectModel);

				str2 = str2.substring(str2.indexOf("责任描述:") + 5, str2.length());

				// System.out.println(duty);
				// System.out.println("--------------------------");
				// System.exit(0);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 专业技能 //skillName skillInfo skilltime skillMap skillModel
	 * 
	 * @param ele
	 */
	public static void proSkill(String docStr) {
		try {
			if (docStr.indexOf("技能:") != -1) {

				String str2 = docStr.substring(docStr.indexOf("技能:"), docStr.length()), tem = "";
				String[] s;
				int h = 0, j = 0, k = 0, n = 0;
				str2 = str2.replaceAll("&nbsp", " ");
				while (str2.indexOf("个月") != -1) {

					skillModel = new Skill();
					j = str2.indexOf("技能:");
					h = str2.indexOf("-");
					k = str2.indexOf("个月");
					if (j != -1) {
						if (k != -1)
							if (h != -1) {
								skillName = str2.substring(j + 3, h);
								skillModel.setSkillName(skillName);
								skillInfo = str2.substring(h + 1, k - 3);
								skillModel.setSkillInfo(skillInfo);
								skilltime = str2.substring(k - 3, k + 2);
								skillModel.setTime(skilltime);
							}

					} else {

						if (h != -1) {
							skillName = str2.substring(0, h);
							skillModel.setSkillName(skillName);
							skillInfo = str2.substring(h + 1, k - 3);
							skillModel.setSkillInfo(skillInfo);
							skilltime = str2.substring(k - 3, k + 2);
							skillModel.setTime(skilltime);
						}

					}

					str2 = str2.substring(k + 2, str2.length());
					skillMap.put((n++) + "", skillModel);

					// System.out.println("--------------------------");

				}
			}
		} catch (

		Exception e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		// String path =H:/猎头需要软件/Html/前程无忧/0/51job_archibald(8248536).html
		// C:/Users/gongcaichun/Desktop/HTML/未分类/Aries Liu 1.html 中英文简历
		// C:/Users/gongcaichun/Desktop/模板/WModel4Parse/a 1.html
		String path = "C:/Users/gongcaichun/Desktop/html额外/未分类/br分隔/jm184363849r90250004000-李云.html";
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
