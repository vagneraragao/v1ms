package pt.credibom.checklist.core.implementations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pt.credibom.checklist.core.implementations.pendingdocumentation.CreateServiceImpl;
import pt.credibom.checklist.core.implementations.pendingdocumentation.DeleteServiceImpl;
import pt.credibom.checklist.core.implementations.pendingdocumentation.SearchServiceImpl;
import pt.credibom.checklist.domain.pendingdocumentation.Document;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.ProposalKey;
import pt.credibom.checklist.domain.pendingdocumentation.Reason;
import pt.credibom.checklist.domain.pendingdocumentation.Status;
import pt.credibom.checklist.exceptions.ApiException;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PendingDocumentDeleteImplTest {

    @Autowired
    private DeleteServiceImpl deleteService;
    @Autowired
    private CreateServiceImpl createService;
    @Autowired
    private SearchServiceImpl searchService;
    private static PendingDocument expectedPendingDocumentation;
    private static DocumentationNote expectedDocumentationNote;
    private static ProposalKeyCommand key;

    @BeforeAll
    public static void setup() {
        expectedDocumentationNote = DocumentationNote.builder()
                .id(ProposalKey.builder().number(1L).brand("test").build())
                .user("user")
                .authorizer("user")
                .internalNotes("Test")
                .externalNotes("Test")
                .build();

        key = ProposalKeyCommand.builder().number(1L).brand("test").build();

        Date date = Date.from(Instant.now());

        expectedPendingDocumentation = PendingDocument.builder()
                .id(1L)
                .user("user")
                .config(expectedDocumentationNote)
                .status(Status.builder().id(1).build())
                .possibleDocument(Document.builder().id(1).build())
                .documentId("test")
                .date(date)
                .manual(true)
                .modifiedDate(date)
                .reason(Reason.builder().id(1).build())
                .build();

    }

    @Test
    void deleteByConfiguration() {
        DocumentationNote documentationNote = createService.save(expectedDocumentationNote);
        PendingDocument pendingDocument = createService.save(expectedPendingDocumentation);
        deleteService.deleteByConfiguration(documentationNote, "user");
        assertEquals(List.of(), searchService.searchPendingDocumentation(key));
        assertThrows(ApiException.class, () -> searchService.search(key.getNumber(), key.getBrand()));
    }

    @Test
    void deleteByPendingDocument() {
        PendingDocument pendingDocument = createService.save(expectedPendingDocumentation);
        deleteService.delete(pendingDocument);
        assertEquals(List.of(), searchService.searchPendingDocumentation(key));
    }

}