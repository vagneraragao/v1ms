package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorNotificationPK implements Serializable {

	private static final long serialVersionUID = -186234671668278794L;

	//@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "VNDVEND")
    //private PviEntity vendedor;

	@Size(max = 11)
    @Column(name = "VENDOR_ID")
    //@Type(type = TRIMMED_TO_NULL_STRING_TYPE)
	@JsonProperty("vendedor")
    private String vendor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_ID")
	@JsonProperty("documentoPossivel")
	private Document possibleDocument;

}
