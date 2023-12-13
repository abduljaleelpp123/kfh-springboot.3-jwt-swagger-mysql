package com.abdul.kfh.api.ctrl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.kfh.AppConst;
import com.abdul.kfh.api.request.CourseFilter;
import com.abdul.kfh.api.response.MessageResponse;
import com.abdul.kfh.api.services.CourseService;
import com.abdul.kfh.entity.Course;
import com.abdul.kfh.repository.CourseRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course/")
public class CourseCtrl implements AppConst {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseService courseService;

	@PostMapping("/add")
	public ResponseEntity<?> addCourse(@Valid @RequestBody Course course) {
		MessageResponse res = new MessageResponse();
		if (courseRepository.existsByCourseName(course.getCourseName())) {
			res.setMessage("This course name  already in our database!");
			res.setErrorCode(10);
		} else if (courseRepository.existsByCourseCode(course.getCourseCode())) {
			res.setMessage("This course code  already in our database!");
			res.setErrorCode(11);
		} else {
			// start adding new course here.
			course = courseRepository.save(course);
			if (course != null && course.courseId > 0) {
				res.setMessage(ERRORMESSAGE_SUCCESS);
				res.setErrorCode(ERRORCODE_SUCCESS);
			} else {
				res.setMessage(ERRORMESSAGE_UN_EXPECTED);
				res.setErrorCode(ERRORCODE_UN_EXPECTED);
			}

		}

		return ResponseEntity.badRequest().body(res);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCourse(@Valid @RequestBody Course course) {

		MessageResponse res = new MessageResponse();

		

		if (courseRepository.existsByCourseName(course.getCourseName())) {
			res.setMessage("This course name  already in our database!");
			res.setErrorCode(10);
		} else {
			CourseFilter filter = new CourseFilter();
			filter.courseid = course.getCourseId();
			List<Course> existingCourselist = courseService.courseFilterBy(filter);
			System.out.println(" course ID  >>>>>>>>>>>>>");
			if (existingCourselist != null && !existingCourselist.isEmpty()) {
				course = courseRepository.save(course);
				if (course != null && course.courseId > 0) {
					res.setMessage(ERRORMESSAGE_SUCCESS);
					res.setErrorCode(ERRORCODE_SUCCESS);
				} else {
					res.setMessage(ERRORMESSAGE_UN_EXPECTED);
					res.setErrorCode(ERRORCODE_UN_EXPECTED);
				}
			} else {
				
				res.setMessage("This course not exist!");
				res.setErrorCode(23);
			}

		}

		return ResponseEntity.badRequest().body(res);
	}
	
	
	@GetMapping("get/{id}")
	  public ResponseEntity<?> getCourseById(@PathVariable("id") long id) {
	    Optional<Course> course = courseRepository.findById(id);
	    MessageResponse res = new MessageResponse();
	    if (course.isPresent()) {
	    	res.setErrorCode(ERRORCODE_SUCCESS);
	    	res.setMessage(ERRORMESSAGE_SUCCESS);
	    	res.setResponsevalue(course.get());
	      
	    } else {
	    	res.setErrorCode(10);
	    	res.setMessage("No record(s) found");
	    	res.setResponsevalue(null);
	    }
	    return ResponseEntity.badRequest().body(res);
	  }
	
	
	@GetMapping("get")
	  public ResponseEntity<?> getAllCourses() {
	    List<Course> courseList = courseRepository.findAll();
	    MessageResponse res = new MessageResponse();
	    if (courseList != null && !courseList.isEmpty()) {
	    	res.setErrorCode(ERRORCODE_SUCCESS);
	    	res.setMessage(ERRORMESSAGE_SUCCESS);
	    	res.setResponsevalue(courseList);
	      
	    } else {
	    	res.setErrorCode(12);
	    	res.setMessage("No record(s) found");
	    	res.setResponsevalue(null);
	    }
	    return ResponseEntity.badRequest().body(res);
	  }
	
	
	@DeleteMapping("delete/{id}")
	  public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		MessageResponse res = new MessageResponse();
		 try {
			 courseRepository.deleteById(id);
			 res.setErrorCode(ERRORCODE_SUCCESS);
		     res.setMessage(ERRORMESSAGE_SUCCESS);
		    } catch (Exception e) {
		    	res.setErrorCode(15);
		    	res.setMessage("Some error occored : "+e.getMessage());
		    	res.setResponsevalue(null);
		    }
		
		 return ResponseEntity.badRequest().body(res);
		
	    
	   
	    
	  }
	
	
	
	
}
