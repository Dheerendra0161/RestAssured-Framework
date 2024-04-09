package api.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	

	public static Object[][] getTestDataFromExcel(String sheetName, String filePath) {
		File excelFile = new File(filePath);

		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;

				}

			}

		}

		return data;

	}

	public static void writeDataToExcel(String filePath, String sheetName, Object[][] data) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(sheetName);

			for (int i = 0; i < data.length; i++) {
				XSSFRow row = sheet.createRow(i);
				for (int j = 0; j < data[i].length; j++) {
					XSSFCell cell = row.createCell(j);
					if (data[i][j] instanceof String) {
						cell.setCellValue((String) data[i][j]);
					} else if (data[i][j] instanceof Integer) {
						cell.setCellValue((Integer) data[i][j]);
					} else if (data[i][j] instanceof Boolean) {
						cell.setCellValue((Boolean) data[i][j]);
					}
				}
			}

			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			System.out.println("Data has been written to Excel.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getRowCount(String filePath, String sheetName) {
		try {
			File excelFile = new File(filePath);
			FileInputStream fisExcel = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum() + 1;
			workbook.close();
			fisExcel.close();
			return rowCount;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getColumnCount(String filePath, String sheetName) {
		try {
			File excelFile = new File(filePath);
			FileInputStream fisExcel = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int colCount = sheet.getRow(0).getLastCellNum();
			workbook.close();
			fisExcel.close();
			return colCount;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
		try {
			File excelFile = new File(filePath);
			FileInputStream fisExcel = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNum);
			XSSFCell cell = row.getCell(colNum);
			String cellData = "";
			if (cell != null) {
				CellType cellType = cell.getCellType();
				switch (cellType) {
				case STRING:
					cellData = cell.getStringCellValue();
					break;
				case NUMERIC:
					cellData = Double.toString(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					cellData = Boolean.toString(cell.getBooleanCellValue());
					break;
				default:
					cellData = "";
				}
			}
			workbook.close();
			fisExcel.close();
			return cellData;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\eCom\\Testdata\\TutorialsNinjaTestData.xlsx";
		String sheetName = "Login";
		Object[][] excelData = ExcelUtils.getTestDataFromExcel(sheetName, filePath);
		for (Object[] row : excelData) {
			for (Object cell : row) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		}

		System.out.println("***********************************************");
		System.out.println(ExcelUtils.getCellData(filePath, sheetName, 2, 0));
		System.out.println("***********************************************");
		System.out.println(ExcelUtils.getColumnCount(filePath, sheetName));
		System.out.println("***********************************************");
		System.out.println(ExcelUtils.getRowCount(filePath, sheetName));
		System.out.println("***********************************************");

		Object[][] dataToWrite = { { "Email", "Password" }, { "dheerendra123@gmail.com", "password123" },
				{ "neha.kumar456@gmail.com", "securepass456" }, { "akash.sharma789@gmail.com", "strongpass789" },
				{ "ananya.joshi101@gmail.com", "mypassword101" } };

		ExcelUtils.writeDataToExcel(filePath, sheetName, dataToWrite);

	}
}
