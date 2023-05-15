package techproed.tests.day24_Properties_Pages;

import org.testng.annotations.Test;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

public class C01_ClassWork {
    @Test
    public void test1() {
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("amazon_Url"));
        C01_DataProvider.Driver.closeDriver();
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("techproed_Url"));
        C01_DataProvider.Driver.closeDriver();
    }
}
