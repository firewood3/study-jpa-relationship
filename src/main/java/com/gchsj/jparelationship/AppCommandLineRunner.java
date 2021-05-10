package com.gchsj.jparelationship;

import com.gchsj.jparelationship.manytomany.entity.Course;
import com.gchsj.jparelationship.manytomany.entity.CourseRegistration;
import com.gchsj.jparelationship.manytomany.entity.Student;
import com.gchsj.jparelationship.manytomany.repository.CourseRegistrationRepository;
import com.gchsj.jparelationship.manytomany.repository.CourseRepository;
import com.gchsj.jparelationship.manytomany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public void run(String... args) throws Exception {
        initData();
        addRelational();
        showRelational();
    }

    @Transactional
    public void initData() {
        studentRepository.save(Student.builder().name("HONG").build());
        studentRepository.save(Student.builder().name("SEOK").build());
        studentRepository.save(Student.builder().name("JIN").build());

        courseRepository.save(Course.builder().name("CPP").build());
        courseRepository.save(Course.builder().name("JAVA").build());
        courseRepository.save(Course.builder().name("JAVASCRIPT").build());
    }

    @Transactional
    public void addRelational() {
        Course cpp = courseRepository.findByName("CPP");
        Course java = courseRepository.findByName("JAVA");
        Course javascript = courseRepository.findByName("JAVASCRIPT");

        Student hong = studentRepository.findByName("HONG");
        courseRegistrationRepository.save(CourseRegistration.builder().course(cpp).student(hong).build());
        courseRegistrationRepository.save(CourseRegistration.builder().course(java).student(hong).build());

        Student jin = studentRepository.findByName("JIN");
        courseRegistrationRepository.save(CourseRegistration.builder().course(javascript).student(jin).build());
    }

    @Transactional
    public void showRelational() {
        Student hong = studentRepository.findByName("HONG");
        printCourseRegistration(hong.getRegistrations());

        Student seok = studentRepository.findByName("SEOK");
        printCourseRegistration(seok.getRegistrations());

        Student jin = studentRepository.findByName("JIN");
        printCourseRegistration(jin.getRegistrations());
    }

    private void printCourseRegistration(List<CourseRegistration> registrations) {
        registrations.forEach(courseRegistration -> System.out.println(courseRegistration.getStudent().getName() + " registers " + courseRegistration.getCourse().getName()));
    }
}
