package pt.credibom.checklist.domain.pendingdocumentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import pt.credibom.checklist.domain.converter.BooleanNumericConverter;

/**
 * @author João Rossa
 *
 */
@Immutable
@Entity
@Table(schema = "checklist", name = "VENDOR_NOTIFICATION")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/*TPEND010 - vendor and notification table*/
public class VendorNotification {

	private static final long serialVersionUID = 466963658056345003L;

	@Id
    private VendorNotificationPK id;

    @Column(name = "NOTIFICATION_VALUE")
	@JsonProperty("valor")
	private Integer value;

    @Column(name = "ACTIVE_FLAG")
	@JsonProperty("activo")
	@Convert(converter = BooleanNumericConverter.class )
	private boolean active;
}
