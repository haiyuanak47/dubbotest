package com.lhy.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Exceptions;

public class HttpUtils {
	private final static Logger log = LoggerFactory.getLogger(HttpUtils.class);
	
	public static String postURL(String requestUrl,String params){
		String result = null;
		try
		{
			URL url = new URL(requestUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			
			httpURLConnection.setRequestMethod("POST");// 提交模式
			// conn.setConnectTimeout(10000);//连接超时 单位毫秒
			// conn.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			if(params!=null){
				// 获取URLConnection对象对应的输出流
				printWriter.write(params);
			}
			// flush输出流的缓冲
			//printWriter.flush();
			printWriter.close();
			int responseCode = httpURLConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				InputStream inputStream = httpURLConnection.getInputStream();
				result = IOUtils.toString(inputStream, "UTF-8");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *
	 * @param requestUrl
	 * @param params
	 * @param destination 写入本地目录
	 * @return
	 */
	public static boolean postURL(String requestUrl,String params,String destination){
		try
		{
			URL url = new URL(requestUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

			httpURLConnection.setRequestMethod("POST");// 提交模式
			// conn.setConnectTimeout(10000);//连接超时 单位毫秒
			// conn.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			if(params!=null){
				// 获取URLConnection对象对应的输出流
				printWriter.write(params);
			}
			// flush输出流的缓冲
			//printWriter.flush();
			printWriter.close();
			int responseCode = httpURLConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				InputStream input = httpURLConnection.getInputStream();
				int index;
				byte[] bytes = new byte[1024];
				FileOutputStream downloadFile = new FileOutputStream(destination);
				while ((index = input.read(bytes)) != -1) {
					downloadFile.write(bytes, 0, index);
					downloadFile.flush();
				}
				input.close();
				downloadFile.close();
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将InputStream写入本地文件
	 * @param destination 写入本地目录
	 * @param input 输入流
	 * @throws IOException IOException
	 */
	public static void writeToLocal(String destination, InputStream input)
			throws IOException {
		int index;
		byte[] bytes = new byte[1024];
		FileOutputStream downloadFile = new FileOutputStream(destination);
		while ((index = input.read(bytes)) != -1) {
			downloadFile.write(bytes, 0, index);
			downloadFile.flush();
		}
		input.close();
		downloadFile.close();

	}

	public static String getURL(String requestUrl){
		String result = null;
		try
		{
			URL url = new URL(requestUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			
			httpURLConnection.setRequestMethod("GET");// 提交模式
			//连接
			httpURLConnection.connect();
			
			int responseCode = httpURLConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				InputStream inputStream = httpURLConnection.getInputStream();
				result = IOUtils.toString(inputStream, "UTF-8");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static boolean downLoadFile(String downUrl,String outFile){
		boolean re = true;
		boolean reDown = false;
		try{
			downFile(downUrl,outFile);
		}catch(Exception e){
			//e.printStackTrace();
			reDown = true;
		}
		if(reDown){
			try{
				downFile(downUrl,outFile);
			}catch(Exception e){
				log.error("下载文件失败！"+downUrl);
				e.printStackTrace();
				re =false;
			}
		}
		return re;
	}

	public static void downFile(String downUrl,String outFile)throws Exception {
		BufferedInputStream bis =null;
		BufferedOutputStream bos=null;
		int contentLength = 0;
		try {
			URL url = new URL(downUrl);
			HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setConnectTimeout(3*1000);//连接超时 单位毫秒
			connection.setReadTimeout(20*1000);//传递数据,读取超时 单位毫秒
			connection.connect();

			contentLength = connection.getContentLength();
			//System.out.println("文件的大小是:"+contentLength);
			if (contentLength>32) {
				InputStream is= connection.getInputStream();
				bis = new BufferedInputStream(is);
				FileOutputStream fos = new FileOutputStream(outFile);
				bos= new BufferedOutputStream(fos);
				int b = 0;
				byte[] byArr = new byte[1024];
				while((b= bis.read(byArr))!=-1){
					bos.write(byArr, 0, b);
				}
				//System.out.println("下载的文件的大小是----------------------------------------------:"+contentLength);
			}

		} catch (Exception e) {
			//log.error("下载文件失败！"+downUrl);
			//e.printStackTrace();
			throw new Exception("下载文件失败！文件的大小:"+contentLength+" "+downUrl,e);
		}finally{
			try {
				if(bis !=null){
					bis.close();
				}
				if(bos !=null){
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		/*String url = "http://127.0.0.1:8080/yi-salesman-home/a/wxtemplatemsg/SmallVote";
		String re = HttpUtils.postURL(url,null);
		System.out.println(re);*/

		String downUrl = "http://img.jx885.com/lrjk/content/audio/vixf/km1/7850.mp3";
        HttpUtils.downLoadFile(downUrl,"I:/questionbank/aduio/7850.mp3");
	}
	
}
