package pt.credibom.checklist.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*
* @author Nuno Figueiredo Barata
*
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditTrailContext implements Serializable{

    private String library;

    private String table;

    private String key1;

    private String key2;

    private String key3;
}
