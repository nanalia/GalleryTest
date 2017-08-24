package com.gallery.test.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class Tools {
	public static final Tools tools = new Tools();

	public Tools() {

	}

	public static Tools getInstance() {
		return tools;
	}

	public UiObject isVewExists(String strView, long timeout)
			throws UiObjectNotFoundException {
		UiObject result = null;
		long startTime = Calendar.getInstance().getTimeInMillis();
		while ((Calendar.getInstance().getTimeInMillis() - startTime) <= timeout) {
			if ((result = new UiObject(new UiSelector().text(strView)))
					.exists()) {
				break;
			}
		}
		return result;
	}
	public static boolean isViewIdExists(String strView, long timeout)
			throws UiObjectNotFoundException {
		UiObject result = null;
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) <= timeout) {
			if ((result = new UiObject(new UiSelector().resourceId(strView)))
					.exists()) {
				return true;
			}
		}
		return false;
	}
	public UiObject getViewByResId(String resouceid, int index, long timeout) {

		UiObject result = null;
		long startTime = Calendar.getInstance().getTimeInMillis();
		while ((Calendar.getInstance().getTimeInMillis() - startTime) <= timeout) {
			if ((result = new UiObject(new UiSelector().resourceId(resouceid)
					.instance(index))).exists()) {
				break;
			}
		}
		return result;
	}
	
	public UiObject getViewByResIdAndText(String resouceid, String text,int index, long timeout) {

		UiObject result = null;
		long startTime = Calendar.getInstance().getTimeInMillis();
		while ((Calendar.getInstance().getTimeInMillis() - startTime) <= timeout) {
			if ((result = new UiObject(new UiSelector().resourceId(resouceid)
					.textMatches(text)
					.instance(index))).exists()) {
				break;
			}
			else
			{
				UiDevice.getInstance().swipe(200, 500, 200, 50, 5);
			}
		}
		return result;
	}

	public UiObject getViewByText(String text, int index, long timeout) {

		UiObject result = null;
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		while ((Calendar.getInstance().getTimeInMillis() - startTime) <= timeout) {
			if ((result = new UiObject(new UiSelector().text(text).instance(
					index))).exists()) {
				break;
			}
		}
		return result;
	}

	public UiObject getViewByClass(String classname, int index, long timeout) {

		UiObject result = null;
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		while ((Calendar.getInstance().getTimeInMillis() - startTime) <= timeout) {
			if ((result = new UiObject(new UiSelector().className(classname).instance(
					index))).exists()) {
				break;
			}
		}
		return result;
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static void wakeUp() {
		try {
			while (UiDevice.getInstance().isScreenOn() == false){
				UiDevice.getInstance().wakeUp();
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void unLock() {
		int mWidth = UiDevice.getInstance().getDisplayWidth();
		int mHeight = UiDevice.getInstance().getDisplayHeight();
		
		UiDevice.getInstance().pressKeyCode(26);
		sleep(1000);
		wakeUp();
		sleep(1000);
		UiDevice.getInstance().swipe(mWidth/2, mHeight-100, mWidth/2, 100, 6);
	}
	
	public static boolean sameAs (String path1, String path2) throws FileNotFoundException {
		boolean res = false;
		FileInputStream fis1 = new FileInputStream(path1);
		Bitmap bitmap1  = BitmapFactory.decodeStream(fis1);
		
		FileInputStream fis2 = new FileInputStream(path2);
		Bitmap bitmap2  = BitmapFactory.decodeStream(fis2);
		
		res = sameAs(bitmap1,bitmap2);
	
		return res;
			
	}
	
	public static void setTime (int afterMinutes) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		 


		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH) + 1; 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second); 
		c.add(Calendar.MINUTE, afterMinutes);
		String yearAfter = Integer.toString(c.get(Calendar.YEAR)); 
		String monthAfter = Integer.toString(c.get(Calendar.MONTH) + 1); 

		String dateAfter = Integer.toString(c.get(Calendar.DATE)); 
		String hourAfter = Integer.toString(c.get(Calendar.HOUR_OF_DAY)); 
		String minuteAfter = Integer.toString(c.get(Calendar.MINUTE)); 
		String secondAfter = Integer.toString(c.get(Calendar.SECOND)); 
		System.out.println(yearAfter + "/" + monthAfter + "/" + dateAfter + " " +hourAfter + ":" +minuteAfter + ":" + secondAfter);
		String timeStr = addZero(monthAfter) + addZero(dateAfter) + addZero(hourAfter) + addZero(minuteAfter) + addZero(yearAfter) + "." + addZero(secondAfter);
		
		String cmd = "date " + timeStr;
		System.out.println(cmd);
		try {
			Runtime.getRuntime().exec(cmd);
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	public static String addZero (String str) {
		 if (str.length() == 1) {
			 return "0"+str;
		 } else {
			 return str;
		 }
	}

	
	public static boolean sameAs (Bitmap bitmap1, Bitmap bitmap2) {
		
		System.out.println("enterSameAs");

		int width = bitmap1.getWidth();
		int height = bitmap1.getHeight();

		int numDiffPixels = 0;
		int numSamePixels = 0;
	     for (int y = 0; y < height; y++) {
	       for (int x = 0; x < width; x++) {
	    	 int redValue1 = Color.red(bitmap1.getPixel(x, y));
	         int blueValue1 = Color.blue(bitmap1.getPixel(x, y));
	         int greenValue1 = Color.green(bitmap1.getPixel(x, y));
	         int redValue2 = Color.red(bitmap2.getPixel(x, y));
	         int blueValue2 = Color.blue(bitmap2.getPixel(x, y));
	         int greenValue2 = Color.green(bitmap2.getPixel(x, y));

	         if ((Math.abs(redValue1-redValue2)<=5) && (Math.abs(greenValue1-greenValue2)<=5) && (Math.abs(blueValue1-blueValue2)<=5)){
	        	 numSamePixels++; 
//	           	 System.out.println("bitmap1: " + redValue1 + " " + blueValue1 + "" + greenValue1 + "\nbitmap2:"  + redValue2 + " " + blueValue2 + "" + greenValue2);
//	             System.out.println("相同： x:" + x + "y" + y);
	         } else {
	             numDiffPixels++;
//	             System.out.println("bitmap1: " + redValue1 + " " + blueValue1 + "" + greenValue1 + "\nbitmap2:"  + redValue2 + " " + blueValue2 + "" + greenValue2);
//	             System.out.println("不同： x:" + x + "y" + y);
	         }
	       }
	     }
	     double numberPixels = height * width;
	     double samePercent = numSamePixels / numberPixels;
	     
	     System.out.println(samePercent);
	     System.out.println("相似像素数量：" + numSamePixels + " 不相似像素数量：" + numDiffPixels + " 相似率：" + samePercent);
	     if (samePercent > 0.4) {
	    	 return true;
	     } else {
	    	 return false;
	     }

	}
	

	
	public static Bitmap getSubImage(String path,int x,int y,int width,int height) throws FileNotFoundException {
		
		FileInputStream fis = new FileInputStream(path);
		Bitmap bitmap  = BitmapFactory.decodeStream(fis);
				
		Bitmap res = Bitmap.createBitmap(bitmap, x, y, width, height);
		
		return res;
		
	}
}
