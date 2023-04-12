package pt.credibom.checklist.interfaces.rest.pendingdocumentation;

import jakarta.websocket.server.PathParam;
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
import pt.credibom.checklist.core.interfaces.pendingdocumentation.GetService;

import pt.credibom.checklist.domain.common.IntervenerTypeEnum;
import pt.credibom.checklist.interfaces.command.PendingDocumentsCommand;
import pt.credibom.checklist.domain.pendingdocumentation.History;
import pt.credibom.checklist.domain.pendingdocumentation.Pack;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.Status;

import pt.credibom.checklist.interfaces.model.PendingDocumentationCategoriesResponse;
import pt.credibom.checklist.interfaces.model.PendingDocumentationConfigurationResponse;
import pt.credibom.checklist.interfaces.model.PendingDocumentationHistoryResponse;
import pt.credibom.checklist.interfaces.model.PendingDocumentationMotivesResponse;
import pt.credibom.checklist.interfaces.model.PendingDocumentationNotificationNotesResponse;
import pt.credibom.checklist.interfaces.model.PendingDocumentationResponse;
import pt.credibom.checklist.interfaces.model.PendingDocumentationStatusResponse;
import pt.credibom.checklist.interfaces.model.PendingPossibleDocumentationResponse;
import pt.credibom.checklist.interfaces.rest.utils.ResourceUtils;

@RestController
@RequestMapping("get-service")
@RequiredArgsConstructor
public class GetController {

	private final GetService pendingDocumentationGetService;

	@GetMapping("/category/all")
	public ResponseEntity<PendingDocumentationCategoriesResponse> getCategories() {

		return ResponseEntity.ok(PendingDocumentationCategoriesResponse.builder()
				.categories( pendingDocumentationGetService.getCategories() )
				.build() );
	}

	@GetMapping("/pending-documents")
	ResponseEntity<PendingDocument>  getPendingDocument(
			@RequestParam final Long proposalNumber,
			@RequestParam final String brand,
			@RequestParam final IntervenerTypeEnum intervenerType,
			@RequestParam final int possiblePendingDocument) {

		return ResponseEntity.ok(pendingDocumentationGetService.getPendingDocument(
				proposalNumber, brand, intervenerType, possiblePendingDocument ));

	}

	@GetMapping("/pending-documents/identification-document")
	ResponseEntity<PendingDocumentationResponse>  getPendingIdentificationDocuments(
			@RequestParam final Long proposalNumber,
			@RequestParam final String brand,
			@RequestParam final String intervenerType) {

		return ResponseEntity.ok( PendingDocumentationResponse.builder()
				.documents( pendingDocumentationGetService.getPendingIdentificationDocuments( proposalNumber,
						brand, intervenerType ) )
				.build() );

	}

	@GetMapping("/pending-documents/identification-document/pending")
	ResponseEntity<PendingDocumentationResponse>  getPendingIdentificationDocumentsPending(
			@RequestParam final Long proposalNumber,
			@RequestParam final String brand,
			@RequestParam String intervenerType) {

		return ResponseEntity.ok(PendingDocumentationResponse.builder()
				.documents( pendingDocumentationGetService.getPendingIdentificationDocumentsPending( proposalNumber,
						brand, intervenerType ) )
				.build() );
	}

	@GetMapping("/pending-documentation/possible-documents/all")
	ResponseEntity<PendingPossibleDocumentationResponse>  getPossibleDocuments() {

		return ResponseEntity.ok(PendingPossibleDocumentationResponse.builder()
				.documents( pendingDocumentationGetService.getPossibleDocuments() )
				.build() );
	}

	@GetMapping("/pending-documentation/possible-documents")
	ResponseEntity<PendingPossibleDocumentationResponse>  getPossibleDocumentsByCategoryAndUser(
			@RequestParam String categoryId,
			@RequestParam Integer authorizationLevel) {

		return ResponseEntity.ok(PendingPossibleDocumentationResponse.builder()
				.documents( pendingDocumentationGetService.getPossibleDocumentsByCategoryAndUser( categoryId,
						authorizationLevel ) )
				.build() );
	}

	@GetMapping("/pending-documentation/status/all")
	ResponseEntity<PendingDocumentationStatusResponse>  getStatus() {

		return ResponseEntity.ok( PendingDocumentationStatusResponse.builder()
				.status( pendingDocumentationGetService.getStatus() )
				.build() );
	}

