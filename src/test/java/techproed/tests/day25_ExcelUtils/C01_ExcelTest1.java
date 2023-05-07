package techproed.tests.day25_ExcelUtils;

import org.testng.annotations.Test;
import techproed.utilities.ExcelUtils;

import java.io.FileNotFoundException;

public class C01_ExcelTest1 {
    /*
    Eger bir veriyi (datayi) .properties dosyasindan almak istiyorsak , ConfigReader classindan getProperty()
    methodu kullanarak  .properties dosyamiza girdigimiz key degerini belirtiriz ve bize buy key degerinin
    value sunu dondurur.

     Eger bir veriyi(datayi) excel dosyasindan almak istiyorsak, olusturmus oldugumuz ExcelUtils class'sindaki
     methodlari kullanarak istedigimiz veriyi alabiliriz...
     */


    @Test
    public void excelTest1() throws FileNotFoundException {
        String path = "src/test/java/resources/mysmoketestdata.xlsx";
        String sayfa = "customer_info";
        ExcelUtils excelUtils = new ExcelUtils(path,sayfa);
        System.out.println(excelUtils.getCellData(1, 0));
        String email = excelUtils.getCellData(1,0);
        String password = excelUtils.getCellData(1,1);
        System.out.println(email+" "+password);



    }
}
