package pom.ecommerce.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.swingui.TestRunContext;

public class Reporting extends TestListenerAdapter{
	public ExtentSparkReporter htmlResporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testcontext) {
		String timeStamp=new SimpleDateFormat("YYYY.MM.dd.mm.ss").format(new Date());
		String repName="Test-Report-"+ timeStamp +".html";
		
		htmlResporter=new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/"+repName);
		try {
			htmlResporter.loadXMLConfig(System.getProperty("useer.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent=new ExtentReports();
		extent.attachReporter(htmlResporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","pavan");
		
		htmlResporter.config().setDocumentTitle("framework");
		htmlResporter.config().setReportName("FunctionalTest project");
//		htmlResporter.config().setTestViewChartLocation(chartLocation.TOP);
		htmlResporter.config().setTheme(Theme.DARK);
		
		
	}
	public void onTestSuccess(ITestResult tr) {
		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
   public void onTestFailure(ITestResult tr) {
	   logger=extent.createTest(tr.getName());
	   logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	   
	   
	   String screenshotpath=System.getProperty("user.dir")+"\\ScreenShots\\"+tr.getName()+".png";
	   File f=new File(screenshotpath);
	   
	   if(f.exists()) {
		   logger.fail("Screenshot is below : "+ logger.addScreenCaptureFromPath(screenshotpath));
	   }
   }
	   public void onTestSkipped(ITestResult tr) {
		   logger=extent.createTest(tr.getName());
		   logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		      
	   }
	    public void onFinish(ITestContext TestRunContext) {
	    	extent.flush();
	    }
   }

  
