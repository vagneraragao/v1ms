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
@Table(schema = "CBDADOS", name = "TGENE045")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TGENE045 {

    @Id
    @Column(name = "IDTIPOVIATURA", length = 1)
    //@Type(type = PRODUCT_SUB_TYPE)
    private ProductSubTypeEnum id;

    @Column(name = "DESCRICAO")
    @Size(max = 10)
    @JsonProperty("descricao")
    private String description;
}
