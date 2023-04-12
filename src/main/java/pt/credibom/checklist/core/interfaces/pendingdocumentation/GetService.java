package pt.credibom.checklist.core.interfaces.pendingdocumentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pt.credibom.checklist.domain.common.IntervenerTypeEnum;
import pt.credibom.checklist.domain.pendingdocumentation.*;
import pt.credibom.checklist.interfaces.command.PendingDocumentsCommand;


import java.util.List;

public interface GetService {


    List<Category> getCategories();//getCategorias

    PendingDocument getPendingDocument(final Long proposalNumber, final String brand, final IntervenerTypeEnum intervenerType, final int pendingPossibleDocument);

    List<PendingDocument> getPendingIdentificationDocuments(final Long proposalNumber, final String brand, final String intervenerType);

    List<PendingDocument> getPendingIdentificationDocumentsPending(final Long proposalNumber, final String brand, String intervenerType);

    List<Document> getPossibleDocuments();

    List<Document> getPossibleDocumentsByCategoryAndUser(String categoryId, Integer authorizationLevel);

    List<Status> getStatus();

    Page<History> getHistory(String proposal, String brand, Pageable pageable);

    Page<History> getPendingDocumentHistory(Long pendingDocumentId, Pageable pageable);

    List<History> getPendingDocumentHistory(Long pendingDocumentId);

    List<Reason> getMotives();

    List<Reason> getMotivesByCategory(String categoryId);

    Integer countPendingDocumentsPending(Long proposalNumber, String brand);

    Status getStatusById(Long statusId );


    Pack getPendingDocumentationPack( PendingDocumentsCommand command );

	List<VendorNotification> findNotificationNotesByVendor(String vendorEntityCode );

    PendingDocument getPendingDocumentById(Long id );

    List<DocumentationNote> getConfigurations();
}
