package techproed.utilities;




        import com.aventstack.extentreports.ExtentReports;
        import com.aventstack.extentreports.ExtentTest;
        import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
        import org.apache.commons.io.FileUtils;
        import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
        import org.openqa.selenium.*;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.Select;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;

        import java.io.File;
        import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.time.Duration;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

    public class ExcelUtils {
        private Workbook workbook;
        private Sheet sheet;
        private String path;
        //Constuctor: Excel path'ine ve Excel'deki sayfaya ulaşmak için 2 parametreli cons. oluşturduk
        public ExcelUtils(String path,String sheetName){
            this.path = path;
            try {
                FileInputStream fis = new FileInputStream(path);
                workbook = WorkbookFactory.create(fis);
                sheet = workbook.getSheet(sheetName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //Satir ve sütun sayilari girildiğinde, o hücredeki veriyi return eder
        public String getCellData(int rowNum,int colNum){
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            return cell.toString();
        }
        //Exceldeki satir sayisini return eder
        public int rowCount(){
            return  sheet.getLastRowNum();
        }
        //Exceldeki sütun sayisini return eder
        public int columnCount(){
            return sheet.getRow(0).getLastCellNum();
        }
        //Exceldeki dataları başlık olmadan alabilmek için 2 boyutlu bir array method oluşturalım
        public String[][] getDataArray() {
            String[][] data = new String[rowCount()][columnCount()];
            for (int i = 1; i <=rowCount() ; i++) {
                for (int j = 0; j < columnCount(); j++) {
                    String value = getCellData(i,j);
                    data[i-1][j]=value;
                }
            }
            return data;
        }
        //==============Sutun isimlerini verir==================//
        public List<String> getColumnsNames() {
            List<String> columns = new ArrayList<>();
            for (Cell cell : sheet.getRow(0)) {
                columns.add(cell.toString());
            }
            return columns;
        }
        //=========Deger, Satir, Sutun girindiginde, O satır ve sutuna girilen veriyi ekler===============//
        public void setCellData(String value, int rowNum, int colNum) {
            try {
                sheet.getRow(rowNum).createCell(colNum).setCellValue(value);
                FileOutputStream fos = new FileOutputStream(path);
                workbook.write(fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //    Bu metot ustdeki metotla birlikde calisir. Overload eder. Parametreleri farklidir
        public void setCellData(String value, String columnName, int row) {
            int column = getColumnsNames().indexOf(columnName);
            setCellData(value, row, column);
        }

        public static class ReusableMethods {

            protected static ExtentReports extentReports;
            protected static ExtentHtmlReporter extentHtmlReporter;
            protected static ExtentTest extentTest;


            //HARD WAIT METHOD
            public static void bekle(int saniye) {
                try {
                    Thread.sleep(saniye * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //Alert ACCEPT
            public static void alertAccept() {
                C01_DataProvider.Driver.getDriver().switchTo().alert().accept();
            }

            //Alert DISMISS
            public static void alertDismiss() {
                C01_DataProvider.Driver.getDriver().switchTo().alert().dismiss();
            }

            //Alert getText()
            public static void alertText() {
                C01_DataProvider.Driver.getDriver().switchTo().alert().getText();
            }

            //Alert promptBox
            public static void alertprompt(String text) {
                C01_DataProvider.Driver.getDriver().switchTo().alert().sendKeys(text);
            }

            //DropDown VisibleText
            /*
                Select select2 = new Select(gun);
                select2.selectByVisibleText("7");

                //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
             */
            public static void ddmVisibleText(WebElement ddm, String secenek) {
                Select select = new Select(ddm);
                select.selectByVisibleText(secenek);
            }

            //DropDown Index
            public static void ddmIndex(WebElement ddm, int index) {
                Select select = new Select(ddm);
                select.selectByIndex(index);
            }

            //DropDown Value
            public static void ddmValue(WebElement ddm, String secenek) {
                Select select = new Select(ddm);
                select.selectByValue(secenek);
            }

            //SwitchToWindow1
            public static void switchToWindow(int sayi) {
                List<String> tumWindowHandles = new ArrayList<String>(C01_DataProvider.Driver.getDriver().getWindowHandles());
                C01_DataProvider.Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
            }

            //SwitchToWindow2
            public static void window(int sayi) {
                C01_DataProvider.Driver.getDriver().switchTo().window(C01_DataProvider.Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
            }
            //EXPLICIT WAIT METHODS

            //Visible Wait
            public static void visibleWait(WebElement element, int sayi) {
                WebDriverWait wait = new WebDriverWait(C01_DataProvider.Driver.getDriver(), Duration.ofSeconds(sayi));
                wait.until(ExpectedConditions.visibilityOf(element));

            }

            //VisibleElementLocator Wait
            public static WebElement visibleWait(By locator, int sayi) {
                WebDriverWait wait = new WebDriverWait(C01_DataProvider.Driver.getDriver(), Duration.ofSeconds(sayi));
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            }

            //Alert Wait
            public static void alertWait(int sayi) {
                WebDriverWait wait = new WebDriverWait(C01_DataProvider.Driver.getDriver(), Duration.ofSeconds(sayi));
                wait.until(ExpectedConditions.alertIsPresent());

            }

            //Tüm Sayfa ScreenShot
            public static void tumSayfaResmi() {
                String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
                String dosyaYolu = "TestOutput/screenshot/screenshot" + tarih + ".png";
                TakesScreenshot ts = (TakesScreenshot) C01_DataProvider.Driver.getDriver();
                try {
                    FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //WebElement ScreenShot
            public static void webElementResmi(WebElement element) {
                String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
                String dosyaYolu = "TestOutput/screenshot/webElementScreenshot" + tarih + ".png";

                try {
                    FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //ExtentReport
            public static void extentReport() {
                extentReports = new ExtentReports();
                String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
                String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
                extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
                extentReports.attachReporter(extentHtmlReporter);

                //Raporda gözükmesini istediğimiz bilgiler için
                extentReports.setSystemInfo("Browser", "Chrome");
                extentReports.setSystemInfo("Tester", "Erol");
                extentHtmlReporter.config().setDocumentTitle("Extent Report");
                extentHtmlReporter.config().setReportName("Smoke Test Raporu");
            }

            //WebTable
            public static void printData(int satir, int sutun) {
                WebElement satirSutun = C01_DataProvider.Driver.getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + satir + "]//td[" + sutun + "]"));
                System.out.println(satirSutun.getText());
            }

            //Click Method
            public static void click(WebElement element) {
                try {
                    element.click();
                } catch (Exception e) {
                    JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                    js.executeScript("arguments[0].click();", element);
                }
            }

            //JS Scroll
            public static void scroll(WebElement element) {
                JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                js.executeScript("arguments[0].scrollIntoView(true);", element);
            }

            //JS Sayfa Sonu Scroll
            public static void scrollEnd() {
                JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            }

            //JS Sayfa Başı Scroll
            public static void scrollHome() {
                JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
            }

            //JS SendKeys
            public static void sendKeysJS(WebElement element, String text) {
                JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                js.executeScript("arguments[0].value='" + text + "'", element);
            }

            //JS SendAttributeValue
            public static void sendAttributeJS(WebElement element, String text) {
                JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
            }

            //JS GetAttributeValue
            public static void getValueByJS(String id, String attributeName) {
                JavascriptExecutor js = (JavascriptExecutor) C01_DataProvider.Driver.getDriver();
                String attribute_Value = (String) js.executeScript("return document.getElementById('" + id + "')." + attributeName);
                System.out.println("Attribute Value: = " + attribute_Value);
            }


                }
    }

