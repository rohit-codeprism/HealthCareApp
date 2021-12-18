package in.nit.rohit.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nit.rohit.entity.Specialization;

public class SpecializationPdfView extends AbstractPdfView{
	
	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
		HeaderFooter header = new HeaderFooter(new Phrase("SPECIALIZATION PDF VIEW"),false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+"(C) NareshIT, Page No #"),true);
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.setFooter(footer);
		//super.buildPdfMetadata(model, document, request);
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//download PDF with filename
		response.addHeader("Content-Disposition", "attachement;fileName=Spec.pdf");
		
		
		// Read data from controller
		List<Specialization> list = (List<Specialization>) model.get("list");
		
		// 1. Create Element
		//Font(Family,size,style,color)
		Font titleFont = new Font(Font.TIMES_ROMAN,30,Font.BOLD,Color.red);
		Paragraph title = new Paragraph("SPECIALIZATION DATA",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(20.0f);
		title.setSpacingBefore(25.0f);
		//title.setSpacingAfter(getViewerPreferences());
		//Paragraph title = new Paragraph("Test Sample Output!!!");
		document.add(title);
		
		Font tableHead  = new Font(Font.TIMES_ROMAN,20,Font.BOLD,Color.orange);
		PdfPTable table =  new PdfPTable(4); // coulmn
		table.addCell(new Phrase("ID",tableHead));
		table.addCell(new Phrase("CODE",tableHead));
		table.addCell(new Phrase("NAME",tableHead));
		table.addCell(new Phrase("NOTE",tableHead));
		
		for(Specialization spec : list)
		{
			table.addCell(spec.getId().toString());
			table.addCell(spec.getSpecCode().toString());
			table.addCell(spec.getSpecName().toString());
			table.addCell(spec.getSpecNote().toString());
		}
		// add to document
		document.add(table);
		
		
		
	}
	
	
	
	

}
