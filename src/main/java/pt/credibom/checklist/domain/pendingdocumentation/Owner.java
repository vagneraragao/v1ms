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
 * Tipos de Entidades
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "OWNER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*FTB0202F + enum entidades*/
public class Owner implements ValueObject<String> {

    public Owner(String id) {
        this.id = id;
    }
    @Id
    @Column(name = "ID", length = 3)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @Column(name = "DESCRIPTION")
    @Size(max = 35)
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
