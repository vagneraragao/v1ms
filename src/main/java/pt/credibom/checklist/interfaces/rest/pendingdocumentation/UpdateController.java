package pt.credibom.checklist.interfaces.rest.pendingdocumentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.UpdateService;

import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;
import pt.credibom.checklist.interfaces.command.UpdateStatusCommand;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;


@RestController
@RequestMapping("update-service")
@RequiredArgsConstructor
public class UpdateController {

    private final UpdateService pendingDocumentationUpdateService;

   @PutMapping("/pending-document")
    public ResponseEntity<Void> updatePendingDocument( @RequestBody PendingDocument pendingDocument ) {

       pendingDocumentationUpdateService.updatePendingDocument( pendingDocument );

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

    @PutMapping("/pending-documentation/configuration")
    public ResponseEntity<Void> updatePendingDocumentConfiguration( @RequestBody DocumentationNote configuration ) {

        pendingDocumentationUpdateService.updatePendingDocumentConfiguration( configuration );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/pending-documents/status")
    public ResponseEntity<Void> setPendingDocumentsStatus(
            @RequestBody ProposalKeyCommand key,
            @RequestParam String entityType,
            @RequestParam Integer pendingStatusId,
            @RequestParam String requestedBy  ) {

        pendingDocumentationUpdateService.setPendingDocumentsStatus( key, entityType, pendingStatusId, requestedBy );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/pending-document/status")
    public ResponseEntity<Void> setPendingDocumentStatus(
            @RequestBody ProposalKeyCommand key,
            @RequestParam Integer documentTypeId,
            @RequestParam String entityType,
            @RequestParam Integer pendingStatusId,
            @RequestParam String user  ) {

        pendingDocumentationUpdateService.setPendingDocumentStatus( key, documentTypeId, entityType, pendingStatusId, user  );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/pending-documents/status/list")
    public ResponseEntity<Void> setPendingDocumentsStatus(@Valid @RequestBody UpdateStatusCommand updateStatusCommand ) {

        pendingDocumentationUpdateService.setPendingDocumentStatus( updateStatusCommand );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
