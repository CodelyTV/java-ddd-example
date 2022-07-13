package tv.codely.shared.infrastructure;

import tv.codely.shared.domain.Logger;

import java.io.Serializable;
import java.util.HashMap;

public class VoidLogger implements Logger {
    @Override
    public void info(String $message) {

    }

    @Override
    public void info(String $message, HashMap<String, Serializable> $context) {

    }

    @Override
    public void warning(String $message) {

    }

    @Override
    public void warning(String $message, HashMap<String, Serializable> $context) {

    }

    @Override
    public void critical(String $message) {

    }

    @Override
    public void critical(String $message, HashMap<String, Serializable> $context) {

    }
}
