package pt.credibom.checklist.core.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pt.credibom.checklist.domain.pendingdocumentation.*;

import java.util.ArrayList;
import java.util.List;

public class PendingDocumentSpecificationBuilder {
	static final int ID_ESTADO_CONFORME = 1;

	static final int ID_ESTADO_NAO_RECEBIDO = 3;

	static final int ID_ESTADO_NAO_APLICAVEL = 4;

	public static Specification<PendingDocument> createSpec(final PendingDocument sc){
		return new Specification<PendingDocument>() {

			@Override
			public Predicate toPredicate(final Root<PendingDocument> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				if (sc != null) {

					if (sc.getConfig() != null) {
						if (sc.getConfig().getId() != null) {
							if (sc.getConfig().getId().getNumber() != null) {
								predicates.add(cb.equal(root.get( PendingDocument_.config).get( DocumentationNote_.id).get(
										ProposalKey_.number), sc.getConfig().getId().getNumber()));
							}

							if (sc.getConfig().getId().getBrand() != null) {
								predicates.add(cb.equal(root.get(PendingDocument_.config).get(DocumentationNote_.id)
										.get(ProposalKey_.brand), sc.getConfig().getId().getBrand()));
							}
						}
					}

					/*
					 * Procura todos os documentos (em que estado for) se o objecto Estado no sc for null
					 * Procura todos os que não estão no estado ok se o id do estado passado no sc for null
					 * Procura os documentos no estado x se o id do estado passado no sc não for null
					 */
					if (sc.getStatus() != null) {
						if (sc.getStatus().getId() != null) {
							predicates.add(cb.equal(root.get(PendingDocument_.status)
									.get( Status_.id), sc.getStatus().getId()));
						}
						else {
							predicates.add(cb.notEqual(root.get(PendingDocument_.status)
									.get(Status_.id), ID_ESTADO_CONFORME));
							predicates.add(cb.notEqual(root.get(PendingDocument_.status)
									.get(Status_.id), ID_ESTADO_NAO_APLICAVEL));
						}
					}

					if (sc.getEntityType() != null) {
						predicates.add(cb.equal(root.get(PendingDocument_.entityType), sc.getEntityType()));
					}

					if(sc.getPossibleDocument()!=null) {
						if(sc.getPossibleDocument().getId()!=null) {
							predicates.add(cb.equal(root.get(PendingDocument_.possibleDocument)
									.get( Document_.id), sc.getPossibleDocument().getId()));
						}
					}
				}

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static Specification<PendingDocument> createSpec(final ProposalKey key) {
		return new Specification<>() {

			@Override
			public Predicate toPredicate(final Root<PendingDocument> root, final CriteriaQuery<?> query,
                                         final CriteriaBuilder cb ) {
				List<Predicate> predicates = new ArrayList<>();

				if (key.getNumber() != null) {
					predicates.add( cb.equal( root.get( PendingDocument_.config ).get( DocumentationNote_.id ).get( ProposalKey_.number ),
							key.getNumber() ) );
				}
				if (key.getBrand() != null) {
					predicates.add( cb.equal( root.get( PendingDocument_.config ).get( DocumentationNote_.id ).get( ProposalKey_.brand ),
							key.getBrand() ) );
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static Specification<PendingDocument> createSpecCount(final ProposalKey key ) {
		return new Specification<PendingDocument>() {

			@Override
			public Predicate toPredicate(final Root<PendingDocument> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				predicates.add(cb.equal(root.get(PendingDocument_.config).get(DocumentationNote_.id).get(ProposalKey_.number), key.getNumber()));
				predicates.add(cb.equal(root.get(PendingDocument_.config).get(DocumentationNote_.id).get(ProposalKey_.brand), key.getBrand()));

				predicates.add(cb.notEqual(root.get(PendingDocument_.status).get(Status_.id), ID_ESTADO_CONFORME));
				predicates.add(cb.notEqual(root.get(PendingDocument_.status).get(Status_.id), ID_ESTADO_NAO_APLICAVEL));

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static Specification<PendingDocument> createSpec(Long proposalNumber, String brand, Integer documentTypeId,
                                                            String entityType ) {
		return new Specification<PendingDocument>() {

			@Override
			public Predicate toPredicate(final Root<PendingDocument> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				predicates.add(cb.equal(root.get(PendingDocument_.config).get(DocumentationNote_.id).get(ProposalKey_.number), proposalNumber));
				predicates.add(cb.equal(root.get(PendingDocument_.config).get(DocumentationNote_.id).get(ProposalKey_.brand), brand));
				predicates.add(cb.equal(root.get(PendingDocument_.possibleDocument).get(Document_.id), documentTypeId));
				predicates.add(cb.equal(root.get(PendingDocument_.entityType).get(Owner_.id), entityType));

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
