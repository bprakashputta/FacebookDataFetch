package facebook.lead.push.by.pageid.global;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelFileData {

    public static String[][] getExcelData(String fileName) {
        String[][] dataTable = null;
        File file = new File(fileName);
        try {
            // Create a file input stream to read Excel workbook and worksheet
            FileInputStream xlfile = new FileInputStream(file);
            XSSFWorkbook xlwb = new XSSFWorkbook(xlfile);
            XSSFSheet xlSheet = xlwb.getSheet("Sheet1");

            // Get the number of rows and columns
            int numRows = xlSheet.getLastRowNum() + 1;
            int numCols = xlSheet.getRow(0).getLastCellNum();

            // Create double array data table - rows x cols
            // We will return this data table
            dataTable = new String[numRows][numCols];

            // For each row, create a XSSFRow, then iterate through the "columns"
            // For each "column" create an XSSFCell to grab the value at the specified cell (i,j)
            for (int i = 0; i < numRows; i++) {
                XSSFRow xlRow = xlSheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    XSSFCell xlCell = xlRow.getCell(j);
                    if(xlCell.toString() != null){
                        dataTable[i][j] = xlCell.toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    private static String[] getExcelDataMethod(String filename) {
        Workbook wb = null;
        String[] dataTable = null;
        try {
            wb = WorkbookFactory.create(new FileInputStream(filename));
            Sheet sheet = wb.getSheetAt(0);
            for (int j=0; j< sheet.getLastRowNum() + 1; j++) {
                Row row = sheet.getRow(j);
                if(row.getCell(0) != null){
                    //get first cell
                    Cell cell = row.getCell(0);
                    // Printing Stuff
                    dataTable[j] = cell.toString();
                }else {
                    break;
                }
            }
            return dataTable;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String[] getPageIdArray(String[][] excelData){

        String[] pageid = new String[excelData.length-1];
        int p = 0;
        for(int i=1;i<excelData.length;i++){
//           String page = excelData[i][0].substring(1,excelData[i][0].length()-1);
            String myString = excelData[i][0];
            myString = myString.substring(1, myString.length());
//           System.out.println(myString);
            pageid[i-1] = myString;
//            if(myString != null){
//                pageid[p] = myString;
//                p++;
//            }
        }
//        for (int i=0; i<pageid.length; i++){
//            System.out.println(pageid[i]);
//        }
        return pageid;
    }

    public static void main(String[] args) {
       String[][] excelData = getExcelData("/home/putta.prakash/Sokrati/facebook_lead_push_by_pageid/src/main/java/facebook/lead/push/by/pageid/docs/pageid_data.xlsx");
//        String[] excelData = getExcelDataMethod("/home/putta.prakash/Sokrati/facebook_lead_push_by_pageid/src/main/java/facebook/lead/push/by/pageid/docs/pageid_sheet.xlsx");
       String[] pageId = getPageIdArray(excelData);

        for(int i=0;i<pageId.length; i++){
            System.out.println(pageId[i]);
        }
    }

}
