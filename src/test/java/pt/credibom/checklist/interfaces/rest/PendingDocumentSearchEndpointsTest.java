package pt.credibom.checklist.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.SearchService;
import pt.credibom.checklist.domain.pendingdocumentation.*;
import pt.credibom.checklist.exceptions.ApiException;
import pt.credibom.checklist.interfaces.command.PendingDocumentCommand;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;
import pt.credibom.checklist.interfaces.rest.pendingdocumentation.SearchController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
public class PendingDocumentSearchEndpointsTest {

	@Mock
	private SearchService pendingDocumentationSearchService;

	@InjectMocks
	private SearchController pendingDocumentationSearchEndpoints;

	private static DocumentationNote expectedDocumentationNote;

	private static ProposalKeyCommand key;

	private static Date date;

	private static PendingDocument expectedPendingDocument;

	private static PendingDocumentCommand criteria;


	@BeforeAll
	public static void setup() {
		expectedDocumentationNote = DocumentationNote.builder()
				.id( ProposalKey.builder().number( 1L ).brand( "Test" ).build() )
				.user( "user" )
				.authorizer( "user" )
				.internalNotes( "Test" )
				.externalNotes( "Test" )
				.build();

		key = ProposalKeyCommand.builder().number( 1L ).brand( "Test" ).build();

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

		 criteria = PendingDocumentCommand.builder().documentId( "test" ).build();
	}

