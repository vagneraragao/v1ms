package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.interfaces.command.PendingDocumentCommand;

@Component
@RequiredArgsConstructor
public class PendingDocumentMapper {
	private final NoteMapper noteMapper;

	private final OwnerMapper ownerMapper;
	private final StatusMapper statusMapper;

	private final PossibleDocumentMapper possibleDocumentMapper;

	private final ReasonMapper reasonMapper;

	public PendingDocument fromCommandToEntity( PendingDocumentCommand command ) {
		return PendingDocument.builder()
				.id( command.getId() )
				.config( noteMapper.fromCommandToEntity( command.getConfig() ) )
				.documentId( command.getDocumentId() )
				.entityType( ownerMapper.fromCommandToEntity(command.getEntityType() )  )
				.status( statusMapper.fromCommandToEntity( command.getStatus() ) )
				.possibleDocument( possibleDocumentMapper.fromCommandToEntity( command.getPossibleDocument() ) )
				.user( command.getUser() )
				.date( command.getDate() )
				.manual( command.isManual() )
				.modifiedDate( command.getModifiedDate() )
				.reason( reasonMapper.fromCommandToEntity( command.getReason() ) )
				.build();

	}
}
