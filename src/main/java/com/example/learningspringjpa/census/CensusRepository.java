package com.example.learningspringjpa.census;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CensusRepository extends JpaRepository <Person, Integer> {
}
