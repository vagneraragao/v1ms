package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Configuração Documentação pendente
 */
@Entity
@Table(schema = "checklist", name = "DOCUMENTATION_NOTE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*FPEND002 - tabela de referência*/
public class DocumentationNote {

    @EmbeddedId
    @AttributeOverrides({ 
    	@AttributeOverride(name = "number", column = @Column(name = "PROPOSAL_ID")),
        @AttributeOverride(name = "brand", column = @Column(name = "BRAND"))})
    ProposalKey id;

    @Column(name = "INTERNAL_NOTE")
    @Size(max = 300)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("comentarioInterno")
    private String internalNotes;

    @Column(name = "EXTERNAL_NOTE")
    @Size(max = 300)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("comentarioExterno")
    private String externalNotes;

    @Column(name = "UPDATE_USER")
    //@Type(type = TRIMMED_TO_NULL_STRING_TYPE)
    @JsonProperty("utilizador")
    private String user;

    @Column(name = "AUTHORIZATION_USER")
    //@Type(type = TRIMMED_TO_NULL_STRING_TYPE)
    @JsonProperty("autorizador")
    private String authorizer;

}
