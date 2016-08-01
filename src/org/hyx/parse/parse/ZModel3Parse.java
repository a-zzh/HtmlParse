package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hyx.parse.model.Basic2;
import org.hyx.parse.model.Credential;
import org.hyx.parse.model.Education;
import org.hyx.parse.model.Honor;
import org.hyx.parse.model.Job;
import org.hyx.parse.model.Medal;
import org.hyx.parse.model.OtherInfor;
import org.hyx.parse.model.Project;
import org.hyx.parse.model.Skill;
import org.hyx.parse.model.Train;
import org.hyx.parse.util.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZModel3Parse {

	private static String name;// 姓名
	private static String gender;// 性别
	private static String title;// 职位
	private static String city;// 城市
	private static String workingyear;// 工作年限
	private static String education;// 学历
	private static String latteraddress;// 暂住地
	private static String maritalstatus;// 婚姻状况
	private static String account;// 户口
	private static String phone;// 固定电话
	private static String mobile;// 手机
	private static String email;// 电子邮件
	private static String age;// 年龄
	private static String birthday;// 生日
	private static String degree;// 学位
	private static String school;// 学校
	private static String major;// 专业
	private static String language;// 语言能力
	private static String courses;// 所学课程
	private static String zipCode;//邮编

	private static String expectnaturework;// 期望工作性质
	private static String expectindustry;// 期望从事行业
	private static String currentSalary;// 目前薪资
	private static String expectedCity;// 期望城市
	private static String expectedJob;// 期望职位
	private static String jobStatus;// 工作状态
	private static String expectedSalary;// 期望薪资
	private static String bonus;//奖金

	private static String selfassessment;// 自我评价
	private static String politics;// 政治面貌

	private static String startTime;// 起始时间
	private static String endTime;// 结束时间
	private static String position;// 职位
	private static String salary;// 薪资
	private static String jobInfo;// 工作描述信息（其它关于工作描述的信息）
	private static String company;// 所在公司
	private static String department;// 所在部门
	private static String report;// 汇报人
	private static String member;// 管辖成员
	private static String duty;// 职责
	private static String jobIndustry;// 所属行业
	private static String jobReferences;// 证明人
	private static String achieve;// 工作业绩

	private static String certificateTime;// 证书获得时间
	private static String certificate;// 证书名称

	private static String projectName;// 项目名称
	private static String content;// 项目内容
	private static String proSofEn;// 软件环境
	private static String proHardEn;// 硬件环境
	private static String projectInfo;// 其他信息
	private static String proDevelopment;// 开发工具

	private static String medalName;// 奖项名称
	private static String medalInfo;// 奖项信息

	private static String skillName;// 技能名称
	private static String skillInfo;// 技能熟练程度
	private static String skilltime;// 使用时间

	private static String honorName;// 奖项名称
	private static String honorInfo;// 奖项信息

	private static String trainInstitutions;// 培训机构
	private static String trainClass;// 培训内容
	private static String trainInfo;// 培训信息

	private static String otherInfor;// 附加信息

	private static Map<String, Job> jobMap = new LinkedHashMap<String, Job>();
	private static Map<String, Credential> certificateMap = new LinkedHashMap<String, Credential>();
	private static Map<String, Education> educationMap = new LinkedHashMap<String, Education>();
	private static Map<String, Project> projectmap = new LinkedHashMap<String, Project>();
	private static Map<String, Medal> medalMap = new LinkedHashMap<String, Medal>();
	private static Map<String, Skill> skillMap = new LinkedHashMap<String, Skill>();
	private static Map<String, Honor> honorMap = new LinkedHashMap<String, Honor>();
	private static Map<String, Train> trainMap = new LinkedHashMap<String, Train>();

	private static Job jobModel;
	private static Education educationModel;
	private static Project projectModel;
	private static Basic2 basicModel2 = new Basic2();
	private static Credential certificateModel;// 证书
	private static Medal medalModel;// 学校获奖情况
	private static Skill skillModel;// 技能情况
	private static Honor honorModel;// 获得荣誉
	private static Train trainModel;// 培训经历
	private static OtherInfor otherInfoModel = new OtherInfor();// 其他信息
	private static util utilClass;
	private static int i = 0;
	private static int j = 0;
	private static Elements eles;
	private static String eletext;
	private static Document doc;

	public static void parse(String path) throws IOException {
		System.out.println(path);
		File input = new File(path);
		doc = Jsoup.parse(input, "gbk");
		eles = doc.select("table[width=600]");
		int i = 0;
		for (Element ele : eles) {
			/*System.out.println("ele ===== " +ele);*/
			String cateString = ele.select("tbody>tr[height=25]>td>b").text();
			if (cateString == null || cateString.isEmpty()) {
				basic(ele);// 基本信息的获取
			}
			ele.select("tbody>tr>td>b").wrap(
					"<li>;<span></span></li>");
			String eletoString = ele.toString();
			String[] eletoStrings = eletoString.split(";");
			for (i = 0; i < eletoStrings.length; i++) {
				Element eletoStringele = Jsoup.parse(eletoStrings[i]).body();
				Elements eletoStringeles = eletoStringele.select("body");
				for (Element ele222 : eletoStringeles) {
					Elements cates = ele222.select("span>b");
					for (Element cate : cates) {
						if (cates.text() != null) {
							
							int cateInt = util.category(cate.text().replaceAll(
									" ", ""));
							System.out.println("catetext jobExperience =="
									+ cate.text().replaceAll(" ", ""));
							System.out.println(cateInt);
							switch (cateInt) {
							case 0:
								jobIntension(ele);// 求职意向
								break;
							case 1:
								selfAss(ele);// 自我评价
								break;
							case 2:
								jobExperience(ele222);// 工作经历
								break;
							case 3:
								certificate(ele222);// 证书
								break;
							case 4:
								projectExperience(ele222);// 项目经历
								break;
							case 5:
								educationContent(ele222);// 教育经历

								break;
							case 6:
								language(ele222);// 语言能力
								break;
							case 7:
								// 求职信 暂未发现
								break;
							case 8:
								proSkill(ele222);// 专业技能
								break;
							case 9:

								/*
								 * * gainHonner(ele);//获得荣誉
								 */
								break;
							case 10:

								/*
								 * * schoolStudy(ele);//在校学习情况
								 */
								break;
							case 12:
								trainExperience(ele222);// 培训经历
								break;
							case 13:
								addInformation(ele222);// 附加信息
								break;
							}
						}
					}
				}
			}
		}

		System.out.println(basicModel2.toString());
		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Job> entry : jobMap.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}
		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Project> entry : projectmap.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}
		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Education> entry : educationMap.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}
		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Train> entry : trainMap.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Credential> entry : certificateMap.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}
		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Skill> entry : skillMap.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}

		System.out.println(otherInfoModel.toString());

	}

	/**
	 * 基本信息
	 * 
	 * @param ele
	 */
	public static void basic(Element ele) {
		Elements basicEles = ele.select("tbody>tr");
		int k = 0;
		for (Element basicEle : basicEles) {
			if (k == 0) {
				Elements namesexEles = basicEle.select("td");
				int l = 0;
				for (Element namesexEle : namesexEles) {
					if (l == 1) {
						name = namesexEle.text();
						basicModel2.setName(name);
					} else if (l == 3) {
						gender = namesexEle.text();
						basicModel2.setGender(gender);
					}
					l++;
				}
			} else if (k == 1) {
			Elements namesexEles = basicEle.select("td");
			int l = 0;
				for (Element namesexEle : namesexEles) {
					if (l == 1) {
						birthday = namesexEle.text();
						basicModel2.setBirth(birthday);
					} else if (l == 3) {
						account = namesexEle.text();
						basicModel2.setAccount(account);
					}
					l++;
				}		
			}else if (k == 2) {
				Elements namesexEles = basicEle.select("td");
				int l = 0;
				for (Element namesexEle : namesexEles) {
					if (l == 1) {
						workingyear = namesexEle.text();
						basicModel2.setWorkingyear(workingyear);
					} else if (l == 3) {
						latteraddress = namesexEle.text();
						basicModel2.setLatteraddress(latteraddress);
					}
					l++;
				}
			}else{
				/*System.out.println("basicEle == "+basicEle);*/
				String text = basicEle.text();
				if(text.indexOf("目前年薪")!=-1){
					currentSalary =  basicEle.select("[colspan=3]").text();
					basicModel2.setCurrentSalary(currentSalary);
				}else if(text.indexOf("地 址") !=-1){
					latteraddress = basicEle.select("[colspan=3]").text();
					basicModel2.setLatteraddress(latteraddress);
				}else if(text.indexOf("邮 编") !=-1){
					zipCode = basicEle.select("[colspan=3]").text();
					basicModel2.setZipCode(zipCode);
				}
				else if(text.indexOf("电子邮件") !=-1){
					email = basicEle.select("[colspan=3]").text();
					basicModel2.setEmail(email);
				}
				else if(text.indexOf("家庭电话") !=-1){
					phone = basicEle.select("[colspan=3]").text();
					basicModel2.setPhone(phone);
				}
				else if(text.indexOf("移动电话") !=-1){
					mobile = basicEle.select("[colspan=3]").text();
					basicModel2.setMobile(mobile);
				}
			}
			k++;
		}
	}

	/**
	 * 语言能力
	 * 
	 * @param ele
	 */
	public static void language(Element ele) {
		// 语言能力
		language = ele.text();
		basicModel2.setLanguage(language);
	}

	/**
	 * 求职意向
	 * 
	 * @param ele
	 */
	public static void jobIntension(Element ele) {
		// 求职意向信息
		Elements jobIntensionEles = ele.select("tr");
		for (Element jobIntensionEle : jobIntensionEles) {
			String jobtext = jobIntensionEle.select("[width=18%]").text();
			String jobtextvalue = jobIntensionEle.select("[width=82%]").text();
			if (jobtext.indexOf("工作性质") != -1) {
				expectnaturework = jobtextvalue;
			} else if (jobtext.indexOf("希望行业") != -1) {
				expectindustry = jobtextvalue;
			} else if (jobtext.indexOf("目标地点") != -1) {
				expectedCity = jobtextvalue;
			} else if (jobtext.indexOf("期望工资") != -1) {
				expectedSalary = jobtextvalue;
			} else if (jobtext.indexOf("目标职能") != -1) {
				expectedJob = jobtextvalue;
			}
			basicModel2.setExpectnaturework(expectnaturework);
			basicModel2.setExpectindustry(expectindustry);
			basicModel2.setExpectedCity(expectedCity);
			basicModel2.setExpectedSalary(expectedSalary);
			basicModel2.setExpectedJob(expectedJob);
		}

	}

	/**
	 * 自我评价
	 */
	public static void selfAss(Element ele) {
		// 自我评价
		Elements selfAsseles = ele.select("tr>td");
		selfAsseles.select("b").remove();
		String selfAsstext = selfAsseles.toString();
		selfAsstext = selfAsstext.replaceAll("<br>", ";");
		Element selfAssele = Jsoup.parse(selfAsstext).body();
		selfassessment = selfAssele.text();
		basicModel2.setSelfassessment(selfassessment);

	}

	/**
	 * 工作经历
	 * 
	 * @param ele
	 */
	public static void jobExperience(Element ele) {
		// 工作经历信息
		Elements jobeles = ele.select("table");
		jobeles.select("tr>td>hr").wrap("<li>;</li>");
		String jobelestoString = jobeles.toString();
		String[] jobelestoStrings = jobelestoString.split(";");
		for (i = 0; i < jobelestoStrings.length; i++) {
			String key = "job" + i;
			jobModel = new Job();
			jobelestoStrings[i] = "<table>" + jobelestoStrings[i] + "</table>";
			Element jobelestoStringsele = Jsoup.parse(jobelestoStrings[i])
					.body();
			Elements jobelestoStringseles = jobelestoStringsele.select("tr");
			for (Element jobtextEle : jobelestoStringseles) {
				String jobtext = jobtextEle.select("[colspan=2]").text()
						.replaceAll(" ", ";");
				if (!(jobtext == null || jobtext.isEmpty())) {
					String[] jobtexts = jobtext.split(";");
					department = jobtexts[0];
					position = jobtexts[1];
					jobModel.setDepartment(department);
					jobModel.setPosition(position);
				}

				String jobInfotoString = jobtextEle.select("[colspan=4]")
						.toString();
				if (!(jobInfotoString == null || jobInfotoString.isEmpty())) {
					if (jobInfotoString.indexOf("：") != -1) {
						String jobInfotoStringtext = Jsoup.parse(
								jobInfotoString).body().text();
						String[] jobInfotoStringtexts = jobInfotoStringtext
								.split("：");
						for (j = 0; j < jobInfotoStringtexts.length; j++) {
							String[] startendTiem = jobInfotoStringtexts[0]
									.split("--");
							startTime = startendTiem[0];
							endTime = startendTiem[1];
							company = jobInfotoStringtexts[1];
							jobModel.setStartTime(startTime);
							jobModel.setEndTime(endTime);
							jobModel.setCompany(company);

						}
					} else {
						jobInfo = Jsoup.parse(jobInfotoString).body().text();
						if (!(jobInfo == null || jobInfo.isEmpty())) {
							jobModel.setJobInfo(jobInfo);
						}

					}
				}

				String labeltext = jobtextEle.select("[width=18%]").text();

				if (!(labeltext == null || labeltext.isEmpty())) {
					if (labeltext.indexOf("所属行业") != -1) {
						jobIndustry = jobtextEle.select("[width=82%]").text();
						jobModel.setJobIndustry(jobIndustry);
					} else if (labeltext.indexOf("汇报对象") != -1) {
						report = jobtextEle.select("[width=82%]").text();
						jobModel.setReport(report);
					} else if (labeltext.indexOf("下属人数") != -1) {
						member = jobtextEle.select("[width=82%]").text();
						jobModel.setMember(member);
					} else if (labeltext.indexOf("证 明 人") != -1) {
						jobReferences = jobtextEle.select("[width=82%]").text();
						jobModel.setJobReferences(jobReferences);
					} else if (labeltext.indexOf("工作业绩") != -1) {
						achieve = jobtextEle.select("[width=82%]").text();
						jobModel.setAchieve(achieve);
					}
				}
			}
			jobMap.put(key, jobModel);
		}
	}

	/**
	 *教育信息
	 */
	public static void educationContent(Element ele) {
		// 教育信息

		Elements edueles = ele.select("table");
		edueles.select("tr>td>hr").wrap("<li>;</li>");
		String eduelestoString = edueles.toString();
		String[] eduelestoStrings = eduelestoString.split(";");
		for (i = 0; i < eduelestoStrings.length; i++) {
			String key = "edu" + i;
			educationModel = new Education();
			eduelestoStrings[i] = "<table>" + eduelestoStrings[i] + "</table>";
			Element eduelestoStringsele = Jsoup.parse(eduelestoStrings[i])
					.body();
			Elements eduelestoStringseles = eduelestoStringsele.select("tbody");
			for (Element edutextEle : eduelestoStringseles) {
				Elements edutextsssEles = edutextEle.select("td");
				int j = 0;
				for (Element edutextsssEle : edutextsssEles) {
					if (j == 0) {
						String[] startendTime = edutextsssEle.text()
								.split("--");
						startTime = startendTime[0];
						endTime = startendTime[1];
						educationModel.setStartTime(startTime);
						educationModel.setEndTime(endTime);
					} else if (j == 1) {
						school = edutextsssEle.text();
						/* System.out.println(""school); */
						educationModel.setSchool(school);
					} else if (j == 2) {
						major = edutextsssEle.text();
						educationModel.setMajor(major);
					} else if (j == 3) {
						degree = edutextsssEle.text();
						educationModel.setDegree(degree);
					} else if (j == 4) {
						courses = edutextsssEle.text();
						educationModel.setCourses(courses);
					}
					j++;
				}
			}
			educationMap.put(key, educationModel);
		}

	}

	/**
	 * 证书信息
	 */
	public static void certificate(Element ele) {
		// 证书信息
		Elements cettificateeles = ele.select("table");
		cettificateeles.select("tr>td>hr").wrap("<li>;</li>");
		String cerelestoString = cettificateeles.toString();
		String[] cerjecteleselestoStrings = cerelestoString.split(";");

		for (i = 0; i < cerjecteleselestoStrings.length; i++) {
			String key = "certifical" + i;
			certificateModel = new Credential();
			cerjecteleselestoStrings[i] = "<table>"
					+ cerjecteleselestoStrings[i] + "</table>";
			Element cerelestoStringsele = Jsoup.parse(
					cerjecteleselestoStrings[i]).body();
			Elements cerelestoStringseles = cerelestoStringsele.select("tbody");
			for (Element certextEle : cerelestoStringseles) {
				Elements certextsssEles = certextEle.select("td");
				j = 0;
				int j = 0;
				for (Element certextsssEle : certextsssEles) {
					if (j == 0) {
						certificateTime = certextsssEle.text();
						certificateModel.setCertificateTime(certificateTime);
					} else if (j == 1) {
						certificate = certextsssEle.text();
						certificateModel.setCertificate(certificate);
					}
					j++;
				}

			}
			certificateMap.put(key, certificateModel);
		}

	}

	/**
	 * 项目经历
	 */
	public static void projectExperience(Element ele) {
		Elements projecteles = ele.select("table");
		projecteles.select("tr>td>hr").wrap("<li>;</li>");
		String jobelestoString = projecteles.toString();
		String[] projecteleselestoStrings = jobelestoString.split(";");

		for (i = 0; i < projecteleselestoStrings.length; i++) {
			String key = "project" + i;
			projectModel = new Project();
			projecteleselestoStrings[i] = "<table>"
					+ projecteleselestoStrings[i] + "</table>";
			Element jobelestoStringsele = Jsoup.parse(
					projecteleselestoStrings[i]).body();
			Elements jobelestoStringseles = jobelestoStringsele.select("tr");
			for (Element projecttextEle : jobelestoStringseles) {
				String projectInfotoString = projecttextEle.select(
						"[colspan=4]").toString();
				if (!(projectInfotoString == null || projectInfotoString
						.isEmpty())) {
					if (projectInfotoString.indexOf("：") != -1) {
						String projectInfotoStringtext = Jsoup.parse(
								projectInfotoString).body().text();
						String[] projectTexts = projectInfotoStringtext
								.split("：");
						for (j = 0; j < projectTexts.length; j++) {
							String[] startendTiem = projectTexts[0].split("--");
							startTime = startendTiem[0];
							endTime = startendTiem[1];
							projectName = projectTexts[1];
							projectModel.setStartTime(startTime);
							projectModel.setEndTime(endTime);
							projectModel.setProjectName(projectName);
						}
					}
				}
				String labeltext = projecttextEle.select("[width=18%]").text();

				if (!(labeltext == null || labeltext.isEmpty())) {
					if (labeltext.indexOf("软件环境") != -1) {
						proSofEn = projecttextEle.select("[width=82%]").text();
						projectModel.setProSofEn(proSofEn);
					} else if (labeltext.indexOf("硬件环境") != -1) {
						proHardEn = projecttextEle.select("[width=82%]").text();
						projectModel.setProHardEn(proHardEn);
					} else if (labeltext.indexOf("开发工具") != -1) {
						proDevelopment = projecttextEle.select("[width=82%]")
								.text();
						projectModel.setProDevelopment(proDevelopment);
					} else if (labeltext.indexOf("项目描述") != -1) {
						content = projecttextEle.select("[width=82%]").text();
						projectModel.setContent(content);
					} else if (labeltext.indexOf("责任描述") != -1) {
						projectInfo = projecttextEle.select("[width=82%]")
								.text();
						projectModel.setProjectInfo(projectInfo);
					}
				}

			}
			projectmap.put(key, projectModel);
		}
	}

	/**
	 * 在校学习情况
	 * 
	 * @param ele
	 */
	public static void schoolStudy(Element ele) {

		ele.select("h2").prepend(";");// 在div前添加html内容;
		ele.select("div").prepend("asd");// 在div前添加html内容;
		ele.select("h3").remove();
		String winningTexts = ele.text();
		winningTexts = winningTexts.substring(5);
		String[] winningText = winningTexts.split(";");
		for (i = 0; i < winningText.length; i++) {
			String key = "medalModel" + i;
			medalModel = new Medal();
			String[] winningTexts2 = winningText[i].split("asd");
			for (j = 0; j < winningTexts2.length; j++) {
				if (j == 0) {
					medalName = winningTexts2[j];
				} else {
					if (medalInfo != null) {
						medalInfo += ";" + winningTexts2[j];
					} else {
						medalInfo = winningTexts2[j];
					}
				}
				medalModel.setMedalName(medalName);
				medalModel.setMedalInfo(medalInfo);
			}
			medalMap.put(key, medalModel);
		}

	}

	/**
	 * 专业技能
	 * 
	 * @param ele
	 */
	public static void proSkill(Element ele) {
		Elements proSkillEles = ele.select("table");
		proSkillEles.select("tr>td>hr").wrap("<li>;</li>");
		String proSkillelestoString = proSkillEles.toString();
		String[] proSkilljecteleselestoStrings = proSkillelestoString
				.split(";");

		for (i = 0; i < proSkilljecteleselestoStrings.length; i++) {
			if (i == 1) {

				proSkilljecteleselestoStrings[i] = "<table>"
						+ proSkilljecteleselestoStrings[i] + "</table>";
				Element proSkillelestoStringsele = Jsoup.parse(
						proSkilljecteleselestoStrings[i]).body();
				Elements proSkillelestoStringseles = proSkillelestoStringsele
						.select("tbody>tr");
				j = 0;
				for (Element proSkillEle : proSkillelestoStringseles) {
					String key = "proSkill" + j;
					skillModel = new Skill();
					Elements proSkillElesss = proSkillEle.select("td");
					int k = 0;
					for (Element proSkillEleeee : proSkillElesss) {
						if (k == 0) {
							skillName = proSkillEleeee.text();
							skillModel.setSkillName(skillName);
						} else if (k == 1) {
							skillInfo = proSkillEleeee.text();
							skillModel.setSkillInfo(skillInfo);
						} else if (k == 2) {
							skilltime = proSkillEleeee.text();
							skillModel.setTime(skilltime);
						}
						k++;
					}
					skillMap.put(key, skillModel);
					j++;
				}

			}
		}
	}

	/**
	 * 获得荣誉
	 * 
	 * @param ele
	 */
	public static void gainHonner(Element ele) {
		Elements honorEles = ele.select(">div");
		System.out.println(honorEles);
		String[] honorTexts = honorEles.text().split(" ");
		for (i = 0; i < honorTexts.length; i++) {
			System.out.println(honorTexts[i]);
			String key = "honor" + i;
			honorModel = new Honor();
			honorModel.setHonorInfo(honorTexts[i]);
			honorMap.put(key, honorModel);
		}
	}

	/**
	 * 培训经历
	 * 
	 * @param ele
	 */
	public static void trainExperience(Element ele) {
		/* System.out.println(ele); */
		Elements traineles = ele.select("table");
		traineles.select("tr>td>hr").wrap("<li>;</li>");
		String trainelestoString = traineles.toString();
		String[] trainelestoStrings = trainelestoString.split(";");
		for (i = 0; i < trainelestoStrings.length; i++) {
			String key = "train" + i;
			trainModel = new Train();
			trainelestoStrings[i] = "<table>" + trainelestoStrings[i]
					+ "</table>";
			Element trainelestoStringsele = Jsoup.parse(trainelestoStrings[i])
					.body();
			Elements trainelestoStringseles = trainelestoStringsele
					.select("tbody");
			for (Element traintextEle : trainelestoStringseles) {
				Elements trainextsssEles = traintextEle.select("td");
				int j = 0;
				for (Element traintextsssEle : trainextsssEles) {
					if (j == 0) {
						String[] startendTime = traintextsssEle.text().split(
								"--");
						startTime = startendTime[0];
						endTime = startendTime[1];
						trainModel.setStartTime(startTime);
						trainModel.setEndTime(endTime);

					} else if (j == 1) {
						trainInstitutions = traintextsssEle.text();
						trainModel.setTrainInstitutions(trainInstitutions);

					} else if (j == 2) {
						trainClass = traintextsssEle.text();
						trainModel.setTrainClass(trainClass);

					} else if (j == 3) {
						/* System.out.println(traintextsssEle); */

					} else if (j == 4) {
						trainInfo = traintextsssEle.text();
						trainModel.setTrainInfo(trainInfo);
					}
					j++;
				}
			}
			trainMap.put(key, trainModel);
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
		String path = "F:/eclipseworkspace/html样例/html样例/前程无忧/20088181340125008870.htm";
		/*String path =  "F:/eclipseworkspace/html样例/html样例/智联/137066.html";*/
		parse(path);
		System.out
				.println("-----------------------------------------------------------------------------");

	}
}
