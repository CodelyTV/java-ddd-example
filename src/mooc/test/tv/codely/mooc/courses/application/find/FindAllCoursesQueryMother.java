package tv.codely.mooc.courses.application.find;

public final class FindAllCoursesQueryMother {
    public static FindAllCoursesQuery create() {
        return new FindAllCoursesQuery();
    }

    public static FindAllCoursesQuery random() {
        return create();
    }
}
