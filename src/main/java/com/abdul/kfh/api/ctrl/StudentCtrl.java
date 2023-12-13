package com.abdul.kfh.api.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.kfh.AppConst;
import com.abdul.kfh.api.response.MessageResponse;
import com.abdul.kfh.entity.Student;
import com.abdul.kfh.repository.StudentRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
public class StudentCtrl implements AppConst{
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
		MessageResponse res = new MessageResponse();
		if (studentRepository.existsByStEmail(student.getStEmail())) {
			res.setMessage("This Email  already registered!");
			res.setErrorCode(10);
		}  else {
			// start adding new course here.
			student = studentRepository.save(student);
			if (student != null && student.stId > 0) {
				res.setMessage(ERRORMESSAGE_SUCCESS);
				res.setErrorCode(ERRORCODE_SUCCESS);
			} else {
				res.setMessage(ERRORMESSAGE_UN_EXPECTED);
				res.setErrorCode(ERRORCODE_UN_EXPECTED);
			}

		}

		return ResponseEntity.badRequest().body(res);
	}
	
	
	


	

}
