package pt.credibom.checklist.domain.converter;

import jakarta.persistence.Converter;
import pt.credibom.checklist.domain.common.FinancingTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FinancingTypeConverter implements AttributeConverter<FinancingTypeEnum,String> {
    @Override
    public String convertToDatabaseColumn(FinancingTypeEnum financingTypeEnum) {
        if (financingTypeEnum == null) {
            return null;
        }
        return financingTypeEnum.getPendingDocumentationCode();
    }

    @Override
    public FinancingTypeEnum convertToEntityAttribute(String pendingDocumentationCode) {
        if (pendingDocumentationCode == null) {
            return null;
        }

        return Stream.of(FinancingTypeEnum.values())
                .filter(code -> code.getPendingDocumentationCode().equals(pendingDocumentationCode))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
