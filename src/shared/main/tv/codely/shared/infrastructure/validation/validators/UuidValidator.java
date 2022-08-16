package tv.codely.shared.infrastructure.validation.validators;

import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Pattern;

public final class UuidValidator implements FieldValidator {
    @Override
    public Boolean isValid(String fieldName, HashMap<String, Serializable> fields) {
        Pattern uuidPattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

        return uuidPattern.matcher((String) fields.get(fieldName)).matches();
    }

    @Override
    public String errorMessage(String fieldName) {
        return String.format("The field <%s> is not a valid uuid", fieldName);
    }
}
