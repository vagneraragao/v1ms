package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(schema = "checklist", name = "HISTORY")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*HGENE001 - audittrail*/
public class History /*implements HGENEInterface*/{

    @Id
    @GeneratedValue
    @Column(name = "ID", columnDefinition = "bigint")
    private Long id;

    @Column(name = "SCHEMA_NAME", insertable = false, updatable = false)
    @Size(max = 10)
   // @Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("biblioteca")
    private String library;

    @Column(name = "TABLE_NAME", insertable = false, updatable = false)
    @Size(max = 10)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("tabela")
    private String table;

    @Column(name = "KEY_1")
    @Size(max = 30)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("chave1")
    private String key1;

    @Column(name = "KEY_2")
    @Size(max = 30)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("chave2")
    private String key2;

    @Column(name = "KEY_3")
    @Size(max = 30)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("chave3")
    private String key3;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "SCHEMA_NAME", referencedColumnName = "SCHEMA_NAME"),
            @JoinColumn(name = "TABLE_NAME", referencedColumnName = "TABLE_NAME"),
            @JoinColumn(name = "FIELD_NAME", referencedColumnName = "FIELD_NAME") })
    @JsonProperty("campo")
    private HistoryField field;

    @Column(name = "ACTION")
    @Size(max = 1)
    @JsonProperty("accao")
    private String action;

    @Column(name = "IMAGE")
    @Size(max = 1)
    private String image;

    @Column(name = "VALUE")
    @Size(max = 100)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("valor")
    private String value;

    @Column(name = "CREATION_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("data")
    private Date date;

    @Column(name = "UPDATE_USER")
    @Size(max = 10)
    //@Type(type = DomainObject.TRIMMED_STRING_TYPE_CHAR)
    @JsonProperty("utilizador")
    private String user;

    @Transient
    @JsonProperty("ultimoValor")
    private String lastValue;
}
