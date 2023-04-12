package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.credibom.checklist.domain.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {


}
