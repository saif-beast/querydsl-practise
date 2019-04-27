package com.influido.poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CrudController<T,I> {

    @PostMapping("/create")
    ResponseEntity create(T t) throws JsonProcessingException;


    @PutMapping("/update")
    ResponseEntity update(T t);

    @GetMapping("")
    ResponseEntity get(I id);

    @DeleteMapping("")
    void delete(I id);

}
