package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pt.credibom.checklist.domain.pendingdocumentation.Reason;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Long>, JpaSpecificationExecutor<Reason> {

}
