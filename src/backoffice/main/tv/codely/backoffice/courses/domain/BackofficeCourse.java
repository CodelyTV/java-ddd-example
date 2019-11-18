package tv.codely.backoffice.courses.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class BackofficeCourse {
    private final String id;
    private final String name;
    private final String duration;

    public BackofficeCourse() {
        id       = null;
        name     = null;
        duration = null;
    }

    public BackofficeCourse(String id, String name, String duration) {
        this.id       = id;
        this.name     = name;
        this.duration = duration;
    }

    public static BackofficeCourse fromPrimitives(Map<String, Object> plainData) {
        return new BackofficeCourse(
            (String) plainData.get("id"),
            (String) plainData.get("name"),
            (String) plainData.get("duration")
        );
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String duration() {
        return duration;
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("id", id);
            put("name", name);
            put("duration", duration);
        }};
    }
}
