package com.gallery.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import android.graphics.Bitmap;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.gallery.test.utils.UiAutomatorHelper;
import com.gallery.test.utils.Config;
import com.gallery.test.utils.LogcatThead;
import com.gallery.test.utils.Tools;




public class CompareScreenshots extends UiAutomatorTestCase   {
	
	
	
	@Override
	protected void setUp() throws Exception {

		LogcatThead t = new LogcatThead();
		Thread thread = new Thread(t);
		thread.start();
		Tools.wakeUp();
	}
	
	public void testCompareScreenshotsNSubScrenshots() throws UiObjectNotFoundException, IOException, InterruptedException {
		UiDevice device = getUiDevice();
		//device.pressHome();
//		UiObject appNotes = new UiObject(new UiSelector().text("Notes"));
//		appNotes.click();
		Thread.sleep(3000);
		
		String p1 = "/sdcard/test.png";
		String p2 = "/sdcard/test1.png";
//		File f1 = new File(p1);
//		if(f1.exists())
//			f1.delete();
//		
//		File f2 = new File(p2);
//		if(f2.exists())
//			f2.delete();
//		
//		device.takeScreenshot(f1);
//		
//		
//	
//		device.takeScreenshot(f2);
//		
		Bitmap sub1 = Tools.getSubImage(p1, 0, 0, 1080, 1920);
		Bitmap sub2 = Tools.getSubImage(p2, 0, 0, 1080, 1920);
		

		boolean same = Tools.sameAs(sub1,sub2);
//		assertTrue(same);
		
	
	}
	
