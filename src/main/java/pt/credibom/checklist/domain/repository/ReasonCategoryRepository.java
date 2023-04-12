package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pt.credibom.checklist.domain.pendingdocumentation.Reason_Category;
import pt.credibom.checklist.domain.pendingdocumentation.Reason_CategoryPK;

import java.util.List;

@Repository
public interface ReasonCategoryRepository extends JpaRepository<Reason_Category, Reason_CategoryPK>, JpaSpecificationExecutor<Reason_Category> {
	List<Reason_Category> findById_Category(String categoryId);
}
