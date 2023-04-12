package pt.credibom.checklist.domain.common;

import java.io.Serializable;

public interface ValueObject<T extends Serializable> {

    public T getCode();

    public String getDescription();
}
