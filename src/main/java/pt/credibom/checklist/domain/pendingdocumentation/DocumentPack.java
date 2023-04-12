package pt.credibom.checklist.domain.pendingdocumentation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Tabela relação packs documentação pendente
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "DOCUMENT_PACK")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/*TPEND008*/
public class DocumentPack {

	private static final long serialVersionUID = -8762695013765501735L;

	@EmbeddedId
	DocumentPackPK id;

	@ManyToOne
	@MapsId("packId")
	private Pack pack;

	@ManyToOne
	@MapsId("documentId")
	private Document document;
}
