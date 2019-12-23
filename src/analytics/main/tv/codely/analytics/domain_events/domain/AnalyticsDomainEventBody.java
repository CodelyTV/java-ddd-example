package tv.codely.analytics.domain_events.domain;

import java.io.Serializable;
import java.util.HashMap;

public final class AnalyticsDomainEventBody {
    private HashMap<String, Serializable> value;

    public HashMap<String, Serializable> getValue() {
        return value;
    }

    public AnalyticsDomainEventBody(HashMap<String, Serializable> value) {
        this.value = value;
    }
}
