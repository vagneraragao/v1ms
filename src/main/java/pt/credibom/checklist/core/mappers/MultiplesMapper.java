package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.Multiple;
import pt.credibom.checklist.interfaces.command.MultiplesCommand;

@Component
@RequiredArgsConstructor
public class MultiplesMapper {

	public Multiple fromCommandToEntity( MultiplesCommand command ) {
		return Multiple.builder()
				.id( command.getId() )
				.description( command.getDescription() )
				.active( command.isActive() )
				.build();
	}
}
