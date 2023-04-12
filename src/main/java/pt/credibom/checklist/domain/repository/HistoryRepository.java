package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pt.credibom.checklist.domain.pendingdocumentation.History;

import java.util.Date;

@Repository
public interface HistoryRepository extends JpaRepository<History, Date>, JpaSpecificationExecutor<History> {

}
