package tv.codely.apps.mooc.controller.students;

public class Request {
    private String name;
    private String email;

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public void setEmail(String surname) {
        this.email = surname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
