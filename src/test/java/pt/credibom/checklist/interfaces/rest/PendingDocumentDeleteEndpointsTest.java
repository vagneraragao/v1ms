package pt.credibom.checklist.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.DeleteService;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.SearchService;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.ProposalKey;
import pt.credibom.checklist.domain.pendingdocumentation.Reason;
import pt.credibom.checklist.domain.pendingdocumentation.Status;
import pt.credibom.checklist.interfaces.rest.pendingdocumentation.DeleteController;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
public class PendingDocumentDeleteEndpointsTest {

    @Mock
    private SearchService pendingDocumentationSearchService;
    @Mock
    private DeleteService pendingDocumentationDeleteService;
    @InjectMocks
    private DeleteController pendingDocumentDeleteEndpoints;
    private static PendingDocument expectedPendingDocument;
    private static DocumentationNote expectedDocumentNote;

    @BeforeAll
    public static void setup() {
        expectedDocumentNote = DocumentationNote.builder()
                .id( ProposalKey.builder().number( 1L ).brand( "Test" ).build() )
                .user( "user" )
                .authorizer( "user" )
                .internalNotes( "Test" )
                .externalNotes( "Test" )
                .build();

        Date date = Date.from( Instant.now() );

        expectedPendingDocument = PendingDocument.builder()
                .id( 1L )
                .user( "user" )
                .config( DocumentationNote.builder().build() )
                .status( Status.builder().build() )
                .documentId( "test" )
                .date( date )
                .manual( true )
                .modifiedDate( date )
                .reason( Reason.builder().build() )
                .build();
    }

    @Test
    void deletePendingDocumentationByConfiguration() throws Exception {

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentDeleteEndpoints)
                .setControllerAdvice(ExceptionController.class)
                .build();

        //then
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform( post("/delete-service/pending-document/batch")
                        .content(mapper.writeValueAsString(expectedDocumentNote))
                        .param("requestedBy","user")
                        .contentType( MediaType.APPLICATION_JSON ))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePendingDocumentationByPendingDocument() throws Exception {

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentDeleteEndpoints)
                .setControllerAdvice(ExceptionController.class)
                .build();

        //then
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform( post("/delete-service/pending-document/1")
                        .content(mapper.writeValueAsString(expectedPendingDocument))
                        .contentType( MediaType.APPLICATION_JSON ))
                .andExpect(status().isNoContent());
    }
}
