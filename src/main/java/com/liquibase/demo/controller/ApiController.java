package com.liquibase.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liquibase.demo.entity.Person;
import com.liquibase.demo.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		System.out.println("*********************************************");
		try {
			List<Person> persons = new ArrayList<Person>();
			 persons = (List<Person>) personRepository.findAll();

			

			if (persons.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(persons, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("error is "+e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping("/persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		try {
			 Person payload = Person.builder().name(person.getName()).height(person.getHeight()).build();
			Person savedPerson = personRepository.save(payload);
			return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
