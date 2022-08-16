package tv.codely.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;

public final class RequiredValidator implements FieldValidator {
    @Override
    public Boolean isValid(String fieldName, HashMap<String, Serializable> fields) {
        return fields.containsKey(fieldName);
    }

    @Override
    public String errorMessage(String fieldName) {
        return String.format("The field <%s> is required", fieldName);
    }
}
