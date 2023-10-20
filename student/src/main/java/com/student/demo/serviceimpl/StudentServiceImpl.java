package com.student.demo.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.student.demo.dto.MarksDTO;
import com.student.demo.dto.StudentDTO;
import com.student.demo.modle.Marks;
import com.student.demo.modle.Student;
import com.student.demo.repository.StudentRepo;
import com.student.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepo studentRepository;

	@Override
	public StudentDTO saveStudentDetails(StudentDTO studentDetails) {
		log.info("StudentServiceImpl :- Saving Student Details.");
		Student student = new Student();
		student.setName(studentDetails.getName());

		List<Marks> marksList = new ArrayList<>();

		for (MarksDTO marksDTO : studentDetails.getSubjectDetails()) {
			Marks marks = new Marks();
			marks.setSubject(marksDTO.getSubject());
			marks.setMarks(marksDTO.getMarks());

			// Set the student for each mark
			marks.setStudent(student);

			if (marksDTO.getMarks() > 32) {
				marks.setResult("PASS");
			} else {
				marks.setResult("FAIL");
			}
			if (marks.getResult().isEmpty()) {
				marks.setIsExamGiven(false);
			} else {
				marks.setIsExamGiven(true);
			}
			marksList.add(marks);
		}

		student.setMarks(marksList);

		Student savedStudent = studentRepository.save(student);
		StudentDTO dto = new StudentDTO();
		dto.setName(savedStudent.getName());
		dto.setSubjectDetails(studentDetails.getSubjectDetails());
		return dto;
	}

//	@Cacheable(value = "Student.class",key = "#id")
	@Cacheable(value = "myCache", key = "'allStudents'")
	public List<StudentDTO> getAllStudentsWithMarks() {
		log.info("StudentServiceImpl :- Fetching all students with marks.");
		List<Student> students = studentRepository.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<>();

		for (Student student : students) {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setName(student.getName());

			List<MarksDTO> marksDTOs = student.getMarks().stream().map(mark -> {
				MarksDTO marksDTO = new MarksDTO();
				marksDTO.setSubject(mark.getSubject());
				marksDTO.setMarks(mark.getMarks());
				return marksDTO;
			}).collect(Collectors.toList());

			studentDTO.setSubjectDetails(marksDTOs);
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

	@Override
//	@Cacheable(value = "Student.class",key = "#id")
	@Cacheable(value = "myFailedCache", key = "'allFailedStudents'")
	public Map<String, List<Marks>> findStudentsWhoFailedExam() {
		log.info("StudentServiceImpl :- Fetching Students Who Failed In Exam.");
		List<Student> findStudentsWhoFailedExam = studentRepository.findStudentsWhoFailedExam();
		Map<String, List<Marks>> data = new HashMap<>();

		for (Student student : findStudentsWhoFailedExam) {

			data.put(student.getName(), student.getMarks());
		}
		return data;
	}

}
