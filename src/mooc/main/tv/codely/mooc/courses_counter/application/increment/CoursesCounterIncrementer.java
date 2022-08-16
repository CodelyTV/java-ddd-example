package tv.codely.mooc.courses_counter.application.increment;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public final class CoursesCounterIncrementer {
    @Autowired
    private CoursesCounterRepository repository;
    @Autowired
    private UuidGenerator            uuidGenerator;

    public CoursesCounterIncrementer(CoursesCounterRepository repository, UuidGenerator uuidGenerator) {
        this.repository    = repository;
        this.uuidGenerator = uuidGenerator;
    }

    public void increment(CourseId id) {
        CoursesCounter counter = repository.search()
                                           .orElseGet(() -> CoursesCounter.initialize(uuidGenerator.generate()));

        if (!counter.hasIncremented(id)) {
            counter.increment(id);

            repository.save(counter);
        }
    }
}
