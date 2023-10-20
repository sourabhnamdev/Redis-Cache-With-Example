package com.student.demo.service;

import java.util.List;
import java.util.Map;

import com.student.demo.dto.StudentDTO;
import com.student.demo.modle.Marks;

public interface StudentService {
	public List<StudentDTO> getAllStudentsWithMarks();

	public StudentDTO saveStudentDetails(StudentDTO studentDetails);

	public Map<String, List<Marks>> findStudentsWhoFailedExam();
	
}
