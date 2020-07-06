package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.bookstore.exception.UserNotFoundException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/getBooksForVerification")
	public ResponseEntity<Response> getAllUnverifiedBooks(@RequestHeader("token") String token) throws UserNotFoundException {
		List<BookModel> book = adminService.getAllUnVerifiedBooks(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Getting all the books which are unverified", 200,book));
	}

	@PutMapping("/bookVerification/{sellerId}/{bookId}/{token}")
	public ResponseEntity<Response> bookVerification(@PathVariable("bookId") Long bookId,
													 @PathVariable("sellerId") Long sellerId, @PathVariable("token") String token) throws Exception {
		
		Response verifiedBook =adminService.bookVerification(bookId,sellerId,token);
		return new ResponseEntity<Response>(verifiedBook, HttpStatus.OK);
	}
}