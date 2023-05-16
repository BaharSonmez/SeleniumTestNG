package techproed.tests.day26_ExcelDataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.utilities.DataProviderUtils;

public class C02_DataProvider3 {


    @Test(dataProvider = "sehirVerileri", dataProviderClass = DataProviderUtils.class)
    public void test01(String ad, String bolge, String plaka) {
        System.out.println(ad + "  " + bolge + " " + plaka + " ");

    }

    @Test(dataProvider = "kullanicilar", dataProviderClass = DataProviderUtils.class)
    public void test2(String username, String password) {

    }

}

