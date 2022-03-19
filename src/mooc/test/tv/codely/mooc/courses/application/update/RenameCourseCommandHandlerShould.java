package tv.codely.mooc.courses.application.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.courses.domain.CourseNameMother;

import java.util.Optional;

import static org.mockito.Mockito.when;

final class RenameCourseCommandHandlerShould extends CoursesModuleUnitTestCase {
    private RenameCourseCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new RenameCourseCommandHandler(new CourseRenamer(repository, eventBus));
    }

    @Test
    void rename_an_existing_course() {
        Course existingCourse = CourseMother.random();
        CourseId id = existingCourse.id();
        RenameCourseCommand command = RenameCourseCommandMother.fromCourse(existingCourse);
        Course renamedCourse = CourseMother.create(
            id,
            CourseNameMother.create(command.newName()),
            existingCourse.duration());

        when(repository.search(id)).thenReturn(Optional.of(existingCourse));
        handler.handle(command);

        shouldHaveSearched(id);
        shouldHaveSaved(renamedCourse);
        shouldNotPublishDomainEvent();
    }
}
