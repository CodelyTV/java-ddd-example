package tv.codely.mooc.domain;

import java.util.Objects;

public final class Course {
    private String id;
    private String name;
    private String duration;

    public Course(String id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Course)) return false;
        Course course = (Course) object;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(duration, course.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
