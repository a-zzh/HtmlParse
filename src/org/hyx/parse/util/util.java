package org.hyx.parse.util;

public class util {

	public static int category(String cate){
		if(cate.indexOf("求职")!=-1){
			return 0;
		}else if(cate.indexOf("自我")!=-1){
			return 1;
		}else if(cate.indexOf("工作")!=-1){
			return 2;
		}else if(cate.indexOf("证书")!=-1){
			return 3;
		}else if(cate.indexOf("项目")!=-1){
			return 4;
		}else if(cate.indexOf("教育")!=-1){
			return 5;
		}else if(cate.indexOf("语言")!=-1){
			return 6;
		}else if(cate.indexOf("求职信")!=-1){
			return 7;
		}else if(cate.indexOf("技能")!=-1){
			return 8;
		}else if(cate.indexOf("获得荣誉")!=-1 || cate.indexOf("奖项")!=-1 ){
			return 9;
		}else if(cate.indexOf("在校学习情况")!=-1){
			return 10;
		}else if(cate.indexOf("培训")!=-1){
			return 12;
		}
		else if(cate.indexOf("附加信息")!=-1){
			return 13;
		}else if(cate.indexOf("学生实践经验")!=-1){
			return 14;
		}else if(cate.indexOf("校内职务")!=-1){
			return 15;
		}
		return 20;	
	}
}
