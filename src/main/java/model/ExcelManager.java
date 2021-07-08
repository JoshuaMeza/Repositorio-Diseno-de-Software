package model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelManager implements FileManager {
    @Override
    public ArrayList<String> read(String fileName) {
        ArrayList<String> output = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream( new File( fileName + ".xlsx" ));
            Workbook workbook = new XSSFWorkbook( fis );
            Sheet sheet = workbook.getSheetAt( 0 );

            for ( Row row : sheet ) {
                Iterator<Cell> cells = row.cellIterator();
                String line = "";

                while ( cells.hasNext() ) {
                    Cell cell = cells.next();
                    line += cell.getStringCellValue() + ",";
                }

                if ( !line.isEmpty() ) output.add( line.substring( 0, line.length() - 1 ) );
            }

            workbook.close();
            fis.close();
        } catch ( Exception e ) {
            output = new ArrayList<>();
        }

        return output;
    }

    @Override
    public boolean write(String fileName, ArrayList<String> data, boolean append) {
        boolean result = false;

        try {
            if ( data.size() > 0 ) {
                File file = new File( fileName + ".xlsx" );
                if ( !file.exists() || !append ) {
                    XSSFWorkbook excel = new XSSFWorkbook();
                    excel.createSheet( "Página 1" );
                    FileOutputStream fos = new FileOutputStream( fileName + ".xlsx" );
                    excel.write( fos );
                }

                FileInputStream fis = new FileInputStream( file );
                Workbook workbook = WorkbookFactory.create( fis );
                Sheet sheet = workbook.getSheetAt( 0 );
                int rowCount = append ? sheet.getLastRowNum() + 1 : 0;

                for ( String line : data ) {
                    Row row = sheet.createRow( rowCount++ );
                    int colCount = 0;
                    String[] splat = line.split(",");
                    for ( String piece : splat ) {
                        row.createCell( colCount++ ).setCellValue( piece );
                    }
                }

                fis.close();

                FileOutputStream fos = new FileOutputStream( fileName + ".xlsx" );
                workbook.write( fos );
                workbook.close();
                fis.close();
                result = true;
            }
        } catch ( Exception e ) {
            result = false;
        }

        return result;
    }
}
