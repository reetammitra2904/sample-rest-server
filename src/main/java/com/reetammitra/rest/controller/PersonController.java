package com.reetammitra.rest.controller;


import com.reetammitra.rest.data.DataLoader;
import com.reetammitra.rest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PersonController {

    @Autowired
    private DataLoader dataLoader;

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        Person person = dataLoader.getPerson(id);
        if (Objects.isNull(person)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
