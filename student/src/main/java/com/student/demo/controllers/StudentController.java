package com.student.demo.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.dto.StudentDTO;
import com.student.demo.modle.Marks;
import com.student.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@PostMapping("/save-student")
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDetails) {
		log.info("StudentController :- Inside saveStudent() ");
		StudentDTO student = studentService.saveStudentDetails(studentDetails);
		log.info("StudentController :- End of saveStudent() ");
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/get-all-student-details")
	public ResponseEntity<List<StudentDTO>> getAllStudentsWithMarks() {
		log.info("StudentController :- Inside getAllStudentsWithMarks() ");
		List<StudentDTO> studentDTOs = studentService.getAllStudentsWithMarks();
		log.info("StudentController :- End of getAllStudentsWithMarks() ");
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}

	@GetMapping("/failed-exam")
	public ResponseEntity<Map<String, List<Marks>>> getStudentsWhoFailedExam() {
		log.info("StudentController :- Inside getStudentsWhoFailedExam() ");
		Map<String, List<Marks>> failedStudents = studentService.findStudentsWhoFailedExam();
		log.info("StudentController :- End of getStudentsWhoFailedExam() ");
		return new ResponseEntity<>(failedStudents, HttpStatus.OK);
	}
}
