package tv.codely.shared.domain.criteria;

public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS");

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        switch (value) {
            case "=": return FilterOperator.EQUAL;
            case "!=": return FilterOperator.NOT_EQUAL;
            case ">": return FilterOperator.GT;
            case "<": return FilterOperator.LT;
            case "CONTAINS": return FilterOperator.CONTAINS;
            case "NOT_CONTAINS": return FilterOperator.NOT_CONTAINS;
            default: return null;
        }
    }

    public boolean isPositive() {
        return this != NOT_EQUAL && this != NOT_CONTAINS;
    }

    public String value() {
        return operator;
    }
}
