package pt.credibom.checklist.domain.pendingdocumentation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProposalKey implements Serializable {

	private static final long serialVersionUID = -3167070409491248043L;

	@Column(name = "CONCON")
    private Long number;

    @Column(name = "BRAND")
    @Size(max = 10)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    private String brand;//não pode ser null pq é chave
}
