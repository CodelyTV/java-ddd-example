package tv.codely.backoffice.courses.application.rename;

import org.springframework.context.event.EventListener;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;
import tv.codely.shared.domain.course.CourseRenamedDomainEvent;

@Service
@DomainEventSubscriber({CourseRenamedDomainEvent.class})
public final class RenameBackofficeCourseOnCourseRenamed {
	private final BackofficeCourseRenamer renamer;

	public RenameBackofficeCourseOnCourseRenamed(BackofficeCourseRenamer renamer) {
		this.renamer = renamer;
	}

	@EventListener
	public void on(CourseRenamedDomainEvent event) {
		renamer.rename(event.aggregateId(), event.name());
	}
}
