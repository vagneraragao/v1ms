package pt.credibom.checklist.domain.common;

import pt.credibom.checklist.domain.ProductTypeEnum;

/**
 * 
 * CBOPRLIB.FMCONF\COTPRO tipo bem/negócio -> ProductTypeEnum
 * CBPVLIB.FMVENDF\VNDTNEG area negócio/origem -> Circuito Longo Auto/Lar, Circuito Curto
 * 
 * ?cbtablib.ftb0029f\tbtpro -> ProductTypeEnum
 * cbtablib.FTB0003F\TBTPRO -> ProductTypeEnum
 * 
 * cbtablib.ftb0171f -> código descritivo...
 * 
 * cbdados.fb2b004 -> ProductTypeEnum
 * 
 * @author Diogo Fialho
 * @version %I%
 *
 */

//TODO colocar classe igual ao credibomweb
public enum BusinessTypeEnum implements HasCode, HasDraftReportDescription {

	//Business Area: Circuito Longo, Auto
	//Business Type: Auto
    //fmconf/fmvendf(vndtneg)
    LONG_CHANNEL_AUTO("A", "AUTO", "Auto", ProductTypeEnum.AUTO),

    //Business Area: Circuito Longo, Lar
    //Business Type: Lar
    //fmconf/fmvendf(vndtneg)
    LONG_CHANNEL_LAR("L", "LAR", "Consumo", ProductTypeEnum.LAR),

    //Business Area: Circuito Curto
    //Business Type: ir buscar a cbtablib.FTB0003F\TBTPRO (TBCODI-codProduto) 
    //fmvendf(vndtneg)
    SHORT_CHANNEL("D", null, "Directo", null),

    //Business Type: Motas
    //BusinessType fmconf
    //MOTAS("M", null, "Motas", false, null);
    ;
	
    private final String code;

    //WebService code
    private final String simulationCode;

    // TODO: used only for proposal draft Jasper report... find better way to pass this values to the jasper template
    private String draftDescription;
    
    ProductTypeEnum productType;

    private BusinessTypeEnum(final String code, final String simulationCode, final String draftDescription, ProductTypeEnum productType) {
        this.code = code;
        this.simulationCode = simulationCode;
        this.draftDescription = draftDescription;
        this.productType = productType;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public String getSimulationCode() {
        return this.simulationCode;
    }
    
    public ProductTypeEnum getProductType() {
        return this.productType;
    }

    @Override
    public String getDraftDescription() {
        return this.draftDescription;
    }

    static public BusinessTypeEnum parseByCode(final String code) {
        for (BusinessTypeEnum value : BusinessTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    static public BusinessTypeEnum parseByDescription(final String description) {
        for (BusinessTypeEnum value : BusinessTypeEnum.values()) {
            if (value.getSimulationCode().equals(description)) {
                return value;
            }
        }
        return null;
    }

    static public BusinessTypeEnum parseProductType(final ProductTypeEnum code) {
        for (BusinessTypeEnum value : BusinessTypeEnum.values()) {
            if (value.getProductType().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
