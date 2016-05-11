package poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class Test1 {
	@Test
	public void testHSSF() throws Exception{
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row nRow = sheet.createRow(7);		//第8行
		Cell nCell = nRow.createCell(4);	//第5列
		nCell.setCellValue("judas");
		CellStyle titleStyle = wb.createCellStyle();
		Font titleFont = wb.createFont();
		
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short)24);
		titleStyle.setFont(titleFont);

		nCell.setCellStyle(titleStyle);
		OutputStream os = new FileOutputStream("f:\\Desktop\\aaa.xls");
		wb.write(os);
		os.flush();
		os.close();
	}
}
