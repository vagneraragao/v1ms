package pt.credibom.checklist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pt.credibom.checklist.domain.pendingdocumentation.VendorNotification;
import pt.credibom.checklist.domain.pendingdocumentation.VendorNotificationPK;

@Repository
public interface VendorNotificationRepository extends JpaRepository<VendorNotification, VendorNotificationPK>, JpaSpecificationExecutor<VendorNotification> {

}
