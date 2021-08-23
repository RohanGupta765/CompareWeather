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

@Test(priority=1)
	public void TC2_GetWeatherFromOpenWeather() throws Exception, InterruptedException
	{	
		HttpRequestFactory requestFactory
		  = new NetHttpTransport().createRequestFactory();
		HttpRequest request = requestFactory.buildGetRequest(
		  new GenericUrl("https://api.openweathermap.org/data/2.5/weather?q="+City+"&units=metric&appid=7fe67bf08c80ded756e598d6f8fedaea"));
		String rawResponse = request.execute().parseAsString();

		JSONParser jp = new JSONParser();
		Object dataobj = jp.parse(rawResponse);
		JSONObject weatherObj =(JSONObject)dataobj; 
		
		String name = (String)weatherObj.get("name");
		JSONObject jarray = (JSONObject)weatherObj.get("main");
		openweather_temp_degree = (Double)jarray.get("temp");
		
		if(openweather_temp_degree==0)
		{
			Assert.assertTrue(false);
		}
	
	}
	
	@Test(priority=2)
	public void TC3_CompareTwoWeathers()
	{
		boolean compare_result = false;
		
		if(openweather_temp_degree==accu_degree_temp_weather)
		{
			compare_result = true;
		}
		Assert.assertTrue(compare_result, "AccuWeather : " +accu_degree_temp_weather +" vs OpenWeather :" + openweather_temp_degree+ " did not match exactly! -");

	}
	
	@Test(priority=3)
	public void TC4_VerifyDifferenceInRange()
	{
		boolean range_result = false;
		
		double diff = accu_degree_temp_weather - openweather_temp_degree;
		double abs_diff = Math.abs(diff);
		
		if(abs_diff<range)
		{
			range_result = true;
		}
		Assert.assertTrue(range_result, "The difference: "+abs_diff+ " is out of range. The range is "+range);
		
	}
	

}
