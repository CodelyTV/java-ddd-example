package tv.codely.backoffice.courses.domain;

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

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String duration() {
        return duration;
    }
}
