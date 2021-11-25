package com.example.demo.controller;

import com.example.demo.DAO.PersonDao;
import com.example.demo.Model.Person;
import com.example.demo.Model.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @PostMapping(value = "SpringBootCrudService/addPerson",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws Exception {
        return ResponseEntity.ok(personDao.create(person));
    }

    @GetMapping(value = "/SpringBootCrudService/person/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Person> getPersonByUrl(@PathVariable("id") int id) throws Exception {
        Person personRead = personDao.read(id);

        return personRead == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(personRead);
    }

    @GetMapping(value = "SpringBootCrudService/allPersons",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PersonList> getAllPersons() throws Exception {
        return ResponseEntity.ok(personDao.readAll());
    }

    @PutMapping(value = "SpringBootCrudService/updatePerson",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws Exception {
        Person personRead = personDao.read(person.getId());
        if (personRead == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //todo: error cannot find person
        }

        return ResponseEntity.ok(personDao.updatePerson(person));
    }

    @DeleteMapping(value = "SpringBootCrudService/deletePerson/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Person> deletePerson(@PathVariable("id") int id) throws Exception {
        Person person = personDao.read(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        personDao.delete(person.getId());
        return ResponseEntity.ok(person);
    }

    @GetMapping(value = "SpringBootCrudService/welcome",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Willkommen bei der Personenverwaltung.");
    }

}