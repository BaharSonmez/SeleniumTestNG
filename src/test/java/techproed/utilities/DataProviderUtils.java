package techproed.utilities;

import org.testng.annotations.DataProvider;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;

import java.io.FileNotFoundException;

public class DataProviderUtils {
    @DataProvider
    public static  Object[][] sehirVerileri() {
        return new Object[][]{{"Ankara","icAanadolu","06"},{"Izmir","Ege","35"},{"Diyarbakir","Dogu","21"}};
    }

    @DataProvider
    public Object[][] kullanicilar() {
        return new Object[][]{{"Erol","6537292910"},{"Nuri","235680696"},{"Gul","1267893"},{"Sefa","2345692"}};
    }

    @DataProvider
    public Object[][] blueRental() throws FileNotFoundException {
     ExcelUtils excelUtils = new ExcelUtils("src/test/java/resources/mysmoketestdata.xlsx ","customer_info");
        return excelUtils.getDataArray();



    }
}
