package pt.credibom.checklist.core.implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import pt.credibom.checklist.core.implementations.pendingdocumentation.SearchServiceImpl;
import pt.credibom.checklist.domain.pendingdocumentation.*;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.DocumentationNoteRepository;
import pt.credibom.checklist.exceptions.ApiException;
import pt.credibom.checklist.interfaces.command.ReasonCommand;
import pt.credibom.checklist.interfaces.command.StatusCommand;
import pt.credibom.checklist.interfaces.command.NoteCommand;
import pt.credibom.checklist.interfaces.command.PendingDocumentCommand;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class PendingDocumentSearchImplTest {
	@Mock
	private DocumentationNoteRepository documentationNoteRepository;

	@Mock
	private PendingDocumentRepository pendingDocumentRepository;

	@InjectMocks
	private SearchServiceImpl pendingDocumentationSearchService;

	private static ProposalKey key;

	private static Optional<DocumentationNote> expectedResult;

	private static PendingDocumentCommand criteriaPendingDocumentation;

	private static ProposalKeyCommand commandKey;

	@BeforeAll
	public static void setup() {
		commandKey = ProposalKeyCommand.builder().number( 1L ).brand( "test" ).build();

		key = ProposalKey.builder().number( 1L ).brand( "test" ).build();

		Date date = Date.from( Instant.now() );

		criteriaPendingDocumentation = PendingDocumentCommand.builder()
				.id( 1L )
				.user( "user" )
				.config( NoteCommand.builder().id( commandKey ).build() )
				.status( StatusCommand.builder().build() )
				.documentId( "test" )
				.date( date )
				.manual( true )
				.modifiedDate( date )
				.reason( ReasonCommand.builder().build() )
				.build();

		expectedResult = Optional.of( DocumentationNote.builder()
				.id( key )
				.authorizer( "user" )
				.user( "user" )
				.externalNotes( "test" )
				.internalNotes( "test" )
				.build() );
	}

	@Test
	public void searchWithResultTest() {
		//given
		when(documentationNoteRepository.findById( eq(key ) ) ).thenReturn( expectedResult );

		//when
		DocumentationNote result = pendingDocumentationSearchService.search( 1L, "test" );

		//then
		assertThat( result.getId() ).isEqualTo( key );
		assertThat( result.getAuthorizer() ).isEqualTo( expectedResult.get().getAuthorizer() );
		assertThat( result.getUser() ).isEqualTo( expectedResult.get().getUser() );
		assertThat( result.getExternalNotes() ).isEqualTo( expectedResult.get().getExternalNotes() );
		assertThat( result.getInternalNotes() ).isEqualTo( expectedResult.get().getInternalNotes() );

	}

	@Test
	public void searchNotFoundTest() {
		//given
		when(documentationNoteRepository.findById( eq(key) )).thenReturn( Optional.empty() );

		//when
		Exception exception = Assertions.assertThrows( ApiException.class, () -> {
			pendingDocumentationSearchService.search( 1L, "test" );
		} );

		//then
		assertThat( exception.getMessage() ).isEqualTo( "Configuration Not Found" );
	}

	@Test
	public void searchPendingDocumentationWithResultTest() {
		//given
		when(pendingDocumentRepository.findAll( any( Specification.class )  )).thenReturn( List.of(criteriaPendingDocumentation) );

		//when
		List<PendingDocument> result = pendingDocumentationSearchService.searchPendingDocumentation(criteriaPendingDocumentation);

		//then
		assertThat( result.get( 0 ).getId() ).isEqualTo( criteriaPendingDocumentation.getId() );
		assertThat( result.get( 0 ).getUser() ).isEqualTo( criteriaPendingDocumentation.getUser() );
		assertThat( result.get( 0 ).getConfig() ).isEqualTo( criteriaPendingDocumentation.getConfig() );
		assertThat( result.get( 0 ).getDocumentId() ).isEqualTo( criteriaPendingDocumentation.getDocumentId() );
		assertThat( result.get( 0 ).getPossibleDocument() ).isEqualTo( criteriaPendingDocumentation.getPossibleDocument() );

	}

	@Test
	public void searchPendingDocumentationPageableWithResultTest() {
		//setup
		Pageable pageable = PageRequest.of(0, 20, Sort.by( Sort.Direction.ASC, "id"  )  );
		//given
		when(pendingDocumentRepository.findAll( any( Specification.class ), eq(pageable) ) )
				.thenReturn( new PageImpl<>( List.of(criteriaPendingDocumentation) ) );

		//when
		Page<PendingDocument> result = pendingDocumentationSearchService.searchPendingDocumentation(criteriaPendingDocumentation, pageable );

		//then
		assertThat(result.isLast()).isTrue();
		assertThat(result).satisfies( a -> {
			a.forEach( fpend001 -> {
				assertThat( fpend001.getId() ).isEqualTo( criteriaPendingDocumentation.getId() );
				assertThat( fpend001.getUser() ).isEqualTo( criteriaPendingDocumentation.getUser() );
				assertThat( fpend001.getConfig() ).isEqualTo( criteriaPendingDocumentation.getConfig() );
				assertThat( fpend001.getDocumentId() ).isEqualTo( criteriaPendingDocumentation.getDocumentId() );
				assertThat( fpend001.getPossibleDocument() ).isEqualTo( criteriaPendingDocumentation.getPossibleDocument() );

			} );
		} );

	}

	@Test
	public void searchPendingDocumentationByKeyWithResultTest() {
		//given
		when(pendingDocumentRepository.findAll( any( Specification.class )  )).thenReturn( List.of(criteriaPendingDocumentation) );

		//when
		List<PendingDocument> result = pendingDocumentationSearchService.searchPendingDocumentation( commandKey );

		//then
		assertThat( result.get( 0 ).getId() ).isEqualTo( criteriaPendingDocumentation.getId() );
		assertThat( result.get( 0 ).getUser() ).isEqualTo( criteriaPendingDocumentation.getUser() );
		assertThat( result.get( 0 ).getConfig() ).isEqualTo( criteriaPendingDocumentation.getConfig() );
		assertThat( result.get( 0 ).getDocumentId() ).isEqualTo( criteriaPendingDocumentation.getDocumentId() );
		assertThat( result.get( 0 ).getPossibleDocument() ).isEqualTo( criteriaPendingDocumentation.getPossibleDocument() );

	}

	@Test
	public void searchPendingPendingDocumentationByKeyWithResultTest() {
		//given
		when(pendingDocumentRepository.findAll( any( Specification.class )  )).thenReturn( List.of(criteriaPendingDocumentation) );

		//when
		List<PendingDocument> result = pendingDocumentationSearchService.searchPendingPendingDocumentation( commandKey );

		//then
		assertThat( result.get( 0 ).getId() ).isEqualTo( criteriaPendingDocumentation.getId() );
		assertThat( result.get( 0 ).getUser() ).isEqualTo( criteriaPendingDocumentation.getUser() );
		assertThat( result.get( 0 ).getConfig() ).isEqualTo( criteriaPendingDocumentation.getConfig() );
		assertThat( result.get( 0 ).getDocumentId() ).isEqualTo( criteriaPendingDocumentation.getDocumentId() );
		assertThat( result.get( 0 ).getPossibleDocument() ).isEqualTo( criteriaPendingDocumentation.getPossibleDocument() );
		assertThat( result.get( 0 ).getConfig().getId().getNumber() ).isEqualTo( commandKey.getNumber() );
		assertThat( result.get( 0 ).getConfig().getId().getBrand() ).isEqualTo( commandKey.getBrand() );

	}

	@Test
	public void searchAllPendingPendingDocumentationByParamsWithResultTest() {
		//given
		when(pendingDocumentRepository.findAll( any( Specification.class )  )).thenReturn( List.of(criteriaPendingDocumentation) );

		//when
		List<PendingDocument> result = pendingDocumentationSearchService.searchAllPendingPendingDocumentation( );

		//then
		assertThat( result.get( 0 ).getId() ).isEqualTo( criteriaPendingDocumentation.getId() );
		assertThat( result.get( 0 ).getUser() ).isEqualTo( criteriaPendingDocumentation.getUser() );
		assertThat( result.get( 0 ).getConfig() ).isEqualTo( criteriaPendingDocumentation.getConfig() );
		assertThat( result.get( 0 ).getDocumentId() ).isEqualTo( criteriaPendingDocumentation.getDocumentId() );
		assertThat( result.get( 0 ).getPossibleDocument() ).isEqualTo( criteriaPendingDocumentation.getPossibleDocument() );
		assertThat( result.get( 0 ).getConfig().getId().getNumber() ).isEqualTo( commandKey.getNumber() );
		assertThat( result.get( 0 ).getConfig().getId().getBrand() ).isEqualTo( commandKey.getBrand() );

	}
}