	@GetMapping("/pending-documentation/history/key")
	ResponseEntity<Page<History>>  getHistory(
			@RequestParam String proposal,
			@RequestParam String brand,
			@RequestParam final int page,
			@RequestParam final int size,
			@RequestParam final String[] sort) {

		return ResponseEntity.ok(pendingDocumentationGetService.getHistory( proposal, brand, PageRequest.of(page, size,
				Sort.by( ResourceUtils.toSortOrders( sort ) ) ) ) );
	}

	@GetMapping("/pending-documentation/{pendingDocumentId}/history")
	ResponseEntity<Page<History>>  getPendingDocumentHistory(
			@PathParam( "pendingDocumentId" ) Long pendingDocumentId,
			@RequestParam final int page,
			@RequestParam final int size,
			@RequestParam final String[] sort) {

		return ResponseEntity.ok(pendingDocumentationGetService.getPendingDocumentHistory( pendingDocumentId,
				PageRequest.of(page, size,
						Sort.by( ResourceUtils.toSortOrders( sort ) ) ) ) );
	}

	@GetMapping("/pending-documentation/history")
	ResponseEntity<PendingDocumentationHistoryResponse>  getPendingDocumentHistory(
			@RequestParam Long pendingDocumentId ) {

		return ResponseEntity.ok(PendingDocumentationHistoryResponse.builder()
				.history( pendingDocumentationGetService.getPendingDocumentHistory( pendingDocumentId ))
				.build() );
	}

	@GetMapping("/pending-documentation/motives/all")
	ResponseEntity<PendingDocumentationMotivesResponse>  getMotives() {

		return ResponseEntity.ok( PendingDocumentationMotivesResponse.builder()
				.motives( pendingDocumentationGetService.getMotives() )
				.build() );
	}

	@GetMapping("/pending-documentation/motives")
	ResponseEntity<PendingDocumentationMotivesResponse>  getMotivesByCategory( @RequestParam String categoryId ) {
		return ResponseEntity.ok( PendingDocumentationMotivesResponse.builder()
				.motives( pendingDocumentationGetService.getMotivesByCategory( categoryId ) )
				.build() );
	}

	@GetMapping("/pending-documentation/count")
	ResponseEntity<Integer>  countPendingDocumentsPending(@RequestParam Long proposalNumber,
			@RequestParam String brand ) {
		return ResponseEntity.ok( pendingDocumentationGetService.countPendingDocumentsPending( proposalNumber, brand ) );
	}

	@GetMapping("/pending-documentation/status/{statusId}")
	ResponseEntity<Status>  getStatusById(@PathParam( "statusId" ) Long statusId ) {
		return ResponseEntity.ok( pendingDocumentationGetService.getStatusById( statusId ) );
	}

	@PostMapping("/pending-documentation/pack")
	ResponseEntity<Pack>  getPendingDocumentationPack(@RequestBody
			PendingDocumentsCommand commandPendingDocuments ) {
		return ResponseEntity.ok( pendingDocumentationGetService.getPendingDocumentationPack(
				commandPendingDocuments ) );
	}

	@GetMapping("/pending-documentation/notification-notes")
	ResponseEntity<PendingDocumentationNotificationNotesResponse>  findNotificationNotesByVendor(@RequestParam String vendorEntityCode ) {
		return ResponseEntity.ok( PendingDocumentationNotificationNotesResponse.builder()
						.notificationNotes( pendingDocumentationGetService.findNotificationNotesByVendor(
								vendorEntityCode ) )
						.build()
		);
	}

	@GetMapping("pending-documents/{id}")
	ResponseEntity<PendingDocument> getPendingDocumentById(@PathParam( "id" ) Long id ) {
		return  ResponseEntity.ok(pendingDocumentationGetService.getPendingDocumentById( id ) );
	}

	@GetMapping("/pending-documentation/configuration/all")
	ResponseEntity<PendingDocumentationConfigurationResponse>  geConfigurations() {

		return ResponseEntity.ok( PendingDocumentationConfigurationResponse.builder()
						.configurations( pendingDocumentationGetService.getConfigurations() )
						.build() );
	}
}
