package com.gchsj.jparelationship.manytomany.repository;

import com.gchsj.jparelationship.manytomany.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
}
