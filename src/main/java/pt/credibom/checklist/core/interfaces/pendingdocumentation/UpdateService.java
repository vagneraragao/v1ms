package pt.credibom.checklist.core.interfaces.pendingdocumentation;


import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;
import pt.credibom.checklist.interfaces.command.UpdateStatusCommand;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;


public interface UpdateService {

    void updatePendingDocument(PendingDocument pendingDocument );

    void updatePendingDocumentConfiguration( DocumentationNote configuration );

    void setPendingDocumentsStatus( ProposalKeyCommand key, String entityType, Integer pendingStatusId, String user );

    void setPendingDocumentStatus( ProposalKeyCommand key, Integer documentTypeId, String entityType, Integer pendingStatusId, String user );

    void setPendingDocumentStatus( UpdateStatusCommand updateStatusCommand );
}
