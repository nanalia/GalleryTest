package com.gallery.test.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InputStream;


public class LogcatThead implements Runnable{
	BufferedWriter bW = null;
	private String pathDir = "/mnt/sdcard/test";
	private String path1 = "";
	private String path2 = "";
	@Override
	public void run() {
		path1 = pathDir + File.separator + "testLog1.txt";
		path2 = pathDir + File.separator + "testLog2.txt";
		try {
			Runtime.getRuntime().exec("logcat -c");
			
			Runtime.getRuntime().exec("mkdir " + pathDir);
			Runtime.getRuntime().exec("touch " + path1);
			Runtime.getRuntime().exec("touch " + path2);
			Process process = Runtime.getRuntime().exec("logcat -v time ");
			InputStream inputStream = process.getInputStream();
			InputStreamReader in = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			File file1 = new File(path1);
			while ((line = reader.readLine())!= null) {
//				System.out.println(line);
				if (file1.length()< 512000) {
					saveFile(line,path1);
				} else {
					saveFile(line,path2);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public BufferedWriter saveFile(String line, String path) {
		
		File file = new File(path);
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(file,true);
		
			OutputStreamWriter out = new OutputStreamWriter(stream);
			bW = new BufferedWriter(out);
			bW.append(line);
			bW.newLine();
			bW.flush();
			bW.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bW;
	}

}
