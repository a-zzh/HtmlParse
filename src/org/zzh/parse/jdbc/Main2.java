package org.zzh.parse.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.hyx.parse.model.Project;
import org.hyx.parse.model.Skill;
import org.hyx.parse.model.SocialExperience;
import org.hyx.parse.model.StudentsPractice;
import org.hyx.parse.model.Train;
import org.hyx.parse.parse.QModelParse;
import org.hyx.parse.parse.WModel4Parse;
import org.hyx.parse.parse.ZModel2Parse;
import org.hyx.parse.parse.ZModel3Parse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main2 {

	static int sum = 0, count = 0;

	private static Document doc;
	private static Elements eles, eles2, eles3;
	// basic

	public static String id;
	public static int candidateid;
	public static String create_time;
	public static String update_time;
	public static String process_stage;

	public static String name;
	public static String usedname;
	public static String englishname;
	public static String gender;
	public static String cardtype;
	public static String idcard;
	public static String company;
	public static String title;
	public static String city;
	public static String birth_place;
	public static String phone;
	public static String mobile;
	public static String email;
	public static String age;
	public static String birthday;
	public static String marriage;
	public static String major;
	public static String degree;
	public static String school;
	public static String jobtime;
	public static String height;
	public static String weight;
	public static String BWH;
	public static String nationality;
	public static String country;
	public static String health;
	public static String politics;
	public static String job_status;
	public static String current_salary;
	public static String expected_salary;
	public static String expected_city;
	public static String expected_job;
	public static String weixin;
	public static String qq;
	public static String linkedin;
	public static String dajie;
	public static String weibo;
	public static String score;
	public static String basic_info;
	public static String address;
	public static String trade_id;
	public static String function_id;
	public static String mpc;
	public static String photo_id;
	public static String comment;
	public static String preference;
	public static String flag;
	public static String fileid;
	public static String ownerid;
	public static String company_id;
	public static String job_time;

	// job
	// public static String id;
	// public static String create_time;
	// public static String update_time;
	// public static String candidateid;
	// public static String process_stage;
	public static String start_time;
	public static String end_time;
	// public static String company;
	public static String department;
	// public static String title;
	public static String report;
	public static String salary;
	public static String member;
	public static String duty;
	public static String achieve;
	public static String reason;
	// public static String city;
	public static String info;
	// public static String score;
	public static String job_info;
	// public static String flag;
	// public static String fileid;

	public static String selfassessment; // 自我评价

	public static String expectation_info;// 期望信息

	// 获奖信息 get_time medal_name medal_info
	public static String get_time;
	public static String medal_name;
	public static String medal_info;

	// 技能信息
	public static String skill_name = null;
	public static String skill_info = null;
	public static String skill_experience = null;

	// 项目信息
	public static String project_name = null;
	// public static String company = null;
	public static String content = null;
	// public static String duty = null;
	// public static String achieve = null;
	// public static String city = null;
	// public static String info = null;
	// public static String score = null;
	public static String project_info = null;

	// 教育经历
	// public static String start_time = null;
	// public static String end_time = null;
	// public static String school = null;
	// public static String major = null;
	// public static String degree = null;
	// public static String info = null;
	// public static String score = null;
	public static String education_info = null;

	public static Map<String, Job> jobMap; // 工作经验
	public static Map<String, Credential> certificateMap; // 证书
	public static Map<String, Education> educationMap;// 教育经历
	public static Map<String, Project> projectmap; // 项目经理
	public static Map<String, Medal> medalMap; // 所获奖项
	public static Map<String, SocialExperience> socialMap; // 社会经验
	public static Map<String, Skill> skillMap; // 技能
	public static Map<String, Train> trainMap; // 培训经历
	public static Map<String, StudentsPractice> StudentsJobMap; // 校内职务
	public static Map<String, Attachment> attMap; // 附件
	public static Basic2 basicModel2;// 基本信息

	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	public static final String url = "jdbc:mysql://127.0.0.1/test";
	// connect = DriverManager.getConnection(
	// "jdbc:mysql://123.57.35.10:3306/honestcareer","root","hunter");
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";

	public static Connection connect = null;
	public static PreparedStatement pst = null;
	public static ResultSet ret = null;

	public static int genderNum(String str) {
		if (str == null)
			str = "";
		if (str.equals("男")) { // str.equals("男") str不能为空否则会直接跳出！！！
			return 1;
		} else if (str.equals("女")) {
			return 2;
		}
		return 0;
	}

	public static Connection getConnect() {
		try {
			Class.forName(driver);// 指定连接类型
			connect = DriverManager.getConnection(url, user, password);// 获取连接
			// pst = conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;

	}

	// 模板匹配
	public static int model(File readfile) {

		try {
			doc = Jsoup.parse(readfile, "gbk");
			// if(doc.text().contains("★四、3年世界500强大型美资公司人力资源基中层"))
			// System.exit(0); //51job_Davis 安 全(78185802).html
			// System.out.println(doc);
			// System.exit(0);
			eles = doc.select("html");
			eles2 = doc.select("html>body>table[width=600]table[align=CENTER]");
			eles3 = doc.select("td[valign=top]>table[width=97%]");
			if (eles.attr("xmlns").equals("http://www.w3.org/1999/xhtml")) { // QModelParse
				// 前程无忧
				return 0;

			} else if (!eles.attr("lang").equals("")) {// ZModel2Parse
				// System.out.println("ZModel2Parse"); // 智联
				return 1;
			} else if (eles2.size() != 0) { // ZModel3Parse
				// System.out.println("ZModel3Parse");
				return 2;
			} else if (eles3.size() != 0) { // WModel4Parse
				// System.out.println("WModel4Parse"); // 无忧
				return 3;
				// System.out.println(filename);
				// System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 5;
	}

	public static void cvTable() {
		try {
			// Date date = new Date();
			// DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// String time = format.format(date);
			ResultSet rsKey;
			try {
				connect = getConnect();
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO cv VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, null);
				statement.setString(2, null);
				statement.setInt(3, 1);
				statement.setString(4, null);
				statement.setString(5, null);
				statement.setString(6, null);
				statement.setString(7, null);
				statement.setString(8, null);
				statement.setString(9, null);
				statement.setString(10, null);
				statement.setString(11, null);
				statement.setString(12, null);
				statement.setString(13, null);
				statement.setString(14, null);
				statement.setString(15, null);
				statement.setString(16, null);
				statement.setString(17, null);
				statement.setString(18, null);
				statement.setString(19, null);
				statement.setString(20, null);
				statement.setString(21, null);
				statement.setString(22, null);
				statement.setInt(23, 1);
				statement.setString(24, null);
				statement.executeUpdate();
				rsKey = statement.getGeneratedKeys();
				rsKey.next();
				candidateid = rsKey.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void jobTable(int modelNum) {
		try {
			Map<String, Job> jobMap = new LinkedHashMap<String, Job>();// 工作经历

			switch (modelNum) {
			case 0:
				QModelParse parse1 = new QModelParse();
				jobMap = parse1.jobMap;
				break;
			case 1:
				ZModel2Parse parse2 = new ZModel2Parse();
				jobMap = parse2.jobMap;
				break;
			case 2:
				ZModel3Parse parse3 = new ZModel3Parse();
				jobMap = parse3.jobMap;
				break;
			case 3:
				WModel4Parse parse4 = new WModel4Parse();
				jobMap = parse4.jobMap;
				break;
			}
			if (jobMap.size() != 0) {

				for (Job job : jobMap.values()) {
					create_time = df.format(System.currentTimeMillis());
					update_time = "0000-00-00 00:00:00";
					// candidateid = 0;
					process_stage = null;
					start_time = changeNull(job.getStartTime());
					end_time = changeNull(job.getEndTime());
					company = changeNull(job.getCompany());
					// if(company.contains("一、大型民营合资公司工作履历")) System.exit(0);
					department = changeNull(job.getDepartment());
					title = changeNull(job.getPosition());
					report = changeNull(job.getReport());
					salary = changeNull(job.getSalary());
					member = changeNull(job.getMember());
					duty = changeNull(job.getDuty());
					achieve = changeNull(job.getAchieve());
					reason = changeNull(job.getReason());
					city = changeNull(job.getCity());
					info = changeNull(job.getInfo());
					score = changeNull(job.getScore() + "");
					job_info = changeNull(job.getJobInfo());
					// flag = changeNull(job.getFlag() + "");
					flag = "1";
					fileid = null;
					// (id++) + "\t" +
					// Date date = new Date();
					// DateFormat format = new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss");
					// String time = format.format(date);
					try {
						PreparedStatement statement = connect.prepareStatement(
								"INSERT INTO cv_job VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS);

						// 22行
						statement.setString(1, null);
						statement.setString(2, null);
						statement.setString(3, null);
						statement.setInt(4, candidateid);
						statement.setString(5, null);
						statement.setString(6, start_time);
						statement.setString(7, end_time);
						// System.out.println("start_time: "+start_time+",
						// end_time: "+end_time);
						statement.setString(8, company);
						statement.setString(9, department);
						statement.setString(10, title);

						statement.setString(11, report);
						statement.setString(12, salary);
						statement.setString(13, member);
						statement.setString(14, duty);
						statement.setString(15, achieve);
						statement.setString(16, reason);
						statement.setString(17, city);
						statement.setString(18, info);
						statement.setString(19, score);
						statement.setString(20, job_info);
						statement.setInt(21, 1);
						statement.setString(22, null);
						statement.executeUpdate();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// id
	// create_time
	// update_time
	// candidateid
	// process_stage
	// skill_name
	// info
	// score
	// skill_info
	// flag
	// skill_experience

	public static void skillTable(int modelNum) {

		try {
			try {
				Map<String, Skill> skillMap = new LinkedHashMap<String, Skill>(); // 技能
				switch (modelNum) {
				case 0:
					QModelParse parse1 = new QModelParse();
					skillMap = parse1.skillMap;
					break;
				case 1:
					ZModel2Parse parse2 = new ZModel2Parse();
					skillMap = parse2.skillMap;
					break;
				case 2:
					ZModel3Parse parse3 = new ZModel3Parse();
					skillMap = parse3.skillMap;
					break;
				case 3:
					WModel4Parse parse4 = new WModel4Parse();
					skillMap = parse4.skillMap;
					break;
				}
				if (skillMap.size() != 0) {
					for (Skill skill : skillMap.values()) {
						create_time = df.format(System.currentTimeMillis());
						update_time = "0000-00-00 00:00:00";
						// candidateid = null;
						process_stage = null;
						skill_name = skill.getSkillName();// 技能名
						info = skill.getInfo();
						score = skill.getScore() + "";
						skill_info = skill.getSkillInfo();// 技能描述
						flag = "1";
						skill_experience = skill.getTime();// 技能用时

						// 插入
						try {
							// Date date = new Date();
							// DateFormat format = new
							// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							// String time = format.format(date);
							PreparedStatement statement = connect.prepareStatement(
									"INSERT INTO cv_skill VALUES(?,?,?,?,?,?,?,?,?,?,?)",
									PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
							// 11行
							statement.setString(1, null);
							statement.setString(2, null);
							statement.setString(3, null);
							statement.setInt(4, candidateid);
							statement.setString(5, null);
							statement.setString(6, skill_name);
							statement.setString(7, info);
							statement.setString(8, score);
							statement.setString(9, skill_info);
							statement.setInt(10, 1);
							int skilltime = 0;
							if (skill_experience == null)
								skill_experience = "";
							Pattern p = Pattern.compile("\\d{1,3}");
							Matcher m = p.matcher(skill_experience);
							if (m.find())
								skilltime = Integer.parseInt(m.group());
							statement.setInt(11, skilltime);
							statement.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// id
	// create_time
	// update_time
	// candidateid
	// process_stage
	// start_time
	// end_time
	// project_name
	// company
	// content
	// duty
	// achieve
	// city
	// info
	// score
	// project_info
	// flag

	public static void projectTable(int modelNum) throws IOException {
		try {
			// String start_time = null;
			// String end_time = null;
			// String project_name = null;
			// String company = null;
			// String content = null;
			// String duty = null;
			// String achieve = null;
			// String city = null;
			// String info = null;
			// String score = null;
			// String project_info = null;
			Map<String, Project> projectmap = new LinkedHashMap<String, Project>(); // 项目经理
			File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_project.txt");
			if (!file.exists())
				file.createNewFile();
			switch (modelNum) {
			case 0:
				QModelParse parse1 = new QModelParse();
				projectmap = parse1.projectmap;
				break;
			case 1:
				ZModel2Parse parse2 = new ZModel2Parse();
				projectmap = parse2.projectmap;
				break;
			case 2:
				ZModel3Parse parse3 = new ZModel3Parse();
				projectmap = parse3.projectmap;
				break;
			case 3:
				WModel4Parse parse4 = new WModel4Parse();
				projectmap = parse4.projectmap;
				break;
			}
			if (projectmap.size() != 0) {
				for (Project project : projectmap.values()) {
					start_time = changeNull(project.getStartTime());
					end_time = changeNull(project.getEndTime());
					project_name = changeNull(project.getProjectName());
					company = changeNull(project.getCompany());
					content = changeNull(project.getContent());
					duty = changeNull(project.getDuty());
					achieve = changeNull(project.getAchieve());
					city = changeNull(project.getCity());
					info = changeNull(project.getInfo());
					score = changeNull(project.getScore() + "");
					project_info = changeNull(project.getProjectInfo());
					flag = "1";

					try {
						// Date date = new Date();
						// DateFormat format = new SimpleDateFormat("yyyy-MM-dd
						// HH:mm:ss");
						// String time = format.format(date);
						PreparedStatement statement = connect.prepareStatement(
								"INSERT INTO cv_project VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
						// 17行
						statement.setString(1, null);
						statement.setString(2, null);
						statement.setString(3, null);
						statement.setInt(4, candidateid);
						statement.setString(5, null);
						statement.setString(6, start_time);
						statement.setString(7, end_time);
						statement.setString(8, project_name);
						statement.setString(9, company);
						if (content.length() > 1000) {
							statement.setString(10, (String) content.subSequence(0, 1000));
						} else {
							statement.setString(10, content);
						}
						statement.setString(11, duty);
						statement.setString(12, achieve);
						statement.setString(13, city);
						statement.setString(14, info);
						statement.setString(15, score);
						if (project_info.length() > 1000) {
							statement.setString(16, project_info);
						} else {
							statement.setString(16, project_info);
						}
						statement.setInt(17, 1);
						statement.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch
						// block
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// id
	// create_time
	// update_time
	// candidateid
	// process_stage
	// get_time
	// medal_name
	// info
	// score
	// medal_info
	// flag

	public static void medalTable(int modelNum) {
		try {
			Map<String, Medal> medalMap = new LinkedHashMap<String, Medal>(); // 所获奖项
			switch (modelNum) {
			case 0:
				QModelParse parse1 = new QModelParse();
				medalMap = parse1.medalMap;
				break;
			case 1:
				ZModel2Parse parse2 = new ZModel2Parse();
				medalMap = parse2.medalMap;
				break;
			case 2:
				ZModel3Parse parse3 = new ZModel3Parse();
				medalMap = parse3.medalMap;
				break;
			case 3:
				WModel4Parse parse4 = new WModel4Parse();
				medalMap = parse4.medalMap;
				break;
			}
			if (medalMap.size() != 0) {
				for (Medal medal : medalMap.values()) {

					get_time = changeNull(medal.getGetTime());
					medal_name = changeNull(medal.getMedalName() + medal.getMedalLevel());
					medal_info = changeNull(medal.getMedalInfo());
					info = null;
					score = null;

					try {
						// Date date = new Date();
						// DateFormat format = new SimpleDateFormat("yyyy-MM-dd
						// HH:mm:ss");
						// String time = format.format(date);
						PreparedStatement statement = connect.prepareStatement(
								"INSERT INTO cv_medal VALUES(?,?,?,?,?,?,?,?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
						// 17行
						statement.setString(1, null);
						statement.setString(2, null);
						statement.setString(3, null);
						statement.setInt(4, candidateid);
						statement.setString(5, null);
						statement.setString(6, get_time);
						statement.setString(7, medal_name);
						statement.setString(8, info);
						statement.setString(9, null);
						statement.setString(10, medal_info);
						statement.setInt(11, 1);
						statement.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// id
	// create_time
	// update_time
	// candidateid
	// process_stage
	// info
	// score
	// expectation_info
	// flag
	// fileid

	public static void expectationTable(int modelNum) {

		try {
			info = null;
			expectation_info = changeNull(expectation_info);
			try {
				// Date date = new Date();
				// DateFormat format = new SimpleDateFormat("yyyy-MM-dd
				// HH:mm:ss");
				// String time = format.format(date);
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO cv_expectation VALUES(?,?,?,?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				// 17行
				statement.setString(1, null);
				statement.setString(2, null);
				statement.setString(3, null);
				statement.setInt(4, candidateid);
				statement.setString(5, null);
				statement.setString(6, null);
				statement.setString(7, null);
				statement.setString(8, expectation_info);
				statement.setInt(9, 1);
				statement.setString(10, null);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void educationTable(int modelNum) {

		try {
			Map<String, Education> educationMap = new LinkedHashMap<String, Education>();// 教育经历

			switch (modelNum) {
			case 0:
				QModelParse parse1 = new QModelParse();
				educationMap = parse1.educationMap;
				break;
			case 1:
				ZModel2Parse parse2 = new ZModel2Parse();
				educationMap = parse2.educationMap;
				break;
			case 2:
				ZModel3Parse parse3 = new ZModel3Parse();
				educationMap = parse3.educationMap;
				break;
			case 3:
				WModel4Parse parse4 = new WModel4Parse();
				educationMap = parse4.educationMap;
				break;
			}

			if (educationMap.size() != 0) {
				for (Education education : educationMap.values()) {
					id = null;
					// create_time
					// update_time
					// candidateid
					// process_stage
					start_time = changeNull(education.getStartTime());
					end_time = changeNull(education.getEndTime());
					school = changeNull(education.getSchool());
					major = changeNull(education.getMajor());
					degree = changeNull(education.getDegree());
					info = changeNull(education.getInfo());
					score = changeNull(education.getScore() + "");
					education_info = changeNull(education.getEducationInfo());
					// flag
					// fileid
					try {
						// Date date = new Date();
						// DateFormat format = new SimpleDateFormat("yyyy-MM-dd
						// HH:mm:ss");
						// String time = format.format(date);
						PreparedStatement statement = connect.prepareStatement(
								"INSERT INTO cv_education VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS);
						// 15行
						statement.setString(1, null);
						statement.setString(2, null);
						statement.setString(3, null);
						statement.setInt(4, candidateid);
						statement.setString(5, null);
						statement.setString(6, start_time);
						statement.setString(7, end_time);
						statement.setString(8, school);
						statement.setString(9, major);
						int degree_interger = degreeNum(degree);
						statement.setInt(10, degree_interger);
						statement.setString(11, info);
						statement.setString(12, null);
						statement.setString(13, education_info);
						statement.setInt(14, 1);
						statement.setString(15, null);
						statement.executeUpdate();

					} catch (SQLException e) {

						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// id
	// create_time
	// update_time
	// candidateid
	// process_stage
	// info
	// comment_info
	// flag

	private static int degreeNum(String degree) {
		int degree_interger = 0;
		try {
			if (degree.contains("本科") || degree.contains("学士")) {
				degree_interger = 4;
			} else if (degree.contains("专科") || degree.contains("大专") || degree.contains("高中")
					|| degree.contains("初中")) {
				degree_interger = 3;
			} else if (degree.contains("硕士") || degree.contains("研究生")) {
				degree_interger = 5;
			} else if (degree.contains("博士")) {
				degree_interger = 6;
			} else if (degree.contains("博士后")) {
				degree_interger = 7;
			} else {
				degree_interger = 0;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return degree_interger;
	}

	public static void commentTable(int modelNum) {

		try {
			String comment_info = selfassessment;
			// info = null;

			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);
			PreparedStatement statement;
			try {
				statement = connect.prepareStatement("INSERT INTO cv_comment VALUES(?,?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);

				// 8行
				statement.setString(1, null);
				statement.setString(2, null);
				statement.setString(3, time);
				statement.setInt(4, candidateid);
				statement.setString(5, null);
				statement.setString(6, null);
				statement.setString(7, comment_info);
				statement.setInt(8, 1);
				statement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void basicTable(int modelNum) {
		try {
			Basic2 basic = new Basic2();

			switch (modelNum) {
			case 0:
				QModelParse parse1 = new QModelParse();
				basic = parse1.basicModel2;
				break;
			case 1:
				ZModel2Parse parse2 = new ZModel2Parse();
				basic = parse2.basicModel2;
				break;
			case 2:
				ZModel3Parse parse3 = new ZModel3Parse();
				basic = parse3.basicModel2;
				break;
			case 3:
				WModel4Parse parse4 = new WModel4Parse();
				basic = parse4.basicModel2;
				break;
			}
			// System.out.println(basic.toString());
			// Date date = new Date();
			// DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// String time = format.format(date);
			PreparedStatement statement;
			try {
				statement = connect.prepareStatement(
						"INSERT INTO cv_basic VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);

				id = null;
				// candidateid = null;
				create_time = null;
				update_time = "0000-00-00 00:00:00";
				process_stage = null;

				name = changeNull(basic.getName());

				usedname = null;
				englishname = null;
				gender = changeNull(genderNum(basic.getGender()) + "");
				cardtype = null;
				idcard = null;
				company = changeNull(basic.getCompany());
				title = changeNull(basic.getTitle());
				city = changeNull(basic.getCity());
				birth_place = changeNull(basic.getAccount()); // birth_place
																// phone
																// mobile email
																// age
																// birthday
																// marriage
				phone = changeNull(basic.getPhone());
				mobile = changeNull(basic.getMobile());
				if (mobile.contains("江西省星子县蓼南乡新华村何家堡 （邮编：332806）")) {
					System.exit(0);
				}
				email = changeNull(basic.getEmail());
				age = changeNull(basic.getAge());
				birthday = changeNull(basic.getBirth());
				marriage = changeNull(basic.getMaritalstatus());
				major = changeNull(basic.getMajor());// major degree school
														// jobtime height height
				// weight BWH
				degree = changeNull(basic.getDegree());
				school = changeNull(basic.getSchool());
				jobtime = changeNull(basic.getWorkingyear());// 工作年限标志 height
																// weight
																// BWH
																// nationality
																// country
																// health
				height = changeNull(basic.getHeight());
				weight = changeNull(basic.getWeight());
				BWH = null;
				nationality = null;
				country = null;
				health = null;
				politics = changeNull(basic.getPolitics());
				job_status = changeNull(basic.getJobStatus());
				current_salary = changeNull(basic.getCurrentSalary());
				expected_salary = changeNull(basic.getExpectedSalary());
				expected_city = changeNull(basic.getExpectedCity());
				expected_job = changeNull(basic.getExpectedJob());
				weixin = null;
				qq = null;
				linkedin = null;
				dajie = null;
				weibo = null;
				score = null;
				basic_info = null;
				address = changeNull(basic.getLatteraddress());
				trade_id = null;
				function_id = null;
				mpc = null;
				photo_id = null;
				comment = changeNull(basic.getSelfassessment());
				preference = null;
				flag = "1";
				fileid = null;
				ownerid = null;
				company_id = null;
				job_time = changeNull(basic.getWorkingyear());

				// System.out.println("--------------------------------");
				selfassessment = comment;
				expectation_info = " 期望行业:" + basic.getExpectindustry() + ", 期望薪资：" + basic.getExpectedSalary()
						+ ", 期望城市：" + basic.getExpectedCity() + ", 期望工作:" + basic.getExpectedJob() + ", 期望工作性质："
						+ basic.getExpectnaturework();

				// 56行
				statement.setString(1, null);
				statement.setInt(2, candidateid);
				statement.setString(3, null);
				statement.setString(4, null);
				statement.setString(5, null);
				statement.setString(6, changeNull(basic.getName()));
				statement.setString(7, null);
				statement.setString(8, null);
				statement.setInt(9, genderNum(basic.getGender()));
				statement.setString(10, null);
				statement.setString(11, null);
				statement.setString(12, company);
				statement.setString(13, title);
				statement.setString(14, city);
				statement.setString(15, birth_place);
				statement.setString(16, phone);
				statement.setString(17, mobile);
				statement.setString(18, email);
				statement.setString(19, age);
				statement.setString(20, birthday);

				int marriageNum = getMarriageNum(marriage);
				statement.setInt(21, marriageNum);// 婚姻状况，0未知，1未婚，2已婚，3已育，4离异，5丧偶，6其他

				statement.setString(22, major); // 从教育中获取
				statement.setString(23, degree);
				statement.setString(24, school);
				int work_experience_int = workyear(jobtime);

				if (work_experience_int < 1) {// 年
					statement.setString(25, "102");
				} else if (work_experience_int >= 1 && work_experience_int < 3) {
					statement.setString(25, "103");
				} else if (work_experience_int >= 3 && work_experience_int < 5) {
					statement.setString(25, "104");
				} else if (work_experience_int >= 5 && work_experience_int < 10) {
					statement.setString(25, "11");
				} else if (work_experience_int >= 10) {
					statement.setString(25, "12");
				} else {
					statement.setString(25, "102");
				}

				// height weight BWH nationality country health
				statement.setString(26, height);
				statement.setString(27, null);
				statement.setString(28, null);
				statement.setString(29, null);
				statement.setString(30, null);
				statement.setString(31, null);
				statement.setString(32, politics);

				int jobstatus = jobstatus(job_status);
				if (jobstatus == 0) {
					statement.setString(33, null);
				} else
					statement.setInt(33, jobstatus);

				statement.setString(34, current_salary);
				// expected_salary

				int expected_salary2 = getExpSallary(expected_salary);
				int expected_salary_ayear = expected_salary2 * 12;

				// System.out.println(expected_salary_ayear);
				if (expected_salary != null && !expected_salary.equals("")) {
					if (expected_salary_ayear <= 0) {
						expected_salary2 = 148;
					} else if (expected_salary_ayear > 0 && expected_salary_ayear < 50000) {
						expected_salary2 = 67;
					} else if (expected_salary_ayear >= 50000 && expected_salary_ayear < 100000) {
						expected_salary2 = 68;
					} else if (expected_salary_ayear >= 100000 && expected_salary_ayear < 200000) {
						expected_salary2 = 69;
					} else if (expected_salary_ayear >= 200000 && expected_salary_ayear < 300000) {
						expected_salary2 = 70;
					} else if (expected_salary_ayear >= 300000 && expected_salary_ayear < 500000) {
						expected_salary2 = 71;
					} else if (expected_salary_ayear >= 500000 && expected_salary_ayear < 1000000) {
						expected_salary2 = 72;
					} else if (expected_salary_ayear >= 1000000) {
						expected_salary2 = 73;
					}
				}
				statement.setInt(35, expected_salary2);
				statement.setString(36, expected_city);
				statement.setString(37, expected_job);
				statement.setString(38, null);
				statement.setString(39, null);
				statement.setString(40, null);
				statement.setString(41, null);
				statement.setString(42, null);
				statement.setString(43, null);
				statement.setString(44, null);
				statement.setString(45, address);
				statement.setString(46, null);
				statement.setString(47, null);
				statement.setString(48, null);
				statement.setString(49, null);

				if (changeNull(comment).length() > 1024)
					statement.setString(50, comment.substring(0, 1000));
				else
					statement.setString(50, comment);
				statement.setString(51, null);
				statement.setInt(52, 1);
				statement.setString(53, null);
				statement.setString(54, null);
				statement.setInt(55, 1);
				statement.setInt(56, work_experience_int);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 婚姻状况，0未知，1未婚，2已婚，3已育，4离异，5丧偶，6其他
	private static int getMarriageNum(String marriage) {
		if (marriage.contains("未婚")) {
			return 1;
		} else if (marriage.contains("已婚")) {
			return 2;
		} else if (marriage.contains("已育")) {
			return 3;
		} else if (marriage.contains("离异")) {
			return 4;
		} else if (marriage.contains("丧偶")) {
			return 5;
		} else if (marriage.contains("其他")) {
			return 6;
		}

		return 0;
	}

	private static int getExpSallary(String str) {
		int expected_salary = 0, max = 0, min = 0;
		try {

			Pattern pattern = Pattern.compile("\\d{4,6}");
			Matcher matcher = pattern.matcher(str);

			while (matcher.find()) {
				min = Integer.parseInt(matcher.group());
				max = max > min ? max : min;
			}
			if (max != 0)
				expected_salary = max;
			else if (str.contains("万")) {
				pattern = Pattern.compile("\\d{1,3}");
				matcher = pattern.matcher(str);
				while (matcher.find()) {
					min = Integer.parseInt(matcher.group());
					max = max > min ? max : min;
				}
				if (max != 0)
					expected_salary = max * 10000;

			} else if (str.contains("面议")) {
				expected_salary = 148;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expected_salary;
	}

	private static int jobstatus(String str) {
		int jobstatus = 0;
		try {
			if (str.contains("正在")) {
				jobstatus = 65;
			} else if (str.contains("机会") || str.contains("观望")) {
				jobstatus = 13;
			} else {
				return 64;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobstatus;
	}

	public static int workyear(String str) {
		int yearNum = 0;
		try {
			if (str.contains("一")) {
				yearNum = 1;
			} else if (str.contains("二")) {
				yearNum = 2;
			} else if (str.contains("三")) {
				yearNum = 3;
			} else if (str.contains("四")) {
				yearNum = 4;
			} else if (str.contains("五")) {
				yearNum = 5;
			} else if (str.contains("六")) {
				yearNum = 6;
			} else if (str.contains("七")) {
				yearNum = 7;
			} else if (str.contains("八")) {
				yearNum = 8;
			} else if (str.contains("九")) {
				yearNum = 9;
			} else if (str.contains("十")) {
				yearNum = 10;
			} else if (str.contains("年")) {
				try {
					// yearNum = Integer.parseInt((String) str.subSequence(0,
					// str.indexOf("年")));
					Pattern pattern1 = Pattern.compile("\\d{1,2}");
					Matcher matcher1 = pattern1.matcher(str);
					// System.out.println(matcher1.find());
					// List list =new ArrayList();
					int max = 0, min = 0;
					while (matcher1.find()) {
						min = Integer.parseInt(matcher1.group());
						max = max > min ? max : min;
						yearNum = max;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				yearNum = 0;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yearNum;
	}

	public static String changeNull(String str) {
		try {

			if (str == null)
				str = "";
			else {
				str = replaceTab(str.trim());
				str = str.replace("\\", " ");
				// str = str.equals("") ? "" : str;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			return "";
		}
		return str;
	}

	// 去除制表符
	public static String replaceTab(String str) {
		String dest = "";
		try {
			if (str != null) { // \\s*|\t|\r|\n \t
				Pattern p = Pattern.compile("\t");
				Matcher m = p.matcher(str);
				dest = m.replaceAll("");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}

	/**
	 * 读取某个文件夹下的所有文件
	 */
	public static boolean readfile(String filepath) throws FileNotFoundException, IOException, Exception {
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) { // 如果不是目录
				System.out.println("文件");

			} else if (file.isDirectory()) { // 如果是目录
				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					System.out.println((++sum) + "   " + filelist[i]);
					if (!readfile.isDirectory()) {// 如果是文件

						// 送入模板判断器
						int modelNum = model(readfile);

						// 送入相应解析器
						switch (modelNum) {

						case 0:
							QModelParse.parse(readfile.getAbsolutePath());
							break;
						case 1:
							ZModel2Parse.parse(readfile.getAbsolutePath());
							break;
						case 2:
							ZModel3Parse.parse(readfile.getAbsolutePath());
							break;
						case 3:
							WModel4Parse.parse(readfile.getAbsolutePath());

							break;

						case 5:
							String filepath2 = "C:/Users/gongcaichun/Desktop/database/UnReadFiles.txt";
							File f = new File(filepath2);
							if (!f.exists()) {
								f.createNewFile();
							}
							count++;// 计数器
							System.out.println("文件暂时未能解析！");

							BufferedWriter bw = new BufferedWriter(new FileWriter(filepath2, true));
							bw.write(readfile.getAbsolutePath() + "\r\n");
							bw.flush(); // 把缓存区内容压入文件
							// bw.close(); // 关闭文件
							// 将不能处理的文件路径写入UnReadFiles.txt
							break;
						}
						// 分项处理，插入数据库
						cvTable(); // 主id表
						// System.out.println("cvTable()");
						jobTable(modelNum);
						// System.out.println("jobTable()");
						educationTable(modelNum);
						// System.out.println("educationTable()");
						medalTable(modelNum);
						// System.out.println("medalTable()");
						projectTable(modelNum);
						// System.out.println("projectTable()");
						skillTable(modelNum);
						// System.out.println("skillTable()");
						basicTable(modelNum);
						// System.out.println("basicTable()");

						commentTable(modelNum);
						// System.out.println("commentTable()");
						expectationTable(modelNum);
						// System.out.println("expectationTable()");

					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i]);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public static void main(String[] args) {// ArrayIndexOutOfBoundsException
		try {
			long a = System.currentTimeMillis();
			readfile("C:\\Users\\gongcaichun\\Desktop\\模板");
			System.out.println("耗时： " + (System.currentTimeMillis() - a) / 1000f + " s");
			System.out.println("解析文件成功数： " + sum);
			System.out.println("解析文件失败数： " + count);

		} catch (Exception ex) {
		} // ArrayIndexOutOfBoundsException

	}

}

// 耗时： 788.732 s
// 解析文件成功数： 2151
// 解析文件失败数： 6

// 耗时： 906.08 s
// 解析文件成功数： 2151
// 解析文件失败数： 6
