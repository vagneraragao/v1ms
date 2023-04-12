package pt.credibom.checklist.core.implementations.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.GetService;
import pt.credibom.checklist.core.utils.PendingDocumentationConstants;
import pt.credibom.checklist.domain.*;
import pt.credibom.checklist.domain.common.AuditTrailContext;
import pt.credibom.checklist.domain.common.IntervenerTypeEnum;
import pt.credibom.checklist.domain.pendingdocumentation.*;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.HistoryRepository;
import pt.credibom.checklist.domain.repository.DocumentationNoteRepository;
import pt.credibom.checklist.domain.repository.DocumentRepository;
import pt.credibom.checklist.domain.repository.CategoryRepository;
import pt.credibom.checklist.domain.repository.StatusRepository;
import pt.credibom.checklist.domain.repository.ReasonRepository;
import pt.credibom.checklist.domain.repository.ReasonCategoryRepository;
import pt.credibom.checklist.domain.repository.PackRepository;
import pt.credibom.checklist.domain.repository.VendorNotificationRepository;
import pt.credibom.checklist.exceptions.ApiException;
import pt.credibom.checklist.interfaces.command.PendingDocumentsCommand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pt.credibom.checklist.core.utils.PendingDocumentSpecificationBuilder.createSpec;
import static pt.credibom.checklist.core.utils.PendingDocumentSpecificationBuilder.createSpecCount;
import static pt.credibom.checklist.core.utils.HistorySpecificationBuilder.createSpec;
import static pt.credibom.checklist.core.utils.DocumentSpecificationBuilder.createSpec;
import static pt.credibom.checklist.core.utils.PackSpecificationBuilder.createSpec;
import static pt.credibom.checklist.core.utils.VendorNotificationSpecificationBuilder.createSpec;

@Service
@RequiredArgsConstructor
public class GetServiceImpl implements GetService {
    private final CategoryRepository categoryRepository;

    private final PendingDocumentRepository pendingDocumentRepository;

    private final DocumentRepository documentRepository;

    private final StatusRepository statusRepository;

    private final ReasonRepository reasonRepository;

    private final ReasonCategoryRepository reasonCategoryRepository;

    private final HistoryRepository historyRepository;

    private final PackRepository packRepository;

    private final VendorNotificationRepository vendorNotificationRepository;

    private final DocumentationNoteRepository documentationNoteRepository;

    @Override
    public List<Category> getCategories() {

        return categoryRepository.findByActive( true );
    }

    @Override
    public PendingDocument getPendingDocument(Long proposalNumber, String brand, IntervenerTypeEnum intervenerType, int possibleDocumentId) {

        PendingDocument searchCriteria = PendingDocument.builder()
                .config( DocumentationNote.builder()
                            .id( ProposalKey.builder()
                            .number( proposalNumber )
                            .brand( brand )
                            .build() )
                        .build() )
                .entityType( Owner.builder().id(intervenerType.getCode()).build() )
                .possibleDocument( Document.builder()
                        .id( possibleDocumentId )
                        .build() )
                .build();

        return pendingDocumentRepository.findOne( createSpec( searchCriteria ) )
                .orElseThrow( () -> new ApiException( "Pending Document not found", HttpStatus.NOT_FOUND ) );
    }

    @Override
    public List<PendingDocument> getPendingIdentificationDocuments(Long proposalNumber, String brand, String intervenerType) {

        PendingDocument searchCriteria = PendingDocument.builder()
                .config( DocumentationNote.builder()
                        .id( ProposalKey.builder()
                                .number( proposalNumber )
                                .brand( brand )
                                .build() )
                        .build() )
                .entityType( Owner.builder().id(intervenerType).build() )
                .possibleDocument( Document.builder()
                        .id( PendingDocumentationConstants.ID_PENDENTE_DOCUMENTO_IDENTIFICACAO )
                        .build() )
                .build();

        return pendingDocumentRepository.findAll( createSpec( searchCriteria ) );
    }

