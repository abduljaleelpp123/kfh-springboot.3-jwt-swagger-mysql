package com.abdul.kfh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.abdul.kfh.entity.CourseStudent;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long>, JpaSpecificationExecutor<CourseStudent> {
	 

}
