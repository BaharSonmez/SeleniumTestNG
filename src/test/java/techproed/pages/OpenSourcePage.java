package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;

public class OpenSourcePage {
    /*
    Locate'lerimizi tek bir yerde duzenli bir sekilde tutabilmek icin;
     1-  Ilk olarak olusturmus oldugumuz page calss'imiza bir constructor olustururuz
     2- PageFactory class'indan initelements methodu ile driver'i tanitiriz
     3- @FindBy notasyonu kullanarak locatelerimizi aliriz
     */
    public OpenSourcePage(){
        PageFactory.initElements(C01_DataProvider.Driver.getDriver(),this);
    }

   // Driver.getDriver().findElement(By.xpath("locate")); normalde boyle yapiyordum..
   @FindBy(xpath = "//*[@name='username']")
   public WebElement username;
    @FindBy(xpath = "//*[@name='password']")
    public WebElement password;
    @FindBy(xpath = "//*[@type='submit']")
    public WebElement login;
    @FindBy(xpath = "//h6")
    public WebElement dashboard;




}
