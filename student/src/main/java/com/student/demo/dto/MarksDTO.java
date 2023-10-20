package com.student.demo.dto;

import java.io.Serializable;

public class MarksDTO implements Serializable {
	private Integer marks;
	private String subject;

//	get set
	public Integer getMarks() {
		return marks;
	}

	public String getSubject() {
		return subject;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
