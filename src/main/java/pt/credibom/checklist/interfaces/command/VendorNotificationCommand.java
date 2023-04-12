package pt.credibom.checklist.interfaces.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/*TPEND010 - vendor and notification table*/
public class VendorNotificationCommand {

	private static final long serialVersionUID = 466963658056345003L;

    private PendDocVendorNotificationPKCommand id;

	@NotNull
	private Integer value;

	private boolean active;
}
