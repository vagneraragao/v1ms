package pt.credibom.checklist.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanNumericConverter implements AttributeConverter<Boolean, Integer> {

	@Override
	public Integer convertToDatabaseColumn( Boolean aBoolean ) {
		return aBoolean ? 1 : 0;
	}

	@Override
	public Boolean convertToEntityAttribute( Integer integer ) {
		return integer == 1;
	}
}
