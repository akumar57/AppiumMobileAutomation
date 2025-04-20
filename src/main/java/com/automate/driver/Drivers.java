package com.automate.driver;

import com.automate.constants.FrameworkConstants;
import com.automate.customexceptions.DriverInitializationException;
import com.automate.enums.ConfigJson;
import com.automate.enums.MobileBrowserName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Random;

import static com.automate.utils.configloader.JsonUtils.getConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Drivers {

  public static AppiumDriver<MobileElement> createAndroidDriverForNativeApp(String deviceName, String udid, int port, String emulator) {
    try {
      DesiredCapabilities capability = new DesiredCapabilities();
      capability.setCapability("appium:deviceName", deviceName);
      capability.setCapability("appium:udid", udid);
      capability.setCapability("platformName", "android"); // this is W3C-standard, no prefix needed
      capability.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);
      int systemPort = new Random().nextInt(1000) + 8200; // Generates between 8200-9199
      capability.setCapability("appium:systemPort", systemPort);
      capability.setCapability("appium:webkitDebugProxyPort", port);
      capability.setCapability("appium:chromedriverAutodownload", true);
      capability.setCapability(MobileCapabilityType.APP, FrameworkConstants.ANDROID_APK_PATH);



      if (emulator.equalsIgnoreCase("yes")) {
        capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
        capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                                 Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
      }
      return new AndroidDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createAndroidDriverForWeb(String deviceName, String udid, int port, String emulator) {
    try {
      DesiredCapabilities capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserName.CHROME);
      capability.setCapability(MobileCapabilityType.APP, FrameworkConstants.ANDROID_APK_PATH);

      capability.setCapability("appium:chromedriverPort", port);
      capability.setCapability("appium:chromedriverAutodownload", true);
            int systemPort = new Random().nextInt(1000) + 8200; // Generates between 8200-9199

            capability.setCapability("appium:systemPort", systemPort);


      if (emulator.equalsIgnoreCase("yes")) {
        capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
        capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                                 Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
      }

      return new AndroidDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createIOSDriverForNativeApp(String deviceName, String udid, int port) {
    try {
      DesiredCapabilities capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(MobileCapabilityType.APP, FrameworkConstants.IOS_APP_PATH);
      capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getConfig(ConfigJson.BUNDLE_ID));
      capability.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, port);
            capability.setCapability("appium:chromedriverAutodownload", true);
                  int systemPort = new Random().nextInt(1000) + 8200; // Generates between 8200-9199

                  capability.setCapability("appium:systemPort", systemPort);



      return new IOSDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createIOSDriverForWeb(String deviceName, String udid, int port) {
    try {
      DesiredCapabilities capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getConfig(ConfigJson.BUNDLE_ID));
      capability.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserName.SAFARI);
      capability.setCapability("webkitDebugProxyPort", port);
      capability.setCapability("appium:chromedriverAutodownload", true);
            int systemPort = new Random().nextInt(1000) + 8200; // Generates between 8200-9199

            capability.setCapability("appium:systemPort", systemPort);



      return new IOSDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }
}
