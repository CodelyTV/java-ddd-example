package tv.codely.backoffice.courses.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.backoffice.BackofficeContextInfrastructureTestCase;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseCriteriaMother;
import tv.codely.backoffice.courses.domain.BackofficeCourseMother;
import tv.codely.shared.domain.UuidMother;
import tv.codely.shared.domain.WordMother;
import tv.codely.shared.domain.criteria.Criteria;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ElasticsearchBackofficeCourseRepositoryShould extends BackofficeContextInfrastructureTestCase {
	@Autowired
	private ElasticsearchBackofficeCourseRepository repository;

	@BeforeEach
	protected void setUp() throws IOException {
		clearElasticsearch();
	}

	@Test
	void save_a_course() {
		repository.save(BackofficeCourseMother.random());
	}

	@Test
	void search_an_existing_course() throws Exception {
		BackofficeCourse course = BackofficeCourseMother.random();

		repository.save(course);

		eventually(() -> assertEquals(Optional.of(course), repository.search(course.id())));
	}

	@Test
	void update_an_existing_course() throws Exception {
		BackofficeCourse course = BackofficeCourseMother.random();

		repository.save(course);
		course.rename(WordMother.random());
		repository.save(course);

		eventually(() -> assertEquals(Optional.of(course), repository.search(course.id())));
	}

	@Test
	void not_find_a_non_existing_course() {
		assertEquals(Optional.empty(), repository.search(UuidMother.random()));
	}

	@Test
	void search_all_existing_courses() throws Exception {
		BackofficeCourse course = BackofficeCourseMother.random();
		BackofficeCourse anotherCourse = BackofficeCourseMother.random();

		List<BackofficeCourse> expected = Arrays.asList(course, anotherCourse);

		repository.save(course);
		repository.save(anotherCourse);

		eventually(() -> {
			List<BackofficeCourse> actual = repository.searchAll();

			List<BackofficeCourse> sortedExpected = expected.stream()
				.sorted(Comparator.comparing(BackofficeCourse::id))
				.collect(Collectors.toList());
			List<BackofficeCourse> sortedActual = actual.stream()
				.sorted(Comparator.comparing(BackofficeCourse::id))
				.collect(Collectors.toList());

			assertEquals(sortedExpected, sortedActual);
		});
	}

	@Test
	void search_courses_using_a_criteria() throws Exception {
		BackofficeCourse matchingCourse = BackofficeCourseMother.create("DDD en Java", "3 days");
		BackofficeCourse anotherMatchingCourse = BackofficeCourseMother.create("DDD en TypeScript", "2.5 days");
		BackofficeCourse intellijCourse = BackofficeCourseMother.create("Exprimiendo Intellij", "48 hours");
		BackofficeCourse cobolCourse = BackofficeCourseMother.create("DDD en Cobol", "5 years");

		Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");
		List<BackofficeCourse> expected = Arrays.asList(matchingCourse, anotherMatchingCourse);

		repository.save(matchingCourse);
		repository.save(anotherMatchingCourse);
		repository.save(intellijCourse);
		repository.save(cobolCourse);

		eventually(() -> {
			List<BackofficeCourse> actual = repository.matching(criteria);

			List<BackofficeCourse> sortedExpected = expected.stream()
				.sorted(Comparator.comparing(BackofficeCourse::id))
				.collect(Collectors.toList());
			List<BackofficeCourse> sortedActual = actual.stream()
				.sorted(Comparator.comparing(BackofficeCourse::id))
				.collect(Collectors.toList());

			assertEquals(sortedExpected, sortedActual);
		});
	}
}
