package pt.credibom.checklist.domain.pendingdocumentation;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Relação entre categorias e motivos
 *
 * @author João Rossa
 *
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "REASON_CATEGORY")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
/*TPEND006*/
public class Reason_Category {

	private static final long serialVersionUID = -5628751819276276540L;

	@EmbeddedId
	Reason_CategoryPK id;
}
