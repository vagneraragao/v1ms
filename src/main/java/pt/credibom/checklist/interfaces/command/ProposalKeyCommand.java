package pt.credibom.checklist.interfaces.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProposalKeyCommand {

	private static final long serialVersionUID = -3167070409491248043L;

	private Long number;

	private String brand;
}
