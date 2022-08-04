package Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class FetchDataFromExcel 
{
    public static HashMap<String, String> testDataFromExcel(String Path, String TestCaseName) throws InvalidFormatException, IOException {
    	HashMap<String, String> testData = new HashMap<String, String>();
    	FileInputStream fis = new FileInputStream(Path);
    	XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = null, columnNames = null;
        Boolean isFound = false;

        //Finding the corresponding test data for a test case
        for(int i=0; i <= sheet.getLastRowNum(); i++) {
        	row = sheet.getRow(i);
        	if(row.getCell(0).getStringCellValue().trim().equals(TestCaseName)){
        		isFound = true;
        		columnNames = sheet.getRow(i+1);
        		row = sheet.getRow(i+2);
        		break;
        	}
        }
        //Row will be put in a map for collection if found
        if(isFound) {
        	for(int i=0; i < row.getLastCellNum(); i++)
        		testData.put(columnNames.getCell(i).getStringCellValue().trim(), row.getCell(i).getStringCellValue().trim());
        	System.out.println("Test data for testcase " + TestCaseName + " collected: \n" + testData + "\n");
        	return testData;
        }
        else {
        	System.out.println("Test data for testcase " + TestCaseName + " is not found.\n");
        	return null;
        }
    }
}
