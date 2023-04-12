package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Relação entre documentação pendente possível e motivos
 *
 * @author Nuno Figueiredo Barata
 *
 */
@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Reason_CategoryPK implements Serializable {

	private static final long serialVersionUID = 135031581954797010L;

	@ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
	@JsonProperty("categoria")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "REASON_ID")
	@JsonProperty("motivo")
    private Reason motive;

}
