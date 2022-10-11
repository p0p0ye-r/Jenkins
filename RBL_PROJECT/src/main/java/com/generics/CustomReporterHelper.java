package com.generics;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cucumber.api.Scenario;

public class CustomReporterHelper {
	private static ArrayList<CustomReporterHelper> runnerScenarios = new ArrayList<CustomReporterHelper>();
	private String strExcelFilePath = "";
	private XSSFWorkbook workbook;
	private XSSFSheet summarySheet;
	private XSSFRow summaryCurrentRow;
	private XSSFSheet testResultSheet;
	private XSSFRow testResultSheetCurrentRow;
	private int intTestResultRowCounter = 0;
	// Pojo objPojo;
	private Scenario scenario;
	private Pojo exception;

	synchronized public static ArrayList<CustomReporterHelper> getException() {
		return runnerScenarios;
	}

	public CustomReporterHelper(Scenario scenario, Pojo baseTest) {
		this.scenario = scenario;
		this.exception = baseTest;
	}

	synchronized public void startExcelReport() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String reportDate = dateFormat.format(date).toString();
			if (strExcelFilePath.equals("")) {
				this.strExcelFilePath = System.getProperty("user.dir") + "/custom-reports/ExcelReports_" + reportDate
						+ ".xlsx";
			}
			File reportFile = new File(this.strExcelFilePath);
			String exception;
			if (!reportFile.exists()) {
				(new File(System.getProperty("user.dir") + "/custom-reports")).mkdirs();
				reportFile.createNewFile();
				exception = System.getProperty("user.dir") + "/src/test/resources/reportTemplate.xlsx";
				this.workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(exception));
				this.summarySheet = this.workbook.getSheet("TestSummary");
				this.summaryCurrentRow = this.summarySheet.getRow(1);
				this.testResultSheet = this.workbook.createSheet("TestResult");
				this.testResultSheetCurrentRow = this.testResultSheet.createRow(0);
				this.createHeaderRow(this.testResultSheetCurrentRow);
			} else {
				this.workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(this.strExcelFilePath));
				this.summarySheet = this.workbook.getSheet("TestSummary");
				this.summaryCurrentRow = this.summarySheet.getRow(1);
				this.testResultSheet = this.workbook.getSheet("TestResult");
			}

			this.workbook.setSheetOrder("TestResult", 0);
			this.workbook.setSheetOrder("TestSummary", 1);
			exception = "";
			for (CustomReporterHelper customReporterHelper : runnerScenarios) {
				boolean status = customReporterHelper.scenario.isFailed();
				this.intTestResultRowCounter = this.testResultSheet.getLastRowNum() + 1;
				this.testResultSheetCurrentRow = this.testResultSheet.createRow(this.intTestResultRowCounter);
				String executionStatus = "Pass";
				if (status) {
					executionStatus = "Fail";
				}
				this.addExecutionResultInTestResultSheet(customReporterHelper.scenario, executionStatus,
						customReporterHelper.exception);
				this.updateSummarySheet(executionStatus);
			}

			this.endExcelReport();
		} catch (

		Exception var18) {
			var18.printStackTrace();
		}

	}

	public void createHeaderRow(XSSFRow headerRow) {
		this.createHeaderCell(headerRow, 0, "Sr. No.");
		this.createHeaderCell(headerRow, 1, "Time Stamp");
		this.createHeaderCell(headerRow, 2, "TCID");
		this.createHeaderCell(headerRow, 3, "Module Path");
		this.createHeaderCell(headerRow, 4, "Feature File Name");
		this.createHeaderCell(headerRow, 5, "Specified Tags");
		this.createHeaderCell(headerRow, 6, "Result");
		this.createHeaderCell(headerRow, 7, "Exception");
		this.createHeaderCell(headerRow, 8, "Developer Name");
//		this.createHeaderCell(headerRow, 9, "Test Failed Step");
	}

	public void addExecutionResultInTestResultSheet(Scenario scenario, String failed, Pojo exception) {
		Collection<String> collection = scenario.getSourceTagNames();
		this.createSheetCellRightAlliged(this.testResultSheetCurrentRow, 0,
				String.valueOf(this.intTestResultRowCounter));
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 1, this.getCurrentDateTime());
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 2, scenario.getName());
		this.createSheetCell(this.testResultSheetCurrentRow, 3,
				scenario.getUri().substring(0, scenario.getUri().lastIndexOf("/")));
		this.createSheetCell(this.testResultSheetCurrentRow, 4,
				scenario.getUri().substring(scenario.getUri().lastIndexOf("/") + 1).replace(".feature", ""));
		this.createSheetCell(this.testResultSheetCurrentRow, 8, getResourceName(collection));
		this.createResultCell(this.testResultSheetCurrentRow, 6, failed);
		this.createSheetCell(this.testResultSheetCurrentRow, 7, exception.getCustomException());
		this.createSheetCell(this.testResultSheetCurrentRow, 5, collection.toString());
