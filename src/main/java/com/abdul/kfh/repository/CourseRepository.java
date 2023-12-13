package com.abdul.kfh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.abdul.kfh.entity.Course;

@Repository
public interface CourseRepository  extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
  Optional<Course> findByCourseName(String coursename);

  Boolean existsByCourseName(String courseName);
  Boolean existsByCourseCode(String courseCode);

  
}
