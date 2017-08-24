package com.gallery.test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.gallery.test.utils.Tools;
import com.gallery.test.utils.Config;
import com.gallery.test.utils.LogcatThead;
import com.gallery.test.utils.UiAutomatorHelper;

//import com.a.test.TestStorage;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import android.graphics.Bitmap;
import android.os.RemoteException;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;  
import com.android.uiautomator.core.UiObjectNotFoundException;  
import com.android.uiautomator.core.UiSelector;  


public class TestGallery extends UiAutomatorTestCase {
	
	
	
	@Override
	protected void setUp() throws Exception {

		LogcatThead t = new LogcatThead();
		Thread thread = new Thread(t);
		thread.start();
		Tools.wakeUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
//		
//		getUiDevice().pressKeyCode(26);
//		Tools.wakeUp();
//		String path = "/sdcard/test/testLog.txt";
//		File file = new File(path);
//		System.out.println("文件大小"+file.length());
	}

	
	
	public void atestBattery() throws UiObjectNotFoundException, RemoteException {

		for (int i = 0;;i++){
			System.out.println(i);
//			while (getUiDevice().isScreenOn() == false) {
//				
//				try {
//					getUiDevice().wakeUp();
//				} catch (RemoteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
			Tools.sleep(2000);
	        Tools.unLock();
	        Tools.sleep(2000);
//	        
//
//	        Tools.wakeUp();
//	        Tools.sleep(2000);
		}
		
	}
	
