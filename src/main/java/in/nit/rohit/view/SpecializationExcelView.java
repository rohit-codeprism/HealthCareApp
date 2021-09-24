package in.nit.rohit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import in.nit.rohit.entity.Specialization;

public class SpecializationExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 //1. Define your own excel file name
		response.addHeader("content-Disposition","attachment;filename=SPECS.xls");
		//2. Read Data given by controller
		    // List<Specialization> list = (List<Specialization>)model.get("list");
		    @SuppressWarnings("unchecked")
			List<Specialization> list =  (List<Specialization>) model.get("list");
		     
		//3. create on sheet
		     Sheet sheet = workbook.createSheet("SPECIALIZATION");
		//4. create row#0 as header
		     setHead(sheet);
		// create row#1 from List<T>
		     setBody(sheet,list);
		// TODO Auto-generated method stub

	}

	private void setBody(Sheet sheet, List<Specialization> list) {
		int rowNum = 1;
		for(Specialization spec: list)
		{
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getSpaceCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}
		
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
		
		
		
	}

}
