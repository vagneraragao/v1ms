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
import pt.credibom.checklist.domain.common.ClientTypeEnum;

/**
 * @author Nuno Figueiredo Barata
 *
 */
@Immutable
@Entity
@Table(schema = "CBDADOS", name = "TGENE043")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TGENE043 {

    @Id
    @Column(name = "IDTIPOCLIENTE", length = 1)
    //@Type(type = CLIENT_TYPE_ENUM_TYPE)
    private ClientTypeEnum id;

    @Column(name = "DESCRICAO")
    @Size(max = 15)
    @JsonProperty("descricao")
    private String description;

}
