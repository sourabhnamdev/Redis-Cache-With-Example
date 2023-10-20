package com.student.demo.dto;

public class ThirdPartyApiRequest {
	private String url;
	private String consumerKey;
	private String secret;
	private String bookId;

	// get set
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public ThirdPartyApiRequest(String url, String consumerKey, String secret, String bookId) {
		super();
		this.url = url;
		this.consumerKey = consumerKey;
		this.secret = secret;
		this.bookId = bookId;
	}

	public ThirdPartyApiRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
