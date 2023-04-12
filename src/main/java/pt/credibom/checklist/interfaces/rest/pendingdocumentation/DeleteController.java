package pt.credibom.checklist.interfaces.rest.pendingdocumentation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.DeleteService;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;

@RestController
@RequestMapping("delete-service")
@RequiredArgsConstructor
public class DeleteController {

    private final DeleteService pendingDocumentationDeleteService;

    @PostMapping("/pending-document/{id}")
    ResponseEntity<Void> deletePendingDocument(@RequestBody PendingDocument pendingDocument) {

        pendingDocumentationDeleteService.delete( pendingDocument );
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/pending-document/batch")
    ResponseEntity<Void> deletePendingDocuments(@RequestBody DocumentationNote configuration , @RequestParam String requestedBy) {

        pendingDocumentationDeleteService.deleteByConfiguration( configuration, requestedBy );
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
