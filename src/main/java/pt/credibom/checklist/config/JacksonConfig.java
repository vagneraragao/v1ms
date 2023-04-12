package pt.credibom.checklist.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;

/**
 * The Class JacksonConfig.
 */
@Configuration
public class JacksonConfig {

	/**
	 * Object mapper.
	 *
	 * @return the object mapper
	 */
	@Bean
	@Primary
	ObjectMapper objectMapper() {
		final ObjectMapper mapper = new ObjectMapper();

		mapper.configure( SerializationFeature.INDENT_OUTPUT, false );
		mapper.disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );

		mapper.configure( DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false );
		mapper.configure( SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false );
		mapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
		mapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );

		// enable deserialization of empty arrays
		mapper.enable( DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT );

		mapper.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

		mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

		// Converts properties to snake_case
		mapper.setPropertyNamingStrategy( new PropertyNamingStrategies.SnakeCaseStrategy() );

		mapper.registerModules(
				new JavaTimeModule(),
				new ParanamerModule(),
				new ParameterNamesModule( JsonCreator.Mode.PROPERTIES )
		);

		return mapper;
	}
	
	@Bean( name = "camelCaseMapper" )
	ObjectMapper camelCaseObjectMapper() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure (JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature(), true);

		return mapper;
	}

}
