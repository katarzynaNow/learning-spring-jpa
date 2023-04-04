package com.example.learningspringjpa.toDo;

import com.example.learningspringjpa.toDo.model.ToDo;
import com.example.learningspringjpa.toDo.model.ToDoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping
    public String list(Model model, @RequestParam(required = false) Integer editedId){
        List<ToDo> toDoList = toDoRepository.findAll();
        ToDo newToDo = new ToDo();
        ToDoStatus[] statuses = ToDoStatus.values();

        model.addAttribute("todos", toDoList);
        model.addAttribute("newToDo", newToDo);
        model.addAttribute("statuses", statuses);
        model.addAttribute("editedId", editedId);

        if(editedId != null) {
            model.addAttribute("editToDo", toDoRepository.findById(editedId).get());
        }
        return "list-todo";
    }

    @PostMapping
    public String create (ToDo toDo){
        toDo.setId(null);
        toDo.setStatus(ToDoStatus.NEW);
        toDoRepository.save(toDo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        toDoRepository.deleteById(id);
        return "redirect:/todos";
    }

    @PostMapping("/edit/{id}")
    public String edit(ToDo toDo, @PathVariable Integer id) {
        ToDo existing = toDoRepository.findById(id).get();
        existing.setStatus(toDo.getStatus());
        existing.setDeadline(toDo.getDeadline());
        existing.setTask(toDo.getTask());
        toDoRepository.save(existing);

        return "redirect:/todos";
    }
}