	@Test
	public void searchWithResultTest() throws Exception {
		//given
		when(pendingDocumentationSearchService.search( 1L, "Test" )).thenReturn(expectedDocumentationNote);

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		mockMvc.perform( get("/search-service/pending-documentation/configuration?proposal=1&brand=Test").contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( jsonPath( "$.id.number" ).value( expectedDocumentationNote.getId().getNumber() ) )
				.andExpect( jsonPath( "$.id.brand" ).value( expectedDocumentationNote.getId().getBrand() ) )
				.andExpect( jsonPath( "$.user" ).value( expectedDocumentationNote.getUser() ) )
				.andExpect( jsonPath( "$.authorizer" ).value( expectedDocumentationNote.getAuthorizer() ) )
				.andExpect( jsonPath( "$.internalNotes" ).value( expectedDocumentationNote.getInternalNotes() ) )
				.andExpect( jsonPath( "$.externalNotes" ).value( expectedDocumentationNote.getExternalNotes() ) )
				.andReturn();

	}

	@Test
	public void searchWithoutResultTest() throws Exception {
		//given
		when(pendingDocumentationSearchService.search( eq(1L), eq("Test") ))
				.thenThrow( new ApiException( "error", HttpStatus.NOT_FOUND ) );

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		mockMvc.perform( get("/search-service/pending-documentation/configuration?proposal=1&brand=Test").contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( status().is4xxClientError() )
				.andReturn();

	}

	@Test
	public void searchPendingPendingDocumentationByKeyWithResultTest() throws Exception {
		//given
		when( pendingDocumentationSearchService.searchPendingPendingDocumentation( eq(key) ) )
				.thenReturn( List.of(expectedPendingDocument) );

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		ObjectMapper mapper = new ObjectMapper();

		mockMvc.perform( get("/search-service/pending-documentation/pending")
						.content(mapper.writeValueAsString( key ))
						.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( jsonPath( "$..documents[0].id" ).value( expectedPendingDocument.getId().intValue() ) )
				.andExpect( jsonPath( "$..documents[0].config" ).exists() )
				.andExpect( jsonPath( "$..documents[0].status" ).exists() )
				.andExpect( jsonPath( "$..documents[0].date" )
						.value( expectedPendingDocument.getDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..documents[0].manual" ).value( expectedPendingDocument.isManual() ) )
				.andExpect( jsonPath( "$..documents[0].modifiedDate" )
						.value( expectedPendingDocument.getModifiedDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..documents[0].reason" ).exists() )
				.andReturn();
	}

	@Test
	public void searchPendingDocumentationByCriteriaWithResultTest() throws Exception {
		//given
		when( pendingDocumentationSearchService.searchPendingDocumentation( eq(criteria) ) )
				.thenReturn( List.of(expectedPendingDocument) );

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		ObjectMapper mapper = new ObjectMapper();

		mockMvc.perform( get("/search-service/pending-documents")
						.content(mapper.writeValueAsString( criteria ))
						.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( jsonPath( "$..documents[0].id" ).value( expectedPendingDocument.getId().intValue() ) )
				.andExpect( jsonPath( "$..documents[0].config" ).exists() )
				.andExpect( jsonPath( "$..documents[0].status" ).exists() )
				.andExpect( jsonPath( "$..documents[0].date" )
						.value( expectedPendingDocument.getDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..documents[0].manual" ).value( expectedPendingDocument.isManual() ) )
				.andExpect( jsonPath( "$..documents[0].modifiedDate" )
						.value( expectedPendingDocument.getModifiedDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..documents[0].reason" ).exists() )
				.andReturn();
	}

	@Test
	public void searchPendingDocumentationPageableWithResultTest() throws Exception {
		//given
		when( pendingDocumentationSearchService.searchPendingDocumentation( eq(criteria),
				eq( PageRequest.of(0, 20, Sort.by( Sort.Direction.ASC, "id"  ) ) ) ) )
				.thenReturn( new PageImpl<>( List.of(expectedPendingDocument),
						PageRequest.of( 0, 20, Sort.by( Sort.Direction.ASC, "id" ) ), 1 ) );

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		ObjectMapper mapper = new ObjectMapper();

		mockMvc.perform( get("/search-service/pending-documents/paginated?page=0&size=20&sort=id,asc")
						.content(mapper.writeValueAsString( criteria ))
						.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( jsonPath( "$..content[0].id" ).value( expectedPendingDocument.getId().intValue() ) )
				.andExpect( jsonPath( "$..content[0].config" ).exists() )
				.andExpect( jsonPath( "$..content[0].status" ).exists() )
				.andExpect( jsonPath( "$..content[0].date" )
						.value( expectedPendingDocument.getDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..content[0].manual" ).value( expectedPendingDocument.isManual() ) )
				.andExpect( jsonPath( "$..content[0].modifiedDate" )
						.value( expectedPendingDocument.getModifiedDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..content[0].reason" ).exists() )
				.andExpect( jsonPath( "$.totalPages" ).value( "1" ) )
				.andExpect( jsonPath( "$.totalElements" ).value( "1" ) )
				.andReturn();
	}

	@Test
	public void searchPendingDocumentationByKeyWithResultTest() throws Exception {
		//given
		when( pendingDocumentationSearchService.searchPendingDocumentation( eq(key) ) )
				.thenReturn( List.of(expectedPendingDocument) );

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		ObjectMapper mapper = new ObjectMapper();

		mockMvc.perform( post("/search-service/pending-documents/key")
						.content(mapper.writeValueAsString( key ))
						.contentType( MediaType.APPLICATION_JSON ))
				.andExpect(status().isOk())
				.andExpectAll(
						jsonPath( "$..documents[0].id" ).value( expectedPendingDocument.getId().intValue() ),
						jsonPath( "$..documents[0].config" ).exists(),
						jsonPath( "$..documents[0].estado" ).exists(),
						jsonPath( "$..documents[0].data" )
						.value( expectedPendingDocument.getDate().toInstant().toEpochMilli() ),
						jsonPath( "$..documents[0].manual" ).value( expectedPendingDocument.isManual() ),
						jsonPath( "$..documents[0].dataAlteracao" )
						.value( expectedPendingDocument.getModifiedDate().toInstant().toEpochMilli() ),
						jsonPath( "$..documents[0].motivo" ).exists() );
	}

	@Test
	public void searchAllPendingPendingDocumentationWithResultTest() throws Exception {
		//given
		when( pendingDocumentationSearchService.searchAllPendingPendingDocumentation() )
				.thenReturn( List.of(expectedPendingDocument) );

		//when
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pendingDocumentationSearchEndpoints)
				.setControllerAdvice(ExceptionController.class)
				.build();

		//then
		mockMvc.perform( get("/search-service/pending-documentation/pending/all")
						.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( jsonPath( "$..documents[0].id" ).value( expectedPendingDocument.getId().intValue() ) )
				.andExpect( jsonPath( "$..documents[0].config" ).exists() )
				.andExpect( jsonPath( "$..documents[0].status" ).exists() )
				.andExpect( jsonPath( "$..documents[0].date" )
						.value( expectedPendingDocument.getDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..documents[0].manual" ).value( expectedPendingDocument.isManual() ) )
				.andExpect( jsonPath( "$..documents[0].modifiedDate" )
						.value( expectedPendingDocument.getModifiedDate().toInstant().toEpochMilli() ) )
				.andExpect( jsonPath( "$..documents[0].reason" ).exists() )
				.andReturn();
	}

}
