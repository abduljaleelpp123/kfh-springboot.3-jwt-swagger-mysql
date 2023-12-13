package com.abdul.kfh.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdul.kfh.entity.Student;
import com.abdul.kfh.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository  studentRepository;

	@Override
	public List<Student> getAllComments() {
		
		return studentRepository.findAll();
	}

	

	

}
