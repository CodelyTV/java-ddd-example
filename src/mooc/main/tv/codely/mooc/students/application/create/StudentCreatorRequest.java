package tv.codely.mooc.students.application.create;

public class StudentCreatorRequest {
    private final String id;
    private final String name;
    private final String email;

    public StudentCreatorRequest(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }
}
