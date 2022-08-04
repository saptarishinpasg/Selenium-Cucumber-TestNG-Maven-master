package Common;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class FetchDataFromCSV 
{
	public static HashMap<String, String> testDataFromCsv(String Path, String TestCaseName) throws InvalidFormatException, IOException, CsvException {
    	HashMap<String, String> testData = new HashMap<String, String>();
		CSVReader reader = new CSVReader(new FileReader(Path));
		String [] nextLine;
		Boolean isFound = false;
		int row = 0;
		
		outerloop:
		while ((nextLine = reader.readNext()) != null) {
			row++;
			for(String token : nextLine) { 
				if(token.contains(TestCaseName)) {
					isFound = true;
					break outerloop;
				}
			}
		}
		
		//GetColumns
		if(isFound) {
			int rowKeys = row + 1, rowValues = row+2;
			List <String> columnNames = new ArrayList<>();
			
			while ((nextLine = reader.readNext()) != null) {
				row++;
				if(row == rowKeys) {
					for(String token : nextLine) { 
						if(token.trim() != "") {
							columnNames.add(token.trim());
							testData.put(token.trim(), "");
						}
					}
				} else if(row == rowValues) {
					int index = 0;
					for(String token : nextLine) { 
						if(token.trim() != "") {
							testData.put(columnNames.get(index), token.trim());
							index++;
						}		
					}
				}
			}
			System.out.println("Test data for testcase " + TestCaseName + " collected: \n" + testData + "\n");
			return testData;
		} else {
        	System.out.println("Test data for testcase " + TestCaseName + " is not found.\n");
        	return null;
		}
    }
}
