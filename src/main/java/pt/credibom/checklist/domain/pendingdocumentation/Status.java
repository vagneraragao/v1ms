package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import pt.credibom.checklist.domain.common.ValueObject;
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

/**
 * Estado da documentação pendente
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "STATUS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND004 - status*/
public class Status implements ValueObject<Integer> {

	private static final long serialVersionUID = 4650243620510551231L;

	@Id
    @Column(name = "ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "DESCRIPTION")
    @Size(max = 20)
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
