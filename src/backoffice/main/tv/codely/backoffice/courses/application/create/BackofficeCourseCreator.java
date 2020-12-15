package tv.codely.backoffice.courses.application.create;

import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.backoffice.notifications.application.send_new_course_newsletter.SendNewBackofficeCourseNewsletterCommand;
import tv.codely.backoffice.notifications.application.send_new_course_newsletter.SendNewBackofficeCourseNewsletterCommandHandler;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.course.CourseCreatedDomainEvent;

import java.util.ArrayList;

@Service
public final class BackofficeCourseCreator {
    private final BackofficeCourseRepository                      repository;
    private final SendNewBackofficeCourseNewsletterCommandHandler newsletterCommandHandler;

    public BackofficeCourseCreator(BackofficeCourseRepository repository, SendNewBackofficeCourseNewsletterCommandHandler newsletterCommandHandler) {
        this.repository               = repository;
        this.newsletterCommandHandler = newsletterCommandHandler;
    }

    public void create(String id, String name, String duration) {
        BackofficeCourse course = new BackofficeCourse(id, name, duration);

        this.repository.save(course);

        this.newsletterCommandHandler.handle(new SendNewBackofficeCourseNewsletterCommand(name));
    }
}
