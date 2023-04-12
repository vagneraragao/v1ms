package pt.credibom.checklist.core.implementations.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.DeleteService;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.NoteRepository;
import pt.credibom.checklist.exceptions.ApiException;

import java.util.Date;
import java.util.List;

import static pt.credibom.checklist.core.utils.PendingDocumentSpecificationBuilder.createSpec;

@Service
@RequiredArgsConstructor
public class DeleteServiceImpl implements DeleteService {


    private final PendingDocumentRepository pendingDocumentsRep;
    private final NoteRepository configurationRep;

    @Override
    public void delete(PendingDocument document) {

        try{
            pendingDocumentsRep.save(document);
            pendingDocumentsRep.delete(document);
        }catch(DataAccessException e) {

            throw new ApiException("Error Deleting Pending Document :" + e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Override public void deleteByConfiguration(DocumentationNote configuration, String user ) {

        List<PendingDocument> docs = pendingDocumentsRep.findAll(createSpec( configuration.getId() ) );

        docs.stream().forEach( doc -> {
            doc.setModifiedDate( new Date() );
            doc.setUser( user );
            pendingDocumentsRep.save( doc );
        } );

        pendingDocumentsRep.deleteAll( docs );
        /*logger.info(
                "a apagar configuração de pendentes da proposta " + config.getId().getNumber() + " "
                        + config.getId().getBrand()
        );*/
        configuration.setUser( user );
        configuration = configurationRep.save( configuration );
        configurationRep.delete( configuration );
    }
}
