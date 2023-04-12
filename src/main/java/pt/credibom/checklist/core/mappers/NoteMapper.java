package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.interfaces.command.NoteCommand;

@Component
@RequiredArgsConstructor
public class NoteMapper {
	private final ProposalKeyMapper proposalKeyMapper;

	public DocumentationNote fromCommandToEntity( NoteCommand command ) {
		return DocumentationNote.builder()
				.id( proposalKeyMapper.fromCommandToEntity( command.getId() ) )
				.authorizer( command.getAuthorizer() )
				.externalNotes( command.getExternalNotes() )
				.internalNotes( command.getInternalNotes() )
				.user( command.getUser() )
				.build();
	}
}
