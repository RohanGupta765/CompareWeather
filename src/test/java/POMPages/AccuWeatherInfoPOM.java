package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccuWeatherInfoPOM {
	public AccuWeatherInfoPOM(WebDriver basedriver)
	{
		PageFactory.initElements(basedriver,this);
	}
	
	@FindBy (xpath=("//div[@class='forecast-container']//div[@class='temp-container']/div[@class='temp']"))
	private WebElement weather_degree_text;;        //WebElement Locator Definition
	public WebElement getWeatherInDegreeLabelElement() 
		{return weather_degree_text;}				//Get the WebElement
	public String getWeatherDegreeText()		    
		{return weather_degree_text.getText();}		// Action(s) on/with WebElement. This same pattern is followed below for all Elements

}
