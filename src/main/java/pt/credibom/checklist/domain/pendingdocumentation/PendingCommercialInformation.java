package pt.credibom.checklist.domain.pendingdocumentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingCommercialInformation implements Serializable {

    String regionalDirector;
    String regionalDirectorEmail;
    String coordinator;
    String coordinatorEmail;
    String supervisor;
    String SupervisorCode;
    String supervisorEmail;
    String vendorPoint;
    Long proposal;
    String brand;
    String licensePlate;
    String proposalStatus;
    List<PendingDocument> missingDocumentation;
    Date financingDate;
}
