package org.hyx.parse.parse;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hyx.parse.model.Basic;
import org.hyx.parse.model.Basic2;
import org.hyx.parse.model.Education;
import org.hyx.parse.model.Honor;
import org.hyx.parse.model.Job;
import org.hyx.parse.model.Credential;
import org.hyx.parse.model.Medal;
import org.hyx.parse.model.Project;
import org.hyx.parse.model.Skill;
import org.hyx.parse.util.Verificationcodestart;
import org.hyx.parse.util.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZModel2Parse {

	private static String name;// 姓名
	private static String gender;// 性别
	private static String company;// 公司
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
	private static String photoPath;//照片保存路径

	private static String expectnaturework;// 期望工作性质
	private static String expectindustry;// 期望从事行业
	private static String currentSalary;// 目前薪资
	private static String expectedCity;// 期望城市
	private static String expectedJob;// 期望职位
	private static String jobStatus;// 工作状态
	private static String expectedSalary;// 期望薪资

	private static String selfassessment;// 自我评价
	private static String politics;// 政治面貌

	private static String startTime;// 起始时间
	private static String endTime;// 结束时间
	private static String position;// 职位
	private static String salary;// 薪资
	private static String jobInfo;// 工作描述信息（其它关于工作描述的信息）

	private static String certificateTime;// 证书获得时间
	private static String certificate;// 证书名称
	
	private static String projectName;// 项目名称
	private static String content;//项目内容
	
	private static String medalName;//奖项名称
	private static String medalInfo;//奖项信息
	
	private static String skillName;//技能名称
	private static String skillInfo;//技能熟练程度
	private static String silltime;//使用时间
	
	private static String honorName;//奖项名称
	private String honorInfo;//奖项信息

	private static Map<String, Job> jobMap = new LinkedHashMap<String, Job>();
	private static Map<String, Credential> certificateMap = new LinkedHashMap<String, Credential>();
	private static Map<String, Education> educationMap = new LinkedHashMap<String, Education>();
	private static Map<String, Project> projectmap = new LinkedHashMap<String, Project>();
	private static Map<String, Medal> medalMap = new LinkedHashMap<String, Medal>();
	private static Map<String, Skill> skillMap = new LinkedHashMap<String, Skill>();
	private static Map<String, Honor> honorMap = new LinkedHashMap<String, Honor>();
	
	private static Job jobModel;
	private static Education educationModel;
	private static Project projectModel;
	private static Basic2 basicModel2 = new Basic2();
	private static Credential certificateModel;//证书
	private static Medal medalModel;//学校获奖情况
	private static Skill skillModel;//技能情况
	private static Honor honorModel;//获得荣誉
	private static util utilClass;
	private static int i = 0;
	private static int j = 0;
	private static Elements eles;
	private static String eletext;
	private static Document doc;
	private static String errorPhotoURL = "http://rd.zhaopin.com/img/lookResumes.jpg";

	public static void parse(String path) {
		try {
			System.out.println(path);
			File input = new File(path);
			doc = Jsoup.parse(input, "UTF-8");
			/* System.out.println(doc); */

			
			// 基本信息获取
			String name = doc.select("div.main-title-fl").text();
			String photoURL = doc.select("div.summary >a>img").attr("abs:src");
			if(photoURL.equals(errorPhotoURL)){
				System.out.println("-------------------wrong photo ----------------------");
			}else{
				photoPath = Verificationcodestart.getImg(photoURL);
			}
			
			
			
			eles = doc.select("div.summary-top>span");
			String strtext = eles.toString();
			Pattern p = Pattern.compile("&nbsp;+");
			Matcher m = p.matcher(strtext);
			strtext = m.replaceAll(" ");
			strtext = strtext.replaceAll("\\s+", ";");
			strtext = strtext.replaceAll("<span>", "");
			strtext = strtext.replaceAll("</span>", "");
			String[] strArray = strtext.split(";");
			for (i = 0; i < strArray.length; i++) {
				if (i == 0) {
					gender = strArray[i];
				} else if (i == 1) {
					String agetext = strArray[i];
					agetext = agetext.replaceAll("\\(", ";");
					agetext = agetext.replaceAll("\\)", "");
					String[] agetexts = agetext.split(";");
					for (int j = 0; j < agetexts.length; j++) {
						if (j == 0) {
							age = agetexts[j];
						} else if (j == 1) {
							birthday = agetexts[j];
						}
					}
				} else if (i == 2) {
					workingyear = strArray[i];
				} else if (i == 3) {
					education = strArray[i];
				} else if (i == 4) {
					maritalstatus = strArray[i];
				}
			}
			eles = doc.select("div.summary-top");
			String stringele = eles.text();
			stringele = stringele.replaceAll("：", ";");
			stringele = stringele.replaceAll(" | ", ";");
			
			String[] eletexts = stringele.split(";");
			for (int i = 0; i < eletexts.length; i++) {
				if (i == 2 || i == 3) {
					if (latteraddress != null) {
						latteraddress = latteraddress + eletexts[i];
					} else {
						latteraddress = eletexts[i];
					}
				} else if (i == 6) {
					account = eletexts[i];
				} else if (i == 8) {
					politics = eletexts[i];
				}
			}
			eles = doc.select("div.feedbackD >div >span >em");
			phone = eles.select("b").text();
			email = eles.select("i").text();

			eles = doc.select("div.resume-preview-all");
			for (Element ele : eles) {
				String cate = ele.select("h3").text();
				System.out.println("cate===="+cate);
				int cateInt = utilClass.category(cate);
				switch (cateInt) {

				case 0:
					jobIntension();//求职意向
					break;
				case 1:
					selfAss();//自我评价
					break;
				case 2:
					jobExperience();//工作经历
					break;
				case 3:
					certificate(ele);//证书
					break;
				case 4:
					projectExperience(ele);//项目经历 未完成
					break;
				case 5:
					educationContent(ele);//教育经历
					break;
				case 6:
					language();//语言能力
					break;
				case 7:
					//求职信 暂未发现
					break;
				case 8:
					proSkill(ele);//专业技能
					break;
				case 9:
					gainHonner(ele);//获得荣誉
					break;
				case 10:
					schoolStudy(ele);//在校学习情况
					break;

				}
			}

			basicModel2.setName(name);
			basicModel2.setPhotoPath(photoPath);
			basicModel2.setLatteraddress(latteraddress);
			basicModel2.setAccount(account);
			basicModel2.setPolitics(politics);
			basicModel2.setPhone(phone);
			basicModel2.setEmail(email);
			basicModel2.setLanguage(language);
			basicModel2.setMaritalstatus(maritalstatus);
			basicModel2.setAge(age);
			basicModel2.setBirth(birthday);
			basicModel2.setWorkingyear(workingyear);
			basicModel2.setEducation(education);
			basicModel2.setGender(gender);
			/* basicModel.set */

			System.out.println(basicModel2.toString());
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
			for (Entry<String, Job> entry : jobMap.entrySet()) {
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
			for (Entry<String, Medal> entry : medalMap.entrySet()) {
				System.out.println(entry.getKey() + ":"
						+ entry.getValue().toString());
			}
			
			// 第三种：推荐，尤其是容量大时
			System.out.println("通过Map.entrySet遍历key和value");
			for (Entry<String, Skill> entry : skillMap.entrySet()) {
				System.out.println(entry.getKey() + ":"
						+ entry.getValue().toString());
			}
			
			System.out.println("通过Map.entrySet遍历key和value");
			for (Entry<String, Honor> entry : honorMap.entrySet()) {
				System.out.println(entry.getKey() + ":"
						+ entry.getValue().toString());
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 语言能力
	 */
	public static void language() {
		// 语言能力
		language = doc.select("div.resume-preview-line-height").text();
	}

	/**
	 * 求职意向
	 */
	public static void jobIntension() {
		// 求职意向信息
		eles = doc.select("div.resume-preview-top>table>tbody>tr>td");
		i = 0;
		for (Element ele : eles) {
			if (i == 1) {
				expectedCity = ele.text();
				basicModel2.setExpectedCity(expectedCity);
			} else if (i == 3) {
				expectedSalary = ele.text();
				basicModel2.setExpectedSalary(expectedSalary);
			} else if (i == 5) {
				jobStatus = ele.text();
				basicModel2.setJobStatus(jobStatus);
			} else if (i == 7) {
				expectnaturework = ele.text();
				basicModel2.setExpectnaturework(expectnaturework);
			} else if (i == 9) {
				expectedJob = ele.text();
				basicModel2.setExpectedJob(expectedJob);
			} else if (i == 11) {
				expectindustry = ele.text();
				basicModel2.setExpectindustry(expectindustry);
			}
			i++;
		}
	}

	/**
	 * 自我评价
	 */
	public static void selfAss() {
		// 自我评价
		eles = doc.select("div.rd-break");
		selfassessment = eles.text();
		basicModel2.setSelfassessment(selfassessment);
	}

	/**
	 * 工作经历
	 */
	public static void jobExperience() {
		// 工作经历信息
		eles = doc.select("div.workExperience");
		Elements elesworks = eles.select("h2");
		i = 0;
		for (Element elework : elesworks) {
			jobModel = new Job();
			String[] eleworktexts = elework.text().split("  ");
			for (j = 0; j < eleworktexts.length; j++) {
				if (j % 3 == 0) {
					String[] startendtext = eleworktexts[j].split(" - ");
					startTime = startendtext[0];
					endTime = startendtext[1];
					jobModel.setStartTime(startTime);
					jobModel.setEndTime(endTime);
				}
				if (j == 1) {
					company = eleworktexts[j];
					jobModel.setCompany(company);
				}
			}
			String key = "job" + i;
			jobMap.put(key, jobModel);
			i++;
		}

		Elements elespositions = eles.select("h5");
		i = 0;
		for (Element elesposition : elespositions) {
			String key = "job" + i;
			jobModel = jobMap.get(key);
			String[] elespositiontexts = elesposition.text().split(" | ");
			for (j = 0; j < elespositiontexts.length; j++) {
				if (j == 0) {
					position = elespositiontexts[j];
					jobModel.setPosition(position);
				} else if (j == 2) {
					salary = elespositiontexts[j];
					jobModel.setSalary(salary);
				}
			}
			jobMap.put(key, jobModel);
			i++;
		}

		Elements jobInfoeles = eles
				.select("div.resume-preview-dl>table>tbody>tr");
		/*System.out.println(jobInfoeles.text());*/
		i = 0;
		for (Element jobInfoele : jobInfoeles) {
			String key = "job" + i;
			jobModel = jobMap.get(key);
			String[] jobInfoele1texts = jobInfoele.text().split("：");
			for(j = 1;j<jobInfoele1texts.length;j++){
				if(jobInfo != null){
					jobInfo +=jobInfoele1texts[j];
				}else{
					jobInfo = jobInfoele1texts[j];
				}
			}
			
			jobModel.setJobInfo(jobInfo);
			jobMap.put(key, jobModel);
			i++;
		}

	}

	/**
	 *教育信息 
	 */
	public static void educationContent(Element ele) {
		// 教育信息
		eles = ele.select("div.resume-preview-dl ");
		String toString = eles.toString().replaceAll("<br>", ";");
		Element ele2 = Jsoup.parse(toString).body();
		String educationContentTexts = ele2.text();
		String[] educationContentTextsss = educationContentTexts.split(";");
		for(i = 0;i< educationContentTextsss.length;i++){
			String[] educationContentText = educationContentTextsss[i].split("  ");
			String key = "educationContent"+i;
			educationModel = new Education();
			for (j = 0; j< educationContentText.length; j++) {
				if (j == 0) {
					String startendTexts = educationContentText[j];
					String[] startendText = startendTexts.split(" - ");
					startTime = startendText[0];
					endTime = startendText[1];
					educationModel.setStartTime(startTime);
					educationModel.setEndTime(endTime);
				}
				if (j == 1) {

					school = educationContentText[j];
					educationModel.setSchool(school);
				}
				if (j == 2) {
					major = educationContentText[j];
					educationModel.setMajor(major);
				}
				if (j == 3) {
					degree = educationContentText[j];
					educationModel.setDegree(degree);
				}
				educationMap.put(key, educationModel);
			}
			
		}
	}

	/**
	 * 证书信息
	 */
	public static void certificate(Element ele) {
		// 证书信息
		eles = doc.select("div.resume-preview-all");
		doc.select("div.workExperience").remove();
		eles = ele.select("div.resume-preview-all>h2");
		i = 0;
		for (Element ele2 : eles) {
			String key = "Credential" + i;
			certificateModel = new Credential();
			String[] certificateModelText = ele2.text().split("  ");
			for (j = 0; j < certificateModelText.length; j++) {
				
				if (j == 0) {
					certificateTime = certificateModelText[j];
					certificateModel.setCertificateTime(certificateTime);
				} else if (j == 1) {
					certificate = certificateModelText[j];
					certificateModel.setCertificate(certificate);
				}
			}
			certificateMap.put(key, certificateModel);
			i++;
		}
	}

	/**
	 * 项目经历
	 */
	public static void projectExperience(Element ele){
		/*System.out.println(ele);*/
		Elements projectExperienceEles = ele.select("h2");
		i = 0;
		for(Element projectExperienceEle:projectExperienceEles){
			String key = "project"+i;
			projectModel = new Project();
			String projectExperienceEleString = projectExperienceEle.toString().replaceAll("&nbsp;+", ";");
			projectExperienceEleString = projectExperienceEleString.replaceAll(";+", ";");
			projectExperienceEleString = projectExperienceEleString.replaceAll(" - ", ":");
			
			String projectExperienceText = Jsoup.parse(projectExperienceEleString).body().text();
			
			String[] projectExperienceTexts = projectExperienceText.split(";");
			for(j = 0;j< projectExperienceTexts.length;j++){
				if(j == 0){
					String[] startendTiem = projectExperienceTexts[i].split(":");
					startTime = startendTiem[0];
					endTime = startendTiem[1];
					projectModel.setStartTime(startTime);
					projectModel.setEndTime(endTime);
					System.out.println("startTime:"+startTime);
					System.out.println("endTime:"+endTime);
				}else if(j ==1){
					projectName = projectExperienceTexts[j];
					projectModel.setProjectName(projectName);
					System.out.println("projectName:"+projectName);
				}
			}
			content = ele.select("div>table").text();
			projectModel.setContent(content);
		    System.out.println("content="+content);
			projectmap.put(key,projectModel);
			i++;
		}
		
	    
	    
		
	}
	
	/**
	 * 在校学习情况
	 * @param ele
	 */
	public static void schoolStudy(Element ele){
		
		ele.select("h2").prepend(";");//在div前添加html内容;
		ele.select("div").prepend("asd");//在div前添加html内容;
		ele.select("h3").remove();
		String winningTexts = ele.text();
		winningTexts = winningTexts.substring(5); 
		String[] winningText = winningTexts.split(";");
		for(i = 0;i<winningText.length;i++){
			String key = "medalModel"+i;
			medalModel = new Medal();
			String[] winningTexts2 =  winningText[i].split("asd");
			for(j =0 ;j<winningTexts2.length;j++){
				if(j == 0){
					medalName = winningTexts2[j];
				}else{
					if(medalInfo != null){
						medalInfo += ";"+winningTexts2[j];
					}else{
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
	 * @param ele
	 */
	public static void proSkill(Element ele){
		Elements preSkillEles = ele.select(">div");
		String preSkillElesASteinf = preSkillEles.toString();
		preSkillElesASteinf = preSkillElesASteinf.replaceAll("<br>", ";");
		preSkillElesASteinf = Jsoup.parse(preSkillElesASteinf).text();
		String[] preSkillElesASteinfTexts = preSkillElesASteinf.split(";");
		for(i = 0;i<preSkillElesASteinfTexts.length;i++){
			String key = "Skill"+i;
			skillModel = new Skill();
			String[] preSkillStrings = preSkillElesASteinfTexts[i].split("：");
			for(j = 0;j<preSkillStrings.length;j++){
				if(j == 0){
					skillName = preSkillStrings[j];
					skillModel.setSkillName(skillName);
				}else{
					String[] skillInftimeString =  preSkillStrings[j].replaceAll(" ", ";").split(";");
					for(int k=0;k<skillInftimeString.length;k++ ){
						if(k == 0 ){
							skillInfo = skillInftimeString[0];
							skillModel.setInfo(skillInfo);
						}else if(k == 2){
							silltime = skillInftimeString[2];
							skillModel.setTime(silltime);
						}	
					}
				}
			}
			skillMap.put(key, skillModel);
		}
	}

	/**
	 * 获得荣誉
	 * @param ele
	 */
	public static void gainHonner(Element ele){
		Elements honorEles = ele.select(">div");
		String[] honorTexts = honorEles.text().split(" ");
		for(i = 0;i < honorTexts.length;i++){
			String key = "honor"+i;
			honorModel = new Honor();
			honorModel.setHonorInfo(honorTexts[i]);
			honorMap.put(key, honorModel);
		}
	}
	
	public static void main(String[] args) {
		String path1 = "F:\\eclipseworkspace\\html样例\\html样例\\智联\\2747640218531737098407011570.html";
		String path = "F:\\eclipseworkspace\\html样例\\html样例\\智联\\2747877157331445100923972290.html";
		String path2 = "F:/eclipseworkspace/html样例/html样例/智联/2747877157331445100923972290.html";

		String path3 = "F:/简历分类/智联/全国简历3/7/岳昶欢.html";
		
		parse(path1);
		/*parse(path3);*/
		System.out.println("-----------------------------------------------------------------------------");
		/*parse(path1);*/
		/*System.out
				.println("-----------------------------------------------------------------------------");
		parse(path2); */
	}
}
