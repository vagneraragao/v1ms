package pt.credibom.checklist.core.implementations.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.SearchService;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.UpdateService;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.Status;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.NoteRepository;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;
import pt.credibom.checklist.interfaces.command.UpdateStatusCommand;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService {

    private final PendingDocumentRepository pendingDocumentRepository;

    private final NoteRepository noteRepository;

    private final SearchService pendingDocumentationSearchService;

    @Override
    public void updatePendingDocument( PendingDocument pendingDocument ) {
        pendingDocumentRepository.save( pendingDocument );
    }

    @Override
    public void updatePendingDocumentConfiguration( DocumentationNote configuration ) {
        noteRepository.save( configuration );
    }

    @Override
    public void setPendingDocumentsStatus( ProposalKeyCommand key, String entityType,
            Integer pendingStatusId, String user ) {
        List<PendingDocument> documents = pendingDocumentationSearchService.searchPendingPendingDocumentation( key );

        documents.stream()
                .filter( document -> document.getEntityType().equals( entityType ) )
                .forEach( document -> {
                    document.setUser(user);
                    document.setStatus(Status.builder()
                            .id( pendingStatusId )
                            .build()
                    );

                    this.updatePendingDocument( document );
                });
    }

    @Override
    public void setPendingDocumentStatus( ProposalKeyCommand key, Integer documentTypeId, String entityType, Integer pendingStatusId,
            String user ) {

        Optional<PendingDocument> doc = pendingDocumentationSearchService.searchPendingDocumentation( key.getNumber(),
                key.getBrand(),  documentTypeId, entityType ).stream().findFirst();

       doc.ifPresent( document -> {
           document.setStatus( Status.builder()
                   .id( pendingStatusId )
                   .build()
           );

           this.updatePendingDocument( document );
       } );
    }

    @Override
    public void setPendingDocumentStatus( UpdateStatusCommand updateStatusCommand ) {

        updateStatusCommand.getStatus().forEach( item -> {
            this.setPendingDocumentStatus( item.getKey(), item.getDocumentTypeId(), item.getEntityType(),
                    item.getPendingStatusId(), item.getRequestedBy());

        } );
    }
}
