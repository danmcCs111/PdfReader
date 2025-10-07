package PdfReader;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader 
{
	public static void main(String [] args)
	{
		File file = new File("./my_sql-refman-8.4-en.a4.pdf");
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
		for(int i = 0; i < document.getNumberOfPages(); i++)
		{
//			PDPage pdPage = document.getPage(i);
			try {
				PDFTextStripper pdfStripper = new PDFTextStripper();
				String text = pdfStripper.getText(document);
				System.out.println(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
