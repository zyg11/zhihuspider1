package zhihuspider1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static String SendGet(String url) {
		  // 定义一个字符串用来存储网页内容
		  String result = "";
		  // 定义一个缓冲字符输入流
		  BufferedReader in = null;
		  try {
		   // 将string转成url对象
		   URL realUrl = new URL(url);
		   // 初始化一个链接到那个url的连接
		   URLConnection connection = realUrl.openConnection();
		   // 开始实际的连接
		   connection.connect();
		   // 初始化 BufferedReader输入流来读取URL的响应
		   in = new BufferedReader(new InputStreamReader(
		     connection.getInputStream(),"UTF-8"));
		   // 用来临时存储抓取到的每一行的数据
		   String line;
		   while ((line = in.readLine()) != null) {
		    // 遍历抓取到的每一行并将其存储到result里面
		    result += line;
		   }
		  } catch (Exception e) {
		   System.out.println("发送GET请求出现异常！" + e);
		   e.printStackTrace();
		  }
		  // 使用finally来关闭输入流
		  finally {
		   try {
		    if (in != null) {
		     in.close();
		    }
		   } catch (Exception e2) {
		    e2.printStackTrace();
		   }
		  }
		  return result;
}
	static ArrayList<String> RegexString(String targetStr, String patternStr) {
		  // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		  // 相当于埋好了陷阱匹配的地方就会掉下去
		ArrayList<String>result=new ArrayList<String>();
		  Pattern pattern = Pattern.compile(patternStr);
		  // 定义一个matcher用来做匹配
		  Matcher matcher = pattern.matcher(targetStr);
		  // 如果找到了
		  
//		  if (matcher.find()) {
//		   // 打印出结果
//		   return matcher.group(1);
//		  }
		  boolean isFind=matcher.find();// 使用循环将句子里所有的kelvin找出并替换再将内容加到sb里
			  while (isFind) {
				//添加成功匹配到的结果
				  result.add(matcher.group(1));
				  //继续匹配查找下一个对象
				  isFind=matcher.find();
			}
			  return result;
		 }
		 public static void main(String[] args) {
		  // 定义即将访问的链接
		  String url = "https://www.zhihu.com/explore/recommendations";
		  // 访问链接并获取页面内容
		  String result = SendGet(url);
		  // 使用正则匹配图片的src内容  //question_link.+?href=\"(.+?)\"
		  ArrayList<String> imgSrc = RegexString(result, "question_link.+?>(.+?)<");//而其中能够和其他超链接区分开的，应该就是那个class了，也就是类选择器。question_link.+?href=\"(.+?)\"
		  // 打印结果
		  System.out.println(imgSrc);
		 }
		}
