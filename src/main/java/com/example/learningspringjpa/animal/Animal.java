package com.example.learningspringjpa.animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String species;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public String getSpecies() {return species;}

    public void setSpecies(String species) {
        this.species = species;}}

