package pt.credibom.checklist.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Nuno Figueiredo Barata
 *
 */
@Immutable
@Entity
@Table(schema ="CBDADOS", name = "TGENE044")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TGENE044 {

    @Id
    @Column(name = "IDTIPORESERVA", length=1)
    //@Type(type = TIPOSRESERVA_ENUM_TYPE)
    private TiposReservaEnum id;

    @Column(name = "DESCRICAO")
    @Size(max = 15)
    @JsonProperty("descricao")
    private String description;
}
