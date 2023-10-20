package com.student.demo.modle;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marks implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private Integer marks;
	private String result;
	private Boolean isExamGiven;

	@ManyToOne
	@JoinColumn(name = "student_id")
	@JsonIgnore
	private Student student;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public Integer getMarks() {
		return marks;
	}

	public Student getStudent() {
		return student;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setIsExamGiven(Boolean isExamGiven) {
		this.isExamGiven = isExamGiven;

	}

	public Boolean getIsExamGiven() {
		return isExamGiven;
	}

}
