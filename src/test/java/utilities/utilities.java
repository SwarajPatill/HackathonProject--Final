package utilities;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class utilities {
	

	 
	
	
	public static void writeInfoInColumn(String[] data,int colNum) throws FileNotFoundException {
        
		FileOutputStream file = new FileOutputStream(".\\TestData\\Data.xlsx");
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        
        XSSFSheet pm = workbook.createSheet("Popularmodels");
        
        

        
        // XSSFRow r1 = pm.createRow(0);
        // Create a font with bold style
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        // Create a cell style with the bold font
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

	          
	        
			
	         //XSSFSheet  = workbook.createSheet("PopularModels");
	         XSSFRow r1 = pm.createRow(0);
	         XSSFCell cell = r1.createCell(0);
	         cell.setCellValue("Popular Models");
	         
            cell.setCellStyle(style);
	         pm.autoSizeColumn(0);

	    	 for(int i = 0; i<data.length; i++) {
	    		 XSSFRow row = pm.createRow(i+1);
	    		 row.createCell(colNum).setCellValue(data[i]);
		         pm.autoSizeColumn(colNum);


	    	 }

	    	 
	    	 try {
				file.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	 FileOutputStream fileOut=null;
	    	 try {
				fileOut = new FileOutputStream(".\\TestData\\Data.xlsx");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	    	 try {
				workbook.write(fileOut);
				workbook.close();
		         file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
		        System.out.println("Excel file has been generated successfully!");

	    	
	    }
	
	
	public static void writeToExcel(Map<String, String[]> bikeUnder4La) throws IOException {
	    
	 	FileInputStream file = null;
	 	XSSFWorkbook workbook = null;
	 	
	 	
	 	file = new FileInputStream(".\\TestData\\Data.xlsx");
	 	
	 	workbook = new XSSFWorkbook(file);
	    XSSFSheet sheet = null;
	    try {
	    		sheet = workbook.createSheet("bikeDetails");
	    		
	    }
	    catch(Exception e) {
	    	sheet = workbook.getSheet("bikeDetails");
	    }

	    // Create bold font style
	    XSSFFont headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    XSSFCellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);

	    // Create header row with bold text
	    XSSFRow headerRow = sheet.createRow(0);
	    String[] headers = {"Manufacturing", "Bike Name", "Bike Price", "Launch Date"};
	    for (int i = 0; i < headers.length; i++) {
	        XSSFCell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        cell.setCellStyle(headerCellStyle);
	        sheet.autoSizeColumn(i);

	    }
	    //System.out.println(bikeUnder4La);
	    
	    
	    int rowNum = 1; // Start from the next row for data
	    for (Map.Entry<String, String[]> entry : bikeUnder4La.entrySet()) {

	        XSSFRow row = sheet.createRow(rowNum++);
	        
	        row.createCell(0).setCellValue("Honda"); // Assuming all bikes are Honda rowNum++
	        row.createCell(1).setCellValue(entry.getKey());
	        row.createCell(2).setCellValue(entry.getValue()[0]);
	        row.createCell(3).setCellValue(entry.getValue()[1]);

	    }

	    try {
	        FileOutputStream fileoutput = null;
	        fileoutput= new FileOutputStream(".\\TestData\\Data.xlsx");
	        workbook.write(fileoutput);
	        workbook.close();
	        file.close();
	        System.out.println("Excel file has been generated successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	 

}
