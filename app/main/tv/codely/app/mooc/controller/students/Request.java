package tv.codely.app.mooc.controller.students;

public class Request {
    private String name;
    private String surname;

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
