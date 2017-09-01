package com.spedia.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcelFile {
	/**
	 * Create a jpeg image for chart by reading xsl datasheet it will take xsl
	 * sheet path as argument
	 * 
	 * @throws IOException
	 */

	public static Sheet readFromExcelSheet(String xslSheetPath)
			throws IOException {
		/*
		 * create a FileInputStream input object from xslSheetPath
		 */
		
		/**
		 * HSSFWorkbook object reads the full Excel document. we will manipulate
		 * it
		 * 
		 */
		

		FileInputStream file = new FileInputStream(new File(xslSheetPath));
		
		//Get the workbook instance for XLS file 
		 org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * get sheet 0 of xsl document
		 */
		 org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		
		/**
		 * we will have load the bar chart data in my_bar_chart_dataset Iterate
		 * using iterator object
		 */
		
		return sheet;
		
		
	}
	
}