//		this.createSheetCell(this.testResultSheetCurrentRow, 9, exception.getTestFailedStep());
	}

	public String getResourceName(Collection<String> collection) {
		String resourcename = "Undefined";
		for (String string : collection)
			if (string.toLowerCase().startsWith("@resourcename")) {
				resourcename = string.replace("@ResourceName_", "");
				collection.remove(string);
				break;
			}
		return resourcename;
	}

	public void updateSummarySheet(String status) {
		int skippedCount;
		if (status.equalsIgnoreCase("pass")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(0).getNumericCellValue() + 0;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 0, String.valueOf(skippedCount));
		} else if (status.equalsIgnoreCase("fail")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(1).getNumericCellValue() + 1;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 1, String.valueOf(skippedCount));
		}
		skippedCount = (int) this.summaryCurrentRow.getCell(2).getNumericCellValue() + 1;
		this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 2, String.valueOf(skippedCount));
	}

	public void endExcelReport() throws IOException {
		this.autoSetColumnWidth();
		FileOutputStream fileOutputStream = new FileOutputStream(this.strExcelFilePath);
		this.workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	public void createSheetCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleLeftAlliged());
	}

	public void createSheetCellCenterAlliged(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleCenterAlliged());
	}

	public void createSheetCellCenterAlligedNumeric(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue((double) Integer.parseInt(value));
		cell.setCellStyle(this.getCellStyleCenterAlliged());
	}

	public void createSheetCellRightAlliged(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleRightAlliged());
	}

	public void createSheetPassStepCell(XSSFRow row, int cellNumber) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("PASS"));
		cell.setCellStyle(this.getPassCellStyle());
	}

	public void createResultCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		System.out.println("Result ****************:     " + value.toLowerCase().trim());
		String var5 = value.toLowerCase().trim();
		byte var6 = -1;
		switch (var5.hashCode()) {
		case -1988746795:
			if (var5.equals("fail - Application Side Issue")) {
				var6 = 3;
			}
			break;
		case -1643861444:
			if (var5.equals("fail - Application Freeze")) {
				var6 = 2;
			}
			break;
		case 3135262:
			if (var5.equals("fail")) {
				var6 = 1;
			}
			break;
		case 3433489:
			if (var5.equals("pass")) {
				var6 = 0;
			}
			break;
		case 154432742:
			if (var5.equals("fail - Tool Limitations")) {
				var6 = 7;
			}
			break;
		case 1534758069:
			if (var5.equals("fail - Assertion Error")) {
				var6 = 4;
			}
			break;
		case 1872143336:
			if (var5.equals("fail - UI Change/Element Changed")) {
				var6 = 5;
			}
			break;
		case 1879267419:
			if (var5.equals("fail - Developer Side Issue")) {
				var6 = 6;
			}
			break;
		case 2147444528:
			if (var5.equals("skipped")) {
				var6 = 8;
			}
		}

		switch (var6) {
		case 0:
			cell.setCellStyle(this.getPassCellStyle());
			break;
		case 1:
		case 2:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 3:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 4:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 5:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 6:
			cell.setCellStyle(this.getSeleniumSideFailCellStyle());
			break;
		case 7:
			cell.setCellStyle(this.getSeleniumSideFailCellStyle());
			break;
		case 8:
			cell.setCellStyle(this.getSkippedCellStyle());
		}

	}

	public void createHeaderCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getHeaderCellStyle());
	}

	 public void createWelcomeHeaderCell(XSSFRow row, int cellNumber, String
	 value) {
	 XSSFCell cell = row.createCell(cellNumber);
	 cell.setCellValue(new XSSFRichTextString(value));
	 cell.setCellStyle(this.getWelcomeCellStyle());
	 }

	private XSSFCellStyle getCellStyleLeftAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getCellStyleCenterAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		return cellStyle;
	}

	private XSSFCellStyle getCellStyleRightAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 3);
		return cellStyle;
	}

	private XSSFCellStyle getPassCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.GREEN);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getFailCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.RED);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getSeleniumSideFailCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.ORANGE);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getSkippedCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.LIGHT_GRAY);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getHeaderCellStyle() {
		XSSFFont headerFont = this.workbook.createFont();
		headerFont.setBoldweight((short) 700);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillForegroundColor((short) 22);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	public XSSFCellStyle getWelcomeCellStyle() {
		XSSFFont headerFont = this.workbook.createFont();
		headerFont.setBoldweight((short) 700);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillPattern((short) 1);
		XSSFColor myColor = new XSSFColor(Color.GREEN);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	 public void createSummaryLinkCell(XSSFRow row, int cellNumber, String value,
	 String nevigationSheet) {
	 XSSFCell cell = row.createCell(cellNumber);
	 cell.setCellValue(new XSSFRichTextString(value));
	 CreationHelper createHelper = this.workbook.getCreationHelper();
	 Hyperlink link = createHelper.createHyperlink(2);
	 link.setAddress("'" + nevigationSheet + "'!A1");
	 cell.setHyperlink(link);
	 cell.setCellStyle(this.getHlinkCellStyle());
	 }

	public XSSFCellStyle getHlinkCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		Font hlink_font = this.workbook.createFont();
		hlink_font.setUnderline((byte) 1);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_font.setBoldweight((short) 700);
		hlink_font.setFontName("Arial");
		cellStyle.setFont(hlink_font);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillForegroundColor((short) 22);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private String getCurrentDateTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		return sdfDate.format(now);
	}

	public void autoSetColumnWidth() {
		this.testResultSheet.autoSizeColumn(0);
		this.testResultSheet.autoSizeColumn(1);
		this.testResultSheet.autoSizeColumn(2);
		this.testResultSheet.autoSizeColumn(3);
		this.testResultSheet.autoSizeColumn(4);
		this.testResultSheet.autoSizeColumn(5);
		this.testResultSheet.autoSizeColumn(6);
		this.testResultSheet.autoSizeColumn(7);
		this.testResultSheet.autoSizeColumn(8);
		this.testResultSheet.autoSizeColumn(9);
		this.testResultSheet.autoSizeColumn(10);
		this.testResultSheet.autoSizeColumn(11);
		this.testResultSheet.autoSizeColumn(12);
		this.testResultSheet.autoSizeColumn(13);
	}
}