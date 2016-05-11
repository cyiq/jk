package poi;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class TestMore100 {
	public static void main(String[] args) throws Exception {
		TestMore100 tm = new TestMore100();
		tm.jdbcex(true);
	}

	public void jdbcex(boolean isClose) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException, InterruptedException {
		String xlsFile = "f:/Desktop/test100.xlsx";					//输出文件
		Workbook wb = new SXSSFWorkbook(100);				//创建excel文件，内存只有100条记录【关键语句】
		Sheet sheet = wb.createSheet("我的第一个工作簿");		//建立新的sheet对象

		Row nRow = null;
		Cell nCell   = null;

		//使用jdbc链接数据库
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
		String user = "root";
		String password = "cyiq";
		
		Connection conn = DriverManager.getConnection(url, user,password);   
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);   

		String sql = "select id,breast,adipocytes,Negative from customer limit 100000";   //100万测试数据
		ResultSet rs = stmt.executeQuery(sql);  
		
		
		long  startTime = System.currentTimeMillis();	//开始时间
		System.out.println("strat execute time: " + startTime);
		//context
		int rowNo = 0;
		int colNo = 0;
		while(rs.next()) {
			colNo = 0;
			nRow = sheet.createRow(rowNo++);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(rs.getString(colNo));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(rs.getString(colNo));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(rs.getString(colNo));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(rs.getString(colNo));
			
			if(rowNo%100==0){
				System.out.println("row no: " + rowNo);
			}
			
			Thread.sleep(1);			//休息一下，防止对CPU占用
		}
		
		long finishedTime = System.currentTimeMillis();	//处理完成时间
		System.out.println("finished execute  time: " + (finishedTime - startTime)/1000 + "m");
		
		
		FileOutputStream fOut = new FileOutputStream(xlsFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
		
		long stopTime = System.currentTimeMillis();		//写文件时间
		System.out.println("write xlsx file time: " + (stopTime - startTime)/1000 + "s");
		
		if(isClose){
			this.close(rs, stmt, conn);
		}
	}
	
	//close resource
	private void close(ResultSet rs, Statement stmt, Connection conn ) throws SQLException{
		rs.close();   
		stmt.close();   
		conn.close(); 
	}	
	
}