	package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;




public class TestBase {
    public WebDriver driver;
    static ExtentReports extent;
    ExtentHtmlReporter reporter;	
    ExtentTest logger;
    protected static Configuration configurationGet = Configuration.getInstance();
    
    @BeforeSuite
    public void beforeSuite() {
        extent = new ExtentReports();
        reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\AutomationReport\\"+ String.valueOf(new File(".\\Reports\\AutomationReport\\").list().length+1)+".html");
       
      System.out.println(reporter.getFilePath());
        extent.attachReporter(reporter);
    }

    @BeforeClass
    public void beforeClass() {    }

    

    @BeforeMethod
    public void beforeMethod(Method method) throws Exception {
    	

    	
    	////////////////////*********mute the code below when scheduled on Jenkins******
        System.setProperty("browser", "chrome");
     
        
    	
  	if (configurationGet.gettestTarget().equals("webLocal")) {

            switch (System.getProperty("browser")) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//chromeDriver//chromedriver.exe");
                    driver = new ChromeDriver(chromeOptions());
                    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                    driver.manage().window().maximize();
                    
                      driver.manage().window().setPosition(new Point(0, 0));
                      driver.manage().window().setSize(new Dimension(1920, 1080));
                      
                      System.out.println("Window width: " + driver.manage().window().getSize().getWidth());
                      System.out.println("Window height: " + driver.manage().window().getSize().getHeight());
                      break;

                case "firefox":
                    System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//geckoDriver//geckodriver.exe");
                    driver = new FirefoxDriver(firefoxOptions());
                    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                    driver.manage().window().maximize();
                    
                      driver.manage().window().setPosition(new Point(0, 0));
                      driver.manage().window().setSize(new Dimension(1920, 1080));
                      
                      System.out.println("Window width: " + driver.manage().window().getSize().getWidth());
                      System.out.println("Window height: " + driver.manage().window().getSize().getHeight());
                      break;

                case "ie":
                    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"//ieDriver//IEDriverServer_x32.exe");
                    driver = new InternetExplorerDriver(ieCapabilities());
                    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
                    driver.manage().window().maximize();
                    
                    
                    driver.manage().window().setPosition(new Point(0, 0));
                    driver.manage().window().setSize(new Dimension(1920, 1080));
                    
                    System.out.println("Window width: " + driver.manage().window().getSize().getWidth());
                    System.out.println("Window height: " + driver.manage().window().getSize().getHeight());
                    break;
            }
        }
    	
    	
        logger = extent.createTest(method.getName());
        logger.info("Driver has been initialized and the test has started.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            String temp=ExtentReportUtilities.getScreenshot(driver);
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
           
            
        }

        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.info("Test succeeded.");
        }
    }

    @AfterClass()
    public void afterClass() {

       driver.quit(); 

       
    }

    @AfterSuite()
    public void afterSuite() throws Exception {
        extent.flush();
    
    }


    private ChromeOptions chromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-translate");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");

        return chromeOptions;
    }

    private FirefoxOptions firefoxOptions() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--test-type");
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.addArguments("--ignore-certificate-errors");
        firefoxOptions.addArguments("--disable-translate");
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-notifications");

        return firefoxOptions;
    }

    private InternetExplorerOptions ieCapabilities() {

        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("nativeEvents", false);
        options.setCapability("unexpectedAlertBehaviour", "accept");
        options.setCapability("disable-popup-blocking", true);
        options.setCapability("enablePersistentHover", true);
        options.setCapability("ignoreZoomSetting", true);

        return options;
    }
}
