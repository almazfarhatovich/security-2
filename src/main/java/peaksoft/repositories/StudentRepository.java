package peaksoft.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import java.util.List;

@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(student);

        transaction.commit();
    }

    public boolean existsByEmail(String email) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Boolean exists = entityManager.createQuery("""
                        select 
                        case when count(s) > 0 
                         then true
                         else false end 
                        from Student s 
                        where s.email = ?1
                        """, Boolean.class).setParameter(1, email)
                .getSingleResult();

        transaction.commit();

        return exists;
    }

    public List<Student> findAll() {

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        List<Student> students = entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();

        transaction.commit();

        return students;
    }

    public boolean existsById(Long studentId) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Boolean exists = entityManager.createQuery("""
                        select 
                        case when count(s) > 0 
                         then true
                         else false end 
                        from Student s 
                        where s.id = ?1
                        """, Boolean.class).setParameter(1, studentId)
                .getSingleResult();

        transaction.commit();

        return exists;
    }

    public Student findById(Long studentId) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Student student = entityManager.find(Student.class, studentId);

        transaction.commit();

        return student;
    }

    public void deleteById(Long studentId) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(entityManager.find(Student.class, studentId));

        transaction.commit();
    }

    public void updateStudent(Long studentId, Student newStudent) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Student student = entityManager.find(Student.class, studentId);

        String currentName = student.getName();
        String newName = newStudent.getName();

        if (!currentName.equals(newName)) {
            student.setName(newName);
        }

        String currentEmail = student.getEmail();
        String newEmail = newStudent.getEmail();

        if (!currentEmail.equals(newEmail)) {
            student.setEmail(newEmail);
        }

        transaction.commit();
    }

    public List<Student> findAll(String search) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        List<Student> students = entityManager.createQuery("select s from Student s where s.name like ?1", Student.class)
                .setParameter(1, search + "%")
                .getResultList();

        transaction.commit();

        return students;
    }
}