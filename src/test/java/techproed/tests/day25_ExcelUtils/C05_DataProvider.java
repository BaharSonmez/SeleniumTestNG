package techproed.tests.day25_ExcelUtils;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.pages.GooglePage;
import techproed.utilities.ConfigReader;
import techproed.utilities.DataProviderUtils;
import techproed.utilities.Driver;

import java.io.FileNotFoundException;
import java.util.Arrays;


public class C05_DataProvider {



         /*
           sam.walker@bluerentalcars.com	c!fas_art
           kate.brown@bluerentalcars.com	tad1$Fas
           raj.khan@bluerentalcars.com	v7Hg_va^
           pam.raymond@bluerentalcars.com	Nga^g6!
           Verileri kullanarak bluerentalcar sayfasına login olalim

            */

    @DataProvider
    public static Object[][] blueRental() {
        return new Object[][]{{"sam.walker@bluerentalcars.com","c!fas_art"},{" kate.brown@bluerentalcars.com","tad1$Fas"}};
    }
        @Test(dataProvider = "blueRental")
         public void testdataprovider(String email, String password){
           Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));

            BlueRentalPage blueRentalPage = new BlueRentalPage();
            blueRentalPage.login.click();//login butonuna basti
            blueRentalPage.email.sendKeys(email, Keys.TAB,password,Keys.ENTER);
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ReusableMethods.bekle(3);
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.closeDriver();
    }


    public static class C01_DataProvider {

        @DataProvider
        public static Object[][] kullanicilar() {
            return new Object[][]{{ConfigReader.getProperty("email1"),ConfigReader.getProperty("password1")},
                                   {ConfigReader.getProperty("email2"),ConfigReader.getProperty("password2")}};
        }

        @Test(dataProvider = "kullanicilar")
        public void test1(String email, String password) {

            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));

            BlueRentalPage blueRentalPage = new BlueRentalPage();
            blueRentalPage.login.click();//login butonuna basti
            blueRentalPage.email.sendKeys(email, Keys.TAB, password, Keys.ENTER);

            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ReusableMethods.bekle(3);
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.closeDriver();

        }

        }

    public static class C02_DataProvider {
        @Test(dataProvider = "sehirVerileri",dataProviderClass = DataProviderUtils.class)
        public void test1(String  ad, String bolge,String plaka) {
            System.out.println(ad+ " "+bolge+" "+plaka);
        }

        @Test(dataProvider = "kullanicilar",dataProviderClass =DataProviderUtils.class )
        public void test2(String username, String password) {
            System.out.println(username + " "+ password);
        }
    }

    public static class C03_DataProviderExcel {
        @Test
        public void test1() throws FileNotFoundException {
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ExcelUtils excelUtils = new techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ExcelUtils("src/test/java/resources/mysmoketestdata.xlsx","customer_info");
            System.out.println(Arrays.deepToString(excelUtils.getDataArray()));
        }

        @Test(dataProvider = "blueRental",dataProviderClass = DataProviderUtils.class)
        public void test2(String email, String password) {
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));

            BlueRentalPage blueRentalPage = new BlueRentalPage();
            blueRentalPage.login.click();//login butonuna basti
            blueRentalPage.email.sendKeys(email, Keys.TAB,password,Keys.ENTER);
            //reusableMethods.bekle(3);
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.closeDriver();

        }
    }

    public static class C04_DataProvider {

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
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("googleUrl"));

            // {"Volvo"},{"Mercedes"},{"Audi"},{"Honda"},{"Toyota"},{"Opel"},{"BMW"} araclarini aratiniz
            //simdi google arama kutusaunu locate edip
            GooglePage googlePage = new GooglePage();

            googlePage.aramaKutusu1.sendKeys(araclar, Keys.ENTER);

            //her aratmadan sonra sayfa resmi aliniz
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ReusableMethods.tumSayfaResmi();
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ReusableMethods.bekle(3);
            techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.closeDriver();

        }
    }
}

