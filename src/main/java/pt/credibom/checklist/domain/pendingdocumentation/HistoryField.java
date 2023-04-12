package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

/**
 * Tabela configuração AuditTrail - campos
 *
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "HISTORY_FIELD")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TGENE020*/
public class HistoryField {

    @Id
    private HistoryFieldPK id;

    @Column(name = "SHOW_FLAG", length = 1)
    //@Type(type = DomainObject.YES_NO_TO_BOOLEAN_TYPE)
    @Convert(converter = BooleanNumericConverter.class )
    private Boolean show;

    @Column(name = "SHORT_TEXT")
    //@Size(max = 25)
    @JsonProperty("descricaoCurta")
    private String shortDescription;

    @Column(name = "LONG_TEXT")
    //@Size(max = 50)
    @JsonProperty("descricaoLonga")
    private String longDescription;

}
