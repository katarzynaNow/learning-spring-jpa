package com.example.learningspringjpa.animal;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class AnimalSpecifications {

    public static Specification<Animal> findBySpeciesAndName(String species, String name){
        return new Specification<Animal>() {
            @Override
            public Predicate toPredicate(Root<Animal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> predicates = new ArrayList<>();
                if(species != null){
                    predicates.add(
                            criteriaBuilder.equal(root.get("species"), species)
                    );
                }

                if(name != null){
                    predicates.add(
                            criteriaBuilder.equal(root.get("name"), name)
                    );
                }

                return criteriaBuilder.and(
                        predicates.toArray(new Predicate[0])
                );
            }
        };
    }
}
