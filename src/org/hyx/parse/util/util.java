package org.hyx.parse.util;

public class util {

	public static int category(String cate) {
		if (cate.indexOf("求职") != -1) {
			return 0;
		} else if (cate.indexOf("自我") != -1) {
			return 1;
		} else if (cate.indexOf("工作") != -1) {
			return 2;
		} else if (cate.indexOf("证书") != -1) {
			return 3;
		} else if (cate.indexOf("项目") != -1) {
			return 4;
		} else if (cate.indexOf("教育") != -1) {
			return 5;
		} else if (cate.indexOf("语言") != -1) {
			return 6;
		} else if (cate.indexOf("求职信") != -1) {
			return 7;
		} else if (cate.indexOf("技能") != -1) {
			return 8;
		} else if (cate.indexOf("获得荣誉") != -1 || cate.indexOf("奖项") != -1) {
			return 9;
		} else if (cate.indexOf("在校学习情况") != -1) {
			return 10;
		} else if (cate.indexOf("培训") != -1) {
			return 12;
		} else if (cate.indexOf("附加信息") != -1) {
			return 13;
		} else if (cate.indexOf("学生实践经验") != -1) {
			return 14;
		} else if (cate.indexOf("校内职务") != -1) {
			return 15;
		}
		return 20;
	}

	/*
	 * 头部栏目有：--在WModel4Parse中直接处理
	 * 简历关键字
	 * 基本信息（通过居住地识别）
	 * 最近工作
	 * 自我评价
	 * 求职意向
	 * 工作经验
	 * 
	 * 处理最后table中的栏目分别有： 
	 * 工作经验 
	 * 项目经验 
	 * 教育经历 
	 * 所获奖项 
	 * 培训经历 
	 * 社会经验 
	 * 语言能力 
	 * 技能
	 * 证书
	 * 其他信息 
	 * 附件
	 * 
	 */
	public static int category4(String cate) {
		if (cate.contains("工作")) {
			return 0;
		} else if (cate.contains("项目")) {
			return 1;
		} else if (cate.contains("教育")) { 
			return 2;
		} else if (cate.contains("奖项")) {
			return 3;
		} else if (cate.contains("培训")) {
			return 4;
		} else if (cate.contains("社会")) {
			return 5;
		} else if (cate.contains("语言")) {
			return 6;
		} else if (cate.contains("技能")) {
			return 7;
		} else if (cate.contains("证书")) {
			return 8;
		} else if (cate.contains("其他")) {
			return 9;
		} else if (cate.contains("附件")) {
			return 10;
		}
		else if (cate.contains("校内")) {
			return 11;
		}

		return 14;// 未找到匹配
	}
}
