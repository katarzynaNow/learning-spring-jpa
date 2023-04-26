package com.example.learningspringjpa.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping("/{id}")
    private Pet get(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    @PostMapping
    public Pet create(@RequestBody Pet pet){
        return repository.save(pet);
    }
}
