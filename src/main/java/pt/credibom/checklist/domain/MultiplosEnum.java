package pt.credibom.checklist.domain;

import pt.credibom.checklist.domain.common.HasCode;

public enum MultiplosEnum implements HasCode {

    SIM("S"),

    NAO("N"),

    PARTICULARES("P"),

    EMPRESAS1T1A("A"),
    ;

    private final String code;

    private MultiplosEnum(final String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

}
