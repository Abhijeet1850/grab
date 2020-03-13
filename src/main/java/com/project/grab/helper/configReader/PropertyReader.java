package com.project.grab.helper.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.project.grab.helper.browserConfiguration.BrowserType;
import com.project.grab.helper.resource.ResourceHelper;

public class PropertyReader {

	public static FileInputStream fis;
	public static Properties mvConfig = new Properties();

	public PropertyReader() {
		try {
			fis = new FileInputStream(
					ResourceHelper.getResourcePath("\\src\\main\\resources\\ConfigFile\\grab.properties"));
			mvConfig.load(fis);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Long getImplicitWait() {
		return Long.parseLong(mvConfig.getProperty("implicitwait"));
	}

	public Long getExplicitWait() {
		return Long.parseLong(mvConfig.getProperty("explicitwait"));
	}

	public Long getLessExplicitWait() {
		return Long.parseLong(mvConfig.getProperty("LessExplicitWait"));
	}

	public Long getPageLoadTime() {
		return Long.parseLong(mvConfig.getProperty("pageloadTime"));
	}

	public Long getPollingTime() {
		return Long.parseLong(mvConfig.getProperty("pollingTime"));
	}

	public BrowserType getBroswerType() {
		return BrowserType.valueOf(mvConfig.getProperty("broswertype"));
	}

	public String getBroswerName() {
		return mvConfig.getProperty("browserName");
	}

	public String getHost() {
		return mvConfig.getProperty("host");
	}

	public String getBroswerVersion() {
		return mvConfig.getProperty("browserVersion");
	}

	public String getPlatform() {
		return mvConfig.getProperty("platform");
	}

	public String getAppRedirectURL() {
		return mvConfig.getProperty("AppLogin_NotSuccessFull_URL");
	}

	
// Android properties	
	
	public String getPlatformName() {
		return mvConfig.getProperty("platformName");
	}

	public String getPlatformVersion() {
		return mvConfig.getProperty("platformVersion");
	}

	public String getDeviceName() {
		return mvConfig.getProperty("deviceName");
	}

	public String getAutomationName() {
		return mvConfig.getProperty("automationName");
	}

	public String getAppiumServerURL() {
		return mvConfig.getProperty("appiumServerURL");
	}

	public String getAppiumServerPort() {
		return mvConfig.getProperty("appiumServerPort");
	}

}
