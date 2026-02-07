import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main_Hibernate {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        String hql = "From " + Course.class.getSimpleName() + " Where price > 120000";
        List<Course> courseList = session.createQuery(hql).getResultList();

        for (Course course: courseList) {
            System.out.println(course.getName() + " - " + course.getPrice());
        }


        //сложные запросы
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Course> query = builder.createQuery(Course.class);
//        Root<Course> root = query.from(Course.class);
//        query.select(root).where(builder.greaterThan(root.<Integer>get("price"),
//                100_000)).orderBy(builder.desc(root.get("price")));
//        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
//
//           for (Course course : courseList) {
//            System.out.println(course.getName() + " - " + course.getPrice());
//        }

//        courseList.stream().map(Course::getName).forEach(System.out::println);
//        courseList.stream().map(course -> course.getTeacher().getName()).forEach(System.out::println);

//        Transaction transaction = session.beginTransaction();

//        Course course = session.get(Course.class, 1); //получаем данные из базы
//        System.out.println(course.getName());
//        System.out.println(course.getStudentsCount());
//        System.out.println(course.getTeacher().getName());
//        System.out.println(course.getStudents().size());
//        List<Student> studentList = course.getStudents();
//        for (Student student : studentList) {
//            System.out.println(student.getName());
//        }
//        studentList.stream().map(Student::getName).forEach(System.out::println);


//        Course course = new Course(); //создаем строку
//        course.setName("Новый курс");
//        course.setType(CourseType.BUSINESS);
//        course.setTeacherId(1);

//        Course course = session.get(Course.class, 50); //обновляем строку
//        course.setName("Совсем новый курс");

//        Course course = session.get(Course.class, 48); //удаляем строку
//        session.delete(course);

        //     session.save(course);

//        transaction.commit();
        sessionFactory.close();

    }
}
