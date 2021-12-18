package in.nit.rohit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.rohit.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("SELECT docId,docName FROM Document")
	List<Object[]> getDocumentIdAndName();
}
