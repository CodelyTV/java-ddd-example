package tv.codely.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;

public final class NotEmptyValidator implements FieldValidator {
    @Override
    public Boolean isValid(String fieldName, HashMap<String, Serializable> fields) {
        return !fields.get(fieldName).toString().isEmpty();
    }

    @Override
    public String errorMessage(String fieldName) {
        return String.format("The field <%s> should not be empty", fieldName);
    }
}
