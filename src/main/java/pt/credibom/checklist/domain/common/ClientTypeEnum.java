package pt.credibom.checklist.domain.common;

/**
 *
 * @author João Fialho
 * @author Diogo Fialho
 * @version %I%
 *
 */
public enum ClientTypeEnum implements HasCode, HasDraftReportDescription {
	
    ENTERPRISE("N", false, "Empresa"),

    PARTICULAR("P", false, "Particular"),

    //FCLIPROP e WS passam valor para P
    //@see BusinessEntityCodes.TypeOfGoodsCodes.ENI_ONLY_STATIC_CODES
    ENI("E", false, "ENI"), // TODO: fill in the code

    PARTICULAR_ENI("Z", false, "Particular/ENI"), //???
    
    ALL("T", true, "Todos");//Usado na configuração das tabelas de financiamento

    private final String code;

    private boolean aggregatedClientType;

    // TODO: used only for proposal draft Jasper report... find better way to pass this values to the jasper template
    private String draftDescription;

    private ClientTypeEnum(final String code, final boolean aggregatedClientType, final String draftDescription) {
        this.code = code;
        this.aggregatedClientType = aggregatedClientType;
        this.draftDescription = draftDescription;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * This method identifies if a client type is an aggregated type, that means that the client type includes more than one simple
     * client type. example( client type 'T' means ALL and includes all simple client types.
     *
     *
     * @return true if the client type is aggregated, false otherwise.
     */
    public boolean isAggregatedClientType() {
        return this.aggregatedClientType;
    }

    @Override
    public String getDraftDescription() {
        return this.draftDescription;
    }

    static public ClientTypeEnum parseByCode(final String code) {
        for (ClientTypeEnum value : ClientTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
