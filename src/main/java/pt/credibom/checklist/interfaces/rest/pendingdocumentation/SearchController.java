package pt.credibom.checklist.interfaces.rest.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.SearchService;

import pt.credibom.checklist.interfaces.command.PendingDocumentCommand;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;

import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;

import pt.credibom.checklist.interfaces.model.PendingDocumentationResponse;
import pt.credibom.checklist.interfaces.rest.utils.ResourceUtils;

@RestController
@RequestMapping("search-service")
@RequiredArgsConstructor
public class SearchController {

	private final SearchService pendingDocumentationSearchService;

	@GetMapping("/pending-documentation/configuration")
	ResponseEntity<DocumentationNote> search(@RequestParam Long proposal, @RequestParam String brand) {

		return ResponseEntity.ok( pendingDocumentationSearchService.search( proposal, brand ) );
	}

	@PostMapping("/pending-documents")
	ResponseEntity<PendingDocumentationResponse> searchPendingDocumentation(@RequestBody
			PendingDocumentCommand searchCriteria) {

		return ResponseEntity.ok(
				PendingDocumentationResponse.builder()
						.documents( pendingDocumentationSearchService.searchPendingDocumentation( searchCriteria ) )
						.build()
		);
	}

	@PostMapping("/pending-documents/paginated")
	ResponseEntity<Page<PendingDocument>> searchPendingDocumentation(@RequestBody
			PendingDocumentCommand searchCriteria,
																		  @RequestParam final int page,
																		  @RequestParam final int size,
																		  @RequestParam final String[] sort) {

		return ResponseEntity.ok( pendingDocumentationSearchService
				.searchPendingDocumentation( searchCriteria, PageRequest.of(page, size,
						Sort.by( ResourceUtils.toSortOrders( sort ) ) ) ) );
	}

	@PostMapping("/pending-documents/key")
	ResponseEntity<PendingDocumentationResponse> searchPendingDocumentation( @RequestBody ProposalKeyCommand key) {

		return ResponseEntity.ok(
				PendingDocumentationResponse.builder()
						.documents( pendingDocumentationSearchService.searchPendingDocumentation( key ) )
						.build()
		);
	}

	@PostMapping("/pending-documentation/pending")
	ResponseEntity<PendingDocumentationResponse> searchPendingPendingDocumentation(@RequestBody
			ProposalKeyCommand key) {


		return ResponseEntity.ok(
				PendingDocumentationResponse.builder()
				.documents( pendingDocumentationSearchService.searchPendingPendingDocumentation( key ) )
				.build()
		);
	}

	@GetMapping("/pending-documentation/pending/all")
	ResponseEntity<PendingDocumentationResponse> searchAllPendingPendingDocumentation() {
		return ResponseEntity.ok(
				PendingDocumentationResponse.builder()
						.documents( pendingDocumentationSearchService.searchAllPendingPendingDocumentation() )
						.build()
		);
	}

	@GetMapping("/pending-documents/filter")
	ResponseEntity<PendingDocumentationResponse> searchPendingDocuments(
			@RequestParam Long proposalNumber,
			@RequestParam String brand,
			@RequestParam Integer documentTypeId,
			@RequestParam String entityType  ) {
		return ResponseEntity.ok(
				PendingDocumentationResponse.builder()
						.documents( pendingDocumentationSearchService.searchPendingDocumentation( proposalNumber, brand,
								documentTypeId, entityType ) )
						.build()
		);
	}
}
