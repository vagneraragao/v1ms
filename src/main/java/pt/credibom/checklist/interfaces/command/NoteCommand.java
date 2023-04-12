package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Configuração Documentação pendente
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*FPEND002 - tabela de referência*/
public class NoteCommand {

	ProposalKeyCommand id;

    @Size(max = 300)
    private String internalNotes;

    @Size(max = 300)
    private String externalNotes;

    private String user;

    private String authorizer;

}
