package pt.credibom.checklist.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.pendingdocumentation.Category;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingDocumentationCategoriesResponse {
	private List<Category> categories;
}
