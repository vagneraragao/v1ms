package pt.credibom.checklist.interfaces.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.BusinessTypeEnum;
import pt.credibom.checklist.domain.common.ClientTypeEnum;
import pt.credibom.checklist.domain.common.FinancingTypeEnum;
import pt.credibom.checklist.domain.common.IntervenerTypeEnum;
import pt.credibom.checklist.domain.ProductSubTypeEnum;
import pt.credibom.checklist.domain.TiposReservaEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingDocumentsCommand implements Serializable {

	private static final long serialVersionUID = 3029146104745627392L;

	Optional<FinancingTypeEnum> financingType;

	Optional<BusinessTypeEnum> businessArea;

	Optional<ClientTypeEnum> clientType;

	Optional<Boolean> livranca;

	Optional<Boolean> insurance;

	Optional<TiposReservaEnum> reservationType;

	Optional<ProductSubTypeEnum> vehicleConditionType;

	Optional<IntervenerTypeEnum> owner;

	Optional<Integer> profession;

	Optional<BigDecimal> grossIncome ;

}
