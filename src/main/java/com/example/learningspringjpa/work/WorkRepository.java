package com.example.learningspringjpa.work;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Integer> {

    List<Work> findAllByPosition(String position);
}
