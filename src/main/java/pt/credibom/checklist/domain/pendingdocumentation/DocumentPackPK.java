package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentPackPK implements Serializable {

	private static final long serialVersionUID = -2025081912800976838L;

    @Column(name = "PACK_ID")
    private Integer pack;

    @Column(name = "DOCUMENT_ID")
	@JsonProperty("documentoPossivel")
    private Integer possibleDocument;

}
