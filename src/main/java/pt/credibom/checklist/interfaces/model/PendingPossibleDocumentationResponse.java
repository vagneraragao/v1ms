package pt.credibom.checklist.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.pendingdocumentation.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingPossibleDocumentationResponse {
	private List<Document> documents;
}
