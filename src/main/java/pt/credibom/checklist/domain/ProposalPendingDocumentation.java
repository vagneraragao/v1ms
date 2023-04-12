package pt.credibom.checklist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProposalPendingDocumentation implements Serializable {

    private static final long serialVersionUID = 3340041129534362319L;

    private Long proposalNumber;

    private List<String> observations;

    private List<String> pendingDocsAuto;

    private List<String> pendingDocs;


}
