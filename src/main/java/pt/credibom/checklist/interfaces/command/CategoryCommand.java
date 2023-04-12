package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.ValueObject;

/**
 * Categoria de documentação pendente
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND003 - category*/
public class CategoryCommand implements ValueObject<String> {

	private static final long serialVersionUID = 7700372395508303850L;

    @Size(max = 1)
    private String id;

    @Size(max = 35)
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
