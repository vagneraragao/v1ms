package pt.credibom.checklist.core.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pt.credibom.checklist.domain.*;
import pt.credibom.checklist.domain.common.AuditTrailContext;
import pt.credibom.checklist.domain.common.LabelValue;
import pt.credibom.checklist.domain.pendingdocumentation.History;
import pt.credibom.checklist.domain.pendingdocumentation.HistoryFieldPK_;
import pt.credibom.checklist.domain.pendingdocumentation.HistoryField_;
import pt.credibom.checklist.domain.pendingdocumentation.History_;

import java.util.List;

public class HistorySpecificationBuilder {


	public static Specification<History> createSpec(final AuditTrailSearchCriteria sc) {
		return new Specification<History>() {
			@Override
			public Predicate toPredicate(final Root<History> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

				Predicate andPredicate = cb.conjunction();
				List<Expression<Boolean>> ands = andPredicate.getExpressions();

				Predicate orTable = cb.disjunction();
				List<Expression<Boolean>> orTables = orTable.getExpressions();
				for ( AuditTrailContext c : sc.getContext()) {
					Predicate andTable = cb.conjunction();
					List<Expression<Boolean>> andTables = andTable.getExpressions();
					andTables.add(cb.equal(root.get( History_.library), c.getLibrary()));
					andTables.add(cb.equal(root.get(History_.table), c.getTable()));
					if (c.getKey1() != null)
						andTables.add(cb.equal(root.get(History_.key1), c.getKey1()));
					if (c.getKey2() != null)
						andTables.add(cb.equal(root.get(History_.key2), c.getKey2()));
					if (c.getKey3() != null)
						andTables.add(cb.equal(root.get(History_.key3), c.getKey3()));

					orTables.add(andTable);
				}
				ands.add(orTable);

				if (sc.isFilterShowOnScreen())
					ands.add(cb.equal(root.get(History_.field).get( HistoryField_.show), true));

				if (sc.getFieldsToShow() != null && sc.getFieldsToShow().size() > 0) {
					Predicate orField = cb.disjunction();
					List<Expression<Boolean>> orFields = orField.getExpressions();
					for (String campo : sc.getFieldsToShow()) {
						orFields.add(cb.equal(root.get(History_.field).get(HistoryField_.id).get( HistoryFieldPK_.field), campo));
					}
					ands.add(orField);
				}

				if (sc.getFieldValue() != null && sc.getFieldValue().size() > 0) {
					Predicate orField = cb.disjunction();
					List<Expression<Boolean>> orFields = orField.getExpressions();
					for ( LabelValue campo : sc.getFieldValue()) {
						if (campo.getValue().contains("||")) {
							String valor = campo.getValue();
							// || não funcionava com  regex do split..
							valor = valor.replace("||", "pipe-pipe");
							String[] values = valor.split("pipe-pipe");
							for (String value : values) {
								if (value != null && value.trim().length() > 0)
									orFields.add(cb.and(
											cb.equal(root.get(History_.field).get(HistoryField_.id).get(HistoryFieldPK_.field),
													campo.getLabel()), cb.equal(root.get(History_.value), value.trim())));
							}
						} else
							orFields.add(cb.and(
									cb.equal(root.get(History_.field).get(HistoryField_.id).get(HistoryFieldPK_.field), campo.getLabel()),
									cb.equal(root.get(History_.value), campo.getValue())));
					}
					ands.add(orField);
				}

				if (sc.getAction() != null)
					ands.add(cb.equal(root.get(History_.action), sc.getAction()));
				if (sc.getImage() != null)
					ands.add(cb.equal(root.get(History_.image), sc.getImage()));
				if (sc.getValue() != null)
					ands.add(cb.equal(root.get(History_.value), sc.getValue()));
				if (sc.getUser() != null)
					ands.add(cb.equal(root.get(History_.user), sc.getUser()));
				return andPredicate;
			}
		};
	}

}
