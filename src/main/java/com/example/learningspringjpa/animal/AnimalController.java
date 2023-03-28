package com.example.learningspringjpa.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalRepository repository;

    @GetMapping
    public List<Animal> list(@RequestParam(required = false) String species){
        if (species == null) {
            return repository.findAll();
        }
        return repository.findAllBySpecies(species);}

    @GetMapping("/{id}")
    public Animal get(@PathVariable Integer id){
        return repository.findById(id)
                .get();}

    @PostMapping
    public Animal create(@RequestBody Animal animal){
        animal.setId(null);
        return repository.save(animal);}

    @PutMapping
    public Animal update(@RequestBody Animal animal){
        Animal existing = get(animal.getId());
        existing.setName(animal.getName());
        existing.setAge(animal.getAge());
        existing.setSpecies(animal.getSpecies());
        return repository.save(existing);}

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id){
        repository.deleteById(id);}}

