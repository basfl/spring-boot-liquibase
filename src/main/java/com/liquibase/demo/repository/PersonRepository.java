package com.liquibase.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.liquibase.demo.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

 //   @Query("SELECT name FROM persons WHERE name LIKE %:personName%",nativeQuery = true)
    @Query(value="SELECT name FROM persons WHERE name LIKE %:personName% ", nativeQuery=true)
    String findByName(String personName);


}