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
 * Tabela motivos da documentação pendente
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "REASON")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND005 - reason*/
public class Reason implements ValueObject<Integer> {

	private static final long serialVersionUID = -7248619084019013115L;

	@Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION")
    @Size(max = 80)
	@JsonProperty("descricao")
    private String description;

    @Column(name = "ACTIVE_FLAG")
	@JsonProperty("activo")
	@Convert(converter = BooleanNumericConverter.class )
    private boolean active;

	@Override
	public Integer getCode() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
