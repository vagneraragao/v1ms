package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendDocVendorNotificationPKCommand implements Serializable {

	private static final long serialVersionUID = -186234671668278794L;

	@Size(max = 11)
   	@NotNull
    private String vendor;

   	@NotNull
	private PossibleDocumentCommand possibleDocument;

}
