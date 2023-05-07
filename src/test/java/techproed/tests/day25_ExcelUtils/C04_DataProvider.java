package techproed.tests.day25_ExcelUtils;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.pages.GooglePage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;

import java.sql.SQLOutput;

public class C04_DataProvider {

    /*
        Data Provider , bir cok veriyi test caselere loop kullanmadan aktarmak icin kullanilir.
        TestNG den gelen bir ozelliktir.
        2boyutlu bir Object Array return eder.
        DDT(DATA DRIVING TESTING) icin kullanilir. Yani birden fazla veriyi test caselerde ayni anda kullanmak
        icin kullanilir
        Kullanimi icin @Test notasyonundan sonra parametre olarak Data provider yazilir ve String bir isim belirtilir
        Bu belirttigimiz isim ile @DataProvider notasyonu ile bir method olusturulur
         */
    @DataProvider(name = "googleTest")
    public static Object[][] urunler() {
        return new Object[][]{{"Volvo"},{"Mercedes"},{"Audi"},{"Honda"},{"Toyota"},{"Opel"},{"BMW"}};
    }
  /*
  Eger farkli bir test methodu icin ayni dataProvider methodu kullanilacaksa (@DataProvider(name ="googleTest")
  seklinde dataprovider notasyonundan name parametresine yeni olusturdugumuz methodun adini yazariz
   */

    @Test(dataProvider = "urunler")
    public void testdataprovider(String data) { //Datap.'daki verileri alabilmek icin
                                                 // Test methodumuza String bir parametre atamasi yapariz
        System.out.println(data);

    }

    @Test(dataProvider = "googleTest")
    public void googletest(String araclar) {
       //google sayfasina gidiniz
       // Driver.getDriver().get("https://google.com");
        Driver.getDriver().get(ConfigReader.getProperty("googleUrl"));

        // {"Volvo"},{"Mercedes"},{"Audi"},{"Honda"},{"Toyota"},{"Opel"},{"BMW"} araclarini aratiniz
        //simdi google arama kutusaunu locate edip
        GooglePage googlePage = new GooglePage();

        googlePage.aramaKutusu1.sendKeys(araclar, Keys.ENTER);

        //her aratmadan sonra sayfa resmi aliniz
        ReusableMethods.tumSayfaResmi();
        ReusableMethods.bekle(2);
        Driver.closeDriver();

    }
}
