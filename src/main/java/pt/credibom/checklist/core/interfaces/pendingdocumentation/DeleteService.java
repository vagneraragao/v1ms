package pt.credibom.checklist.core.interfaces.pendingdocumentation;

import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;

public interface DeleteService {

    void delete(PendingDocument document);

    void deleteByConfiguration(DocumentationNote configuration, String user );
}
