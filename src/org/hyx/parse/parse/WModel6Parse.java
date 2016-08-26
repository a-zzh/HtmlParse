package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hyx.parse.model.Attachment;
import org.hyx.parse.model.Basic2;
import org.hyx.parse.model.Credential;
import org.hyx.parse.model.Education;
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

public class WModel6Parse {

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

	public static Map<String, Basic2> basicMap;// 简历列表

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

	public WModel6Parse() {

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

		basicMap = new LinkedHashMap<String, Basic2>();
		try {

			File input = new File(path);
			doc = Jsoup.parse(input, "gbk");
			// model
			// eles = doc.select("head>title");
			// eles = doc.select("tbody>tr[style=background:#C2DBF9;]");
			//
			// System.out.println(eles);
			// System.exit(0);

			String header = doc.select("div[id=header]").text();
			int row = 0;
			Pattern p = Pattern.compile("\\d{1,3}");
			Matcher m = p.matcher(header);
			if (m.find())
				row = Integer.parseInt(m.group());
			// ; 1 ; 倪雷健(30132227) ; 男 ; 25 ; 三年以上 ; 13758376734 ;
			// leijian_ni@hotmail.com ; 嘉兴 ; 6000-7999 ; 2012-10-30 
			String[] str = null;
			if (row > 0) {
				int i = 0, k = 0;
				for (Element ele : doc.select("td[valign=top]>table>tbody>tr")) {

					try {
						if (i > 0) {
							if (i % 2 == 1) {
								ele.select("td").wrap("<li>;<li>");
								str = ele.text().split(";");
								name = str[2].split("\\(")[0];
								gender = str[3];
								age = str[4];
								workingyear = str[5];
								mobile = str[6];
								email = str[7];
								city = str[8];
								latteraddress = str[8];
								expectedSalary = str[9];

							} else if (i % 2 == 0) {

								int j = 0;
								if (ele.select("td[colspan=9]>p").size() == 3)
									for (Element el : ele.select("td[colspan=9]>p")) {

										String str2 = el.text().trim();
										if (j == 0) {
											// expectedJob = str2.split("：")[1];
											// basicModel2.setExpectedJob(expectedJob);
										} else if (j == 1) {
											//System.out.println(str2);
											company = str2.split("：")[1].split("\\|")[0];
											title = str2.split("：")[1].split("\\|")[1];

										} else if (j == 2) {
											school = str2.split("：")[1].split("\\|")[0];
											major = str2.split("：")[1].split("\\|")[1];
											degree = str2.split("：")[1].split("\\|")[2];

										}

										j++;
									}
								basicModel2 = new Basic2();

								basicModel2.setName(name);
								basicModel2.setGender(gender);
								basicModel2.setAge(age);
								basicModel2.setWorkingyear(workingyear);
								basicModel2.setMobile(mobile);
								basicModel2.setEmail(email);
								basicModel2.setCity(city);
								basicModel2.setLatteraddress(latteraddress);
								basicModel2.setExpectedSalary(expectedSalary);
								basicModel2.setCompany(company);
								basicModel2.setTitle(title);
								basicModel2.setSchool(school);
								basicModel2.setMajor(major);
								basicModel2.setDegree(degree);
								// System.out.println(basicModel2);

								basicMap.put((k++) + "", basicModel2);

							}
						}
						i++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			// begin 基本信息

		} catch (Exception e) {
			e.printStackTrace();
		}
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

		 

		String s = null;
		for (int i = 0; i < str.length; i++) {
			s = str[i].trim();
			if (s.indexOf("男") != -1 || str[i].indexOf("女") != -1) {
				gender = s.trim();
			} else if (s.indexOf("户口") != -1) {
				account = s.split("：")[1];

			} else if (s.indexOf("居住于") != -1) {
				latteraddress = (String) s.subSequence(s.indexOf("居住于") + 3, s.indexOf(" "));
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

	public static void main(String[] args) throws IOException {

		String path = "C:/Users/gongcaichun/Desktop/html额外/未分类/1/网才导出简历列表3.html";
		parse(path);
		// System.out.println("-----------------------------------------------------------------------------");

		System.out.println("基本信息  " + basicModel2.toString());
		for (Basic2 b : basicMap.values())
			System.out.println(b.toString());

		System.exit(0);

	}
}
