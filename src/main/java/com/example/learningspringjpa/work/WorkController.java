package com.example.learningspringjpa.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkRepository repository;

    @GetMapping
    public List<Work> list(@RequestParam(required = false) String position){
      if (position == null) {
          return repository.findAll();
      }
      return repository.findAllByPosition(position);
    }

    @GetMapping("/{id}")
    public Work get(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    @PostMapping
    public Work create(@RequestBody Work work) {
        work.setId(null);
        return repository.save(work);
    }

    @PutMapping
    public Work update(@RequestBody Work work) {
       Work existing = get(work.getId());
        existing.setPosition(work.getPosition());
        existing.setCompany(work.getCompany());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
