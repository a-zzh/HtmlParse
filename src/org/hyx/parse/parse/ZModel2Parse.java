package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.JobOriginatingUserName;

import org.hyx.parse.model.Basic;
import org.hyx.parse.model.Basic2;
import org.hyx.parse.model.Education;
import org.hyx.parse.model.Honor;
import org.hyx.parse.model.Job;
import org.hyx.parse.model.Credential;
import org.hyx.parse.model.Medal;
import org.hyx.parse.model.OtherInfor;
import org.hyx.parse.model.Project;
import org.hyx.parse.model.Skill;
import org.hyx.parse.model.Train;
//import org.hyx.parse.util.Verificationcodestart;
import org.hyx.parse.util.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZModel2Parse {

	public static String zipCode;// 邮编
	public static String idcard;// 身份证号
	public static String name;// 姓名
	public static String gender;// 性别
	public static String company;// 公司
	public static String title;// 职位
	public static String city;// 城市
	public static String workingyear;// 工作年限
	public static String education;// 学历
	public static String latteraddress;// 暂住地
	public static String maritalstatus;// 婚姻状况
	public static String account;// 户口
	public static String phone;// 固定电话
	public static String mobile;// 手机
	public static String email;// 电子邮件
	public static String age;// 年龄
	public static String birthday;// 生日
	public static String degree;// 学位
	public static String school;// 学校
	public static String major;// 专业
	public static String language;// 语言能力
	public static String photoPath;// 照片保存路径

	public static String expectnaturework;// 期望工作性质
	public static String expectindustry;// 期望从事行业
	public static String currentSalary;// 目前薪资
	public static String expectedCity;// 期望城市
	public static String expectedJob;// 期望职位
	public static String jobStatus;// 工作状态
	public static String expectedSalary;// 期望薪资

	public static String selfassessment;// 自我评价
	public static String politics;// 政治面貌

	public static String info = "";// 其他信息
	public static String jobIndustry;// 行业
	public static String duration;// 在位时长
	public static String startTime;// 起始时间
	public static String endTime;// 结束时间
	public static String position;// 职位
	public static String salary;// 薪资
	public static String jobInfo;// 工作描述信息（其它关于工作描述的信息）

	public static String certificateTime;// 证书获得时间
	public static String certificate;// 证书名称

	public static String projectName;// 项目名称
	public static String content;// 项目内容

	public static String medalName;// 奖项名称
	public static String medalInfo;// 奖项信息

	public static String skillName;// 技能名称
	public static String skillInfo;// 技能熟练程度
	public static String silltime;// 使用时间

	public static String honorName;// 奖项名称
	public String honorInfo;// 奖项信息

	public static Map<String, Job> jobMap;
	public static Map<String, Credential> certificateMap;
	public static Map<String, Education> educationMap;
	public static Map<String, Project> projectmap;
	public static Map<String, Medal> medalMap;
	public static Map<String, Skill> skillMap;
	public static Map<String, Honor> honorMap;

	public static Job jobModel;
	public static Education educationModel;
	public static Project projectModel;
	public static Basic2 basicModel2;
	public static Credential certificateModel;// 证书
	public static Medal medalModel;// 学校获奖情况
	public static Skill skillModel;// 技能情况
	public static Honor honorModel;// 获得荣誉
	public static util utilClass;
	public static int i = 0;
	public static int j = 0;
	public static Elements eles;
	public static String eletext;
	public static Document doc;
	public static String errorPhotoURL = "http://rd.zhaopin.com/img/lookResumes.jpg";

	public static void parse(String path) {
		basicModel2 = new Basic2();
		jobMap = new LinkedHashMap<String, Job>();
		certificateMap = new LinkedHashMap<String, Credential>();
		educationMap = new LinkedHashMap<String, Education>();
		projectmap = new LinkedHashMap<String, Project>();
		medalMap = new LinkedHashMap<String, Medal>();
		skillMap = new LinkedHashMap<String, Skill>();
		honorMap = new LinkedHashMap<String, Honor>();
		// trainMap = new LinkedHashMap<String, Train>();

		// otherInfoModel = new OtherInfor();// 其他信息
		try {

			File input = new File(path);
			doc = Jsoup.parse(input, "UTF-8");

			basicInfo(doc);

			for (Element ele : doc.select("div.resume-preview-all")) {

				String str = ele.select("h3").text();
				//System.out.println(str);

				if (str.indexOf("求职意向") != -1) {

					jobIntension(ele);// ok
				} else if (str.indexOf("自我评价") != -1) {
					selfassessment = ele.select("div>div").text();
					basicModel2.setSelfassessment(selfassessment);
				} else if (str.indexOf("工作经历") != -1) {
					jobExperience(ele);// ok
				}else if (str.indexOf("项目经历") != -1) {
					projectExperience(ele);// ok
				} else if (str.indexOf("教育经历") != -1) {
					educationContent(ele); // ok
				} else if (str.indexOf("证书") != -1) {
					certificate(ele);// ok
				} else if (str.indexOf("语言能力") != -1) {// 专业技能 获得荣誉 在校学习情况  projectExperience  项目经历
					language = ele.select("div>div").text();
					basicModel2.setLanguage(language);
				} else if (str.indexOf("技能") != -1) {
					proSkill(ele);//
				} else if (str.indexOf("荣誉") != -1) {
					gainHonner(ele);//
				} else if (str.indexOf("学习情况") != -1) {
					schoolStudy(ele);//
				}

			}
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void basicInfo(Document doc2) {
		try {
			name = doc.select("div.resume-preview-main-title>div").get(0).text();
			basicModel2.setName(name);

			String str = doc.select("div.summary-top>span").toString();

			str = str.replace("&nbsp;", " ").replace("    ", " ");
			str = str.replace("</span>", "");
			String[] s = str.split(" ");
			for (int i = 0; i < s.length; i++) {
				try {
					//System.out.println(s[i]);
					if (0 == i) {
						gender = s[i].substring(s[i].length() - 1, s[i].length());
						basicModel2.setGender(gender);
					} else if (1 == i) {
						age = s[i].split("\\(")[0];
						basicModel2.setAge(age);
						birthday = s[i].split("\\(")[1].split("\\)")[0];
						basicModel2.setBirth(birthday);
					} else if (2 == i) {
						workingyear = s[i];
						basicModel2.setWorkingyear(workingyear);
					} else if (3 == i) {
						degree = s[i];
						basicModel2.setDegree(degree);
					} else if (4 == i) {
						maritalstatus = s[i].substring(0, 2);
						basicModel2.setMaritalstatus(maritalstatus);
					}
				} catch (Exception e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			doc.select("div.summary-top>br").wrap("<li>;</li>");
			str = doc.select("div.summary-top").text().split(";")[1];
			s = str.split("\\|");
			for (int i = 0; i < s.length; i++) {
				try {
					if (s[i].indexOf("居住地") != -1) {
						city = s[i].split("：")[1];
						basicModel2.setCity(city);
					} else if (s[i].indexOf("户口") != -1) {
						account = s[i].split("：")[1];
						basicModel2.setAccount(account);
					} else {
						politics = s[i];
						basicModel2.setPolitics(politics);
					}
				} catch (Exception e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			doc.select("div.summary-bottom>br").wrap("<li>;</li>");
			str = doc.select("div.summary-bottom").text();
			s = str.split(";");
			for (String ss : s) {
				try {
					if (!ss.equals("")) {
						if (ss.indexOf("身份证") != -1) {
							idcard = ss.split("：")[1];
							basicModel2.setIdcard(idcard);

						} else if (ss.indexOf("地址") != -1) {
							latteraddress = ss.split("：")[1].split("\\|")[0];
							basicModel2.setLatteraddress(latteraddress);
							zipCode = ss.split("\\|")[1].split("：")[1];
							basicModel2.setZipCode(zipCode);
						} else if (ss.indexOf("手机") != -1) {
							mobile = ss.split("：")[1];
							basicModel2.setMobile(mobile);
						} else if (ss.indexOf("E-mail") != -1) {
							email = ss.split("：")[1];
							basicModel2.setEmail(email);

						}

					} 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	 
	/**
	 * 求职意向
	 * 
	 * @param ele2
	 */
	public static void jobIntension(Element ele2) {
		// 求职意向信息
		for (Element ele : ele2.select("table>tbody>tr")) {
			try {
				String str = ele.text();
			//	System.out.println(str);
				if (str.indexOf("期望工作地区") != -1) {
					expectedCity = str.split("：")[1];
					basicModel2.setExpectedCity(expectedCity);
				} else if (str.indexOf("期望月薪") != -1) {
					expectedSalary = str.split("：")[1];
					basicModel2.setExpectedSalary(expectedSalary);
				} else if (str.indexOf("目前状况") != -1) {
					jobStatus = str.split("：")[1];
					basicModel2.setJobStatus(jobStatus);

				} else if (str.indexOf("期望工作性质") != -1) {
					expectnaturework = str.split("：")[1];
					basicModel2.setExpectnaturework(expectnaturework);
				} else if (str.indexOf("期望从事职业") != -1) {
					expectedJob = str.split("：")[1];
					basicModel2.setExpectedJob(expectedJob);

				} else if (str.indexOf("期望从事行业") != -1) {
					expectindustry = str.split("：")[1];
					basicModel2.setExpectindustry(expectindustry);
				}
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
	public static void jobExperience(Element ele) {
		int i, j, k = 0, n = 0;
		String str = null, str2 = null;
		int len = ele.select("div>h2").size();

		for (Element el3 : ele.select("div>div")) {

			try {
				if (k % 2 == 0) {
					str2 = el3.text();

				} else if (k % 2 == 1) {
					i = 0;
					for (Element el : ele.select("div>h2")) {
						j = 0;
						for (Element el2 : ele.select("div>h5")) {

							if ((k - 1) / 2 == i && i == j) {
								Job job = new Job();

								// 时间 公司
								str = el.toString().replace("&nbsp;", "; ").replace("; ;", ";");
								int m = 0;
								for (String s : str.split(";")) {
									if (0 == m) {
										startTime = s.substring(s.indexOf("<h2>") + 4, s.indexOf("-"));
										endTime = s.substring(s.indexOf("-") + 1, s.length());

										job.setStartTime(startTime);
										job.setEndTime(endTime);
									} else if (1 == m) {
										company = s;
										job.setCompany(company);

									} else if (2 == m) {
										duration = s.substring(1, s.length() - 1);
										job.setDuration(duration);
									}

									m++;
								}

								// 职位薪资
								str = el2.text();
								if (str.indexOf("|") != -1) {
									m = 0;
									for (String s : str.split("\\|")) {
										if (0 == m) {
											title = s;
											job.setPosition(title);

										} else if (s.indexOf("元/月") != -1) {
											salary = s;
											job.setSalary(salary);
										}
										m++;
									}

								} else {
									title = str;
									job.setPosition(title);

								}

								// 行业
								info="";
								m = 0;
								if (str2.indexOf("\\|") != 0)
									for (String ss : str2.split("\\|")) {
										if (0 == m) {
											jobIndustry = ss;
											job.setJobIndustry(jobIndustry);
										} else if (1 == m) {
											info += ss + " ";
											job.setInfo(info);
										} else if (2 == m) {
											info += ss + " ";
											job.setInfo(info);
										}

										m++;
									}
								else {
									jobIndustry = str2;
									job.setJobIndustry(jobIndustry);
								}

								// 工作描述
								str = el3.text();
								jobInfo = str.substring(str.indexOf("工作描述：") + 5, str.length());
								job.setJobInfo(jobInfo);
								jobMap.put((n++) + "", job);
								//System.out.println(job + "---------");
							}
							j++;
						}

						i++;
					}
				}
				k++;
			} catch (Exception e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 教育信息
	 */
	public static void educationContent(Element ele) {
		try {
			ele.select("div>div>br").wrap("<li>;</li>");
			// System.out.println(ele.text());
			String st = ele.select("div>div").toString().replaceAll("&nbsp;", ";").replaceAll(";;", "&");
			// System.out.println(st);
			Pattern p = Pattern.compile("<[^>]+>");
			Matcher m = p.matcher(st);
			st = m.replaceAll("");
			String[] str = st.split(";");
			int n = 0;
			for (String s : str) {
				if (!s.trim().equals("")) {
					//System.out.println(s);
					Education edu = new Education();
					int i = 0;
					for (String ss : s.split("&")) {
						if (0 == i) {
							startTime = ss.substring(0, ss.indexOf("-")).replaceAll("\n", "");
							endTime = ss.substring(ss.indexOf("-") + 1, ss.length());
							edu.setStartTime(startTime);
							edu.setEndTime(endTime);
						} else if (1 == i) {
							school = ss.replaceAll("\n", "");
							edu.setSchool(school);

						} else if (2 == i) {
							major = ss.replaceAll("\n", "");
							edu.setMajor(major);

						} else if (3 == i) {
							degree = ss.replaceAll("\n", "");
							edu.setDegree(degree);
							educationMap.put((n++) + "", edu);
						}
						i++;
						// System.out.println(ss);
					}

				}

			}
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 证书信息
	 */
	public static void certificate(Element ele) {
		try {
			int i = 0, n = 0;
			for (Element el : ele.select("div>h2")) {
				Credential cd = new Credential();
				// System.out.println(el.text());
				i = 0;
				for (String ss : el.text().split("  ")) {
					String str = el.toString();
				//	System.out.println(ss);

					if (0 == i) {
						certificateTime = ss;
						cd.setCertificateTime(certificateTime);
					} else if (1 == i) {
						certificate = ss;
						cd.setCertificate(certificate);

						certificateMap.put((n++) + "", cd);
					}
					i++;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}

	}

	/**
	 * 项目经历
	 */
	public static void projectExperience(Element ele) {
		try {
			/* System.out.println(ele); */
			Elements projectExperienceEles = ele.select("div>h2");
			i = 0;
			for (Element projectExperienceEle : projectExperienceEles) {
				String key = "project" + i;
				projectModel = new Project();
				String projectExperienceEleString = projectExperienceEle.toString().replaceAll("&nbsp;+", ";");
				projectExperienceEleString = projectExperienceEleString.replaceAll(";+", ";");
				projectExperienceEleString = projectExperienceEleString.replaceAll(" - ", ":");

				String projectExperienceText = Jsoup.parse(projectExperienceEleString).body().text();

				String[] projectExperienceTexts = projectExperienceText.split(";");
				for (j = 0; j < projectExperienceTexts.length; j++) {
					if (j == 0) {
						String[] startendTiem = projectExperienceTexts[i].split(":");
						startTime = startendTiem[0];
						endTime = startendTiem[1];
						projectModel.setStartTime(startTime);
						projectModel.setEndTime(endTime);
						// System.out.println("startTime:"+startTime);
						// System.out.println("endTime:"+endTime);
					} else if (j == 1) {
						projectName = projectExperienceTexts[j];
						projectModel.setProjectName(projectName);
						// System.out.println("projectName:"+projectName);
					}
				}
				content = ele.select("div>table").text();
				projectModel.setContent(content);
				// System.out.println("content="+content);
				projectmap.put(key, projectModel);
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	/**
	 * 在校学习情况
	 * 
	 * @param ele
	 */
	public static void schoolStudy(Element ele) {

		try {
			ele.select("h2").prepend(";");// 在div前添加html内容;
			ele.select("div").prepend("asd");// 在div前添加html内容;
			ele.select("h3").remove();
			String winningTexts = ele.text();
			winningTexts = winningTexts.substring(5);
			String[] winningText = winningTexts.split(";");
			for (i = 0; i < winningText.length; i++) {
				String key = "medalModel" + i;
				medalModel = new Medal();
				medalInfo="";
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 专业技能
	 * 
	 * @param ele
	 */
	public static void proSkill(Element ele) {
		try {
			Elements preSkillEles = ele.select(">div");
			String preSkillElesASteinf = preSkillEles.toString();
			preSkillElesASteinf = preSkillElesASteinf.replaceAll("<br>", ";");
			preSkillElesASteinf = Jsoup.parse(preSkillElesASteinf).text();
			String[] preSkillElesASteinfTexts = preSkillElesASteinf.split(";");
			for (i = 0; i < preSkillElesASteinfTexts.length; i++) {
				String key = "Skill" + i;
				skillModel = new Skill();
				String[] preSkillStrings = preSkillElesASteinfTexts[i].split("：");
				for (j = 0; j < preSkillStrings.length; j++) {
					if (j == 0) {
						skillName = preSkillStrings[j];
						skillModel.setSkillName(skillName);
					} else {
						String[] skillInftimeString = preSkillStrings[j].replaceAll(" ", ";").split(";");
						for (int k = 0; k < skillInftimeString.length; k++) {
							if (k == 0) {
								skillInfo = skillInftimeString[0];
								skillModel.setInfo(skillInfo);
							} else if (k == 2) {
								silltime = skillInftimeString[2];
								skillModel.setTime(silltime);
							}
						}
					}
				}
				skillMap.put(key, skillModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得荣誉
	 * 
	 * @param ele
	 */
	public static void gainHonner(Element ele) {
		try {
			Elements honorEles = ele.select(">div");
			String[] honorTexts = honorEles.text().split(" ");
			for (i = 0; i < honorTexts.length; i++) {
				String key = "honor" + i;
				honorModel = new Honor();
				honorModel.setHonorInfo(honorTexts[i]);
				honorMap.put(key, honorModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try { // L:/简历分类/智联/1946/智联招聘_赵晨_游戏测试_中文_20150828_79775206.html
			String path1 = "L:/简历分类/智联/1950/智联招聘_赵阳_俄语翻译_中文_20150827_80449139.html";

			parse(path1);
			System.out.println("工作经验  " + jobMap.values());
			System.out.println("证书   " + certificateMap.values());
			System.out.println("教育经历  " + educationMap.values());
			System.out.println("项目经验  " + projectmap.values());
			System.out.println("所获奖励  " + medalMap.values());
			// System.out.println("社会经验 " + socialMap.values());
			System.out.println("技能  " + skillMap.values());
			// System.out.println("培训信息 " + trainMap.values());
			//System.out.println("校内职务 " + StudentsJobMap.values());
			// System.out.println("附件 " + attMap.values());
			System.out.println("基本信息  " + basicModel2.toString());
			for (Job job : jobMap.values()) {
				System.out.println(job.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
