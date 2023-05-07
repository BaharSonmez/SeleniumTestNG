package techproed.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
     private Workbook workbook;
     private Sheet sheet;
    private String path;
    //Constrictor: Excel path'ine ve Excel'deki sayfaya ulasmak icin 2 parametreli constructor olusturduk

    public ExcelUtils(String path, String sheetName) throws FileNotFoundException {
        this.path = path;
        FileInputStream fis = new FileInputStream(path);
        try {
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    //Satir ve sutun sayilari girildiginde , o hucredeki veriyi return eder

    public String getCellData(int rowNum, int colNum){
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        return cell.toString();


    }
   //Exceldeki satir sayisini return eder
    public int rowCount(){
        return sheet.getLastRowNum();

    }
    //Exceldeki sutun sayisini return eder
    public int columnCount(){
        return sheet.getRow(0).getLastCellNum();
    }
}
