package pt.credibom.checklist.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.pendingdocumentation.VendorNotification;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingDocumentationNotificationNotesResponse {
	private List<VendorNotification> notificationNotes;
}
