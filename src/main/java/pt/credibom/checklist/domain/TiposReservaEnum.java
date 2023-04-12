/**
 *
 */
package pt.credibom.checklist.domain;

import pt.credibom.checklist.domain.common.HasCode;

/**
 * @author Nuno Figueiredo Barata
 *
 */
public enum TiposReservaEnum implements HasCode {

    LOCACAO("L"),

    HIPOTECA("H"),

    RESERVA("R"),

    SEMRESERVA("S"), ;

    private final String code;

    private TiposReservaEnum(final String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public static TiposReservaEnum parseByCode(String code){
        for (TiposReservaEnum value : TiposReservaEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
