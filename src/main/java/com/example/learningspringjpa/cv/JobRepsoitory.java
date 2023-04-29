package com.example.learningspringjpa.cv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepsoitory extends JpaRepository<Job, Integer> {
}
