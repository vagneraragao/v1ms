package pt.credibom.checklist.domain.common;

/**
 * Produto
 *
 * @author Nuno Figueiredo Barata
 * @version %I%
 *
 */
public enum FinancingTypeEnum implements HasCode, HasDraftReportDescription {

    CLASSIC("FEC", null, "C", "CRD", "Crédito", "CRD", "CRE"),

    LEASING("LSG", "01", "L", "LSG", "Leasing", "LSG", "LSG"),

    ALD("ALD", "02", "A", "ALD", "ALD", "ALD", "ALD"),

    REVOLVING("REV", null, "R", null, "Revolving", null, null), // TODO: is it used here? what code should be mapped?
    ;
	
    private final String code;
    private final String productTypeCode;
    private final String pendingDocumentationCode;
    private final String entityToTableCode;
    // TODO: used only for proposal draft Jasper report... find better way to pass this values to the jasper template
    private final String draftDescription;
    private final String commissionNotesCode;
    private final String financingProduct;

    private FinancingTypeEnum(final String code, final String productTypeCode, final String pendingDocumentationCode,
            final String entityToTableCode, final String draftDescription, final String commissionNotesCode, final String financingProduct) {
        this.code = code;
        this.productTypeCode = productTypeCode;
        this.pendingDocumentationCode = pendingDocumentationCode;
        this.entityToTableCode = entityToTableCode;
        this.draftDescription = draftDescription;
        this.commissionNotesCode = commissionNotesCode;
        this.financingProduct = financingProduct; 
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public String getFinancingProduct() {
        return this.financingProduct;
    }

    public String getProductTypeCode() {
        return this.productTypeCode;
    }

    public String getPendingDocumentationCode() {
        return this.pendingDocumentationCode;
    }

    public String getEntityToTableCode() {
        return this.entityToTableCode;
    }

    @Override
    public String getDraftDescription() {
        return this.draftDescription;
    }

    public String getCommissionNotesCode() {
        return this.commissionNotesCode;
    }

    public static FinancingTypeEnum getByCode(final String code) {
        if (code == null) {
            return null;
        }

        for (FinancingTypeEnum val : values()) {
            if (code.equals(val.getCode())) {
                return val;
            }
        }

        return null;
    }

    public static FinancingTypeEnum getByProductTypeCode(final String productTypeCode) {
        if (productTypeCode == null) {
            return null;
        }

        for (FinancingTypeEnum val : values()) {
            if (productTypeCode.equals(val.getProductTypeCode())) {
                return val;
            }
        }

        return null;
    }

    public static FinancingTypeEnum getByEntityToTableCode(final String entityToTableCode) {
        if (entityToTableCode == null) {
            return null;
        }

        for (FinancingTypeEnum val : values()) {
            if (entityToTableCode.equals(val.getEntityToTableCode())) {
                return val;
            }
        }

        return null;
    }

    /**
     * Não funciona para contractos Kronus e propostas temporárias (precisa acesso a bd)
     */
    public static FinancingTypeEnum getByContractNumber(final Long id) {
        String str = id + "";
        if (str.length() == 16)
            return REVOLVING;
        if (str.length() == 9 || str.length() == 14) {
            if (str.startsWith("5"))
                return ALD;
            if (str.startsWith("6"))
                return LEASING;
        } else if (str.length() == 11) {
            if (str.startsWith("8000"))
                return CLASSIC;// 8000(contratos),1000(propostas)
        } else {
            // TODO kronus(credilar)
            // ,temporários (user(165)/internet) <- só propostas
        }
        return null;
    }
    
    public boolean isLeasingAld() {
        return this == FinancingTypeEnum.LEASING || this == FinancingTypeEnum.ALD;
    }
}
