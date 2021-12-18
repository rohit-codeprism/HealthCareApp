package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Document;

public interface IDocumentService {
	
	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);

}
