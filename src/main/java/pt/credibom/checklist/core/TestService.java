package pt.credibom.checklist.core;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.credibom.checklist.domain.TestEntity;
import pt.credibom.checklist.domain.repository.TestRepository;
import pt.credibom.checklist.interfaces.command.TestCommand;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TestRepository testRepository;

	public TestEntity createTest( final TestCommand command ) {
		final TestEntity entity = TestEntity.builder().name( command.getName() ).build();

		// save to repo
		testRepository.save( entity );

		return entity;
	}
	
	public List< TestEntity > loadAllEntities() {
		return testRepository.findAll();
	}
	
}
