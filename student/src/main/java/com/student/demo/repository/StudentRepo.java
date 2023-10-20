package com.student.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.demo.modle.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	List<Student> findAll();

	@Query("SELECT s FROM Student s JOIN FETCH s.marks m WHERE m.isExamGiven = true AND m.result = 'fail'")
	List<Student> findStudentsWhoFailedExam();

}
