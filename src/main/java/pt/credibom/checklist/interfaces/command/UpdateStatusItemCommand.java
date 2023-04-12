package pt.credibom.checklist.interfaces.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusItemCommand {

	private static final long serialVersionUID = -5774120606498583843L;

	@NotNull
	private ProposalKeyCommand key;

	@NotNull
	@JsonProperty(value = "documentTypeId")
	private Integer documentTypeId;

	@NotNull
	@JsonProperty(value = "entityType")
	private  String entityType;

	@NotNull
	@JsonProperty(value = "pendingStatusId")
	private  Integer pendingStatusId;

	@NotNull
	@JsonProperty(value = "requestedBy")
	private  String requestedBy;
}
