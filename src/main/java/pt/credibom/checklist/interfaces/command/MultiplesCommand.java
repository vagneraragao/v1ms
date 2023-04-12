package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*TPEND009 - multiples*/
public class MultiplesCommand {

	private static final long serialVersionUID = -5735422662626944519L;

    private String id;

    @Size(max = 10)
	@NotNull
    private String description;

    private boolean active;
}
