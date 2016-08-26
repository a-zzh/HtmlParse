//package org.hyx.parse.util;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLSession;
//
//import org.jsoup.Jsoup;
//import org.jsoup.Connection.Response;
//
//
//public class Verificationcodestart { 
//
//	
//	private static File file;
//	private static String pname = System.getProperty("user.dir");
//	private static File photoImgFile;
//	private static HostnameVerifier hv = new HostnameVerifier() {
//		public boolean verify(String urlHostName, SSLSession session) {
//			System.out.println("Warning: URL Host: " + urlHostName + " vs. "
//					+ session.getPeerHost());
//			return true;
//		}
//	};
//
//	/**
//	 * 根据获得的url下载图片
//	 * @param path 输入一个url 
//	 * @return 
//	 */
//	public static String getImg(String path) {
//		
//		try {
//			SslUtils.ignoreSsl();//忽略网页安全证
//			String pathname =path.substring(path.lastIndexOf("/")+1);
//			Response resultImageResponse = Jsoup.connect(path).ignoreContentType(true).timeout(3000).execute();
//			//将图片保存到项目的相对路径
//			/* String pname = getClass().getResource(".").getFile().toString();  */
//			/*System.out.println(pname);*/
//			file = new File(pname +"\\zhilianimg\\");
//			System.out.println(file);
//			if  (!file .exists()  && !file .isDirectory())      
//			{       
//			    System.out.println("//不存在");  
//			    file .mkdir();    
//			} else   
//			{  
//			    System.out.println("//目录存在");  
//			} 
//		     photoImgFile = new File(file+"\\"+pathname);
//			if(!photoImgFile.exists())    
//			{    
//			    try {    
//			    	photoImgFile.createNewFile();    
//			    } catch (IOException e) {    
//			        // TODO Auto-generated catch block    
//			        e.printStackTrace();    
//			    }    
//			}   
//			FileOutputStream out = (new FileOutputStream(photoImgFile));
//			out.write(resultImageResponse.bodyAsBytes());           
//			out.close();		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return photoImgFile+"";
//	}
//	
//	public static void main(String[] args){
//		String path = "http://rd.zhaopin.com/img/lookResumes.jpg";
//		getImg(path);
//	}
//
//}
