package pt.credibom.checklist.interfaces.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pt.credibom.checklist.core.TestService;
import pt.credibom.checklist.domain.TestEntity;
import pt.credibom.checklist.interfaces.command.TestCommand;

@RestController
@RequiredArgsConstructor
public class TestEndpoints extends AbstractEndpoints {

	private final TestService testService;

	@GetMapping( "/tests" )
	public ResponseEntity<List<TestEntity>> loadAll( ) {
		return ResponseEntity.ok( testService.loadAllEntities() );
	}

	@PostMapping( "/tests" )
	public ResponseEntity<TestEntity> createEntity( @RequestBody  TestCommand command ) {
		return ResponseEntity.ok( testService.createTest( command ) );
	}
	
}