	public void testMonkey() throws UiObjectNotFoundException {
		
		int i = 0;
		int runTimes = 5000000;
		while (i < runTimes) {
			Tools.wakeUp();
			if (Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).exists()) {
				Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
			}
			double seedx = Math.random();
			int x = (int)(seedx * 1002);
			double seedy = Math.random();
			int y = (int)(seedy * 1737);
			getUiDevice().click(x, y);
			double seedstartX = Math.random();
			int startX = (int)(seedstartX * 1080);
			double seedstartY = Math.random();
			int startY = (int)(seedstartY * 1760);
			double seedendX = Math.random();
			int endX = (int)(seedendX * 1080);
			double seedendY = Math.random();
			int endY = (int)(seedendY * 1760);
			getUiDevice().swipe(startX, startY+158, endX, endY+158, 6);
			
			Tools.sleep(1000);
			i++;
			System.out.println("第" + i + "次");
		}
		
	}
	
	public void testShellMonkey() throws IOException {
		System.out.println("testBegin");
		int seed = (int)(Math.random()*10000);
		String cmd = "monkey -p com.mfashiongallery.emag -s " + seed + " -v  --ignore-crashes --ignore-security-exceptions --ignore-timeouts --pct-touch 10 --pct-motion 50 --pct-nav 0 --pct-trackball 0 --pct-majornav 20 --pct-appswitch 10 --pct-anyevent 10 --throttle 300 1000";
		System.out.println(cmd);
		Process process = Runtime.getRuntime().exec(cmd);
		System.out.println("finished_aaa");
	}
	public void testWakeUp() throws UiObjectNotFoundException {
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		Tools.wakeUp();
		Tools.sleep(2000);
		getUiDevice().swipe(mWidth/2,mHeight-100 , mWidth/2, 100, 6);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
	}
	
	public void testClick(){
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		getUiDevice().click(mWidth/2, mHeight/2);
	}
	
	public void testTime() throws IOException {
		Tools.setTime(10);
		Tools.sleep(2000);
		Tools.setTime(5);
	}
	public void testCheck() throws UiObjectNotFoundException {
		
		UiObject button = Tools.getInstance().getViewByResId(Config.SWITCH_BUTTON, 0, 3000);
		System.out.println(button.isChecked());
	}
	
	public static void testReInstall() throws UiObjectNotFoundException, IOException, InterruptedException {
		String cmd1 = "pm install /sdcard/0511.apk";
		String cmd2 = "pm install -r /sdcard/0527.apk";
		String cmd3 = "pm uninstall com.mfashiongallery.emag";
		String openSetting = "am start -W -n com.mfashiongallery.emag/com.mfashiongallery.emag.ssetting.SSettingActivity";

		for (int i=0;i<10000;i++) {
			System.out.println("第" + i + "次");
			String ls_1;
			Process process = Runtime.getRuntime().exec(cmd3);
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(process.getInputStream()));   
					while((ls_1=bufferedReader.readLine())!=null)   
						System.out.println(ls_1);  
			process.waitFor();
			Tools.sleep(3000);
			Process process1 = Runtime.getRuntime().exec(cmd1);
			BufferedReader bufferedReader1 = new BufferedReader( new InputStreamReader(process1.getInputStream()));   
			while((ls_1=bufferedReader1.readLine())!=null)   
				System.out.println(ls_1);  
			process1.waitFor();
			Tools.sleep(3000);
			Process process2 = Runtime.getRuntime().exec(openSetting);
			BufferedReader bufferedReader2 = new BufferedReader( new InputStreamReader(process2.getInputStream()));   
			while((ls_1=bufferedReader2.readLine())!=null)   
				System.out.println(ls_1);  
			process2.waitFor();
			Tools.sleep(3000);
			Process process3 = Runtime.getRuntime().exec(cmd2);
			BufferedReader bufferedReader3 = new BufferedReader( new InputStreamReader(process3.getInputStream()));   
			while((ls_1=bufferedReader3.readLine())!=null)   
				System.out.println(ls_1);  
			process3.waitFor();
			Tools.sleep(5000);
			Process process4 = Runtime.getRuntime().exec(openSetting);
			BufferedReader bufferedReader4 = new BufferedReader( new InputStreamReader(process4.getInputStream()));   
			while((ls_1=bufferedReader4.readLine())!=null)   
				System.out.println(ls_1);  
			process4.waitFor();
			Tools.sleep(3000);
		}
	}
	public static void testTag() throws UiObjectNotFoundException {
//		UiObject tagButton = new UiObject(new UiSelector().fromParent(new UiSelector().className(Config.TAG_PLAYER)));
//		UiObject tagButton = new UiObject(new UiSelector().resourceId(Config.TAG_PLAYER_ID).childSelector(new UiSelector().className(Config.TAG_CLASS).index(0)));
//		tagButton.clickAndWaitForNewWindow();
//		int mWidth = UiDevice.getInstance().getDisplayWidth();
//		int mHeight = UiDevice.getInstance().getDisplayHeight();
		
		int i = 0, testTime = 200;
		while (i < testTime) {
			
			File filePath = new File("/sdcard/test/test"+i+".png");
			if(filePath.exists())
				filePath.delete();
			Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
			Tools.getInstance().getViewByResId(Config.CANCEL, 0, 3000).clickAndWaitForNewWindow();
			Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).clickAndWaitForNewWindow();
			UiDevice.getInstance().swipe(540, 1940, 540, 100, 6);
			Tools.sleep(1000);
			UiDevice.getInstance().takeScreenshot(filePath);
			System.out.println("第"+ i + "次 done! ");
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressMenu();
			Tools.getInstance().getViewByResId(Config.CLEAR_BUTTON, 0, 3000).clickAndWaitForNewWindow();
			Tools.getInstance().getViewByText("设置", 0, 2000).clickAndWaitForNewWindow();
			UiDevice.getInstance().pressKeyCode(26);
			Tools.wakeUp();
			i++;
		}
	}
	
	public static void testBlack() throws UiObjectNotFoundException {
		int i = 0, testTime = 2000;
		while (i < testTime) {

			File filePath = new File("/sdcard/test/test"+i+".png");
			if(filePath.exists())
				filePath.delete();
			int mWidth = UiDevice.getInstance().getDisplayWidth();
			int mHeight = UiDevice.getInstance().getDisplayHeight();
			
			UiDevice.getInstance().pressKeyCode(26);
			Tools.sleep(2000);
			Tools.wakeUp();
			Tools.sleep(2000);
			UiDevice.getInstance().takeScreenshot(filePath);
			UiDevice.getInstance().swipe(mWidth/2, mHeight-100, mWidth/2, 100, 6);
			Tools.sleep(3000);
			i++;
			System.out.println("sunguannan第"+i+"次test!");
		}
	}
	
	public static void testEnterTime() throws UiObjectNotFoundException, IOException {
		String cmdStop = "am force-stop com.mfashiongallery.emag";
		int runTimes = 50;
		int totalTime = 0;
		for (int i=0;i<runTimes;i++) {
			Tools.wakeUp();
			System.out.println("第" + i + "次测试");
			long startTime = System.currentTimeMillis();
			Tools.getInstance().getViewByResId(Config.ENTER_BUTTON_7,0, 3000).clickAndWaitForNewWindow();
			System.out.println("clickAndWaitForNewWindow" + System.currentTimeMillis());
			while (Tools.isViewIdExists(Config.MENU_ITEM,50)== false) {
				System.out.println("not show");
			}
			System.out.println("isViewIdExists" + System.currentTimeMillis());
			System.out.println("show");
			long endTime = System.currentTimeMillis();
			long onceTime = endTime - startTime;
			System.out.println(onceTime);
			totalTime += onceTime;
			UiDevice.getInstance().pressBack();
			Runtime.getRuntime().exec(cmdStop);
			Tools.sleep(1000);
		}
		System.out.println("平均时间：" + totalTime/runTimes);
	}
	public static void testLaunchTime() throws UiObjectNotFoundException, IOException {
		int runTimes = 50;
		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<50;i++) {
			System.out.println("第" + i + "次测试");
			
			Tools.getInstance().getViewByText("小米画报", 0, 3000).clickAndWaitForNewWindow();
			Tools.sleep(2000);
			Runtime.getRuntime().exec(stopGallery);
			Tools.sleep(2000);
		}
	}
	
	public static void testLaunchDetailCold() throws UiObjectNotFoundException, IOException {
		int runTimes = 50;
		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<runTimes;i++) {
			System.out.println("第" + i + "次测试");
			
			Tools.getInstance().getViewByText("打开插件", 0, 3000).clickAndWaitForNewWindow();
			Tools.sleep(3000);
			Runtime.getRuntime().exec(stopGallery);
			Tools.sleep(2000);
		}
	}	
	public static void testLaunchDetailCold1() throws UiObjectNotFoundException, IOException {
		int runTimes = 50;
		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<runTimes;i++) {
			System.out.println("第" + i + "次测试");
			
			Tools.getInstance().getViewByText("小米画报", 0, 3000).clickAndWaitForNewWindow();
			boolean testaa = (new UiObject (new UiSelector().resourceId(Config.PIC))).exists(); 
			while( testaa == false) {
				Tools.sleep(500);
				testaa = (new UiObject (new UiSelector().resourceId(Config.PIC))).exists();
				System.out.println("wait sss");
			}
				
			UiDevice.getInstance().click(260, 640);
			Tools.sleep(3000);
			Runtime.getRuntime().exec(stopGallery);
			Tools.sleep(2000);
		}
	}
	
	public static void testLaunchDetailCold2() throws UiObjectNotFoundException, IOException {
		int runTimes = 50;
		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<runTimes;i++) {
			System.out.println("第" + i + "次测试");
			Tools.wakeUp();
			Tools.getInstance().getViewByResId(Config.ENTER_BUTTON, 0, 3000).clickAndWaitForNewWindow();
			Tools.sleep(3000);
			Runtime.getRuntime().exec(stopGallery);
			Tools.sleep(2000);
		}
	}
	
	public static void testLaunchDetailHot() throws UiObjectNotFoundException, IOException {
		int runTimes = 50;
//		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<runTimes;i++) {
			System.out.println("第" + i + "次测试");
			
			Tools.getInstance().getViewByText("打开插件", 0, 3000).clickAndWaitForNewWindow();
			Tools.sleep(3000);
			UiDevice.getInstance().pressBack();
//			Runtime.getRuntime().exec(stopGallery);
			Tools.sleep(2000);
		}
	}
	public static void main(String[] args)
	{
		String jarName, testClass, testName, androidId;
	   	jarName = "Gallery1";
	   	testClass = "com.gallery.test.CompareScreenshots";
	   	testName = "testEnterTime";
	   	androidId = "1";
	   	UiAutomatorHelper na = new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
}
