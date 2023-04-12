
package pt.credibom.checklist.core.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pt.credibom.checklist.domain.pendingdocumentation.Pack_;
import pt.credibom.checklist.domain.pendingdocumentation.Pack;
import pt.credibom.checklist.interfaces.command.PendingDocumentsCommand;


import java.util.ArrayList;
import java.util.List;

public class PackSpecificationBuilder {

	public static Specification<Pack> createSpec(final PendingDocumentsCommand sc){
		return new Specification<Pack>() {


			@Override
			public Predicate toPredicate(final Root<Pack> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				sc.getFinancingType().ifPresent( financingType -> {
					predicates.add( cb.equal( root.get( Pack_.product ), financingType ) );

				} );

				sc.getBusinessArea().ifPresentOrElse( businessTypeEnum -> {
					predicates.add( cb.equal( root.get( Pack_.business ), businessTypeEnum ) );
				}, () -> {
					predicates.add(cb.isNull(root.get(Pack_.business)));
				} );

				sc.getClientType().ifPresentOrElse( clientTypeEnum -> {
					predicates.add( cb.equal( root.get( Pack_.clientType ), clientTypeEnum ) );
				}, () -> {
					predicates.add( cb.isNull( root.get(Pack_.clientType) ) );
				} );

				sc.getLivranca().ifPresent( livranca -> {
					predicates.add( cb.equal( root.get( Pack_.livranca ), livranca ) );
				});

				sc.getInsurance().ifPresent( insurance -> {
					predicates.add( cb.equal( root.get( Pack_.insurance ), insurance ) );

				} );

				sc.getReservationType().ifPresentOrElse( tiposReservaEnum -> {
					predicates.add( cb.equal( root.get( Pack_.reservationType ),
							tiposReservaEnum ) );
				}, () -> {
					predicates.add( cb.isNull( root.get( Pack_.reservationType ) ) );
				} );

				sc.getVehicleConditionType().ifPresentOrElse( productSubTypeEnum -> {
					predicates.add( cb.equal( root.get( Pack_.vehicleType ),
							productSubTypeEnum ) );
				}, () -> {
					predicates.add( cb.isNull( root.get( Pack_.vehicleType ) ) );
				} );

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

