package Configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetupClass {
	WebDriver  sourcedriver;
	public WebDriver LaunchBrowser(String browser, String site)
	{
		if(browser.equalsIgnoreCase("Chrome"))
			{WebDriverManager.chromedriver().setup();
			ChromeOptions chrOpt= new ChromeOptions();
			chrOpt.addArguments("start-maximized");
			chrOpt.addArguments("disable-extentions");
			chrOpt.addArguments("disable-popup-blocking");
			chrOpt.addArguments("ignore-certificate-errors");
			sourcedriver = new ChromeDriver(chrOpt);}
		else if(browser.equalsIgnoreCase("Firefox"))
			{WebDriverManager.firefoxdriver().setup();
			sourcedriver = new FirefoxDriver();}
		else if(browser.equalsIgnoreCase("Edge"))
			{WebDriverManager.edgedriver().setup();
			sourcedriver = new EdgeDriver();}
		else if(browser.equalsIgnoreCase("Opera"))
			{WebDriverManager.edgedriver().setup();
			sourcedriver = new OperaDriver();
			}
		sourcedriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		sourcedriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		sourcedriver.get(site);
		sourcedriver.manage().window().maximize();
		return sourcedriver;
	}
	
	public void closeBrowser()
	{
		sourcedriver.quit();
	}
	public String getpropertiesValues(String key) throws IOException
	{
		
		File f = new File(System.getProperty("user.dir") + "/src/test/java/Configurations/configure.properties");
		FileInputStream str = new FileInputStream(f);
		Properties p = new Properties();
		p.load(str);
		String val = p.getProperty(key);
		return val;
	}
	
	public ExtentReports GetHtmlReports() {
		{	
			ExtentHtmlReporter sourcehtml;
			ExtentReports sourceReports;
			
			sourcehtml = new ExtentHtmlReporter(System.getProperty("user.dir")+"/endReport/ExtReportDemo.html");
			
			sourcehtml.config().setDocumentTitle("AccuWeather Automation Demo ");
			sourcehtml.config().setReportName("Fetch Weather");
			sourcehtml.config().setTheme(Theme.STANDARD);
			
			sourceReports = new ExtentReports();
			sourceReports.attachReporter(sourcehtml);
				
			sourceReports.setSystemInfo("AccuWeather Page", "Automation Demo by Rohan");
			return sourceReports;
		}

	  }
		
//		public static void endReport()
//		{
//			eReports.flush();
//		}
		
		public static String CaptureSS(WebDriver driver, String ssName) throws IOException
		{
			String datename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destn = System.getProperty("user.dir") + "/captures/" + ssName + datename + ".png";
			File final_destn = new File(destn);
			FileUtils.copyFile(source, final_destn);
			return destn;
			
		}

}