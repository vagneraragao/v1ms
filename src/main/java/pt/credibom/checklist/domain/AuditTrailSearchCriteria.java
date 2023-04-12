package pt.credibom.checklist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.credibom.checklist.domain.common.AuditTrailContext;
import pt.credibom.checklist.domain.common.LabelValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Nuno Figueiredo Barata
*
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditTrailSearchCriteria implements Serializable{

    List<AuditTrailContext> context = new ArrayList<AuditTrailContext>();

    boolean filterShowOnScreen;

    String action;

    List<String> fieldsToShow;

    List<LabelValue> fieldValue;

    String image;

    String value;

    String user;

}
