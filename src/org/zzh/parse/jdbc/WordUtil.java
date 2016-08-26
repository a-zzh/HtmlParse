package org.zzh.parse.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class WordUtil {

	/**
	 * ��ȡ�ļ��µ�ÿһ�е�����
	 * 
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> readFile(File file) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String line = "";

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "utf8");
			br = new BufferedReader(isr);
			Connection connect = null;
			try {
				connect = DriverManager.getConnection("jdbc:mysql://123.57.35.10:3306/hunter2", "root", "hunter");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while ((line = br.readLine()) != null) {
				ResultSet rsKey;
				int candidateId = 0;
				String company_temp = null;
				String title_temp = null;
				String degree_temp = null;
				String school_temp = null;
				String major_temp = null;
				String end_job_time = "0";
				String end_school_time = "0";
				Integer expected_salary = 148;
				Integer degree_interger = 0;
				List<String> list_citys = new ArrayList<String>();
				// Connection connect = null;
				// connect = DriverManager.getConnection(
				// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
				try {
					Date date = new Date();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = format.format(date);
					// MessageDigest digest = null;
					/*
					 * try { digest = MessageDigest.getInstance("MD5"); } catch
					 * (NoSuchAlgorithmException e) { e.printStackTrace(); }
					 */
					/*
					 * byte[] content = result.getBytes(); //byte[] filename =
					 * talent_one.getOriginalFilename().getBytes(); //byte[] id
					 * = loginUser.getId().toString().getBytes(); //byte[] type
					 * = "3".toString().getBytes(); byte[] total = new
					 * byte[content.length];
					 * System.arraycopy(content,0,total,0,content.length);
					 * //System.arraycopy(filename,0,total,content.length,
					 * filename.length);
					 * //System.arraycopy(id,0,total,content.length+filename.
					 * length,id.length);
					 * //System.arraycopy(type,0,total,content.length+id.length,
					 * type.length); digest.update(total, 0, total.length);
					 * BigInteger bigInt = new BigInteger(1, digest.digest());
					 */
					PreparedStatement statement = connect.prepareStatement(
							"INSERT INTO cv VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
					statement.setString(1, null);
					statement.setString(2, null);
					statement.setInt(3, 1);
					statement.setString(4, null);
					statement.setString(5, time);
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
					// statement.setString(25,null);
					// statement.setString(10,null);
					statement.executeUpdate();
					rsKey = statement.getGeneratedKeys();
					rsKey.next();
					// System.out.println(rsKey.getInt(1));
					candidateId = rsKey.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Map<String, Object> cvMap = new HashMap<String, Object>();
					map = parseJSON2Map(line);
					int work_experience_int = 0;
					// ��ʼ��ȡ��д�뵽word��
					if (map.containsKey("cv_parse")) {
						Map<String, Object> cv_parseMap = new HashMap<String, Object>();
						String cv_parse = map.get("cv_parse").toString();
						cv_parseMap = parseJSON2Map(cv_parse);
						if (cv_parseMap.containsKey("basic_info")) {

							Map<String, Object> basic_infoMap = new HashMap<String, Object>();
							String basic_info = cv_parseMap.get("basic_info").toString();
							basic_infoMap = parseJSON2Map(basic_info);

							if (basic_infoMap.size() > 0) {
								String name = (String) basic_infoMap.get("name");
								if (null != name && !"".equals(name)) {
									cvMap.put("name", name);
								} else {
									cvMap.put("name", "保密");
								}
								String id_number = (String) basic_infoMap.get("id_number");
								if (null != id_number && !"".equals(id_number)) {
									cvMap.put("id_number", id_number);
								} else {
									cvMap.put("id_number", "保密");
								}
								String gender = (String) basic_infoMap.get("gender");
								if (null != gender && !"".equals(gender)) {
									cvMap.put("gender", gender);
								} else {
									cvMap.put("gender", "保密");
								}
								String nation = (String) basic_infoMap.get("nation");
								if (null != nation && !"".equals(nation)) {
									cvMap.put("nation", nation);
								} else {
									cvMap.put("nation", "保密");
								}
								String birthyear = (String) basic_infoMap.get("birthyear");
								if (null != birthyear && !"".equals(birthyear)) {
									cvMap.put("birthyear", birthyear);
								} else {
									cvMap.put("birthyear", "保密");
								}
								String work_experience = (String) basic_infoMap.get("work_experience");
								if (null != work_experience && !"".equals(work_experience)) {
									cvMap.put("work_experience", work_experience);
									Calendar calendar = Calendar.getInstance();
									int year = calendar.get(Calendar.YEAR);
									work_experience_int = year - Integer.parseInt(work_experience);
								} else {
									cvMap.put("work_experience", "保密");
								}
								String location = basic_infoMap.get("location").toString();
								if (null != location && !"".equals(location)) {
									Map<String, Object> locationMap = new HashMap<String, Object>();
									locationMap = parseJSON2Map(location);
									if (locationMap.size() > 0) {
										String province = (String) locationMap.get("province");
										if (null != province && !"".equals(province)) {
											cvMap.put("province", province);
										} else {
											cvMap.put("province", "保密");
										}
										String city = (String) locationMap.get("city");
										if (null != city && !"".equals(city)) {
											cvMap.put("city", city);
										} else {
											cvMap.put("city", "保密");
										}
									}
								}

								try {
									if (basic_infoMap.get("current_yearsalary") != null) {
										String current_yearsalary = basic_infoMap.get("current_yearsalary").toString();
										if (null != current_yearsalary && !"".equals(current_yearsalary)) {
											Map<String, Object> current_yearsalaryMap = new HashMap<String, Object>();
											current_yearsalaryMap = parseJSON2Map(current_yearsalary);
											if (current_yearsalaryMap.size() > 0) {
												String upper = (String) current_yearsalaryMap.get("upper");
												if (null != upper && !"".equals(upper)) {
													cvMap.put("upper", upper);
												} else {
													cvMap.put("upper", "保密");
												}
												String current_salary = (String) current_yearsalaryMap
														.get("current_salary");
												if (null != current_salary && !"".equals(current_salary)) {
													cvMap.put("current_salary", current_salary);
												} else {
													cvMap.put("current_salary", "保密");
												}
												String floor = (String) current_yearsalaryMap.get("floor");
												if (null != floor && !"".equals(floor)) {
													cvMap.put("floor", floor);
												} else {
													cvMap.put("floor", "保密");
												}
											}
										}
									}
								} catch (Exception e) {
									// e.printStackTrace();
								}
							}
							// 自我评价
							if (cv_parseMap.containsKey("self_evaluate")) {
								String self_evaluate = cv_parseMap.get("self_evaluate").toString();
								cvMap.put("self_evaluate", self_evaluate);
								// connect = DriverManager.getConnection(
								// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
								try {
									Date date = new Date();
									DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String time = format.format(date);
									PreparedStatement statement = connect.prepareStatement(
											"INSERT INTO cv_comment VALUES(?,?,?,?,?,?,?,?)",
											PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
									// 8行
									statement.setString(1, null);
									statement.setString(2, null);
									statement.setString(3, time);
									statement.setInt(4, candidateId);
									statement.setString(5, null);
									statement.setString(6, null);
									statement.setString(7, self_evaluate);
									statement.setInt(8, 1);
									statement.executeUpdate();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							// 求职意向
							if (cv_parseMap.containsKey("job_objective")) {
								String job_objective = cv_parseMap.get("job_objective").toString();
								Map<String, Object> job_objectiveMap = new HashMap<String, Object>();
								job_objectiveMap = parseJSON2Map(job_objective);
								if (job_objectiveMap.size() > 0) {
									String industries = job_objectiveMap.get("industries").toString();
									if (null != industries && !"".equals(industries)) {
										cvMap.put("industries", industries);
									} else {
										cvMap.put("industries", "保密");
									}
									String expect_salary_upper = job_objectiveMap.get("expect_salary_upper").toString();
									if (null != expect_salary_upper && !"".equals(expect_salary_upper)) {
										cvMap.put("expect_salary_upper", expect_salary_upper);
									} else {
										cvMap.put("expect_salary_upper", "保密");
									}

									String expect_salary = job_objectiveMap.get("expect_salary").toString();
									if (null != expect_salary && !"".equals(expect_salary)) {
										cvMap.put("expect_salary", expect_salary);
									} else {
										cvMap.put("expect_salary", "保密");
									}

									String expect_titles = job_objectiveMap.get("expect_titles").toString();
									if (null != expect_titles && !"".equals(expect_titles)) {
										cvMap.put("expect_titles", expect_titles);
									} else {
										cvMap.put("expect_titles", "");
									}
									String expect_salary_floor = job_objectiveMap.get("expect_salary_floor").toString();
									if (null != expect_salary_floor && !"".equals(expect_salary_floor)) {
										cvMap.put("expect_salary_floor", expect_salary_floor);
									} else {
										cvMap.put("expect_salary_floor", "保密");
									}

									String status = job_objectiveMap.get("status").toString();
									if (null != status && !"".equals(status)) {
										Map<String, Object> statusMap = new HashMap<String, Object>();
										statusMap = parseJSON2Map(status);
										if (statusMap.size() > 0) {
											String status_code = statusMap.get("status_code").toString();
											if (null != status_code && !"".equals(status_code)) {
												cvMap.put("status_code", status_code);
											} else {
												cvMap.put("status_code", "保密");
											}

											String status_msg = statusMap.get("status_msg").toString();
											if (null != status_msg && !"".equals(status_msg)) {
												cvMap.put("status_msg", status_msg);
											} else {
												cvMap.put("status_msg", "保密");
											}

										}
									}
									// 地点
									List<Object> expect_locations = (List<Object>) job_objectiveMap
											.get("expect_locations");
									List<Map<String, Object>> expect_citys = new ArrayList<Map<String, Object>>();
									Map<String, Object> citys = new HashMap<String, Object>();
									if (expect_locations.size() > 0) {
										Map<String, Object> expect_locationsMap = new HashMap<String, Object>();
										for (Object o : expect_locations) {
											expect_locationsMap = parseJSON2Map(o.toString());
											if (expect_locationsMap.size() > 0) {

												String expect_province = expect_locationsMap.get("province").toString();
												if (null != expect_province && !"".equals(expect_province)) {
													citys.put("expect_province", expect_province);
												} else {
													citys.put("expect_province", "保密");
												}

												String expect_city = expect_locationsMap.get("city").toString();
												if (null != expect_city && !"".equals(expect_city)) {
													citys.put("expect_city", expect_city);
													list_citys.add(expect_city);
												} else {
													citys.put("expect_city", "保密");
													// list_citys.add("保密");
												}
											}
											expect_citys.add(citys);
										}
										cvMap.put("expect_citys", expect_citys);
									}
									if (expect_salary_upper != null && !expect_salary_upper.equals("")) {
										if (Integer.parseInt(expect_salary_upper) * 12 <= 0) {
											expected_salary = 148;
										} else if (Integer.parseInt(expect_salary_upper) * 12 > 0
												&& Integer.parseInt(expect_salary_upper) * 12 < 50000) {
											expected_salary = 67;
										} else if (Integer.parseInt(expect_salary_upper) * 12 >= 50000
												&& Integer.parseInt(expect_salary_upper) * 12 < 100000) {
											expected_salary = 68;
										} else if (Integer.parseInt(expect_salary_upper) * 12 >= 100000
												&& Integer.parseInt(expect_salary_upper) * 12 < 200000) {
											expected_salary = 69;
										} else if (Integer.parseInt(expect_salary_upper) * 12 >= 200000
												&& Integer.parseInt(expect_salary_upper) * 12 < 300000) {
											expected_salary = 70;
										} else if (Integer.parseInt(expect_salary_upper) * 12 >= 300000
												&& Integer.parseInt(expect_salary_upper) * 12 < 500000) {
											expected_salary = 71;
										} else if (Integer.parseInt(expect_salary_upper) * 12 >= 500000
												&& Integer.parseInt(expect_salary_upper) * 12 < 1000000) {
											expected_salary = 72;
										} else if (Integer.parseInt(expect_salary_upper) * 12 >= 1000000) {
											expected_salary = 73;
										}
									}
								}
							}

							// 工作经历
							if (cv_parseMap.containsKey("occupations")) {
								List<Object> occupations = (List<Object>) cv_parseMap.get("occupations");
								List<Map<String, Object>> occupation_list = new ArrayList<Map<String, Object>>();
								Map<String, Object> occupation = new HashMap<String, Object>();
								if (occupations.size() > 0) {
									Map<String, Object> occupationsMap = new HashMap<String, Object>();
									for (Object o : occupations) {
										occupationsMap = parseJSON2Map(o.toString());
										if (occupationsMap.size() > 0) {
											String title = occupationsMap.get("title").toString();
											if (null != title && !"".equals(title)) {
												occupation.put("title", title);
											} else {
												occupation.put("title", "保密");
											}

											String start_time = occupationsMap.get("start_time").toString();
											if (null != start_time && !"".equals(start_time)) {
												occupation.put("startTime", start_time);
											} else {
												occupation.put("startTime", "保密");
											}

											String end_time = "";
											if (occupationsMap.get("end_time") != null) {
												end_time = occupationsMap.get("end_time").toString();
												if (null != end_time && !"".equals(end_time)) {
													occupation.put("endTime", end_time);
												} else {
													occupation.put("endTime", "保密");
												}
											}

											String department = "";
											if (occupationsMap.get("department") != null) {
												department = occupationsMap.get("department").toString();
												if (null != department && !"".equals(department)) {
													occupation.put("department", department);
												} else {
													occupation.put("department", "");
												}
											}

											String company = occupationsMap.get("company").toString();
											if (null != company && !"".equals(company)) {
												occupation.put("company", company);
											} else {
												occupation.put("company", "");
											}

											String industry = occupationsMap.get("industry").toString();
											if (null != industry && !"".equals(industry)) {
												occupation.put("industry", industry);
											} else {
												occupation.put("industry", "");
											}

											String desc = occupationsMap.get("desc").toString();
											if (null != desc && !"".equals(desc)) {
												occupation.put("desc", desc);
											} else {
												occupation.put("desc", "");
											}
											if (end_time.length() == 6) {
												end_time = end_time.substring(0, end_time.indexOf('/')) + "0" + end_time
														.substring(end_time.indexOf('/') + 1, end_time.length());
												// System.out.println(end_time);
											} else if (end_time.length() == 7) {
												end_time = end_time.substring(0, end_time.indexOf('/')) + end_time
														.substring(end_time.indexOf('/') + 1, end_time.length());
											} else if (end_time == null || end_time.equals("")) {
												end_time = "0";
											}
											// System.out.println("end_time_after
											// = " + end_time);
											if (Integer.parseInt(end_time) > Integer.parseInt(end_job_time)) {
												end_job_time = end_time;
												company_temp = company;
												title_temp = title;
											}

											if (occupation.get("startTime").toString().length() == 6) {
												start_time = occupation.get("startTime").toString().substring(0,
														occupation.get("startTime").toString().indexOf('/'))
														+ "-0"
														+ occupation.get("startTime").toString().substring(
																occupation.get("startTime").toString().indexOf('/') + 1,
																occupation.get("startTime").toString().length());
											} else if (occupation.get("startTime").toString().length() == 7) {
												start_time = occupation.get("startTime").toString().substring(0,
														occupation.get("startTime").toString().indexOf('/'))
														+ "-"
														+ occupation.get("startTime").toString().substring(
																occupation.get("startTime").toString().indexOf('/') + 1,
																occupation.get("startTime").toString().length());
											}
											String end_time1 = null;
											if (occupation.get("endTime").toString().length() == 6) {
												end_time1 = occupation.get("endTime").toString().substring(0,
														occupation.get("endTime").toString().indexOf('/'))
														+ "-0"
														+ occupation.get("endTime").toString().substring(
																occupation.get("endTime").toString().indexOf('/') + 1,
																occupation.get("endTime").toString().length());
											} else if (occupation.get("endTime").toString().length() == 7) {
												end_time1 = occupation.get("endTime").toString().substring(0,
														occupation.get("endTime").toString().indexOf('/'))
														+ "-"
														+ occupation.get("endTime").toString().substring(
																occupation.get("endTime").toString().indexOf('/') + 1,
																occupation.get("endTime").toString().length());
											}

											// connect =
											// DriverManager.getConnection(
											// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
											try {
												Date date = new Date();
												DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
												String time = format.format(date);
												PreparedStatement statement = connect.prepareStatement(
														"INSERT INTO cv_job VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
														PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
												// 22行
												statement.setString(1, null);
												statement.setString(2, null);
												statement.setString(3, time);
												statement.setInt(4, candidateId);
												statement.setString(5, null);
												statement.setString(6, start_time);
												statement.setString(7, end_time1);
												statement.setString(8, occupation.get("company").toString());
												if (occupation.get("department") != null) {
													statement.setString(9, occupation.get("department").toString());
												} else {
													statement.setString(9, null);
												}
												statement.setString(10, occupation.get("title").toString());
												statement.setString(11, null);
												statement.setString(12, null);
												statement.setString(13, null);
												statement.setString(14, null);
												statement.setString(15, null);
												statement.setString(16, null);
												statement.setString(17, null);
												statement.setString(18, null);
												statement.setString(19, null);
												if (occupation.get("desc").toString().length() > 1000) {
													statement.setString(20,
															occupation.get("desc").toString().substring(0, 1000));
												} else {
													statement.setString(20,
															occupation.get("desc").toString().toString());
												}
												// statement.setString(20,occupation.get("desc").toString());
												statement.setInt(21, 1);
												statement.setString(22, null);
												statement.executeUpdate();
											} catch (SQLException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
										occupation_list.add(occupation);
									}
									cvMap.put("occupation_list", occupation_list);
								} else {
									cvMap.put("occupation_list", occupation_list);
								}
							}

							// 教育经历
							if (cv_parseMap.containsKey("educations")) {
								List<Object> educations = (List<Object>) cv_parseMap.get("educations");
								List<Map<String, Object>> educations_list = new ArrayList<Map<String, Object>>();
								Map<String, Object> education = new HashMap<String, Object>();
								if (educations.size() > 0) {
									Map<String, Object> educationsMap = new HashMap<String, Object>();
									for (Object o : educations) {
										educationsMap = parseJSON2Map(o.toString());
										if (educationsMap.size() > 0) {
											String major = educationsMap.get("major").toString();
											if (null != major && !"".equals(major)) {
												education.put("major", major);
												cvMap.put("major", major);
											} else {
												education.put("major", "保密");
												cvMap.put("major", "保密");
											}

											String degree = educationsMap.get("degree").toString();
											if (null != degree && !"".equals(degree)) {
												education.put("degree", degree);
											} else {
												education.put("degree", "");
											}

											if (degree.contains("本科") || degree.contains("学士")) {
												degree_interger = 4;
											} else if (degree.contains("专科") || degree.contains("大专")
													|| degree.contains("高中") || degree.contains("初中")) {
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

											String school_level = (String) educationsMap.get("school_level");
											if (null != school_level && !"".equals(school_level)) {
												education.put("school_level", school_level);
											} else {
												education.put("school_level", "");
											}

											String start_time = educationsMap.get("start_time").toString();
											if (null != start_time && !"".equals(start_time)) {
												education.put("startTime", start_time);
											} else {
												education.put("startTime", "");
											}

											String end_time = educationsMap.get("end_time").toString();
											if (null != end_time && !"".equals(end_time)) {
												education.put("endTime", end_time);
											} else {
												education.put("endTime", "");
											}

											String school = educationsMap.get("school").toString();
											if (null != school && !"".equals(school)) {
												education.put("school", school);
												cvMap.put("school", school);
											} else {
												education.put("school", "");
												cvMap.put("school", "");
											}
											if (end_time.length() == 6) {
												end_time = end_time.substring(0, end_time.indexOf('/')) + "0" + end_time
														.substring(end_time.indexOf('/') + 1, end_time.length());
												// System.out.println(end_time);
											} else if (end_time.length() == 7) {
												end_time = end_time.substring(0, end_time.indexOf('/')) + end_time
														.substring(end_time.indexOf('/') + 1, end_time.length());
											} else if (end_time == null || end_time.equals("")) {
												end_time = "0";
											}
											if (Integer.parseInt(end_time) > Integer.parseInt(end_school_time)) {
												end_school_time = end_time;
												degree_temp = degree_interger.toString();
												school_temp = school;
												major_temp = major;
											}

											// if(education.get("startTime").toString().length()
											// == 6)
											// {
											// start_time =
											// education.get("startTime").toString().substring(0,
											// education.get("startTime").toString().indexOf('/'))
											// + "-0" +
											// education.get("startTime").toString().substring(education.get("startTime").toString().indexOf('/')+1,
											// education.get("startTime").toString().length());
											// }
											// else
											// if(education.get("startTime").toString().length()
											// == 7)
											// {
											// start_time =
											// education.get("startTime").toString().substring(0,
											// education.get("startTime").toString().indexOf('/'))
											// + "-" +
											// education.get("startTime").toString().substring(education.get("startTime").toString().indexOf('/')+1,
											// education.get("startTime").toString().length());
											// }
											// String end_time1 = null;
											// if(education.get("endTime").toString().length()
											// == 6)
											// {
											// end_time1 =
											// education.get("endTime").toString().substring(0,
											// education.get("endTime").toString().indexOf('/'))
											// + "-0" +
											// education.get("endTime").toString().substring(education.get("endTime").toString().indexOf('/')+1,
											// education.get("endTime").toString().length());
											// }
											// else
											// if(education.get("endTime").toString().length()
											// == 7)
											// {
											// end_time1 =
											// education.get("endTime").toString().substring(0,
											// education.get("endTime").toString().indexOf('/'))
											// + "-" +
											// education.get("endTime").toString().substring(education.get("endTime").toString().indexOf('/')+1,
											// education.get("endTime").toString().length());
											// }

											// connect =
											// DriverManager.getConnection(
											// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
											try {
												Date date = new Date();
												DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
												String time = format.format(date);
												PreparedStatement statement = connect.prepareStatement(
														"INSERT INTO cv_education VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
														PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
												// 15行
												statement.setString(1, null);
												statement.setString(2, null);
												statement.setString(3, time);
												statement.setInt(4, candidateId);
												statement.setString(5, null);
												statement.setString(6, start_time);
												statement.setString(7, end_time);
												statement.setString(8, education.get("school").toString());
												statement.setString(9, education.get("major").toString());
												statement.setInt(10, degree_interger);
												statement.setString(11, null);
												statement.setString(12, null);
												statement.setString(13, null);
												statement.setInt(14, 1);
												statement.setString(15, null);
												statement.executeUpdate();
											} catch (SQLException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
										educations_list.add(education);
									}
									cvMap.put("educations_list", educations_list);
								} else {
									cvMap.put("educations_list", educations_list);
								}
							}

							// 项目经历
							if (cv_parseMap.containsKey("projects")) {
								List<Object> projects = (List<Object>) cv_parseMap.get("projects");
								List<Map<String, Object>> projects_list = new ArrayList<Map<String, Object>>();
								Map<String, Object> project = new HashMap<String, Object>();
								if (projects.size() > 0) {
									Map<String, Object> projectsMap = new HashMap<String, Object>();
									for (Object o : projects) {
										projectsMap = parseJSON2Map(o.toString());
										if (projectsMap.size() > 0) {
											String start_month = projectsMap.get("start_month").toString();
											String start_year = projectsMap.get("start_year").toString();
											String start_time = null;
											if (start_month.length() == 1) {
												start_time = start_year + "-0" + start_month;
											} else if (start_month.length() == 2) {
												start_time = start_year + "-" + start_month;
											}
											// start_time = start_year +
											// start_month;
											if (null != start_time && !"".equals(start_time)) {
												project.put("startTime", start_time);
											} else {
												project.put("startTime", "");
											}

											String end_month = projectsMap.get("end_month").toString();
											String end_year = projectsMap.get("end_year").toString();
											String end_time = null;
											if (end_month.length() == 1) {
												end_time = end_year + "-0" + end_month;
											} else if (end_month.length() == 2) {
												end_time = end_year + "-" + end_month;
											}
											if (null != end_time && !"".equals(end_time)) {
												project.put("endTime", end_time);
											} else {
												project.put("endTime", "");
											}

											String name = projectsMap.get("name").toString();
											if (null != name && !"".equals(name)) {
												project.put("name", name);
											} else {
												project.put("name", "");
											}

											String desc = projectsMap.get("desc").toString();
											if (null != desc && !"".equals(desc)) {
												project.put("desc", desc);
											} else {
												project.put("desc", "");
											}

											// connect =
											// DriverManager.getConnection(
											// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
											try {
												Date date = new Date();
												DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
												String time = format.format(date);
												PreparedStatement statement = connect.prepareStatement(
														"INSERT INTO cv_project VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
														PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
												// 17行
												statement.setString(1, null);
												statement.setString(2, null);
												statement.setString(3, time);
												statement.setInt(4, candidateId);
												statement.setString(5, null);
												statement.setString(6, project.get("startTime").toString());
												statement.setString(7, project.get("endTime").toString());
												statement.setString(8, project.get("name").toString());
												statement.setString(9, null);
												if (project.get("desc").toString().length() > 1000) {
													statement.setString(10,
															project.get("desc").toString().substring(0, 1000));
												} else {
													statement.setString(10, project.get("desc").toString());
												}
												statement.setString(11, null);
												statement.setString(12, null);
												statement.setString(13, null);
												statement.setString(14, null);
												statement.setString(15, null);
												if (project.get("desc").toString().length() > 1000) {
													statement.setString(16,
															project.get("desc").toString().substring(0, 1000));
												} else {
													statement.setString(16, project.get("desc").toString());
												}
												statement.setInt(17, 1);
												statement.executeUpdate();
											} catch (SQLException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
										projects_list.add(project);
									}
									cvMap.put("projects_list", projects_list);
								} else {
									cvMap.put("projects_list", projects_list);
								}
							}

							// contact
							if (cv_parseMap.containsKey("contact")) {
								String contact = cv_parseMap.get("contact").toString();
								Map<String, Object> contactMap = new HashMap<String, Object>();
								contactMap = parseJSON2Map(contact);
								if (contactMap.size() > 0) {
									String mobile = contactMap.get("mobile").toString();
									if (null != mobile && !"".equals(mobile)) {
										cvMap.put("mobile", mobile);
									} else {
										cvMap.put("mobile", "");
									}
									String qq = contactMap.get("qq").toString();
									if (null != qq && !"".equals(qq)) {
										cvMap.put("qq", qq);
									} else {
										cvMap.put("qq", "");
									}
									String email = contactMap.get("email").toString();
									if (null != email && !"".equals(email)) {
										cvMap.put("email", email);
									} else {
										cvMap.put("email", "");
									}
								}
							}

							// skill
							if (cv_parseMap.containsKey("skills")) {
								String skill_description = "";
								String skill_name = "";
								int skill_time = 0;
								String skills = cv_parseMap.get("skills").toString();
								String skillTemp = "";
								// System.out.println("skills = " + skills);
								Map<String, Object> skillsMap = new HashMap<String, Object>();
								/*
								 * Pattern pattern1 = Pattern.compile("\\\\n");
								 * Matcher matcher1 = pattern1.matcher(skills);
								 * if(matcher1.find()) { skills =
								 * matcher1.replaceAll(""); }
								 */
								// System.out.println("skills = " + skills);
								// skillsMap = parseJSON2Map(skills);
								skills = skills.replaceFirst("\\{", "");
								skills = skills.replaceFirst("\"", "");
								skills = skills.replaceFirst("skills", "");
								skills = skills.replaceFirst("\"", "");
								skills = skills.replaceFirst(":", "");
								skills = skills.replaceFirst("\\[", "");
								skills = skills.replaceFirst("\"", "");
								// System.out.println("skills = " + skills);
								int indexTemp = skills.indexOf("]");
								// System.out.println("indexTemp = " +
								// indexTemp);
								if (indexTemp >= 0)
									skills = skills.substring(0, indexTemp);
								skills = skills.replaceAll("\"", "");
								// System.out.println("skills = " + skills);
								String[] stringArrays = skills.split("\\\\n");
								// System.out.println(stringArrays[0]);

								skills = skills.replaceAll("\\\\n", "");
								List<Integer> iList = new ArrayList<Integer>();
								List<String> sList = new ArrayList<String>();
								// 解析IT技能
								Pattern pattern = Pattern.compile("月");
								Matcher matcher = pattern.matcher(skills);
								while (matcher.find()) {
									String str = matcher.group();
									sList.add(str);
									iList.add(matcher.end());
								}
								for (int i = 0, len = iList.size(); i < len; i++) {
									if (i == 0) {
										// System.out.println(skills.substring(0,
										// iList.get(i)));
										String tempString = skills.substring(0, iList.get(i));
										List<Integer> iListtempstart = new ArrayList<Integer>();
										List<Integer> iListtempend = new ArrayList<Integer>();
										List<String> sListtemp = new ArrayList<String>();
										// 解析IT技能
										Pattern pattern1 = Pattern.compile("精通|熟悉|了解|熟练|一般|良好");
										Matcher matcher1 = pattern1.matcher(tempString);
										while (matcher1.find()) {
											String str = matcher1.group();
											skill_description = str;
											sListtemp.add(str);
											iListtempstart.add(matcher1.start());
											iListtempend.add(matcher1.end());
										}
										skill_name = tempString.substring(0, iListtempstart.get(0));
										skill_name = skill_name.replaceAll("-", "");
										Pattern pattern2 = Pattern.compile("\\d{1,3}");
										Matcher matcher2 = pattern2.matcher(
												tempString.substring(iListtempend.get(0), tempString.length() - 1));
										while (matcher2.find()) {
											skill_time = Integer.parseInt(matcher2.group());
										}
										// System.out.println("skill_name = " +
										// skill_name);
										// System.out.println("skill_description
										// = " + skill_description);
										// System.out.println("skill_time = " +
										// skill_time);
										// connect =
										// DriverManager.getConnection(
										// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
										try {
											Date date = new Date();
											DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											String time = format.format(date);
											PreparedStatement statement = connect.prepareStatement(
													"INSERT INTO cv_skill VALUES(?,?,?,?,?,?,?,?,?,?,?)",
													PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
											// 17行
											statement.setString(1, null);
											statement.setString(2, null);
											statement.setString(3, time);
											statement.setInt(4, candidateId);
											statement.setString(5, null);
											statement.setString(6, skill_name);
											statement.setString(7, null);
											statement.setString(8, null);
											statement.setString(9, skill_description);
											statement.setInt(10, 1);
											statement.setInt(11, skill_time);
											statement.executeUpdate();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {
										// System.out.println(skills.substring(iList.get(i-1),
										// iList.get(i)));
										String tempString = skills.substring(iList.get(i - 1), iList.get(i));
										List<Integer> iListtempstart = new ArrayList<Integer>();
										List<Integer> iListtempend = new ArrayList<Integer>();
										List<String> sListtemp = new ArrayList<String>();
										// 解析IT技能
										Pattern pattern1 = Pattern.compile("精通|熟悉|了解|熟练|一般|良好");
										Matcher matcher1 = pattern1.matcher(tempString);
										while (matcher1.find()) {
											String str = matcher1.group();
											skill_description = str;
											sListtemp.add(str);
											iListtempstart.add(matcher1.start());
											iListtempend.add(matcher1.end());
										}
										skill_name = tempString.substring(0, iListtempstart.get(0));
										skill_name = skill_name.replaceAll("-", "");
										Pattern pattern2 = Pattern.compile("\\d{1,3}");
										Matcher matcher2 = pattern2.matcher(
												tempString.substring(iListtempend.get(0), tempString.length() - 1));
										while (matcher2.find()) {
											skill_time = Integer.parseInt(matcher2.group());
										}
										// System.out.println("skill_name = " +
										// skill_name);
										// System.out.println("skill_description
										// = " + skill_description);
										// System.out.println("skill_time = " +
										// skill_time);
										// connect =
										// DriverManager.getConnection(
										// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
										try {
											Date date = new Date();
											DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											String time = format.format(date);
											PreparedStatement statement = connect.prepareStatement(
													"INSERT INTO cv_skill VALUES(?,?,?,?,?,?,?,?,?,?,?)",
													PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
											// 17行
											statement.setString(1, null);
											statement.setString(2, null);
											statement.setString(3, time);
											statement.setInt(4, candidateId);
											statement.setString(5, null);
											statement.setString(6, skill_name);
											statement.setString(7, null);
											statement.setString(8, null);
											statement.setString(9, skill_description);
											statement.setInt(10, 1);
											statement.setInt(11, skill_time);
											statement.executeUpdate();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}

						}
						// connect = DriverManager.getConnection(
						// "jdbc:mysql://123.57.35.10:3306/hunter2","root","hunter");
						Date date = new Date();
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = format.format(date);
						PreparedStatement statement = connect.prepareStatement(
								"INSERT INTO cv_basic VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS);// ְλ
						// 55行
						statement.setString(1, null);
						statement.setInt(2, candidateId);
						statement.setString(3, null);
						statement.setString(4, time);
						statement.setString(5, null);
						statement.setString(6, cvMap.get("name").toString());
						statement.setString(7, null);
						statement.setString(8, null);
						if (cvMap.get("gender").toString() == null || cvMap.get("gender").toString().equals("")
								|| cvMap.get("gender").toString().equals("保密"))
							statement.setString(9, null);
						else
							statement.setInt(9, cvMap.get("gender").toString().equals("男") ? 1 : 2);
						statement.setString(10, null);
						statement.setString(11, cvMap.get("id_number").toString());
						statement.setString(12, company_temp);
						statement.setString(13, title_temp);
						statement.setString(14, cvMap.get("city").toString());
						statement.setString(15, null);
						statement.setString(16, null);
						statement.setString(17, cvMap.get("mobile").toString());
						statement.setString(18, cvMap.get("email").toString());
						statement.setString(19, null);
						statement.setString(20, cvMap.get("birthyear").toString());
						statement.setString(21, null);
						statement.setString(22, major_temp);
						statement.setString(23, degree_temp);
						statement.setString(24, school_temp);
						// statement.setString(25,cvMap.get("work_experience").toString());
						if (work_experience_int < 1) {//年
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
						statement.setString(26, null);
						statement.setString(27, null);
						statement.setString(28, null);
						statement.setString(29, null);
						statement.setString(30, null);
						statement.setString(31, null);
						statement.setString(32, null);
						// statement.setString(33,cvMap.get("status_msg").toString());
						if (cvMap.get("status_code") != null) {//
							if (Integer.parseInt(cvMap.get("status_code").toString()) == 1
									|| Integer.parseInt(cvMap.get("status_code").toString()) == 4
									|| Integer.parseInt(cvMap.get("status_code").toString()) == 5) {
								statement.setInt(33, 65);
							} else if (Integer.parseInt(cvMap.get("status_code").toString()) == 2) {
								statement.setInt(33, 13);
							} else if (Integer.parseInt(cvMap.get("status_code").toString()) == 3) {
								statement.setInt(33, 64);
							} else {
								statement.setString(33, null);
							}
						} else {
							statement.setString(33, null);
						}

						if (cvMap.get("current_salary") == null || cvMap.get("current_salary").toString().equals("")) {
							statement.setString(34, null);
						} else {
							statement.setString(34, cvMap.get("current_salary").toString());
						}
						statement.setInt(35, expected_salary); 
						statement.setString(36, list_citys.toString()); 
						if (cvMap.get("expect_titles") == null || cvMap.get("expect_titles").toString().equals("")) {
							statement.setString(37, null);
						} else {
							if (cvMap.get("expect_titles").toString().length() > 128)
								statement.setString(37, cvMap.get("expect_titles").toString().substring(0, 124));
							else
								statement.setString(37, cvMap.get("expect_titles").toString());
						}
						statement.setString(38, null);
						statement.setString(39, cvMap.get("qq").toString());
						statement.setString(40, null);
						statement.setString(41, null);
						statement.setString(42, null);
						statement.setString(43, null);
						statement.setString(44, null);
						statement.setString(45, null);
						statement.setString(46, null);
						statement.setString(47, null);
						statement.setString(48, null);
						statement.setString(49, null);
						if (cvMap.get("self_evaluate").toString().length() > 1024)
							statement.setString(50, cvMap.get("self_evaluate").toString().substring(0, 1000));
						else
							statement.setString(50, cvMap.get("self_evaluate").toString());
						// statement.setString(50,cvMap.get("self_evaluate").toString());
						statement.setString(51, null);
						statement.setInt(52, 1);
						statement.setString(53, null);
						statement.setString(54, null);
						statement.setInt(55, 1);
						statement.setInt(56, work_experience_int); 
						statement.executeUpdate();
					}
					// WordGenerator.createDoc(cvMap, "resume", "F:\\");
				} catch (Exception e) {
					// e.printStackTrace();
					continue;
				}

			}
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * ������Ҫת�����ַ���
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
//		// ����һ��Map
		Map<String, Object> map = new HashMap<String, Object>();
//		// ��������ַ���ת��ΪJSON
//		JSONObject json = JSONObject.fromObject(jsonStr);
//		// ����JSON�е�����
//		for (Object k : json.keySet()) {
//			// ��ȡ��K����Ӧ��value��ֵ
//			Object v = json.get(k);
//			if (v instanceof JSONArray) {
//				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//				Iterator<JSONObject> it = ((JSONArray) v).iterator();
//				while (it.hasNext()) {
//					JSONObject json2 = it.next();
//					list.add(parseJSON2Map(json2.toString()));
//				}
//				map.put(k.toString(), list);
//			} else {
//				// System.out.println("撒旦撒大声大声道"+v.toString());
//				map.put(k.toString(), v.toString());
//			}
//			// ������ֵ��JSONArray�Ļ�
//			map.put(k.toString(), v);
//		}
		return map;
	}

	public static File[] getFile(String path) {
		File file = new File(path);
		File[] tempList = file.listFiles();
		return tempList;
	}

	public static void main(String[] args) {
		File[] files = getFile("/data/cvs");
		for (int i = 0; i < files.length; i++) {
			System.out.println("处理第" + i + "个文件");
			// if(i >= 437)
			readFile(files[i]);
		}
	}

}
