package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccuWeatherHomePOM {
	
	public AccuWeatherHomePOM(WebDriver basedriver)
	{
		PageFactory.initElements(basedriver,this);
	}
	
	
	@FindBy (xpath=("//input[@name='query']"))
	private WebElement enter_city;        
	public WebElement getEnterCityInputBoxElement() //WebElement Locator Definition
		{return enter_city;}		//Get the WebElement
	public void EnterCity(String city) 	
		{enter_city.sendKeys(city);;}		// Action(s) on/with WebElement. This same pattern is followed below for all Elements
	
	@FindBy (xpath=("//div[@class='results-container']/div[1]"))
	private WebElement first_suggested_city;        
	public WebElement getFirstSuggestedCity() 
		{return first_suggested_city;}
	public void clickFirstSuggestedCity() 
	{first_suggested_city.click();}		
	
}
