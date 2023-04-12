package pt.credibom.checklist.core.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pt.credibom.checklist.domain.pendingdocumentation.Document;
import pt.credibom.checklist.domain.pendingdocumentation.Document_;
import pt.credibom.checklist.domain.pendingdocumentation.Category_;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DocumentSpecificationBuilder {

	public static Specification<Document> createSpec(final String categoryId, final Optional<Integer> authorizationLevel){
		return new Specification<Document>() {

			@Override
			public Predicate toPredicate(final Root<Document> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				predicates.add(cb.equal(root.get(Document_.category).get( Category_.id), categoryId));
				predicates.add(cb.equal(root.get(Document_.category).get( Category_.active), true));

				authorizationLevel.ifPresent( authLevel -> {
					predicates.add(cb.lessThanOrEqualTo(root.get(Document_.authorizationLevel), authLevel));
				} );

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

}
