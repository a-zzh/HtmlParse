package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
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

public class WModel4Parse {

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

	public WModel4Parse() {

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
			//System.out.println(path);
			File input = new File(path);
			doc = Jsoup.parse(input, "gbk");
			eles = doc.select("td[valign=top]>table[width=97%]");

			for (Element ele : eles) {
				dealwithTable(ele, ele.text());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dealwithTable(Element ele, String str) {
		// 以table读取简历
		if (str.trim().equals("")) {// 不处理

		} else if (str.contains("简历关键字")) {// 获取头简历关键字
			keyword = ele.select("span[id=spanTitled]").text();
			basicModel2.setKeywords(keyword);
			//System.out.println(keyword);
		} else if (str.contains("居住地")) {// 基本信息处理
			basicInfo(ele);
		} else if (str.contains("最高学历")) { // 最近工作
			education(ele);
		} else if (str.contains("目前年薪")) {
			currentSalary(ele);
		} else if (str.contains("自我评价")) {
			selfAss(ele);
		} else if (str.contains("求职意向")) {
			jobIntension(ele);
		} else if (str.contains("工作经验")) { // 工作经验 项目经理 教育经历 培训经历 语言能力 技能 附件...
			dealwithTr(ele);
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
	 * 目前年薪
	 * 
	 * 
	 */
	public static void currentSalary(Element ele) {
		currentSalary = ele.select("tbody>tr>td[width=221]").text() + "/年";
		basicModel2.setCurrentSalary(currentSalary);
		//System.out.println(currentSalary);

	}

	/*
	 * dealwithTr处理工作经验所在的table，里面又分很多小table
	 */
	public static void dealwithTr(Element ele) {
		Elements els1 = ele.select("tr>td[class=cvtitle]"); // 栏目名
		Elements els2 = ele.select("tr>td>table");// 栏目信息

		// 将栏目与栏目信息一一对应
		int i = 0, j = 0;
		for (Element el : els1) {
			j = 0;
			for (Element el2 : els2) {
				if (i == j) {
				//	System.out.println(el.text() + ":" + el2.text());
				//	System.out.println();
					// mp.put(el.text(), el2.text());
					int cate = util.category4(el.text());
					switch (cate) {
					case 0: // 工作经验 (包括起始时间) ok
						jobExperience(el2);
						break;
					case 1: // 项目经验 (包括起始时间) ok
						projectExperience(el2);
						break;
					case 2: // 教育经历 ok
						educationContent(el2);
						//System.out.println(projectmap.values());
						break;
					case 3: // 所获奖项 ok
						medalInfo(el2);
						break;
					case 4:// 培训经历 ok
						trainExperience(el2);
						break;
					case 5:// 社会经验 ok
						socialExperience(el2);
						break;
					case 6:// 语言能力 ok
						language(el2);
						break;
					case 7:// 技能 ok
						proSkill(el2);
						break;
					case 8:// 证书 ok
						credentialInfo(el2);
						break;
					case 9:// 其他信息
						addInformation(el2);
						// elseInfo(el2);
						break;
					case 10:// 附件

						attInfo(el2);
						break;
					case 11:// 校内职务

						stdJobInfo(el2);
						break;
					}
				}
				j++;
			}
			i++;
		}

		// System.exit(0);
	}

	/**
	 * 附件信息 图片url +图片名字 //attName attUrl attchment attMap
	 */
	public static void attInfo(Element ele) {
		int i = 0;
		for (Element el : ele.select("tbody>tr")) {
			attchment = new Attachment();
			String str = el.text();
			if (!str.equals("")) {
				if (el.select("td").size() == 2) {
					attUrl = el.select("td[width=25%]>a").attr("href");
					attName = el.select("td[class=text]td[width=75%]").text();
					attchment.setAttName(attName);
					attchment.setAttUrl(attUrl);
					attMap.put((i++) + "", attchment);
				}

			}
		}

	}

	// practiceTime practiceName practiceInfo StudentsJob StudentsJobMap
	public static void stdJobInfo(Element ele) {
		int i = 0;
		for (Element el : ele.select("tbody>tr")) {
			StudentsJob = new StudentsPractice();
			String str = el.text();
			if (!str.equals("")) {
				if (str.contains("--")) {
					practiceTime = el.select("td[width=25%]").text();
					practiceName = el.select("td[class=text]").text();
					StudentsJob.setPracticeTime(practiceTime);
					StudentsJob.setPracticeName(practiceName);
					StudentsJobMap.put((i++) + "", StudentsJob);
				}
			}
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
			certificateModel = new Credential();
			if (!el.text().equals("")) {
				certificateTime = el.select("td[width=18%]").text();
				certificate = el.select("td[width=52%]").text();
				CredentialLevel = el.select("td[width=30%]").text();
				certificateModel.setCertificateTime(certificateTime);
				certificateModel.setCertificate(certificate);
				certificateModel.setCredentialLevel(CredentialLevel);
				certificateMap.put((i++) + "", certificateModel);

			}

		}

	}

	/*
	 * 社会经验 SocialExperience socialMap startTime endTime title content
	 * 
	 */
	public static void socialExperience(Element ele) {
		startTime = null;
		endTime = null;
		socialtitle = null;
		int i = 0;
		for (Element el : ele.select("tbody>tr")) {
			socialEx = new SocialExperience();
			String str = null;
			if (!el.text().equals("")) {
				if (el.select("td").size() == 2) {
					try {
						startTime = el.select("td[width=25%]").text().split("-")[0];
						endTime = el.select("td[width=25%]").text().split("-")[2];
						socialtitle = el.select("td[class=text]").text();
					} catch (ArrayIndexOutOfBoundsException e) {

					}
				} else if (el.select("td").size() == 1) {
					socialcontent = el.text();
					socialEx.setStartTime(startTime);
					socialEx.setEndTime(endTime);
					socialEx.setSocialtitle(socialtitle);
					socialEx.setSocialcontent(socialcontent);
					socialMap.put((i++) + "", socialEx);

				}

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
	public static void basicInfo(Element ele) {
		// TODO Auto-generated method stub zipCode
		workingyear = null;
		gender = null;
		age = null;
		birthday = null;

		int i = 0;
		for (Element el : ele.select("tbody>tr")) {
			if (i == 0) {
				name = el.text();
				basicModel2.setName(name);
			} else if (i == 1) {
				for (Element el2 : el.select("td>table>tbody>tr")) {
					try {

						String el2text = el2.text();
						if (!el2.select("span[class=blue]>b").text().equals("")) {
							String[] str = el2text.split("\\|");

							workingyear = str[0].trim();// 工作年限

							int index = 0;
							if (el2text.contains("男") || el2text.contains("女")) { // 性别
								if (el2text.contains("男"))
									gender = "男";
								else
									gender = "女";
							}
							if (el2text.contains("岁")) {
								index = el2text.indexOf("岁");
								age = (String) el2text.subSequence(index - 2, index);
							}
							if (el2text.contains("年")) {
								birthday = el2text.split("（")[1].split("）")[0]; // 1988年8月6日
							}
							if (el2text.contains("婚")) {  	//婚姻状况，0未知，1未婚，2已婚，3已育，4离异，5丧偶，6其他
								index = el2text.indexOf("婚");
								maritalstatus = (String) el2text.subSequence(index - 1, index + 1);
							} else if (el2text.contains("已育")) {
								maritalstatus = "已育";
							} else if (el2text.contains("离异")) {
								maritalstatus = "离异";
							} else if (el2text.contains("丧偶")) {
								maritalstatus = "离异";
							} else if (el2text.contains("其他")) {
								maritalstatus = "其他";
							}
							if (el2text.contains("cm")) {
								index = el2text.indexOf("cm");
								height = (String) el2text.subSequence(index - 3, index);
							}

							basicModel2.setWorkingyear(workingyear);
							basicModel2.setGender(gender);
							basicModel2.setAge(age);
							basicModel2.setBirth(birthday);
							basicModel2.setMaritalstatus(maritalstatus);
							basicModel2.setHeight(height);

						} else if (el2text.contains("居住地")) {
							city = el2.select("td[width=42%]").text();
							String str = el2.select("td[width=20%]").text().trim();
							if (!str.equals(""))
								account = str;

							basicModel2.setCity(city);
							basicModel2.setAccount(account);

						} else if (el2text.contains("地　址")) {
							latteraddress = el2.select("td[colspan=3]").text(); // 地址
							zipCode = ele.select("td[colspan=3]").text().split("：")[1].split("）")[0];
							basicModel2.setLatteraddress(latteraddress);
							basicModel2.setZipCode(zipCode);

						} else if (el2text.contains("电　话")) {
							// mobile phone
							if (el2text.contains("手机")) { // 手机
								mobile = el2.select("td[colspan=3]").text().split("（")[0];
								basicModel2.setMobile(mobile);
							} else { // 固话
								phone = el2.select("td[colspan=3]").text();
								basicModel2.setPhone(phone);
							}
						} else if (el2text.contains("E-mail")) {
							email = el2.select("td>a").text();
							basicModel2.setEmail(email);

						}

					} catch (ArrayIndexOutOfBoundsException e) {

					}
				}

			}
			i++;
		}
//		System.out.println(name);
//		System.out.println(workingyear);
//		System.out.println(age);
//		System.out.println(birthday);
//		System.out.println(city);
//		System.out.println(latteraddress);
//		System.out.println(zipCode);
//		System.out.println(mobile);
//		System.out.println(phone);
//		System.out.println(email);
	}

	/**
	 * 语言能力
	 * 
	 * @param ele
	 */
	public static void language(Element ele) {
		language = ele.text();
		basicModel2.setLanguage(language);
	//	System.out.println(language);
		// System.exit(0);
	}

	/**
	 * 求职意向
	 * 
	 * @param ele
	 */
	public static void jobIntension(Element ele) {
		// 求职意向信息 class="table_set"
		for (Element el : ele.select("table[class=table_set]>tbody>tr")) {
			String str = el.text();
			String str2 = el.select("span[class=text]").text();
			if (str.contains("到岗时间")) {
				dutytime = str2;
				basicModel2.setDutytime(dutytime);
			} else if (str.contains("工作性质")) {
				expectnaturework = str2;
				basicModel2.setExpectnaturework(expectnaturework);
			} else if (str.contains("希望行业")) {
				expectindustry = str2;
				basicModel2.setExpectindustry(expectindustry);
			} else if (str.contains("目标地点")) {
				expectedCity = str2;
				basicModel2.setExpectedCity(expectedCity);
			} else if (str.contains("期望月薪")) {
				expectedSalary = str2;
				basicModel2.setExpectedSalary(expectedSalary);
			} else if (str.contains("目标职能")) {
				expectedJob = str2;
				basicModel2.setExpectedJob(expectedJob);
			} else if (str.contains("求职状态")) {
				jobStatus = str2;
				basicModel2.setJobStatus(jobStatus);
			}

		}
		//System.out.println("--------------------" + jobStatus);
	}

	/**
	 * 自我评价
	 */
	public static void selfAss(Element ele) {
		// 自我评价
		selfassessment = ele.select("span[class=text]").text();
		basicModel2.setSelfassessment(selfassessment);
		//System.out.println(selfassessment);
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

		startTime = null;
		endTime = null;
		company = null;
		duration = null;
		int i = 0, j = 0;
		for (Element el : ele.select("tbody>tr")) {
			Job job = new Job();
			String str = el.text();
			if (str.trim().equals("")) {
				continue;
			} else if (ele.text().contains("工作业绩")) {

				try {
					if (str.contains("--")&&str.length()<100) {
						startTime = str.split("：")[0].split("-")[0];
						endTime = str.split("：")[0].split("-")[2];
						company = str.split("：")[1].split("\\[")[0];
						duration = str.split("\\[")[1].split("\\]")[0];
					} else if (str.contains("所属行业")) {
						jobIndustry = el.select("td[width=78%]").text();
					} else if (el.select("td").size() == 1) {
						jobInfo = str;

					} else if (str.contains("汇报对象")) {
						report = el.select("td[class=text]").text();
					} else if (str.contains("下属人数")) {
						member = el.select("td[class=text]").text();
					} else if (str.contains("证 明 人")) {
						jobReferences = el.select("td[class=text]").text();
					} else if (str.contains("离职原因")) {

						reason = el.select("td[class=text]").text();

					} else if (str.contains("工作业绩")) {
						achieve = el.select("td[class=text]").text();

						job.setReason(reason);
						job.setJobInfo(jobInfo);
						job.setStartTime(startTime);
						job.setEndTime(endTime);
						job.setCompany(company);
						job.setDuration(duration);
						job.setJobIndustry(jobIndustry);
						job.setDepartment(department);
						job.setPosition(position);
						job.setReport(report);
						job.setMember(member);
						job.setJobReferences(jobReferences);
						job.setAchieve(achieve);
						jobMap.put((j++) + "", job);
						//System.out.println(startTime + "\n" + endTime + "\n" + company + "\n" + duration + "\n");
					} else if (el.select("td").size() == 2) {
						department = el.select("td[class=text_left]").text();
						position = el.select("td[class=text]").text();
					}

				} catch (ArrayIndexOutOfBoundsException e) {
				}

			} else {
				try {
					if (str.contains("--")&&str.length()<100) {
						startTime = str.split("：")[0].split("-")[0];
						endTime = str.split("：")[0].split("-")[2];
						company = str.split("：")[1].split("\\[")[0];
						duration = str.split("\\[")[1].split("\\]")[0];
					} else if (str.contains("所属行业")) {
						jobIndustry = el.select("td[width=78%]").text();
					} else if (i % 4 == 2) {
						department = el.select("td[class=text_left]").text();
						position = el.select("td[class=text]").text();
					} else if (i % 4 == 3) {
						jobInfo = str;
						job.setJobInfo(jobInfo);
						job.setStartTime(startTime);
						job.setEndTime(endTime);
						job.setCompany(company);
						job.setDuration(duration);
						job.setJobIndustry(jobIndustry);
						job.setDepartment(department);
						job.setPosition(position);

						jobMap.put((j++) + "", job);
					//	System.out.println(startTime + "\n" + endTime + "\n" + company + "\n" + duration + "\n");
					}

					i++;
				} catch (ArrayIndexOutOfBoundsException e) {

				}

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
		startTime = null;
		endTime = null;
		int i = 0;
		for (Element el : ele.select("tbody>tr")) {
			educationModel = new Education();
			String str = el.text();
			if (str.trim().equals("")) {
				continue;
			} else if (str.contains("--")&&str.length()<100) { //
				String time = el.select("td[width=26%]").text();
				try {
					startTime = time.split("-")[0];
					endTime = time.split("-")[2];
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}
				try {
					school = el.select("td[width=30%]").get(0).text();
					major = el.select("td[width=30%]").get(1).text();
				} catch (IndexOutOfBoundsException e) {
					//e.printStackTrace();
				}

				degree = el.select("td[width=14%]").text();
				
				if(i==0){
					basicModel2.setDegree(degree);
					basicModel2.setMajor(major); 
					basicModel2.setSchool(school);
					
				}

				if (!ele.select("td").toString().contains("<td colspan=")) {
					educationModel.setStartTime(startTime);
					educationModel.setEndTime(endTime);
					educationModel.setSchool(school);
					educationModel.setMajor(major);
					educationModel.setDegree(degree);
					educationMap.put((i++) + "", educationModel);
				}

			} else if (str.contains("主要课程")) {
				educationInfo = el.select("td[colspan=4]td[class=text_left]").text();
				try {
					if(str.contains("毕业")){
						courses = (String) educationInfo.subSequence(0, educationInfo.indexOf("毕业")); // 课程
						educationInfo = educationInfo.substring(educationInfo.indexOf("毕业")); // 毕业设计/论文
					} 
				} catch (StringIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

				educationModel.setStartTime(startTime);
				educationModel.setEndTime(endTime);
				educationModel.setSchool(school);
				educationModel.setMajor(major);
				educationModel.setDegree(degree);
				educationModel.setCourses(courses);
				educationModel.setEducationInfo(educationInfo);
				educationMap.put((i++) + "", educationModel);
				
				

				// System.out.println(educationMap.size());
				// System.out.println(educationMap.values());
			} else {
				educationInfo = el.select("td[colspan=4]td[class=text_left]").text();

				educationModel.setStartTime(startTime);
				educationModel.setEndTime(endTime);
				educationModel.setSchool(school);
				educationModel.setMajor(major);
				educationModel.setDegree(degree);
				educationModel.setEducationInfo(educationInfo);
				educationModel.setEducationInfo(educationInfo);
				educationMap.put((i++) + "", educationModel);
				// System.out.println(educationMap.values());

			}

		}

		// System.exit(0);
	}

	/**
	 * 项目经历 startTime endTime company proHardEn/proSofEn proDevelopment content
	 * duty
	 */

	public static void projectExperience(Element ele) {
		startTime = null;
		endTime = null;
		company = null;
		projectName= null;
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
					if (str.contains("--")&&str.length()<100) { //
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
						//projectModel.setCompany(company);
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
		for (Element el : ele.select("tbody>tr")) {
			skillModel = new Skill();
			if (i > 1) {
				j = 0;
				for (Element el2 : el.select("td")) {
					String str = el2.text();
					if (j == 0) {
						skillName = str;
					} else if (j == 1) {
						skillInfo = str;
					} else if (j == 2) {
						skilltime = str;

						skillModel.setSkillName(skillName);
						skillModel.setSkillInfo(skillInfo);
						skillModel.setTime(skilltime);
						skillMap.put(i + "", skillModel);

					}
					j++;
				}

			}

			i++;
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
				if (str.contains("--")&&str.length()<200) {
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
						trainInfo="";

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
		String path = "K:/mht2html/未分类/1393/18601156456-男-北京_北京-制造_贸易_经理-德语_英语-硕士_大专.html";
		parse(path);
		//		System.out.println("-----------------------------------------------------------------------------");

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
