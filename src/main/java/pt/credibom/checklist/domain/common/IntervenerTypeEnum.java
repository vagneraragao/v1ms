package pt.credibom.checklist.domain.common;

import lombok.RequiredArgsConstructor;
import pt.credibom.checklist.domain.IntervenerConfigurationRegistry;
import pt.credibom.checklist.domain.IntervenerRoleEnum;

@RequiredArgsConstructor
public enum IntervenerTypeEnum {

    PRIMEIROTITULAR("1º Titular", "1T", IntervenerRoleEnum.HOLDER, 1, "1ºTIT", IntervenerConfigurationRegistry.FIRST_HOLDER_WITH_ORDER),
    SEGUNDOTITULAR("2º Titular","2T", IntervenerRoleEnum.HOLDER, 2, "2ºTIT", IntervenerConfigurationRegistry.SECOND_HOLDER),
    PRIMEIROAVALISTA("1º Avalista","1A", IntervenerRoleEnum.ENDORSER, 1 , "AVAL", IntervenerConfigurationRegistry.FIRST_ENDORSER_WITH_ORDER),
    SEGUNDOAVALISTA("2º Avalista","2A", IntervenerRoleEnum.ENDORSER, 2, null, IntervenerConfigurationRegistry.SECOND_ENDORSER),
    TERCEIROAVALISTA("3º Avalista","3A", IntervenerRoleEnum.ENDORSER, 3, null, IntervenerConfigurationRegistry.THIRD_ENDORSER),
    QUARTOAVALISTA("4º Avalista","4A", IntervenerRoleEnum.ENDORSER, 4, null, IntervenerConfigurationRegistry.FOURTH_ENDORSER),
    //Há sítios com TC - terceiro
    ;

    private final String description;
    private final String code;
    private final IntervenerRoleEnum role;
    private final Integer order;
    private final String valorCondicaoFCCLA003;
    private final String intervenerConfigurationRegistry;

    public String getDescription() {
        return this.description;
    }

    public String getCode() {
        return this.code;
    }

    public IntervenerRoleEnum getRole() {
        return this.role;
    }

    public Integer getOrder() {
        return this.order;
    }

    public String getValorCondicaoFCCLA003() {
        return this.valorCondicaoFCCLA003;
    }

    public String getIntervenerConfigurationRegistry(){
        return this.intervenerConfigurationRegistry;
    }

    static public IntervenerTypeEnum parseByCode(final String code) {
        for (IntervenerTypeEnum value : IntervenerTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    static public IntervenerTypeEnum parseByIntervenerConfigurationRegistry(final String code) {
        for (IntervenerTypeEnum value : IntervenerTypeEnum.values()) {
            if (value.getIntervenerConfigurationRegistry().equalsIgnoreCase(code)) {
                return value;
            }
        }
        return null;
    }

    static public IntervenerTypeEnum parseByValorCondicaoFCCLA003(final String valorCondicaoFCCLA003) {
        for (IntervenerTypeEnum value : IntervenerTypeEnum.values()) {
            if (value.getValorCondicaoFCCLA003() != null
                    && value.getValorCondicaoFCCLA003().equalsIgnoreCase(valorCondicaoFCCLA003)) {
                return value;
            }
        }
        return null;
    }

    static public IntervenerTypeEnum parseByRoleAndOrder(IntervenerRoleEnum role, Integer order){
        for (IntervenerTypeEnum value : IntervenerTypeEnum.values()) {
            if (value.getRole() == role && value.getOrder() == order) {
                return value;
            }
        }
        return null;
    }
}
