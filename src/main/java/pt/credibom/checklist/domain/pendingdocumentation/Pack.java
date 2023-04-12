package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import pt.credibom.checklist.domain.*;
import pt.credibom.checklist.domain.common.BusinessTypeEnum;
import pt.credibom.checklist.domain.common.ClientTypeEnum;
import pt.credibom.checklist.domain.common.FinancingTypeEnum;
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

import java.util.List;

/**
 * Tabela packs documentação pendente
 *
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "PACK")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/*TPEND007 - Pack*/
public class Pack {

	private static final long serialVersionUID = 1837691037918477868L;
    public static final String FINANCING_TYPE_ENUM_TYPE_PENDING_DOCUMENTATION_CODE = "financingTypeEnumPendingDocumentationCode";

	@Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    @Size(max = 20)
    @JsonProperty("descricao")
    private String description;

    @Column(name = "PRODUCT_CODE")
    @JsonProperty("produto")
    private FinancingTypeEnum product;

    @Column(name = "BUSINESS_CODE", nullable = true)
    @JsonProperty("negocio")
    private BusinessTypeEnum business;

    @Column(name = "CLIENT_TYPE_CODE", nullable = true)
    @JsonProperty("tipoCliente")
    private ClientTypeEnum clientType;

    @Column(name = "BAIL_FLAG")
    @Convert(converter = BooleanNumericConverter.class )
    private Boolean livranca;

    @Column(name = "INSURANCE_FLAG")
    @JsonProperty("seguro")
    @Convert(converter = BooleanNumericConverter.class )
    private Boolean insurance;

    @Column(name = "RESERVATION_TYPE", nullable = true)
    @JsonProperty("tipoReserva")
    private TiposReservaEnum reservationType;

    @Column(name = "VEHICLE_TYPE", nullable = true)
    @JsonProperty("tipoViatura")
    private ProductSubTypeEnum vehicleType;

    @OneToMany(mappedBy = "pack")
    @JsonProperty("documentosPossiveis")
    private List<DocumentPack> possibleDocuments;
}
