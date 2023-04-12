package pt.credibom.checklist.interfaces.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusCommand {

	@NotNull
	private List<UpdateStatusItemCommand> status;
}
