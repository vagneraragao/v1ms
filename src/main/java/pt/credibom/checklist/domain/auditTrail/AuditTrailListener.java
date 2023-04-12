package pt.credibom.checklist.domain.auditTrail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditTrailListener {

	private final ObjectMapper mapper = new ObjectMapper();

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate( Object o ) throws JsonProcessingException {
		//TODO - Save auditing info to Historic Table (HGENE001)
		log.debug( "Object saved: " + mapper.writeValueAsString( o ) );
	}
}
