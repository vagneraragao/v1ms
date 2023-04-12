package pt.credibom.checklist.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import pt.credibom.checklist.domain.common.HasCode;

public enum StatusEnum implements HasCode {
    COMPLIANT("1" , "Conforme"),
    NON_COMPLIANT("2" , "Não conforme"),
    NOT_RECEIVED("3" , "Não recepcionado" ),
    NOT_APPLICABLE("4", "Não aplicável"),
    UNDER_ANALYSIS_CRDF("5" , "Doc. em análise CRDF")
    ;


    private final String code;

    private final String description;

    StatusEnum(final String code, final String description){
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @JsonValue
    public int toValue() {
        return ordinal() + 1;
    }
}
