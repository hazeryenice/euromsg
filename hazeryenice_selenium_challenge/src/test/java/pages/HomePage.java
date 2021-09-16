

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.PageBase;

public class HomePage extends PageBase {

    //*********Constructor*********
    public HomePage(WebDriver driver) {
        super(driver);
    }
        
    
	//*********Web Elements*********
    By btnLoginBy = By.cssSelector("a.et_pb_button.et_pb_button_0_tb_header.hvr-buzz-out.et_pb_bg_layout_light");
 	By txtEmailBy = By.id("exampleInputEmail1");
	 By txtPasswordBy = By.id("exampleInputPassword1");
	 By btnOturumAcBy = By.cssSelector("button.btn.btn-block.btn-primary.mt-3");
	 By btnUyeEkleBy = By.cssSelector("b.mb-1");
	 By btnYeniListeBy = By.cssSelector("button.btn.btn-labeled.btn-purple");
	 By txtListeAdiBy = By.cssSelector("input.form-control.ng-pristine.ng-invalid.ng-touched");
    By btnSaveBy = By.cssSelector("span.btn-label");
	By btnUyeEkle2By = By.cssSelector("i.fa.fa-plus");
	By btnFormIleEkleBy = By.cssSelector("a.dropdown-item");
	By txtAdBy = By.id("firstName");
	By txtSoyadBy = By.id("lastName");
	By txtEpostaBy = By.id("email");
	By txtCheckBy = By.cssSelector("span.fa.fa-check");
	By btnKaydetBy = By.cssSelector("button.btn.btn-labeled.btn-success");
	By txtSuccessfulBy = By.cssSelector("tr.ng-star-inserted");

	//*********Page Methods*********

	
	public HomePage goToTheWebSite() {
		
		goToWebPage("https://www.euromsgexpress.com/");
	
		return this;	
	}
	public HomePage login() {

		click(btnLoginBy);
		writeText(txtEmailBy, "hazeryenice@gmail.com");
		writeText(txtPasswordBy, "hazerTest123");
		//bu adimda manuel olarak recaptcha dogrulanmalidir
		click(btnOturumAcBy);
		return this;
	}
	public HomePage uyeEkle() {
		click(btnUyeEkleBy);
		return this;
	}

	public HomePage yeniListeOlustur() {
		click(btnYeniListeBy);
		writeText(txtListeAdiBy, "liste");
		click(btnSaveBy, 0);
		return this;
	}

	public HomePage uyeEkle2() {
		click(btnUyeEkle2By);
		click(btnFormIleEkleBy, 0);
		writeText(txtAdBy, "Hazer");
		writeText(txtSoyadBy, "Yenicee");
		writeText(txtEpostaBy, "hazeryenice1@gmail.com");
		checked(txtCheckBy, 0);
		click(btnKaydetBy);
		return this;
	}

	public HomePage checkSuccess() {
		if (getChild(txtSuccessfulBy, "td", 1).equals("Hazer")){
			System.out.println("Test successful!");
		}

		return this;
	}















}