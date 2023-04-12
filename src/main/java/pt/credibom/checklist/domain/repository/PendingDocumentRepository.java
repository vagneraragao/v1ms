package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;

import java.util.Optional;

@Repository
public interface PendingDocumentRepository extends JpaRepository<PendingDocument, Long>, JpaSpecificationExecutor<PendingDocument> {
	Optional<PendingDocument> findById(Long id);
}
