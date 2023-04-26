package com.example.learningspringjpa.databaseRelations;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Human {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "human")
    private Employee employee;

    @ManyToMany
    @JoinTable
    private List<Adress> adresses;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
