package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.Document;
import pt.credibom.checklist.interfaces.command.PossibleDocumentCommand;

@Component
@RequiredArgsConstructor
public class PossibleDocumentMapper {

	private final CategoryMapper categoryMapper;

	private final MultiplesMapper multiplesMapper;

	private final SubTypeMapper subTypeMapper;

	public Document fromCommandToEntity( PossibleDocumentCommand command ) {
		return Document.builder()
				.id( command.getId() )
				.active( command.isActive() )
				.authorizationLevel( command.getAuthorizationLevel() )
				.category( categoryMapper.fromCommandToEntity( command.getCategory() ) )
				.multiples( multiplesMapper.fromCommandToEntity( command.getMultiples() ) )
				.type( subTypeMapper.fromCommandToEntity( command.getType() ) )
				.build();
	}
}
