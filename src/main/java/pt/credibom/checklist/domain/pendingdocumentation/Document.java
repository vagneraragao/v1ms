package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * Documentação pendente possível
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "DOCUMENT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND001*/
public class Document implements ValueObject<Integer> {

    @Id
    @Column(name = "ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DESCRIPTION")
    @Size(max = 80)
	@JsonProperty("descricao")
    private String description;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_TYPE_ID")
	@JsonProperty("tipo")
    private DocumentType type;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
	@JsonProperty("categoria")
    private Category category;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "MULTIPLE_ID")
	@JsonProperty("multiplos")
    private Multiple multiples;

    @Column(name = "AUTHORIZATION_FLAG")
	@JsonProperty("nivelAutorizacao")
    private Integer authorizationLevel;

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
