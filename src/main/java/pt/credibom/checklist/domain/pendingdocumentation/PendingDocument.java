package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.auditTrail.AuditTrailListener;
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "checklist", name = "PENDING_DOCUMENT")
@EntityListeners( AuditTrailListener.class )
/*FPEND001 - tabela de referência*/
public class PendingDocument implements Serializable {

	private static final long serialVersionUID = 3869597544877713249L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_ID", referencedColumnName = "PROPOSAL_ID"),
            @JoinColumn(name = "BRAND", referencedColumnName = "BRAND") })
    private DocumentationNote config;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    @JsonProperty("documentoPossivel")
    private Document possibleDocument;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonProperty("estado")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "REASON_ID")
    @JsonProperty("motivo")
    private Reason reason;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    //@Type(type=TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("tipoEntidade")
    private Owner entityType;

    @Column(name = "MANUAL_FLAG")
    @Convert(converter = BooleanNumericConverter.class )
    private boolean manual;

    @Column(name = "UPDATE_USER")
    //@Type(type = TRIMMED_TO_NULL_STRING_TYPE)
    @JsonProperty("utilizador")
    private String user;

    @Column(name = "GDMS_GUID")
    @Size(max = 40)
    //@Type(type = DomainObject.TRIMMED_TO_NULL_STRING_TYPE_CHAR)
    @JsonProperty("documentoAlfrescoId")
    String documentId;

    @Column(name = "CREATION_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("data")
    private Date date;

	@Column(name = "UPDATE_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("dataAlteracao")
    private Date modifiedDate;

}
