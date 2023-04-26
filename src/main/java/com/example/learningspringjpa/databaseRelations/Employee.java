package com.example.learningspringjpa.databaseRelations;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    private String position;

    @OneToOne
    @JoinColumn
    private Human human;

    @OneToMany(mappedBy = "employee")
    private List<Duty> duties;

    public List<Duty> getDuties() {
        return duties;
    }

    public void setDuties(List<Duty> duties) {
        this.duties = duties;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human person) {
        this.human = person;
    }
}
