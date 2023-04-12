package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.BusinessTypeEnum;
import pt.credibom.checklist.domain.common.ClientTypeEnum;
import pt.credibom.checklist.domain.common.FinancingTypeEnum;
import pt.credibom.checklist.domain.ProductSubTypeEnum;
import pt.credibom.checklist.domain.TiposReservaEnum;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/*TPEND007 - pack*/
public class PackCommand {

	private static final long serialVersionUID = 1837691037918477868L;

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private FinancingTypeEnum product;

    private BusinessTypeEnum business;

    private ClientTypeEnum clientType;

    @NotNull
    private Boolean livranca;

    @NotNull
    private Boolean insurance;

    private TiposReservaEnum reservationType;

    private ProductSubTypeEnum vehicleType;

    private List<PackPendingCommand> possibleDocuments;
}
