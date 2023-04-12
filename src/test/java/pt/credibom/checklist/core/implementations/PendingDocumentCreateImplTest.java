package pt.credibom.checklist.core.implementations;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.credibom.checklist.core.implementations.pendingdocumentation.CreateServiceImpl;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.ProposalKey;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.DocumentationNoteRepository;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class PendingDocumentCreateImplTest {

    @Mock
    PendingDocumentRepository pendingDocumentRepository;
    @Mock
    DocumentationNoteRepository pendingDocumentationConfigRepository;
    @InjectMocks
    CreateServiceImpl pendingDocumentationCreateService;

    private static Optional<PendingDocument> expectePendingDocumentdResult;
    private static Optional<DocumentationNote> expectePendingDocumentdConfigResult;
    private static  ProposalKey pendingDocumentionConfigkey;

    public static PendingDocument pendingDocument = PendingDocument.builder()
                .config(expectePendingDocumentdConfigResult.get())
                .date(new Date())
                .manual(true)
                .user("D23545904")
                .documentId("123456")
                .modifiedDate(new Date())
                .user( "user" )
            .build();

    public static DocumentationNote pendingDocumentConfig = DocumentationNote.builder()
				.authorizer( "user" )
				.user( "user" )
				.externalNotes( "test" )
				.internalNotes( "test" )
            .build();

    @BeforeAll
    public static void setup() {

        //FPENND001
        expectePendingDocumentdResult = Optional.of(pendingDocument);
        expectePendingDocumentdResult.get().setId(123123L);

        //FPEND002
        pendingDocumentionConfigkey = ProposalKey.builder()
                .number( 1L )
                .brand( "test" )
                .build();

        expectePendingDocumentdConfigResult = Optional.of(pendingDocumentConfig );
        expectePendingDocumentdConfigResult.get().setId(pendingDocumentionConfigkey);
    }

    @Test
    public void saveWithResultTest() {

        //given
        when(pendingDocumentationConfigRepository.findById( eq(pendingDocumentionConfigkey)))
                .thenReturn( expectePendingDocumentdConfigResult);

        when(pendingDocumentationConfigRepository.save( eq( pendingDocumentConfig))).
                thenReturn( expectePendingDocumentdConfigResult.get());

        when(pendingDocumentRepository.save( eq(pendingDocument)) )
                .thenReturn( expectePendingDocumentdResult.get());

        //when
        PendingDocument result = pendingDocumentationCreateService.save(pendingDocument);

        //then
        assertThat( result.getId() ).isEqualTo( expectePendingDocumentdResult.get().getId() );
        assertThat( result.getConfig() ).isEqualTo( expectePendingDocumentdConfigResult.get() );
        assertThat( result.getDate() ).isEqualTo( expectePendingDocumentdResult.get().getDate() );
        assertThat( result.getEntityType() ).isEqualTo( expectePendingDocumentdResult.get().getEntityType() );
        assertThat( result.isManual() ).isEqualTo( expectePendingDocumentdResult.get().isManual() );
        assertThat( result.getUser() ).isEqualTo( expectePendingDocumentdResult.get().getUser() );
        assertThat( result.getDocumentId() ).isEqualTo( expectePendingDocumentdResult.get().getDocumentId() );
        assertThat( result.getModifiedDate() ).isEqualTo( expectePendingDocumentdResult.get().getModifiedDate() );

    }

    @Test
    public void saveConfigWithResultTest() {

        DocumentationNote result = pendingDocumentationCreateService.save(pendingDocumentConfig);

        assertThat( result.getId() ).isEqualTo( pendingDocumentionConfigkey );
        assertThat( result.getAuthorizer() ).isEqualTo( expectePendingDocumentdConfigResult.get().getAuthorizer() );
        assertThat( result.getUser() ).isEqualTo( expectePendingDocumentdConfigResult.get().getUser() );
        assertThat( result.getExternalNotes() ).isEqualTo( expectePendingDocumentdConfigResult.get().getExternalNotes() );
        assertThat( result.getInternalNotes() ).isEqualTo( expectePendingDocumentdConfigResult.get().getInternalNotes() );

    }

}
