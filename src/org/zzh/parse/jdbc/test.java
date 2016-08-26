package org.zzh.parse.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class test {

	public static void main(String[] args) throws Exception {
		 String str = "\\sada\\\\\\\\\\sad||a????？？????";
		 System.out.println(str.replaceAll("[\\\\?？]", ""));

//		FileReader fr = new FileReader("C:/Users/gongcaichun/Desktop/database/zl/skill_test.txt");
//
//		BufferedReader br = new BufferedReader(fr);
//		String str = br.readLine();
//		while (str != null) {
//
//			System.out.println(str.replaceAll("[\\\\?]", ""));
//			str = br.readLine();
//
//		}

	}

}
