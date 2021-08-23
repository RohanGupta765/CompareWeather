package Tests;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import Configurations.SetupClass;
import POMPages.AccuWeatherHomePOM;
import POMPages.AccuWeatherInfoPOM;



public class CompareTemperature extends SetupClass{
	
	WebDriver driver=null;
	String site = null;
	static double accu_degree_temp_weather=0;
	static double openweather_temp_degree=0;
	static double range =0;
	static String City="";
	
	@BeforeClass
	@Parameters("browser")
	public void setTestConfig(String setbrowser)
	{	try {
			String browserName = setbrowser;
			String rangeStr = getpropertiesValues("range");
			range= Double.valueOf(rangeStr);
			site = getpropertiesValues("url");
			City =getpropertiesValues("city");
			driver = LaunchBrowser(browserName,site);
	  	  } catch (IOException io) 
			{	// TODO Auto-generated catch block
	  		  System.out.println(io.getClass().getSimpleName());
	  		  Assert.assertTrue(false);
	  		}
			catch (WebDriverException wd)
			{
				System.out.println(wd.getClass().getSimpleName());
				Assert.assertTrue(false);
			}
	}
	
	@AfterClass(alwaysRun = true)
	public void teardown()
	{
		closeBrowser();
	}
	
	@Test(priority=0)
	public void TC1_GetWeatherFromAccu()
	{
		driver.get(site);
		AccuWeatherHomePOM accuhp = new AccuWeatherHomePOM(driver);
		accuhp.EnterCity(City);
		accuhp.clickFirstSuggestedCity();
		
		AccuWeatherInfoPOM accuwp = new AccuWeatherInfoPOM(driver);
		String str_accu_weather_in_degree = accuwp.getWeatherDegreeText();
		String[] splitArray= str_accu_weather_in_degree.split("°");
		accu_degree_temp_weather = Double.valueOf((splitArray[0]).trim());
		Assert.assertTrue(accuwp.getWeatherInDegreeLabelElement().isDisplayed());
		
	}	
	
	

}
