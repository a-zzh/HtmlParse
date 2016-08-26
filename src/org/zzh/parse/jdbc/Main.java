package org.zzh.parse.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
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
import org.hyx.parse.parse.WModel4Parse;
import org.hyx.parse.parse.ZModel2Parse;
import org.hyx.parse.parse.ZModel3Parse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
	static int x = 0, y = 0, z = 0, sum = 1;
	static String toPath1;
	static String toPath2;
	static String toPath3;

	private static Document doc;
	private static Elements eles, eles2, eles3;
	static int count = 0;
	// basic

	// public static String id;
	// public static String candidateid;
	// public static String create_time;
	// public static String update_time;
	// public static String process_stage;
	public static String name;
	public static String usedname;
	public static String englishname;
	public static String gender;
	public static String cardtype;
	public static String idcard;
	// public static String company;
	// public static String title;
	// public static String city;
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
	// public static String score;
	public static String basic_info;
	public static String address;
	public static String trade_id;
	public static String function_id;
	public static String mpc;
	public static String photo_id;
	public static String comment;
	public static String preference;
	// public static String flag;
	// public static String fileid;
	public static String ownerid;
	public static String company_id;
	public static String job_time;

	// job
	public static String id;
	public static String create_time;
	public static String update_time;
	public static String candidateid;
	public static String process_stage;
	public static String start_time;
	public static String end_time;
	public static String company;
	public static String department;
	public static String title;
	public static String report;
	public static String salary;
	public static String member;
	public static String duty;
	public static String achieve;
	public static String reason;
	public static String city;
	public static String info;
	public static String score;
	public static String job_info;
	public static String flag;
	public static String fileid;

	public static String selfassessment; // 自我评价

	public static String expectation_info;// 期望信息

	// 获奖信息 get_time medal_name medal_info
	public static String get_time;
	public static String medal_name;
	public static String medal_info;

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
				System.out.println("ZModel2Parse"); // 智联
				return 1;
			} else if (eles2.size() != 0) { // ZModel3Parse
				System.out.println("ZModel3Parse");
				return 2;
			} else if (eles3.size() != 0) { // WModel4Parse
				System.out.println("WModel4Parse"); // 无忧
				return 3;
				// System.out.println(filename);
				// System.exit(0);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 5;
	}

	public static void jobTable(int modelNum) throws IOException {
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

			File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_job.txt");
			if (!file.exists())
				file.createNewFile();

			// System.out.println(df.format(System.currentTimeMillis()));

			// System.out.println(jobMap.values());
			// System.exit(0);
			System.out.println("jobMap.size()-------------------------" + jobMap.size());
			for (Job job : jobMap.values()) {
				create_time = df.format(System.currentTimeMillis());
				update_time = "0000-00-00 00:00:00";
				candidateid = "\\N";
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
				// (id++) + "\t" +
				String line = "\\N" + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t"
						+ process_stage + "\t" // 5
						+ start_time + "\t" + end_time + "\t" + company + "\t" + department + "\t" + title + "\t"
						+ report // 6
						+ "\t" + salary + "\t" + member + "\t" + duty + "\t" + achieve + "\t" + reason + "\t" + city
						+ "\t" // 6
						+ info + "\t" + score + "\t" + job_info + "\t" + flag + "\t" + fileid + "\t"; // 5
				// line.replaceAll("null", "\\N");
				System.out.println(line);
				// System.exit(0);

				FileWriter fw = new FileWriter(file, true);// 可追加读写入
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(line + "\r\n");
				bw.flush();
				bw.close();
				fw.close();

			}

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

	public static void skillTable(int modelNum) throws IOException {
		String create_time = null;
		String update_time = null;
		String candidateid = null;
		String process_stage = null;
		String skill_name = null;
		String info = null;
		String score = null;
		String skill_info = null;
		String flag = null;
		String skill_experience = null;
		Map<String, Skill> skillMap = new LinkedHashMap<String, Skill>(); // 技能
		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_skill.txt");
		if (!file.exists())
			file.createNewFile();
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
				candidateid = null;
				process_stage = null;
				skill_name = skill.getSkillName();
				info = skill.getInfo();
				score = skill.getScore() + "";
				skill_info = skill.getSkillInfo();
				flag = "1";
				skill_experience = skill.getTime();

				// 插入

			}
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
		String start_time = null;
		String end_time = null;
		String project_name = null;
		String company = null;
		String content = null;
		String duty = null;
		String achieve = null;
		String city = null;
		String info = null;
		String score = null;
		String project_info = null;
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

				String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage
						+ "\t" + start_time + "\t" + end_time + "\t" + project_name + "\t" + company + "\t" + content
						+ "\t" + duty + "\t" + achieve + "\t" + info + "\t" + score + "\t" + project_info + "\t" + flag
						+ "\t";

				FileWriter fw = new FileWriter(file, true);// 可追加读写入
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(line + "\r\n");
				bw.flush();
				bw.close();
			}
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

	public static void medalTable(int modelNum) throws IOException {
		Map<String, Medal> medalMap = new LinkedHashMap<String, Medal>(); // 所获奖项
		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_medal.txt");
		if (!file.exists())
			file.createNewFile();
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
				info = "\\N";
				score = "\\N";

				String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage
						+ "\t" + get_time + "\t" + medal_name + "\t" + info + "\t" + score + "\t" + medal_info + "\t"
						+ flag + "\t";

				FileWriter fw = new FileWriter(file, true);// 可追加读写入
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(line + "\r\n");
				bw.flush();
				bw.close();

			}
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

	public static void expectationTable(int modelNum) throws IOException {
		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_expectation.txt");
		if (!file.exists())
			file.createNewFile();

		info = "\\N";
		expectation_info = changeNull(expectation_info);
		String line = id + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage + "\t"
				+ info + "\t" + score + "\t" + expectation_info + "\t" + flag + "\t" + fileid + "\t";
		FileWriter fw = new FileWriter(file, true);// 可追加读写入
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line + "\r\n");
		bw.flush();
		bw.close();

	}

	public static void educationTable(int modelNum) throws IOException {
		// System.out.println(medalMap.size());
		// System.exit(0);

		Map<String, Education> educationMap = new LinkedHashMap<String, Education>();// 教育经历
		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_education.txt");
		if (!file.exists())
			file.createNewFile();
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

		String start_time = null;
		String end_time = null;
		String school = null;
		String major = null;
		String degree = null;
		String info = null;
		String score = null;
		String education_info = null;

		if (educationMap.size() != 0) {
			for (Education education : educationMap.values()) {
				id = "\\N";
				// create_time
				// update_time
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
				String line = id + "\t" + create_time + "\t" + update_time + "\t" + process_stage + "\t" + start_time
						+ "\t" + end_time + "\t" + school + "\t" + major + "\t" + degree + "\t" + info + "\t" + score
						+ "\t" + education_info + "\t" + flag + "\t" + fileid + "\t";

				FileWriter fw = new FileWriter(file, true);// 可追加读写入
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(line + "\r\n");
				bw.flush();
				bw.close();

			}
		}

	}

	public static void commentTable(int modelNum) throws IOException {

		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_comment.txt");
		if (!file.exists())
			file.createNewFile();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		String comment_info = selfassessment;
		info = "\\N";

		String line = "\\N" + "\t" + create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage + "\t"
				+ info + "\t" + comment_info + "\t" + flag + "\t" ;

		FileWriter fw = new FileWriter(file, true);// 可追加读写入
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line + "\r\n");
		bw.flush();
		bw.close();
		fw.close();

	}

	public static void basicTable(int modelNum) throws IOException {
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

		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_basic.txt");
		if (!file.exists())
			file.createNewFile();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println(basic.toString());

		id = "\\N";
		candidateid = "\\N";
		create_time = df.format(System.currentTimeMillis());
		update_time = "0000-00-00 00:00:00";
		process_stage = "\\N";

		name = changeNull(basic.getName());

		usedname = "\\N";
		englishname = "\\N";
		gender = changeNull(genderNum(basic.getGender()) + "");
		cardtype = "\\N";
		idcard = "\\N";
		company = changeNull(basic.getCompany());
		title = changeNull(basic.getTitle());
		city = changeNull(basic.getCity());
		birth_place = changeNull(basic.getAccount());
		phone = changeNull(basic.getPhone());
		mobile = changeNull(basic.getMobile());
		email = changeNull(basic.getEmail());
		age = changeNull(basic.getAge());
		birthday = changeNull(basic.getBirth());
		marriage = changeNull(basic.getMaritalstatus());
		major = "\\N";
		degree = changeNull(basic.getDegree());
		school = changeNull(basic.getSchool());
		jobtime = changeNull(basic.getDutytime());// 工作年限
		height = changeNull(basic.getHeight());
		weight = changeNull(basic.getWeight());
		BWH = "\\N";
		nationality = "\\N";
		country = "\\N";
		health = "\\N";
		politics = changeNull(basic.getPolitics());
		job_status = changeNull(basic.getJobStatus());
		current_salary = changeNull(basic.getCurrentSalary());
		expected_salary = changeNull(basic.getExpectedSalary());
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
		preference = "\\N";
		flag = "1";
		fileid = "\\N";
		ownerid = "\\N";
		company_id = "\\N";
		job_time = changeNull(basic.getWorkingyear());

		System.out.println("--------------------------------");
		selfassessment = comment;
		expectation_info = " 期望行业:" + basic.getExpectindustry() + ", 期望薪资：" + basic.getExpectedSalary() + ", 期望城市："
				+ basic.getExpectedCity() + ", 期望工作:" + basic.getExpectedJob() + ", 期望工作性质："
				+ basic.getExpectnaturework();

		String line = id + "\t" + candidateid + "\t" + create_time + "\t" + update_time + "\t" + process_stage + "\t"
				+ name + "\t" + usedname + "\t" + englishname + "\t" + gender + "\t" + cardtype + "\t" + idcard + "\t"
				+ company + "\t" + title + "\t" + city + "\t" + birth_place + "\t" + phone + "\t" + mobile + "\t"
				+ email + "\t" + age + "\t" + birthday + "\t" + marriage + "\t" + major + "\t" + degree + "\t" + school
				+ "\t" + jobtime + "\t" + height + "\t" + weight + "\t" + BWH + "\t" + nationality + "\t" + country
				+ "\t" + health + "\t" + politics + "\t" + job_status + "\t" + current_salary + "\t" + expected_salary
				+ "\t" + expected_city + "\t" + expected_job + "\t" + weixin + "\t" + qq + "\t" + mpc + "\t" + linkedin
				+ "\t" + dajie + "\t" + weibo + "\t" + score + "\t" + basic_info + "\t" + address + "\t" + trade_id
				+ "\t" + function_id + "\t" + photo_id + "\t" + comment + "\t" + preference + "\t" + flag + "\t"
				+ fileid + "\t" + ownerid + "\t" + company_id + "\t" + job_time + "\t";

		// (id++) + "\t" +
		// line.replaceAll("null", "\\N");
		System.out.println(line);
		// System.exit(0);

		FileWriter fw = new FileWriter(file, true);// 可追加读写入
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line + "\r\n");
		bw.flush();
		bw.close();
		fw.close();

	}

	public static String changeNull(String str) {
		try {

			if (str == null)
				str = "";
			else {
				str = replaceTab(str);
				str = str.replace("\\", " ");
				// str = filterOffUtf8Mb4(str);
				str = str.trim().equals("") ? "\\N" : str;
			} 
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "\\N"; 
		}
		return str;
	}

	// 去除制表符
	public static String replaceTab(String str) {
		String dest = "";
		if (str != null) { // \\s*|\t|\r|\n \t
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
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
						 jobTable(modelNum);
						 commentTable(modelNum);
						 educationTable(modelNum);
						 expectationTable(modelNum);
						 medalTable(modelNum);
						 projectTable(modelNum);
						 skillTable(modelNum);
						 basicTable(modelNum);
						 
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
			readfile("C:\\Users\\gongcaichun\\Desktop\\模板\\WModel4Parse\\前程无忧");
			System.out.println("耗时： "+(System.currentTimeMillis()-a)/1000f+" s");
			System.out.println("解析文件成功数： " + sum);
			System.out.println("解析文件失败数： " + count);

		} catch (Exception ex) {
		} // ArrayIndexOutOfBoundsException
	}

}
