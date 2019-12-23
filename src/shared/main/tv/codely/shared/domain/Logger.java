package tv.codely.shared.domain;

import java.io.Serializable;
import java.util.HashMap;

public interface Logger {
    void info(String $message);
    void info(String $message, HashMap<String, Serializable> $context);

    void warning(String $message);
    void warning(String $message, HashMap<String, Serializable> $context);

    void critical(String $message);
    void critical(String $message, HashMap<String, Serializable> $context);
}
