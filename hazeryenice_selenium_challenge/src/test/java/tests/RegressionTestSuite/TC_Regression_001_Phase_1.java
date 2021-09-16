package tests.RegressionTestSuite;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
 
import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.testng.annotations.Test;
import pages.*;
import utilities.RetryAnalyzer;
import utilities.TestBase;


public class TC_Regression_001_Phase_1 extends TestBase {

	

	
	HomePage homePage;

			@Test(retryAnalyzer=RetryAnalyzer.class)                                       //(priority = 0)
	public void TEST_RUN() throws Throwable  {
		// ***************************************PAGE INSTANTIATIONS***************************************
	//	provider = new Provider();

		homePage = new HomePage(driver);



		// ******************************************PARAMETERS**************************************
	
		
		
		
		// *************PAGE METHODS********************
			homePage.goToTheWebSite()
					.login()
					.uyeEkle()
					.yeniListeOlustur()
					.uyeEkle2()
					.checkSuccess();

		
		

		
		//tarih bilgisini getirir.. current date.
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date date = new Date();  
	    System.out.println(formatter.format(date));  

	    System.out.println("------------------------------");
		System.out.println("T E S T   S U C C E S S F U L.");
		System.out.println("------------------------------");
	}
}

