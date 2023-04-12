package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.ProposalKey;

@Repository
public interface DocumentationNoteRepository extends JpaRepository<DocumentationNote, ProposalKey> {

}
