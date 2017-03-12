package courseSelection.excelToDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDB {
    
    
     
//    public static void main(String[] args) throws IOException {
//        List<String> readFromExcelFile = readFromExcelFile();
//    }
//    
//    private static List<String> readFromExcelFile() throws FileNotFoundException, IOException {
//        String excelFilePath = "E:\\MSC-AI\\project\\agentsystem\\studentData.xlsx";
//        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//         
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet firstSheet = workbook.getSheetAt(0);
//        Iterator<Row> iterator = firstSheet.iterator();
//        
//        List<String> insertQueryList = new ArrayList<>();
//        int index = 0;
//        while (iterator.hasNext()) {
//            //if(index == 0) continue;
//            Row nextRow = iterator.next();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//            String queryString = "INSERT INTO STUDENT_DATA ("
//                    + "name, subject1, subject2, subject3, district, zscore,"
//                    + "gender, age, olEnglish, olMaths, email, city, adress,"
//                    + "attempt, selected_course, university, distance, sujested_course,"
//                    + "salary, student_dataa) VALUES(";
//            int i=1;
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                System.out.println(cell.getCellType());
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        //System.out.print(cell.getStringCellValue());
//                        queryString = queryString + cell.getStringCellValue()+",";
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        //System.out.print(cell.getBooleanCellValue());
//                        queryString = queryString + cell.getBooleanCellValue()+",";
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        //System.out.print(cell.getNumericCellValue());
//                        queryString = queryString + cell.getNumericCellValue()+",";
//                        break;
//                    case Cell.CELL_TYPE_BLANK:
//                        if(i==6 || i==8 || i==14 || i==17 || i==19){
//                            queryString = queryString + 0 + ",";
//                        } else {
//                            queryString = queryString + "' ',";
//                        }
//                         
//                }
//                i++;
//                
//            }
//            System.out.print(" --------------------------------- ");
//            insertQueryList.add(queryString);
//            index++;
//            //System.out.println(index);
//        }
//         
//        workbook.close();
//        inputStream.close();
//        
//        return insertQueryList;
//    }
// 
}
