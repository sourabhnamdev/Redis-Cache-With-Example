package com.student.demo.dto;

import java.io.Serializable;
import java.util.List;

public class StudentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
