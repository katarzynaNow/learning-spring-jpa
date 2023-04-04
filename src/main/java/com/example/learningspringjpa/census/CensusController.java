package com.example.learningspringjpa.census;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/census")
public class CensusController {

    @Autowired
    private CensusRepository repository;

   @GetMapping
    public String list(Model model, @RequestParam(required = false) Integer editedId){
       List<Person> people = repository.findAll();
       model.addAttribute("people", people);
       model.addAttribute("editedId", editedId);

       if(editedId != null) {
           model.addAttribute("editPerson", repository.findById(editedId).get());
       }

       return "census/list";
   }

   @GetMapping("/create")
    public String create (Model model){
       Person newPerson = new Person();
       model.addAttribute("newPerson", newPerson);
       return "census/create";
   }

   @PostMapping("/create")
    public String createAction(Person newPerson){
       newPerson.setId(null);
       repository.save(newPerson);
       return "redirect:/census";
   }

   @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
       repository.deleteById(id);
       return "redirect:/census";
   }

   @PostMapping("edit/{id}")
    public String edit(Person person, @PathVariable Integer id){
       Person existing = repository.findById(id).get();
       existing.setFirstName(person.getFirstName());
       existing.setLastName(person.getLastName());
       existing.setBirthDate(person.getBirthDate());
       repository.save(existing);
       return "redirect:/census";
   }
}
