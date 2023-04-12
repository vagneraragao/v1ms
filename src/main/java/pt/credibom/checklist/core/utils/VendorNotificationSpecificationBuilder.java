
package pt.credibom.checklist.core.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pt.credibom.checklist.domain.pendingdocumentation.VendorNotification;
import pt.credibom.checklist.domain.pendingdocumentation.VendorNotificationPK_;
import pt.credibom.checklist.domain.pendingdocumentation.VendorNotification_;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendorNotificationSpecificationBuilder {

	public static Specification<VendorNotification> createSpec(Optional<String> vendorEntityCode){
		return new Specification<VendorNotification>() {

			@Override
			public Predicate toPredicate(final Root<VendorNotification> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				vendorEntityCode.ifPresent( vendor -> {
					predicates.add(cb.equal(root.get( VendorNotification_.id).get( VendorNotificationPK_.vendor), vendor));

				} );

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

