package tv.codely.backoffice.courses.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BackofficeCourse that = (BackofficeCourse) o;
        return id.equals(that.id) &&
               name.equals(that.name) &&
               duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
