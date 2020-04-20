package reusability;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	ReusableMethods res = new ReusableMethods();
	static String path;
	String timeStamp = res.newDateText();
	String sheetname = "data";
	Workbook workbook;

	public Utilities() {
		try {
			path = res.readConfig("pathforExcelReport");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String screenCapture(WebDriver driver) throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage src = ImageIO.read(scrFile);
		BufferedImage convertedImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		convertedImg.getGraphics().drawImage(src, 0, 0, null);

		File output = new File("compressed.jpeg");
		OutputStream out = new FileOutputStream(output);

		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(out);
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();
		if (param.canWriteCompressed()) {
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.5f);
		}

		writer.write(null, new IIOImage(convertedImg, null, null), param);

		out.close();
		ios.close();
		writer.dispose();
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(output);
			byte[] bytes = new byte[(int) output.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.getEncoder().encode(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "data:image/dib;base64," + encodedBase64;
	}


	public void createExcelReport() throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			FileUtils.touch(file);
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetname);
			Row rowhead = sheet.createRow(0);
			CellStyle style = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(IndexedColors.GREEN.getIndex());
			style.setFont(headerFont);
			rowhead.createCell(0).setCellValue("Feature");
			rowhead.createCell(1).setCellValue("Auto Generated Email ID");
			rowhead.createCell(2).setCellValue("Auto Generated userName");
			rowhead.createCell(3).setCellValue("Order Number");
			for (int i = 0; i <= 3; i++) {
				sheet.autoSizeColumn(i);
				rowhead.getCell(i).setCellStyle(style);
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		}
	}

	public Sheet getExcel() throws IOException {
		FileInputStream fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		return sheet;
	}

	public void writeExcel() throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	public void deleteSheet() throws IOException {
		File file = new File(path);
		if (file.exists()) {
			Sheet sheet = getExcel();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i) != null) {
					sheet.removeRow(sheet.getRow(i));
				}
			}
			writeExcel();
		}
	}

}
