package tv.codely.shared.domain;

import java.util.HashMap;

public interface Monitoring {
    void incrementCounter(int times);

    void incrementGauge(int times);
    void decrementGauge(int times);
    void setGauge(int value);

    void observeHistogram(int value, HashMap<String, String> labels);
}
