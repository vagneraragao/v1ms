package pt.credibom.checklist.domain;

import lombok.RequiredArgsConstructor;
import pt.credibom.checklist.domain.common.HasCode;
import pt.credibom.checklist.domain.common.HasDraftReportDescription;

@RequiredArgsConstructor
public enum IntervenerRoleEnum implements HasCode, HasDraftReportDescription {
    HOLDER("T", "Titular", "Titular"),

    ENDORSER("A", "Avalista", "Avalista");

    private final String code;

    private final String description;

    // TODO: used only for proposal draft Jasper report... find better way to pass this values to the jasper template
    private final String draftDescription;

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDraftDescription() {
        return null;
    }
}
