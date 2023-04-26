package com.example.learningspringjpa.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
}
