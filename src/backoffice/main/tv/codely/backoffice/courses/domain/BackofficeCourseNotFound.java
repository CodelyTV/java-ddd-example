package tv.codely.backoffice.courses.domain;

public class BackofficeCourseNotFound extends RuntimeException {
	public BackofficeCourseNotFound(String id) {
		super(String.format("The course <%s> doesn't exist", id));
	}
}
