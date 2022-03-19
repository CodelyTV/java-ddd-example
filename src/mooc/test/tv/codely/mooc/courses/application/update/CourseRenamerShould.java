package tv.codely.mooc.courses.application.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

final class CourseRenamerShould extends CoursesModuleUnitTestCase {
    private CourseRenamer renamer;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        renamer = new CourseRenamer(repository, eventBus);
    }

    @Test
    void rename_an_existing_course() {
        Course existingCourse = CourseMother.random();
        CourseId id = existingCourse.id();
        CourseName newName = CourseNameMother.random();
        Course renamedCourse = CourseMother.create(id, newName, existingCourse.duration());

        when(repository.search(id)).thenReturn(Optional.of(existingCourse));
        renamer.rename(id, newName);

        shouldHaveSearched(id);
        shouldHaveSaved(renamedCourse);
        shouldNotPublishDomainEvent();
    }

    @Test
    void should_fail_if_course_doesnt_exists() {
        Course nonexistentCourse = CourseMother.random();
        CourseName newName = CourseNameMother.random();

        assertThrows(CourseNotExist.class, () -> renamer.rename(nonexistentCourse.id(), newName));
    }
}
