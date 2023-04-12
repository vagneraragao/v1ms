package pt.credibom.checklist.interfaces.command;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*FPEND001 - tabela de referência*/
public class PendingDocumentCommand implements Serializable {

	private static final long serialVersionUID = 3869597544877713249L;

    private Long id;

    @NotNull
    private NoteCommand config;

    private PossibleDocumentCommand possibleDocument;

    @NotNull
    private StatusCommand status;

    private ReasonCommand reason;

    private OwnerCommand entityType;

    private boolean manual;

    private String user;

    @Size(max = 40)
    @NotNull
    String documentId;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date modifiedDate;

}