    @Override
    public List<PendingDocument> getPendingIdentificationDocumentsPending(Long proposalNumber, String brand, String intervenerType) {

        PendingDocument searchCriteria = PendingDocument.builder()
                .config( DocumentationNote.builder()
                        .id( ProposalKey.builder()
                                .number( proposalNumber )
                                .brand( brand )
                                .build() )
                        .build() )
                .entityType( Owner.builder().id(intervenerType).build() )
                .status( Status.builder().build() )
                .possibleDocument( Document.builder()
                        .id( PendingDocumentationConstants.ID_PENDENTE_DOCUMENTO_IDENTIFICACAO )
                        .build() )
                .build();

        return pendingDocumentRepository.findAll( createSpec( searchCriteria ) );
    }

    @Override
    public List<Document> getPossibleDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public List<Document> getPossibleDocumentsByCategoryAndUser(String categoryId, Integer authorizationLevel) {
        return documentRepository.findAll( createSpec(categoryId, Optional.ofNullable(authorizationLevel) ) );
    }

    @Override
    public List<Status> getStatus() {
        return statusRepository.findByActive( true );
    }

    @Override
    public Page<History> getHistory(String proposalNumber, String brand, Pageable pageable) {

        final AuditTrailContext contextFpend002 = AuditTrailContext.builder()
                .library( "checklist" )
                .table( "FPEND002" )
                .key1( proposalNumber )
                .key2( brand )
                .build();

        final AuditTrailContext contextFpend001 = AuditTrailContext.builder()
                .library( "checklist" )
                .table( "FPEND001" )
                .key2( proposalNumber )
                .key3( brand )
                .build();

        AuditTrailSearchCriteria sc = AuditTrailSearchCriteria.builder()
                .context( List.of(contextFpend001, contextFpend002) )
                .build();

        return historyRepository.findAll( createSpec(sc), pageable );
    }

    @Override
    public Page<History> getPendingDocumentHistory(Long pendingDocumentId, Pageable pageable) {
        final AuditTrailContext contextFpend001 = AuditTrailContext.builder()
                .library( "CBDADOS" )
                .table( "FPEND001" )
                .key1( pendingDocumentId.toString() )
                .build();

        AuditTrailSearchCriteria sc = AuditTrailSearchCriteria.builder()
                .context( List.of(contextFpend001) )
                .build();

        return historyRepository.findAll( createSpec(sc), pageable );
    }

    @Override
    public List<History> getPendingDocumentHistory(Long pendingDocumentId) {
        final AuditTrailContext contextFpend001 = AuditTrailContext.builder()
                .library( "CBDADOS" )
                .table( "FPEND001" )
                .key1( pendingDocumentId.toString() )
                .build();

        AuditTrailSearchCriteria sc = AuditTrailSearchCriteria.builder()
                .context( List.of(contextFpend001) )
                .build();

        return historyRepository.findAll( createSpec(sc) );
    }

    @Override
    public List<Reason> getMotives() {
        return reasonRepository.findAll();
    }

    @Override
    public List<Reason> getMotivesByCategory(String categoryId) {

        return reasonCategoryRepository.findById_Category(categoryId).stream()
                .filter( relation -> relation.getId().getMotive().isActive() )
                .map( relationFiltered -> relationFiltered.getId().getMotive() )
                .collect( Collectors.toList());
    }

    @Override
    public Integer countPendingDocumentsPending( Long proposalNumber, String brand ) {

        return (int) pendingDocumentRepository.count( createSpecCount( ProposalKey.builder()
                .number( proposalNumber )
                .brand( brand ).build() ) );
    }

    @Override
    public Status getStatusById(Long statusId ) {
        return statusRepository.findById( statusId ).orElseThrow(
                () -> new ApiException( "Status not found", HttpStatus.NOT_FOUND ) );
    }

    @Override
    public Pack getPendingDocumentationPack(PendingDocumentsCommand command ) {

        return packRepository.findOne( createSpec(command) ).orElseThrow(
                () -> new ApiException( "Pack not found", HttpStatus.NOT_FOUND ) );
    }

    @Override
    public List<VendorNotification> findNotificationNotesByVendor(String vendorEntityCode ) {
        return vendorNotificationRepository.findAll( createSpec( Optional.ofNullable( vendorEntityCode ) ) );
    }

    @Override
    public PendingDocument getPendingDocumentById(Long id ) {
        return pendingDocumentRepository.findById( id ).orElseThrow(
                () -> new ApiException( "Pending Document not found", HttpStatus.NOT_FOUND ) );
    }

    @Override
    public List<DocumentationNote> getConfigurations() {
        return documentationNoteRepository.findAll();
    }
}
