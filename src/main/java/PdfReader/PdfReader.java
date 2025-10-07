package PdfReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader 
{
	
	private static final String 
		PDF_FILENAME = "./my_sql-refman-8.4-en.a4.pdf",
		TEXT_FILENAME = "./mysql-refman-text.txt";
	
	public static void main(String [] args)
	{
		File file = new File(PDF_FILENAME);
		try {
			PDDocument document = Loader.loadPDF(file);
			System.out.println("PDF loaded"); 
			printPages(document);
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void printPages(PDDocument document)
	{
		try {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			writeToFile(text, TEXT_FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeToFile(String content, String textFilename)
	{
		try {
			FileWriter writer = new FileWriter(textFilename);
			writer.write(content); // Write content to the file
			writer.close(); // Close the writer to release resources
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	
	private static void saveDocument(PDDocument document, String location)
	{
		try {
			document.save(location);
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
