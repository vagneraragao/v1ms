package pt.credibom.checklist.config;

import java.time.Instant;
import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories( basePackages = { "pt.credibom.checklist.domain" } )
@EntityScan( basePackages = { "pt.credibom.checklist.domain" } )
@EnableJpaAuditing( auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditingDateTimeProvider" )
@Configuration
public class PersistenceConfig {

	/**
	 * Returns an {@link Instant} object to be store in auditing table.
	 *
	 * @return DateTimeProvider implementation
	 */
	@Bean( name = "auditingDateTimeProvider" )
	public DateTimeProvider dateTimeProvider() {
		return () -> Optional.of( Instant.now() );
	}

	/**
	 * Implementation of {@link AuditorAware} interface.
	 *
	 * @return the SecurityContext user or NA if any user is register
	 */
	@Bean( name = "auditorAware" )
	public AuditorAware<String> auditorAware() {
		return () -> {
			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if ( auth == null ) {
				return Optional.of( "NA" );
			}
			return Optional.ofNullable( auth.getName() );
		};
	}

}
