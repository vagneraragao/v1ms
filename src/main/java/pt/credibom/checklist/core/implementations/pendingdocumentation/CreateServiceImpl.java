package pt.credibom.checklist.core.implementations.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.CreateService;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.DocumentationNoteRepository;
import pt.credibom.checklist.exceptions.ApiException;

@Service
@RequiredArgsConstructor
public class CreateServiceImpl implements CreateService {


    private final PendingDocumentRepository pendingDocumentsRep;

    private final DocumentationNoteRepository pendingDocumentsconfigRep;

    @Override
    public PendingDocument save(PendingDocument document) {

        PendingDocument pendingDocument = null;

        try{
            DocumentationNote documentationNote = null;
            if ( document.getConfig().getId() == null || pendingDocumentsconfigRep.findById( document.getConfig().getId() ).isEmpty() ) {
                document.getConfig().setUser( document.getUser() );
                documentationNote = this.save( document.getConfig() );
            } else {
                documentationNote = pendingDocumentsconfigRep.findById( document.getConfig().getId() ).get();
            }
            document.setConfig(documentationNote);
            pendingDocument = pendingDocumentsRep.save(document);

        }catch(DataAccessException e){

            throw new ApiException( "Error saving Pending Document :" + e.getMessage(), HttpStatus.NOT_FOUND );
        }
        return pendingDocument;
    }

    @Override
    public DocumentationNote save(DocumentationNote document) {

        DocumentationNote configPendingDocument = null;

        try{
            configPendingDocument= pendingDocumentsconfigRep.save(document);
        }catch(DataAccessException e){
            throw new ApiException( "Error saving Pending Document Config:" + e.getMessage(), HttpStatus.NOT_FOUND );

        }
        return configPendingDocument;
    }

}
