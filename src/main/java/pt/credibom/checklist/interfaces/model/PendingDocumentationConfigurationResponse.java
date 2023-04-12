package pt.credibom.checklist.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingDocumentationConfigurationResponse {
	private List<DocumentationNote> configurations;
}
