package com.abdul.kfh.api.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.kfh.AppConst;
import com.abdul.kfh.api.exceptions.RecordNotAvailableException;
import com.abdul.kfh.api.exceptions.RequestParameterNotValidException;
import com.abdul.kfh.api.request.CourseRegistraion;
import com.abdul.kfh.api.request.UpdateStudentCourseRecord;
import com.abdul.kfh.api.response.MessageResponse;
import com.abdul.kfh.api.services.CourseService;
import com.abdul.kfh.entity.Course;
import com.abdul.kfh.entity.CourseStudent;
import com.abdul.kfh.entity.Student;
import com.abdul.kfh.repository.CourseStudentRepository;

import jakarta.validation.Valid;

@RestController
public class CourseRegisterCtrl implements AppConst {
	
	@Autowired
	CourseStudentRepository courseStudentRepository; 
	
	
	@Autowired 
	CourseService courseService;
	
	@PostMapping("/student-allocate-to-course")
	public ResponseEntity<?> studentCourseRegistraion(@Valid @RequestBody CourseRegistraion courseRegistraion) {
		MessageResponse res = new MessageResponse();
		CourseStudent cs = new CourseStudent();
		
		
		Student s = new Student();
		s.setStId(courseRegistraion.studentId);
		
		Course c = new Course();
		c.setCourseId(courseRegistraion.courseId);
		cs.setCourse(c);
		cs.setStudent(s);
		cs = courseStudentRepository.save(cs);
		if (cs != null && cs.id > 0) {
			res.setMessage(ERRORMESSAGE_SUCCESS);
			res.setErrorCode(ERRORCODE_SUCCESS);
		} else {
			res.setMessage(ERRORMESSAGE_UN_EXPECTED);
			res.setErrorCode(ERRORCODE_UN_EXPECTED);
		}
		return ResponseEntity.badRequest().body(res);
	}
	
	
	@PutMapping("/student-course-change")
	public ResponseEntity<?> updateStudentCourseRegistraion(@Valid @RequestBody UpdateStudentCourseRecord updateRequest) {
		MessageResponse res = new MessageResponse();
		
		boolean isUpdated;
		try {
			isUpdated = courseService.updateStudentCourseRecord(updateRequest);
			if (isUpdated) {
				res.setMessage(ERRORMESSAGE_SUCCESS);
				res.setErrorCode(ERRORCODE_SUCCESS);
			} else {
				res.setMessage("The student's course update cannot be completed at this time.!");
				res.setErrorCode(234);
			}
		} catch (RecordNotAvailableException e) {
			
			e.printStackTrace();
			
			res.setMessage(e.getMessage());
			res.setErrorCode(24);
		} catch (RequestParameterNotValidException e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setErrorCode(25);
			
		}
		
		
		
		
		
		return ResponseEntity.badRequest().body(res);
	}
	
	
	
	@GetMapping("getstudentListbyCourseID/{courseId}")
	  public ResponseEntity<?> getStudentListByCourseId(@PathVariable("courseId") long id) {
	   
		List<CourseStudent> courseStudentList = courseService.getStudentListBy(id);
		
		 
	    MessageResponse res = new MessageResponse();
	    if (courseStudentList != null && ! courseStudentList.isEmpty() ) {
	    	res.setErrorCode(ERRORCODE_SUCCESS);
	    	res.setMessage(ERRORMESSAGE_SUCCESS);
	    	res.setResponsevalue(courseStudentList);
	      
	    } else {
	    	res.setErrorCode(10);
	    	res.setMessage("No record(s) found");
	    	res.setResponsevalue(null);
	    }
	    return ResponseEntity.badRequest().body(res);
	  }
	
	
	
	@PostMapping("removeStudentFromCourseList")
	  public ResponseEntity<?> removeStudentFromCourseList(@Valid @RequestBody CourseRegistraion courseRegistraion) {
		
		MessageResponse res = new MessageResponse();
		
		try {
			courseService.deleteStudentFromCourseList(courseRegistraion.courseId, courseRegistraion.studentId);
			res.setErrorCode(ERRORCODE_SUCCESS);
	    	res.setMessage(ERRORMESSAGE_SUCCESS);
	    	
		} catch (RequestParameterNotValidException e) {
			
			e.printStackTrace();
			res.setErrorCode(200);
	    	res.setMessage(e.getMessage());
		}
		    
	    return ResponseEntity.badRequest().body(res);
		
		
		
		
		
		 
	    
	  }
	
	
	

}
