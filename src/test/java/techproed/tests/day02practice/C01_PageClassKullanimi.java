package techproed.tests.day02practice;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.AmazonPage;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

import java.io.File;
import java.io.IOException;

public class C01_PageClassKullanimi {
// amazon sayfasına gidin

// dropdown'dan "Computers" optionunu secin
// arama motoruna "Asus" yazıp aratın
// ikinci urunun fotografını cekin
// ikinci urune tıklayın
// title'ının "ASUS" icerdigini test edin
// sayfayı kapatın


    @Test
    public void test01() throws IOException {
        // amazon sayfasına gidin
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

    // dropdown'dan "Computers" optionunu secin
        AmazonPage amazonPage = new AmazonPage();
        Select select = new Select(amazonPage.ddm);
        select.selectByVisibleText("Computers");

   // arama motoruna "Asus" yazıp aratin
        amazonPage.aramaKutusu.sendKeys("Asus"+ Keys.ENTER);
  // ikinci urunun fotografını cekin
       File  kayit =new File("target/ekranGoruntusu/kayit.Jpeg");
       File gecici = amazonPage.ikinciUrun.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(gecici,kayit);


  // ikinci urune tıklayın
        amazonPage.ikinciUrun.click();
  // title'ının "ASUS" icerdigini test edin
     String title = C01_DataProvider.Driver.getDriver().getTitle();
     Assert.assertTrue(title.contains("ASUS"));
   // sayfayı kapatın
        C01_DataProvider.Driver.closeDriver();


    }
}
