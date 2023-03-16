package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	@GetMapping("/http/get")
    public String getTest() {
    	
    	return "get요청";
    	
    }
	@PostMapping("/http/post")
    public String postTest() {
    	
    	return "post";
    	
    }
	@PutMapping("/http/put")
    public String putTest() {
    	
    	return "put";
    	
    }
	@DeleteMapping("/http/delete")
    public String deleteTest() {
    	
    	return "delete";
    	
    }
    
}
