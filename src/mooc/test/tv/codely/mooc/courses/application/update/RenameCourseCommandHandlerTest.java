package tv.codely.mooc.courses.application.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseNotExist;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;


class RenameCourseCommandHandlerTest extends CoursesModuleUnitTestCase {

    private static final String NEW_NAME = "new name";
    private RenameCourseCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new RenameCourseCommandHandler(new CourseNameUpdater(repository, eventBus));
    }

    @Test
    @DisplayName("Should rename course correctly when exist the course")
    void should_rename_course_correctly_when_exist_the_course() {
        // Arrange
        final Course courseMock = CourseMother.random();
        final RenameCourseCommand renameCourseCommand = new RenameCourseCommand(courseMock.id().value(), NEW_NAME);
        final Course courseExpected = Course.rename(courseMock.id(), new CourseName(NEW_NAME), courseMock.duration());

        when(super.repository.search(courseMock.id())).thenReturn(Optional.of(courseMock));

        // Action

        handler.handle(renameCourseCommand);

        // Assert
        shouldHaveSaved(courseExpected);
    }

    @Test
    @DisplayName("Should return error when rename course but it not exist")
    void should_return_error_when_rename_course_but_it_not_exist() {
        // Arrange
        final Course courseMock = CourseMother.random();
        final RenameCourseCommand renameCourseCommand = new RenameCourseCommand(courseMock.id().value(), NEW_NAME);

        // Action
        assertThatThrownBy(() -> handler.handle(renameCourseCommand)).isInstanceOf(CourseNotExist.class);

        // Assert
        shouldNotHaveSaved();
    }

}
