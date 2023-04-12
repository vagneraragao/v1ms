package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentType;
import pt.credibom.checklist.interfaces.command.SubTypeCommand;

@Component
@RequiredArgsConstructor
public class SubTypeMapper {
	public DocumentType fromCommandToEntity( SubTypeCommand command ) {
		return DocumentType.builder()
				.id( command.getId() )
				.description( command.getDescription() )
				.active( command.isActive() )
				.build();
	}
}
