package pt.credibom.checklist.interfaces.rest.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.CreateService;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;

@RestController
@RequestMapping("create-service")
@RequiredArgsConstructor
public class CreateController {

    private final CreateService pendingDocumentationCreateService;

    @PostMapping("/pending-document")
    ResponseEntity<PendingDocument> save(@RequestBody PendingDocument pendingDocument) {

        PendingDocument newPendingDoc = pendingDocumentationCreateService.save( pendingDocument );

        /*URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{pendingDocumentId}")
                .buildAndExpand(newPendingDoc.getId())
                .toUri();*/

        return ResponseEntity.ok(newPendingDoc);
    }

    @PostMapping("/pending-documentation/configuration")
    ResponseEntity<DocumentationNote> save(@RequestBody DocumentationNote configuration) {

        DocumentationNote newPendingDocConfig = pendingDocumentationCreateService.save( configuration );

       /* URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{pendingDocumentId}")
                .buildAndExpand(newPendingDocConfig.getId())
                .toUri();*/

        return ResponseEntity.ok(newPendingDocConfig);

    }
}
