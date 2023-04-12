package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * Tabela configuração AuditTrail - campos
 * primary key
 *
 */
@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryFieldPK implements Serializable {

    @Column(name = "SCHEMA_NAME")
    @Size(max = 10)
    //@Type(type=TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("biblioteca")
    private String library;

    @Column(name = "TABLE_NAME")
    @Size(max = 10)
    //@Type(type=TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("tabela")
    private String table;

    @Column(name = "FIELD_NAME")
    @Size(max = 10)
    //@Type(type=TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("campo")
    private String field;
}
