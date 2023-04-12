package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.Reason;
import pt.credibom.checklist.interfaces.command.ReasonCommand;

@Component
@RequiredArgsConstructor
public class ReasonMapper {
	public Reason fromCommandToEntity( ReasonCommand command ) {
		return Reason.builder()
				.id( command.getId() )
				.description( command.getDescription() )
				.active( command.isActive() )
				.build();
	}
}
