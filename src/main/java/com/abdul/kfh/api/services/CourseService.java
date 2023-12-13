package com.abdul.kfh.api.services;

import java.util.List;

import com.abdul.kfh.api.exceptions.RecordNotAvailableException;
import com.abdul.kfh.api.exceptions.RequestParameterNotValidException;
import com.abdul.kfh.api.request.CourseFilter;
import com.abdul.kfh.api.request.UpdateStudentCourseRecord;
import com.abdul.kfh.entity.Course;
import com.abdul.kfh.entity.CourseStudent;

public interface CourseService {
	
	public List<Course> courseFilterBy(CourseFilter filter);
	
	public List<CourseStudent> getStudentListBy(long courseId);
	public boolean deleteStudentFromCourseList(long studentId, long courseId) throws  RequestParameterNotValidException;
	public boolean updateStudentCourseRecord(UpdateStudentCourseRecord updateRequest) throws RecordNotAvailableException,RequestParameterNotValidException;

}
