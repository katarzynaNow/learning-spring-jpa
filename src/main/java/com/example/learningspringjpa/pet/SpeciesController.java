package com.example.learningspringjpa.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/species")
public class SpeciesController {

    @Autowired
    private SpeciesRepository repository;

    @GetMapping("/{id}")
    public Species get(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    @PostMapping
    public Species create(@RequestBody String name){
        Species newSpecies = new Species();
        newSpecies.setName(name);
        return repository.save(newSpecies);
    }
}
