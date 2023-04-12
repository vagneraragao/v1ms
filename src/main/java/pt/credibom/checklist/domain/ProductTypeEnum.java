package pt.credibom.checklist.domain;

import pt.credibom.checklist.domain.common.HasCode;
import pt.credibom.checklist.domain.common.HasDraftReportDescription;

public enum ProductTypeEnum implements HasCode, HasDraftReportDescription {
    
	ALL("ALL", null, null),
	
	AUTO("A", "AUTO", "Auto"),

    LAR("L", "LAR", "Consumo"),

    MOTAS("M", null, "Motas");

    private final String code;
    
    private final String webServiceCode;
    
    // TODO: used only for proposal draft Jasper report... find better way to pass this values to the jasper template
    private String draftDescription;

    private ProductTypeEnum(final String code, final String webServiceCode, final String draftDescription) {
        this.code = code;
        this.webServiceCode = webServiceCode;
        this.draftDescription = draftDescription;
    }

    @Override
    public String getCode() {
        return this.code;
    }
    
    @Override
    public String getDraftDescription() {
        return this.draftDescription;
    }
    
    public String getWebServiceCode(){
    	return this.webServiceCode;
    }

    static public ProductTypeEnum parseByCode(final String code) {
        for (ProductTypeEnum value : ProductTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
