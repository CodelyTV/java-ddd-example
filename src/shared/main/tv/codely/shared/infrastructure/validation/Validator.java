package tv.codely.shared.infrastructure.validation;

import tv.codely.shared.infrastructure.validation.validators.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Validator {
    private static final HashMap<String, FieldValidator> validators = new HashMap<String, FieldValidator>() {{
        put("required", new RequiredValidator());
        put("string", new StringValidator());
        put("not_empty", new NotEmptyValidator());
        put("uuid", new UuidValidator());
    }};

    public static ValidationResponse validate(
        HashMap<String, Serializable> input,
        HashMap<String, String> combinedRules
    ) throws ValidatorNotExist {
        HashMap<String, List<String>> validationErrors = new HashMap<>();

        for (Map.Entry<String, String> entry : combinedRules.entrySet()) {
            String[] rules = entry.getValue().split("\\|");

            for (String rule : rules) {
                FieldValidator validator = validators.get(rule);

                if (null == validator) {
                    throw new ValidatorNotExist(rule);
                }

                if (!validator.isValid(entry.getKey(), input)) {
                    List<String> existingErrors = validationErrors.getOrDefault(entry.getKey(), new ArrayList<>());
                    existingErrors.add(validator.errorMessage(entry.getKey()));

                    validationErrors.put(entry.getKey(), existingErrors);
                }
            }
        }

        return new ValidationResponse(validationErrors);
    }
}
