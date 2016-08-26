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
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hyx.parse.model.Job;
import org.hyx.parse.parse.Main;
import org.hyx.parse.parse.WModel4Parse;

public class jdbcTest {

	public static String create_time = "\\N";
	public static String update_time = "\\N";
	public static String candidateid = "\\N";
	public static String process_stage = "\\N";
	public static String start_time = "\\N";
	public static String end_time = "\\N";
	public static String company = "\\N";
	public static String department = "\\N";
	public static String title = "\\N";
	public static String report = "\\N";
	public static String salary = "\\N";
	public static String member = "\\N";
	public static String duty = "\\N";
	public static String achieve = "\\N";
	public static String reason = "\\N";
	public static String city = "\\N";
	public static String info = "\\N";
	public static String score = "\\N";
	public static String job_info = "\\N";
	public static String flag = "\\N";
	public static String fileid = "\\N";

	public static final String url = "jdbc:mysql://127.0.0.1/test";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";

	public static Connection conn = null;
	public static PreparedStatement pst = null;
	public static ResultSet ret = null;
	public static jdbcTest jdbc = null;

	public static Connection getConnect() {
		try {//// "jdbc:mysql://123.57.35.10:3306/honestcareer","root","hunter"
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection("jdbc:mysql://123.57.35.10:3306/honestcareer", "root", "hunter");// 获取连接

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			conn.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 去除制表符
	public static String replaceTab(String str) {
		String dest = "";
		if (str != null) { // \\s*|\t|\r|\n
			Pattern p = Pattern.compile("\\t");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	private static void select() {

		String sql = "select * from cv_copy";
		conn = getConnect();
		try {
			// pst = conn.prepareStatement(sql);
			//ret = pst.executeQuery();
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO cv_copy VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
			ResultSet rsKey = statement.getGeneratedKeys();
			rsKey.next();
			int candidateid = rsKey.getInt(1);
			System.out.println(candidateid);
		} catch (SQLException e) {
			e.printStackTrace();
		} // 准备执行语句

	}

	// Basic2 base
	public static void insertTable() {
		String sql = "insert into cv_job(create_time,candidateid,company,department) values(?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.S");
		System.out.println(df.format(System.currentTimeMillis()));
		// System.exit(0);

		try {
			conn = getConnect();
			pst = conn.prepareStatement(sql);
			pst.setString(1, df.format(System.currentTimeMillis()));
			// pst.setString(2, df.format(System.currentTimeMillis()));
			pst.setString(2, "111");
			pst.setString(3, "★四、3年世界500强大型美资公司人力资源基中层管理经验：2001年3月--2004年3月：摩托罗拉（中国）电子有限公司（著名的大型美");
			pst.setString(4, "美资■经营范围：生产、销售、研发手机、对");
			int res = pst.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test() {
		WModel4Parse w = new WModel4Parse();
		Map<String, Job> jobMap = new LinkedHashMap<String, Job>();// 工作经历
		w.parse("C:/mht2html/前程无忧/0/51job_Davis 安 全(78185802).html");

		File file = new File("C:\\Users\\gongcaichun\\Desktop\\database\\cv_job.txt");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println(df.format(System.currentTimeMillis()));

		jobMap = w.jobMap;
		System.out.println(jobMap.values());
		// System.exit(0);

		for (Job job : jobMap.values()) {
			create_time = df.format(System.currentTimeMillis());
			update_time = "0000-00-00 00:00:00";
			candidateid = "\\N";
			process_stage = "\\N";
			start_time = changNull(job.getStartTime());
			end_time = changNull(job.getEndTime());
			company = changNull(job.getCompany());
			department = changNull(job.getDepartment());
			title = changNull(job.getPosition());
			report = changNull(job.getReport());
			salary = changNull(job.getSalary());
			member = changNull(job.getMember());
			duty = changNull(job.getDuty());
			achieve = changNull(job.getAchieve());
			reason = changNull(job.getReason());
			city = changNull(job.getCity());
			info = changNull(job.getInfo());
			score = changNull(job.getScore() + "");
			job_info = changNull(job.getJobInfo());
			flag = changNull(job.getFlag() + "");
			fileid = "\\N";
			// (id++) + "\t" +
			String line = create_time + "\t" + update_time + "\t" + candidateid + "\t" + process_stage + "\t" // 5
					+ start_time + "\t" + end_time + "\t" + company + "\t" + department + "\t" + title + "\t" + report // 6
					+ "\t" + salary + "\t" + member + "\t" + duty + "\t" + achieve + "\t" + reason + "\t" + city + "\t" // 6
					+ info + "\t" + score + "\t" + job_info + "\t" + flag + "\t" + fileid + "\t"; // 5
			// line.replaceAll("null", "\\N");
			System.out.println(line);
			// System.exit(0);
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file, true);// 可追加读写入
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(line + "\r\n");
				bw.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static String changNull(String str) {
		try {
			str = replaceTab(str);
			str = str.trim().equals("") ? "\\N" : str;
		} catch (NullPointerException e) {
			return "\\N";
		}
		return str;
	}

	public static void main(String[] args) {
		String str = "select * from cv_job";
		// insertTable();
		// test();
		select();
		// try {
		// ret = new jdbcTest(str).pst.executeQuery();
		// int i = 1;
		// while (ret.next()) {
		// if (i == 3)
		// continue;
		// System.out.println(ret.getTimestamp("update_time"));
		// i++;
		// }
		// ret.close();
		// jdbcTest.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

	}

}
// 插入数据库
// String sql = "insert into cv_job(create_time,
// update_time,candidateid,"
// + "
// process_stage,start_time,end_time,company,department,title,report,salary,"
// + " member,duty,achieve,reason,city,info,score,job_info,flag,fileid)
// values(?,?,?)";
