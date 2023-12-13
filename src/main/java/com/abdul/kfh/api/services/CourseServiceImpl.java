package com.abdul.kfh.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.abdul.kfh.api.exceptions.RecordNotAvailableException;
import com.abdul.kfh.api.exceptions.RequestParameterNotValidException;
import com.abdul.kfh.api.request.CourseFilter;
import com.abdul.kfh.api.request.UpdateStudentCourseRecord;
import com.abdul.kfh.entity.Course;
import com.abdul.kfh.entity.CourseStudent;
import com.abdul.kfh.entity.Student;
import com.abdul.kfh.repository.CourseRepository;
import com.abdul.kfh.repository.CourseStudentRepository;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseStudentRepository courseStudentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Override
	public List<Course> courseFilterBy(CourseFilter filter) {
		List<Course> courseList = null;
		if (filter != null) {
			Specification<Course> spec = (root, query, criteriaBuilder) -> {
				Predicate predicate = criteriaBuilder.conjunction();
				if (filter != null) {

					if (filter.courseid > 0) {
						predicate = criteriaBuilder.and(predicate,
								criteriaBuilder.equal(root.get("courseId"), filter.courseid));
					}

					if (filter.courseCode != null && !"".equals(filter.courseCode.trim())) {
						predicate = criteriaBuilder.and(predicate,
								criteriaBuilder.equal(root.get("courseCode"), filter.courseCode));

					}

					if (filter.courseStatus != null && !"".equals(filter.courseStatus.trim())) {

						String statusKey = filter.courseStatus.trim();
						int courseStatusID = (statusKey.equalsIgnoreCase("active") ? 1
								: (statusKey.equalsIgnoreCase("inactive")) ? 0 : 100);
						predicate = criteriaBuilder.and(predicate,
								criteriaBuilder.equal(root.get("courseStatus"), courseStatusID));

					}

				}
				return predicate;
			};
			// with filter
			courseList = courseRepository.findAll(spec);

		} else {
			// with out any filter
			courseList = courseRepository.findAll();
		}

		return courseList;
	}

	@Override
	public List<CourseStudent> getStudentListBy(long courseId) {

		Specification<CourseStudent> spec = (root, query, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (courseId > 0) {

				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("course").get("courseId"), courseId));

			}
			return predicate;
		};

		List<CourseStudent> studentlist = courseStudentRepository.findAll(spec);

		return studentlist;
	}

	@Override
	@Transactional
	public boolean deleteStudentFromCourseList(long studentId, long courseId) throws RequestParameterNotValidException {

		if (studentId > 0 && courseId > 0) {
			Specification<CourseStudent> spec = (root, query, criteriaBuilder) -> {
				Predicate predicate = criteriaBuilder.conjunction();

				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("course").get("courseId"), courseId));
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("student").get("stId"), studentId));

				return predicate;
			};
			courseStudentRepository.delete(spec);
			return true;
		} else {
			throw new RequestParameterNotValidException("Student id and Course id might be +ve value");

		}
	}

	@Override
	@Transactional
	public boolean updateStudentCourseRecord(UpdateStudentCourseRecord updateRequest)
			throws RecordNotAvailableException, RequestParameterNotValidException {
		
		if(updateRequest != null && updateRequest.newValue != null && updateRequest.oldValue != null 
				&& updateRequest.oldValue.courseId > 0 && updateRequest.oldValue.studentId > 0 && 
				updateRequest.newValue.courseId > 0 &&  updateRequest.newValue.studentId > 0 ) {
			
			
			Specification<CourseStudent> spec = (root, query, criteriaBuilder) -> {
				Predicate predicate = criteriaBuilder.conjunction();

				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("course").get("courseId"), updateRequest.oldValue.courseId));
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("student").get("stId"), updateRequest.oldValue.studentId));

				return predicate;
			};
			List<CourseStudent> currentRecords =  courseStudentRepository.findAll(spec);
			if(currentRecords != null && currentRecords.size() > 0 ) {
				CourseStudent newStudentCoursRecord = currentRecords.get(0);
				
				
				Student s = newStudentCoursRecord.getStudent();
				s.setStId(updateRequest.newValue.studentId);
				newStudentCoursRecord.setStudent(s);
				
				
				Course c = newStudentCoursRecord.getCourse();
				c.setCourseId(updateRequest.newValue.courseId);
				newStudentCoursRecord.setCourse(c);
				
				
				courseStudentRepository.save(newStudentCoursRecord); 
				
				return true;
			}else {
				throw new RecordNotAvailableException("The student record(s) not found as per old value.!");
			}
			
			
			
		}else {
			throw new RequestParameterNotValidException("Student id and Course id should be +ve in both oldvalue and newvalue in request");
		}
		
		
		
	}

}
