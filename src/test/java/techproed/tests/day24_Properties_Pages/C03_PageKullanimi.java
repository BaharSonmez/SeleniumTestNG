package techproed.tests.day24_Properties_Pages;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.TestCenterTechproPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;

public class C03_PageKullanimi {
    @Test
    public void test01() {
      //https://testcenter.techproeducation.com/index.php?page=form-authentication sayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("testCenterUrl"));
        //Page object Model kullanarak sayfaya giriş yapildigini test edin
        //Yukarida sayfaya girdim simdi locateleri almam lazim onun icin
        TestCenterTechproPage TestCenterTechproPage = new TestCenterTechproPage();
        TestCenterTechproPage.userName.sendKeys(ConfigReader.getProperty("kullaniciAdi"), Keys.TAB,
                                                 ConfigReader.getProperty("kullaniciPassword"),Keys.ENTER);

        //simdi giris yapildigini test edicez yani karsimiza cikan sayfada yesil you logged alanini locate edicez
        Assert.assertTrue(TestCenterTechproPage.text.isDisplayed());

        //Sayfadan cikis yap ve cikis yapildigini test et
        //burada logout butonunu locate edicez
         TestCenterTechproPage.logout.click();
         //loguut oldugunu "login Page " basligini locate ederek dogrulayabiliriz
        Assert.assertTrue(TestCenterTechproPage.giris.isDisplayed());
        Driver.closeDriver();


    }
}
