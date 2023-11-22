package tv.codely.mooc.students.infrastructure.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

import java.util.List;

@Service
@Transactional("mooc-transaction_manager")
public class MySqlStudentRepository extends HibernateRepository<Student> implements StudentRepository {
    public MySqlStudentRepository(@Qualifier("mooc-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Student.class);
    }

    @Override
    public void register(Student student) {
        persist(student);
    }

    @Override
    public List<Student> searchAll() {
        return all();
    }
}
