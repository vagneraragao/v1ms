package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.ProposalKey;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;

@Component
@RequiredArgsConstructor
public class ProposalKeyMapper {
	public ProposalKey fromCommandToEntity( ProposalKeyCommand command ) {
		return ProposalKey.builder()
				.brand( command.getBrand() )
				.number( command.getNumber() )
				.build();
	}
}
