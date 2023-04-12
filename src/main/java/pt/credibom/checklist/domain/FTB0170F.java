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
import pt.credibom.checklist.domain.common.FinancingTypeEnum;

/**
 * @author jpfialho
 *
 */
@Entity
@Immutable
@Table(schema = "CBTABLIB", name = "FTB0170F")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FTB0170F {

    @Id
    @Column(name = "AUPROD", length = 1)
    //@Type(type = FINANCING_TYPE_ENUM_TYPE_PENDING_DOCUMENTATION_CODE)
    private FinancingTypeEnum id;

    @Column(name = "CODDESC")
    @Size(max = 15)
    @JsonProperty("descricao")
    private String description;

}
