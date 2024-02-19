package tv.codely.mooc.students.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.*;

import static org.mockito.Mockito.*;
public class StudentCreatorTest {

    @Test
    public void create_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);
        StudentCreator creator = new StudentCreator(repository);
        StudentCreatorRequest creatorRequest = new StudentCreatorRequest(
                "decf33ca-81a7-419f-a07a-74f214e928e5",
                "name",
                "example@example.com");

        Student student = new Student(
                new StudentId(creatorRequest.id()),
                new StudentName(creatorRequest.name()),
                new StudentEmail(creatorRequest.email())
        );

        creator.create(creatorRequest);

        verify(repository, atLeastOnce()).save(student);
    }

}
