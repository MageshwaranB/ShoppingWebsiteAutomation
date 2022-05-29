package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader 
{
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path=null;
	
	public ExcelReader(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
	}
	public int getCellCount(String sheetName, int rowNum) throws IOException{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		int cellCount=sheet.getRow(rowNum).getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}
	public String getData(String sheetName, int rowNum, int colNum) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		String data;
		cell=sheet.getRow(rowNum).getCell(colNum);
		DataFormatter formatter=new DataFormatter();
		try {
			
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	public String[][] provideData( String path, String sheetName) {
		try {
			ExcelReader excel=new ExcelReader(path);
			int rowCount = excel.getRowCount(sheetName);
			int cellCount=excel.getCellCount(sheetName, 1);
			String data[][]=new String[rowCount][cellCount];
			for(int i=1;i<=rowCount;i++)
			{
				for(int j=0;j<cellCount;j++) {
					data[i-1][j]=excel.getData(sheetName, i, j);
				}
			}
			return data;
		}
		catch(IOException e) {
			return null;
		}
	}
}
