package pt.credibom.checklist.core.interfaces.pendingdocumentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.credibom.checklist.interfaces.command.PendingDocumentCommand;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;

import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;

import java.util.List;

public interface SearchService {
	DocumentationNote search(Long proposta, String brand);

	List<PendingDocument> searchPendingDocumentation( PendingDocumentCommand searchCriteria);

	Page<PendingDocument> searchPendingDocumentation( PendingDocumentCommand searchCriteria, Pageable pageable);

	List<PendingDocument> searchPendingDocumentation( ProposalKeyCommand documentacaoPendente);

	List<PendingDocument> searchPendingPendingDocumentation( ProposalKeyCommand key);

	List<PendingDocument> searchAllPendingPendingDocumentation();

	List<PendingDocument> searchPendingDocumentation(Long proposalNumber, String brand, Integer documentTypeId, String entityTpe );
}
