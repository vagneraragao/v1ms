package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.ValueObject;

/**
 * Documentação pendente possível
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND001*/
public class PossibleDocumentCommand implements ValueObject<Integer> {

    private Integer id;

    @Size(max = 80)
	@NotNull
    private String description;

	@NotNull
    private SubTypeCommand type;

	@NotNull
    private CategoryCommand category;

	@NotNull
    private MultiplesCommand multiples;

	@NotNull
    private Integer authorizationLevel;

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
