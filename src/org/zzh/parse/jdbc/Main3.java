package org.zzh.parse.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.hyx.parse.parse.UModel7Parse;
import org.hyx.parse.parse.WModel4Parse;
import org.hyx.parse.parse.WModel6Parse;
import org.hyx.parse.parse.ZModel2Parse;
import org.hyx.parse.parse.ZModel3Parse;
import org.hyx.parse.parse.ZModel5Parse;
import org.hyx.parse.parse.brModel8Parse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main3 {

	static int sum = 0, count = 0, help = 0, connCounter = 0, success = 0, lose = 0;

	private static Document doc;
	private static Elements eles0, eles, eles2, eles3, eles4, eles5, eles6, eles7;
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
	public static String skill_name;
	public static String skill_info;
	public static String skill_experience;

	// 项目信息
	public static String project_name;
	// public static String company = null;
	public static String content;
	// public static String duty = null;
	// public static String achieve = null;
	// public static String city = null;
	// public static String info = null;
	// public static String score = null;
	public static String project_info;

	// 教育经历
	// public static String start_time = null;
	// public static String end_time = null;
	// public static String school = null;
	// public static String major = null;
	// public static String degree = null;
	// public static String info = null;
	// public static String score = null;
	public static String education_info;

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

	// "jdbc:mysql://123.57.35.10:3306/honestcareer","root","hunter"
	// public static final String url = "jdbc:mysql://127.0.0.1/test";
	// public static final String url =
	// "jdbc:mysql://123.57.35.10:3306/honestcareer";
	// public static final String driver = "com.mysql.jdbc.Driver";
	// public static final String user = "root";
	// public static final String password = "hunter";

	public static Connection connect = null;
	public static PreparedStatement statement = null;
	public static ResultSet ret = null;
	// K:\\mht2html
	// public static String cv_job = "K:\\mht2html\\cv_job.txt";
	// public static String cv_skill = "K:\\mht2html\\cv_skill.txt";
	// public static String cv_project = "K:\\mht2html\\cv_project.txt";
	// public static String cv_medal = "K:\\mht2html\\cv_medal.txt";
	//
	// public static String cv_expectation = "K:\\mht2html\\cv_expectation.txt";
	// public static String cv_education = "K:\\mht2html\\cv_education.txt";
	// public static String cv_comment = "K:\\mht2html\\cv_comment.txt";
	// public static String cv_basic = "K:\\mht2html\\cv_basic.txt";
	// public static String UnReadFiles =
	// "C:/Users/gongcaichun/Desktop/database/UnReadFiles.txt";

	public static String cv_job = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_job.txt";
	public static String cv_skill = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_skill.txt";
	public static String cv_project = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_project.txt";
	public static String cv_medal = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_medal.txt";

	public static String cv_expectation = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_expectation.txt";
	public static String cv_education = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_education.txt";
	public static String cv_comment = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_comment.txt";
	public static String cv_basic = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\cv_basic.txt";
	// public static String UnReadFiles = "K:\\mht2html\\UnReadFiles.txt";

	public static String UnReadFiles = "C:\\Users\\gongcaichun\\Desktop\\database\\wfl\\1\\UnReadFiles_test.txt";

	// public static String cv_job = "/data/mht/cv_job.txt";
	// public static String cv_skill = "/data/mht/cv_skill.txt";
	// public static String cv_project = "/data/mht/cv_project.txt";
	// public static String cv_medal = "/data/mht/cv_medal.txt";
	//
	// public static String cv_expectation = "/data/mht/cv_expectation.txt";
	// public static String cv_education = "/data/mht/cv_education.txt";
	// public static String cv_comment = "/data/mht/cv_comment.txt";
	// public static String cv_basic = "/data/mht/cv_basic.txt";
	// public static String UnReadFiles = "/data/mht/UnReadFiles.txt";

	public static File cv_job_txt;
	public static File cv_skill_txt;
	public static File cv_project_txt;
	public static File cv_medal_txt;

	public static File cv_expectation_txt;
	public static File cv_education_txt;
	public static File cv_comment_txt;
	public static File cv_basic_txt;
	public static File UnReadFiles_txt;

	public static FileWriter cv_job_txt_fw;// 可追加读写入
	public static FileWriter cv_skill_txt_fw;// 可追加读写入
	public static FileWriter cv_project_txt_fw;// 可追加读写入
	public static FileWriter cv_medal_txt_fw;// 可追加读写入

	public static FileWriter cv_expectation_txt_fw;// 可追加读写入
	public static FileWriter cv_education_txt_fw;// 可追加读写入
	public static FileWriter cv_comment_txt_fw;// 可追加读写入
	public static FileWriter cv_basic_txt_fw;// 可追加读写入
	public static FileWriter UnReadFiles_txt_fw;// 可追加读写入

	public static BufferedWriter cv_job_txt_bw;
	public static BufferedWriter cv_skill_txt_bw;
	public static BufferedWriter cv_project_txt_bw;
	public static BufferedWriter cv_medal_txt_bw;

	public static BufferedWriter cv_expectation_txt_bw;
	public static BufferedWriter cv_education_txt_bw;
	public static BufferedWriter cv_comment_txt_bw;
	public static BufferedWriter cv_basic_txt_bw;
	public static BufferedWriter UnReadFiles_txt_bw;

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
			Class.forName("com.mysql.jdbc.Driver");// 指定连接类型 honestcareer
													// hunter2 test
			// connect =
			// DriverManager.getConnection("jdbc:mysql://123.57.35.10:3306/hunter2",
			// "root", "hunter");// 获取连接
			connect = DriverManager.getConnection("jdbc:mysql://123.57.35.10:3306/hunter2", "root", "hunter");// 获取连接
			// connect =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test",
			// "root", "123456");// 获取连接

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
			// eles = doc.select("html");

			// if(doc.text().indexOf("五年军旅生涯锻造的自己坚韧不拔的品质，对事")!=-1){
			// System.out.println(readfile.getAbsolutePath());
			// System.exit(0);
			//
			// }

			// if(doc.text().indexOf("Office Project")!=-1){
			// System.out.println(readfile.getAbsolutePath());
			// System.exit(0);
			//
			// }

			eles0 = doc.select("html>body[id=rBody]");// ok
			eles = doc.select("body>div[class=resume-preview clearfix]");
			eles2 = doc.select("html>body>table[width=600]table[align=CENTER]");
			eles3 = doc.select("td[valign=top]>table[width=97%]");
			eles4 = doc.select("body>div[class=zpResumeS]");
			eles5 = doc.select("tbody>tr[style=background:#C2DBF9;]");

			eles6 = doc.select("div.mcon>div.ResumeC");

			doc = Jsoup.parse(readfile, "utf-8");// 重新解析
			eles7 = doc.select("html[lang=en]>head");

			if (eles0.size() != 0) { // QModelParse
				// 前程无忧
				return 0;

			} else if (eles.size() != 0) {// ZModel2Parse
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
			} else if (eles4.size() != 0) { // ZModel5Parse
				return 4;
			} else if (eles5.size() != 0) { // WModel6Parse
				return 6;
			} else if (eles6.size() != 0) { // UModel7Parse
				return 7;
			} else if (eles7.text().equals("简历")) { // brModel8Parse
				return 8;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 5;
	}

	public static void cvTable() {

		id = "\\N";

		create_time = "\\N";
		update_time = "\\N";
		process_stage = "\\N";

		name = "\\N";
		usedname = "\\N";
		englishname = "\\N";
		gender = "\\N";
		cardtype = "\\N";
		idcard = "\\N";
		company = "\\N";
		title = "\\N";
		city = "\\N";
		birth_place = "\\N";
		phone = "\\N";
		mobile = "\\N";
		email = "\\N";
		age = "\\N";
		birthday = "\\N";
		marriage = "\\N";
		major = "\\N";
		degree = "\\N";
		school = "\\N";
		jobtime = "\\N";
		height = "\\N";
		weight = "\\N";
		BWH = "\\N";
		nationality = "\\N";
		country = "\\N";
		health = "\\N";
		politics = "\\N";
		job_status = "\\N";
		current_salary = "\\N";
		expected_salary = "\\N";
		expected_city = "\\N";
		expected_job = "\\N";
		weixin = "\\N";
		qq = "\\N";
		linkedin = "\\N";
		dajie = "\\N";
		weibo = "\\N";
		score = "\\N";
		basic_info = "\\N";
		address = "\\N";
		trade_id = "\\N";
		function_id = "\\N";
		mpc = "\\N";
		photo_id = "\\N";
		comment = "\\N";
		preference = "\\N";
		flag = "1";
		fileid = "\\N";
		ownerid = "\\N";
		company_id = "\\N";
		job_time = "\\N";

		try {

			ResultSet rsKey;
			try {
				if (connCounter % 10000 == 9999) {
					connect.close();
					connect = getConnect();
				}
				statement = connect.prepareStatement(
						"INSERT INTO cv  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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

				connCounter++;
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

			case 4:
				ZModel5Parse parse5 = new ZModel5Parse();
				jobMap = parse5.jobMap;
				break;
			case 6:
				// WModel6Parse parse6 = new WModel6Parse();
				// jobMap = parse6.jobMap;
				break;
			case 7:
				UModel7Parse parse7 = new UModel7Parse();
				jobMap = parse7.jobMap;
				break;
			case 8:
				brModel8Parse parse8 = new brModel8Parse();
				jobMap = parse8.jobMap;
				break;

			}

			if (jobMap != null) {

				for (Job job : jobMap.values()) {
					create_time = "\\N";
					update_time = "\\N";
					// candidateid = 0;
					process_stage = "\\N";
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
					fileid = "\\N";
					// duty achieve reason info
					if (duty.length() > 1024) {
						duty = duty.substring(0, 1000);
					}
					if (achieve.length() > 1024) {
						achieve = achieve.substring(0, 1000);
					}
					if (reason.length() > 1024) {
						reason = reason.substring(0, 1000);
					}
					if (info.length() > 1024) {
						info = info.substring(0, 1000);
					}

					String line = "\\N" + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t"
							+ process_stage + "\t" // 5
							+ start_time + "\t" + end_time + "\t" + company + "\t" + department + "\t" + title + "\t"
							+ report // 6
							+ "\t" + salary + "\t" + member + "\t" + duty + "\t" + achieve + "\t" + reason + "\t" + city
							+ "\t" // 6
							+ info + "\t" + score + "\t" + job_info + "\t" + flag + "\t" + fileid + "\t"; // 5

					// File file = new File(cv_job);
					// if (!file.exists())
					// file.createNewFile();
					// FileWriter fw = new FileWriter(file, true);// 可追加读写入
					// BufferedWriter bw = new BufferedWriter(fw);
					// bw.write(line + "\r\n");
					// bw.flush();
					// bw.close();
					// fw.close();

					cv_job_txt_fw.write(line + "\r\n");
					cv_job_txt_fw.flush();

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
				case 4:
					ZModel5Parse parse5 = new ZModel5Parse();
					skillMap = parse5.skillMap;
					break;
				case 7:
					UModel7Parse parse7 = new UModel7Parse();
					skillMap = parse7.skillMap;
					break;
				case 8:
					brModel8Parse parse8 = new brModel8Parse();
					skillMap = parse8.skillMap;
					break;
				}
				if (skillMap != null) {
					for (Skill skill : skillMap.values()) {
						// id = "\\N";
						// create_time = df.format(System.currentTimeMillis());
						// update_time = "0000-00-00 00:00:00";
						// candidateid = null;
						// process_stage = null;
						skill_name = changeNull(skill.getSkillName());// 技能名
						if (skill_name.length() > 1024)
							skill_name = skill_name.substring(0, 1000);
						info = changeNull(skill.getInfo());
						if (info.length() > 1024)
							info = info.substring(0, 1000);
						score = changeNull(skill.getScore() + "");
						skill_info = changeNull(skill.getSkillInfo());// 技能描述
						flag = "1";
						skill_experience = changeNull(skill.getTime());// 技能用时
						if (skill_experience == null) {
							skill_experience = "";
						}
						int skilltime = 0;
						Pattern p = Pattern.compile("\\d{1,3}");
						Matcher m = p.matcher(skill_experience);
						if (m.find())
							skilltime = Integer.parseInt(changeNull(m.group()));

						// 插入
						String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t"
								+ process_stage + "\t" + skill_name + "\t" + info + "\t" + score + "\t" + skill_info
								+ "\t" + flag + "\t" + skilltime + "\t";

						// File file = new File(cv_skill);
						// if (!file.exists())
						// file.createNewFile();
						// FileWriter fw = new FileWriter(file, true);// 可追加读写入
						// BufferedWriter bw = new BufferedWriter(fw);
						// bw.write(line + "\r\n");
						// bw.flush();
						// bw.close();
						// fw.close();

						cv_skill_txt_fw.write(line + "\r\n");
						cv_skill_txt_fw.flush();

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
			case 4:
				ZModel5Parse parse5 = new ZModel5Parse();
				projectmap = parse5.projectmap;
				break;
			case 7:
				UModel7Parse parse7 = new UModel7Parse();
				projectmap = parse7.projectmap;
				break;
			case 8:
				brModel8Parse parse8 = new brModel8Parse();
				projectmap = parse8.projectmap;
				break;
			}
			if (projectmap != null) {
				for (Project project : projectmap.values()) {
					start_time = changeNull(project.getStartTime());
					end_time = changeNull(project.getEndTime());
					project_name = changeNull(project.getProjectName());
					company = changeNull(project.getCompany());
					content = changeNull(project.getContent());

					if (content.length() > 1000) {
						content = (String) content.subSequence(0, 1000);
					}
					duty = changeNull(project.getDuty());
					achieve = changeNull(project.getAchieve());
					if (achieve.length() > 1024)
						achieve = achieve.substring(0, 1000);
					city = changeNull(project.getCity());
					info = changeNull(project.getInfo());
					if (info.length() > 1000) {
						info = (String) info.subSequence(0, 1000);
					}
					score = changeNull(project.getScore() + "");
					project_info = changeNull(project.getProjectInfo());
					flag = "1";
					// 17
					String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t"
							+ process_stage + "\t" + start_time + "\t" + end_time + "\t" + project_name + "\t" + company
							+ "\t" + content + "\t" + duty + "\t" + achieve + "\t" + city + "\t" + info + "\t" + score
							+ "\t" + project_info + "\t" + flag + "\t";

					// File file = new File(cv_project);
					// if (!file.exists())
					// file.createNewFile();
					// FileWriter fw = new FileWriter(file, true);// 可追加读写入
					// BufferedWriter bw = new BufferedWriter(fw);
					// bw.write(line + "\r\n");
					// bw.flush();
					// bw.close();

					cv_project_txt_fw.write(line + "\r\n");
					cv_project_txt_fw.flush();
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
			case 4:
				ZModel5Parse parse5 = new ZModel5Parse();
				medalMap = parse5.medalMap;
				break;
			case 7:
				UModel7Parse parse7 = new UModel7Parse();
				medalMap = parse7.medalMap;
				break;
			case 8:
				brModel8Parse parse8 = new brModel8Parse();
				medalMap = parse8.medalMap;
				break;
			}
			if (medalMap != null) {
				for (Medal medal : medalMap.values()) {

					get_time = changeNull(medal.getGetTime());
					medal_name = changeNull(medal.getMedalName() + medal.getMedalLevel());
					medal_info = changeNull(medal.getMedalInfo());
					info = "\\N";
					score = "\\N";
					flag = "1";
					if (get_time.length() > 1000) {
						get_time = (String) get_time.subSequence(0, 1000);
					}
					if (medal_name.length() > 1000) {
						medal_name = (String) medal_name.subSequence(0, 1000);
					}

					// 11
					String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t"
							+ process_stage + "\t" + get_time + "\t" + medal_name + "\t" + info + "\t" + score + "\t"
							+ medal_info + "\t" + flag + "\t";
					// File file = new File(cv_medal);
					// if (!file.exists())
					// file.createNewFile();
					// FileWriter fw = new FileWriter(file, true);// 可追加读写入
					// BufferedWriter bw = new BufferedWriter(fw);
					// bw.write(line + "\r\n");
					// bw.flush();
					// bw.close();

					cv_medal_txt_fw.write(line + "\r\n");
					cv_medal_txt_fw.flush();
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

			// info = null;
			expectation_info = changeNull(expectation_info);
			flag = "1";
			// 10
			String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage
					+ "\t" + info + "\t" + score + "\t" + expectation_info + "\t" + flag + "\t" + fileid + "\t";

			// File file = new File(cv_expectation);
			// if (!file.exists())
			// file.createNewFile();
			// FileWriter fw = new FileWriter(file, true);// 可追加读写入
			// BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(line + "\r\n");
			// bw.flush();
			// bw.close();
			cv_expectation_txt_fw.write(line + "\r\n");
			cv_expectation_txt_fw.flush();
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
			case 4:
				ZModel5Parse parse5 = new ZModel5Parse();
				educationMap = parse5.educationMap;
				break;
			case 7:
				UModel7Parse parse7 = new UModel7Parse();
				educationMap = parse7.educationMap;
				break;
			case 8:
				brModel8Parse parse8 = new brModel8Parse();
				educationMap = parse8.educationMap;
				break;
			}

			if (educationMap != null) {
				for (Education education : educationMap.values()) {
					// id = null;
					// create_time
					// update_time
					// candidateid
					// process_stage
					start_time = changeNull(education.getStartTime());
					end_time = changeNull(education.getEndTime());
					school = changeNull(education.getSchool());
					major = changeNull(education.getMajor());
					degree = changeNull(education.getDegree());
					int degree_interger = degreeNum(degree);
					info = changeNull(education.getInfo());
					score = changeNull(education.getScore() + "");
					education_info = changeNull(education.getEducationInfo());
					flag = "1";
					// fileid

					if (info.length() > 1024) {
						info = info.substring(0, 1000);
					}

					// 15
					String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t"
							+ process_stage + "\t" + start_time + "\t" + end_time + "\t" + school + "\t" + major + "\t"
							+ degree_interger + "\t" + info + "\t" + score + "\t" + education_info + "\t" + flag + "\t"
							+ fileid + "\t";

					// File file = new File(cv_education);
					// if (!file.exists())
					// file.createNewFile();
					// FileWriter fw = new FileWriter(file, true);// 可追加读写入
					// BufferedWriter bw = new BufferedWriter(fw);
					// bw.write(line + "\r\n");
					// bw.flush();
					// bw.close();

					cv_education_txt_fw.write(line + "\r\n");
					cv_education_txt_fw.flush();

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
			info = "\\N";

			// 8
			String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage
					+ "\t" + info + "\t" + comment_info + "\t" + flag + "\t";

			// File file = new File(cv_comment);
			// if (!file.exists())
			// file.createNewFile();
			// FileWriter fw = new FileWriter(file, true);// 可追加读写入
			// BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(line + "\r\n");
			// bw.flush();
			// bw.close();
			// fw.close();
			cv_comment_txt_fw.write(line + "\r\n");
			cv_comment_txt_fw.flush();

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
			case 4:
				ZModel5Parse parse5 = new ZModel5Parse();
				basic = parse5.basicModel2;
				break;
			case 7:
				UModel7Parse parse7 = new UModel7Parse();
				basic = parse7.basicModel2;
				break;
			case 8:
				brModel8Parse parse8 = new brModel8Parse();
				basic = parse8.basicModel2;
				break;
			case 6:
				WModel6Parse parse6 = new WModel6Parse();
				Map<String, Basic2> basicMap = new LinkedHashMap<String, Basic2>();
				basicMap = parse6.basicMap;
				for (Basic2 ba : basicMap.values()) {
					PreparedStatement statement;

					// id = null;
					// candidateid = null;
					// create_time = null;
					// update_time = "0000-00-00 00:00:00";
					// process_stage = null;

					name = changeNull(ba.getName());

					// usedname = null;
					// englishname = null;
					gender = changeNull(genderNum(ba.getGender()) + "");
					// cardtype = null;
					// idcard = null;
					company = changeNull(ba.getCompany());
					title = changeNull(ba.getTitle());
					city = changeNull(ba.getCity());
					birth_place = changeNull(ba.getAccount()); // birth_place

					phone = changeNull(ba.getPhone());
					mobile = changeNull(ba.getMobile());
					email = changeNull(ba.getEmail());
					age = changeNull(ba.getAge());
					birthday = changeNull(ba.getBirth());
					marriage = changeNull(ba.getMaritalstatus());// marriageNum

					int marriageNum = getMarriageNum(marriage); // 婚姻状况，0未知，1未婚，2已婚，3已育，4离异，5丧偶，6其他

					// major = null; // major degree school jobtime height
					// height
					// weight BWH
					degree = changeNull(ba.getDegree());
					school = changeNull(ba.getSchool());
					jobtime = changeNull(ba.getWorkingyear());// 工作年限标志
																// str_25
																// work_experience_int

					int work_experience_int = workyear(jobtime);
					String str_25 = "\\N";
					if (work_experience_int < 1) {// 年

						str_25 = "102";
					} else if (work_experience_int >= 1 && work_experience_int < 3) {

						str_25 = "103";
					} else if (work_experience_int >= 3 && work_experience_int < 5) {

						str_25 = "104";
					} else if (work_experience_int >= 5 && work_experience_int < 10) {
						str_25 = "11";
					} else if (work_experience_int >= 10) {
						str_25 = "12";
					} else {
						str_25 = "102";
					}

					height = changeNull(ba.getHeight());
					weight = changeNull(ba.getWeight());
					BWH = "\\N";
					nationality = "\\N";
					country = "\\N";
					health = "\\N";
					politics = changeNull(ba.getPolitics());
					job_status = changeNull(ba.getJobStatus()); // jobstatus

					int jobstatus = jobstatus(job_status);
					if (jobstatus == 0) {
						job_status = "\\N";
					}

					current_salary = changeNull(ba.getCurrentSalary());
					expected_salary = changeNull(ba.getExpectedSalary()); // expected_salary2

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

					expected_city = changeNull(ba.getExpectedCity());
					if (expected_city.length() > 1024) {
						expected_city = expected_city.substring(0, 1000);
					}
					expected_job = changeNull(ba.getExpectedJob());
					weixin = "\\N";
					qq = "\\N";
					linkedin = "\\N";
					dajie = "\\N";
					weibo = "\\N";
					score = "\\N";
					basic_info = "\\N";
					address = changeNull(ba.getLatteraddress());
					trade_id = "\\N";
					function_id = "\\N";
					mpc = "\\N";
					photo_id = "\\N";
					comment = changeNull(ba.getSelfassessment().replaceAll(";", " "));

					if (comment.length() > 1024)
						comment = comment.substring(0, 1000);

					preference = "\\N";
					flag = "1";
					fileid = "\\N";
					ownerid = "\\N";
					company_id = "\\N";
					job_time = changeNull(ba.getWorkingyear()); // work_experience_int

					// System.out.println("--------------------------------");
					selfassessment = comment;
					expectation_info = " 期望行业:" + ba.getExpectindustry() + ", 期望薪资：" + ba.getExpectedSalary()
							+ ", 期望城市：" + ba.getExpectedCity() + ", 期望工作:" + ba.getExpectedJob() + ", 期望工作性质："
							+ ba.getExpectnaturework();

					// 56行
					String line = id + "\t" + candidateid + "\t" + create_time + "\t" + update_time + "\t"
							+ process_stage + "\t" + name + "\t" + usedname + "\t" + englishname + "\t" + gender + "\t"
							+ cardtype + "\t" + idcard + "\t" + company + "\t" + title + "\t" + city + "\t"
							+ birth_place + "\t" + phone + "\t" + mobile + "\t" + email + "\t" + age + "\t" + birthday
							+ "\t" + marriageNum + "\t" + major + "\t" + degree + "\t" + school + "\t" + str_25 + "\t"
							+ height + "\t" + weight + "\t" + BWH + "\t" + nationality + "\t" + country + "\t" + health
							+ "\t" + politics + "\t" + jobstatus + "\t" + current_salary + "\t" + expected_salary2
							+ "\t" + expected_city + "\t" + expected_job + "\t" + weixin + "\t" + qq + "\t" + mpc + "\t"
							+ linkedin + "\t" + dajie + "\t" + weibo + "\t" + score + "\t" + basic_info + "\t" + address
							+ "\t" + trade_id + "\t" + function_id + "\t" + photo_id + "\t" + comment + "\t"
							+ preference + "\t" + flag + "\t" + fileid + "\t" + ownerid + "\t" + company_id + "\t"
							+ work_experience_int + "\t";

					// File file = new File(cv_basic);
					// if (!file.exists())
					// file.createNewFile();
					// FileWriter fw = new FileWriter(file, true);// 可追加读写入
					// BufferedWriter bw = new BufferedWriter(fw);
					// bw.write(line + "\r\n");
					// bw.flush();
					// bw.close();
					// fw.close();

					cv_basic_txt_fw.write(line + "\r\n");
					cv_basic_txt_fw.flush();

				}

				break;
			}
			if (basic != null) {
				PreparedStatement statement;

				// id = null;
				// candidateid = null;
				// create_time = null;
				// update_time = "0000-00-00 00:00:00";
				// process_stage = null;

				name = changeNull(basic.getName());

				// usedname = null;
				// englishname = null;
				gender = changeNull(genderNum(basic.getGender()) + "");
				// cardtype = null;
				// idcard = null;
				company = changeNull(basic.getCompany());
				title = changeNull(basic.getTitle());
				city = changeNull(basic.getCity());
				birth_place = changeNull(basic.getAccount()); // birth_place

				phone = changeNull(basic.getPhone());
				mobile = changeNull(basic.getMobile());
				email = changeNull(basic.getEmail());
				age = changeNull(basic.getAge());
				birthday = changeNull(basic.getBirth());
				marriage = changeNull(basic.getMaritalstatus());// marriageNum

				int marriageNum = getMarriageNum(marriage); // 婚姻状况，0未知，1未婚，2已婚，3已育，4离异，5丧偶，6其他

				// major = null; // major degree school jobtime height height
				// weight BWH
				degree = changeNull(basic.getDegree());
				school = changeNull(basic.getSchool());
				jobtime = changeNull(basic.getWorkingyear());// 工作年限标志 str_25
																// work_experience_int

				int work_experience_int = workyear(jobtime);
				String str_25 = "\\N";
				if (work_experience_int < 1) {// 年

					str_25 = "102";
				} else if (work_experience_int >= 1 && work_experience_int < 3) {

					str_25 = "103";
				} else if (work_experience_int >= 3 && work_experience_int < 5) {

					str_25 = "104";
				} else if (work_experience_int >= 5 && work_experience_int < 10) {
					str_25 = "11";
				} else if (work_experience_int >= 10) {
					str_25 = "12";
				} else {
					str_25 = "102";
				}

				height = changeNull(basic.getHeight());
				weight = changeNull(basic.getWeight());
				BWH = "\\N";
				nationality = "\\N";
				country = "\\N";
				health = "\\N";
				politics = changeNull(basic.getPolitics());
				job_status = changeNull(basic.getJobStatus()); // jobstatus

				int jobstatus = jobstatus(job_status);
				if (jobstatus == 0) {
					job_status = "\\N";
				}

				current_salary = changeNull(basic.getCurrentSalary());
				expected_salary = changeNull(basic.getExpectedSalary()); // expected_salary2

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

				expected_city = changeNull(basic.getExpectedCity());
				expected_job = changeNull(basic.getExpectedJob());
				weixin = "\\N";
				qq = "\\N";
				linkedin = "\\N";
				dajie = "\\N";
				weibo = "\\N";
				score = "\\N";
				basic_info = "\\N";
				address = changeNull(basic.getLatteraddress());
				trade_id = "\\N";
				function_id = "\\N";
				mpc = "\\N";
				photo_id = "\\N";
				comment = changeNull(basic.getSelfassessment());

				if (comment.length() > 1024)
					comment = comment.substring(0, 1000);

				preference = "\\N";
				flag = "1";
				fileid = "\\N";
				ownerid = "\\N";
				company_id = "\\N";
				job_time = changeNull(basic.getWorkingyear()); // work_experience_int

				// System.out.println("--------------------------------");
				selfassessment = comment;
				expectation_info = " 期望行业:" + basic.getExpectindustry() + ", 期望薪资：" + basic.getExpectedSalary()
						+ ", 期望城市：" + basic.getExpectedCity() + ", 期望工作:" + basic.getExpectedJob() + ", 期望工作性质："
						+ basic.getExpectnaturework();

				// 56行
				String line = id + "\t" + candidateid + "\t" + create_time + "\t" + update_time + "\t" + process_stage
						+ "\t" + name + "\t" + usedname + "\t" + englishname + "\t" + gender + "\t" + cardtype + "\t"
						+ idcard + "\t" + company + "\t" + title + "\t" + city + "\t" + birth_place + "\t" + phone
						+ "\t" + mobile + "\t" + email + "\t" + age + "\t" + birthday + "\t" + marriageNum + "\t"
						+ major + "\t" + degree + "\t" + school + "\t" + str_25 + "\t" + height + "\t" + weight + "\t"
						+ BWH + "\t" + nationality + "\t" + country + "\t" + health + "\t" + politics + "\t" + jobstatus
						+ "\t" + current_salary + "\t" + expected_salary2 + "\t" + expected_city + "\t" + expected_job
						+ "\t" + weixin + "\t" + qq + "\t" + mpc + "\t" + linkedin + "\t" + dajie + "\t" + weibo + "\t"
						+ score + "\t" + basic_info + "\t" + address + "\t" + trade_id + "\t" + function_id + "\t"
						+ photo_id + "\t" + comment + "\t" + preference + "\t" + flag + "\t" + fileid + "\t" + ownerid
						+ "\t" + company_id + "\t" + work_experience_int + "\t";

				// File file = new File(cv_basic);
				// if (!file.exists())
				// file.createNewFile();
				// FileWriter fw = new FileWriter(file, true);// 可追加读写入
				// BufferedWriter bw = new BufferedWriter(fw);
				// bw.write(line + "\r\n");
				// bw.flush();
				// bw.close();
				// fw.close();

				cv_basic_txt_fw.write(line + "\r\n");
				cv_basic_txt_fw.flush();

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
					Pattern pattern1 = Pattern.compile("\\d{1,2}");
					Matcher matcher1 = pattern1.matcher(str);
					int max = 0, min = 0;
					while (matcher1.find()) {
						min = Integer.parseInt(matcher1.group());
						max = max > min ? max : min;
						yearNum = max;
					}
					if (yearNum == 0) {
						yearNum = 11;
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
				str = "\\N";
			else if (str == "\\N") {
				return str;
			} else {

				str = replaceTab(str);
				//str = str.replaceAll("[\\\\?？]", "");
				// str = str.replace("😊", "").replace("𡿨", "");
				str = new String(str.getBytes("gbk"), "gb2312").toString().replace("\\", ",").replace("?", "").replace("？", "");
				str = str.trim().equals("") ? "\\N" : str;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "\\N";
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

	static public String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes("utf-8");
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		int i = 0;
		while (i < bytes.length) {
			short b = bytes[i];
			if (b > 0) {
				buffer.put(bytes[i++]);
				continue;
			}
			b += 256;
			if ((b ^ 0xC0) >> 4 == 0) {
				buffer.put(bytes, i, 2);
				i += 2;
			} else if ((b ^ 0xE0) >> 4 == 0) {
				buffer.put(bytes, i, 3);
				i += 3;
			} else if ((b ^ 0xF0) >> 4 == 0) {
				i += 4;
			}
		}
		buffer.flip();
		return new String(buffer.array(), "utf-8");
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

						if (connCounter % 10000 == 9999) {// 9999

							cv_job_txt_fw.close();
							cv_skill_txt_fw.close();
							cv_project_txt_fw.close();
							cv_medal_txt_fw.close();

							cv_expectation_txt_fw.close();
							cv_education_txt_fw.close();
							cv_comment_txt_fw.close();
							cv_basic_txt_fw.close();
							UnReadFiles_txt_fw.close();

							cv_job_txt_fw = new FileWriter(cv_job, true);// 可追加读写入
							cv_skill_txt_fw = new FileWriter(cv_skill, true);// 可追加读写入
							cv_project_txt_fw = new FileWriter(cv_project, true);// 可追加读写入
							cv_medal_txt_fw = new FileWriter(cv_medal, true);// 可追加读写入

							cv_expectation_txt_fw = new FileWriter(cv_expectation, true);// 可追加读写入
							cv_education_txt_fw = new FileWriter(cv_education, true);// 可追加读写入
							cv_comment_txt_fw = new FileWriter(cv_comment, true);// 可追加读写入
							cv_basic_txt_fw = new FileWriter(cv_basic, true);// 可追加读写入
							UnReadFiles_txt_fw = new FileWriter(UnReadFiles, true);// 可追加读写入

						}

						// 送入模板判断器
						int modelNum = model(readfile);

						if (modelNum != 5) {
							success++;
							// continue;
						}

						// 未写入各个方法 八个方法需写

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
						case 4:
							ZModel5Parse.parse(readfile.getAbsolutePath());
							break;

						case 6:
							WModel6Parse.parse(readfile.getAbsolutePath());
							break;

						case 7:
							UModel7Parse.parse(readfile.getAbsolutePath());
							break;
						case 8:
							brModel8Parse.parse(readfile.getAbsolutePath());
							break;

						case 5:
							/// data/mht/mht2html
							// C:/Users/gongcaichun/Desktop/database/UnReadFiles.txt
							try {

								count++;// 计数器
								System.out.println("文件暂时未能解析！");

								UnReadFiles_txt_fw.write(readfile.getAbsolutePath() + "\r\n");
								UnReadFiles_txt_fw.flush(); // 把缓存区内容压入文件
								break;

								// 将不能处理的文件路径写入UnReadFiles.txt
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (modelNum != 5) {
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
						}

					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i]);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("readfile() Exception:" + e.getMessage());
		}
		return true;
	}

	public static void main(String[] args) {// ArrayIndexOutOfBoundsException
		try {

			cv_job_txt_fw = new FileWriter(cv_job, true);// 可追加读写入
			cv_skill_txt_fw = new FileWriter(cv_skill, true);// 可追加读写入
			cv_project_txt_fw = new FileWriter(cv_project, true);// 可追加读写入
			cv_medal_txt_fw = new FileWriter(cv_medal, true);// 可追加读写入
			cv_expectation_txt_fw = new FileWriter(cv_expectation, true);//
			cv_education_txt_fw = new FileWriter(cv_education, true);//
			cv_comment_txt_fw = new FileWriter(cv_comment, true);// 可追加读写入
			cv_basic_txt_fw = new FileWriter(cv_basic, true);// 可追加读写入
			UnReadFiles_txt_fw = new FileWriter(UnReadFiles, true);// 可追加读写入

			long a = System.currentTimeMillis();

			connect = getConnect();
			readfile("L:/简历分类/未分类/来自I盘/未分类_1");

			System.out.println("耗时： " + (System.currentTimeMillis() - a) / 1000f + " s");
			System.out.println("文件总数： " + sum);
			System.out.println("解析文件成功数： " + success);
			System.out.println("解析文件失败数： " + count);
			System.out.println("help： " + help);

		} catch (Exception ex) {
			ex.printStackTrace();
		} // ArrayIndexOutOfBoundsException

	}

}

/**
 * mht 耗时： 106.034 s 解析文件成功数： 2106 解析文件失败数： 0
 * 
 */

/**
 * 250万HTML 耗时： 36517.996 s 解析文件成功数： 2120677 解析文件失败数： 381
 */

// cv_job_txt = new File(cv_job);
// cv_skill_txt = new File(cv_skill);
// cv_project_txt = new File(cv_project);
// cv_medal_txt = new File(cv_medal);
//
// cv_expectation_txt = new File(cv_expectation);
// cv_education_txt = new File(cv_education);
// cv_comment_txt = new File(cv_comment);
// cv_basic_txt = new File(cv_basic);
// UnReadFiles_txt = new File(UnReadFiles);

// cv_job_txt_fw.write(line + "\r\n");
// cv_job_txt_fw.flush();

// cv_job_txt_bw = new
// BufferedWriter(cv_job_txt_fw);
// cv_skill_txt_bw;
// cv_project_txt_bw;
// cv_medal_txt_bw;
//
// cv_expectation_txt_bw;
// cv_education_txt_bw;
// cv_comment_txt_bw;
// cv_basic_txt_bw;
// UnReadFiles_txt_bw;
