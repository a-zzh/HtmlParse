package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hyx.parse.model.Basic2;
import org.hyx.parse.model.Credential;
import org.hyx.parse.model.Education;
import org.hyx.parse.model.Honor;
import org.hyx.parse.model.Job;
import org.hyx.parse.model.Medal;
import org.hyx.parse.model.OtherInfor;
import org.hyx.parse.model.Project;
import org.hyx.parse.model.Skill;
import org.hyx.parse.model.StudentsPractice;
import org.hyx.parse.model.Train;
import org.hyx.parse.util.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QModelParse {

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
	private static String zipCode;// 邮编
	private static String height;// 身高
	private static String weight;// 体重
	private static String currentSalary;// 目前薪资

	private static String expectnaturework;// 期望工作性质
	private static String expectindustry;// 期望从事行业
	private static String expectedCity;// 期望城市
	private static String expectedJob;// 期望职位
	private static String jobStatus;// 工作状态
	private static String expectedSalary;// 期望薪资
	private static String bonus;// 奖金

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
	private static String getTime;// 时间

	private static String skillName;// 技能名称
	private static String skillInfo;// 技能熟练程度
	private static String skilltime;// 使用时间

	private static String honorName;// 奖项名称
	private static String honorInfo;// 奖项信息

	private static String trainInstitutions;// 培训机构
	private static String trainClass;// 培训内容
	private static String trainInfo;// 培训信息

	private static String praTime;// 时间
	private static String practiceName;// 职位
	private static String practiceInfo;// 信息

	private static String otherInfor;// 附加信息

	private static Map<String, Job> jobMap = new LinkedHashMap<String, Job>();
	private static Map<String, Credential> certificateMap = new LinkedHashMap<String, Credential>();
	private static Map<String, Education> educationMap = new LinkedHashMap<String, Education>();
	private static Map<String, Project> projectmap = new LinkedHashMap<String, Project>();
	private static Map<String, Medal> medalMap = new LinkedHashMap<String, Medal>();
	private static Map<String, Skill> skillMap = new LinkedHashMap<String, Skill>();
	private static Map<String, Honor> honorMap = new LinkedHashMap<String, Honor>();
	private static Map<String, Train> trainMap = new LinkedHashMap<String, Train>();
	private static Map<String, StudentsPractice> StudentsPracticeMap = new LinkedHashMap<String, StudentsPractice>();

	private static Job jobModel;
	private static Education educationModel;
	private static Project projectModel;
	private static Basic2 basicModel2 = new Basic2();
	private static Credential certificateModel;// 证书
	private static Medal medalModel;// 学校获奖情况
	private static Skill skillModel;// 技能情况
	private static Honor honorModel;// 获得荣誉
	private static Train trainModel;// 培训经历
	private static StudentsPractice StudentsPracticeModel;// 培训经历
	private static OtherInfor otherInfoModel = new OtherInfor();// 其他信息
	private static util utilClass;
	private static int i = 0;
	private static int j = 0;
	private static Elements eles;
	private static String eletext;
	private static Document doc;

	private static void parse(String path) throws IOException {
		System.out.println(path);
		File input = new File(path);
		doc = Jsoup.parse(input, "utf-8");
		eles = doc.select("#divResume>tbody>tr>td>table>tbody>tr>td>table>tbody>tr>td>table");
		int k = 0;
		for (Element ele : eles) {
			/* System.out.println("ele =="+ele); */
			if (k == 0) {
				Elements tableEles = ele.select("tbody>tr");

				int l = 0;
				for (Element tableEle : tableEles) {
					if (l == 0) {
						name = tableEle.select("td>span>b").text();
						basicModel2.setName(name);
					} else if (l == 2) {
						/* System.out.println(tableEle); */
						String tableEleTostring = tableEle.toString().replaceAll(" | ", ";").replaceAll("&nbsp;", ";")
								.replaceAll(";;", ";").replaceAll("（", ";").replaceAll("）", ";");
						Element tableEleTostringEle = Jsoup.parse(tableEleTostring).body();
						String basicText = tableEleTostringEle.text();
						String[] basicTexts = basicText.split(";");
						for (i = 0; i < basicTexts.length; i++) {
							String text = basicTexts[i];
							if (text.indexOf("工作经验") != -1) {
								workingyear = text;
								basicModel2.setWorkingyear(workingyear);
							} else if (text.indexOf("女") != -1 || text.indexOf("男") != -1) {
								gender = text;
								basicModel2.setGender(gender);
							} else if (text.indexOf("岁") != -1) {
								age = text;
								basicModel2.setAge(age);
							} else if (text.indexOf("年") != -1) {
								birthday = text;
								basicModel2.setBirth(birthday);
							} else if (text.indexOf("cm") != -1) {
								height = text;
								basicModel2.setHeight(height);
							} else if (text.indexOf("员 ") != -1) {
								politics = text;
								basicModel2.setPolitics(politics);
							}

						}
					} else if (l == 3) {
						/* System.out.println(tableEle); */
						latteraddress = tableEle.select("td[width=42%]").text();
						basicModel2.setLatteraddress(latteraddress);
						account = tableEle.select("td[width=20%]").text();
						basicModel2.setAccount(account);
					} else if (l == 4) {
						/* System.out.println(tableEle); */
						mobile = tableEle.select("td[colspan=3]").text();
						basicModel2.setMobile(mobile);

					} else if (l == 5) {
						/* System.out.println(tableEle); */
						email = tableEle.select("td[colspan=3]").text();
						basicModel2.setEmail(email);
					}
					l++;
				}
			} else if (k == 1) {
				Elements tableEles = ele.select("tbody>tr>td>table>tbody");
				int l = 0;
				for (Element tableEle : tableEles) {
					if (l == 1) {
						Elements trEles = tableEle.select("tr");
						trEles.select("[colspan=2]").remove();
						for (Element trEle : trEles) {
							String[] texts = trEle.text().split("：");
							if (trEle.text().indexOf("学　历：") != -1) {
								education = texts[1];
								basicModel2.setEducation(education);
							} else if (trEle.text().indexOf("学　校：") != -1) {
								school = texts[1];
								basicModel2.setSchool(school);
							}
						}
					}
					l++;
				}
			} else if (k == 2) {
				Elements tableEles = ele.select("tbody>tr>td>table");
				int l = 0;
				for (Element table : tableEles) {
					if (l == 0) {
						/* System.out.println("table = "+table); */
						currentSalary = table.select("[width=221]").text();
						basicModel2.setCurrentSalary(currentSalary);
					} else if (l == 1) {
						/* System.out.println("table = "+table); */
						table.select(".cvtitle").remove();
						selfassessment = table.text();
						basicModel2.setSelfassessment(selfassessment);
					} else if (l == 2) {
						/* System.out.println("table = "+table); */
						Elements trEles = table.select("td");
						for (Element trEle : trEles) {
							String[] texts = trEle.text().split("：");
							if (trEle.text().indexOf("到岗时间：") != -1) {
								jobStatus = texts[1];
								basicModel2.setJobStatus(jobStatus);
							} else if (trEle.text().indexOf("工作性质：") != -1) {
								expectnaturework = texts[1];
								basicModel2.setExpectnaturework(expectnaturework);
							} else if (trEle.text().indexOf("希望行业：") != -1) {
								expectindustry = texts[1];
								basicModel2.setExpectindustry(expectindustry);
							} else if (trEle.text().indexOf("目标地点：") != -1) {
								expectedCity = texts[1];
								basicModel2.setExpectedCity(expectedCity);
							} else if (trEle.text().indexOf("期望薪资：") != -1) {
								expectedSalary = texts[1];
								basicModel2.setExpectedSalary(expectedSalary);
							} else if (trEle.text().indexOf("目标职能：") != -1) {
								expectedJob = texts[1];
								basicModel2.setExpectedJob(expectedJob);
							} else if (trEle.text().indexOf("求职状态：") != -1) {
								jobStatus += texts[1];
								basicModel2.setJobStatus(jobStatus);
							}
						}
					} else if (l == 4) {

						table.select("tbody>tr>td[height=20]").wrap("<li>?</li>");
						String eletoString = table.toString();

						String[] eletoStrings = eletoString.split("\\?");
						for (int p = 0; p < eletoStrings.length; p++) {
							if (p > 0) {
								eletoStrings[p] = "<table>" + eletoStrings[p] + "</table>";
							}

							Element eletoStringele = Jsoup.parse(eletoStrings[p]).body();

							Elements eletoStringeles = eletoStringele.select("body");

							String cvtitle = eletoStringeles.select(".cvtitle").text();
							int cateInt = util.category(cvtitle.replaceAll(" ", ""));
							System.out.println("catetext jobExperience ==" + p + ":" + cvtitle.replaceAll(" ", ""));
							System.out.println(cateInt);
							switch (cateInt) {
							case 0:
								/*
								 * jobIntension(ele);// 求职意向
								 */ break;
							case 1:

								selfAss(eletoStringele);// 自我评价
								break;
							case 2:
								jobExperience(eletoStringele);// 工作经历
								break;
							case 3:
								certificate(eletoStringele);// 证书
								break;
							case 4:

								projectExperience(eletoStringele);// 项目经历
								break;
							case 5:

								educationContent(eletoStringele);// 教育经历

								break;
							case 6:

								language(eletoStringele);// 语言能力
								break;
							case 7:
								// 求职信 暂未发现
								break;
							case 8:

								proSkill(eletoStringele);// 专业技能
								break;
							case 9:

								eletoStringele = Jsoup.parse(eletoStrings[p + 1]).body();
								gainMedal(eletoStringele);// 获得荣誉

								break;
							case 10:

								/*
								 * * schoolStudy(ele);//在校学习情况
								 */
								break;
							case 12:

								trainExperience(eletoStringele);// 培训经历
								break;
							case 13:
								/*
								 * addInformation(ele222);// 附加信息
								 */ break;
							case 14:
								eletoStringele = Jsoup.parse(eletoStrings[p + 1]).body();
								StudentsPractice(eletoStringele);// 在校实践经历
								break;

							case 15:
								eletoStringele = Jsoup.parse(eletoStrings[p + 1]).body();
								StudentsOffice(eletoStringele);// 在校职务
								break;
							}
						}

					}
					l++;
				}
			}
			k++;
		}
		System.out.println(basicModel2.toString());

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Job> entry : jobMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Education> entry : educationMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Medal> entry : medalMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, StudentsPractice> entry : StudentsPracticeMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Credential> entry : certificateMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Train> entry : trainMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Skill> entry : skillMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Entry<String, Project> entry : projectmap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

	}

	/**
	 * 工作经历
	 * 
	 * @param ele
	 */
	public static void jobExperience(Element ele) {
		// 工作经历信息
		/* System.out.println(ele); */
		Elements jobeles = ele.select("table");
		jobeles.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String jobelestoString = jobeles.toString();
		String[] jobelestoStrings = jobelestoString.split("!");
		for (i = 0; i < jobelestoStrings.length; i++) {

			String key = "job" + i;
			jobModel = new Job();
			if (i != 0) {
				jobelestoStrings[i] = "<table>" + jobelestoStrings[i] + "</table>";
			}
			Element jobelestoStringsele = Jsoup.parse(jobelestoStrings[i]).body();
			Elements jobEles = jobelestoStringsele.select("table>tbody>tr>td>table");
			if (i > 0) {
				jobEles = jobelestoStringsele.select("table");
			}
			Elements timeInfoEles = jobEles.select("[colspan=2]");
			j = 0;
			for (Element timeInfoEle : timeInfoEles) {
				if (j == 0) {
					String Info = timeInfoEle.text();
					String[] infos = Info.split("：");
					for (int k = 0; k < infos.length; k++) {
						if (k == 0) {
							String[] times = infos[k].split("--");
							startTime = times[0];
							endTime = times[1];
							jobModel.setStartTime(startTime);
							jobModel.setEndTime(endTime);
						} else if (k == 1) {
							company = infos[k];
							jobModel.setCompany(company);
						}
					}
				}
				if (j == 1) {
					duty = timeInfoEle.text();
					jobModel.setDuty(duty);
				}
				j++;
			}
			jobEles.select("[colspan=2]").remove();
			Elements hangyeInfoEles = jobEles.select("tbody>tr");
			j = 0;
			for (Element hangyeInfoEle : hangyeInfoEles) {
				if (j == 1) {

					jobIndustry = hangyeInfoEle.select("[width=78%]").text();
					jobModel.setJobIndustry(jobIndustry);
				} else if (j == 2) {
					/* System.out.println("hangyeInfoEle == "+hangyeInfoEle); */
					department = hangyeInfoEle.select("text_left").text();
					position = hangyeInfoEle.select("text").text();
					jobModel.setDepartment(department);
					jobModel.setPosition(position);
				}
				j++;
			}
			jobMap.put(key, jobModel);
		}
	}

	/**
	 * 教育信息
	 */
	public static void educationContent(Element ele) {
		// 教育信息

		Elements edueles = ele.select("table>tbody>tr>td>table");
		edueles.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String eduelestoString = edueles.toString();
		String[] eduelestoStrings = eduelestoString.split("!");
		for (i = 0; i < eduelestoStrings.length; i++) {
			String key = "edu" + i;
			educationModel = new Education();
			eduelestoStrings[i] = "<table>" + eduelestoStrings[i] + "</table>";
			Element eduelestoStringsele = Jsoup.parse(eduelestoStrings[i]).body();
			Elements eduelestoStringseles = eduelestoStringsele.select("tbody");
			for (Element edutextEle : eduelestoStringseles) {
				Elements edutextsssEles = edutextEle.select("td");

				int j = 0;
				for (Element edutextsssEle : edutextsssEles) {
					if (j == 0) {
						String[] startendTime = edutextsssEle.text().split("--");
						startTime = startendTime[0];
						endTime = startendTime[1];
						educationModel.setStartTime(startTime);
						educationModel.setEndTime(endTime);
					} else if (j == 1) {
						school = edutextsssEle.text();
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
	 * 获得荣誉
	 * 
	 * @param ele
	 */
	public static void gainMedal(Element ele) {
		Elements honorEles = ele.select("tr");
		int i = 0;
		for (Element MedalEle : honorEles) {
			medalModel = new Medal();
			Elements tdMedalEles = MedalEle.select("td");
			String key = "Medal" + i;
			j = 0;
			for (Element tdMedalEle : tdMedalEles) {
				if (j == 0) {
					getTime = tdMedalEle.text();
					medalModel.setGetTime(getTime);
				}
				if (j == 1) {
					medalName = tdMedalEle.text();
					medalModel.setMedalName(medalName);
				}
				if (j == 2) {
					medalInfo = tdMedalEle.text();
					medalModel.setMedalInfo(medalInfo);
				}
				j++;
				medalMap.put(key, medalModel);
			}
			i++;
		}

	}

	/**
	 * 在校实践经历
	 * 
	 * @param ele
	 */
	public static void StudentsPractice(Element ele) {
		ele.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String praelestoString = ele.toString();
		String[] praelestoStrings = praelestoString.split("!");

		for (int j = 0; j < praelestoStrings.length; j++) {
			String key = "StudentsPractice" + j;
			StudentsPracticeModel = new StudentsPractice();
			praelestoStrings[j] = "<table>" + praelestoStrings[j] + "</table>";
			Elements praEles = Jsoup.parse(praelestoStrings[j]).body().select("tr");
			int k = 0;
			for (Element praEle : praEles) {
				if (k == 0) {
					praTime = praEle.select(".text_left").text();
					practiceName = praEle.select(".text").text();

					StudentsPracticeModel.setPracticeTime(praTime);
					StudentsPracticeModel.setPracticeName(practiceName);
				} else if (k == 1) {
					practiceInfo = praEle.select(".text_left").text();
					StudentsPracticeModel.setPracticeInfo(practiceInfo);
				}
				k++;
			}
			StudentsPracticeMap.put(key, StudentsPracticeModel);
		}

	}

	/**
	 * 在校职务
	 * 
	 * @param ele
	 */
	public static void StudentsOffice(Element ele) {
		ele.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String praelestoString = ele.toString();
		String[] praelestoStrings = praelestoString.split("!");
		for (int j = 0; j < praelestoStrings.length; j++) {
			String key = "StudentsOffice" + j;
			StudentsPracticeModel = new StudentsPractice();
			praelestoStrings[j] = "<table>" + praelestoStrings[j] + "</table>";
			Elements praEles = Jsoup.parse(praelestoStrings[j]).body().select("tr");
			int k = 0;
			for (Element praEle : praEles) {
				if (k == 0) {
					praTime = praEle.select(".text_left").text();
					practiceName = praEle.select(".text").text();

					StudentsPracticeModel.setPracticeTime(praTime);
					StudentsPracticeModel.setPracticeName(practiceName);
				} else if (k == 1) {
					practiceInfo = praEle.select(".text_left").text();
					StudentsPracticeModel.setPracticeInfo(practiceInfo);
				}
				k++;
			}
			StudentsPracticeMap.put(key, StudentsPracticeModel);
		}
	}

	/**
	 * 证书信息
	 */
	public static void certificate(Element ele) {
		// 证书信息
		Elements cettificateeles = ele.select("table");
		cettificateeles.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String cerelestoString = cettificateeles.toString();
		String[] cerjecteleselestoStrings = cerelestoString.split("!");

		for (i = 0; i < cerjecteleselestoStrings.length; i++) {
			String key = "certifical" + i;
			certificateModel = new Credential();
			cerjecteleselestoStrings[i] = "<table>" + cerjecteleselestoStrings[i] + "</table>";
			Element cerelestoStringsele = Jsoup.parse(cerjecteleselestoStrings[i]).body();
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
	 * 培训经历
	 * 
	 * @param ele
	 */
	public static void trainExperience(Element ele) {
		Elements traineles = ele.select("table>tbody>tr>td>table");
		traineles.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String trainelestoString = traineles.toString();

		String[] trainelestoStrings = trainelestoString.split("!");
		for (i = 0; i < trainelestoStrings.length; i++) {
			String key = "train" + i;
			trainModel = new Train();
			trainelestoStrings[i] = "<table>" + trainelestoStrings[i] + "</table>";
			Element trainelestoStringsele = Jsoup.parse(trainelestoStrings[i]).body();
			Elements trainelestoStringseles = trainelestoStringsele.select("tbody");
			for (Element traintextEle : trainelestoStringseles) {
				Elements trainextsssEles = traintextEle.select("td");
				int j = 0;
				for (Element traintextsssEle : trainextsssEles) {
					if (j == 0) {

						String[] startendTime = traintextsssEle.text().split("--");
						System.out.println(startendTime.length);
						startTime = startendTime[0];
						/* endTime = startendTime[1]; */
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
	 * 项目经历
	 */
	public static void projectExperience(Element ele) {
		Elements projecteles = ele.select("table>tbody>tr>td>table");
		projecteles.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String jobelestoString = projecteles.toString();
		String[] projecteleselestoStrings = jobelestoString.split("!");

		for (i = 0; i < projecteleselestoStrings.length; i++) {
			String key = "project" + i;
			projectModel = new Project();
			projecteleselestoStrings[i] = "<table>" + projecteleselestoStrings[i] + "</table>";
			Element jobelestoStringsele = Jsoup.parse(projecteleselestoStrings[i]).body();
			Elements jobelestoStringseles = jobelestoStringsele.select("tr");
			for (Element projecttextEle : jobelestoStringseles) {
				String projectInfotoString = projecttextEle.select("[colspan=4]").toString();
				if (!(projectInfotoString == null || projectInfotoString.isEmpty())) {
					if (projectInfotoString.indexOf("：") != -1) {
						String projectInfotoStringtext = Jsoup.parse(projectInfotoString).body().text();
						String[] projectTexts = projectInfotoStringtext.split("：");
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
						proDevelopment = projecttextEle.select("[width=82%]").text();
						projectModel.setProDevelopment(proDevelopment);
					} else if (labeltext.indexOf("项目描述") != -1) {
						content = projecttextEle.select("[width=82%]").text();
						projectModel.setContent(content);
					} else if (labeltext.indexOf("责任描述") != -1) {
						projectInfo = projecttextEle.select("[width=82%]").text();
						projectModel.setProjectInfo(projectInfo);
					}
				}

			}
			projectmap.put(key, projectModel);
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
	 * 专业技能
	 * 
	 * @param ele
	 */
	public static void proSkill(Element ele) {
		Elements proSkillEles = ele.select("table>tbody>tr>td>table");
		proSkillEles.select("tr>td>hr").wrap("<li><span>!</span></li>");
		String proSkillelestoString = proSkillEles.toString();
		String[] proSkilljecteleselestoStrings = proSkillelestoString.split("!");

		for (i = 0; i < proSkilljecteleselestoStrings.length; i++) {
			if (i == 1) {
				proSkilljecteleselestoStrings[i] = "<table>" + proSkilljecteleselestoStrings[i] + "</table>";
				Element proSkillelestoStringsele = Jsoup.parse(proSkilljecteleselestoStrings[i]).body();
				Elements proSkillelestoStringseles = proSkillelestoStringsele.select("tbody>tr");
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

	public static void main(String[] args) throws IOException {
		String path = "F:/简历分类/前程无忧/G/16186176.html";
		/* String path = "F:/eclipseworkspace/html样例/html样例/智联/137066.html"; */
		parse(path);
		System.out.println("-----------------------------------------------------------------------------");

	}
}
