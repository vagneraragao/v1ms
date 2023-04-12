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
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

/**
 *
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "MULTIPLE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND009 - multiples*/
public class Multiple {

	private static final long serialVersionUID = -5735422662626944519L;

	@Id
    @Column(name = "ID", length = 1)
    //@Type(type = MULTIPLOS_ENUM_TYPE)
    private String id;

    @Column(name = "DESCRIPTION")
    @Size(max = 10)
	@JsonProperty("descricao")
    private String description;

    @Column(name = "ACTIVE_FLAG")
	@JsonProperty("activo")
	@Convert(converter = BooleanNumericConverter.class )
    private boolean active;
}