	public void atestFav() throws UiObjectNotFoundException, FileNotFoundException {
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		
		File filePath = new File("/sdcard/test.png");
		File filePath1 = new File("/sdcard/test1.png");
		if(filePath.exists())
			filePath.delete();
		if(filePath1.exists())
			filePath1.delete();
//
//		while (Tools.getInstance().getViewByResId(Config.MENU_ITEM,1, 3000).exists() == false) {
//			Tools.sleep(1000);
//		}
		getUiDevice().click(mWidth/2 , mHeight/2);
		Tools.sleep(2000);
		getUiDevice().takeScreenshot(filePath);


		getUiDevice().click(mWidth/2 , mHeight/2);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 1, 3000).click();		
		
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).clickAndWaitForNewWindow();
		Tools.sleep(1000);
		UiObject switchButton = Tools.getInstance().getViewByResId(Config.JUMPER_VALUE, 1, 3000);
		switchButton.clickAndWaitForNewWindow();
		
		Tools.sleep(2000);
		getUiDevice().click(mWidth/2 , mHeight/2);
		Tools.sleep(2000);
		getUiDevice().takeScreenshot(filePath1);
		System.out.println(filePath1.getAbsolutePath()); 
		Bitmap sub1 = Tools.getSubImage(filePath1.getAbsolutePath(), 0, 0, mWidth, mHeight);
		Bitmap sub2 = Tools.getSubImage(filePath.getAbsolutePath(), 0, 0, mWidth, mHeight);
		boolean isSame = Tools.sameAs(sub1, sub2);
		Tools.unLock();
		assertTrue("收藏图片fail", isSame);

	}

	public void atestApply() throws UiObjectNotFoundException, FileNotFoundException {
		
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		File filePath = new File("/sdcard/test.png");
		File filePath1 = new File("/sdcard/test1.png");
		
		if(filePath.exists())
			filePath.delete();
		if(filePath1.exists())
			filePath1.delete();

		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		Tools.sleep(4000);
		getUiDevice().swipe(mWidth-100, 1000, 100, 1000, 6);
		Tools.sleep(2000);
		getUiDevice().click(mWidth/2 , mHeight/2);
		Tools.sleep(2000);
		getUiDevice().takeScreenshot(filePath);
		getUiDevice().click(mWidth/2 , mHeight/2);
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 4, 3000).click();
		while (!Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).exists()) {
			Tools.sleep(1000);
		}
		Tools.sleep(2000);
		getUiDevice().takeScreenshot(filePath1);
		Bitmap sub1 = Tools.getSubImage(filePath1.getAbsolutePath(), 0, 0, mWidth, mHeight);
		Bitmap sub2 = Tools.getSubImage(filePath.getAbsolutePath(), 0, 0, mWidth, mHeight);
		boolean isSame = Tools.sameAs(sub1, sub2);
		assertTrue("应用图片fail!", isSame);
		Tools.unLock();
	}
	
	public void testDelete() throws UiObjectNotFoundException {

		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		Tools.sleep(2000);
		String content1 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();

		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 2, 3000).clickAndWaitForNewWindow();
		Tools.sleep(2000);
		String content2 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		getUiDevice().pressBack();
		Tools.sleep(3000);

		assertFalse(content1.equals(content2));
		
	}
	
	public void testFrequencyAuto() throws UiObjectNotFoundException {
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();

		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).click();

		Tools.getInstance().getViewByResId(Config.JUMPER_VALUE, 0, 3000).clickAndWaitForNewWindow();
		Tools.getInstance().getViewByText("自动", 0, 3000).click();
		getUiDevice().pressBack();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		getUiDevice().swipe(mWidth/2,mHeight-100 , mWidth/2, 100, 6);
		Tools.sleep(3000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		
		String content1 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		getUiDevice().pressBack();

		Tools.wakeUp();
		getUiDevice().swipe(mWidth/2,mHeight-100 , mWidth/2, 100, 6);
		Tools.sleep(3000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		String content2 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		System.out.println("\ncontent1: " + content1 + "\ncontent2:" + content2 );
		assertFalse("自动更新图片fail!", content1.equals(content2));
	}
	
	public void testFrequency15() throws UiObjectNotFoundException {
		
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		getUiDevice().pressHome();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).click();

		Tools.getInstance().getViewByResId(Config.JUMPER_VALUE, 0, 3000).clickAndWaitForNewWindow();
		Tools.getInstance().getViewByText("15分钟", 0, 3000).click();
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		String content1 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		Tools.setTime(13);
		Tools.unLock();
		Tools.sleep(2000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(3000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content2 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		assertTrue("未到15分钟，不应该换图！", content1.equals(content2));
		
		Tools.setTime(3);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.unLock();
		Tools.sleep(3000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content3 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		System.out.println("\ncontent1: " + content1 + "\ncontent2:" + content2 + "\ncontent3:" + content3);
		assertFalse("超过15分钟，未换图！", content2.equals(content3));

	}
	
	public void testFrequencyOneHour() throws UiObjectNotFoundException {
		//应用一张图片为壁纸
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		getUiDevice().swipe(mWidth-100, 1000, 100, 1000, 6);
		
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 4, 3000).clickAndWaitForNewWindow();
		
		//选择更新频率为1小时
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).click();

		Tools.getInstance().getViewByResId(Config.JUMPER_VALUE, 0, 3000).clickAndWaitForNewWindow();
		Tools.getInstance().getViewByText("1小时", 0, 3000).click();
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		
		//调整时间不到1小时以后，不换图
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content1 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		Tools.setTime(58);
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(3000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content2 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		assertTrue("未到1小时，不应该换图！", content1.equals(content2));
		
		//调整时间1小时以后，换图
		Tools.setTime(3);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.unLock();
		Tools.sleep(3000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content3 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		System.out.println("\ncontent1: " + content1 + "\ncontent2:" + content2 + "\ncontent3:" + content3);
		assertFalse("超过1小时，未换图！", content2.equals(content3));

	}
	
	public void testFrequencyThreeHours() throws UiObjectNotFoundException {
		
		//应用一张图片为壁纸
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		getUiDevice().swipe(mWidth-100, 1000, 100, 1000, 6);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 4, 3000).clickAndWaitForNewWindow();
				
				
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).click();

		Tools.getInstance().getViewByResId(Config.JUMPER_VALUE, 0, 3000).clickAndWaitForNewWindow();
		Tools.getInstance().getViewByText("3小时", 0, 3000).click();
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		String content1 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		Tools.setTime(3*60-2);
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(3000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		String content2 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		assertTrue("未到3小时，不应该换图！", content1.equals(content2));
		
		Tools.setTime(3);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.unLock();
		Tools.sleep(3000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content3 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		System.out.println("\ncontent1: " + content1 + "\ncontent2:" + content2 + "\ncontent3:" + content3);
		assertFalse("超过3小时，未换图！", content2.equals(content3));

	}
	
	public void testFrequencyOneDay() throws UiObjectNotFoundException {
		
		//应用一张图片为壁纸
		int mWidth = getUiDevice().getDisplayWidth();
		int mHeight = getUiDevice().getDisplayHeight();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		getUiDevice().swipe(mWidth-100, 1000, 100, 1000, 6);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 4, 3000).clickAndWaitForNewWindow();
		
		
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).click();

		Tools.getInstance().getViewByResId(Config.JUMPER_VALUE, 0, 3000).clickAndWaitForNewWindow();
		Tools.getInstance().getViewByText("1天", 0, 3000).click();
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content1 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		Tools.setTime(24*60-2);
		Tools.unLock();
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(3000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content2 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		assertTrue("未到1天，不应该换图！", content1.equals(content2));
		
		Tools.setTime(3);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.unLock();
		Tools.sleep(3000);
		getUiDevice().pressKeyCode(26);
		Tools.wakeUp();
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).click();
		String content3 = Tools.getInstance().getViewByResId(Config.PLAYER_CONTENT, 0, 3000).getText();
		System.out.println("\ncontent1: " + content1 + "\ncontent2:" + content2 + "\ncontent3:" + content3);
		assertFalse("超过1天，未换图！", content2.equals(content3));

	}
	
	public static void testSwitch() throws UiObjectNotFoundException {
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		Tools.getInstance().getViewByResId(Config.MENU_ITEM, 0, 3000).clickAndWaitForNewWindow();
		UiObject switchButton = Tools.getInstance().getViewByResId(Config.SWITCH_BUTTON, 0, 3000);
		if (switchButton.isChecked()) {
			switchButton.click();
		}
		Tools.unLock();
		
		UiDevice.getInstance().pressKeyCode(26);
		Tools.wakeUp();
		Tools.sleep(1000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		UiObject declarationButton = Tools.getInstance().getViewByResId(Config.DECLARATION_APPLY, 0, 3000);
		assertTrue("没有弹出引导页！", declarationButton.exists());
		declarationButton.clickAndWaitForNewWindow();
		UiDevice.getInstance().pressBack();
		Tools.sleep(2000);
		Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
		assertFalse("开启画报未成功!",Tools.getInstance().getViewByResId(Config.DECLARATION_APPLY, 0, 3000).exists());
		
	}
	
	public static void atestTag() throws UiObjectNotFoundException {
//		UiObject tagButton = new UiObject(new UiSelector().fromParent(new UiSelector().className(Config.TAG_PLAYER)));
		UiObject tagButton = new UiObject(new UiSelector().resourceId(Config.TAG_PLAYER_ID).childSelector(new UiSelector().className(Config.TAG_CLASS).index(0)));
		tagButton.clickAndWaitForNewWindow();
	}
	
	public static void testRefresh() throws UiObjectNotFoundException, IOException {
		int runTimes = 50;
		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<runTimes;i++) {
			System.out.println("第" + i + "次测试");
			File filePath = new File("/sdcard/test1/test"+i+".png");
			if(filePath.exists())
				filePath.delete();
			
			Tools.getInstance().getViewByText("小米画报", 0, 3000).clickAndWaitForNewWindow();
			boolean result = Tools.getInstance().isViewIdExists(Config.REFRESH_BUTTON, 5000);
			
			if (result == true){
				UiDevice.getInstance().takeScreenshot(filePath);
				System.out.println("fail");
				break;
			} else {
				System.out.println("pass");
			}
			UiObject result1 = null;
//			while ((new UiObject(new UiSelector().text("小米画报"))).exists()== false)
//			{
//				long timeout =Configurator.getInstance().getActionAcknowledgmentTimeout();
//				Configurator.getInstance().setActionAcknowledgmentTimeout(0);
//				UiDevice.getInstance().pressBack();
//				UiDevice.getInstance().pressBack();
//				UiDevice.getInstance().pressBack();
//				Configurator.getInstance().setActionAcknowledgmentTimeout(timeout);
//				
//			}
			Runtime.getRuntime().exec(stopGallery);
			
		}
	}
	
	public static void testClickBuy() throws UiObjectNotFoundException, IOException {
		int runTimes = 500000;
		String stopGallery = "am force-stop com.mfashiongallery.emag";
		for (int i=0;i<runTimes;i++) {
			UiDevice.getInstance().click(905, 815);
			
		}
	}
	
	public static void atestSubscribe() throws UiObjectNotFoundException, IOException {
		String installCmd = "pm install /sdcard/053.apk";
		String uninstallCmd = "pm uninstall com.mfashiongallery.emag";
		String openSetting = "am start -W -n com.mfashiongallery.emag/com.mfashiongallery.emag.ssetting.SSettingActivity";
		
		int runTimes = 50;
		for (int i=0;i<runTimes;i++) {
			System.out.println("第" + i + "次测试");
			File filePath = new File("/sdcard/test1/test"+i+".png");
			if(filePath.exists())
				filePath.delete();
			Runtime.getRuntime().exec(uninstallCmd);
			Tools.sleep(2000);
			Runtime.getRuntime().exec(installCmd);
			Tools.sleep(2000);
//			Tools.unLock();
			UiDevice.getInstance().pressKeyCode(26);
			Tools.wakeUp();
			
			Tools.unLock();
			UiDevice.getInstance().pressKeyCode(26);
			Tools.wakeUp();
			Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
			UiDevice.getInstance().pressBack();
			Tools.getInstance().getViewByResId(Config.ENTER_BUTTON,0, 3000).clickAndWaitForNewWindow();
			UiDevice.getInstance().pressBack();
			Tools.unLock();
			Tools.unLock();
			Runtime.getRuntime().exec(openSetting);
			Tools.sleep(1000);
			UiObject result = null;
			if ((result = new UiObject(new UiSelector().text("系统默认")
					.instance(1))).exists()) {
				UiDevice.getInstance().takeScreenshot(filePath);
				System.out.println("fail");
			} else {
				System.out.println("pass");
			}
		}
	}
	public static void testReInstall() throws UiObjectNotFoundException, IOException {
		String cmd1 = "pm install /sdcard/0511.apk";
		String cmd2 = "pm install /sdcard/0527.apk";
		String cmd3 = "pm uninstall com.mfashiongallery.emag";
		String openSetting = "am start -W -n com.mfashiongallery.emag/com.mfashiongallery.emag.ssetting.SSettingActivity";

		for (int i=0;i<1;i++) {
			
			Runtime.getRuntime().exec(cmd3);
			Tools.sleep(3000);
			Runtime.getRuntime().exec(cmd1);
			Runtime.getRuntime().exec(openSetting);
			Runtime.getRuntime().exec(cmd2);
			Runtime.getRuntime().exec(openSetting);
		}
	}
	public static void main(String[] args)
	{

		String jarName, testClass, testName, androidId;
	   	jarName = "Gallery";
	   	testClass = "com.gallery.test.TestGallery";
	   	testName = "testRefresh";
	   	androidId = "1";
	   	UiAutomatorHelper na = new UiAutomatorHelper(jarName, testClass, testName, androidId);

	}	
		
}