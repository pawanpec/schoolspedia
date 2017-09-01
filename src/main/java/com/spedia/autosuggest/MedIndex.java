package com.spedia.autosuggest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.omg.CORBA.INTF_REPOS;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.spedia.utils.ReadExcelFile;
import com.spedia.utils.SchoolsConstants;

public class MedIndex {
	/**
	 * path where index will be created.
	 */
	/**
	 * will write the documents into the file system.
	 */
	private static IndexWriter writer = null;
	/**
	 * contains the total documents indexed count.
	 */
	private static long sizeCounter = 0;
	
	public static String INDEX_PATH = "/var/www/spedia/medical";
	public static String xslSheetPath="/home/pawan/POC/medicine.xls";
	public static final String[] ENGLISH_STOP_WORDS = { "a", "an", "and",
			"are", "as", "at", "be", "but", "by", "for", "if", "into", "is",
			"no", "not", "of", "on", "or", "such", "that", "the", "their",
			"then", "there", "these", "they", "this", "to", "was", "will",
			"with" };

	/**
	 * 
	 * @return Analyzer
	 */

	public static Analyzer getAnalyzer() {
		return  new StandardAnalyzer(Version.LUCENE_4_10_3);
	}

	/**
	 * initializes the Index writers
	 */
	public static void initializeWriter() {
		try {
			FSDirectory dir = FSDirectory.open(new File(INDEX_PATH));
			IndexWriterConfig config = new IndexWriterConfig(
					Version.LUCENE_4_10_3, getAnalyzer());
			writer = new IndexWriter(dir, config);
		} catch (Exception ioe) {
			System.out
					.println("SEVERE EXCEPTION OCCURRED WHILE INITIALIZING INDEX WRITER.............TERMINATING!!!");
			System.exit(1);
		}

	}

	/**
	 * 
	 * @param tokens
	 *            : String [] of values to be indexed.
	 * @throws IOException
	 */
	public static void insertIntoIndex(Map medData) throws IOException {
		String code = (String) medData.get(1);
		String name = (String) medData.get(2);
		String unit = (String) medData.get(3);
		unit=unit.replace("'s", "");
		String price = (String) medData.get(4);
		Document doc = new Document();
		doc.add(new StringField("code", code, Field.Store.YES));
		doc.add(new StringField("name", name.toLowerCase(), Field.Store.YES));
		doc.add(new StringField("unit", unit, Field.Store.YES));
		doc.add(new StringField("price", price, Field.Store.YES));
		if (doc != null) {
			writer.addDocument(doc);
			sizeCounter++;
		}

	}

	public static void main(String[] args) {
		try {
			initializeWriter();
			Iterator<Row> rowIterator = ReadExcelFile.readFromExcelSheet(xslSheetPath).iterator();
			/**
			 * Iterator through all row data and fill chart data
			 */
			// Iterate for all the rows
			while (rowIterator.hasNext()) {
				// Read row from excel documents
				Row row = rowIterator.next();
				Iterator<Cell>	cells = row.cellIterator();
				Map<Integer,String> cellData=new HashMap<Integer, String>();
				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();
					// Read cell within the row
					String val="";
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						Double d=cell.getNumericCellValue();
						val=d.intValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						val+=cell.getStringCellValue();
						break;
					default:
						break;
					}
				    cellData.put(cell.getColumnIndex(), val);
				}
				insertIntoIndex(cellData);
			}
			writer.commit();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// DO SOMETHING HERE.
		} finally {
		}
		
		System.out.println("Total Documents Indexed = " + sizeCounter);
		System.out.println("ENDING THE PROCESS......>>>>>>>.");
	}
}
