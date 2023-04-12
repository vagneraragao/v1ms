package pt.credibom.checklist.core.implementations.pendingdocumentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pt.credibom.checklist.core.interfaces.pendingdocumentation.SearchService;
import pt.credibom.checklist.core.mappers.PendingDocumentMapper;
import pt.credibom.checklist.domain.pendingdocumentation.DocumentationNote;
import pt.credibom.checklist.domain.pendingdocumentation.PendingDocument;
import pt.credibom.checklist.domain.pendingdocumentation.Status;
import pt.credibom.checklist.domain.pendingdocumentation.ProposalKey;
import pt.credibom.checklist.domain.repository.PendingDocumentRepository;
import pt.credibom.checklist.domain.repository.DocumentationNoteRepository;
import pt.credibom.checklist.exceptions.ApiException;
import pt.credibom.checklist.interfaces.command.PendingDocumentCommand;
import pt.credibom.checklist.interfaces.command.ProposalKeyCommand;

import java.util.List;

import static pt.credibom.checklist.core.utils.PendingDocumentSpecificationBuilder.createSpec;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final PendingDocumentRepository pendingDocumentRepository;

	private final DocumentationNoteRepository configurationRepository;

	private final PendingDocumentMapper pendingDocumentMapper;

	@Override
	public DocumentationNote search(Long proposal, String brand ) {
		ProposalKey key = ProposalKey.builder()
				.number( proposal )
				.brand( brand )
				.build();

		return configurationRepository.findById( key )
				.orElseThrow( () -> new ApiException( "Configuration Not Found", HttpStatus.NOT_FOUND ) );
	}

	@Override
	public List<PendingDocument> searchPendingDocumentation( PendingDocumentCommand searchCriteria ) {
		return pendingDocumentRepository.findAll(
				createSpec( pendingDocumentMapper.fromCommandToEntity( searchCriteria ) ));
	}

	@Override
	public Page<PendingDocument> searchPendingDocumentation( PendingDocumentCommand searchCriteria, Pageable pageable ) {
		return pendingDocumentRepository.findAll(
				createSpec( pendingDocumentMapper.fromCommandToEntity( searchCriteria ) ), pageable);
	}

	@Override
	public List<PendingDocument> searchPendingDocumentation( ProposalKeyCommand key ) {

		return pendingDocumentRepository.findAll(createSpec( ProposalKey.builder()
				.number( key.getNumber() )
				.brand( key.getBrand() )
				.build()
		));
	}

	@Override
	public List<PendingDocument> searchPendingPendingDocumentation( ProposalKeyCommand key ) {

		PendingDocument searchCriteria = PendingDocument.builder()
				.config( DocumentationNote.builder().build() )
				.status( Status.builder().build() )
				.build();

		searchCriteria.getConfig().setId( ProposalKey.builder().brand( key.getBrand() ).number( key.getNumber() ).build() );

		return pendingDocumentRepository.findAll(createSpec( searchCriteria ));
	}

	@Override
	public List<PendingDocument> searchAllPendingPendingDocumentation() {
		PendingDocument searchCriteria = PendingDocument.builder()
				.status( Status.builder().build() )
				.build();

		return pendingDocumentRepository.findAll(createSpec( searchCriteria ));
	}

	@Override
	public List<PendingDocument> searchPendingDocumentation(Long proposalNumber, String brand, Integer documentTypeId,
															String entityTpe ) {

		return pendingDocumentRepository.findAll( createSpec( proposalNumber, brand, documentTypeId, entityTpe ));
	}
}
