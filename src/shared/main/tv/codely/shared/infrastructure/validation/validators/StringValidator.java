package tv.codely.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;

public final class StringValidator implements FieldValidator {
    @Override
    public Boolean isValid(String fieldName, HashMap<String, Serializable> fields) {
        return true;
    }

    @Override
    public String errorMessage(String fieldName) {
        return String.format("The field <%s> should be of type string", fieldName);
    }
}
