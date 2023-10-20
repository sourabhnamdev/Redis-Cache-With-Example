package com.student.demo.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.student.demo.dto.ThirdPartyApiRequest;

@RestController
public class ApiController {

	@PostMapping("/callThirdPartyApi")
	public ResponseEntity<String> callThirdPartyApi(@RequestBody ThirdPartyApiRequest request) {
		// Set up RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Prepare headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Your User Agent");
		headers.set("Authorization", "Your Authorization Header");
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		headers.set("Cookie", "JSESSIONID=YourSessionID");

		// Prepare the request body
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		// Add your request parameters here

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

		// Make the HTTP request to the third-party API
		ResponseEntity<String> response = restTemplate.exchange(
				"prodlti.kitaboo.com/lti/launch?oauth_consumer_key=LTE6Y2xpZW50MjcwNzoyNzA3", HttpMethod.POST, entity,
				String.class);

		// Read and return the response headers
		HttpHeaders responseHeaders = response.getHeaders();

		return new ResponseEntity<>(responseHeaders.toString(), HttpStatus.OK);
	}
}
