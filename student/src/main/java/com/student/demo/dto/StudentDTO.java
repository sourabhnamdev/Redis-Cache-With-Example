package com.student.demo.dto;

import java.util.List;

public class StudentDTO {
	private String name;
	private List<MarksDTO> subjectDetails;

	public List<MarksDTO> getSubjectDetails() {
		return subjectDetails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubjectDetails(List<MarksDTO> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}
}
