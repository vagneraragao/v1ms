package pt.credibom.checklist.interfaces.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/*TPEND008*/
public class PackPendingCommand {

	private static final long serialVersionUID = -8762695013765501735L;

	PackPendingPKCommand id;
}
