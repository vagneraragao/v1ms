package pt.credibom.checklist.domain.common;

import java.util.Objects;

public class LabelValue {

	String label;

	String value;

	public LabelValue() {
	}

	public LabelValue( String label, String value ) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel( String label ) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{\"label\": \"" + this.label + "\", \"value\": \"" + this.value + "\"}";
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final LabelValue rhs = (LabelValue) obj;

		return this.getLabel().equals( rhs.getLabel() ) && this.getValue().equals( rhs.getValue() ) ? true : false;
    }

	@Override
	public int hashCode() {
		return Objects.hash( label, value );
	}
}
