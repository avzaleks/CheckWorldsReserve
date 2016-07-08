package avz.bz.ua.googleassist;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class FantomDriver {

	private static String executable = getPath();
	private WebDriver driver;

	public FantomDriver() {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				executable);

	driver = new PhantomJSDriver(caps);
	}

	private static String getPath() {
		if (System.getProperty("os.name").contains("Windows"))
			executable = "D:\\phantomjs.exe";
		return executable;
	}

	public WebDriver getDriver() {
		return driver;
	}

}
