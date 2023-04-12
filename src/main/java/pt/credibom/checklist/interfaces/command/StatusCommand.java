package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.StatusEnum;
import pt.credibom.checklist.domain.common.ValueObject;

/**
 * Estado da documentação pendente
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND004 - status*/
public class StatusCommand implements ValueObject<Integer> {

	private static final long serialVersionUID = 4650243620510551231L;

	private StatusEnum id;

    @Size(max = 20)
	@NotNull
    private String description;

    private boolean active;

	@Override
	public Integer getCode() {
		return Integer.getInteger(id.getCode());
	}

	@Override
	public String getDescription() {
		return description;
	}
}
