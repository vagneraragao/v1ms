package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import pt.credibom.checklist.domain.common.ValueObject;
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

/**
 * SubTipo de documentação pendente
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "DOCUMENT_TYPE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND002*/
public class DocumentType implements ValueObject<String> {

	private static final long serialVersionUID = -3468811986457525975L;

	@Id
    @Column(name = "ID")
    @Size(max = 1)
    private String id;

    @Column(name = "DESCRIPTION")
    @Size(max = 15)
	@JsonProperty("descricao")
    private String description;

    @Column(name = "ACTIVE_FLAG")
	@JsonProperty("activo")
	@Convert(converter = BooleanNumericConverter.class )
    private boolean active;

	@Override
	public String getCode() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
