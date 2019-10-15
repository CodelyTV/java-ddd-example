package tv.codely.mooc.courses_counter.application.find;

import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.Service;

@Service
public final class CoursesCounterFinder {
    private CoursesCounterRepository repository;

    public CoursesCounterFinder(CoursesCounterRepository repository) {
        this.repository = repository;
    }

    public CoursesCounterResponse find() {
        CoursesCounter coursesCounter = repository.search().orElseGet(() -> {
            throw new CoursesCounterNotInitialized();
        });

        return new CoursesCounterResponse(coursesCounter.total().value());
    }
}
