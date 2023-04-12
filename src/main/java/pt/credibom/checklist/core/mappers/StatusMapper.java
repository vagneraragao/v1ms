package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.Status;
import pt.credibom.checklist.interfaces.command.StatusCommand;

@Component
@RequiredArgsConstructor
public class StatusMapper {
	public Status fromCommandToEntity( StatusCommand command ) {
		return Status.builder()
				.id( Integer.getInteger( command.getId().getCode() ) )
				.active( command.isActive() )
				.description( command.getDescription() )
				.build();
	}
}
