package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.ValueObject;

/**
 * SubTipo de documentação pendente
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND002*/
public class SubTypeCommand implements ValueObject<String> {

	private static final long serialVersionUID = -3468811986457525975L;

    @Size(max = 1)
    private String id;

    @Size(max = 15)
	@NotNull
    private String description;

    private boolean active;

	@Override
	public String getCode() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
