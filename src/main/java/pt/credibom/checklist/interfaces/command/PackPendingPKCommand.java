package pt.credibom.checklist.interfaces.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackPendingPKCommand implements Serializable {

	private static final long serialVersionUID = -2025081912800976838L;

    private PackCommand pack;

    private PossibleDocumentCommand possibleDocument;

}
