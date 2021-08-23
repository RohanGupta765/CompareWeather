package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Configurations.SetupClass;

public class Listeners extends SetupClass implements ITestListener{
	ExtentReports eReports = GetHtmlReports();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentLocal = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result)
	{	
		extentTest = eReports.createTest(result.getMethod().getMethodName());
		extentTest.assignCategory(result.getTestClass().getRealClass().toString());
		result.getClass().toString();
		extentLocal.set(extentTest);
		
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		
		extentLocal.get().log(Status.PASS, "Test Passed");
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		String excMsg = null; 
		if (null != result.getThrowable()) {

		      excMsg = "Caused By : " + result.getThrowable().getMessage();
		    }
		extentLocal.get().log(Status.FAIL, excMsg);
		System.out.println("Test case Failed");
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		extentLocal.get().log(Status.SKIP, "Test was skipped: It may be because suite setup failed causing all tests to be skipped OR it was skipped intentionally using enabled=false testng annotation property");
	}
	public void onFinish(ITestContext result)
	{	
		
		eReports.flush();
		
	}
	
	

}
