package org.hyx.parse.parse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
	static int x = 0, y = 0, z = 0, sum = 1;
	static String toPath1;
	static String toPath2;
	static String toPath3;

	private static Document doc;
	private static Elements eles, eles2,eles3;
	static int count = 0;
	/**
	 * 读取某个文件夹下的所有文件
	 */
	public static boolean readfile(String filepath) throws FileNotFoundException, IOException ,Exception{
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
					if (!readfile.isDirectory()) {
						// 去除后缀的文件名
						String filename = readfile.getName().substring(0, readfile.getName().lastIndexOf("."));

						doc = Jsoup.parse(readfile, "utf-8");
						eles = doc.select("html");
						eles2 = doc.select("html>body>table[width=600]table[align=CENTER]");
						eles3 = doc.select("tr>td>table[width=97%]");

						// 送入解析器
						count++;//计数器
						if (eles.attr("xmlns").equals("http://www.w3.org/1999/xhtml")) { // QModelParse
																							// 前程无忧
							// System.out.println(filename);
							// System.exit(0);
							System.out.println("QModelParse");			
							QModelParse.parse(readfile.getAbsolutePath());

						} else if (!eles.attr("lang").equals("")) {// ZModel2Parse
							System.out.println("ZModel2Parse");						// 智联
							ZModel2Parse.parse(readfile.getAbsolutePath()); 
							
						} else if (eles2.size()!=0) { // ZModel3Parse
							System.out.println("ZModel3Parse");
							ZModel3Parse.parse(readfile.getAbsolutePath());
							 
						} else if (eles3.size()!=0) { // WModel4Parse
							System.out.println("WModel4Parse");									// 无忧
							WModel4Parse.parse(readfile.getAbsolutePath());
//							System.out.println(filename);
//							System.exit(0);
						} else {
							 
								System.out.println("文件暂时未能解析！");
								BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/gongcaichun/Desktop/UnReadFiles.txt",true));
								bw.write(readfile.getAbsolutePath() + "\n");
								bw.flush(); // 把缓存区内容压入文件
								//bw.close(); // 关闭文件
							 

							// 将不能处理的文件路径写入UnReadFiles.txt
						}

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

	public static void main(String[] args) {//ArrayIndexOutOfBoundsException
		try {//C:\Users\gongcaichun\Desktop\模板\智联招聘       C:\\Users\\gongcaichun\\Desktop\\模板      H:\\猎头需要软件\\Html\\前程无忧\\0
			readfile("C:\\mht2html\\前程无忧\\0");
			
			// deletefile("D:/file");
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}catch (Exception ex) {
		}  //ArrayIndexOutOfBoundsException
		System.out.println("ok");
		System.out.println(count); 
	}

}
