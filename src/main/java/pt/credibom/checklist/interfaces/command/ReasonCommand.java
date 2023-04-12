package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.ValueObject;

/**
 * Tabela motivos da documentação pendente
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND005 - reason*/
public class ReasonCommand implements ValueObject<Integer> {

	private static final long serialVersionUID = -7248619084019013115L;

    private Integer id;

    @Size(max = 80)
	@NotNull
    private String description;

    private boolean active;

	@Override
	public Integer getCode() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
