package pt.credibom.checklist.domain;

import pt.credibom.checklist.domain.common.HasCode;

public enum ProductSubTypeEnum implements HasCode {
    USED("U"),

    NEW("N"),

    HOME("L"),

    SEMI_NEW("S"); // TODO semi_new will not be used in the application

    private final String code;

    private ProductSubTypeEnum(final String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

}
