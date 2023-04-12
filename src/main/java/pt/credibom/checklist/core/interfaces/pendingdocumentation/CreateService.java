package pt.credibom.checklist.core.interfaces.pendingdocumentation;

import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;

public interface CreateService {

    PendingDocument save(PendingDocument document);

    DocumentationNote save(DocumentationNote document);

}
